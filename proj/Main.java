package proj;

import java.io.*;
import java.util.*;


public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Coordinate> coords = new ArrayList();
        ArrayList<Disk> disks = new ArrayList();
        int w = takeInput(coords, disks);
        System.out.println(w);
        filterDisks(disks);
        List<List<String>> a = createGraph(coords, disks, w);
    }

    public static List<List<String>> createGraph(List<Coordinate> coords, List<Disk> disks, int w) {
        List<List<String>> nodes = new ArrayList<>();
        List<Integer> startNode = new ArrayList<>();
        int endNode = ((coords.size() * disks.size()) ^ 2);
        for (int i = 0; i < coords.size(); i++) {
            for (int j = 0; j < disks.size(); j++) {
                List<String> x = new ArrayList<>();
                inner:
                for (int h = 0; h < coords.size(); h++) {
                    if (!(i == h)) {
                        int distance = coords.get(i).dist(coords.get(h));
                        for (int k = 0; k < disks.size(); k++) {
                            if (disks.get(j).radius + disks.get(k).radius < distance) {
                                break inner;
                            }
                            x.add("coords: " + h + " disk: " + k);
                        }

                    }
                }
                nodes.add(x);
            }
        }
        return nodes;
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
        //sorts based on radius
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