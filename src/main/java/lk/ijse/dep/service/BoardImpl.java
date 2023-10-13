package lk.ijse.dep.service;

import java.util.Arrays;

public class BoardImpl implements Board {
    private final BoardUI boardUI;

    private final Piece[][] pieces = new Piece[NUM_OF_COLS][NUM_OF_ROWS];


    public BoardImpl(BoardUI boardUI) {
        for (int col = 0; col < pieces.length; col++) {
            Arrays.fill(pieces[col], Piece.EMPTY);

        }
        this.boardUI = boardUI;
    }


    @Override
    public BoardUI getBoardUI() {
        return boardUI;
    }

    @Override
    public int findNextAvailableSpot(int col) {
        for (int row = 0; row < pieces[col].length; row++) {
            if (pieces[col][row] == Piece.EMPTY) {
                return row;
            }
        }
        return -1;
    }

    @Override
    public boolean isLegalMove(int col) {
        return findNextAvailableSpot(col) != -1;
    }

    @Override
    public boolean existLegalMoves() {
        for (int col = 0; col < pieces.length; col++) {
            if (isLegalMove(col)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void updateMove(int col, Piece move) {
        int row = findNextAvailableSpot(col);
        pieces[col][row] = move;
    }

    @Override
    public void updateMove(int col, int row, Piece move) {
        pieces[col][row] = move;
    }

    @Override
    public Winner findWinner() {
        for (int row = 0; row < NUM_OF_ROWS; row++) {
            for (int cols = 0; cols < 3; cols++) {
                Piece piece=pieces[cols][row];
                if (piece != Piece.EMPTY && piece==pieces[cols+1][row] && piece==pieces[cols+2][row] && piece==pieces[cols+3][row]){
                    return new Winner(piece,cols,cols+3,row,row);
                }
            }
        }
        for (int cols = 0; cols < NUM_OF_COLS; cols++) {
            for (int row = 0; row < 2; row++) {
                Piece piece=pieces[cols][row];
                if (piece != Piece.EMPTY && piece == pieces[cols][row+1] && piece == pieces[cols][row+2] && piece == pieces[cols][row+3]){
                    return new Winner(piece,cols,cols,row,row+3);
                }
            }
        }
        return new Winner(Piece.EMPTY,-1,-1,-1,-1);
    }
}
