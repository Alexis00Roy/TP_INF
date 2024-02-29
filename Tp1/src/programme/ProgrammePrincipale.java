package programme;



import utilitaire.FileSchainer;
import utilitaire.Vect2D;

public class ProgrammePrincipale {

	public static void main(String[] args) {
		Vect2D vect= new Vect2D(2,3);
		Vect2D vect2= new Vect2D(3,4);
		System.out.print(vect.calculerDiff(vect2).Longueur());
		System.out.print(vect.Longueur());
		
		

	}

}
