import java.util.*;

/**
 * Your implementation of various different graph algorithms.
 *
 * @author Rohit Mittapalli
 * @userid rmittapalli3
 * @GTID 903309727
 * @version 1.0
 */
public class GraphAlgorithms {

    /**
     * Performs a breadth first search (bfs) on the input graph, starting at
     * {@code start} which represents the starting vertex.
     *
     * When exploring a vertex, make sure to explore in the order that the
     * adjacency list returns the neighbors to you. Failure to do so may cause
     * you to lose points.
     *
     * You may import/use {@code java.util.Set}, {@code java.util.List},
     * {@code java.util.Queue}, and any classes that implement the
     * aforementioned interfaces, as long as it is efficient.
     *
     * The only instance of {@code java.util.Map} that you may use is the
     * adjacency list from {@code graph}. DO NOT create new instances of Map
     * for BFS (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @throws IllegalArgumentException if any input
     *  is null, or if {@code start} doesn't exist in the graph
     * @param <T> the generic typing of the data
     * @param start the vertex to begin the bfs on
     * @param graph the graph to search through
     * @return list of vertices in visited order
     */
    public static <T> List<Vertex<T>> breadthFirstSearch(Vertex<T> start,
                                                         Graph<T> graph) {
        if (graph == null) {
            throw new IllegalArgumentException("Graph cannot be null");
        }
        if (start == null) {
            throw new IllegalArgumentException("Start cannot be null");
        }

        if (!graph.getAdjList().containsKey(start)) {
            throw new IllegalArgumentException("Start is not in graph");
        }

        List<Vertex<T>> output = new ArrayList<>();
        Queue<Vertex<T>> queue = new LinkedList<>();
        Set<Vertex<T>> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {

            Vertex<T> next = queue.remove();

            output.add(next);

            for (Edge<T> vertex : graph.getAdjList().get(next)) {
                if (!visited.contains(vertex.getV())) {
                    queue.add(vertex.getV());
                    visited.add(vertex.getV());
                }
            }

        }
        return output;
    }


    /**
     * Performs a depth first search (dfs) on the input graph, starting at
     * {@code start} which represents the starting vertex.
     *
     * When deciding which neighbors to visit next from a vertex, visit the
     * vertices in the order presented in that entry of the adjacency list.
     *
     * *NOTE* You MUST implement this method recursively, or else you will lose
     * most if not all points for this method.
     *
     * You may import/use {@code java.util.Set}, {@code java.util.List}, and
     * any classes that implement the aforementioned interfaces, as long as it
     * is efficient.
     *
     * The only instance of {@code java.util.Map} that you may use is the
     * adjacency list from {@code graph}. DO NOT create new instances of Map
     * for DFS (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @throws IllegalArgumentException if any input
     *  is null, or if {@code start} doesn't exist in the graph
     * @param <T> the generic typing of the data
     * @param start the vertex to begin the dfs on
     * @param graph the graph to search through
     * @return list of vertices in visited order
     */
    public static <T> List<Vertex<T>> adepthFirstSearch(Vertex<T> start,
                                            Graph<T> graph) {
        if (graph == null) {
            throw new IllegalArgumentException("Graph cannot be null");
        }
        if (start == null) {
            throw new IllegalArgumentException("Start cannot be null");
        }

        if (!graph.getAdjList().containsKey(start)) {
            throw new IllegalArgumentException("Start is not in graph");
        }
        Set<Vertex<T>> visited = new HashSet<>();
        List<Vertex<T>> output = new ArrayList<>();
        dfsRecursive(start, output, visited, graph);

        return output;    }

    /**
     * Recursive helper method
     *
     * @param s starting
     * @param visited visited vertices
     * @param output the output vertices
     * @param g the graph
     * @param <T> the generic
     */
    private static <T> void dfsRecursive(Vertex<T> s, List<Vertex<T>> output,
                                          Set<Vertex<T>> visited, Graph<T> g) {
        output.add(s);
        visited.add(s);

        for (Edge<T> e : g.getAdjList().get(s)) {
            Vertex<T> next = e.getV();
            if (!visited.contains(next)) {
                dfsRecursive(next, output, visited, g);

            }
        }
    }

    public static <T> List<Vertex<T>> depthFirstSearch(Vertex<T> start,
                                                       Graph<T> graph) {
        if (start == null) {
            throw new IllegalArgumentException("Start vertex is null."
                    + " It cannot be null.");
        } else if (graph == null) {
            throw new IllegalArgumentException("Graph is null."
                    + " It cannot be null.");
        }

        Map<Vertex<T>, List<Edge<T>>> vertList =
                graph.getAdjList();

        if (!vertList.containsKey(start)) {
            throw new IllegalArgumentException("The graph does not "
                    + "contain the start key.");
        }

        Set<Vertex<T>> visited = new HashSet<>();
        List<Vertex<T>> list = new ArrayList<>();
        Stack<Vertex<T>> stack = new Stack<>();

        stack.push(start);
        //visited.add(start);
        while (!stack.isEmpty()) {
            Vertex<T> v = stack.pop();
            list.add(v);
            //stack.pop();
            visited.add(v);


            for (Edge<T> edge: vertList.get(v)) {
                if (!visited.contains(edge.getV())) {
                    stack.push(edge.getV());
                }
            }
        }
        //realDepthFirstSearch(start, visited, list, vertList);

        return list;

    }

