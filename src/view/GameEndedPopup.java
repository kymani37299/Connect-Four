package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GameEndedPopup extends Stage{
	
	public GameEndedPopup(String winner){
		String text;
		if(winner==null){
			text = "Tie!";
		}else{
			text = "Winner is " + winner+ "!";
		}
		VBox layout = new VBox(15);
		layout.getChildren().add(new Label(text));
		layout.setPadding(new Insets(15));
		
		Scene sc = new Scene(layout);
		this.setScene(sc);
		this.setResizable(false);
		this.initModality(Modality.APPLICATION_MODAL);
		this.setTitle("Game over!");
	}
	

}
