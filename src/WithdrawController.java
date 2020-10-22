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

public class WithdrawController {
	private ConnectDB db;
	private Connection con;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField input_amount;

	@FXML
	private AnchorPane rootPane;

	@FXML
	void submit(ActionEvent event) {
		try {
			
			String login_acc = ((Labeled) rootPane.getChildren().get(1)).getText();
			
			double balance = db.getbalance(con, login_acc);
			
			double amount = Double.parseDouble(input_amount.getText());

			if (amount > balance) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Error");
				alert.setHeaderText("Withdraw error");
				alert.setContentText("You do not have enough money.");
				alert.showAndWait();
			} else {
				
				db.update(con, login_acc,  balance-amount);
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Success");
				alert.setHeaderText("Withdraw suceess");
				alert.setContentText("Your new balance is "+ db.getbalance(con, login_acc) );
				alert.showAndWait();
			}
			con.close();
			Login.getStage().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
