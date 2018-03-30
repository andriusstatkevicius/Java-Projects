package lt.andrius_statkevicius.laivu_musis.entities;

import java.util.ArrayList;

public class Ship {

    public static final int[] shipLengths = {4, 3, 3, 2, 2, 2, 1, 1, 1, 1};

    Coordinate head;
    Coordinate tail;

    public Ship(Coordinate head, Coordinate tail) {
        this.head = head;
        this.tail = tail;
    }

    public Coordinate getHead() {
        return head;
    }

    public void setHead(Coordinate head) {
        this.head = head;
    }

    public Coordinate getTail() {
        return tail;
    }

    public void setTail(Coordinate tail) {
        this.tail = tail;
    }
}
