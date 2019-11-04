import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PathFinder {

    Queue<Integer> qx = new LinkedList<>();
    Queue<Integer> qy = new LinkedList<>();
    List<Integer> pathx = new LinkedList<>();
    List<Integer> pathy = new LinkedList<>();
    int level[][], par[][][];
    boolean vis[][];
    int cx, cy;
    char localMap[][];
    int r = 8, c = 12;

    /**
     * paramitized constructor
     * Constructs a PathFinder object based on the starting position, the maze taken from an input object and the taret character
     * @param sx starting x-coordinate of the rat
     * @param sy starting y-coordinate of the rat
     * @param target the target character that this object is used to find ('X' for exit and 'C' for cheese)
     * @param map the maze in the form of 2d array of type char
     *
     */
    public PathFinder(int sx, int sy, char[][] map, char target) {
        level = new int[r][c];
        vis = new boolean[r][c];
        par = new int[r][c][2];
        localMap = map;
        drawRat(sx, sy);
        System.out.println("the shortest path is:" + search(sx, sy, 0, target));
        findPath(cx, cy, sx, sy);
        printGraph();
    }//end constructor

     /**
     * search method
     * Recursive breadth first search that finds the shortest path to the target (cheese or exit)
     * @param x current x-coordinate of the rat
     * @param y current y-coordinate of the rat
     * @return the length of the shortest path
     */
    int search(int x, int y, int lv, char target) {
        vis[x][y] = true;
        if (localMap[x][y] == target) {
            cx = x;
            cy = y;
            return lv;
        }
        push(x - 1, y, lv, -1, 0);
        push(x + 1, y, lv, 1, 0);
        push(x, y - 1, lv, 0, -1);
        push(x, y + 1, lv, 0, 1);
        if (qx.isEmpty()) {
            System.out.println("No path available");
            return -1;
        }
        int t1 = qx.poll(), t2 = qy.poll();

        return search(t1, t2, level[t1][t2], target);
    }//end method search

     /**
     * findPath method
     * Recursive method that traces back from the end position to the starting point, finds all the points on the shortest path,
     * and push them into two separate lists, one for x-coordinats and one for y-coordinates, 
     * as preparation for printing the path later
     * terminates when reaches the starting position
     * @param curX the x-coordinate of the current point on the shortest path
     * @param curY the y-coordinate of the current point on the shortest path
     * @param tarX the x-coordinate of the start position
     * @param tarY the y-coordinate of the start position
     * @return void
     */
    void findPath(int curX, int curY, int tarX, int tarY) {
        if (curX == tarX && curY == tarY) {
            return;
        }
        pathx.add(curX);
        pathy.add(curY);
        findPath(par[curX][curY][0], par[curX][curY][1], tarX, tarY);
    }//end method findPath

     /**
     * push method
     * checks if a point around the current position of the rat is a valid point the rat can go,
     * if so, set the level of that point to the shortest distance from it to the starting point,
     * set its parent to the current point,
     * and pushes its coordinates into two queues for BFS processing in the search method
     * @param x the x coordinate of the rat's current location
     * @param y the y coordinate of the rat's current location
     * @param lev the shortest distance from the current point to the starting position
     * @param dx the shift in horizontal direction
     * @param dy the shift in vertical direction
     * @return void
     */
    void push(int x, int y, int lev, int dx, int dy) {
        if (x < r && y < c && !vis[x][y] && localMap[x][y] != 'B') {
            level[x][y] = lev + 1;
            par[x][y][0] = x - dx;
            par[x][y][1] = y - dy;
            qx.add(x);
            qy.add(y);

        }
    }//end method push 

     /**
     * printGraph method
     * Prints the maze with the shortest path drew on it
     * @return void
     */
    void printGraph() {
        draw();
        for (int i = 0; i < localMap.length; i++) {
            for (int j = 0; j < localMap[0].length; j++) {
                System.out.print(localMap[i][j] + " ");
            }
            System.out.println();
        }
    }//end method printGraph 

     /**
     * draw method
     * changes the character of every point on the shortest path in the maze to "*" to show the path
     * @return void
     */
    void draw() { 
        while (!pathx.isEmpty()) {
            int tx = pathx.remove(pathx.size() - 1);
            int ty = pathy.remove(pathy.size() - 1);
            if (localMap[tx][ty] != 'C' && localMap[tx][ty] != 'X') {
                localMap[tx][ty] = '*';
            }
        }
    }//end method draw

     /**
     * drawRat method
     * changes the character of the rat's starting position  to 'R' on the maze
     * @param mx the x coordinate of the starting position
     * @param my the y coordinate of the starting position
     * @return void
     */
    void drawRat(int mx, int my) {
        localMap[mx][my] = 'R';
    }//end method drawRat

    /**
     * getCx method
     * accessor method that return the current x-coordinate of the rat
     * @return the current x-coordinate of the rat
     */
    int getCx(){
        return cx;
    }//end method getCx

    /**
     * getCx method
     * accessor method that return the current y-coordinate of the rat
     * @return the current y-coordinate of the rat
     */
    int getCy(){
        return cy;
    }//end method getCy

}