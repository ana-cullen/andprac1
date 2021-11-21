package proj;

public class Pillar implements Comparable<Pillar>{
  
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
        return this.coord.toString() + " " + this.disk.toString() + " " + this.id;
    }

    @Override
    public int compareTo(Pillar p){
      return this.disk.cost-p.disk.cost;
    }

    public boolean inRange(Pillar p){
        int distance = this.coord.dist(p.coord);
        return (distance <= this.disk.radius + p.disk.radius
                && (distance != 0)
                && this.disk.radius < distance + p.disk.radius
                && p.disk.radius < distance + this.disk.radius);
    }
}
