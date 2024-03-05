package modele.centreControle;
import modele.communication.Message;
import modele.communication.Messagetest;
import modele.communication.Nack;
import modele.satelliteRelai.SatelliteRelai;
import modele.communication.TransporteurMessage;

public class CentreControle extends TransporteurMessage {
	private SatelliteRelai relai;
	
	
	public CentreControle(SatelliteRelai relai) {
		this.relai=relai;
	}

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
		
	}
	public void testCom() {
		//System.out.print("preparation Envoie");
		Message m1=new Messagetest(compteurMsg.getCompteActuel());
		Message m2=new Messagetest(compteurMsg.getCompteActuel());
		Message m3=new Messagetest(compteurMsg.getCompteActuel());
		Message m4=new Messagetest(compteurMsg.getCompteActuel());
		Message m5=new Messagetest(compteurMsg.getCompteActuel());
		Message m6=new Messagetest(compteurMsg.getCompteActuel());
		Message m7=new Messagetest(compteurMsg.getCompteActuel());
		Message m8=new Messagetest(compteurMsg.getCompteActuel());
		envoyerMessage(m1);
		envoyerMessage(m2);
		envoyerMessage(m3);
		envoyerMessage(m4);
		envoyerMessage(m5);
		envoyerMessage(m6);
		envoyerMessage(m7);
		envoyerMessage(m8);
		
	}

}
