import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class IO {
    char[][] ch, copych;
    int r = 7, c = 11;
    
    public int sx, sy;
 
    public IO() throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        ch = new char[7][11];
        copych = new char[7][11];
        readIn();
        System.out.println("Where do you want to drop the rat?");
        sx = sc.nextInt();
        sy = sc.nextInt();

    }

    void readIn() throws FileNotFoundException {
        File maze = new File("C:\\HighSchool\\UHS\\maze.txt");
        Scanner fsc = new Scanner(maze);
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                ch[i][j] = fsc.next().charAt(0);
                copych[i][j] = ch[i][j];
            }
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
            if (prex[tx][ty] == tx && chList[tx][ty] != 'C' && chList[tx][ty] != 'X') {
                chList[tx][ty] = '-';
            } else if (chList[tx][ty] != 'C' && chList[tx][ty] != 'X') {
                chList[tx][ty] = '|';
            }
        }
    }
    char[][] reCh(){
        return ch;
    }
    char[][] reCopych(){
        return copych;
    }


}