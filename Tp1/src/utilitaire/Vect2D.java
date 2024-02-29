package utilitaire;
import java.lang.Math;
public class Vect2D {
	private double Lx;
	private double Ly;
	
	public Vect2D() {
		Lx=1;
		Ly=1;
	}
	public Vect2D(double x, double y) {
		Lx=x;
		Ly=y;
	}
	
	public double getLy() {
		return Ly;
	}
	
	public double getLx() {
		return Lx;
	}
	
	public double Longueur() {
		return Math.sqrt((Lx*Lx)+(Ly*Ly));
	}
	
	public double getAngle() {
		return ((Math.atan2(Ly, Lx)*(360))/(2*Math.PI));
	}
	
	 public Vect2D calculerDiff(Vect2D posFin) {
		 Vect2D Diff= new Vect2D(posFin.getLx()-Lx,posFin.getLy()-Ly);
		 return Diff;
	 }
	 
	 public void diviser(double div) {
		 Lx=Lx/div;
		 Ly=Ly/div;
	 }
	 
	 public void ajouter(double x,double y) {
		 Lx= Lx+x;
		 Ly= Ly+y;
	 }
	 
	 public boolean equals(Vect2D v) {
		 boolean equal= false;
		 if((v.getLx()==Lx)&&(v.getLy()==Ly))
			 equal=true;
		 
		 return equal;
	 }
	 

}
//ghjwergtfojehwbenfrgdjkejwhbrf