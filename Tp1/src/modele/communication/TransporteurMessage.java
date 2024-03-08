package modele.communication;
/**
 * Classe qui implémente le protocol de communication entre le Rover
 * et le Centre d'opération.
 * 
 * Il se base sur une interprétation libre du concept de Nack:
 * 	https://webrtcglossary.com/nack/
 *  
 * Les messages envoyés sont mémorisé. À l'aide du compte unique
 * le transporteur de message peut identifier les Messages manquant
 * dans la séquence et demander le renvoi d'un Message à l'aide du Nack.
 * 
 * Pour contourner la situation ou le Nack lui-même est perdu, le Nack
 * est renvoyé periodiquement, tant que le Message manquant n'a pas été reçu.
 * 
 * C'est également cette classe qui gère les comptes unique.
 * 
 * Les messages reçu sont mis en file pour être traité.
 * 
 * La gestion des messages reçu s'effectue comme une tâche s'exécutant indépendamment (Thread)
 * 
 * Quelques détails:
 *  - Le traitement du Nack a priorité sur tout autre message.
 *  - Un message NoOp est envoyé périodiquement pour s'assurer de maintenir
 *    une communication active et identifier les messages manquants en bout de séquence.
 * 
 * Services offerts:
 *  - TransporteurMessage
 *  - receptionMessageDeSatellite
 *  - run
 * 
 * @author Frederic Simard, ETS
 * @version Hiver, 2024
 */

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public abstract class TransporteurMessage extends Thread {
	
	// compteur de message
	protected CompteurMessage compteurMsg;
	// lock qui protège la liste de messages reçu
	private ReentrantLock lock = new ReentrantLock();
	
	protected ArrayList<Message> list;
	protected ArrayList<Message> Envoye;
	
	/**
	 * Constructeur, initialise le compteur de messages unique
	 */
	public TransporteurMessage() {
		compteurMsg = new CompteurMessage();	
		list= new ArrayList<>();
		Envoye= new ArrayList<>();
		
	}
	
	/**
	 * Méthode gérant les messages reçu du satellite. La gestion se limite
	 * à l'implémentation du Nack, les messages spécialisé sont envoyés
	 * aux classes dérivés
	 * @param msg, message reçu
	 */
	public void receptionMessageDeSatellite(Message msg) {
		lock.lock();
		try {
			if(msg instanceof Nack) {
				list.add(0, msg);
			}else if(list.size()!=0){
				for(int i =list.size()-1; i>=0;i--) {
					if((msg.getCompte()>list.get(i).getCompte())||(list.get(i) instanceof Nack)) {
						list.add(i+1,msg);
						i=-1;
					}
					if(i==0) {
						list.add(0,msg);
					}
				}
			}
			else {
				list.add(msg);
			}
			
		}finally {
			lock.unlock();
		}
	}

	@Override
	/**
	 * Tâche effectuant la gestion des messages reçu
	 */
	public void run() {
		
		int compteCourant = 0;
		boolean nackSent;
		boolean found;
		
		while(true) {
			
			lock.lock();
			
			try {
				nackSent=false;
				found=false;
				while(!(list.isEmpty())&&(!(nackSent))) {
					if(list.get(0) instanceof Nack) {
						while((Envoye.size()>0) && (!(found))) {
							if(list.get(0).getCompte() > Envoye.get(0).getCompte()||((Envoye.get(0) instanceof Nack))) {
								Envoye.remove(0);
								
							}
							else {
								envoyerMessage(Envoye.get(0));
								list.remove(0);
								found=true;
							}
						}
					}
					else if(list.get(0).getCompte()>compteCourant) {
						envoyerMessage( new Nack(compteCourant));
						nackSent=true;
					}
					else if(list.get(0).getCompte()<compteCourant) {
						list.remove(0);
					}
					else {
						gestionnaireMessage(list.get(0));
						list.remove(0);
						compteCourant++;
					}
				}
			/////////////////////////
			if(nackSent==false) {
			envoyerMessage(new NoOp(compteurMsg.getCompteActuel()));
			}			
			}finally{
				lock.unlock();
			}
			
			// pause, cycle de traitement de messages
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * méthode abstraite utilisé pour envoyer un message
	 * @param msg, le message à envoyer
	 */
	abstract protected void envoyerMessage(Message msg);

	/**
	 * méthode abstraite utilisé pour effectuer le traitement d'un message
	 * @param msg, le message à traiter
	 */
	abstract protected void gestionnaireMessage(Message msg);

	

}
