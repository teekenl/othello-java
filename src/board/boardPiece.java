package board;

public class boardPiece extends Board {

    // Constructor
    public boardPiece(String color) {
        super(color);
    }
    public void flip(){
        this.boardName = this.boardName.equals("O") ? "X" : "O";
    }
}
