package othello;

import javax.swing.JFrame;
import board.Board;

public abstract class _2DGame extends JFrame {
    private Board[][] board;
    private int rows, cols;

    public abstract boolean hasGameOver();
    public abstract String getWinner();
    public abstract void computerTurn();

    public _2DGame(int rows, int columns) {
        this.rows = rows;
        this.cols = columns;
    }

    public Board getboardPiece(int row, int col) {
        return board[row][col];
    }

    public Board getRefboardPiece(int row, int col, Board[][] board) {
        return board[row][col];
    }

    public void setBoardPiece(int row, int col, Board b) {
        this.board[row][col] = b;
    }

    public void setRefBoardPiece(int row, int col,Board b, Board[][] board) {
        board[row][col] = b;
    }

    public int getTotalRows() {
        return this.rows;
    }

    public int getTotalColumns() {
        return this.cols;
    }

    public Board[][] getBoard(){
        return this.board;
    }

    public void renderBoard() {
        String[] boardInString =
                new String [this.board.length];
        int rowCount = 0;
        for(Board[] rowBoard: this.board) {
            boardInString[rowCount] = String.valueOf(rowCount + 1);
            boardInString[rowCount] += ": ";
           for(Board boardPiece: rowBoard) {
                if(boardPiece == null) {
                    boardInString[rowCount] += ".";
                } else{
                    boardInString[rowCount] += boardPiece.toString();
                }
           }
           rowCount++;
        }
        System.out.println("The Board Result: ");
        System.out.println("-------------------");
        for(String boardResult : boardInString) {
            System.out.println(boardResult);
        }
        System.out.println("-------------------");
    }

    public boolean isBoardFull(){
        for (Board[] rowBoard: this.board) {
            for(Board boardPiece : rowBoard) {
                if(boardPiece == null) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isRightMove(int row, int col) {
        return (row >=0 && row < this.rows && col >= 0 && col < this.cols);
    }
}
