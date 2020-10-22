import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Login extends Application {
	private static Stage stage;
	public static Stage getStage() { return stage; }

	@Override
	public void start(Stage stage) throws Exception {
		this.stage=stage;
		Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
		Scene scene = new Scene(root); // attach scene graph to scene
		stage.setTitle("Bank System"); // displayed in window's title bar
		stage.setScene(scene); // attach scene to stage
		stage.show(); // display the stage
	}

	public static void main(String[] args) {
		// create a TipCalculator object and call its start method
		launch(args);
	}
}
