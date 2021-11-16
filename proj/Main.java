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
        ArrayList<Disk> disks = new ArrayList();
        takeInput(w, coords, disks);
        System.out.println(disks);
        filterDisks(disks);
        System.out.println(disks);
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
        for(int i = 0; i < m; i++){
            String disk = myReader.nextLine();
            String[] split = disk.split(" ");
            dl.add(new Disk(Integer.parseInt(split[0]), Integer.parseInt(split[1])));
        }
        myReader.close();
    }

}