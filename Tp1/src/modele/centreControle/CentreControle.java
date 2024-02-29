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
			System.out.print(msg.getCompte());
		}
		relai.envoyerMessageVersRover(msg);
		
	}

	@Override
	protected void gestionnaireMessage(Message msg) {
		// TODO Auto-generated method stub
		
	}
	public void testCom() {
		System.out.print("preparation Envoie");
		//Message m1=new Messagetest(0);
		//Message m2=new Messagetest(1);
		//envoyerMessage(m1);
		//envoyerMessage(m2);
		
	}

}
