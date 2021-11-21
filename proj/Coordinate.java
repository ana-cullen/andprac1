package proj;

public class Coordinate implements Comparable<Coordinate> {

    public int x;
    public int y;

    public Coordinate(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int dist(Coordinate c){
        int deltaX = Math.abs(this.x-c.x);
        int deltaY = Math.abs(this.y-c.y);
        return (int) Math.ceil(Math.sqrt(deltaX * deltaX + deltaY * deltaY));

    }

    @Override
    public String toString(){
        return "("+x+", "+y+")";
    }


    @Override
    public int compareTo(Coordinate o) {
        if(this.x-o.x != 0){
            return this.x-o.x;
        }
        else{
            return this.y-o.y;
        }
    }
}