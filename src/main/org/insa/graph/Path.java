package org.insa.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * Class representing a path between nodes in a graph.
 * </p>
 * 
 * <p>
 * A path is represented as a list of {@link Arc} with an origin and not a list
 * of {@link Node} due to the multi-graph nature (multiple arcs between two
 * nodes) of the considered graphs.
 * </p>
 *
 */
public class Path {

	/**
	 * Create a new path that goes through the given list of nodes (in order),
	 * choosing the fastest route if multiple are available.
	 * 
	 * @param graph
	 *            Graph containing the nodes in the list.
	 * @param nodes
	 *            List of nodes to build the path.
	 * 
	 * @return A path that goes through the given list of nodes.
	 * 
	 * @throws IllegalArgumentException
	 *             If the list of nodes is not valid, i.e. two consecutive nodes in
	 *             the list are not connected in the graph.
	 */
	public static Path createFastestPathFromNodes(Graph graph, List<Node> nodes) throws IllegalArgumentException {
		List<Arc> arcs = new ArrayList<Arc>();

		System.out.println("____NEW CALL____");

		boolean b_contains = true;

		for (Node var_node : nodes) {
			System.out.println("Node : " + var_node);
			if (!graph.getNodes().contains(var_node)) {
				b_contains = false;
			}
		}

		if (!b_contains) {
			System.out.println("Some nodes are not in the graph.");
			throw new IllegalArgumentException();
		} else {
			System.out.println("Every nodes are in the graph.");
		}

		int i_indice_nodes = 0;
		boolean b_first = true;
		if (nodes.size() == 0) {
			// do nothing
		} else if (nodes.size() == 1) {
			arcs.add(nodes.get(i_indice_nodes).getSuccessors().get(0));
		} else {
			while (i_indice_nodes != nodes.size() - 1) {
				System.out.println("Indice nodes : " + i_indice_nodes);
				System.out.println("Number successor : " + nodes.get(i_indice_nodes).getSuccessors().size());
				Arc arc_tmp = null;
				if (nodes.get(i_indice_nodes).getSuccessors().size() != 0) {
					for (Arc var_arcs : nodes.get(i_indice_nodes).getSuccessors()) {
						System.out.println("Node origin : " + nodes.get(i_indice_nodes) + " needed "
								+ nodes.get(i_indice_nodes + 1));
						if (var_arcs.getDestination().equals(nodes.get(i_indice_nodes + 1))) {
							System.out.println("Arc " + var_arcs.getOrigin() + " to " + var_arcs.getOrigin());
							if (b_first) {
								arc_tmp = var_arcs;
								b_first = false;
							} else {
								if (var_arcs.getMinimumTravelTime() < arc_tmp.getMinimumTravelTime()) {
									System.out.println("Arc moins long de "
											+ (arc_tmp.getMinimumTravelTime() - var_arcs.getMinimumTravelTime()));
									arc_tmp = var_arcs;
								}
							}
						}
					}
					System.out.println("Arc " + arc_tmp + " added.");
					arcs.add(arc_tmp);
					b_first = true;
					i_indice_nodes++;
				}
			}
		}

		System.out.println("Graph returned : " + graph.toString());
		System.out.println("Arcs returned : " + arcs.toString());
		return new Path(graph, arcs);
	}

