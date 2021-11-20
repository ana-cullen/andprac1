package proj;

import java.util.*;

public class Graph {
  
  public ArrayList<Pillar> vertices = new ArrayList<Pillar>(); //lenght nm
  public ArrayList<Integer> costs = new ArrayList<Integer>(); //adj (length nm+2)
  public ArrayList<ArrayList<Pillar>> adj = new ArrayList<>();
  public ArrayList<Pillar> sources = new ArrayList<Pillar>(); //pillars that touch y=0
  public ArrayList<Pillar> sinks = new ArrayList<Pillar>(); //pillars that touch y=w
  public ArrayList<Pillar> explored = new ArrayList<Pillar>();
  public int w; //width of canyon
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
      for(int l=0; l<vertices.size(); l++){
        if(k != l){
          Pillar v1 = vertices.get(k);
          Pillar v2 = vertices.get(l);
          if(v1.inRange(v2)){
            adjV.add(v2);
          }
          else{
            if(v2.disk == disks.get(0))
              l= l+disks.size()-1;
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
    System.out.println(sinks);
    for(int i=0;i<sinks.size();i++){
      Pillar n =sinks.get(i);
      Integer sCost =-1;
      if(costs.size()>1){
        sCost = costs.get(n.id);
        System.out.println(n.id + " ");
      }
      endPathsCosts.add(sCost);
    }
    Collections.sort(endPathsCosts);
    System.out.println(costs);
    System.out.println(endPathsCosts);
    return(endPathsCosts.get(0));
  }

  public void checkAdj(Pillar cur){
    //int edgeCost = cur.disk.cost;
    int pathCost;
    for(int i =0; i<adj.get(cur.id).size(); i++){ //cur.id
      Pillar v = adj.get(cur.id).get(i);
      if(!explored.contains(v)){
        int edgeCost = v.disk.cost;
        pathCost = this.costs.get(cur.id) + edgeCost;
        if(pathCost < costs.get(v.id) && pathCost >= 0){
          costs.set(v.id, pathCost);
          System.out.println(v.id + " " + pathCost);
        }
        this.pq.add(v);
      }
    }
  }
}
