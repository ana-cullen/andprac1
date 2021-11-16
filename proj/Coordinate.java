package proj;

public class Coordinate {

    public int x;
    public int y;

    public Coordinate(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int dist(Coordinate c){
        return (int) Math.ceil(Math.sqrt((this.x-c.x)^2+(this.y-c.y)^2));
    }

    @Override
    public String toString(){
        return "("+x+", "+y+")";
    }
}