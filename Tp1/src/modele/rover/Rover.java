package modele.rover;
import modele.communication.Message;
import modele.satelliteRelai.SatelliteRelai;
import modele.communication.TransporteurMessage;
public class Rover extends TransporteurMessage {
	private SatelliteRelai relai;
	
	
	public Rover(SatelliteRelai relai) {
		this.relai=relai;
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
	}

}
