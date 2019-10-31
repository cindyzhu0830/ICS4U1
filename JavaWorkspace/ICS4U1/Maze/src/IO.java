import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class IO {
    char[][] ch, copych;
    int r = 7, c = 11;
    
    public int sx = -1, sy = -1;
 
    public IO() throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        ch = new char[7][11];
        copych = new char[7][11];
        readIn();
        System.out.println("Where do you want to drop the rat?");
        do{
            sx = sc.nextInt();
            sy = sc.nextInt();
        }while(!isValid(sx, sy));

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


    boolean isValid(int x,  int y){
        boolean valid = true;
        if(x>=r||x<0||y>=c||y<0){
            valid = false;
        }
        else if(ch[x][y]=='B') valid = false;
        if(!valid){
            System.out.println("Position not valid, try again");
        }
        return valid;
    }


}