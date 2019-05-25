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
import org.insa.algo.shortestpath.DijkstraAlgorithm;
import org.insa.graph.io.BinaryGraphReader;
import org.insa.graph.io.GraphReader;
import org.junit.BeforeClass;
import org.junit.Test;


public class DijkstraAlgorithmTest {
        
    private static ShortestPathData impossible_path,null_path,shortest_possible_path,shortest_possible_path_car,
    	fastest_possible_path,fastest_possible_path_pedestrian,fastest_possible_path_car;
    
    
    @BeforeClass
    public static void initAll() throws IOException {
    	
    	String guadeloupe_map = "/home/kde/Documents/maps/guadeloupe.mapgr";
    	
        GraphReader reader_guadeloupe_map = new BinaryGraphReader(new DataInputStream(new BufferedInputStream(new FileInputStream(guadeloupe_map))));
        Graph graph_guadeloupe_map = reader_guadeloupe_map.read();
        
        List<ArcInspector> ArcInsp = ArcInspectorFactory.getAllFilters();
        
        System.out.println(ArcInsp);
        
        for(int i =0 ;i < 5 ; i ++){
            System.out.println(ArcInsp.get(i));

        }
        
        impossible_path = new ShortestPathData(graph_guadeloupe_map,graph_guadeloupe_map.get(21361),graph_guadeloupe_map.get(15418),ArcInsp.get(0));               
        null_path = new ShortestPathData(graph_guadeloupe_map,graph_guadeloupe_map.get(21361),graph_guadeloupe_map.get(21361),ArcInsp.get(0));        
        shortest_possible_path = new ShortestPathData(graph_guadeloupe_map,graph_guadeloupe_map.get(21361),graph_guadeloupe_map.get(21201),ArcInsp.get(0));
        shortest_possible_path_car = new ShortestPathData(graph_guadeloupe_map,graph_guadeloupe_map.get(21361),graph_guadeloupe_map.get(21201),ArcInsp.get(1));
        fastest_possible_path = new ShortestPathData(graph_guadeloupe_map,graph_guadeloupe_map.get(21361),graph_guadeloupe_map.get(21201),ArcInsp.get(2));
        fastest_possible_path_car = new ShortestPathData(graph_guadeloupe_map,graph_guadeloupe_map.get(21361),graph_guadeloupe_map.get(21201),ArcInsp.get(3));
        fastest_possible_path_pedestrian = new ShortestPathData(graph_guadeloupe_map,graph_guadeloupe_map.get(21361),graph_guadeloupe_map.get(21201),ArcInsp.get(4));
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
}
