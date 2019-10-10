package app;

import java.util.*;
//git push test

public class Maze {
    static Queue<Integer> q1 = new LinkedList<>();
    static Queue<Integer> q2 = new LinkedList<>();
    static List<Integer> resultx = new LinkedList<>();
    static List<Integer> resulty = new LinkedList<>();
    static int level[][], prex[][], prey[][];
    static int r, c, cx, cy;
    static char[][] ch, copych;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        r = 7;
        c = 11;
        level = new int[r][c];
        prex = new int[r][c];
        prey = new int[r][c];
        ch = new char[r][c];
        copych = new char[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                ch[i][j] = sc.next().charAt(0);
                copych[i][j] = ch[i][j];
            }
        }
        System.out.println("where do you want to drop the rat? (input 2d coordinates");
        int sx = sc.nextInt();
        int sy = sc.nextInt();

        boolean vis[][] = new boolean[r][c];
        System.out.println(search(sx, sy, vis, ch, 0, 'C'));
        printPath(cx, cy, sx, sy);
        draw(copych);
        printGraph(copych);
        System.out.println();

        sx = cx;
        sy = cy;

        boolean vis2[][] = new boolean[r][c];
        q1.clear();
        q2.clear();
        System.out.println(search(cx, cy, vis2, copych, 0, 'X'));
        printPath(cx, cy, sx, sy);
        printGraph(ch);
        System.out.println();
        draw(ch);
        printGraph(ch);

    }







    static int search(int x, int y, boolean vis[][], char[][] ch, int lv, char target) {
        level[x][y] = lv;
        vis[x][y] = true;

        if (ch[x][y] == target) {

            cx = x;
            cy = y;
            return lv;

        }
        if (x < r - 1 && y < c - 1) {
            if (!vis[x - 1][y] && ch[x - 1][y] != 'B') {
                level[x - 1][y] = lv + 1;
                prex[x - 1][y] = x;
                prey[x - 1][y] = y;
                q1.add(x - 1);
                q2.add(y);

            }
            if (!vis[x + 1][y] && ch[x + 1][y] != 'B') {
                level[x + 1][y] = lv + 1;
                prex[x + 1][y] = x;
                prey[x + 1][y] = y;
                q1.add(x + 1);
                q2.add(y);
            }
            if (!vis[x][y - 1] && ch[x][y - 1] != 'B') {
                level[x][y - 1] = lv + 1;
                prex[x][y - 1] = x;
                prey[x][y - 1] = y;
                q1.add(x);
                q2.add(y - 1);

            }
            if (!vis[x][y + 1] && ch[x][y + 1] != 'B') {
                level[x][y + 1] = lv + 1;
                prex[x][y + 1] = x;
                prey[x][y + 1] = y;
                q1.add(x);
                q2.add(y + 1);

            }
        }
        int t1 = q1.poll();
        int t2 = q2.poll();
        return search(t1, t2, vis, ch, level[t1][t2], target);
    }

    public static void printPath(int curX, int curY, int tarX, int tarY) {
        while (curX != tarX || curY != tarY) {
            resultx.add(curX);
            resulty.add(curY);
            int tcx = curX;
            curX = prex[curX][curY];
            curY = prey[tcx][curY];
        }
    }

    public static void printGraph(char[][] chList) {
        for (int i = 0; i < chList.length; i++) {
            for (int j = 0; j < chList[0].length; j++) {
                System.out.print(chList[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void draw(char[][] chList) {
        while (!resultx.isEmpty()) {
            int tx = resultx.remove(resultx.size() - 1);
            int ty = resulty.remove(resulty.size() - 1);
            if (prex[tx][ty] == tx && chList[tx][ty] != 'C' && chList[tx][ty] != 'X') {
                chList[tx][ty] = '-';
            } else if (chList[tx][ty] != 'C' && chList[tx][ty] != 'X') {
                chList[tx][ty] = '|';
            }
        }
    }

}