package modele.satelliteRelai;

/**
 * Classe simulant le satellite relai
 * 
 * Le satellite ne se contente que de transferer les messages du Rover vers le centre de contrôle
 * et vice-versa.
 * 
 * Le satellite exécute des cycles à intervale de TEMPS_CYCLE_MS. Période à
 * laquelle tous les messages en attente sont transmis. Ceci est implémenté à
 * l'aide d'une tâche (Thread).
 * 
 * Le relai satellite simule également les interférence dans l'envoi des messages.
 * 
 * Services offerts:
 *  - lierCentrOp
 *  - lierRover
 *  - envoyerMessageVersCentrOp
 *  - envoyerMessageVersRover
 * 
 * @author Frederic Simard, ETS
 * @version Hiver, 2024
 */

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;
import utilitaire.FileSchainer;

import modele.communication.Message;

public class SatelliteRelai extends Thread{
	
	static final int TEMPS_CYCLE_MS = 500;
	static final double PROBABILITE_PERTE_MESSAGE = 0.15;
	
	ReentrantLock lock = new ReentrantLock();
	
	private Random rand = new Random();
	private FileSchainer messageRover = new FileSchainer();
	private FileSchainer messageControl = new FileSchainer();
	
	
	/**
	 * Méthode permettant d'envoyer un message vers le centre d'opération
	 * @param msg, message à envoyer
	 */
	public void envoyerMessageVersCentrOp(Message msg) {
		
		lock.lock();
		
		try {
			double proba = (double) (Math.random() * (1.00 - 0.00)) + 0.00;
			if(proba>PROBABILITE_PERTE_MESSAGE) {
				messageControl.push(msg);
			}
			
		}finally {
			lock.unlock();
		}
	}
	
	/*
	 * Méthode permettant d'envoyer un message vers le rove
	 * @param msg, message à envoyer
	 */
	public void envoyerMessageVersRover(Message msg) {
		lock.lock();
		
		try {

			double proba = (double) (Math.random() * (1.00 - 0.00)) + 0.00;
			if(proba>PROBABILITE_PERTE_MESSAGE) {
				messageRover.push(msg);
			}
			
		}finally {
			lock.unlock();
		}
	}

	@Override
	public void run() {
		
		while(true) {
			
			/*
			 * (5.3) Insérer votre code ici 
			 */

			// attend le prochain cycle
			try {
				Thread.sleep(TEMPS_CYCLE_MS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	

}
