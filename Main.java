public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        int w = 0;
        List<Coordinate> coords = new ArrayList();
        List<Disk> disks = new ArrayList();
        takeInput(w, coords, disks);
        //yeet
    }

    public static void takeInput(int w, List <Coordinate> cl, List <Disk> dl) throws FileNotFoundException{
        File myObj = new File("test.txt");
        Scanner myReader = new Scanner(myObj);
        String fstLn = myReader.nextLine();
        String[] splitted = fstLn.split(" ");
        int n;
        n = Integer.parseInt(splitted[0]);
        System.out.println(n);
        myReader.close();
    }

}