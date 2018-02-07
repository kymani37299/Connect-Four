package view.settingsView;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ChooseDifficultyView{
	
	private int depth;
	
	public ChooseDifficultyView(){
		makeView();
	}
	
	public void makeView(){
		Stage stage = new Stage();
		VBox layout = new VBox(15);
		layout.setPadding(new Insets(15,50,0,50));
		
		RadioButton rbEasy = new RadioButton("Easy");
		RadioButton rbNormal = new RadioButton("Normal");
		RadioButton rbHard = new RadioButton("Hard");
		RadioButton rbExpert = new RadioButton("Expert");
		ToggleGroup tgDifficulty = new ToggleGroup();
		tgDifficulty.getToggles().addAll(rbEasy,rbNormal,rbHard,rbExpert);
		tgDifficulty.selectToggle(rbHard);
		
		Button btnOk = new Button("Accept");
		btnOk.setOnAction(e -> {
			RadioButton tg = (RadioButton) tgDifficulty.getSelectedToggle();
			if(tg==rbEasy) depth = 1;
			else if(tg==rbNormal) depth = 3;
			else if(tg==rbHard) depth = 5;
			else if(tg==rbExpert) depth = 6;
			stage.close();
		});
		
		layout.getChildren().addAll(rbEasy,rbNormal,rbHard,rbExpert,btnOk);
		
		Scene sc = new Scene(layout);
		stage.setScene(sc);
		stage.setResizable(false);
		stage.setTitle("Choose difficulty");
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.showAndWait();
	}
	
	public int getDepth(){
		return depth;
	}
	
	
	

}
