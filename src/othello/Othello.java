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
import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;
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
                int numBoardPieceFlipped = 0;
                for(int boardIndex = col - 1; boardIndex >= 0; boardIndex--) {
                    if(getRefboardPiece(row, boardIndex, board) == null ||
                            (this.getRefboardPiece(row,boardIndex,board).toString().equals(player)
                            && numBoardPieceFlipped == 0))
                        break;
                    if(!(this.getRefboardPiece(row,boardIndex,board).toString().equals(player)))
                        numBoardPieceFlipped++;
                    else if(numBoardPieceFlipped>0 &&
                            this.getRefboardPiece(row,boardIndex,board).toString().equals(player))
                        return true;
                }
                numBoardPieceFlipped = 0;

                for(int c = col+1; c<this.getTotalColumns(); c++) {
                    if(this.getRefboardPiece(row,c,board)==null ||
                            (this.getRefboardPiece(row,c,board).toString().equals(player)
                                && numBoardPieceFlipped == 0))
                        break;
                    if(!(this.getRefboardPiece(row,c,board).toString().equals(player)))
                        numBoardPieceFlipped++;
                    else if(numBoardPieceFlipped>0 &&
                            this.getRefboardPiece(row,c,board).toString().equals(player))
                        return true;
                }
                numBoardPieceFlipped = 0;

                for(int r = row-1; r>=0; r--) {
                    if(this.getRefboardPiece(r,col,board)==null ||
                            (this.getRefboardPiece(r,col,board).toString().equals(player)
                                    && numBoardPieceFlipped == 0))
                        break;
                    if(!(this.getRefboardPiece(r,col,board).toString().equals(player)))
                        numBoardPieceFlipped++;
                    else if(numBoardPieceFlipped>0 &&
                            this.getRefboardPiece(r,col,board).toString().equals(player))
                        return true;
                }
                numBoardPieceFlipped = 0;

                for(int r = row+1; r<this.getTotalRows(); r++) {
                    if(this.getRefboardPiece(r,col,board)==null ||
                            (this.getRefboardPiece(r,col,board).toString().equals(player)
                                    && numBoardPieceFlipped == 0))
                        break;
                    if(!(this.getRefboardPiece(row,col,board).toString().equals(player)))
                        numBoardPieceFlipped++;
                    else if(numBoardPieceFlipped>0 &&
                            this.getRefboardPiece(row,col,board).toString().equals(player))
                        return true;
                }
                numBoardPieceFlipped = 0;

                int rc = col+1;
                int cr = row+1;

                while(rc<this.getTotalColumns() && cr<this.getTotalRows()) {
                    if(this.getRefboardPiece(cr,rc,board)==null ||
                            (this.getRefboardPiece(cr,rc,board).toString().equals(player)
                                    && numBoardPieceFlipped == 0))
                        break;
                    if(!(this.getRefboardPiece(cr,rc,board).toString().equals(player)))
                        numBoardPieceFlipped++;
                    else if(numBoardPieceFlipped>0 &&
                            this.getRefboardPiece(cr,rc,board).toString().equals(player))
                        return true;
                    rc++;
                    cr++;
                }
                numBoardPieceFlipped = 0;

                rc = col-1;
                cr = row+1;
                while(rc>=0 && cr<this.getTotalRows()) {
                    if(this.getRefboardPiece(cr,rc,board)==null ||
                            (this.getRefboardPiece(cr,rc,board).toString().equals(player)
                                    && numBoardPieceFlipped == 0))
                        break;
                    if(!(this.getRefboardPiece(cr,rc,board).toString().equals(player)))
                        numBoardPieceFlipped++;
                    else if(numBoardPieceFlipped>0 &&
                            this.getRefboardPiece(cr,rc,board).toString().equals(player))
                        return true;
                    rc--;
                    cr++;
                }
                numBoardPieceFlipped = 0;

                rc = col+1;
                cr = row-1;
                while(rc<this.getTotalColumns() && cr>=0) {
                    if(this.getRefboardPiece(cr,rc,board)==null ||
                            (this.getRefboardPiece(cr,rc,board).toString().equals(player)
                                    && numBoardPieceFlipped == 0))
                        break;
                    if(!(this.getRefboardPiece(cr,rc,board).toString().equals(player)))
                        numBoardPieceFlipped++;
                    else if(numBoardPieceFlipped>0 &&
                            this.getRefboardPiece(cr,rc,board).toString().equals(player))
                        return true;
                    rc++;
                    cr--;
                }
                numBoardPieceFlipped = 0;

                rc = col-1;
                rc = row-1;
                while(rc>=0 && cr>=0) {
                    if(this.getRefboardPiece(cr,rc,board)==null ||
                            (this.getRefboardPiece(cr,rc,board).toString().equals(player)
                                    && numBoardPieceFlipped == 0))
                        break;
                    if(!(this.getRefboardPiece(cr,rc,board).toString().equals(player)))
                        numBoardPieceFlipped++;
                    else if(numBoardPieceFlipped>0 &&
                            this.getRefboardPiece(cr,rc,board).toString().equals(player))
                        return true;
                    rc--;
                    cr--;
                }
                numBoardPieceFlipped = 0;
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

    public void updateBoard(int row, int col, String player) {
        ArrayList<Board> boardPieces = new ArrayList<Board>();

        for(int c = col-1; c >= 0; c--) {
            if(this.getboardPiece(row,c)==null ||
                    (this.getboardPiece(row,c).toString().equals(player)
                        && boardPieces.size()==0))
                break;
            if(!(this.getboardPiece(row,c).toString().equals(player)))
                boardPieces.add(this.getboardPiece(row,c));
            else if(boardPieces.size() > 0
                    && this.getboardPiece(row,c).toString().equals(player)) {
                boardFlips(boardPieces);
                break;
            }
        }
        boardPieces.clear();

        for(int c = col+1; c<this.getTotalColumns(); c++) {
            if(this.getboardPiece(row,c)== null ||
                    (this.getboardPiece(row,c).toString().equals(player)
                        && boardPieces.size()==0))
                break;
            if(!(this.getboardPiece(row,c)).toString().equals(player))
                boardPieces.add(this.getboardPiece(row,c));
            else if(boardPieces.size() > 0
                    && this.getboardPiece(row,c).toString().equals(player)) {
                boardFlips(boardPieces);
                break;
            }
        }
        boardPieces.clear();

        for(int c=row-1; c>=0; c--) {
            if(this.getboardPiece(c,col) == null ||
                    (this.getboardPiece(c,col).toString().equals(player)
                        && boardPieces.size()==0))
                break;
            if(!(this.getboardPiece(c,col).toString().equals(player)))
                boardPieces.add(this.getboardPiece(c,col));
            else if(boardPieces.size()>0
                    && this.getboardPiece(c,col).toString().equals(player)){
                boardFlips(boardPieces);
                break;
            }
        }
        boardPieces.clear();

        for(int r = row+1; r<this.getTotalRows(); r++) {
            if(this.getboardPiece(r,col)== null ||
                    (this.getboardPiece(r,col).toString().equals(player)
                        && boardPieces.size()==0))
                break;
            if(!(this.getboardPiece(r,col).toString().equals(player)))
                boardPieces.add(this.getboardPiece(r,col));
            else if(boardPieces.size()>0
                    && this.getboardPiece(r,col).toString().equals(player)) {
                boardFlips(boardPieces);
                break;
            }
        }
        boardPieces.clear();

        int rc = col +1;
        int cr = row + 1;
        while(rc<this.getTotalColumns() && cr < this.getTotalRows()) {
            if(this.getboardPiece(cr,rc)== null ||
                    (this.getboardPiece(cr,rc).toString().equals(player)
                            && boardPieces.size()==0))
                break;
            if(!(this.getboardPiece(cr,rc).toString().equals(player)))
                boardPieces.add(this.getboardPiece(cr,rc));
            else if(boardPieces.size()>0
                    && this.getboardPiece(cr,rc).toString().equals(player)) {
                boardFlips(boardPieces);
                break;
            }
            rc++;
            cr++;
        }
        boardPieces.clear();

        rc = col-1;
        cr = row+1;
        while(rc >=0 && cr <this.getTotalRows()) {
            if(this.getboardPiece(cr,rc)== null ||
                    (this.getboardPiece(cr,rc).toString().equals(player)
                            && boardPieces.size()==0))
                break;
            if(!(this.getboardPiece(cr,rc).toString().equals(player)))
                boardPieces.add(this.getboardPiece(cr,rc));
            else if(boardPieces.size()>0
                    && this.getboardPiece(cr,rc).toString().equals(player)) {
                boardFlips(boardPieces);
                break;
            }
            rc++;
            cr++;
        }
        boardPieces.clear();

        rc = col-1;
        cr = row+1;
        while(rc>=0 && cr<this.getTotalRows()) {
            if(this.getboardPiece(cr,rc)== null ||
                    (this.getboardPiece(cr,rc).toString().equals(player)
                            && boardPieces.size()==0))
                break;
            if(!(this.getboardPiece(cr,rc).toString().equals(player)))
                boardPieces.add(this.getboardPiece(cr,rc));
            else if(boardPieces.size()>0
                    && this.getboardPiece(cr,rc).toString().equals(player)) {
                boardFlips(boardPieces);
                break;
            }
            rc--;
            cr++;
        }
        boardPieces.clear();

        rc = col+1;
        cr = row-1;
        while(rc<this.getTotalColumns() && cr >=0) {
            if(this.getboardPiece(cr,rc)== null ||
                    (this.getboardPiece(cr,rc).toString().equals(player)
                            && boardPieces.size()==0))
                break;
            if(!(this.getboardPiece(cr,rc).toString().equals(player)))
                boardPieces.add(this.getboardPiece(cr,rc));
            else if(boardPieces.size()>0
                    && this.getboardPiece(cr,rc).toString().equals(player)) {
                boardFlips(boardPieces);
                break;
            }
            rc++;
            cr--;
        }
        boardPieces.clear();

        rc = col-1;
        cr = row-1;
        while(rc>=0 && cr<=0) {
            if(this.getboardPiece(cr,rc)== null ||
                    (this.getboardPiece(cr,rc).toString().equals(player)
                            && boardPieces.size()==0))
                break;
            if(!(this.getboardPiece(cr,rc).toString().equals(player)))
                boardPieces.add(this.getboardPiece(cr,rc));
            else if(boardPieces.size()>0
                    && this.getboardPiece(cr,rc).toString().equals(player)) {
                boardFlips(boardPieces);
                break;
            }
            rc--;
            cr--;
        }
        boardPieces.clear();
    }

    public void computerTurn() {
        System.out.println("-------------");
        System.out.println("Computer's turn");
        this.coreAIMove(3);
        this.renderBoard();
    }

    // Not finished
    public int[][] listCoreAIMove(int move, String player, Board[][] board) {
        int[][] listPossibleMoves = new int[8][8];
        for(int row = 0; row<this.getTotalRows(); row++) {
            for(int col=0; col<this.getTotalColumns(); col++) {
                if(move<=0) {
                    if(isRightMove(row, col, player, board)){
                        listPossibleMoves[row][col] = this.boardValues[row][col];
                        ArrayList<Integer> rowListPossibleMove;
                        ArrayList<Integer> colListPossibleMove;
                        ArrayList<ArrayList<Integer>> listPossileMovesArray = getTotalFlippedLocale(row, col, player);

                        for(int index = 0; index<listPossileMovesArray.get(0).size(); index++) {
                            listPossibleMoves[row][col] += 2 * (this.boardValues[listPossileMovesArray.get(0).get(index)]
                                    [listPossileMovesArray.get(1).get(index)]);
                        }
                    } else {
                        listPossibleMoves[row][col] = -10000;
                    }
                } else{
                    if(isRightMove(row, col, player, board)) {
                        listPossibleMoves[row][col] = this.boardValues[row][col];
                        ArrayList<Integer> rowListPossibleMove;
                        ArrayList<Integer> colListPossibleMove;
                        ArrayList<ArrayList<Integer>> listPossileMovesArray = getTotalFlippedLocale(row, col, player);

                        for(int index = 0; index<listPossileMovesArray.get(0).size(); index++) {
                            listPossibleMoves[row][col] += 2 * (this.boardValues[listPossileMovesArray.get(0).get(index)]
                                    [listPossileMovesArray.get(1).get(index)]);
                        }
                        Board[][]boardPieces = new Board[this.getTotalColumns()][this.getTotalRows()];
                        for(int r=0; r<this.getTotalRows(); r++) {
                            for(int c=0; c<this.getTotalColumns(); c++) {
                                if(board[r][c] !=null &&
                                        board[r][c].toString().equals("O"))
                                    setRefBoardPiece(row,col,new boardPiece("O"),boardPieces);
                                else if (board[r][c]!=null &&
                                        board[r][c].toString().equals("X"))
                                    setRefBoardPiece(row,col,new boardPiece("X"),boardPieces);
                            }
                            setRefBoardPiece(row,col, new boardPiece(player), board);
                            //updateBoard(row,col, player,boardPieces);
                            listPossibleMoves[row][col] -= maxElement(listCoreAIMove(move-1,boardColorFlip(player),boardPieces));
                        }
                    } else{
                        listPossibleMoves[row][col] = -10000;
                    }
                }
            }
        }
        return listPossibleMoves;
    }


    public void coreAIMove(int move) {
        int rowMove = 0, columnMove = 0;
        ArrayList<Integer> rowListMoves = new ArrayList<Integer>(rowMove);
        ArrayList<Integer> columnListMoves = new ArrayList<Integer>(columnMove);

        int[][] computerMoves;
        Board[][] board = new Board[this.getTotalRows()][this.getTotalColumns()];
        for (int row = 0; row < this.getTotalRows(); row++) {
            for (int col = 0; col < this.getTotalColumns(); col++) {
                board[row][col] = this.getBoard()[row][col];
            }
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

    public void boardFlips(ArrayList<Board> board) {
        for(Board piece : board) {
            boardPiece _p = (boardPiece)piece;
            _p.flip();
        }
    }

    public String getWinner(){
        int player = 0, computer = 0;
        for(Board[] rowBoard: this.getBoard()) {
            for(Board boardPieces: rowBoard) {
                if(boardPieces != null && boardPieces.toString().equals("X")){
                    player++;
                }
                if(boardPieces != null && boardPieces.toString().equals("O")) {
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
        ArrayList<Board> boardPieces = new ArrayList<Board>();
        ArrayList<Integer> rowListMove = new ArrayList<Integer>();
        ArrayList<Integer> colListMove = new ArrayList<Integer>();
        ArrayList<Integer> tmp1 = new ArrayList<Integer>();
        ArrayList<Integer> tmp2 = new ArrayList<Integer>();
        int totalFlipped = 0;

        for(int r = col-1; r >= 0; r--) {
            if(this.getboardPiece(row,r) == null ||
                    (this.getboardPiece(row,r).toString().equals(player) &&
                        boardPieces.size() == 0))
                break;
            if(!(this.getboardPiece(row,r).toString().equals(player))) {
                boardPieces.add(this.getboardPiece(row,r));
            }
            else if(boardPieces.size() > 0  &&
                    this.getboardPiece(row,r).toString().equals(player)) {
                totalFlipped += boardPieces.size();
                for(int loopFlip=0; loopFlip < tmp1.size(); loopFlip++) {
                    rowListMove.add(tmp1.get(loopFlip));
                    colListMove.add(tmp2.get(loopFlip));
                    rowListMove.clear();
                    colListMove.clear();
                }
                break;
            }
        }
        boardPieces.clear();

        for(int r = col+1; r < this.getTotalColumns(); r++) {
            if(this.getboardPiece(r,col) == null ||
                    (this.getboardPiece(r,row).toString().equals(player) &&
                            boardPieces.size() == 0))
                break;
            if(!(this.getboardPiece(r,col).toString().equals(player))) {
                boardPieces.add(this.getboardPiece(r,col));
                tmp1.add(r);
                tmp1.add(col);
            }
            else if(boardPieces.size() > 0  &&
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
        boardPieces.clear();

        for(int upward = row-1; upward>=0; upward--) {
            if(this.getboardPiece(upward,col)==null ||
                    (this.getboardPiece(upward,col).toString().equals(player) &&
                        boardPieces.size() == 0))
                break;
            if (!(this.getboardPiece(upward, col).toString().equals(player))) {
                boardPieces.add(this.getboardPiece(upward, col));
                tmp1.add(upward);
                tmp2.add(col);
            } else if (boardPieces.size() > 0 &&
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
        boardPieces.clear();

        for(int downward = row+1; downward < this.getTotalRows(); downward++) {
            if(this.getboardPiece(downward,col)== null ||
                    (this.getboardPiece(downward,col).toString().equals(player) &&
                        boardPieces.size()==0))
                break;
            if (!(this.getboardPiece(downward, col).toString().equals(player))) {
                boardPieces.add(this.getboardPiece(downward,col));
                tmp1.add(downward);
                tmp2.add(col);
            } else if(boardPieces.size() > 0 &&
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
        boardPieces.clear();

        int rc = col+1, cr = row + 1;
        while(rc < this.getTotalColumns() && cr < this.getTotalRows()) {
            if(this.getboardPiece(cr, rc) == null ||
                    (this.getboardPiece(cr,rc).toString().equals(player) &&
                        boardPieces.size() > 0))
                break;
            if(!(this.getboardPiece(cr, rc).toString().equals(player))) {
                boardPieces.add(this.getboardPiece(cr,rc));
                tmp1.add(cr);
                tmp2.add(rc);
            } else if(boardPieces.size() > 0 &&
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
        boardPieces.clear();

        rc  = col -1;
        cr =  row + 1;
        while(rc >= 0 && cr <this.getTotalRows()) {
            if(this.getboardPiece(cr,rc) == null ||
                    (this.getboardPiece(cr, rc).toString().equals(player)
                        && boardPieces.size()>0))
                break;
            if(!(this.getboardPiece(cr, rc).toString().equals(player))) {
                boardPieces.add(this.getboardPiece(cr, rc));
                tmp1.add(cr);
                tmp2.add(rc);
            } else if(boardPieces.size() > 0 &&
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
        boardPieces.clear();

        rc  = col +1;
        cr =  row - 1;
        while(rc < this.getTotalColumns() && cr >= 0) {
            if(this.getboardPiece(cr,rc) == null ||
                    (this.getboardPiece(cr, rc).toString().equals(player)
                            && boardPieces.size()>0))
                break;
            if(!(this.getboardPiece(cr, rc).toString().equals(player))) {
                boardPieces.add(this.getboardPiece(cr, rc));
                tmp1.add(cr);
                tmp2.add(rc);
            } else if(boardPieces.size() > 0 &&
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
        boardPieces.clear();

        rc  = col -1;
        cr =  row -1;
        while(rc >= 0 && cr >= 0) {
            if(this.getboardPiece(cr,rc) == null ||
                    (this.getboardPiece(cr, rc).toString().equals(player)
                            && boardPieces.size()>0))
                break;
            if(!(this.getboardPiece(cr, rc).toString().equals(player))) {
                boardPieces.add(this.getboardPiece(cr, rc));
                tmp1.add(cr);
                tmp2.add(rc);
            } else if(boardPieces.size() > 0 &&
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
        boardPieces.clear();

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

