package view.mainView;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.board.Vec2;
import view.Colors;

public class ScoreView extends VBox{
	
	private String player1;
	private String player2;
	private Label lscoreP1;
	private Label lscoreP2;
	
	public ScoreView(String p1,String p2){
		super();
		this.player1 = p1;
		this.player2 = p2;
		
		lscoreP1 = new Label("0");
		lscoreP2 = new Label("0");
		
		
		HBox hbP1 = new HBox(15);
		hbP1.getChildren().addAll(new Label(player1+":"),lscoreP1);
		HBox hbP2 = new HBox(15);
		hbP2.getChildren().addAll(new Label(player2+":"),lscoreP2);
		
		
		this.getChildren().add(hbP1);
		this.getChildren().add(hbP2);
		this.setPadding(new Insets(5));
		updateColors();
	}
	
	public void updateScore(Vec2 score){
		lscoreP1.setText(""+score.getX());
		lscoreP2.setText(""+score.getY());
	}
	
	public void updateColors(){
		this.setStyle("-fx-background-color:#" + Colors.scoreBackgroundColor.toString().substring(2));
	}

}
