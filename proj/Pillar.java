package proj;

public class Pillar {
  
    public Coordinate coord;
    public Disk disk;
    public int id;

    public Pillar (Coordinate c, Disk d, int id){
      this.coord = c;
      this.disk = d;
      this.id=id;
    }

    public int getId(){
        return this.id;
    }

    public String toString(){
        return this.coord.toString() + this.disk.toString();
    }

    public boolean inRange(Pillar p){
      if(this.coord.dist(p.coord) <= this.disk.radius+p.disk.radius){
        return true;
      }
      return false;
    }
}
