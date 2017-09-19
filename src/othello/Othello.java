package othello;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.util.*;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

// Own package
import board.Board;
import board.boardPiece;
import main.GUI_Othello;


public class Othello extends _2DGame implements Runnable {
    private Scanner input;
    private JButton[][] jbuttons;
    private JPanel jPanel;
    private GUI_Othello othello;
    private int[][] oldboardValues = { { 50, -8, 5, 2, 2, 5, -8, 50 },
            { -8, -16, 1, 1, 1, 1, -16, -8 }, { 5, 1, 1, 1, 1, 1, 1, 5 },
            { 2, 1, 1, 0, 0, 1, 1, 2 }, { 2, 1, 1, 0, 0, 1, 1, 2 },
            { 5, 1, 1, 1, 1, 1, 1, 5 }, { -8, -16, 1, 1, 1, 1, -16, -8 },
            { 50, -8, 5, 2, 2, 5, -8, 50 } };
    private int[][] boardValues = { { 50, -8, 6, 4, 4, 6, -8, 50 },
            { -8, -16, -3, -3, -3, -3, -16, -8 }, { 6, -3, 1, 1, 1, 1, -3, 6 },
            { 4, -3, 1, 0, 0, 1, -3, 4 }, { 4, -3, 1, 0, 0, 1, -3, 4 },
            { 6, -3, 1, 1, 1, 1, -3, 6 }, { -8, -16, -3, -3, -3, -3, -16, -8 },
            { 50, -8, 6, 4, 4, 6, -8, 50 } };
    private int[][] forsightboardValues = { { 200, 6, 8, 6, 6, 8, 6, 200 },
            { 6, 1, 1, 1, 1, 1, 1, 6 },
            { 8, 1, 1, 1, 1, 1, 1, 8 },
            { 6, 1, 1, 1, 1, 1, 1, 6 },
            { 6, 1, 1, 1, 1, 1, 1, 6 },
            { 8, 1, 1, 1, 1, 1, 1, 8 },
            { 6, 1, 1, 1, 1, 1, 1, 6 },
            { 200, 6, 8, 6, 6, 8, 6, 200 } };

    public Othello (){
        super(8,8);
        this.setBoardPiece(3,3, new boardPiece("O"));
        this.setBoardPiece(3,4, new boardPiece("X"));
        this.setBoardPiece(4,4, new boardPiece("O"));
        this.setBoardPiece(4,3, new boardPiece("X"));

        this.input = new Scanner(System.in);
    }

    // Not finished
    public boolean isRightMove(int row, int col, String player, Board[][] board) {
        if(super.isRightMove(row, col)) {
            if(getRefboardPiece(row, col, board) != null) {
                return false;
            } else{
                for(int boardIndex = col - 1; boardIndex >= 0; board == null) {
                    if(getRefboardPiece(row, col, board) == null) {
                        break;
                    }
                }
            }
        }
        return false;
    }

    public boolean hasGameOver(){
        boolean gameOver = false;
        int countX =0, countO = 0;
        for(int row = 0; row < this.getTotalRows(); row ++ ){
            for(int col = 0; col < this.getTotalColumns(); col++) {
                if(this.isRightMove(row, col, "X",this.getBoard())) {
                    countX++;
                }
                if(this.isRightMove(row, col, "O", this.getBoard())){
                    countO++;
                }
            }
        }
        return (countX == 0 || countO == 0);
    }

    public boolean playerCanMove(){
        int row = 0, col = 0;
        while(row < this.getTotalRows()) {
            while(col < this.getTotalColumns()) {
                if(this.isRightMove(row, col,"X", this.getBoard())){
                    return true;
                }
                col++;
            }
            row++;
        }
        return false;
    }

    public boolean playerTurn(int row, int col) {
        if (this.isRightMove(row, col, "X", this.getBoard())) {
            this.setBoardPiece(row, col, new boardPiece("X"));
            this.updateBoard(row,col, "X");
            this.renderBoard();
            return true;
        }
        return false;
    }

