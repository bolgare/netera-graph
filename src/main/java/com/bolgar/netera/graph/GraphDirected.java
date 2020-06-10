package com.bolgar.netera.graph;

import java.util.Collection;
import java.util.stream.Collectors;

public class GraphDirected<Vertex, D> extends AbstractGraph<Vertex, D> {
    @Override
    protected Collection<Edge<Vertex, D>> getEdgesFrom(Vertex source) {
        return getEdges().stream()
                .filter(e -> e.getA() == source)
                .collect(Collectors.toList());
    }
}
