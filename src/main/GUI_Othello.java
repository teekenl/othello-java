package main;

// Native GUI libraries
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


// Own Package
import othello.Othello;
import board.*;


// Main GUI implements here
public class GUI_Othello extends JFrame implements ActionListener {

	// Component declared here.
	private JPanel jpanel;
	private JButton[][] jbuttons;
	private String[][] board = new String[1][1];
	
	public GUI_Othello() {
		// game = new othello();
		//this.setBoard(game.getBoard());
		initializeGUI();
		this.setVisible(true);
	}
	
	// second constructor
	
	public void initializeGUI() {
		this.jpanel = new JPanel();
		this.jpanel.setLayout(new GridLayout(8,8,5,5));
		this.jpanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		this.jbuttons = new JButton[8][8];
		
		
		int i = 0,j = 0; // initialize the row and column to be zero in the board
	
	    while(i++ < this.board.length) {
	    	while(j++ < this.board[i].length) {
	    		if(this.board[i][j] != null) {
	    			this.jbuttons[i][j] = new JButton(
	    					this.board[i][j].toString());
	    		} else {
	    			this.jbuttons[i][j] = new JButton(".");
	    		}
	    		this.jpanel.add(this.jbuttons[i][j]);
	    		this.jbuttons[i][j].addActionListener(this);
	    		if(this.renderButtonBackground(jbuttons[i][j])!=null) {
		    		this.jbuttons[i][j].setBackground
		    			(this.renderButtonBackground(this.jbuttons[i][j]));	
	    		}
	    		
	    	}
	    	j = 0; // reset count to the col to zero
	    	this.jpanel.setVisible(true);
	    }
	    this.add(jpanel);
	    this.setTitle("Welcome Othello Java");
	    this.setSize(600,600);
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // close button;  
	}
	/*
	public void setBoard(Piece[][] board) {
		this.board = board;
	}*/
	
	
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

	public void setBoard() {
		this.board = board;
	}
	
	public void renderGUIBoard() {
		 int i = 0, j = 0;
		  while(i++ < this.board.length) {
		    	while(j++ < this.board[i].length) {
		    		if(this.board[i][j] != null) {
		    			this.jbuttons[i][j] = new JButton(
		    					this.board[i][j].toString());
		    		} else {
		    			this.jbuttons[i][j] = new JButton(".");
		    		}
		    		this.jpanel.add(this.jbuttons[i][j]);
		    		this.jbuttons[i][j].addActionListener(this);
		    		if(this.renderButtonBackground(jbuttons[i][j])!=null) {
			    		this.jbuttons[i][j].setBackground
			    			(this.renderButtonBackground(this.jbuttons[i][j]));	
		    		}
		    		
		    	}
		    	j = 0; // reset count to the col to zero
		    
		    }	
	}
	
	/**
	 * Helper function
	 * */
	public Color renderButtonBackground(JButton button) {
		if(button.getText() ==  "X") {
			return Color.BLACK;	
		} else if (button.getText() == "O"){
			return Color.WHITE;
		} else {
			return Color.LIGHT_GRAY;
		}
	}
	
	public boolean playerRound(int row, int col) {
		boolean tf = false;
		if(this.game.playerTurn(row, col)) {
			tf = true;
		}
		this.setBoard(game.getBoard());
		this.renderGUIBoard();
		if(this.game.hasGameOver()) {
			System.out.println("The game has ended");
			System.out.println("Winner is ...."); // We'll come back later
		}
		if(!this.game.playerCanMove() && ! thsi.game.hasGameOver()) {
			System.out.println("Sorry! It's computer's turn now");
		}
		
		return tf;
	}
	
	public void computerRound() {
		Thread thread = new Thread() {
			public void run() {
				try {
					Thread.sleep(2000);
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
				this.game.computerTurn();
			 
			}
		};
		thread.start();
		if(this.game.hasGameOver()) {
			System.out.println("The game has ended");
			System.out.println(this.game.getWinner());
		}
	}

}

 
