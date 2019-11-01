import java.io.FileNotFoundException;
public class Maze {
    public static void main(String[] args) throws FileNotFoundException {
        Reader reader = new Reader();
        PathFinder cheeser = new PathFinder(reader.sx, reader.sy, reader.copych, 'C');
        PathFinder exiter = new PathFinder(cheeser.cx, cheeser.cy,reader.ch, 'X');
    }
}