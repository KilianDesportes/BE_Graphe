package org.insa.graph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.insa.algo.ArcInspector;
import org.insa.algo.ArcInspectorFactory;
import org.insa.algo.shortestpath.ShortestPathData;
import org.insa.algo.shortestpath.BellmanFordAlgorithm;
import org.insa.algo.shortestpath.BellmanFordAlgorithm;
import org.insa.algo.shortestpath.DijkstraAlgorithm;
import org.insa.graph.io.BinaryGraphReader;
import org.insa.graph.io.GraphReader;
import org.junit.BeforeClass;
import org.junit.Test;


public class DijkstraAlgorithmTest {
        
    private static ShortestPathData impossible_path,null_path,shortest_possible_path,shortest_possible_path_car,
	fastest_possible_path,fastest_possible_path_pedestrian,fastest_possible_path_car, toulouse_path_shortest,
	toulouse_path_fastest,carreD_path_shortest,carreD_path_fastest;
    
    
    @BeforeClass
    public static void initAll() throws IOException {
    	
String guadeloupe_map = "/home/kde/Documents/maps/guadeloupe.mapgr";
    	
        GraphReader reader_guadeloupe_map = new BinaryGraphReader(new DataInputStream(new BufferedInputStream(new FileInputStream(guadeloupe_map))));
        Graph graph_guadeloupe_map = reader_guadeloupe_map.read();
        
        String toulouse_map = "/home/kde/Documents/maps/toulouse.mapgr";
    	
        GraphReader reader_toulouse_map = new BinaryGraphReader(new DataInputStream(new BufferedInputStream(new FileInputStream(toulouse_map))));
        Graph graph_toulouse_map = reader_toulouse_map.read();
        
        String carreD_map = "/home/kde/Documents/maps/carre-dense.mapgr";
    	
        GraphReader reader_carreD_map = new BinaryGraphReader(new DataInputStream(new BufferedInputStream(new FileInputStream(carreD_map))));
        Graph graph_carreD_map = reader_carreD_map.read();
        
        List<ArcInspector> ArcInsp = ArcInspectorFactory.getAllFilters();
        
        impossible_path = new ShortestPathData(graph_guadeloupe_map,graph_guadeloupe_map.get(21361),graph_guadeloupe_map.get(15418),ArcInsp.get(0));               
        null_path = new ShortestPathData(graph_guadeloupe_map,graph_guadeloupe_map.get(21361),graph_guadeloupe_map.get(21361),ArcInsp.get(0));        
        shortest_possible_path = new ShortestPathData(graph_guadeloupe_map,graph_guadeloupe_map.get(21361),graph_guadeloupe_map.get(21201),ArcInsp.get(0));
        shortest_possible_path_car = new ShortestPathData(graph_guadeloupe_map,graph_guadeloupe_map.get(21361),graph_guadeloupe_map.get(21201),ArcInsp.get(1));
        fastest_possible_path = new ShortestPathData(graph_guadeloupe_map,graph_guadeloupe_map.get(21361),graph_guadeloupe_map.get(21201),ArcInsp.get(2));
        fastest_possible_path_car = new ShortestPathData(graph_guadeloupe_map,graph_guadeloupe_map.get(21361),graph_guadeloupe_map.get(21201),ArcInsp.get(3));
        fastest_possible_path_pedestrian = new ShortestPathData(graph_guadeloupe_map,graph_guadeloupe_map.get(21361),graph_guadeloupe_map.get(21201),ArcInsp.get(4));
        
        //Chemin pour les tests de performance
        toulouse_path_shortest = new ShortestPathData(graph_toulouse_map,graph_toulouse_map.get(32007),graph_toulouse_map.get(15218),ArcInsp.get(0));
        toulouse_path_fastest = new ShortestPathData(graph_toulouse_map,graph_toulouse_map.get(32007),graph_toulouse_map.get(15218),ArcInsp.get(2));

        carreD_path_shortest = new ShortestPathData(graph_carreD_map,graph_carreD_map.get(274717),graph_carreD_map.get(92252),ArcInsp.get(0));
        carreD_path_fastest = new ShortestPathData(graph_carreD_map,graph_carreD_map.get(32007),graph_carreD_map.get(15218),ArcInsp.get(2));

    
    }
    
