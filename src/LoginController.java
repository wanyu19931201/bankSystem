import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

public class LoginController {

	//private Customer[] list_customer;
	private ConnectDB db;
	private Connection con;

	@FXML
	private AnchorPane rootPane;
	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField input_account;

	@FXML
	private PasswordField input_password;

	@FXML
	void clear(ActionEvent event) {
		input_account.setText("");
		input_password.setText("");
	}

	@FXML
	void login(ActionEvent event) throws IOException, SQLException {

		if (db.check_account(con, input_account.getText(), input_password.getText())) {
			con.close();
			AnchorPane pane = FXMLLoader.load(getClass().getResource("selectType.fxml"));
			// System.out.println(pane.getChildren());
			Label lab = new Label(input_account.getText());
			lab.setVisible(false);
			pane.getChildren().add(lab);
			System.out.println();
			rootPane.getChildren().setAll(pane);
		} else {
			show_alert_message("Error", "Login Fail", "Please check your account number and password again");
			input_account.setText("");
			input_password.setText("");
		}

	}

	public void show_alert_message(String title, String header, String detail) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(detail);
		alert.showAndWait();
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
