package proj;

import java.io.*;
import java.util.*;


public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Coordinate> coords = new ArrayList<Coordinate>();
        ArrayList<Disk> disks = new ArrayList<Disk>();
        int w = takeInput(coords, disks);
        filterDisks(disks);
        Graph g = new Graph(coords,disks,w);
        int cheapestPathCost = g.dijkstras();
        System.out.println(cheapestPathCost);
    }

    public static void filterDisks(ArrayList <Disk> disks){
        ArrayList<Disk> copyOfDisks = new ArrayList<Disk>(disks);
        for(int i = 0; i< copyOfDisks.size(); i++){
            for(int j = 0; j< copyOfDisks.size(); j++){
                if( i != j){
                    if(copyOfDisks.get(i).radius <= copyOfDisks.get(j).radius && copyOfDisks.get(i).cost >= copyOfDisks.get(j).cost)
                        disks.remove(disks.get(i));
                }
            }
        }
        Collections.sort(disks);
        Collections.reverse(disks);
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
        return w;
    }
}