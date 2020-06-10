package com.bolgar.netera.graph;

import java.util.Iterator;
import java.util.List;

public interface Path<Vertex, D> extends Iterable<Vertex> {
    List<Vertex> getVertices();

    List<Edge<Vertex, D>> getEdges();

    Double getWeight();

    @Override
    default Iterator<Vertex> iterator() {
        return getVertices().iterator();
    }
}
