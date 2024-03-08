package modele.rover;
import modele.communication.Commande;
import modele.communication.Message;
import modele.communication.Status;
import utilitaire.Vect2D;
import modele.communication.eCommande;
import modele.satelliteRelai.SatelliteRelai;
import modele.communication.TransporteurMessage;
public class Rover extends TransporteurMessage {
	static final double VITESSE_MparS = 0.5;
	private SatelliteRelai relai;
	private Vect2D position;
	
	
	public Rover(SatelliteRelai relai,Vect2D v) {
		position=v;
		this.relai=relai;
	}
	
	
	
	private void deplacerRover(Vect2D vect) {
		Vect2D deplacement = position.calculerDiff(vect);
		double longueur = deplacement.Longueur();
		double temps = longueur/VITESSE_MparS;
		double angle = deplacement.getAngle();
		envoyerMessage(new Status(compteurMsg.getCompteActuel(),position));
		for(int i= 0;i < temps;i++) {
			position.ajouter(Math.cos(angle)*VITESSE_MparS,Math.sin(angle)*VITESSE_MparS);//********************
			envoyerMessage(new Status(compteurMsg.getCompteActuel(),position));
		}
		temps = temps%1.0;
		position.ajouter(Math.cos(angle)*VITESSE_MparS*temps,Math.sin(angle)*VITESSE_MparS*temps);//********************
		envoyerMessage(new Status(compteurMsg.getCompteActuel(),position));
	}
	
	
	
	
	public void gestionnaireCommande(Commande commande) {
		switch(commande.getCommande().DEPLACER_ROVER) {
		case NULLE:
			
		break;
		case DEPLACER_ROVER:
			deplacerRover(commande.getVect());
		break;
		case PRENDRE_PHOTOS:
		break;
		
		}
	}
	@Override
	protected void envoyerMessage(Message msg) {
		// TODO Auto-generated method stub
		relai.envoyerMessageVersCentrOp(msg);
		
	}

	@Override
	protected void gestionnaireMessage(Message msg) {
		// TODO Auto-generated method stub
		System.out.println(msg.getClass());
		System.out.println(msg.getCompte());
		if(msg instanceof Commande) {
			gestionnaireCommande((Commande)msg);
		}
	}
	
	public void testStat() {
		envoyerMessage(new Status(compteurMsg.getCompteActuel(),position));
	}

}
