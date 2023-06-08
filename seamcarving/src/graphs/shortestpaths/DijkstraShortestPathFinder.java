package graphs.shortestpaths;

import priorityqueues.ExtrinsicMinPQ;
import priorityqueues.NaiveMinPQ;
import graphs.BaseEdge;
import graphs.Graph;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Map;
import java.util.Objects;

/**
 * Computes shortest paths using Dijkstra's algorithm.
 * @see SPTShortestPathFinder for more documentation.
 */
public class DijkstraShortestPathFinder<G extends Graph<V, E>, V, E extends BaseEdge<V, E>>
    extends SPTShortestPathFinder<G, V, E> {

    protected <T> ExtrinsicMinPQ<T> createMinPQ() {
        return new NaiveMinPQ<>();
        /*
        If you have confidence in your heap implementation, you can disable the line above
        and enable the one below.
         */
        // return new ArrayHeapMinPQ<>();

        /*
        Otherwise, do not change this method.
        We override this during grading to test your code using our correct implementation so that
        you don't lose extra points if your implementation is buggy.
         */
    }

    @Override
    protected Map<V, E> constructShortestPathsTree(G graph, V start, V end) {
        ExtrinsicMinPQ<V> priorityQueue = createMinPQ();
        Set<V> known = new HashSet<>();
        Map<V, E> edgeTo = new HashMap<>();
        Map<V, Double> distTo = new HashMap<>();

        distTo.put(start, 0.0);
        while (!priorityQueue.isEmpty()) {
            V currentVertex = priorityQueue.removeMin();
            if (Objects.equals(currentVertex, end)) {
                break;
            }
            known.add(currentVertex);
            for (E edge: graph.outgoingEdgesFrom(currentVertex)) {
                V edgeToVertex = edge.to();
                if (!priorityQueue.contains(edgeToVertex) && !known.contains(edgeToVertex)) {
                    priorityQueue.add(edgeToVertex, edge.weight());
                }
                double oldDist;
                if (distTo.containsKey(edgeToVertex)) {
                    oldDist = distTo.get(edgeToVertex);
                } else {
                    oldDist = Double.POSITIVE_INFINITY;
                }
                double newDist = distTo.get(currentVertex) + edge.weight();
                if (newDist < oldDist) {
                    distTo.put(edgeToVertex, newDist);
                    edgeTo.put(edgeToVertex, edge);
                }
            }
        }
        return edgeTo;
    }

    @Override
    protected ShortestPath<V, E> extractShortestPath(Map<V, E> spt, V start, V end) {
        // return all the edges on spt from start to end
        if (Objects.equals(start, end)) {
            return new ShortestPath.SingleVertex<>(start);
        }
        ArrayList<E> listOfEdges = new ArrayList<>();
        V vertex = end;
        // Iterate from end to start each vertex, add edge to list
        while (!vertex.equals(start)) {
            E edge = spt.get(vertex);
            listOfEdges.add(edge);
            vertex = edge.from();
        }
        // Reverse array list into new list, then return new list
        ArrayList<E> finalPath = new ArrayList<>();
        for(int i = listOfEdges.size() - 1; i >= 0; i--) {
            finalPath.add(listOfEdges.get(i));
        }
        return new ShortestPath.Success<>(finalPath);
    }
}
