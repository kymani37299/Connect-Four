package view.mainView;


import controller.ActionManager;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import model.board.Board;
import model.board.Vec2;
import view.Colors;

public class BoardView extends GridPane{
	
	
	private Circle board[][];
	private Color P1Color;
	private Color P2Color;
	private Color emptyColor;
	
	
	public BoardView(){
		P1Color = Colors.player1Color;
		P2Color = Colors.player2Color;
		emptyColor = Colors.emptyColor;
		this.setPadding(new Insets(15));
		this.setVgap(15);
		this.setHgap(15);
		this.setStyle("-fx-background-color:#" + Colors.gameBackgroundColor.toString().substring(2));
		this.board = new Circle[Board.getBoardHeight()][Board.getBoardWidth()];
		for(int i=0;i<Board.getBoardHeight();i++){
			for(int j=0;j<Board.getBoardWidth();j++){
				this.board[i][j] = new Circle(25);
				this.add(this.board[i][j], j, i);
				this.board[i][j].setFill(Colors.emptyColor); // 93b5ec
				this.board[i][j].setStroke(Color.BLACK);
				this.board[i][j].setStrokeWidth(2);
				this.board[i][j].setOnMouseClicked(ActionManager.getInstance().getMoveAction());
				
			}
		}
	}
	
	public void setFieldColor(Vec2 pos,Color color){
		this.board[pos.getX()][pos.getY()].setFill(color);
	}

	public Circle[][] getBoard() {
		return board;
	}
	
	public void resetBoard(){
		for(int i=0;i<Board.getBoardHeight();i++){
			for(int j=0;j<Board.getBoardWidth();j++){
				this.board[i][j].setFill(Colors.emptyColor);
			}
		}
	}
	
	public void updateColors(){
		for(int i=0;i<Board.getBoardHeight();i++){
			for(int j=0;j<Board.getBoardWidth();j++){
				if(this.board[i][j].getFill() == P1Color) this.board[i][j].setFill(Colors.player1Color);
				else if(this.board[i][j].getFill() == P2Color) this.board[i][j].setFill(Colors.player2Color);
				else if(this.board[i][j].getFill() == emptyColor) this.board[i][j].setFill(Colors.emptyColor);
			}
		}
		this.setStyle("-fx-background-color:#" + Colors.gameBackgroundColor.toString().substring(2));
		P1Color = Colors.player1Color;
		P2Color = Colors.player2Color;
		emptyColor = Colors.emptyColor;
	}
	
}
