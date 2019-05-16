package org.insa.graph;

import java.util.List;
import java.util.ArrayList;

public class Label implements Comparable<Label> {
	private Node sommet_courant;
	private boolean marque;//True : cout min du sommet connu
	private double coût;
	private Arc pere;
	
	// Cas inf
	public Label(Node ps)
	{
		this.sommet_courant = ps;
		this.marque = false;
		this.coût = Double.MAX_VALUE;
		this.pere = null;
	}
	
	//Depart
	public Label(Node ps, boolean m)
	{
		this.sommet_courant = ps;
		this.marque = m;
		this.coût = 0;
		this.pere = null;
	}

	/**
	 * @return the sommet_courant
	 */
	public Node getSommet_courant() {
		return sommet_courant;
	}

	/**
	 * @param sommet_courant the sommet_courant to set
	 */
	public void setSommet_courant(Node sommet_courant) {
		this.sommet_courant = sommet_courant;
	}

	/**
	 * @return the marque
	 */
	public boolean isMarque() {
		return marque;
	}

	/**
	 * @param marque the marque to set
	 */
	public void setMarque(boolean marque) {
		this.marque = marque;
	}

	/**
	 * @return the coût
	 */
	public double getCoût() {
		return coût;
	}

	/**
	 * @param coût the coût to set
	 */
	public void setCoût(double coût) {
		this.coût = coût;
	}

	/**
	 * @return the pere
	 */
	public Arc getPere() {
		return pere;
	}

	/**
	 * @param pere the pere to set
	 */
	public void setPere(Arc pere) {
		this.pere = pere;
	}
	
	static public ArrayList<Label> InitGraphNode(List<Node> ar)
	{
		ArrayList<Label> newAr = new ArrayList<Label>();
		int i;
		for(i=0;i<ar.size();i++)
		{
			newAr.add(new Label(ar.get(i)));
		}
		return newAr;
	}

	@Override
	public int compareTo(Label o) {
		return (int)this.getCoût() - (int)o.getCoût();
	}
	

}
