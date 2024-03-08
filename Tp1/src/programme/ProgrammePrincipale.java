package programme;




import utilitaire.FileSchainer;
import utilitaire.Vect2D;

import java.io.IOException;

import modele.satelliteRelai.SatelliteRelai;
import modele.centreControle.CentreControle;
import modele.rover.Rover;
public class ProgrammePrincipale {

	/**
	 * Programme principale, instancie les éléments de la simulation,
	 * les lie entre eux, puis lance la séquence de test.
	 * @param args, pas utilisé
	 */
	public static void main(String[] args){
	
	
		SatelliteRelai satellite = new SatelliteRelai();
		Rover rover=new Rover(satellite, new Vect2D(50,50));
		CentreControle controle = new CentreControle(satellite);
		satellite.lierCentrOp(controle);
		satellite.lierRover(rover);
		
		satellite.start();
		rover.start();
		controle.start();
		
		controle.testCom();
		//rover.testStat();
		
		
	}

}
