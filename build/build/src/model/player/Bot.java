package model.player;


import java.util.ArrayList;
import java.util.Random;


import model.Game;
import model.board.BoardNode;
import model.board.State;

public class Bot extends Player{

	private int maxDepth;
	private Random rand;
	
	public Bot(String name,State state,int depth){
		super(name,state);
		this.maxDepth = depth;
		rand = new Random();
	}
	
	public int getMove(Game game){
		return miniMax(new BoardNode(game.getBoard()));
	}
	
	private int miniMax(BoardNode boardNode){
		if (super.getState()==State.p1) return max(boardNode,0,Integer.MIN_VALUE,Integer.MAX_VALUE);
		else return min(boardNode,0,Integer.MIN_VALUE,Integer.MAX_VALUE);
	}
	
	private int max(BoardNode node,int depth,int alpha,int beta){
		int eval = node.evaluateBoard();
		if(isTerminalNode(eval) || depth>=maxDepth){
			return eval;
		}
		ArrayList<BoardNode> childNodes = node.getChildren();
		int maxValue = Integer.MIN_VALUE;
		ArrayList<Integer> bestMoves = new ArrayList<>();
		for(BoardNode b : childNodes){
			int value = min(b,depth+1,alpha,beta);
			if(value > maxValue){
				maxValue = value;
				bestMoves.clear();
				bestMoves.add(b.getLastMove());
			}else if(value == maxValue){
				bestMoves.add(b.getLastMove());
			}
			if(value > alpha){
				alpha = value;
			}
			if(alpha>beta){
				break;
			}
		}
		
		if(depth==0) return bestMoves.get(rand.nextInt(bestMoves.size()));
		return maxValue;
	}
	
	private int min(BoardNode node,int depth,int alpha,int beta){
		int eval = node.evaluateBoard();
		if(isTerminalNode(eval) || depth>=maxDepth){
			return eval;
		}
		
		ArrayList<BoardNode> childNodes = node.getChildren();
		int minValue = Integer.MAX_VALUE;
		ArrayList<Integer> bestMoves = new ArrayList<>();
		for(BoardNode b : childNodes){
			int value = max(b,depth+1,alpha,beta);
			if(value < minValue){
				minValue = value;
				bestMoves.clear();
				bestMoves.add(b.getLastMove());
			}else if(value == minValue){
				bestMoves.add(b.getLastMove());
			}
			if(value<beta){
				beta = value;
			}
			if(alpha>beta){
				break;
			}
		}
		
		if(depth==0) return bestMoves.get(rand.nextInt(bestMoves.size()));
		return minValue;
	}
	
	boolean isTerminalNode(int eval){
		if(eval>=100 || eval<=-100) return true;
		else return false;
	}
}
