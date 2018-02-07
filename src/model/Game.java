package model;

import model.board.Board;
import model.board.State;
import model.board.Vec2;
import model.player.Human;
import model.player.Player;

public class Game {
	
	private Board board;
	private Player player1;
	private Player player2;
	private Player playerOnTurn;
	private Player winner;
	private boolean gameOver;
	private ScoreBoard scoreBoard;
	
	public Game(Player player1,Player player2){
		this.board = new Board();
		
		this.player1 = player1;
		this.player2 = player2;
		scoreBoard = new ScoreBoard(this.player1, this.player2);
		this.playerOnTurn = this.player1;
		this.winner = null;
		this.gameOver = false;
	}
	
	public boolean gameOver(){
		return this.gameOver;
	}
	
	public Board getBoard(){
		return board;
	}
	
	public void resetBoard(){
		this.board = new Board();
		this.playerOnTurn = this.player1;
		this.winner = null;
		this.gameOver = false;
	}
	
	private Player getPlayerByState(State state){
		if(state==State.p1){
			return player1;
		}else if(state==State.p2){
			return player2;
		}else{
			return null;
		}
	}
	
	private void switchPlayer(){
		if(this.playerOnTurn==player1){
			this.playerOnTurn = player2;
		}else{
			this.playerOnTurn = player1;
		}
	}
	
	public Player getWinner(){
		return winner;
	}

	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(Human player1) {
		this.player1 = player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Human player2) {
		this.player2 = player2;
	}

	public Player getPlayerOnTurn() {
		return playerOnTurn;
	}
	
	public ScoreBoard getScoreBoard(){
		return scoreBoard;
	}
	
	public Vec2 makeTurn(int move){
		Vec2 validanPotez = board.makeMove(move);
		if(validanPotez==null){
			return null;
		}
		switchPlayer();
		
		if(board.gameEnded()){
			this.gameOver = true;
			State winner = board.getWinner();
			if(winner!=null){
				this.winner = getPlayerByState(winner);
			}
		}
		
		return validanPotez;
	}
	
	public Vec2 undoTurn(){
		Vec2 turn = board.undoMove();
		if(turn==null){
			return null;
		}
		switchPlayer();
		return turn;
	}
	
}