    public int[] coordsInputSelection (){
        int [] coords = new int [2];
        try {
            System.out.print("Please enter the row number where you would like to enter");
            coords[0] = this.input.nextInt();
            System.out.print("Please enter the column number where you would like to enter");
            coords[1] = this.input.nextInt();
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("The coords is invalid, please enter numeric input");
            while(true) {
                try {
                    System.out.print("Please enter the row number where you would like to enter");
                    coords[0] = this.input.nextInt();
                    System.out.print("Please enter the column number where you would like to enter");
                    coords[1] = this.input.nextInt();
                    break;
                } catch(Exception e1) {
                    e1.printStackTrace();
                }
            }
        }
        return coords;
    }

    // Not finished
    public void updateBoard(int row, int col, String player) {

    }

    public void computerTurn() {
        System.out.println("-------------");
        System.out.println("Computer's turn");
        this.coreAIMove(3);
        this.renderBoard();
    }

    // Not finished
    public int[][] listCoreAIMove(int move, String player, Board[][] board) {
        return new int[8][8];
    }


    public void coreAIMove(int move) {
        int rowMove = 0, columnMove = 0;
        ArrayList<Integer> rowListMoves = new ArrayList<Integer>(rowMove);
        ArrayList<Integer> columnListMoves = new ArrayList<Integer>(columnMove);

        int[][] computerMoves;
        Board[][] board = new Board[this.getTotalRows()][this.getTotalColumns()];
        for (int row = 0; row < this.getTotalRows(); row++)
            for(int col = 0; col < this.getTotalColumns(); col++) {
                board[row][col] = this.getBoard()[row][col];
            }

        computerMoves = this.listCoreAIMove(move, "O", board);

        for (int row = 0; row < this.getTotalRows(); row++) {
            for (int col = 0; col < this.getTotalColumns(); col++) {
                if (this.isRightMove(row, col, "O", this.getBoard())) {
                    if (computerMoves[row][col] > computerMoves[rowMove][columnMove]) {
                        rowListMoves.clear();
                        columnListMoves.clear();
                        rowMove = row;
                        columnMove = col;
                        rowListMoves.add(rowMove);
                        columnListMoves.add(columnMove);
                    } else if (computerMoves[row][col] == computerMoves[rowMove][columnMove]) {
                        rowListMoves.add(row);
                        rowListMoves.add(col);
                    }
                }
            }
        }
        int step = new Random().nextInt(rowListMoves.size());
        rowMove = rowListMoves.get(step);
        columnMove = columnListMoves.get(step);

        if (this.isRightMove(rowMove, columnMove, "O", this.getBoard()))
            this.setBoardPiece(rowMove, columnMove, new boardPiece("O"));
    }

    public void boardFlips(ArrayList<boardPiece> board) {
        for(boardPiece piece : board) {
            piece.flip();
        }
    }

    public String getWinner(){
        int player = 0, computer = 0;
        for(Board[] rowBoard: this.getBoard()) {
            for(Board boardPiece: rowBoard) {
                if(boardPiece != null && boardPiece.toString().equals("X")){
                    player++;
                }
                if(boardPiece != null && boardPiece.toString().equals("O")) {
                    computer++;
                }
            }
        }

        return (player == computer || player >= computer || player <= computer) ? "T" :
                ((player > computer) ? "X" : "O");
    }

    public void renderBoardArray(int[][] board) {
        for(int row=0; row<this.getBoard().length; row++) {
            for(int col=0; col<this.getBoard()[row].length; col++) {
               if(board[row][col] != -10000)
                   System.out.print("\t" + board[row][col]);
               else
                   System.out.print("\t" + this.getBoard()[row][col]);
            }
        }
    }

    public int maxElement(int[][] board) {
        int max = -10000;
        for(int[] rowBoard : board) {
            for(int piece : rowBoard) {
                max = Math.max(max, piece);
            }
        }
        return max == -10000 ? 0 : max;
    }

    public String boardColorFlip (String s) {
        return s.equals("X") ? "O" : "X";
    }

