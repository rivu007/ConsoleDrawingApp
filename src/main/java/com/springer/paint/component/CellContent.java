package com.springer.paint.component;

/**
 * Enumerations for the cell contents
 *
 * @author Abhilash Ghosh
 * @since 1.1
 * @version 1.2
 */
public enum CellContent {

    HORIZONTAL("-"),
    VERTICAL("|"),
    CROSS("X"),
    EMPTY(" ");

    String symbol;

    CellContent(String symbol) {
        this.symbol = symbol;
    }
}
