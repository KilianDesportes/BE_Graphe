package org.insa.graph;

import java.util.ArrayList;
import java.util.List;

public class LabelStar extends Label{
	
	private double cout_destination;	

	public LabelStar(Node ps) {
		super(ps);
		this.cout_destination = Double.MAX_VALUE;
	}
	

	public double getCout_destination() {
		return cout_destination;
	}


	public void setCout_destination(double cout_destination) {
		this.cout_destination = cout_destination;
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
