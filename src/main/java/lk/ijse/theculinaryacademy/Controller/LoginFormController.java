package lk.ijse.theculinaryacademy.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.theculinaryacademy.DAO.Custom.UserDao;
import lk.ijse.theculinaryacademy.DAO.DaoFactory;

import java.io.IOException;

public class LoginFormController {
    @FXML
    private AnchorPane anpDashboard;

    @FXML
    private Button btnLogin;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUsername;

    String username;
    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {
        username = txtUsername.getText();
        String password = txtPassword.getText();

        UserDao userDao = (UserDao) DaoFactory
                .getDaoFactory()
                .getDaoType(DaoFactory.DaoType.USER);

        boolean isAuthenticated = userDao.checkCredential(username, password);

        if (isAuthenticated) {
            navigateToTheDashboard();
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid username or password!").show();
        }
    }
    private void navigateToTheDashboard() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainForm.fxml"));
        AnchorPane anchorPane = loader.load();

        MainFormController mainFormController = loader.getController();
        mainFormController.setUsername(username);

        Scene scene = new Scene(anchorPane);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Dashboard Form");
        stage.show();

        anpDashboard.getScene().getWindow().hide();
    }
}
