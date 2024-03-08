package modele.centreControle;
import modele.communication.Message;
import modele.communication.Commande;
import modele.communication.Messagetest;
import modele.communication.Nack;
import modele.communication.Status;
import modele.satelliteRelai.SatelliteRelai;
import modele.communication.TransporteurMessage;

public class CentreControle extends TransporteurMessage {
	private SatelliteRelai relai;
	
	
	public CentreControle(SatelliteRelai relai) {
		this.relai=relai;
	}
//	public void gestionnaireCommande(eCommande commande){
//		switch(commande) {
//		case Status
//		}
//	}

	@Override
	protected void envoyerMessage(Message msg) {
		// TODO Auto-generated method stub
		if(!(msg instanceof Nack)) {
			Envoye.add(msg);
			//System.out.println(msg.getClass());	
			//System.out.print(msg.getCompte());
		}
		relai.envoyerMessageVersRover(msg);
		
	}

	@Override
	protected void gestionnaireMessage(Message msg) {
		// TODO Auto-generated method stub
		//System.out.println(msg.getClass());
		//System.out.println(msg.getCompte());
		switch(msg.getClass().toString()) {
		case "Status":
			gestionStatus((Status)msg);
		break;
		
		}
		
	}
	private void gestionStatus(Status stat) {
		System.out.println("Status recu");
		System.out.println("position du rover  x:"+stat.getVect().getLx()+"y:"+stat.getVect().getLy());
		
	}
	
	
	public void seqTest() {
		Commande msgCom=null;
	}
	
	
	
	public void testCom() {
		//System.out.print("preparation Envoie");
		Message m1=new Messagetest(compteurMsg.getCompteActuel());
		envoyerMessage(m1);
		Message m2=new Messagetest(compteurMsg.getCompteActuel());
		envoyerMessage(m2);
		Message m3=new Messagetest(compteurMsg.getCompteActuel());
		envoyerMessage(m3);
		Message m4=new Messagetest(compteurMsg.getCompteActuel());
		envoyerMessage(m4);
		Message m5=new Messagetest(compteurMsg.getCompteActuel());
		envoyerMessage(m5);
		Message m6=new Messagetest(compteurMsg.getCompteActuel());
		envoyerMessage(m6);
		Message m7=new Messagetest(compteurMsg.getCompteActuel());
		envoyerMessage(m7);
		Message m8=new Messagetest(compteurMsg.getCompteActuel());
		envoyerMessage(m8);
		
	}

}
