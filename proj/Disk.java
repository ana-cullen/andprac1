package proj;

public class Disk implements Comparable<Disk> {

    public int radius;
    public int cost;

    public Disk(int r, int c){
        this.radius = r;
        this.cost = c;
    }

    @Override
    public String toString(){
        return "r: "+this.radius+" c: "+this.cost;
    }

    @Override
    public int compareTo(Disk o) {
        if(this.radius - o.radius != 0){
            return this.radius - o.radius;
        }
        else{
            return o.cost - this.cost;
        }

    }
}