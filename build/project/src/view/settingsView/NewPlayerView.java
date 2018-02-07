package view.settingsView;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class NewPlayerView{
	
	private String name;
	private boolean isBot;
	private int depth;
	private int player;
	
	
	public NewPlayerView(int player){
		this.player = player;
		this.depth = 5;
		makeView();
	}
	
	public void makeView(){
		
		BorderPane layout = new BorderPane();
		layout.setPadding(new Insets(15,15,5,15));
		
		Button btnOk = new Button("Accepts");
		layout.setBottom(btnOk);
		BorderPane.setAlignment(btnOk, Pos.BOTTOM_CENTER);
		
		String text;
		TextField tfName = new TextField();
		if(player==1){
			text = "First player name:";
			tfName.setText("Player 1");
		}else{
			text = "Second player name:";
			tfName.setText("Player 2");
		}
		VBox center = new VBox(10);
		center.getChildren().addAll(new Label(text),tfName);
		layout.setCenter(center);
		
		VBox left = new VBox(10);
		RadioButton rbHuman = new RadioButton("Human");
		RadioButton rbMachine = new RadioButton("Bot");
		rbMachine.setOnAction(e -> {
			ChooseDifficultyView cdv = new ChooseDifficultyView();
			depth = cdv.getDepth();
		});
		
		ToggleGroup tgPlayerType = new ToggleGroup();
		tgPlayerType.getToggles().addAll(rbHuman,rbMachine);
		tgPlayerType.selectToggle(rbHuman);
		left.getChildren().addAll(rbHuman,rbMachine);
		left.setPadding(new Insets(0,15,15,0));
		layout.setLeft(left);
		
		Stage stage = new Stage();
		btnOk.setOnAction(e -> {
			name = tfName.getText();
			isBot = rbMachine.isSelected();
			stage.close();
		});
		
		Scene sc = new Scene(layout);
		stage.setTitle("New game");
		stage.setScene(sc);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.showAndWait();
	}
	
	public int getPlayer(){
		return this.player;
	}
	
	public boolean isBot(){
		return this.isBot;
	}
	
	public int getDepth(){
		return this.depth;
	}
	
	public String getName(){
		return this.name;
	}
}


