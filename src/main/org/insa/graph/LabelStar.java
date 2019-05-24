package org.insa.graph;

import java.util.ArrayList;
import java.util.List;

import org.insa.algo.AbstractInputData.Mode;
import org.insa.algo.shortestpath.ShortestPathData;

public class LabelStar extends Label{
	
	private double cout_destination;	

	public LabelStar(Node ps) {
		super(ps);
		this.cout_destination = Double.MAX_VALUE;
	}
	

	public double getCout_destination() {
		return cout_destination;
	}


	public void setCout_destination(double cout_destination, ShortestPathData data) {
		if (data.getMode() == Mode.LENGTH) {
			this.cout_destination = Point.distance(this.getSommet_courant().getPoint(),data.getDestination().getPoint());
		}
		else {
			int vitesse = Math.max(data.getMaximumSpeed(), data.getGraph().getGraphInformation().getMaximumSpeed());
			this.cout_destination = Point.distance(this.getSommet_courant().getPoint(),data.getDestination().getPoint())/(vitesse/3.6);
		}
	}

	
	public double getTotalCost() {
		return this.getCout_destination() + this.getCoût();
	}
	
	static public ArrayList<LabelStar> InitGraphNodeAStar(List<Node> ar)
	{
		ArrayList<LabelStar> newAr = new ArrayList<LabelStar>();
		int i;
		for(i=0;i<ar.size();i++)
		{
			newAr.add(new LabelStar(ar.get(i)));
		}
		return newAr;
	}
}