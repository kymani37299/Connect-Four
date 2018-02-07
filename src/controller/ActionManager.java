package controller;

import controller.actionListeners.MoveAction;
import controller.actionListeners.NewGameAction;
import controller.actionListeners.PreferencesAction;
import controller.actionListeners.UndoAction;
import model.board.State;
import model.player.Bot;
import model.player.Human;
import model.player.Player;
import view.mainView.MainView;

public class ActionManager {
	private static  ActionManager instance; 
	
	private MoveAction moveAction;
	private GameController gameController;
	private NewGameAction newGameAction;
	private UndoAction undoAction;
	private PreferencesAction preferencesAction;
	
	private ActionManager(){
		moveAction = new MoveAction();
		newGameAction = new NewGameAction();
		undoAction = new UndoAction();
		preferencesAction = new PreferencesAction();
	}
	
	public static ActionManager getInstance(){
		if(instance==null){
			instance = new ActionManager();
		}
		return instance;
	}
	
	public void addGameController(MainView mv,Player p1,Player p2){
		gameController = new GameController(mv,p1,p2);
	}
	
	public void addGameController(MainView mv){
		gameController = new GameController(mv,new Human("Player 1",State.p1),new Bot("Player 2",State.p2,5));
	}
	
	public MoveAction getMoveAction() {
		return moveAction;
	}

	public GameController getGameController() {
		return gameController;
	}

	public NewGameAction getNovaIgraAction() {
		return newGameAction;
	}
	
	public UndoAction getUndoAction(){
		return undoAction;
	}
	
	public PreferencesAction getPreferencesAction(){
		return preferencesAction;
	}
	
	
	
	
	
	
	
}
