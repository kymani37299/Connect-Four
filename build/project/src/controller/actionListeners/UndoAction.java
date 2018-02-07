package controller.actionListeners;

import controller.ActionManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class UndoAction implements EventHandler<ActionEvent>{

	@Override
	public void handle(ActionEvent event) {
		ActionManager.getInstance().getGameController().undoMove();	
	}

}
