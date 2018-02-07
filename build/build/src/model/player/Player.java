package model.player;

import model.Game;
import model.board.State;

public abstract class Player {

	private String name;
	private State state;
	
	
	public Player(String name,State state){
		this.name = name;
		this.state = state;
	}
	
	
	public State getState(){
		return this.state;
	}

	public String getName() {
		return name;
	}
	
	public abstract int getMove(Game game);
	
}
