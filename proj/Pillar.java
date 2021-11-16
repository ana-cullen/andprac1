package proj;

public class Pillar {
  
    public Coordinate coord;
    public Disk disk;

    public Pillar (Coordinate c, Disk d){
      this.coord = c;
      this.disk = d;
    }

    public boolean inRange(Pillar p){
      if(this.coord.dist(p.coord) <= this.disk.radius+p.disk.radius){
        return true;
      }
      return false;
    }
}
