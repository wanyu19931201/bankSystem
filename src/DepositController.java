import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

public class DepositController {

	private ConnectDB db;
	private Connection con;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private AnchorPane rootPane;

	@FXML
	private TextField input_amount;

	@FXML
	void submit(ActionEvent event) throws SQLException {

		try {
			String login_acc = ((Labeled) rootPane.getChildren().get(1)).getText();
			double balance = db.getbalance(con, login_acc);
			double amount = Double.parseDouble(input_amount.getText());
			System.out.println(input_amount.getText());
			System.out.println(amount + balance);

			db.update(con, login_acc, amount + balance);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Success");
			alert.setHeaderText("Deposit Success");
			alert.setContentText("Your balance is "+ (amount + balance));
			alert.showAndWait();
		} catch (NumberFormatException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Error");
			alert.setHeaderText("Deposit Error");
			alert.setContentText("Please try again.");
			alert.showAndWait();
		}
		con.close();
		Login.getStage().close();
	}

	@FXML
	void initialize() {
		db = new ConnectDB();
		try {
			con = db.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
