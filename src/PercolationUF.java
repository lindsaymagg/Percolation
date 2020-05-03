
public class PercolationUF implements IPercolate {
	
	//instance variables
	boolean[][] myGrid;
	int myOpenCount = 0;
	IUnionFind myFinder;
	int VTOP;
	int VBOTTOM;
	

	/*
	 * 
	 */
	public PercolationUF(int size, IUnionFind finder) {
		myGrid = new boolean[size][size];
		VTOP = size*size;
		VBOTTOM = size*size + 1;
		myFinder = finder;
		finder.initialize(size*size + 2);
	}

	@Override
	public boolean isOpen(int row, int col) {
		if (row < 0 || row >= myGrid.length) throw new IndexOutOfBoundsException();
		if (col < 0 || col >= myGrid[0].length) throw new IndexOutOfBoundsException();
		return myGrid[row][col] == true;
	}

	@Override
	public boolean isFull(int row, int col) {
		if (row < 0 || row >= myGrid.length) throw new IndexOutOfBoundsException();
		if (col < 0 || col >= myGrid[0].length) throw new IndexOutOfBoundsException();
		return myFinder.connected(VTOP, makeInt(row,col));
	}

	@Override
	public boolean percolates() {
		return myFinder.connected(VTOP, VBOTTOM);
	}

	@Override
	public int numberOfOpenSites() {
		return myOpenCount;
	}

	private int makeInt(int row, int col) {
		return row * myGrid.length + col;
	}

	@Override
	public void open(int row, int col) {
		if (row < 0 || row >= myGrid.length) throw new IndexOutOfBoundsException();
		if (col < 0 || col >= myGrid[0].length) throw new IndexOutOfBoundsException();
		
		int[] rowDelta = {0,0,-1,1};
        int[] colDelta = {-1,1,0,0};
		if (! isOpen(row, col)) {
			myGrid[row][col] = true;
			myOpenCount += 1;
			int cellInt = makeInt(row,col);		
			
            //if cell is connected to VTOP, union
            if (row == 0) myFinder.union(cellInt, VTOP);
            
            //if cell is connected to VBOTTOM, union
            if (row == myGrid.length - 1) myFinder.union(cellInt, VBOTTOM);
            	
            //investigate neighbors
            for(int k=0; k < rowDelta.length; k++){
                int neighborRow = row + rowDelta[k];
                int neighborCol = col + colDelta[k];
                //if neighbor is in bounds and open, union
                if (inBounds(neighborRow,neighborCol) && isOpen(neighborRow,neighborCol)){
                	myFinder.union(cellInt, makeInt(neighborRow,neighborCol));
                }
            }
			
		}
		
	}

	private boolean inBounds(int row, int col) {
		if (row < 0 || row >= myGrid.length) return false;
		if (col < 0 || col >= myGrid[0].length) return false;
		return true;
	}
}
