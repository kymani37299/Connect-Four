package controller.actionListeners;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.settingsView.PreferencesView;

public class PreferencesAction implements EventHandler<ActionEvent>{

	@Override
	public void handle(ActionEvent event) {
		new PreferencesView();
	}

}
