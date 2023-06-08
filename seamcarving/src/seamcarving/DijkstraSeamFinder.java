package seamcarving;

import graphs.AdjacencyListUndirectedGraph;
import graphs.Edge;
import graphs.Graph;
import graphs.shortestpaths.DijkstraShortestPathFinder;
import graphs.shortestpaths.ShortestPath;
import graphs.shortestpaths.ShortestPathFinder;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DijkstraSeamFinder implements SeamFinder {
    // TODO: replace all 4 references to "Object" on the line below with whatever vertex type
    //  you choose for your graph
    private final ShortestPathFinder<Graph<String, Edge<String>>, String, Edge<String>> pathFinder;

    // private class HorizGraph implements Graph<String, Edge<String>> {
    //
    //     @Override
    //     public Collection<Edge<String>> outgoingEdgesFrom(String vertex) {
    //         String[] vSplit = vertex.split(",");
    //         int x = Integer.parseInt(vSplit[0]);
    //         int y = Integer.parseInt(vSplit[1]);
    //
    //         Collections.unmodifiableSet();
    //     }
    // }

    public DijkstraSeamFinder() {
        this.pathFinder = createPathFinder();
    }

    protected <G extends Graph<V, Edge<V>>, V> ShortestPathFinder<G, V, Edge<V>> createPathFinder() {
        /*
        We override this during grading to test your code using our correct implementation so that
        you don't lose extra points if your implementation is buggy.
        */

        return new DijkstraShortestPathFinder<>();
    }

    @Override
    public List<Integer> findHorizontalSeam(double[][] energies) {
        // TODO: replace this with your code
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public List<Integer> findVerticalSeam(double[][] energies) {
        // TODO: replace this with your code
        Collection<Edge<String>> edges = new ArrayList<Edge<String>>();
        String s = "s,s";
        String e = "e,e";

        for (int i = 0; i < energies.length; i++) {
            for (int j = 0; j < energies[i].length; j++) {
                if (i == 0) {
                    edges.add(new Edge(s, "" + i + "," + j, energies[i][j]));
                } else if (j == 0) {
                    edges.add(new Edge("" + (i-1) + "," + (j),   "" + (i) + "," + (j), energies[i][j]));
                    edges.add(new Edge("" + (i-1) + "," + (j+1), "" + (i) + "," + (j), energies[i][j]));
                } else if (j == energies[i].length - 1) {
                    edges.add(new Edge("" + (i-1) + "," + (j-1),"" + (i) + "," + (j), energies[i][j]));
                    edges.add(new Edge("" + (i-1) + "," + (j),  "" + (i) + "," + (j), energies[i][j]));
                } else {
                    edges.add(new Edge("" + (i-1) + "," + (j-1),"" + (i) + "," + (j), energies[i][j]));
                    edges.add(new Edge("" + (i-1) + "," + (j),  "" + (i) + "," + (j), energies[i][j]));
                    edges.add(new Edge("" + (i-1) + "," + (j+1),"" + (i) + "," + (j), energies[i][j]));
                }
            }
        }

        int bound = energies[0].length-1;
        for (int i = 0; i < energies[0].length; i++) {
            edges.add(new Edge("" + bound + "," + i, e, 0));
        }

        // for (Edge ed : edges) {
        //     System.out.println(ed.from() + " -> " + ed.to());
        // }

        AdjacencyListUndirectedGraph<String, Edge<String>> g = new AdjacencyListUndirectedGraph<>(edges);

        DijkstraShortestPathFinder<Graph<String, Edge<String>>, String, Edge<String>> dspf = new DijkstraShortestPathFinder();
        ShortestPath<String, Edge<String>> spt = dspf.findShortestPath(g, s, e);
        List<String> path = spt.vertices();

        System.out.println(path);

        List<Integer> res = new ArrayList<>();
        for (int i = 1; i < path.size() - 1; i++) {
            String v = path.get(i);
            String[] vSplit = v.split(",");
            res.add(Integer.parseInt(vSplit[1]));
        }
        System.out.println(res);
        return res;
    }
}
