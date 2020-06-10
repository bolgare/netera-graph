package com.bolgar.netera.graph;

import java.util.*;

public abstract class AbstractGraph<Vertex, D> implements Graph<Vertex, D> {
    protected final Set<Vertex> vertices = new HashSet<>();

    protected final List<Edge<Vertex, D>> edges = new ArrayList<>();

    @Override
    public Collection<Vertex> getVertices() {
        return Collections.unmodifiableCollection(vertices);
    }

    @Override
    public void addVertex(Vertex vertex) {
        if (!vertices.add(vertex)) {
            throw new IllegalArgumentException("Vertex already exists");
        }
    }

    @Override
    public Collection<Edge<Vertex, D>> getEdges() {
        return Collections.unmodifiableList(edges);
    }

    @Override
    public Edge<Vertex, D> addEdge(Vertex a, Vertex b, D data, Double weight) {
        for (Edge<Vertex, D> edge : getEdgesFrom(a)) {
            if (edge.getA() == a && edge.getB() == b) {
                throw new IllegalArgumentException("Edge already exists");
            }
            if (edge.getA() == b && edge.getB() == a) {
                throw new IllegalArgumentException("Edge already exists");
            }
        }
        if (!vertices.contains(a)) {
            throw new IllegalArgumentException("Vertex A is not exists: " + a);
        }
        if (!vertices.contains(b)) {
            throw new IllegalArgumentException("Vertex B is not exists: " + b);
        }
        Edge<Vertex, D> edge = new Edge<>(a, b, data, weight);
        edges.add(edge);
        return edge;
    }

    protected abstract Collection<Edge<Vertex, D>> getEdgesFrom(Vertex source);

    @Override
    public Path<Vertex, D> getPath(Vertex source, Vertex target) {
        List<PathImpl> list = new ArrayList<>();
        list.add(new PathImpl(source));
        list = getPathList(list, target);
        Collections.sort(list);
        return list.isEmpty() ? null : list.get(0);
    }

    private List<PathImpl> getPathList(List<PathImpl> current, Vertex target) {
        List<PathImpl> list = new ArrayList<>();
        boolean extended = false;
        for (PathImpl path : current) {
            if (path.vertices.getLast() == target) {
                list.add(path);
                continue;
            }
            for (Edge<Vertex, D> edge : getEdgesFrom(path.vertices.getLast())) {
                Vertex vertex = edge.getA() == path.vertices.getLast() ? edge.getB() : edge.getA();
                if (path.vertices.contains(vertex)) {
                    continue;
                }
                extended = true;
                list.add(path.add(vertex, edge));
            }
        }
        return extended ? getPathList(list, target) : list;
    }

    private class PathImpl implements Path<Vertex, D>, Comparable<Path<Vertex, D>> {
        private final LinkedList<Vertex> vertices;

        private final List<Edge<Vertex, D>> edges;

        private Double weight;

        private PathImpl(Vertex vertex) {
            this.vertices = new LinkedList<>();
            this.vertices.add(vertex);
            this.edges = Collections.emptyList();
            this.weight = 0d;
        }

        private PathImpl(LinkedList<Vertex> list, List<Edge<Vertex, D>> edges, Double weight) {
            this.vertices = list;
            this.edges = edges;
            this.weight = weight;
        }

        private PathImpl add(Vertex vertex, Edge<Vertex, D> edge) {
            LinkedList<Vertex> list = new LinkedList<>(this.vertices);
            list.add(vertex);
            List<Edge<Vertex, D>> edges = new ArrayList<>(this.edges);
            edges.add(edge);
            Double weight = this.weight == null || edge.getWeight() == null ? null : this.weight + edge.getWeight();
            return new PathImpl(list, edges, weight);
        }

        @Override
        public List<Vertex> getVertices() {
            return Collections.unmodifiableList(vertices);
        }

        @Override
        public List<Edge<Vertex, D>> getEdges() {
            return Collections.unmodifiableList(edges);
        }

        @Override
        public Double getWeight() {
            return weight;
        }

        @Override
        public int compareTo(Path<Vertex, D> o) {
            return Double.compare(weight == null ? 0 : weight, o.getWeight() == null ? 0 : o.getWeight());
        }

        @Override
        public String toString() {
            return "Path{" +
                    "vertices=" + vertices +
                    ", weight=" + weight +
                    '}';
        }
    }
}
