package proj;

public class Disk implements Comparable<Disk>{

    public int radius;
    public int cost;

    public Disk(int r, int c){
        this.radius = r;
        this.cost = c;
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