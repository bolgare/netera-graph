package com.bolgar.netera.graph;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class GraphUndirectedTest {

    @Test
    public void testSingle() {
        Graph<String, Void> graph = new GraphUndirected<>();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B", null, null);
        Path<String, Void> path = graph.getPath("A", "B");
        assertEquals(Arrays.asList("A", "B"), path.getVertices());
        path = graph.getPath("B", "A");
        assertEquals(Arrays.asList("B", "A"), path.getVertices());
    }

    @Test
    public void testDirect() {
        Graph<String, Void> graph = new GraphUndirected<>();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addEdge("A", "B", null, 10d);
        graph.addEdge("A", "C", null, 1d);
        graph.addEdge("B", "C", null, 1d);
        Path<String, Void> path = graph.getPath("A", "B");
        assertEquals(Arrays.asList("A", "C", "B"), path.getVertices());
    }

    @Test
    public void testWeight() {
        Graph<String, Void> graph = new GraphUndirected<>();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addEdge("A", "B", null, 10d);
        graph.addEdge("A", "C", null, 1d);
        graph.addEdge("C", "B", null, 1d);
        Path<String, Void> path = graph.getPath("A", "B");
        assertEquals(Arrays.asList("A", "C", "B"), path.getVertices());
    }
}
