package main;

// Native GUI libraries
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.util.Date;

// Own Package
import othello.Othello;
import board.Board;

// Main GUI implements here
public class GUI_Othello extends JFrame implements ActionListener {

	// Component declared here.
	private JPanel jpanel;
	private JButton[][] jbuttons;
	private Board[][] board;
	private Othello game;
	
	private GUI_Othello() {
		this.game = new Othello();
		this.updateBoard(game.getBoard());
		this.initialize();
		this.setVisible(true);
	}

	public GUI_Othello(Board[][] board) {
		this.updateBoard(board);
		initialize();
		this.setVisible(true);
	}
	
	// second constructor
	
	private void initialize() {
		this.jpanel = new JPanel();
		this.jpanel.setLayout(new GridLayout(8,8,5,5));
		this.jpanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		this.jbuttons = new JButton[8][8];
		
		for(int row=0 ; row<this.board.length; row++){
	    	for(int col =0; col<this.board[row].length; col++) {
	    		if(this.board[row][col] != null) {
	    			this.jbuttons[row][col] = new JButton(this.board[row][col].toString());
	    		} else {
	    			this.jbuttons[row][col] = new JButton(".");
	    		}
	    		this.jpanel.add(this.jbuttons[row][col]);
	    		this.jbuttons[row][col].addActionListener(this);
	    		if(this.renderButtonBackground(jbuttons[row][col])!=null) {
		    		this.jbuttons[row][col].setBackground
		    			(this.renderButtonBackground(this.jbuttons[row][col]));
	    		}
	    		
	    	}
	    	this.jpanel.setVisible(true);
	    }
	    this.add(jpanel);
	    this.setTitle("Welcome Othello Java");
	    this.setSize(600,600);
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // close button;
	}

	public void updateBoard(Board[][] board) {
		this.board = board;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				GUI_Othello game_othello = new GUI_Othello();
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		for(int row = 0; row<jbuttons.length; row++) {
			for(int col=0; col<jbuttons.length; col++) {
				if(jbuttons[row][col] == e.getSource()) {
					if(playerRound(row,col)) {
						computerRound();
					}
				}
			}
		}
	
	}

	private void setBoard(Board[][] board) {
		this.board = board;
	}
	
	private void renderGUIBoard() {
		 int row = 0, col = 0;
		  for(;row < this.board.length;row++) {
		    	for(; col < this.board[row].length; col++) {
					if (this.board[row][col] != null) {
						this.jbuttons[row][col] = new JButton(this.board[row][col].toString());
					} else {
						this.jbuttons[row][col] = new JButton(".");
					}
					this.jpanel.add(this.jbuttons[row][col]);
					this.jbuttons[row][col].addActionListener(this);
					if (this.renderButtonBackground(jbuttons[row][col]) != null) {
						this.jbuttons[row][col].setBackground
								(this.renderButtonBackground(this.jbuttons[row][col]));
					}
				}
		    }	
	}

	/**
	 * Helper function
	 * */
	private Color renderButtonBackground(JButton button) {
		if(button.getText().equals("X")) {
			return Color.BLACK;	
		} else if (button.getText().equals("O")){
			return Color.WHITE;
		} else {
			return Color.LIGHT_GRAY;
		}
	}
	
	private boolean playerRound(int row, int col) {
		boolean tf = false;
		if(this.game.playerTurn(row, col)) {
			tf = true;
		}
		this.setBoard(this.game.getBoard());
		this.renderGUIBoard();
		if(this.game.hasGameOver()) {
			System.out.println("The game has ended");
			System.out.println("Winner is ...."); // We'll come back later
		}
		if(!this.game.playerCanMove() && ! this.game.hasGameOver()) {
			System.out.println("Sorry! It's computer's turn now");
		}
		
		return tf;
	}
	
	private void computerRound() {
		Thread thread = new Thread(this.game);
		thread.start();
		if(this.game.hasGameOver()) {
			System.out.println("The game has ended");
			System.out.println(this.game.getWinner());
		}
	}

}

 
