package controller.actionListeners;

import controller.ActionManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.board.State;
import model.player.Bot;
import model.player.Human;
import model.player.Player;
import view.mainView.MainView;
import view.settingsView.NewPlayerView;

public class NewGameAction implements EventHandler<ActionEvent>{

	@Override
	public void handle(ActionEvent event) {
		Player p1,p2;
		NewPlayerView npv = new NewPlayerView(1);
		p1 = makePlayer(npv);
		npv = new NewPlayerView(2);
		p2 = makePlayer(npv);
		
		ActionManager.getInstance().addGameController(MainView.getInstance(), p1, p2);
	}
	
	private Player makePlayer(NewPlayerView npv){
		Player p;
		State s;
		if(npv.getPlayer()==1) s = State.p1;
		else s = State.p2;
		
		if(npv.isBot()){
			p = new Bot(npv.getName(),s,npv.getDepth());
		}else{
			p = new Human(npv.getName(),s);
		}
		
		return p;
	}
	

}
