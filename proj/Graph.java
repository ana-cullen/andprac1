package proj;

import java.util.*;

public class Graph {

  public int w; //width of canyon

  public ArrayList<Pillar> vertices = new ArrayList<Pillar>(); //lenght nm
  public ArrayList<Integer> costs = new ArrayList<Integer>(); //adj (length nm+2)
  public ArrayList<ArrayList<Pillar>> adj = new ArrayList<>();
  public ArrayList<Pillar> sources = new ArrayList<Pillar>(); //pillars that touch y=0
  public ArrayList<Pillar> sinks = new ArrayList<Pillar>(); //pillars that touch y=w
  public ArrayList<Pillar> explored = new ArrayList<Pillar>();
  public PriorityQueue<Pillar> pq = new PriorityQueue<Pillar>();

  public Graph (ArrayList<Coordinate> coords, ArrayList<Disk> disks, int w) {
    this.w = w;
    int count = 1;
    for(int i = 0; i< coords.size(); i++){
      for(int j= 0; j< disks.size(); j++){
        Pillar v = new Pillar(coords.get(i), disks.get(j), count);
        if(v.coord.y-v.disk.radius <= 0){
          sources.add(v);
        }
        if(v.coord.y+v.disk.radius >= w){
          sinks.add(v);
        }
        vertices.add(v);
        count++;
      }
    }
    adj.add(sources);

    for(int k=0; k<vertices.size(); k++){
      ArrayList<Pillar> adjV = new ArrayList<Pillar>();
      Pillar v1 = vertices.get(k);
      for(int l=0; l<vertices.size(); l++){
        if(k != l){
          Pillar v2 = vertices.get(l);
          if(v1.inRange(v2)){
            adjV.add(v2);
          }
        }
      }
      adj.add(adjV);
    }
  }

  public int dijkstras(){
    costs.add(0);

    for(int i=0; i<vertices.size(); i++){
      costs.add(Integer.MAX_VALUE);
    }
    for (int i = 0; i < adj.get(0).size(); i++) {
      costs.set(adj.get(0).get(i).id, adj.get(0).get(i).disk.cost);
    }
    this.pq.addAll(adj.get(0));
    while(explored.size()!=vertices.size()){
      if(pq.isEmpty())
        break;
      Pillar cur = pq.remove();
      if(!explored.contains(cur)){
        explored.add(cur);
        this.checkAdj(cur);
      }
    }
    ArrayList<Integer> endPathsCosts = new ArrayList<>();
    for(int i=0;i<sinks.size();i++){
      Pillar n =sinks.get(i);
      Integer sCost =-1;
      if(costs.size()>1){
        sCost = costs.get(n.id);
      }
      endPathsCosts.add(sCost);
    }
    Collections.sort(endPathsCosts);
    if(endPathsCosts.size() == 0){
      return Integer.MAX_VALUE;
    }
    else {
      return (endPathsCosts.get(0));
    }
  }

  public void checkAdj(Pillar cur){
    for(int i =0; i<adj.get(cur.id).size(); i++){
      Pillar v = adj.get(cur.id).get(i);
      if(!explored.contains(v)){
        int edgeCost = v.disk.cost;
        int pathCost = costs.get(cur.id) + edgeCost;
        if(pathCost < costs.get(v.id)){
          costs.set(v.id, pathCost);
        }

      }
      this.pq.add(v);
    }
  }
}
