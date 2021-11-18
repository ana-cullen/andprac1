package proj;

<<<<<<< HEAD
public class Disk implements Comparable<Disk>{
=======
public class Disk implements Comparable<Disk> {
>>>>>>> origin/main

    public int radius;
    public int cost;

    public Disk(int r, int c){
        this.radius = r;
        this.cost = c;
    }

    @Override
    public int compareTo(Disk d){
        if(this.radius < d.radius){
            return -1;
        }
        if(this.radius == d.radius){
            return 0;
        }
        return 1;
    }

    @Override
    public String toString(){
        return "r: "+this.radius+" c: "+cost;
    }

    @Override
    public int compareTo(Disk o) {
        return this.radius - o.radius;
    }
}