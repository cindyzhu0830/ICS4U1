import java.io.FileNotFoundException;
public class Maze {
    public static void main(String[] args) throws FileNotFoundException {
        int r = 7, c = 11;
        IO io = new IO();
        PathFinder cheeseFinder = new PathFinder(r, c, io.sx, io.sy, io.reCopych(), 'C');
        io.printGraph(io.reCh(), cheeseFinder.resultx, cheeseFinder.resulty, cheeseFinder.prex);
        PathFinder exitFinder = new PathFinder(r, c, cheeseFinder.cx, cheeseFinder.cy,io.reCh(), 'X');
        io.printGraph(io.reCopych(), exitFinder.resultx, exitFinder.resulty, exitFinder.prex);

    }
}