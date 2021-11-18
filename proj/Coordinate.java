package proj;

public class Coordinate {

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
}