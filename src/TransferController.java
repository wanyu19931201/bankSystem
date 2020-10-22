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

public class TransferController {

	private ConnectDB db;
	private Connection con;
	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private AnchorPane rootPane;

	@FXML
	private TextField tranfer_account;

	@FXML
	private TextField tranfer_amount;

	@FXML
	void submit(ActionEvent event) throws SQLException {
		
		String login_acc = ((Labeled) rootPane.getChildren().get(1)).getText();
		double balance = db.getbalance(con, login_acc);
		double amount = Double.parseDouble(tranfer_amount.getText());
		
		String trans_acc=tranfer_account.getText();
		
		if(db.exist_account(con, trans_acc ) && balance > amount) {
			double transfer_amount=db.getbalance(con, trans_acc);
			db.update(con, login_acc, balance-amount);
			db.update(con, trans_acc, transfer_amount+amount);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Success");
			alert.setHeaderText("Transfer Success!");
			alert.setContentText("You have transfered to traget account.");
			alert.showAndWait();
			
		}else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Error");
			alert.setHeaderText("Transfer error");
			alert.setContentText("Please check information and try it again.");
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
