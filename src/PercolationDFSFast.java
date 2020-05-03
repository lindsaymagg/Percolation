
public class PercolationDFSFast extends PercolationDFS {

	/**
	 * Initialize a grid so that all cells are blocked.
	 * 
	 * @param n is the size of the simulated (square) grid
	 */
	public PercolationDFSFast(int n) {
		super(n);
	}
	
	@Override
	public void updateOnOpen(int row, int col) {
		//if touches the top, fill it
		if (row == 0) {
			dfs(row,col);
			return;
		}
		//if the cell above it is full
		if ((inBounds(row-1,col) && isFull(row-1,col))) { 
			dfs(row,col);
			return;
		}
		//if the cell below it is full
		if ((inBounds(row+1,col) && isFull(row+1,col))) { 
			dfs(row,col);
			return;
		}
		
		//if the cell to the left is full
		if ((inBounds(row,col-1) && isFull(row,col-1))) { 
			dfs(row,col);
			return;
		}
		
		//if the cell to the right is full
		if ((inBounds(row,col+1) && isFull(row,col+1))) { 
			dfs(row,col);
			return;
		}
	}
}
	
