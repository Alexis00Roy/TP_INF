package modele.communication;
import utilitaire.Vect2D;
public class Status extends Message {
	
	private Vect2D vect;
	
	public Status(int compte,Vect2D vect) {
		super(compte);
		// TODO Auto-generated constructor stub
		this.vect=vect;
	}

	
	
	public Vect2D getVect() {
		return vect;
	}
}