	/**
	 * Create a new path that goes through the given list of nodes (in order),
	 * choosing the shortest route if multiple are available.
	 * 
	 * @param graph
	 *            Graph containing the nodes in the list.
	 * @param nodes
	 *            List of nodes to build the path.
	 * 
	 * @return A path that goes through the given list of nodes.
	 * 
	 * @throws IllegalArgumentException
	 *             If the list of nodes is not valid, i.e. two consecutive nodes in
	 *             the list are not connected in the graph.
	 */
	public static Path createShortestPathFromNodes(Graph graph, List<Node> nodes) throws IllegalArgumentException {
		List<Arc> arcs = new ArrayList<Arc>();
		System.out.println("____NEW CALL____");

		boolean b_contains = true;
		int j = 0;
		boolean trouve = false;
		System.out.println(nodes.size());
		if (nodes.size() == 1) {
			//throw new IllegalArgumentException("path invalid");
		} else {
			for (j = 0; j < nodes.size() - 1; j++) {
				for (Arc var_arc : nodes.get(j).getSuccessors()) {
					System.out.println(var_arc.getDestination().toString());
					System.out.println(nodes.get(j + 1));

					if (var_arc.getDestination().equals((nodes.get(j + 1)))) {
						trouve = true;
					}
					System.out.println(trouve);
				}
				if (!trouve) {
					throw new IllegalArgumentException("path invalid");
				}
				trouve = false;

				System.out.println("___");
			}
		}

		for (Node var_node : nodes) {
			System.out.println("Node : " + var_node);
			if (!graph.getNodes().contains(var_node)) {
				b_contains = false;
			}
		}

		if (!b_contains) {
			System.out.println("Some nodes are not in the graph.");
			throw new IllegalArgumentException();
		} else {
			System.out.println("Every nodes are in the graph.");
		}

		int i_indice_nodes = 0;
		boolean b_first = true;
		if (nodes.size() == 1) {
			return new Path(graph,nodes.get(0));
		} else if (nodes.size() > 1) {
			while (i_indice_nodes != nodes.size() - 1) {
				System.out.println("Indice nodes : " + i_indice_nodes);
				System.out.println("Number successor : " + nodes.get(i_indice_nodes).getSuccessors().size());
				Arc arc_tmp = null;
				if (nodes.get(i_indice_nodes).getSuccessors().size() != 0) {
					for (Arc var_arcs : nodes.get(i_indice_nodes).getSuccessors()) {
						System.out.println("Node origin : " + nodes.get(i_indice_nodes) + " needed "
								+ nodes.get(i_indice_nodes + 1));
						if (var_arcs.getDestination().equals(nodes.get(i_indice_nodes + 1))) {
							System.out.println("Arc " + var_arcs.getOrigin() + " to " + var_arcs.getOrigin());
							if (b_first) {
								arc_tmp = var_arcs;
								b_first = false;
							} else {
								if (var_arcs.getLength() < arc_tmp.getLength()) {
									System.out.println("Arc moins long (en metres)  de "
											+ (arc_tmp.getLength() - var_arcs.getLength()));
									arc_tmp = var_arcs;
								}
							}
						}
					}
					System.out.println("Arc " + arc_tmp + " added.");
					arcs.add(arc_tmp);
					b_first = true;
					i_indice_nodes++;
				}
			}
		}
		return new Path(graph, arcs);
	}

	/**
	 * Concatenate the given paths.
	 * 
	 * @param paths
	 *            Array of paths to concatenate.
	 * 
	 * @return Concatenated path.
	 * 
	 * @throws IllegalArgumentException
	 *             if the paths cannot be concatenated (IDs of map do not match, or
	 *             the end of a path is not the beginning of the next).
	 */
	public static Path concatenate(Path... paths) throws IllegalArgumentException {
		if (paths.length == 0) {
			throw new IllegalArgumentException("Cannot concatenate an empty list of paths.");
		}
		final String mapId = paths[0].getGraph().getMapId();
		for (int i = 1; i < paths.length; ++i) {
			if (!paths[i].getGraph().getMapId().equals(mapId)) {
				throw new IllegalArgumentException("Cannot concatenate paths from different graphs.");
			}
		}
		ArrayList<Arc> arcs = new ArrayList<>();
		for (Path path : paths) {
			arcs.addAll(path.getArcs());
		}
		Path path = new Path(paths[0].getGraph(), arcs);
		if (!path.isValid()) {
			throw new IllegalArgumentException("Cannot concatenate paths that do not form a single path.");
		}
		return path;
	}

	// Graph containing this path.
	private final Graph graph;

	// Origin of the path
	private final Node origin;

	// List of arcs in this path.
	private final List<Arc> arcs;

	/**
	 * Create an empty path corresponding to the given graph. *
	 * 
	 * @param graph
	 *            Graph containing the path.
	 */
	public Path(Graph graph) {
		this.graph = graph;
		this.origin = null;
		this.arcs = new ArrayList<>();
	}

