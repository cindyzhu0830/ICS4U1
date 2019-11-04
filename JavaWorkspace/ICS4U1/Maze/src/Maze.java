/*=================================================================
Program Name: Maze
Author: Cindy Zhu
Date: November 3, 2019
Programming Language: Java 8
=================================================================
Problem Definition 	– Required to find the shortest path to the cheese and exit with a given maze and starting point
Input – a txt file that contains the maze and two integers representing the starting coordinates of the rat
Output – length of shortest paths, two mazes with the shortest path to cheese and exit drew on each one
Process – Mainly breadth first search
=================================================================
*/ 

import java.io.FileNotFoundException;
public class Maze {
    
    /**main method:
	 * This is the main method that makes use of objects of type Reader and PathFinder
	 * 
	 * List of Local Variables
	 * reader - a Reader object that's used to read the input maze from the txt file and convert it into a 2d array
	 * cheeser - a PathFinder object that finds the shortest path to cheese
	 * exiter - a PathFinder object that finds the shortest path to the exit
	 *
	 * @param args <type String>
	 * @throws IO Exception	
	 * @return void
	 */
    public static void main(String[] args) throws FileNotFoundException {
        Reader reader = new Reader("C:\\HighSchool\\UHS\\mazefile1.txt");
        PathFinder cheeser = new PathFinder(reader.getX(), reader.getY(), reader.getMaze(), 'C');
        PathFinder exiter = new PathFinder(cheeser.getCx(), cheeser.getCy(),reader.getMaze(), 'X');
    }//end main method
}//end Maze class