package xxl.core;

public interface Matriz {
    Cells get(int row, int col);
    void set(int row, int col, Cells cell);
}