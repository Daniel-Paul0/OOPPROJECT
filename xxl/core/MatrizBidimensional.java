package xxl.core;

import java.io.Serializable;

public class MatrizBidimensional implements Matriz, Serializable{
    private int _rows;
    private int _cols;
    private Cells[][] _cells;

    public MatrizBidimensional(int rows, int cols) {
        _rows = rows;
        _cols = cols;
        _cells = new Cells[_rows][_cols];
    }

    public Cells get(int row, int col) {
        return _cells[row][col];
    }

    public void set(int row, int col, Cells cell) {
        _cells[row][col] = cell;
    }
}