    public ArrayList<ArrayList<Integer>> getTotalFlippedLocale(int row, int col, String player) {
        ArrayList<Board> boardPiece = new ArrayList<Board>();
        ArrayList<Integer> rowListMove = new ArrayList<Integer>();
        ArrayList<Integer> colListMove = new ArrayList<Integer>();
        ArrayList<Integer> tmp1 = new ArrayList<Integer>();
        ArrayList<Integer> tmp2 = new ArrayList<Integer>();
        int totalFlipped = 0;

        for(int r = col-1; r >= 0; r--) {
            if(this.getboardPiece(row,r) == null ||
                    (this.getboardPiece(row,r).toString().equals(player) &&
                        boardPiece.size() == 0))
                break;
            if(!(this.getboardPiece(row,r).toString().equals(player))) {
                boardPiece.add(this.getboardPiece(row,r));
            }
            else if(boardPiece.size() > 0  &&
                    this.getboardPiece(row,r).toString().equals(player)) {
                totalFlipped += boardPiece.size();
                for(int loopFlip=0; loopFlip < tmp1.size(); loopFlip++) {
                    rowListMove.add(tmp1.get(loopFlip));
                    colListMove.add(tmp2.get(loopFlip));
                    rowListMove.clear();
                    colListMove.clear();
                }
                break;
            }
        }
        boardPiece.clear();

        for(int r = col+1; r < this.getTotalColumns(); r++) {
            if(this.getboardPiece(r,col) == null ||
                    (this.getboardPiece(r,row).toString().equals(player) &&
                            boardPiece.size() == 0))
                break;
            if(!(this.getboardPiece(r,col).toString().equals(player))) {
                boardPiece.add(this.getboardPiece(r,col));
                tmp1.add(r);
                tmp1.add(col);
            }
            else if(boardPiece.size() > 0  &&
                    this.getboardPiece(r,col).toString().equals(player)) {
                for(int loopFlip=0; loopFlip < tmp1.size(); loopFlip++) {
                    rowListMove.add(tmp1.get(loopFlip));
                    colListMove.add(tmp2.get(loopFlip));
                    rowListMove.clear();
                    colListMove.clear();
                }
                break;
            }
        }
        boardPiece.clear();

        for(int upward = row-1; upward>=0; upward--) {
            if(this.getboardPiece(upward,col)==null ||
                    (this.getboardPiece(upward,col).toString().equals(player) &&
                        boardPiece.size() == 0))
                break;
            if (!(this.getboardPiece(upward, col).toString().equals(player))) {
                boardPiece.add(this.getboardPiece(upward, col));
                tmp1.add(upward);
                tmp2.add(col);
            } else if (boardPiece.size() > 0 &&
                    this.getboardPiece(upward, col).toString().equals(player)) {
                for(int loopFlip=0; loopFlip<tmp1.size();loopFlip++) {
                    rowListMove.add(tmp1.get(loopFlip));
                    colListMove.add(tmp2.get(loopFlip));
                    rowListMove.clear();
                    colListMove.clear();
                }
                break;
            }
        }
        boardPiece.clear();

        for(int downward = row+1; downward < this.getTotalRows(); downward++) {
            if(this.getboardPiece(downward,col)== null ||
                    (this.getboardPiece(downward,col).toString().equals(player) &&
                        boardPiece.size()==0))
                break;
            if (!(this.getboardPiece(downward, col).toString().equals(player))) {
                boardPiece.add(this.getboardPiece(downward,col));
                tmp1.add(downward);
                tmp2.add(col);
            } else if(boardPiece.size() > 0 &&
                    this.getboardPiece(downward,col).toString().equals(player)){
                for(int loopFlip = 0; loopFlip<tmp1.size(); loopFlip++) {
                    rowListMove.add(tmp1.get(loopFlip));
                    colListMove.add(tmp2.get(loopFlip));
                    rowListMove.clear();
                    colListMove.clear();
                }
                break;
            }
        }
        boardPiece.clear();

        int rc = col+1, cr = row + 1;
        while(rc < this.getTotalColumns() && cr < this.getTotalRows()) {
            if(this.getboardPiece(cr, rc) == null ||
                    (this.getboardPiece(cr,rc).toString().equals(player) &&
                        boardPiece.size() > 0))
                break;
            if(!(this.getboardPiece(cr, rc).toString().equals(player))) {
                boardPiece.add(this.getboardPiece(cr,rc));
                tmp1.add(cr);
                tmp2.add(rc);
            } else if(boardPiece.size() > 0 &&
                    this.getboardPiece(cr, rc).toString().equals(player)) {
                for(int loopFlip = 0; loopFlip<tmp1.size(); loopFlip++) {
                    rowListMove.add(tmp1.get(loopFlip));
                    colListMove.add(tmp2.get(loopFlip));
                    rowListMove.clear();
                    colListMove.clear();
                }
                break;
            }
            rc++;
            cr++;
        }
        boardPiece.clear();

        rc  = col -1;
        cr =  row + 1;
        while(rc >= 0 && cr <this.getTotalRows()) {
            if(this.getboardPiece(cr,rc) == null ||
                    (this.getboardPiece(cr, rc).toString().equals(player)
                        && boardPiece.size()>0))
                break;
            if(!(this.getboardPiece(cr, rc).toString().equals(player))) {
                boardPiece.add(this.getboardPiece(cr, rc));
                tmp1.add(cr);
                tmp2.add(rc);
            } else if(boardPiece.size() > 0 &&
                    this.getboardPiece(cr,rc).toString().equals(player)){
                for(int loopFlip=0; loopFlip<tmp1.size();loopFlip++) {
                    rowListMove.add(tmp1.get(loopFlip));
                    colListMove.add(tmp2.get(loopFlip));
                    rowListMove.clear();
                    colListMove.clear();
                }
                break;
            }
            cr ++;
            rc -- ;
        }
        boardPiece.clear();

        rc  = col +1;
        cr =  row - 1;
        while(rc < this.getTotalColumns() && cr >= 0) {
            if(this.getboardPiece(cr,rc) == null ||
                    (this.getboardPiece(cr, rc).toString().equals(player)
                            && boardPiece.size()>0))
                break;
            if(!(this.getboardPiece(cr, rc).toString().equals(player))) {
                boardPiece.add(this.getboardPiece(cr, rc));
                tmp1.add(cr);
                tmp2.add(rc);
            } else if(boardPiece.size() > 0 &&
                    this.getboardPiece(cr,rc).toString().equals(player)){
                for(int loopFlip=0; loopFlip<tmp1.size();loopFlip++) {
                    rowListMove.add(tmp1.get(loopFlip));
                    colListMove.add(tmp2.get(loopFlip));
                    rowListMove.clear();
                    colListMove.clear();
                }
                break;
            }
            cr --;
            rc ++;
        }
        boardPiece.clear();

        rc  = col -1;
        cr =  row -1;
        while(rc >= 0 && cr >= 0) {
            if(this.getboardPiece(cr,rc) == null ||
                    (this.getboardPiece(cr, rc).toString().equals(player)
                            && boardPiece.size()>0))
                break;
            if(!(this.getboardPiece(cr, rc).toString().equals(player))) {
                boardPiece.add(this.getboardPiece(cr, rc));
                tmp1.add(cr);
                tmp2.add(rc);
            } else if(boardPiece.size() > 0 &&
                    this.getboardPiece(cr,rc).toString().equals(player)){
                for(int loopFlip=0; loopFlip<tmp1.size();loopFlip++) {
                    rowListMove.add(tmp1.get(loopFlip));
                    colListMove.add(tmp2.get(loopFlip));
                    rowListMove.clear();
                    colListMove.clear();
                }
                break;
            }
            cr --;
            rc --;
        }
        boardPiece.clear();

        ArrayList<ArrayList<Integer>> flippedLocal = new ArrayList<ArrayList<Integer>>();
        flippedLocal.add(rowListMove);
        flippedLocal.add(colListMove);
        return flippedLocal;
    }


    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.computerTurn();
    }
}

