package lt.andrius_statkevicius.laivu_musis.entities;

public class Coordinate {

    int row;
    int column;

    public Coordinate(int column, int row) {
        this.row = row;
        this.column = column;
    }


    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }



    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}
