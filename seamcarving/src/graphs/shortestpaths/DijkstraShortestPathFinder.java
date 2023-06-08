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
        priorityQueue.add(start, 1);

        // while you haven't visited everything
        while (!priorityQueue.isEmpty()) {
            V current = priorityQueue.removeMin();

            // found end
            if (Objects.equals(current, end)) {
                break;
            }

            known.add(current);
            for (E edge: graph.outgoingEdgesFrom(current)) {
                if (known.contains(edge.to())) {
                    continue;
                }

                priorityQueue.add(edge.to(), edge.weight());

                double oldDistToNew = distTo.getOrDefault(edge.to(), Double.POSITIVE_INFINITY);
                double newDistToNew = distTo.get(current) + edge.weight();

                if (newDistToNew < oldDistToNew) {
                    distTo.put(edge.to(), newDistToNew);
                    edgeTo.put(edge.to(), edge);
                }
            }
        }

        System.out.println(start);
        System.out.println(end);
        System.out.println(edgeTo);
        return edgeTo;
    }

    @Override
    protected ShortestPath<V, E> extractShortestPath(Map<V, E> spt, V start, V end) {
        if (start.equals(end)) {
            return new ShortestPath.SingleVertex<>(start);
        }
        // return all the edges on spt from start to end
        if (!spt.containsKey(end)) {
            return new ShortestPath.Failure<>();
        }

        // start and end must be connected
        V current = end;
        ArrayList<E> listOfEdges = new ArrayList<>();
        while (!current.equals(start)) {
            E edgeToFollow = spt.get(current);
            listOfEdges.add(0, edgeToFollow);
            current = edgeToFollow.from();
        }

        return new ShortestPath.Success<>(listOfEdges);
    }
}
