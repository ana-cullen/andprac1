package proj;

import java.io.*;
import java.util.*;

class CFG {
    static class AdjListNode {
        int vertex, weight;

        AdjListNode(int v, int w)
        {
            vertex = v;
            weight = w;
        }
        int getVertex() { return vertex; }
        int getWeight() { return weight; }
    }

    public static int[] dijkstra(
            int V, ArrayList<ArrayList<AdjListNode> > graph,
            int source)
    {
        int[] distance = new int[V];
        for (int i = 0; i < V; i++)
            distance[i] = Integer.MAX_VALUE;
        distance[source] = 0;

        PriorityQueue<AdjListNode> pq = new PriorityQueue<>(
                (v1, v2) -> v1.getWeight() - v2.getWeight());
        pq.add(new AdjListNode(source, 0));

        while (pq.size() > 0) {
            AdjListNode current = pq.poll();

            for (AdjListNode n :
                    graph.get(current.getVertex())) {
                if (distance[current.getVertex()]
                        + n.getWeight()
                        < distance[n.getVertex()]) {
                    distance[n.getVertex()]
                            = n.getWeight()
                            + distance[current.getVertex()];
                    pq.add(new AdjListNode(
                            n.getVertex(),
                            distance[n.getVertex()]));
                }
            }
        }
        // If you want to calculate distance from source to
        // a particular target, you can return
        // distance[target]
        return distance;
    }
    public static int[] test(ArrayList<ArrayList<Pillar>> a){
        int V = a.size();
        ArrayList<ArrayList<AdjListNode>> b = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            b.add(new ArrayList<>());
        }
        for (int i = 0; i < a.size(); i++) {
            for (int j = 0; j < a.get(i).size(); j++) {
                b.get(i).add(new AdjListNode(a.get(i).get(j).id, a.get(i).get(j).disk.cost));
            }
        }
        return dijkstra(V, b, 0);

    }
}
