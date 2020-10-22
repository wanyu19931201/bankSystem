import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SelectTypeController extends LoginController{
	@FXML
	private Stage stage;

	public void setStageTitle(String newTitle) {
		Login.getStage().setTitle(newTitle);
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane rootPane;
   
    
    @FXML
    void deposit(ActionEvent event) throws IOException {
    	String log_acc=((Labeled) rootPane.getChildren().get(1)).getText();
    	System.out.println(((Labeled) rootPane.getChildren().get(1)).getText());
    	AnchorPane pane=FXMLLoader.load(getClass().getResource("deposit.fxml"));
    	Label lab=new Label(log_acc);
    	lab.setVisible(false);
    	pane.getChildren().add(lab);
    	rootPane.getChildren().setAll(pane);
    }

    @FXML
    void transfer(ActionEvent event) throws IOException {
    	String log_acc=((Labeled) rootPane.getChildren().get(1)).getText();
    	System.out.println(((Labeled) rootPane.getChildren().get(1)).getText());
    	AnchorPane pane=FXMLLoader.load(getClass().getResource("transfer.fxml"));
    	Label lab=new Label(log_acc);
    	lab.setVisible(false);
    	pane.getChildren().add(lab);
    	rootPane.getChildren().setAll(pane);
    }

    @FXML
    void withdraw(ActionEvent event) throws IOException {
    	String log_acc=((Labeled) rootPane.getChildren().get(1)).getText();
    	System.out.println(((Labeled) rootPane.getChildren().get(1)).getText());
    	AnchorPane pane=FXMLLoader.load(getClass().getResource("withdraw.fxml"));
    	Label lab=new Label(log_acc);
    	lab.setVisible(false);
    	pane.getChildren().add(lab);
    	rootPane.getChildren().setAll(pane);
    }

    @FXML
    void initialize() {
    }
}