    @Test
    public void testDjikstraAlgorithmImpossiblePath() {
    	assertFalse(new DijkstraAlgorithm(impossible_path).run().isFeasible());
        assertEquals(new DijkstraAlgorithm(impossible_path).run().isFeasible(),
        		new BellmanFordAlgorithm(impossible_path).run().isFeasible());
    }
    
    @Test
    public void testDjikstraAlgorithmNullPath() {
        assertFalse(new DijkstraAlgorithm(null_path).run().isFeasible());
        assertEquals(new DijkstraAlgorithm(null_path).run().isFeasible(),
        		new BellmanFordAlgorithm(null_path).run().isFeasible());
    }
    
    @Test
    public void testDjikstraAlgorithmShortestPath() {
    	assertTrue(new DijkstraAlgorithm(shortest_possible_path).run().isFeasible());
        assertEquals((int)new DijkstraAlgorithm(shortest_possible_path).run().getPath().getLength(),
        		(int)new BellmanFordAlgorithm(shortest_possible_path).run().getPath().getLength());
        assertEquals((int)new DijkstraAlgorithm(shortest_possible_path).run().getPath().getMinimumTravelTime(),
        		(int)new BellmanFordAlgorithm(shortest_possible_path).run().getPath().getMinimumTravelTime());
        
    }
    
    @Test
    public void testDjikstraAlgorithmShortestPathCar() {
    	assertTrue(new DijkstraAlgorithm(shortest_possible_path_car).run().isFeasible());
        assertEquals((int)new DijkstraAlgorithm(shortest_possible_path_car).run().getPath().getLength(),
        		(int)new BellmanFordAlgorithm(shortest_possible_path_car).run().getPath().getLength());
        assertEquals((int)new DijkstraAlgorithm(shortest_possible_path_car).run().getPath().getMinimumTravelTime(),
        		(int)new BellmanFordAlgorithm(shortest_possible_path_car).run().getPath().getMinimumTravelTime());
        
    }
    
    @Test
    public void testDjikstraAlgorithmFastestPath() {
    	assertTrue(new DijkstraAlgorithm(fastest_possible_path).run().isFeasible());
        assertEquals((int)new DijkstraAlgorithm(fastest_possible_path).run().getPath().getLength(),
        		(int)new BellmanFordAlgorithm(fastest_possible_path).run().getPath().getLength());
        assertEquals((int)new DijkstraAlgorithm(fastest_possible_path).run().getPath().getMinimumTravelTime(),
        		(int)new BellmanFordAlgorithm(fastest_possible_path).run().getPath().getMinimumTravelTime());
        
    }
    
    @Test
    public void testDjikstraAlgorithmFastestPathCar() {
    	assertTrue(new DijkstraAlgorithm(fastest_possible_path_car).run().isFeasible());
        assertEquals((int)new DijkstraAlgorithm(fastest_possible_path_car).run().getPath().getLength(),
        		(int)new BellmanFordAlgorithm(fastest_possible_path_car).run().getPath().getLength());
        assertEquals((int)new DijkstraAlgorithm(fastest_possible_path_car).run().getPath().getMinimumTravelTime(),
        		(int)new BellmanFordAlgorithm(fastest_possible_path_car).run().getPath().getMinimumTravelTime());
        
    }
    
    @Test
    public void testDjikstraAlgorithmFastestPathPedestrian() {
    	assertTrue(new DijkstraAlgorithm(fastest_possible_path_pedestrian).run().isFeasible());
        assertEquals((int)new DijkstraAlgorithm(fastest_possible_path_pedestrian).run().getPath().getLength(),
        		(int)new BellmanFordAlgorithm(fastest_possible_path_pedestrian).run().getPath().getLength());
        assertEquals((int)new DijkstraAlgorithm(fastest_possible_path_pedestrian).run().getPath().getMinimumTravelTime(),
        		(int)new BellmanFordAlgorithm(fastest_possible_path_pedestrian).run().getPath().getMinimumTravelTime());
        
    }
    
