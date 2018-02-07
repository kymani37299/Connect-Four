package app;

import javafx.application.Application;
import javafx.stage.Stage;
import view.mainView.MainView;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		MainView mv = MainView.getInstance();
		mv.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
