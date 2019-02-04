import java.util.*;

/**
 * Class for solving the n-queen problem
 *
 * @Rasmus Herskind and Kristian Larsen
 * @23/10/2018
 */
public class Solver {
    //Decleration of variables
    private int noOfQueens;
    private int[] queens;
    private int noOfSolutions;
    private boolean showSolution;

    // From (opgave 5), creates a Solver obejct and test solutions for specific parameter values
    public static void testSolver() {
        Solver s = new Solver();
        s.findAllSolutions(1);
        s.findAllSolutions(2);
        s.findAllSolutions(6);

        System.out.println("");
        s.findNoOfSolutions(1,12);
    }

    //Method for finding all solutions given a specified number of queens
    public void findAllSolutions (int noOfQueens) {
        queens = new int[noOfQueens];
        this.noOfQueens = noOfQueens;
        noOfSolutions = 0;
        showSolution = true;
        double startTime = System.currentTimeMillis();//The time before the program start in ms
        System.out.println("***************************************************************");
        System.out.println("Solutions for " + noOfQueens + " queens");
        System.out.println("");

        //Use the positionQueens method to find all solutions given the 
        //input noOfQueens in and noOfQueens(row) x noOfQueens(column) chesstable
        positionQueens(0); 

        double endTime = System.currentTimeMillis();//The time after the program has ended in ms
        System.out.println("");
        System.out.println("A total of " + noOfSolutions + " solutions were found in " 
            + (endTime-startTime) + " ms");
        System.out.println("***************************************************************");
    }

    //Finds the number of all the solutions, where noOfQueens runs in the
    //interval from min to max including both and prints the result
    public void findNoOfSolutions(int min, int max) {
        showSolution = false;
        System.out.println("***************************************************************");
        System.out.println("Queens   Solutions   Time (ms+1)    Solution/time");
        for (int i = min;i<=max;i++) {
            noOfQueens = i;
            queens = new int[noOfQueens];
            noOfSolutions = 0;
            int startTime = (int)System.currentTimeMillis();
            positionQueens(0);
            int duration = (int)(System.currentTimeMillis()-startTime)+1;
            System.out.format("  %3d %,12d    %8d       %,8d %n",
                noOfQueens, noOfSolutions, duration, noOfSolutions/duration);
        }
        System.out.println("***************************************************************");
    }

    //Recursive method for placing the queens on a legal spot and printing
    //the solution
    private void positionQueens(int row) {
        //Print solution if the length of the queens array is equal row
        //Test if position is legal and place queen
        if (row < noOfQueens) {
            for (int c = 0;c < noOfQueens; c++) {
                if (legal(row,c)) {
                    queens[row] = c;
                    positionQueens(row+1);
                }
            }
        }
        else {
            if (showSolution) {
                printSolution();
            }
            noOfSolutions++;
        } 
    }

    //Checks if the given position for the queen is legal
    private boolean legal (int row, int col) {
        for (int i = 0; i < row; i++) {
            if (queens[i] == col || //Checks downwards
            queens[i] == col + row - i || //Checks to the left
            queens[i] == col + i - row //Checks to the right
            ) {
                return false;
            }
        }
        return true;
    }

    //If the current position is a solution, it prints the solution to the terminal
    private void printSolution(){
        for (int i=0; i<noOfQueens; i++) {
            System.out.format(" %2s", convert(i,queens[i]));
        }
        System.out.format(" %n");
    }

    //Converts the given parameters row and coloumn to standard chess notation
    private String convert (int row, int col) {
        int number = row+1;
        char letter = (char)(col+97);
        return String.valueOf(letter)+String.valueOf(number);
    }
}
