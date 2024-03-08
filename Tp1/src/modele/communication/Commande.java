package modele.communication;

import utilitaire.Vect2D;

public class Commande extends Message{
	
	private eCommande comm;
	private Vect2D vect;
	public Commande(int compte, Vect2D vect) {
		super(compte);
		this.vect=vect;
		// TODO Auto-generated constructor stub
	}
	
	public eCommande getCommande() {
		return comm;
	}
	
	public Vect2D getVect() {
		return vect;
	}
	

}
