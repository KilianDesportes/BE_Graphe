package org.insa.algo.shortestpath;

import java.util.ArrayList;
import java.util.List;

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
		Node dest_node = data.getDestination();

		List<Node> al_Nodes = data.getGraph().getNodes();
		ArrayList<Label> al_Labels = Label.InitGraphNode(al_Nodes);

		System.out.println("Origin node is " + origin_node);
		System.out.println("Dest node is " + dest_node);

		int cout = 0;

		BinaryHeap<Node> bh_tas = new BinaryHeap<Node>();

		bh_tas.insert(origin_node);
		
		al_Labels.get(al_Nodes.indexOf(origin_node)).setCoût(0);

		int nb_nodes_unmarqued = al_Nodes.size();

		int changement_pere = 0;

		System.out.println("Total nodes " + nb_nodes_unmarqued);

		while (nb_nodes_unmarqued != 0) {
			System.out.println("____________WHILE ITER____________");
			Node node_temp = bh_tas.findMin();
			bh_tas.deleteMin();
			System.out.println("findMin result is " + node_temp);
			System.out.println("Label " + al_Labels.get(al_Nodes.indexOf(node_temp)));

			al_Labels.get(al_Nodes.indexOf(node_temp)).setMarque(true);
						
			nb_nodes_unmarqued--;
			System.out.println(al_Labels.get(al_Nodes.indexOf(node_temp)) + " set to true");
			System.out.println("Nb unmarqued nodes " + nb_nodes_unmarqued);
			for (int i = 0; i < node_temp.getNumberOfSuccessors(); i++) {

//				try {
//					Thread.sleep(50);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}

				System.out.println("____________FOR ITER____________");
				System.out.println("Successor " + i + "/" + node_temp.getNumberOfSuccessors());
				
				Arc arc_node = node_temp.getSuccessors().get(i);
				System.out.println("Arc is " + arc_node);
				System.out.println("Arc origin is " + arc_node.getOrigin());
				System.out.println("Arc dest is " + arc_node.getDestination());

				Node node = arc_node.getDestination();
				
				Label lab_node_dest = al_Labels.get(al_Nodes.indexOf(node));
				System.out.println("DEST");
				System.out.println("The label of this node is " + lab_node_dest);
				System.out.println("Label current node " + lab_node_dest.getSommet_courant());
				System.out.println("Label current père " + lab_node_dest.getPere());
				System.out.println("Label current cout " + lab_node_dest.getCoût());
				System.out.println("Label marked " + lab_node_dest.isMarque());
				
				Label lab_node_origin = al_Labels.get(al_Nodes.indexOf(arc_node.getOrigin()));
				System.out.println("ORIGIN");
				System.out.println("The label of this node is " + lab_node_origin);
				System.out.println("Label current node " + lab_node_origin.getSommet_courant());
				System.out.println("Label current père " + lab_node_origin.getPere());
				System.out.println("Label current cout " + lab_node_origin.getCoût());
				System.out.println("Label marked " + lab_node_origin.isMarque());
				System.out.println("____");

				if (lab_node_dest.isMarque() == false) {
					
					/*PRENDRE EN COMPTE LES AJOUTS DE COUTS AU FUR ET A MESURE DES 
					 * ETAPES DE PARCOURS. PAS SEULEMENT COMPARAISON AU COUT DE L'ARC 
					 * ACTUEL. GL 
					 */
	
					System.out.println("Label non marqué");
					System.out.println("Cout actuel label " + lab_node_dest.getCoût());
					System.out.println("Cout de l'arc " + arc_node.getLength());
					
					int newCoutPotentiel = lab_node_origin.getCoût() + (int) arc_node.getLength();
															
					if (lab_node_dest.getCoût() > newCoutPotentiel) {
						System.out.println("Cout inferieur");
						System.out.println("Cout from " + lab_node_dest.getCoût() + " to " + arc_node.getLength());
						lab_node_dest.setCoût(newCoutPotentiel);
						bh_tas.insert(node);
						System.out.println("Père " + arc_node);
						lab_node_dest.setPere(arc_node);
						changement_pere++;
						System.out.println("Père label" + lab_node_dest.getPere());
					} else {
						System.out.println("Cout supérieur");
					}

				} else {
					System.out.println("Label marqué");
				}
			}
		}
		System.out.println("____________END WHILE____________");
		System.out.println("Origin " + origin_node);
		System.out.println("Dest " + dest_node);
		System.out.println("Changement père " + changement_pere);
		for (int y = 0; y < al_Labels.size(); y++) {
//			if (al_Labels.get(y).getPere() != null) {
				System.out.println("___");
				System.out.println("" + y + "/" + al_Labels.size());
				System.out.println("Cost " + al_Labels.get(y).getCoût());
				System.out.println("Current node " + al_Labels.get(y).getSommet_courant());
				System.out.println("Père " + al_Labels.get(y).getPere());
//				System.out.println(al_Labels.get(y).getPere().getOrigin());
//				System.out.println(al_Labels.get(y).getPere().getDestination());
//			}

		}

		solution = new ShortestPathSolution(new ShortestPathData(data.getGraph(), origin_node, dest_node, null));

		return solution;
	}

}
