package view.mainView;


import controller.ActionManager;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainView extends Stage{
	private static MainView instance;
	
	private BorderPane layout;
	private MainMenuBar mainMenuBar;
	private GameView gameView;
	
	private MainView(){
		layout = new BorderPane();
		mainMenuBar = new MainMenuBar();
		layout.setTop(mainMenuBar);
		
		ActionManager.getInstance().addGameController(this);
		
		Scene sc = new Scene(layout);
		this.setMinHeight(500);
		this.setMinWidth(500);
		this.setScene(sc);
		this.setTitle("Connect 4");
		this.setResizable(false);
	}
	
	
	public static MainView getInstance(){
		if(instance==null){
			instance = new MainView();
		}
		return instance;
	}
	
	public MainMenuBar getMainMenuBar(){
		return mainMenuBar;
	}
	
	public GameView getGameView(){
		return gameView;
	}
	
	public GameView playGame(String name1,String name2){
		this.gameView = new GameView(name1,name2);
		layout.setCenter(this.gameView);
		return this.gameView;
	}
	
	
	
	
	
	
	
	
	

}
