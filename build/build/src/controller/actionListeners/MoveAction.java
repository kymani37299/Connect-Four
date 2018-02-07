package controller.actionListeners;

import controller.ActionManager;
import controller.GameController;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import model.board.Board;

public class MoveAction implements EventHandler<MouseEvent>{


	@Override
	public void handle(MouseEvent event) {
		GameController gc = ActionManager.getInstance().getGameController();
		
		if(gc!=null && gc.getGame().gameOver()){
			gc.resetBoard();
		}
		
		if(gc!=null && event.getSource() instanceof Circle){
			Circle c = (Circle)event.getSource();
			Circle circles[][] = gc.getGameView().getBoardView().getBoard();
						
			for(int i=0;i<Board.getBoardHeight();i++){
				for(int j=0;j<Board.getBoardWidth();j++){
					if(c == circles[i][j]){
						gc.makeMove(j);
						return;
					}
				}
			}
		}
	}
}
