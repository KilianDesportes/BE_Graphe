package org.insa.graph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.insa.algo.ArcInspector;
import org.insa.algo.ArcInspectorFactory;
import org.insa.algo.AbstractSolution.Status;
import org.insa.algo.shortestpath.ShortestPathData;
import org.insa.algo.shortestpath.ShortestPathSolution;
import org.insa.algo.shortestpath.BellmanFordAlgorithm;
import org.insa.algo.shortestpath.DijkstraAlgorithm;
import org.insa.graph.RoadInformation.RoadType;
import org.insa.graph.io.BinaryGraphReader;
import org.insa.graph.io.BinaryPathReader;
import org.insa.graph.io.GraphReader;
import org.insa.graph.io.PathReader;
import org.insa.graphics.drawing.Drawing;
import org.junit.BeforeClass;
import org.junit.Test;


public class ShortPathTest {
        
    private static ShortestPathData impossible_path,null_path,possible_path;
    

    @BeforeClass
    public static void initAll() throws IOException {
    	
    	String guadeloupe_map = "/home/kde/Documents/maps/guadeloupe.mapgr";
    	
        GraphReader reader_guadeloupe_map = new BinaryGraphReader(new DataInputStream(new BufferedInputStream(new FileInputStream(guadeloupe_map))));
        Graph graph_guadeloupe_map = reader_guadeloupe_map.read();
        
        List<ArcInspector> ArcInsp = ArcInspectorFactory.getAllFilters();
        
        System.out.println(ArcInsp);
        
        impossible_path = new ShortestPathData(graph_guadeloupe_map,graph_guadeloupe_map.get(21361),graph_guadeloupe_map.get(15418),ArcInsp.get(0));
                
        null_path = new ShortestPathData(graph_guadeloupe_map,graph_guadeloupe_map.get(21361),graph_guadeloupe_map.get(21361),ArcInsp.get(0));
        
        possible_path = new ShortestPathData(graph_guadeloupe_map,graph_guadeloupe_map.get(21361),graph_guadeloupe_map.get(21201),ArcInsp.get(0));

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
    public void testDjikstraAlgorithmPath() {
    	assertTrue(new DijkstraAlgorithm(possible_path).run().isFeasible());
        assertEquals((int)new DijkstraAlgorithm(possible_path).run().getPath().getLength(),
        		(int)new BellmanFordAlgorithm(possible_path).run().getPath().getLength());
        assertEquals((int)new DijkstraAlgorithm(possible_path).run().getPath().getTravelTime(100),
        		(int)new BellmanFordAlgorithm(possible_path).run().getPath().getTravelTime(100));
        assertEquals((int)new DijkstraAlgorithm(possible_path).run().getPath().getMinimumTravelTime(),
        		(int)new BellmanFordAlgorithm(possible_path).run().getPath().getMinimumTravelTime());
        
    }


}
