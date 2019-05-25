package org.insa.algo.shortestpath;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.insa.algo.AbstractSolution.Status;
import org.insa.algo.utils.BinaryHeap;
import org.insa.algo.utils.EmptyPriorityQueueException;
import org.insa.graph.*;

public class DijkstraAlgorithm extends ShortestPathAlgorithm {

	public DijkstraAlgorithm(ShortestPathData data) {
		super(data);
	}

	@Override
	protected ShortestPathSolution doRun() {
		boolean reached = false;
		ShortestPathData data = getInputData();
		ShortestPathSolution solution = null;
		Graph graph = data.getGraph();

		Node origin_node = data.getOrigin();
		Node destination = data.getDestination();

		List<Node> al_Nodes = graph.getNodes();
		ArrayList<Label> al_Labels = Label.InitGraphNode(al_Nodes);
		BinaryHeap<Label> bh_tas = new BinaryHeap<Label>();


		bh_tas.insert(al_Labels.get(origin_node.getId()));
		al_Labels.get(origin_node.getId()).setCoût(0);
		al_Labels.get(origin_node.getId()).setSommet_courant(origin_node);

		notifyOriginProcessed(data.getOrigin());

		boolean impossible = false;

		if (origin_node.getId() == destination.getId()) {
			impossible = true;
		} else {
			while (!bh_tas.isEmpty() && reached == false) {
				
				Label label_temp = bh_tas.deleteMin();
				label_temp.setMarque(true);
				notifyNodeMarked(label_temp.getSommet_courant());

				for (int i = 0 ; i < label_temp.getSommet_courant().getNumberOfSuccessors() && reached == false ; i++) {
					
					Arc arc_node = label_temp.getSommet_courant().getSuccessors().get(i);
					Node node = arc_node.getDestination();
					Label lab_node_dest = al_Labels.get(node.getId());
					lab_node_dest.setSommet_courant(node);
					Label lab_node_origin = al_Labels.get(arc_node.getOrigin().getId());
					
					notifyNodeReached(node);

					if (lab_node_dest.isMarque() == false) {

						double newCoutPotentiel = lab_node_origin.getCoût() + data.getCost(arc_node);

						if (lab_node_dest.getCoût() > newCoutPotentiel) {

							lab_node_dest.setCoût(newCoutPotentiel);
							lab_node_dest.setPere(arc_node);
							
							if (arc_node.getDestination() == destination) {
								reached = true;
								notifyDestinationReached(destination);
							}else {
								bh_tas.insert(lab_node_dest);
							}
						}
					}
				}
			}
		}

		if (al_Labels.get(data.getDestination().getId()) == null) {
			solution = new ShortestPathSolution(data, Status.INFEASIBLE);
		} else if (impossible == true) {
			solution = new ShortestPathSolution(data, Status.INFEASIBLE);
		} else if (reached == false && bh_tas.isEmpty()) {
			solution = new ShortestPathSolution(data, Status.INFEASIBLE);
		} else {

			ArrayList<Arc> arcs = new ArrayList<>();

			Arc arc = al_Labels.get(data.getDestination().getId()).getPere();

			while (arc != null) {
				arcs.add(arc);
				Node node_origin = arc.getOrigin();
				Label label_origin = al_Labels.get(node_origin.getId());
				arc = label_origin.getPere();
			}

			Collections.reverse(arcs);
			Path p = new Path(graph, arcs);
			if (p.isValid()) {
				solution = new ShortestPathSolution(data, Status.OPTIMAL, new Path(graph, arcs));
			} else {
				solution = null;

			}
		}

		return solution;
	}

}