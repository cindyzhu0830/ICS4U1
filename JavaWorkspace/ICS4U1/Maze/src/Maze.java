import java.io.FileNotFoundException;
public class Maze {
    public static void main(String[] args) throws FileNotFoundException {
        IO io = new IO();
        PathFinder cheeser = new PathFinder(io.sx, io.sy, io.copych, 'C');
        PathFinder exiter = new PathFinder(cheeser.cx, cheeser.cy,io.ch, 'X');
    }
}