    /**
     * Finds the single-source shortest distance between the start vertex and
     * all vertices given a weighted graph (you may assume non-negative edge
     * weights).
     *
     * Return a map of the shortest distances such that the key of each entry
     * is a node in the graph and the value for the key is the shortest distance
     * to that node from start, or Integer.MAX_VALUE (representing infinity)
     * if no path exists.
     *
     * You may import/use {@code java.util.PriorityQueue},
     * {@code java.util.Map}, and {@code java.util.Set} and any class that
     * implements the aforementioned interfaces, as long as it's efficient.
     *
     * You should implement the version of Dijkstra's where you terminate the
     * algorithm once either all vertices have been visited or the PQ becomes
     * empty.
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @throws IllegalArgumentException if any input is null, or if start
     *  doesn't exist in the graph.
     * @param <T> the generic typing of the data
     * @param start index representing which vertex to start at (source)
     * @param graph the graph we are applying Dijkstra's to
     * @return a map of the shortest distances from start to every other node
     *         in the graph
     */
    public static <T> Map<Vertex<T>, Integer> dijkstras(Vertex<T> start,
                                                      Graph<T> graph) {
        if (graph == null) {
            throw new IllegalArgumentException("Graph cannot be null");
        }
        if (start == null) {
            throw new IllegalArgumentException("Start cannot be null");
        }

        if (!graph.getAdjList().containsKey(start)) {
            throw new IllegalArgumentException("Start is not in graph");
        }

        Set<Vertex<T>> visited = new HashSet<>();
        Map<Vertex<T>, Integer> output = new HashMap<>();
        Queue<Edge<T>> queue = new PriorityQueue<>();



        for (Vertex<T> vertex : graph.getAdjList().keySet()) {
            if (vertex.equals(start)) {
                output.put(vertex, 0);
            } else {
                output.put(vertex, Integer.MAX_VALUE);
            }
        }

        queue.add(new Edge<T>(start, start, 0));

        while (!queue.isEmpty() && visited.size()
                < graph.getAdjList().keySet().size()) {
            Edge<T> eC = queue.remove();
            Vertex<T> current = eC.getV();
            if (!visited.contains(current)) {
                visited.add(current);
                for (Edge<T> edge : graph.getAdjList().get(current)) {
                    if (!(edge.getWeight() + eC.getWeight()
                            >= output.get(edge.getV()))) {
                        queue.add(new Edge<>(edge.getV(), edge.getV(),
                                edge.getWeight() + eC.getWeight()));
                        output.put(edge.getV(),
                                edge.getWeight() + eC.getWeight());
                    }
                }
            }
        }
        return output;

    }


    /**
     * Runs Prim's algorithm on the given graph and return the Minimal
     * Spanning Tree (MST) in the form of a set of Edges. If the graph is
     * disconnected and therefore no valid MST exists, return null.
     *
     * You may assume that the passed in graph is undirected. In this framework,
     * this means that if (u, v, 3) is in the graph, then the opposite edge
     * (v, u, 3) will also be in the graph, though as a separate Edge object.
     *
     * The returned set of edges should form an undirected graph. This means
     * that every time you add an edge to your return set, you should add the
     * opposite edge to the set as well. This is for testing purposes.
     *
     * You may assume that there will only be one valid MST that can be formed.
     *
     * You should NOT allow self-loops into the MST.
     *
     * You may import/use {@code java.util.PriorityQueue},
     * {@code java.util.Set}, and any class that implements the aforementioned
     * interface, as long as it's efficient.
     *
     * The only instance of {@code java.util.Map} that you may use is the
     * adjacency list from {@code graph}. DO NOT create new instances of Map
     * for this method (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @throws IllegalArgumentException if any input
     *  is null, or if {@code start} doesn't exist in the graph
     * @param <T> the generic typing of the data
     * @param start the vertex to begin Prims on
     * @param graph the graph we are applying Prims to
     * @return the MST of the graph or null if there is no valid MST
     */
    public static <T> Set<Edge<T>> prims(Vertex<T> start, Graph<T> graph) {
        if (graph == null) {
            throw new IllegalArgumentException("Graph cannot be null");
        }
        if (start == null) {
            throw new IllegalArgumentException("Start cannot be null");
        }

        if (!graph.getAdjList().containsKey(start)) {
            throw new IllegalArgumentException("Start is not in graph");
        }

        Set<Vertex<T>> visited = new HashSet<>();
        visited.add(start);
        Queue<Edge<T>> q = new PriorityQueue<>();
        Set<Edge<T>> output = new HashSet<>();

        for (Edge<T> e : graph.getAdjList().get(start)) {
            q.add(new Edge<>(start, e.getV(), e.getWeight()));
        }

        while (output.size()
                < 2 * graph.getAdjList().size() - 2 && !q.isEmpty()) {
            Edge<T> current = q.remove();

            if (!(visited.contains(current.getV()))) {
                output.add(new Edge<>(current.getV(),
                        current.getU(), current.getWeight()));
                output.add(current);
                visited.add(current.getV());

                for (Edge<T> incident
                        : graph.getAdjList().get(current.getV())) {
                    q.add(incident);
                }

            }
        }

        if (output.size() < 2 * graph.getAdjList().size() - 2) {
            return null;
        }
        return output;


    }
}