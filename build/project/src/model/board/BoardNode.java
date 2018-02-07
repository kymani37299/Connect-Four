package model.board;

import java.util.ArrayList;
import java.util.Stack;

public class BoardNode extends Board{
	
	public BoardNode(){
		super();
	}
	
	public BoardNode(Board board){
		super();
		for(int i=0;i<super.getBoardHeight();i++){
			for(int j=0;j<super.getBoardWidth();j++){
				Vec2 pos = new Vec2(i,j);
				setFieldState(pos,board.getFieldState(pos));
			}
		}
		setStateOnMove(board.getStateOnMove());
	}
	
	private void putHypotheticalMove(Vec2 move,State state){
		super.getBoard()[move.getX()][move.getY()].setState(state);
	}
	
	private void undoHypotheticalMove(Vec2 move){
		super.getBoard()[move.getX()][move.getY()].setState(State.empty);
	}
	
	private int countPossibleWins(State state){
		int count=0;
		
		for(int i=0;i<Board.getBoardWidth();i++){
			Vec2 move = this.makeMove(i);
			if(move!=null && this.getWinner()==state) count+=4;
			if(move!=null) this.undoMove();
		}
		return count;
	}
	
	private int countHypotheticalWins(State state){
		int count = 0;
		for(int i=0;i<Board.getBoardWidth();i++){
			//State lastState = null;
			for(int j=0;j<Board.getBoardHeight();j++){
				Vec2 pos = new Vec2(j,i);
				if(this.getFieldState(pos) == State.empty){
					this.putHypotheticalMove(pos,state);
					State s = this.getWinner();
					/*
					if(lastState!=null && lastState==s){
						if(s==State.p1) return 100;
						else return -100;
					}*/
					if(s == state) count++;
					this.undoHypotheticalMove(pos);
					//lastState = s;
				}
			}
		}
		return count;
	}
	
	public int evaluateBoard(){
		State winner = getWinner();
		if(winner==State.p1) return 100;
		else if(winner==State.p2) return -100;
		else
		{
			int val = 0;
			val += countHypotheticalWins(State.p1) - countHypotheticalWins(State.p2);
			//if(val==100 || val==-100) return val;
			val += countPossibleWins(State.p1) - countPossibleWins(State.p2);
			return val;
		}
	}
	
	public ArrayList<BoardNode> getChildren(){
		ArrayList<BoardNode> children = new ArrayList<>();
		for(int i=0;i<super.getBoardWidth();i++){
			BoardNode tmp = this.clone();
			Vec2 move = tmp.makeMove(i);
			if(move != null){
				children.add(tmp);
			}
		}
		return children;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public BoardNode clone(){
		BoardNode boardCopy = new BoardNode();
		for(int i=0;i<super.getBoardHeight();i++){
			for(int j=0;j<super.getBoardWidth();j++){
				Vec2 pos = new Vec2(i,j);
				boardCopy.setFieldState(pos,this.getFieldState(pos));
			}
		}
		boardCopy.setStateOnMove(this.getStateOnMove());
		boardCopy.setMoveHistory((Stack<Integer>) super.getMoveHistory().clone());
		return boardCopy;
	}
}