    @Test
    public void testDjikstraAlgorithmFastestPathVsShortestPath() {
    	assertTrue(new DijkstraAlgorithm(shortest_possible_path).run().getPath().getLength()<new DijkstraAlgorithm(fastest_possible_path).run().getPath().getLength());
    	assertTrue(new DijkstraAlgorithm(shortest_possible_path).run().getPath().getMinimumTravelTime()>new DijkstraAlgorithm(fastest_possible_path).run().getPath().getMinimumTravelTime());
    }
    
    @Test
    public void testDjikstraAlgorithmPerformanceToulouse() {
    	long f_debut_BellMan = System.currentTimeMillis();
    	new BellmanFordAlgorithm(toulouse_path_fastest).run();
    	long f_fin_BellMan = System.currentTimeMillis();
    	
    	long f_debut_Dijk = System.currentTimeMillis();
    	new DijkstraAlgorithm(toulouse_path_fastest).run();
    	long f_fin_Dijk = System.currentTimeMillis();
    	
    	System.out.println("Toulouse - Fastest");
    	System.out.println("Bellman exec : " + (f_debut_BellMan-f_fin_BellMan) + " ms.");
    	System.out.println("Dijkstra " + (f_fin_Dijk-f_debut_Dijk)+ " ms.");
    	System.out.println();


    	long s_debut_BellMan = System.currentTimeMillis();
    	new BellmanFordAlgorithm(toulouse_path_shortest).run();
    	long s_fin_BellMan = System.currentTimeMillis();
    	
    	long s_debut_Dijk = System.currentTimeMillis();
    	new DijkstraAlgorithm(toulouse_path_shortest).run();
    	long s_fin_Dijk = System.currentTimeMillis();
    	
    	System.out.println("Toulouse - Shortest");
    	System.out.println("Bellman exec : " + (s_debut_BellMan-s_fin_BellMan) + " ms.");
    	System.out.println("Dijkstra " + (s_fin_Dijk-s_debut_Dijk)+ " ms.");
    	System.out.println();


    }
    
    @Test
    public void testDjikstraAlgorithmPerformanceCarreDense() {
    	long f_debut_BellMan = System.currentTimeMillis();
    	new BellmanFordAlgorithm(carreD_path_fastest).run();
    	long f_fin_BellMan = System.currentTimeMillis();
    	
    	long f_debut_Dijk = System.currentTimeMillis();
    	new DijkstraAlgorithm(carreD_path_fastest).run();
    	long f_fin_Dijk = System.currentTimeMillis();
    	
    	System.out.println("Carre Dense - Fastest");
    	System.out.println("bellman exec : " + (f_fin_BellMan-f_debut_BellMan) + " ms.");
    	System.out.println("Dijkstra " + (f_fin_Dijk-f_debut_Dijk)+ " ms.");
    	System.out.println();


    	long s_debut_bellman = System.currentTimeMillis();
    	new BellmanFordAlgorithm(carreD_path_shortest).run();
    	long s_fin_bellman = System.currentTimeMillis();
    	
    	long s_debut_Dijk = System.currentTimeMillis();
    	new DijkstraAlgorithm(carreD_path_shortest).run();
    	long s_fin_Dijk = System.currentTimeMillis();
    	
    	System.out.println("Carre Dense - Shortest");
    	System.out.println("bellman exec : " + (s_fin_bellman-s_debut_bellman) + " ms.");
    	System.out.println("Dijkstra " + (s_fin_Dijk-s_debut_Dijk)+ " ms.");
    	System.out.println();
    }
    
