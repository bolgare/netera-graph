package com.bolgar.netera.graph;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Edge<Vertex, T> {
    private final Vertex a;

    private final Vertex b;

    private final T data;

    private final Double weight;

    public Edge(Vertex a, Vertex b, T data, Double weight) {
        this.a = a;
        this.b = b;
        this.data = data;
        this.weight = weight;
    }
}
