package proj;

import java.io.*;
import java.util.*;


public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Coordinate> coords = new ArrayList<Coordinate>();
        ArrayList<Disk> disks = new ArrayList<Disk>();
        int w = takeInput(coords, disks);

        Graph g = new Graph(coords,disks,w);
        int cheapestPathCost = g.dijkstras();
        if(cheapestPathCost == Integer.MAX_VALUE){
            System.out.println("impossible");
        }
        else{
            System.out.println(cheapestPathCost);
        }
    }

    public static int takeInput(List <Coordinate> cl, List <Disk> dl) throws FileNotFoundException{
        Scanner myReader = new Scanner(System.in);
        String fstLn = myReader.nextLine();
        String[] splitted = fstLn.split(" ");
        int n, m, w;
        n = Integer.parseInt(splitted[0]);
        m = Integer.parseInt(splitted[1]);
        w = Integer.parseInt(splitted[2]);
        for(int i = 0; i < n; i++){
            String coords = myReader.nextLine();
            String[] split = coords.split(" ");
            cl.add(new Coordinate(Integer.parseInt(split[0]), Integer.parseInt(split[1])));
        }
        for(int i = 0; i < m; i++){
            String disk = myReader.nextLine();
            String[] split = disk.split(" ");
            dl.add(new Disk(Integer.parseInt(split[0]), Integer.parseInt(split[1])));
        }
        myReader.close();
        filterDisks(dl);
        return w;
    }

    public static void filterDisks(List <Disk> disks){
        ArrayList<Disk> uniqueDisks = new ArrayList<>();
        ArrayList<Integer> uniqueRadius = new ArrayList<>();
        Collections.sort(disks);
        Collections.reverse(disks);
        for(Disk d: disks){
            if(!(uniqueRadius.contains(d.radius))){
                uniqueDisks.add(d);
                uniqueRadius.add(d.radius);
            }
        }
        disks.retainAll(uniqueDisks);
    }
}