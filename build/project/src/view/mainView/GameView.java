package view.mainView;


import controller.ActionManager;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import view.Colors;

public class GameView extends BorderPane{
	
	private BoardView boardView;
	private ScoreView scoreView;
	VBox right;
	
	public GameView(String p1,String p2){
		this.boardView = new BoardView();
		this.scoreView = new ScoreView(p1,p2);
		this.setCenter(boardView);
		right = new VBox(15);
		Button btnUndo = new Button("Undo");
		btnUndo.setOnAction(ActionManager.getInstance().getUndoAction());
		right.getChildren().addAll(btnUndo,scoreView);
		this.setRight(right);
		
		HBox hbox = new HBox(15);
		
		setTop(hbox);
		hbox.setPadding(new Insets(15));
		updateColors();
	}

	
	
	public BoardView getBoardView() {
		return boardView;
	}
	
	public ScoreView getScoreView(){
		return scoreView;
	}
	
	public void updateColors(){
		right.setStyle("-fx-background-color:#" + Colors.gameBackgroundColor.toString().substring(2));
		this.setStyle("-fx-background-color:#" + Colors.gameBackgroundColor.toString().substring(2));
		boardView.updateColors();
		scoreView.updateColors();
	}
	
	
	

}
