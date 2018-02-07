package model.player;

import model.Game;
import model.board.State;

public class Human extends Player{
	
	
	
	public Human(String name,State state){
		super(name,state);
	}
	
	public int getMove(Game game){
		return -1;
	}

	
	
	
	
	
}
