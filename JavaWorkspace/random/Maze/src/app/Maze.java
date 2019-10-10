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

    public static void main(String[] args) {
        int sx = 0, sy = 0;
        Scanner sc = new Scanner(System.in);
        r = sc.nextInt();
        c = sc.nextInt();
        level = new int[r][c];
        prex = new int[r][c];
        prey = new int[r][c];
        char[][] ch = new char[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                ch[i][j] = sc.next().charAt(0);
                if (ch[i][j] == 'R') {
                    sx = i;
                    sy = j;
                }
            }
        }
        boolean vis[][] = new boolean[r][c];
        System.out.println(search(sx, sy, vis, ch, 0, 'C'));
        int copycx = cx, copycy = cy;
        while(copycx!=sx||copycy!=sy){
            resultx.add(copycx);
            resulty.add(copycy);
            int tcx = copycx;
            copycx = prex[copycx][copycy];
            copycy = prey[tcx][copycy];
        }
        while(!resultx.isEmpty()){
            System.out.print(resultx.remove(resultx.size()-1) + " " + resulty.remove(resulty.size()-1) + ", ");
        }
        System.out.println();

        sx = cx;
        sy = cy;

        boolean vis2[][] = new boolean[r][c];
        q1.clear();
        q2.clear();
        System.out.println(search(cx, cy, vis2, ch, 0, 'X'));
        while(cx!=sx||cy!=sy){
            resultx.add(cx);
            resulty.add(cy);
            int tcx = cx;
            cx = prex[cx][cy];
            cy = prey[tcx][cy];
        }
        while(!resultx.isEmpty()){
            System.out.print(resultx.remove(resultx.size()-1) + " " + resulty.remove(resulty.size()-1) + ", ");
        }
        System.out.println();
        

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
                prey[x-1][y] = y;
                q1.add(x - 1);
                q2.add(y);

            }
            if (!vis[x + 1][y] && ch[x + 1][y] != 'B') {
                level[x + 1][y] = lv + 1;
                prex[x + 1][y] = x;
                prey[x+1][y] = y;
                q1.add(x + 1);
                q2.add(y);
            }
            if (!vis[x][y - 1] && ch[x][y - 1] != 'B') {
                level[x][y - 1] = lv + 1;
                prex[x][y-1] = x;
                prey[x][y-1] = y;
                q1.add(x);
                q2.add(y - 1);

            }
            if (!vis[x][y + 1] && ch[x][y + 1] != 'B') {
                level[x][y + 1] = lv + 1;
                prex[x][y+1] = x;
                prey[x][y+1] = y;
                q1.add(x);
                q2.add(y + 1);

            }
        }
        int t1 = q1.poll();
        int t2 = q2.poll();
        return search(t1, t2, vis, ch, level[t1][t2], target);
    }

}