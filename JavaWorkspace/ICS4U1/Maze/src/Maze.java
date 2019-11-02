import java.io.FileNotFoundException;
public class Maze {
    public static void main(String[] args) throws FileNotFoundException {
        Reader reader = new Reader("C:\\HighSchool\\UHS\\mazefile1.txt");
        PathFinder cheeser = new PathFinder(reader.sx, reader.sy, reader.getMaze(), 'C');
        PathFinder exiter = new PathFinder(cheeser.cx, cheeser.cy,reader.getMaze(), 'X');
    }
}