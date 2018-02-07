package view.mainView;

import controller.ActionManager;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class MainMenuBar extends MenuBar{
	
	private Menu mNew;
	private MenuItem miNovaIgra;
	private MenuItem miPodesavanja;
	
	public MainMenuBar(){
		this.mNew = new Menu("Game");
		this.getMenus().add(mNew);
		this.miNovaIgra = new MenuItem("New game..");
		this.miNovaIgra.setOnAction(ActionManager.getInstance().getNovaIgraAction());
		this.miPodesavanja = new MenuItem("Preferences..");
		this.miPodesavanja.setOnAction(ActionManager.getInstance().getPreferencesAction());
		this.mNew.getItems().addAll(miNovaIgra,miPodesavanja);
	}


	
	
	
	
	
}