	/**
	 * Create a new path containing a single node.
	 * 
	 * @param graph
	 *            Graph containing the path.
	 * @param node
	 *            Single node of the path.
	 */
	public Path(Graph graph, Node node) {
		this.graph = graph;
		this.origin = node;
		this.arcs = new ArrayList<>();
	}

	/**
	 * Create a new path with the given list of arcs.
	 * 
	 * @param graph
	 *            Graph containing the path.
	 * @param arcs
	 *            Arcs to construct the path.
	 */
	public Path(Graph graph, List<Arc> arcs) {
		this.graph = graph;
		this.arcs = arcs;
		this.origin = arcs.size() > 0 ? arcs.get(0).getOrigin() : null;
	}

	/**
	 * @return Graph containing the path.
	 */
	public Graph getGraph() {
		return graph;
	}

	/**
	 * @return First node of the path.
	 */
	public Node getOrigin() {
		return origin;
	}

	/**
	 * @return Last node of the path.
	 */
	public Node getDestination() {
		return arcs.get(arcs.size() - 1).getDestination();
	}

	/**
	 * @return List of arcs in the path.
	 */
	public List<Arc> getArcs() {
		return Collections.unmodifiableList(arcs);
	}

	/**
	 * Check if this path is empty (it does not contain any node).
	 * 
	 * @return true if this path is empty, false otherwise.
	 */
	public boolean isEmpty() {
		return this.origin == null;
	}

	/**
	 * Get the number of <b>nodes</b> in this path.
	 * 
	 * @return Number of nodes in this path.
	 */
	public int size() {
		return isEmpty() ? 0 : 1 + this.arcs.size();
	}

	/**
	 * Check if this path is valid.
	 * 
	 * A path is valid if any of the following is true:
	 * <ul>
	 * <li>it is empty;</li>
	 * <li>it contains a single node (without arcs);</li>
	 * <li>the first arc has for origin the origin of the path and, for two
	 * consecutive arcs, the destination of the first one is the origin of the
	 * second one.</li>
	 * </ul>
	 * 
	 * @return true if the path is valid, false otherwise.
	 */
	public boolean isValid() {
		// TODO:
		if ((this.size() == 0) || (this.size() == 1)) {
			return true;
		} else {
			Arc arcDeb = this.arcs.get(0);
			if (arcDeb.getOrigin() != this.origin) {
				return false;
			}
			int i;
			Arc arcTemp;
			for (i = 1; i < this.arcs.size(); i++) {
				arcTemp = this.arcs.get(i);
				if (!arcTemp.getOrigin().equals(arcDeb.getDestination())) {
					return false;
				}
				arcDeb = arcTemp;
			}
			return true;
		}
	}

	/**
	 * Compute the length of this path (in meters).
	 * 
	 * @return Total length of the path (in meters).
	 */
	public float getLength() {
		float f_cpt_length = 0;
		for (int i = 0; i < arcs.size(); i++) {
			f_cpt_length = f_cpt_length + arcs.get(i).getLength();
		}
		return f_cpt_length;
	}

	/**
	 * Compute the time required to travel this path if moving at the given speed.
	 * 
	 * @param speed
	 *            Speed to compute the travel time.
	 * 
	 * @return Time (in seconds) required to travel this path at the given speed (in
	 *         kilometers-per-hour).
	 * 
	 */
	public double getTravelTime(double speed) {
		// TODO:
		double time = 0;
		int i;
		Arc arcTemp;
		for (i = 0; i < this.arcs.size(); i++) {
			arcTemp = this.arcs.get(i);
			time = time + arcTemp.getTravelTime(speed);
		}
		return time;
	}

	/**
	 * Compute the time to travel this path if moving at the maximum allowed speed
	 * on every arc.
	 * 
	 * @return Minimum travel time to travel this path (in seconds).
	 */
	public double getMinimumTravelTime() {
		double d_travel_time = 0;
		for (int i = 0; i < arcs.size(); i++) {
			d_travel_time = d_travel_time + arcs.get(i).getMinimumTravelTime();
		}
		return d_travel_time;
	}

}
