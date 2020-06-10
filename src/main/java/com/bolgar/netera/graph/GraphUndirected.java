package com.bolgar.netera.graph;

import java.util.Collection;
import java.util.stream.Collectors;

public class GraphUndirected<Vertex, D> extends AbstractGraph<Vertex, D> {
    @Override
    protected Collection<Edge<Vertex, D>> getEdgesFrom(Vertex source) {
        return getEdges().stream()
                .filter(e -> e.getA() == source || e.getB() == source)
                .collect(Collectors.toList());
    }
}
