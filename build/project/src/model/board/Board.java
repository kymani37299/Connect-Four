package model.board;

import java.util.Stack;

public class Board {

	private static final int BOARD_WIDTH=7;
	private static final int BOARD_HEIGHT=6;	
	
	private Field board[][];
	private boolean locked;
	private Stack<Integer> moveHistory;
	private State stateOnMove;
	
	public Board(){
		board = new Field[BOARD_HEIGHT][BOARD_WIDTH];
		locked = false;
		for(int i=0;i<BOARD_HEIGHT;i++){
			for(int j=0;j<BOARD_WIDTH;j++){
				this.board[i][j] = new Field();
			}
		}
		stateOnMove = State.p1;
		moveHistory = new Stack<>();
	}
	
	public static int getBoardWidth() {
		return BOARD_WIDTH;
	}

	public static int getBoardHeight() {
		return BOARD_HEIGHT;
	}
	
	public State getFieldState(Vec2 pos){
		if(pos.getX() >= 0 && pos.getX() < BOARD_HEIGHT && pos.getY() >= 0 && pos.getY() < BOARD_WIDTH )
			return this.board[pos.getX()][pos.getY()].getState();
		return null;
	}
	
	public void setFieldState(Vec2 pos,State state){
		this.board[pos.getX()][pos.getY()].setState(state);
	}
	
	public boolean locked(){
		return locked;
	}
	
	public State getStateOnMove(){
		return stateOnMove;
	}
	
	public void setStateOnMove(State val){
		stateOnMove = val;
	}
	
	public int getLastMove(){
		if(!moveHistory.isEmpty()) return moveHistory.peek();
		else return -1;
	}
	
	private boolean fullColumn(int col){
		if(getFieldState(new Vec2(0,col))==State.empty){
			return false;
		}
		return true;
	}
	
	private boolean checkDraw(){
		for(int i=0;i<BOARD_WIDTH;i++){
			if(!fullColumn(i)){
				return false;
			}
		}
		return true;
	}
	
	private boolean checkWinner(Field f1,Field f2,Field f3,Field f4){
		
		if(f1.getState()!=State.empty && f1.getState()==f2.getState() && f2.getState()==f3.getState() && f3.getState()==f4.getState()){
			return true;
		}
		
		return false;
	}
	
	private void switchState(){
		if(stateOnMove==State.p1) stateOnMove = State.p2;
		else stateOnMove = State.p1;
	}
	
	protected Field[][] getBoard(){
		return board;
	}
	
	protected void setMoveHistory(Stack<Integer> moveHistory){
		this.moveHistory = moveHistory;
	}
	
	protected Stack<Integer> getMoveHistory(){
		return moveHistory;
	}
	
	public void lock(){
		locked = true;
	}
	
	public Vec2 makeMove(int move){
		if(!locked && !fullColumn(move) && move>=0 && move<BOARD_WIDTH){
			int i=BOARD_HEIGHT-1;
			for(;!(getFieldState(new Vec2(i,move))==State.empty);i--);
			board[i][move].setState(stateOnMove);
			moveHistory.push(move);
			switchState();
			return new Vec2(i,move);
		}else{
			return null;
		}
	}
	
	public Vec2 undoMove(){
		if(!locked && !moveHistory.isEmpty() &&board[BOARD_HEIGHT-1][moveHistory.peek()].getState()!=State.empty && moveHistory.peek()>=0 && moveHistory.peek()<BOARD_WIDTH){
			int i=0;
			int move = moveHistory.pop();
			for(;getFieldState(new Vec2(i,move))==State.empty;i++);
			board[i][move].setState(State.empty);
			if(stateOnMove==State.p1) stateOnMove = State.p2;
			else stateOnMove = State.p1;
			return new Vec2(i,move);
		}else{
			return null;
		}
	}
	
	public boolean gameEnded(){
		return (checkDraw() || getWinner()!=null);
	}
	
	public State getWinner(){
		if(checkDraw()) return State.empty;
		else{
			//HORIZONTAL
			for(int i=0;i<Board.getBoardHeight();i++){
				for(int j=0;j<=Board.getBoardWidth()-4;j++){
					if(checkWinner(board[i][j],board[i][j+1],board[i][j+2],board[i][j+3])){
						return board[i][j].getState();
					}
				}
			}
			
			//VERTICAL
			for(int i=0;i<=Board.getBoardHeight()-4;i++){
				for(int j=0;j<Board.getBoardWidth();j++){
					if(checkWinner(board[i][j],board[i+1][j],board[i+2][j],board[i+3][j])){
						return board[i][j].getState();
					}
				}
			}
			
			//DIAGONAL UPWARDS
			for(int i=3;i<Board.getBoardHeight();i++){
				for(int j=0;j<=Board.getBoardWidth()-4;j++){
					if(checkWinner(board[i][j],board[i-1][j+1],board[i-2][j+2],board[i-3][j+3])){
						return board[i][j].getState();
					}
				}
			}
			
			//DIAGONAL DOWNWARDS
			for(int i=0;i<=Board.getBoardHeight()-4;i++){
				for(int j=0;j<=Board.getBoardWidth()-4;j++){
					if(checkWinner(board[i][j],board[i+1][j+1],board[i+2][j+2],board[i+3][j+3])){
						return board[i][j].getState();
					}
				}
			}
			return null;
		}
	}
	
	@Override
	public String toString() {
		String str = "";
		for(int i=0;i<BOARD_HEIGHT;i++){
			for(int j=0;j<BOARD_WIDTH;j++){
				if(board[i][j].getState() == State.empty) str += ".";
				else if(board[i][j].getState() == State.p1) str += "+";
				else str += "o";
			}
			str += "\n";
		}
		return str;
	}	
}
