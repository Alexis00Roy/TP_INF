package utilitaire;

public class FileSchainer {
	private int nbElement;
	private Node premier;
	private Node dernier;
	
	public FileSchainer() {
		nbElement=0;
		this.premier= null;
		this.dernier= null;
	}
	
	
	public void push(Object data) {
		if(nbElement==0) {
			dernier = new Node(data,null);
			premier = dernier;
			System.out.print("dfdvsvf");
		}
		else {
			dernier.next= new Node(data,null );
			dernier=dernier.next;
		}
		nbElement++;
	}
	
	public Object pop(){
		Object result=premier.data;
		premier.data= null;
		premier=premier.next;
		return result;
		
	}
	
	public boolean estVide() {
		boolean estVide;
		if(nbElement==0) {
			estVide=true;
		}
		else
			estVide=true;
		return estVide;
	}
	
	
	
	
	private class Node{
		private Object data;
		private Node next;
		
		public Node(Object data, Node next) {
			this.data=data;
			this.next = next;
		}
		
		public Node(Object data) {
			this.data=data;
			this.next = null;
		}
	}
}