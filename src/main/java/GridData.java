import java.util.ArrayList;

public class GridData {
    private int numRows;
    private int numCols;
    private ArrayList<ArrayList<Boolean>> rows;

    // Generic constructor for Jackson to use
    public GridData() {}

    // Initialize a blank grid of specified size
    public GridData(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
        this.rows = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            ArrayList<Boolean> blankRow = new ArrayList<>();
            for (int j = 0; j < numCols; j++) {
                blankRow.add(false);
            }
            this.rows.add(blankRow);
        }
    }

    public int getNumRows() {
        return numRows;
    }

    public void setNumRows(int numRows) {
        this.numRows = numRows;
    }

    public ArrayList<ArrayList<Boolean>> getRows() {
        return rows;
    }

    public void setRows(ArrayList<ArrayList<Boolean>> rows) {
        this.rows = rows;
    }

    public int getNumCols() {
        return numCols;
    }

    public void setNumCols(int numCols) {
        this.numCols = numCols;
    }

    public boolean getCellValue(int row, int col) {
        return this.rows.get(row).get(col);
    }

    public void setCellValue(int row, int col, boolean val) {
        this.rows.get(row).set(col, val);
    }
}
