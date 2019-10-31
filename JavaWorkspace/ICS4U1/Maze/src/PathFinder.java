import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PathFinder {
   
    Queue<Integer> q1 = new LinkedList<>();
    Queue<Integer> q2 = new LinkedList<>();
    List<Integer> resultx = new LinkedList<>();
    List<Integer> resulty = new LinkedList<>();
    int level[][], prex[][], prey[][];
    boolean vis[][];
    int cx, cy, rl, cl;
    char chl[][];
    int r = 7, c = 11;
     public PathFinder( int sx, int sy,  char[][] ch, char target){
        level = new int[r][c];
        vis = new boolean[r][c];
        prex = new int[r][c];
        prey = new int[r][c];
        rl = r;
        cl = c;
        chl = ch;
        System.out.println("the shortest path is:" + search(sx, sy, 0, target));
        findPath(cx, cy, sx, sy);    
        printGraph(ch, resultx, resulty,prex);
    }
    int search(int x, int y,  int lv, char target) {
        vis[x][y] = true;
        if (chl[x][y] == target) {
            cx = x;
            cy = y;
            return lv;
        }
        push(x - 1, y, lv, -1, 0);
        push(x + 1, y, lv, 1, 0);
        push(x, y - 1, lv, 0, -1);
        push(x, y + 1, lv, 0, 1);
        if(q1.isEmpty()){
            System.out.println("No path available");
            return -1;
        }
        int t1 = q1.poll(), t2 = q2.poll();

        return search(t1, t2, level[t1][t2], target);
    }

    void findPath(int curX, int curY, int tarX, int tarY) {
        if(curX == tarX && curY == tarY) {
            return;
        }
        resultx.add(curX);
        resulty.add(curY);
        findPath(prex[curX][curY], prey[curX][curY], tarX, tarY);
    }

    void push(int x, int y, int lev, int dx, int dy) {
        if (x < rl && y < cl && !vis[x][y] && chl[x][y] != 'B') {
            level[x][y] = lev + 1;
            prex[x][y] = x - dx;
            prey[x][y] = y - dy;
            q1.add(x);
            q2.add(y);

        }
    }
    void printGraph(char[][] chList, List<Integer> resultx, List<Integer> resulty, int[][] prex) {
        draw(chList, resultx, resulty, prex);
        for (int i = 0; i < chList.length; i++) {
            for (int j = 0; j < chList[0].length; j++) {
                System.out.print(chList[i][j] + " ");
            }
            System.out.println();
        }
    }

    void draw(char[][] chList, List<Integer> resultx, List<Integer> resulty, int[][] prex) { // mutator that changes characters in the graph to draw the path
        while (!resultx.isEmpty()) {
            int tx = resultx.remove(resultx.size() - 1);
            int ty = resulty.remove(resulty.size() - 1);
            if (chList[tx][ty] != 'C' && chList[tx][ty] != 'X') {
                chList[tx][ty] = '*';
            }
        }
    }
    
}