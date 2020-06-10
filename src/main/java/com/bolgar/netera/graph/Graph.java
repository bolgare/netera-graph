package com.bolgar.netera.graph;

import java.util.Collection;

public interface Graph<Vertex, D> {
    Collection<Vertex> getVertices();

    Collection<Edge<Vertex, D>> getEdges();

    void addVertex(Vertex vertex);

    default Edge<Vertex, D> addEdge(Vertex a, Vertex b, D data) {
        return addEdge(a, b, data, null);
    }

    Edge<Vertex, D> addEdge(Vertex a, Vertex b, D data, Double weight);

    Path<Vertex, D> getPath(Vertex source, Vertex target);
}
