package model.board;

public class Field {

	private State state;
	
	public Field(){
		state = State.empty;
	}
	
	public State getState(){
		return state;
	}
	
	public void setState(State state){
		this.state = state;
	}
	

}
