package view.settingsView;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import view.Colors;
import view.mainView.MainView;

public class PreferencesView extends Stage{
	
	private GridPane layout;
	
	private ColorPicker cpP1;
	private ColorPicker cpP2;
	private ColorPicker cpEmpty;
	
	private ColorPicker cpGameBG;
	private ColorPicker cpScoreBG;
	
	private Button btnMoreOptions;
	boolean moreOptions = false;
	
	public PreferencesView(){
		layout = new GridPane();
		layout.setHgap(15);
		layout.setVgap(5);
		layout.setPadding(new Insets(15,15,5,15));
		
		Button btnClose;
		btnClose = new Button("Sacuvaj");
		btnClose.setOnAction(e -> {
			Colors.player1Color = cpP1.getValue();
			Colors.player2Color = cpP2.getValue();
			if(moreOptions){
				Colors.emptyColor = cpEmpty.getValue();
				Colors.gameBackgroundColor = cpGameBG.getValue();
				Colors.scoreBackgroundColor = cpScoreBG.getValue();
			}
			MainView.getInstance().getGameView().updateColors();
			this.close();
		});
		
		btnMoreOptions = new Button("Jos opcija..");
		btnMoreOptions.setOnAction(e ->{
			moreOptions();
		});
		
		cpP1 = new ColorPicker();
		cpP1.setValue(Colors.player1Color);
		layout.add(new Label("Boja prvog igraca: "), 0, 0);
		layout.add(cpP1, 1, 0);
		
		
		cpP2 = new ColorPicker();
		cpP2.setValue(Colors.player2Color);
		layout.add(new Label("Boja drugog igraca: "), 0, 1);
		layout.add(cpP2, 1, 1);
		
		
		
		layout.add(btnClose, 1, 6);
		layout.add(btnMoreOptions, 0, 6);
		
		Scene sc  = new Scene(layout);
		this.setScene(sc);
		this.setTitle("Podesavanja boja");
		this.setResizable(false);
		this.showAndWait();
	}
	
	public void moreOptions(){
		moreOptions = true;
		cpEmpty = new ColorPicker();
		cpEmpty.setValue(Colors.emptyColor);
		layout.add(new Label("Boja praznog polja: "), 0, 3);
		layout.add(cpEmpty, 1, 3);
		
		cpGameBG = new ColorPicker();
		cpGameBG.setValue(Colors.gameBackgroundColor);
		layout.add(new Label("Boja pozadine: "), 0, 4);
		layout.add(cpGameBG, 1, 4);
		
		cpScoreBG = new ColorPicker();
		cpScoreBG.setValue(Colors.scoreBackgroundColor);
		layout.add(new Label("Boja pozadine rezultata: "), 0, 5);
		layout.add(cpScoreBG, 1, 5);
		layout.getChildren().remove(btnMoreOptions);
		this.sizeToScene();
	}

}
