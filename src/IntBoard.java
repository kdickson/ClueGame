
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

public class IntBoard {

    private int row = 4;
    private int col = 4;
    // Note: we should probably, for the sake of good design, use a Board Cell object instead of Integers here. That way we can store both the i and j position and clean up the adjacency list code quite a bit.
    private HashMap<Integer, List<Integer>> adj = new HashMap<Integer, List<Integer>>();
    private List<Integer> adjList;
    private TreeSet<Integer> targets;
    private boolean visited[][];

    public IntBoard() {
    	visited = new boolean[row][col];
    	targets = new TreeSet<Integer>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                visited[i][j] = false;
            }
        }
    }

    public void calcAdjacencies() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                    boolean top = false;
                    boolean bottom = false;
                    boolean right = false;
                    boolean left = false;
                    if ((j + 1) == row) {
                        right = true;
                    }
                    if ((j - 1) == -1) {
                        left = true;
                    }
                    if (i - 1 == -1) {
                        top = true;
                    }
                    if ((i + 1) == col) {
                        bottom = true;
                    }
                    adjList = new LinkedList<Integer>();
                    if (!right) {
                        adjList.add(4 * i + j + 1);
                    }
                    if (!left) {
                        adjList.add(4 * i + j - 1);
                    }
                    if (!top) {
                        adjList.add(4 * (i - 1) + j);
                    }
                    if (!bottom) {
                        adjList.add(4 * (i + 1) + j);
                    }
                    adj.put(4*i + j, adjList);

            }
        }
    }

    public void calcTargets(int rowT, int colT, int steps) {
    	if (visited[rowT][colT] == false && steps==0){
           		targets.add(row*rowT+colT); 
       	 }
         else {
        	 for (int i : getAdjList(row*colT+rowT)){
        		visited[rowT][colT] = true;
        		rowT = (int) Math.floor(i/row);
        		colT = i%row;//racist kkk
        		calcTargets(rowT, colT, --steps);
        	 } 
         }
         
    }

    public TreeSet getTargets() {
        return targets;
    }

    public LinkedList<Integer> getAdjList(int indx) {
        return (LinkedList<Integer>) adj.get(indx);
    }

    public int calcIndex(int r, int c) {
        return row * r + c;
    }

    public static void main(String[] args) {
        IntBoard ib = new IntBoard();
        ib.calcAdjacencies();
        ib.calcTargets(0, 0, 2);
    }
}