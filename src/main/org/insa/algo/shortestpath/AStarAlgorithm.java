package org.insa.algo.shortestpath;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.insa.algo.AbstractSolution.Status;
import org.insa.algo.utils.BinaryHeap;
import org.insa.graph.Arc;
import org.insa.graph.Graph;
import org.insa.graph.Label;
import org.insa.graph.LabelStar;
import org.insa.graph.Node;
import org.insa.graph.Path;

public class AStarAlgorithm extends DijkstraAlgorithm {

	public AStarAlgorithm(ShortestPathData data) {
		super(data);
	}

	@Override
	protected ShortestPathSolution doRun() {
		boolean reached = false;
		ShortestPathData data = getInputData();
		ShortestPathSolution solution = null;

		Node origin_node = data.getOrigin();
		Node destination = data.getDestination();

		List<Node> al_Nodes = data.getGraph().getNodes();
		ArrayList<LabelStar> al_Labels = LabelStar.InitGraphNodeAStar(al_Nodes);

		Graph graph = data.getGraph();

		BinaryHeap<Label> bh_tas = new BinaryHeap<Label>();

		bh_tas.insert(al_Labels.get(origin_node.getId()));
		al_Labels.get(origin_node.getId()).setCoût(0);
		al_Labels.get(origin_node.getId())
				.setCout_destination(origin_node.getPoint().distanceTo(destination.getPoint()),data);
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

				for (int i = 0; i < label_temp.getSommet_courant().getNumberOfSuccessors(); i++) {
					Arc arc_node = label_temp.getSommet_courant().getSuccessors().get(i);
					Node node = arc_node.getDestination();
					LabelStar lab_node_dest = al_Labels.get(node.getId());
					if (lab_node_dest.getCoût() == Double.MAX_VALUE) {
						lab_node_dest.setCout_destination(node.getPoint().distanceTo(destination.getPoint()),data);
					}
					lab_node_dest.setSommet_courant(node);
					LabelStar lab_node_origin = al_Labels.get(arc_node.getOrigin().getId());
					
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
				LabelStar label_origin = al_Labels.get(node_origin.getId());
				arc = label_origin.getPere();
			}

			Collections.reverse(arcs);
			Path p = new Path(graph, arcs);
			if (p.isValid()) {
				solution = new ShortestPathSolution(data, Status.OPTIMAL, new Path(graph, arcs));
			} else {
				solution = new ShortestPathSolution(data, Status.INFEASIBLE);

			}
		}
		return solution;
	}
}