    @Test
    public void testDjikstraAlgorithmPerformanceGuadeloupe() {
    	long f_debut_BellMan = System.currentTimeMillis();
    	new BellmanFordAlgorithm(fastest_possible_path).run();
    	long f_fin_BellMan = System.currentTimeMillis();
    	
    	long f_debut_Dijk = System.currentTimeMillis();
    	new DijkstraAlgorithm(fastest_possible_path).run();
    	long f_fin_Dijk = System.currentTimeMillis();
    	
    	System.out.println("Guadeloupe - Fastest");
    	System.out.println("bellman exec : " + (f_fin_BellMan-f_debut_BellMan) + " ms.");
    	System.out.println("Dijkstra " + (f_fin_Dijk-f_debut_Dijk)+ " ms.");
    	System.out.println();


    	long s_debut_bellman = System.currentTimeMillis();
    	new BellmanFordAlgorithm(shortest_possible_path).run();
    	long s_fin_bellman = System.currentTimeMillis();
    	
    	long s_debut_Dijk = System.currentTimeMillis();
    	new DijkstraAlgorithm(shortest_possible_path).run();
    	long s_fin_Dijk = System.currentTimeMillis();
    	
    	System.out.println("Guadeloupe - Shortest");
    	System.out.println("bellman exec : " + (s_fin_bellman-s_debut_bellman) + " ms.");
    	System.out.println("Dijkstra " + (s_fin_Dijk-s_debut_Dijk)+ " ms.");
    	System.out.println();
    }
    
    @Test
    public void testDjikstraAlgorithmPerformanceNodes() {

    	BellmanFordAlgorithm bellman = new BellmanFordAlgorithm(shortest_possible_path);
    	DijkstraAlgorithm Dijkstra = new DijkstraAlgorithm(shortest_possible_path);
    	bellman.run();
    	Dijkstra.run();
    	
    	System.out.println("Nombre de sommet parcouru - Guadeloupe shortest");
    	System.out.println("bellman " + bellman.getNbSommetsParcouru());
    	System.out.println("Dijkstra " + Dijkstra.getNbSommetsParcouru());
    	
    	bellman = new BellmanFordAlgorithm(carreD_path_shortest);
    	Dijkstra = new DijkstraAlgorithm(carreD_path_shortest);
    	bellman.run();
    	Dijkstra.run();
    	
    	System.out.println("Nombre de sommet parcouru - Carre Dense shortest");
    	System.out.println("bellman " + bellman.getNbSommetsParcouru());
    	System.out.println("Dijkstra " + Dijkstra.getNbSommetsParcouru());
    	
    	bellman = new BellmanFordAlgorithm(carreD_path_fastest);
    	Dijkstra = new DijkstraAlgorithm(carreD_path_fastest);
    	bellman.run();
    	Dijkstra.run();
    	
    	System.out.println("Nombre de sommet parcouru - Carre Dense fastest");
    	System.out.println("bellman " + bellman.getNbSommetsParcouru());
    	System.out.println("Dijkstra " + Dijkstra.getNbSommetsParcouru());
    	
    	bellman = new BellmanFordAlgorithm(fastest_possible_path);
    	Dijkstra = new DijkstraAlgorithm(fastest_possible_path);
    	bellman.run();
    	Dijkstra.run();
    	
    	System.out.println("Nombre de sommet parcouru - Guadeloupe fastest");
    	System.out.println("bellman " + bellman.getNbSommetsParcouru());
    	System.out.println("Dijkstra " + Dijkstra.getNbSommetsParcouru());
    	
    	bellman = new BellmanFordAlgorithm(toulouse_path_shortest);
    	Dijkstra = new DijkstraAlgorithm(toulouse_path_shortest);
    	bellman.run();
    	Dijkstra.run();
    	
    	System.out.println("Nombre de sommet parcouru - Toulouse Shortest");
    	System.out.println("bellman " + bellman.getNbSommetsParcouru());
    	System.out.println("Dijkstra " + Dijkstra.getNbSommetsParcouru());
    	
    	bellman = new BellmanFordAlgorithm(toulouse_path_fastest);
    	Dijkstra = new DijkstraAlgorithm(toulouse_path_fastest);
    	bellman.run();
    	Dijkstra.run();
    	
    	System.out.println("Nombre de sommet parcouru - Toulouse fastest");
    	System.out.println("bellman " + bellman.getNbSommetsParcouru());
    	System.out.println("Dijkstra " + Dijkstra.getNbSommetsParcouru());

    }
}
