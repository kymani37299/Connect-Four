package controller;

import javafx.scene.paint.Color;
import model.Game;
import model.board.Vec2;
import model.player.Bot;
import model.player.Player;
import view.Colors;
import view.mainView.BoardView;
import view.mainView.GameView;
import view.mainView.MainView;
import view.GameEndedPopup;

public class GameController {
	
	private BoardView boardView;
	private GameView gameView;
	private Game game;
	
	public GameController(MainView mv,Player p1,Player p2){
		game = new Game(p1,p2);
		gameView = mv.playGame(p1.getName(),p2.getName());
		boardView = gameView.getBoardView();
		makeMove(p1.getMove(game));
		mv.sizeToScene();
	}
	
	private Color getPlayerColor(Player p){
		if(p==game.getPlayer1()){
			return Colors.player1Color;
		}else{
			return Colors.player2Color;
		}
	}
		
	public GameView getGameView() {
		return gameView;
	}
	
	public Game getGame(){
		return game;
	}
	
	public void resetBoard(){
		game.resetBoard();
		boardView.resetBoard();
		makeMove(game.getPlayerOnTurn().getMove(game));
	}
	
	private void endGame(Player winner){
		game.getBoard().lock();
		if(winner==null){
			new GameEndedPopup(null).show();
			return;
		}
		new GameEndedPopup(winner.getName()).show();
		game.getScoreBoard().updateScore(winner);
		gameView.getScoreView().updateScore(game.getScoreBoard().getScore());
		
	}
	
	public void makeMove(int move){
		Color moveColor= getPlayerColor(game.getPlayerOnTurn());
		Vec2 valid = game.makeTurn(move);
		if(valid!=null){
			boardView.setFieldColor(valid,moveColor);
			if(this.game.gameOver()){
				endGame(game.getWinner());
			}else{
				makeMove(game.getPlayerOnTurn().getMove(game));
			}
		}
	}
	
	public void undoMove(){
		Vec2 move = game.undoTurn();
		if(move!=null){
			boardView.setFieldColor(move, Colors.emptyColor);
			if(game.getPlayerOnTurn() instanceof Bot){
				undoMove();
			}
		}else{
			this.makeMove(game.getPlayerOnTurn().getMove(game));
		}
	}
	
	
}

