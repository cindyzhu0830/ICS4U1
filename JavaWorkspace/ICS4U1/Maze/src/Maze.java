

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Maze {
    static Queue<Integer> q1 = new LinkedList<>();
    static Queue<Integer> q2 = new LinkedList<>();
    static List<Integer> resultx = new LinkedList<>();
    static List<Integer> resulty = new LinkedList<>();
    static int level[][], prex[][], prey[][], r, c, cx, cy, sx, sy;
    static char[][] ch, copych;

    public static void main(String[] args) throws FileNotFoundException {
        r = 7;
        c = 11;
        level = new int[r][c];
        prex = new int[r][c];
        prey = new int[r][c];
        ch = new char[r][c];
        copych = new char[r][c];
        boolean vis[][] = new boolean[r][c];
        boolean vis2[][] = new boolean[r][c];
        
        IO io = new IO();
        ch = io.reCh();
        copych = io.reCopych();
        sx = io.sx; sy = io.sy;
        System.out.println("Shortest path to cheese: " + search(sx, sy, vis, ch, 0, 'C'));
        findPath(cx, cy, sx, sy);
        io.printGraph(copych, resultx, resulty, prex);

        sx = cx;
        sy = cy;
        q1.clear();
        q2.clear();

        System.out.println("Shortest path to exit: " + search(cx, cy, vis2, copych, 0, 'X'));
        findPath(cx, cy, sx, sy);
        io.printGraph(ch, resultx, resulty, prex);

    }



    static int search(int x, int y, boolean vis[][], char[][] ch, int lv, char target) {
        vis[x][y] = true;
        if (ch[x][y] == target) {
            cx = x;
            cy = y;
            return lv;
        }
        if (x < r - 1 && y < c - 1) {
            push(x - 1, y, vis, lv, -1, 0);
            push(x + 1, y, vis, lv, 1, 0);
            push(x, y - 1, vis, lv, 0, -1);
            push(x, y + 1, vis, lv, 0, 1);
            if (!vis[x][y + 1] && ch[x][y + 1] != 'B') {
                level[x][y + 1] = lv + 1;
                prex[x][y + 1] = x;
                prey[x][y + 1] = y;
                q1.add(x);
                q2.add(y + 1);

            }
        }
        int t1 = q1.poll(), t2 = q2.poll();

        return search(t1, t2, vis, ch, level[t1][t2], target);
    }

    public static void findPath(int curX, int curY, int tarX, int tarY) {
        while (curX != tarX || curY != tarY) {
            resultx.add(curX);
            resulty.add(curY);
            int tcx = curX;
            curX = prex[curX][curY];
            curY = prey[tcx][curY];
        }
    }



    public static void push(int x, int y, boolean[][] visi, int lev, int dx, int dy) {
        if (!visi[x][y] && ch[x][y] != 'B') {
            level[x][y] = lev + 1;
            prex[x][y] = x - dx;
            prey[x][y] = y - dy;
            q1.add(x);
            q2.add(y);

        }
    }

}