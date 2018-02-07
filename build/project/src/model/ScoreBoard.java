package model;

import model.board.Vec2;
import model.player.Player;

public class ScoreBoard {
	private Player player1;
	private Player player2;
	private int scoreP1;
	private int scoreP2;
	
	
	public ScoreBoard(Player p1,Player p2){
		this.player1 = p1;
		this.player2 = p2;
		scoreP1 = 0;
		scoreP2 = 0;
	}
	
	public Vec2 getScore(){
		return new Vec2(scoreP1,scoreP2);
	}

	public void updateScore(Player winner){
		if(winner==player1){
			scoreP1++;
		}else if(winner==player2){
			scoreP2++;
		}
	}
	
}
