package org.insa.graph;

public class LabelStar extends Label {
	
	private Node sommet_courant;
	private boolean marque;
	private double cout_origine;
	private double cout_destination;
	private Arc pere;
	

	public LabelStar(Node ps) {
		super(ps);
	}
	
	public double getCout_origine() {
		return cout_origine;
	}


	public void setCout_origine(double cout_origine) {
		this.cout_origine = cout_origine;
	}


	public double getCout_destination() {
		return cout_destination;
	}


	public void setCout_destination(double cout_destination) {
		this.cout_destination = cout_destination;
	}

	public Node getSommet_courant() {
		return sommet_courant;
	}

	public void setSommet_courant(Node sommet_courant) {
		this.sommet_courant = sommet_courant;
	}

	public boolean isMarque() {
		return marque;
	}

	public void setMarque(boolean marque) {
		this.marque = marque;
	}

	public Arc getPere() {
		return pere;
	}

	public void setPere(Arc pere) {
		this.pere = pere;
	}

}
