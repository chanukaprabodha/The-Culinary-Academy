package lk.ijse.theculinaryacademy.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.theculinaryacademy.BO.BoFactory;
import lk.ijse.theculinaryacademy.BO.Custom.UserBo;
import lk.ijse.theculinaryacademy.DAO.Custom.UserDao;
import lk.ijse.theculinaryacademy.DAO.DaoFactory;
import lk.ijse.theculinaryacademy.DTO.UserDto;
import lk.ijse.theculinaryacademy.Entity.User;
import lk.ijse.theculinaryacademy.EntityTm.UserTm;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class UserFormController {

    @FXML
    private AnchorPane anpUser;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colRole;

    @FXML
    private TableColumn<?, ?> colUsername;

    @FXML
    private TableView<UserTm> tblUser;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtRole;

    @FXML
    private TextField txtSearch;

    @FXML
    private TextField txtUserId;

    @FXML
    private TextField txtUsername;

    UserBo userBo = (UserBo) BoFactory
            .getBoFactory()
            .getBoType(BoFactory.BoType.USER);

    ObservableList<UserTm> userTmObservableList = FXCollections.observableArrayList();

    public void initialize() throws IOException {
        setCellValueFactory();
        setTable();
        selectTableRow();
        generateNewId();
        filterUser();
        txtPassword.setTooltip(new Tooltip("Password must be at least 6 characters long."));
    }

    private String generateNewId() throws IOException {
        UserDao userDao = (UserDao) DaoFactory
                .getDaoFactory()
                .getDaoType(DaoFactory.DaoType.USER);

        String nextId = userDao.getCurrentId();
        txtUserId.setText(nextId);
        return nextId;
    }

    private void setTable() throws IOException {
        userTmObservableList.clear();
        List<User> userList = userBo.getUserList();
        for (User user : userList) {
            UserTm userTm = new UserTm(
                    user.getUser_id(),
                    user.getUsername(),
                    user.getUser_email(),
                    user.getUser_phone(),
                    user.getUser_role()
            );
            userTmObservableList.add(userTm);

        }
        tblUser.setItems(userTmObservableList);
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("user_role"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("user_email"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("user_phone"));
    }

    private void selectTableRow() {
        tblUser.setOnMouseClicked(mouseEvent -> {
            int row = tblUser.getSelectionModel().getSelectedIndex();
            UserTm userTm = tblUser.getItems().get(row);
            txtUserId.setText(userTm.getUser_id());
            txtUsername.setText(userTm.getUsername());
            txtEmail.setText(userTm.getUser_email());
            txtContact.setText(userTm.getUser_phone());
            txtRole.setText(userTm.getUser_role());
        });
    }

    public void clearFields() {
        txtContact.clear();
        txtEmail.clear();
        txtPassword.clear();
        txtRole.clear();
        txtUserId.clear();
        txtUsername.clear();
        txtPassword.clear();
    }

    private void filterUser() {
        FilteredList<UserTm> filterData = new FilteredList<>(userTmObservableList, e -> true);

        txtSearch.textProperty().addListener((observableValue, oldValue, newValue) -> {
            filterData.setPredicate(user -> {
                if (newValue == null || newValue.isEmpty() || newValue.isBlank()) {
                    return true;
                }

                String searchKeyword = newValue.toLowerCase();
                if (user.getUsername().toLowerCase().contains(searchKeyword)) {
                    return true;
                } else if (user.getUser_email().toLowerCase().contains(searchKeyword)) {
                    return true;
                } else if (user.getUser_id().toLowerCase().contains(searchKeyword)) {
                    return true;
                } else if (user.getUser_phone().toLowerCase().contains(searchKeyword)) {
                    return true;
                } else if (user.getUser_role().toLowerCase().contains(searchKeyword)) {
                    return true;
                }
                return false;
            });
        });

        SortedList<UserTm> userTmSortedList = new SortedList<>(filterData);
        userTmSortedList.comparatorProperty().bind(tblUser.comparatorProperty());
        tblUser.setItems(userTmSortedList);
    }

    @FXML
    void btnClearOnAction(ActionEvent event) throws IOException {
        clearFields();
        generateNewId();
        setTable();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws IOException {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

        if (result.orElse(no) == yes) {
            if (userBo.delete(txtUserId.getText())) {
                new Alert(Alert.AlertType.CONFIRMATION, "User Deleted Successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "SQL Error").show();
            }
        }
        clearFields();
        setTable();
        generateNewId();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws IOException {
        if (!validateFields()) {
            return; // Stop further execution if validation fails
        }

        String id = txtUserId.getText();
        String role = txtRole.getText();
        String username = txtUsername.getText();
        String rawPassword = txtPassword.getText();
        String email = txtEmail.getText();
        String contact = txtContact.getText();

        // Hash the password using BCrypt
        String hashedPassword = BCrypt.hashpw(rawPassword, BCrypt.gensalt());

        UserDto userDto = new UserDto(id, username, hashedPassword, email, contact, role);
        if (userBo.save(userDto)) {
            clearFields();
            txtUserId.setText(generateNewId());
            new Alert(Alert.AlertType.CONFIRMATION, "User Added Successfully!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "SQL Error").show();
        }
        clearFields();
        setTable();
        generateNewId();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws IOException {
        if (!validateFields()) {
            return; // Stop further execution if validation fails
        }

        String id = txtUserId.getText();
        String role = txtRole.getText();
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        String email = txtEmail.getText();
        String contact = txtContact.getText();

        UserDto userDto = new UserDto(id, username, password, email, contact, role);
        if(userBo.update(userDto)){
            new Alert(Alert.AlertType.CONFIRMATION, "User Updated Successfully!").show();
        }else {
            new Alert(Alert.AlertType.ERROR, "SQL Error").show();
        }
        clearFields();
        setTable();
        generateNewId();
    }

    @FXML
    void txtContactOnAction(ActionEvent event) {
    }

    @FXML
    void txtEmailOnAction(ActionEvent event) {
    }

    @FXML
    void txtPasswordOnAction(ActionEvent event) {

    }

    @FXML
    void txtRoleOnAction(ActionEvent event) {

    }

    @FXML
    void txtUserIdOnAction(ActionEvent event) {

    }

    @FXML
    void txtUsernameOnAction(ActionEvent event) {

    }

    private boolean validateFields() {
        if (txtUserId.getText().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "User ID cannot be empty!").show();
            txtUserId.requestFocus();
            return false;
        }
        if (txtUsername.getText().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Username cannot be empty!").show();
            txtUsername.requestFocus();
            return false;
        }
        if (txtEmail.getText().isEmpty() || !txtEmail.getText().matches("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
            new Alert(Alert.AlertType.WARNING, "Invalid Email Address!").show();
            txtEmail.requestFocus();
            return false;
        }
        if (txtContact.getText().isEmpty() || !txtContact.getText().matches("^\\d{10}$")) {
            new Alert(Alert.AlertType.WARNING, "Invalid Contact Number!").show();
            txtContact.requestFocus();
            return false;
        }
        if (txtRole.getText().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Role cannot be empty!").show();
            txtRole.requestFocus();
            return false;
        }
        if (txtPassword.getText().isEmpty() || txtPassword.getText().length() < 6) {
            new Alert(Alert.AlertType.WARNING, "Password must be at least 6 characters!").show();
            txtPassword.requestFocus();
            return false;
        }
        return true;
    }

}
