package org.insa.algo.shortestpath;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.insa.algo.AbstractSolution.Status;
import org.insa.algo.utils.BinaryHeap;
import org.insa.graph.*;

public class DijkstraAlgorithm extends ShortestPathAlgorithm {

	public DijkstraAlgorithm(ShortestPathData data) {
		super(data);
	}

	@Override
	protected ShortestPathSolution doRun() {
		
		ShortestPathData data = getInputData();
		ShortestPathSolution solution = null;

		Node origin_node = data.getOrigin();

		List<Node> al_Nodes = data.getGraph().getNodes();
		ArrayList<Label> al_Labels = Label.InitGraphNode(al_Nodes);
		
		Graph graph = data.getGraph();
	    final int nbNodes = graph.size();
	    int nbNodes_unmarqued = nbNodes;

		BinaryHeap<Label> bh_tas = new BinaryHeap<Label>();

		bh_tas.insert(al_Labels.get(origin_node.getId()));		
		al_Labels.get(origin_node.getId()).setCoût(0);
		al_Labels.get(origin_node.getId()).setSommet_courant(origin_node);


		while (nbNodes_unmarqued != 0) {
			
			Label label_temp = bh_tas.findMin();
			bh_tas.deleteMin();

			label_temp.setMarque(true);
			nbNodes_unmarqued--;
			
			for (int i = 0; i < label_temp.getSommet_courant().getNumberOfSuccessors(); i++) {

				Arc arc_node = label_temp.getSommet_courant().getSuccessors().get(i);
				Node node = arc_node.getDestination();
				Label lab_node_dest = al_Labels.get(node.getId());
				lab_node_dest.setSommet_courant(node);
				Label lab_node_origin = al_Labels.get(arc_node.getOrigin().getId());

				if (lab_node_dest.isMarque() == false) {
					
					int newCoutPotentiel = lab_node_origin.getCoût() + (int) arc_node.getLength();
															
					if (lab_node_dest.getCoût() > newCoutPotentiel) {
						
						lab_node_dest.setCoût(newCoutPotentiel);
						lab_node_dest.setPere(arc_node);
						bh_tas.insert(lab_node_dest);

					}

				} 
			}
		}
		
        if (al_Labels.get(data.getDestination().getId()) == null) {
            solution = new ShortestPathSolution(data, Status.INFEASIBLE);
        }
        else {

            ArrayList<Arc> arcs = new ArrayList<>();
            
            Arc arc = al_Labels.get(data.getDestination().getId()).getPere();
            
            while (arc != null) {
                arcs.add(arc);
                Node node_origin = arc.getOrigin();
                Label label_origin = al_Labels.get(node_origin.getId());
                arc = label_origin.getPere();
                System.out.println(arc);
            }

            Collections.reverse(arcs);

            solution = new ShortestPathSolution(data, Status.OPTIMAL, new Path(graph, arcs));
        }

		return solution;
	}

}
