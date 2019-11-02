import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Reader {
    private char[][] map;
    int r = 8, c = 12;

    int sx = -1, sy = -1;

    public Reader() throws FileNotFoundException{
        this("C:\\mazefile1.txt");
    }

    public Reader(String fileDir) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        map = new char[r][c];
        readIn(fileDir);
        System.out.println("Where do you want to drop the rat?");
        do {
            sx = sc.nextInt();
            sy = sc.nextInt();
        } while (!isValid(sx, sy));

    }

    /**
     * readIn method
     * Reads a maze in the form of txt file and stores it in a 2d array. 
     * @param fileDir the location of the maze file
     * @return void
     */

    void readIn(String fileDir) throws FileNotFoundException {
        File maze = new File(fileDir);
        Scanner fsc = new Scanner(maze);
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                map[i][j] = fsc.next().charAt(0);
            }
        }

    }

    boolean isValid(int x, int y) {
        boolean valid = true;
        if (x >= r || x < 0 || y >= c || y < 0) {
            valid = false;
        } else if (map[x][y] == 'B')
            valid = false;
        if (!valid) {
            System.out.println("Position not valid, try again");
        }
        return valid;
    }

    char[][] getMaze() {
        char[][] copy = new char[r][c];
        for (int i = 0; i < map.length; i++) {
            System.arraycopy(map[i], 0, copy[i], 0, map[i].length);
        }
        return copy;
    }

}