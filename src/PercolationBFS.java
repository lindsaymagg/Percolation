import java.util.LinkedList;
import java.util.Queue;

public class PercolationBFS extends PercolationDFSFast {

	/**
	 * Initialize a grid so that all cells are blocked.
	 * 
	 * @param n is the size of the simulated (square) grid
	 */
	public PercolationBFS(int n) {
		super(n);
	}
	
	
	/**
	 * Private helper method to mark all cells that are open and reachable from
	 * (row,col).
	 * 
	 * @param row is the row coordinate of the cell being checked/marked
	 * @param col is the column coordinate of the cell being checked/marked
	 * This version uses a queue.
	 */
	@Override
	public void dfs(int row, int col) {
		// out of bounds?
		if (! inBounds(row,col)) {
			return;
		}
		
		// Full or NOT open, don't process
		if (isFull(row, col) || !isOpen(row, col)) {
			return;
		}
		
		myGrid[row][col] = FULL;  // fill cell
        
        Queue<Integer> qp = new LinkedList<>();       
        int[] rowDelta = {0,0,-1,1};
        int[] colDelta = {-1,1,0,0};
     	
        //put cell on queue
        int cellNum = row * myGrid.length + col;
        qp.add(cellNum);
        
        while (qp.size() != 0){
            int cell = qp.remove();
            int cellRow = cell/myGrid.length;
            int cellCol = cell%myGrid.length;
            
            //investigate neighbors
            for(int k=0; k < rowDelta.length; k++){
                int neighborRow = cellRow + rowDelta[k];
                int neighborCol = cellCol + colDelta[k];
                //if neighbor is in bounds, empty, and open, fill it and add it to the queue
                if (inBounds(neighborRow,neighborCol) && !isFull(neighborRow,neighborCol) && isOpen(neighborRow,neighborCol)){
                	myGrid[neighborRow][neighborCol] = FULL;
                	int newCell = neighborRow * myGrid.length + neighborCol;
                    qp.add(newCell);  
                }
            }
        }	
	}
	
}
