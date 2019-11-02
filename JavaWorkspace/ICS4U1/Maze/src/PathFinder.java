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

    public PathFinder(int sx, int sy, char[][] map, char target) {
        level = new int[r][c];
        vis = new boolean[r][c];
        par = new int[r][c][2];
        localMap = map;
        drawRat(sx, sy);
        System.out.println("the shortest path is:" + search(sx, sy, 0, target));
        findPath(cx, cy, sx, sy);
        printGraph();
    }

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
    }

    void findPath(int curX, int curY, int tarX, int tarY) {
        if (curX == tarX && curY == tarY) {
            return;
        }
        pathx.add(curX);
        pathy.add(curY);
        findPath(par[curX][curY][0], par[curX][curY][1], tarX, tarY);
    }

    void push(int x, int y, int lev, int dx, int dy) {
        if (x < r && y < c && !vis[x][y] && localMap[x][y] != 'B') {
            level[x][y] = lev + 1;
            par[x][y][0] = x - dx;
            par[x][y][1] = y - dy;
            qx.add(x);
            qy.add(y);

        }
    }

    void printGraph() {
        draw();
        for (int i = 0; i < localMap.length; i++) {
            for (int j = 0; j < localMap[0].length; j++) {
                System.out.print(localMap[i][j] + " ");
            }
            System.out.println();
        }
    }

    void draw() { // mutator that changes characters in the graph to draw the path
        while (!pathx.isEmpty()) {
            int tx = pathx.remove(pathx.size() - 1);
            int ty = pathy.remove(pathy.size() - 1);
            if (localMap[tx][ty] != 'C' && localMap[tx][ty] != 'X') {
                localMap[tx][ty] = '*';
            }
        }
    }

    void drawRat(int mx, int my) {
        localMap[mx][my] = 'R';
    }

}