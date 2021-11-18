package proj;

import java.io.*;
import java.util.*;


public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        int w = 0;
        ArrayList<Coordinate> coords = new ArrayList();
        ArrayList<Disk> disks = new ArrayList<>();
        takeInput(w, coords, disks);
        filterDisks(disks);

        List<List<Integer>> a = createGraph(coords, disks, w);
        System.out.println(a.size());
        System.out.println(a);
    }

    public static List<List<Integer>> createGraph(List<Coordinate> coords, List<Disk> disks, int w) {
        List<List<Integer>> graph = new ArrayList<>();
        List<List<Pillar>> nodes = new ArrayList<>();
        List<Pillar> startNode = new ArrayList<>();
        List<Integer> startNodeInt = new ArrayList<>();
        int endNode = 20000000;
        for (int i = 0; i < coords.size(); i++) {
            for (int j = 0; j < disks.size(); j++) {
                if (coords.get(i).y - disks.get(j).radius <= 0) {
                    int id = (10000*i) + j;
                    Pillar p = new Pillar(coords.get(i), disks.get(j), id);
                    startNode.add(p);
                    startNodeInt.add(id);
                }
                List<Pillar> x = new ArrayList<>();
                List<Integer> y = new ArrayList<>();
                for (int h = 0; h < coords.size(); h++) {
                    if (!(i == h)) {
                        int distance = coords.get(i).dist(coords.get(h));
                        for (int k = 0; k < disks.size(); k++) {
                            if (disks.get(j).radius + disks.get(k).radius >= distance) {
                                int id = (10000 * h) + k;
                                Pillar p = new Pillar(coords.get(h), disks.get(k), id);
                                x.add(p);
                                y.add(id);
                            }
                            else{
                                break;
                            }
                        }
                    }
                }
                if (coords.get(i).y + disks.get(j).radius >= w){
                    System.out.println(coords.get(i).y + disks.get(j).radius);
                    System.out.println(w);
                    y.add(endNode);
                }
                nodes.add(x);
                graph.add(y);
            }
        }
        graph.add(0, startNodeInt);
        nodes.add(0, startNode);
        return graph;
    }

    public static void filterDisks(ArrayList <Disk> disks){
        for(int i = 0; i< disks.size(); i++){
            for(int j = 0; j< disks.size(); j++){
                if( i != j){
                    if(disks.get(i).radius <= disks.get(j).radius && disks.get(i).cost >= disks.get(j).cost)
                        disks.remove(i);
                }
            }
        }
    }

    public static void takeInput(int w, List <Coordinate> cl, List <Disk> dl) throws FileNotFoundException{
        Scanner myReader = new Scanner(System.in);
        String fstLn = myReader.nextLine();
        String[] splitted = fstLn.split(" ");
        int n, m;
        n = Integer.parseInt(splitted[0]);
        m = Integer.parseInt(splitted[1]);
        w = Integer.parseInt(splitted[2]);
        for(int i = 0; i < n; i++){
            String coords = myReader.nextLine();
            String[] split = coords.split(" ");
            cl.add(new Coordinate(Integer.parseInt(split[0]), Integer.parseInt(split[1])));
        }
        for(int i = 0; i < m; i++) {
            String disk = myReader.nextLine();
            String[] split = disk.split(" ");
            dl.add(new Disk(Integer.parseInt(split[0]), Integer.parseInt(split[1])));
        }
        dl.sort(Collections.reverseOrder());
    }

}