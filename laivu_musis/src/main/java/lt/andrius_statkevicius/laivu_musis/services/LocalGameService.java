package lt.andrius_statkevicius.laivu_musis.services;

import lt.andrius_statkevicius.laivu_musis.entities.Board;
import lt.andrius_statkevicius.laivu_musis.entities.Coordinate;
import lt.andrius_statkevicius.laivu_musis.entities.Ship;

import java.util.List;

public class LocalGameService {


    public static final String[] HEADER_COLUMNS = {"K", "I", "L", "O", "M", "E", "T", "R", "A", "S"};
    public static final String[] HEADER_ROWS = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    public static final String SHIP = "#";
    public static final String HIT = "X";
    public static final String EMPTY = ".";
    public static final String MISSED = "+";

    public String[][] initializeBoard() {
        Board board = new Board( new String[10][10] );
        String[][] battleBoard = board.getBoard();
        for (int i = 0; i < battleBoard.length; i++) {
            for (int j = 0; j < battleBoard[i].length; j++) {
                battleBoard[i][j] = EMPTY;
            }
        }
        return battleBoard;
    }

    public void placeShipsOnBoard(String[][] myBoard, List<Ship> ships) {
        for (Ship ship : ships) {
            if (isShipVertical( ship )) {
                for (int i = ship.getHead().getRow(); i <= ship.getTail().getRow(); i++) {
                    myBoard[i][ship.getHead().getColumn()] = SHIP;
                }
            } else {
                //T0-A0
                for (int i = ship.getHead().getColumn(); i <= ship.getTail().getColumn(); i++) {
                    myBoard[ship.getHead().getRow()][i] = SHIP;
                }
            }
        }
    }

    public boolean isShipVertical(Ship ship) {
        return ship.getHead().getColumn() == ship.getTail().getColumn();
    }

    public int getCoordinateFromString(String columnAsString) {
        for (int i = 0; i < HEADER_COLUMNS.length; i++) {
            if (HEADER_COLUMNS[i].equalsIgnoreCase( columnAsString )) {
                return i;
            }
        }
        return -1;
    }

    public Coordinate parseCoordinate(String coordinate) {

        String rowAsString = coordinate.substring( 1, 2 );
        String columnAsString = coordinate.substring( 0, 1 );

        int rowAsInt = Integer.parseInt( rowAsString );
        int column = getCoordinateFromString( columnAsString );

        return new Coordinate( column, rowAsInt );
    }

    public Ship parseShip(String shipAsString) {
        String[] coordinate = shipAsString.split( "-" );
        Coordinate head = parseCoordinate( coordinate[0] );
        Coordinate tail = parseCoordinate( coordinate[1] );
        return new Ship( head, tail );
    }

    public String parseCoordinateToString(Coordinate coordinate) {
        String columnAsString = getColumnFromInt( coordinate.getColumn() );
        String row = String.valueOf(coordinate.getRow() );
        return columnAsString+row;
    }


    public String getColumnFromInt(int column) {
        String result = null;
        for (int i = 0; i < HEADER_COLUMNS.length; i++) {
            result = HEADER_COLUMNS[column];
        }
        return result;
    }

    public void drawBoard(String[][] board) {

        System.out.println();

        System.out.printf( "%2s", " " );

        for (int i = 0; i < 10; i++) {
            System.out.printf( "%2s", HEADER_COLUMNS[i] );
        }

        System.out.println();
        for (int i = 0; i < 10; i++) {
            System.out.printf( "%2s", HEADER_ROWS[i] );
            for (int j = 0; j < 10; j++) {
                System.out.printf( "%2s", board[i][j] );
            }
            System.out.println();
        }
        System.out.println();
    }
}
