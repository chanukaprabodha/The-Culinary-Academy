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
import lk.ijse.theculinaryacademy.BO.Custom.CourseBo;
import lk.ijse.theculinaryacademy.BO.Custom.StudentBo;
import lk.ijse.theculinaryacademy.BO.Custom.UserBo;
import lk.ijse.theculinaryacademy.DAO.Custom.StudentDao;
import lk.ijse.theculinaryacademy.DAO.Custom.UserDao;
import lk.ijse.theculinaryacademy.DAO.DaoFactory;
import lk.ijse.theculinaryacademy.DTO.CourseDto;
import lk.ijse.theculinaryacademy.DTO.StudentDto;
import lk.ijse.theculinaryacademy.Entity.Course;
import lk.ijse.theculinaryacademy.Entity.Student;
import lk.ijse.theculinaryacademy.Entity.Student_Course;
import lk.ijse.theculinaryacademy.Entity.User;
import lk.ijse.theculinaryacademy.EntityTm.StudentTm;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentFormController {

    @FXML
    private AnchorPane anpStudent;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colUser;

    @FXML
    private ComboBox<String> comboCourse;

    @FXML
    private ComboBox<String> comboUser;

    @FXML
    private TableView<StudentTm> tblStudent;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtDuration;

    @FXML
    private TextField txtFree;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSearch;

    @FXML
    private TextField txtcourseName;

    UserDao userDao = (UserDao) DaoFactory
            .getDaoFactory()
            .getDaoType(DaoFactory.DaoType.USER);

    ObservableList<StudentTm> studentTmObservableList = FXCollections.observableArrayList();

    CourseBo courseBo = (CourseBo) BoFactory
            .getBoFactory()
            .getBoType(BoFactory.BoType.COURSE);

    ArrayList<Course> courseArrayList = new ArrayList<>();

    StudentDao studentDao = (StudentDao) DaoFactory
            .getDaoFactory()
            .getDaoType(DaoFactory.DaoType.STUDENT);

    ArrayList<Student> studentArrayList = new ArrayList<>();

    StudentBo studentBo = (StudentBo) BoFactory
            .getBoFactory()
            .getBoType(BoFactory.BoType.STUDENT);

    public void initialize() throws IOException {
        generateNewId();
        getAllCourses();
        setCourseId();
        setUserId();
        getAllStudents();
        setDate();
        setCellValueFactory();
        setTable();
        filterStudent();
        selectTableRow();
    }

    private void getAllStudents() throws IOException {
        List<Student> studentList = studentBo.getStudentList();
        studentArrayList = (ArrayList<Student>) studentList;
    }

    private void getAllCourses() throws IOException {
        List<Course> courseList = courseBo.getCourseList();
        courseArrayList = (ArrayList<Course>) courseList;
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("stu_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("stu_name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("stu_address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("stu_phone"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
    }

    private void setTable() throws IOException {
        studentTmObservableList.clear();
        List<Student> studentList = studentBo.getStudentList();
        for (Student student : studentList) {
            StudentTm studentTm = new StudentTm(
                    student.getStu_id(),
                    student.getStu_name(),
                    student.getStu_address(),
                    student.getStu_phone(),
                    student.getDate()
            );
            studentTmObservableList.add(studentTm);
        }
        tblStudent.setItems(studentTmObservableList);
    }

    private void selectTableRow() {
        tblStudent.setOnMouseClicked(mouseEvent -> {
            int row = tblStudent.getSelectionModel().getSelectedIndex();
            StudentTm studentTm = tblStudent.getItems().get(row);
            txtId.setText(studentTm.getStu_id());
            txtName.setText(studentTm.getStu_name());
            txtAddress.setText(studentTm.getStu_address());
            txtContact.setText(studentTm.getStu_phone());
        });
    }

    private void setDate() {
        LocalDate now = LocalDate.now();
        txtDate.setText(String.valueOf(now));
    }

    private String generateNewId() throws IOException {
        String nextId = studentDao.getCurrentId();
        txtId.setText(nextId);
        return nextId;
    }

    private void clearFields() {
        txtAddress.clear();
        txtContact.clear();
        txtDate.clear();
        txtId.clear();
        txtName.clear();
        txtSearch.clear();
        txtFree.clear();
        txtcourseName.clear();
        txtSearch.clear();
    }

    private void setUserId() {
        List<String> userIds = userDao.getUserId();
        ObservableList<String> users = FXCollections.observableArrayList();
        for (String userId : userIds) {
            users.add(userId);
        }
        comboUser.setItems(users);
    }

    public void setCourseId() {
        ObservableList<String> id = FXCollections.observableArrayList();
        for (Course course : courseArrayList) {
            id.add(course.getCourse_id());
        }
        comboCourse.setItems(id);
    }

    @FXML
    void btnClearOnAction(ActionEvent event) throws IOException {
        clearFields();
        generateNewId();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws IOException {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

        if (result.orElse(no) == yes) {
            if (studentBo.delete(txtId.getText())) {
                new Alert(Alert.AlertType.CONFIRMATION, "Student Deleted Successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "SQL Error").show();
            }
        }
        setTable();
        clearFields();
        generateNewId();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws IOException {
        if (validateAllFields()) {
            // Existing logic for saving a student
            String studentId = txtId.getText();
            Student existingStudent = studentDao.getStudentById(studentId);

            if (existingStudent != null) {
                registerStudentForCourse(existingStudent);
            } else {
                User user = userDao.getUserById(comboUser.getValue());

                StudentDto studentDto = new StudentDto();
                studentDto.setStu_id(studentId);
                studentDto.setStu_name(txtName.getText());
                studentDto.setStu_address(txtAddress.getText());
                studentDto.setStu_phone(txtContact.getText());
                studentDto.setDate(Date.valueOf(txtDate.getText()));
                studentDto.setUser(user);

                studentBo.save(studentDto);
                Student newStudent = studentDao.getStudentById(studentId);
                registerStudentForCourse(newStudent);
            }

            setTable();
            new Alert(Alert.AlertType.INFORMATION, "Student Added With Course Successfully!").show();
            clearFields();
            generateNewId();
        }
    }

    private void registerStudentForCourse(Student student) throws IOException {
        String courseId = comboCourse.getValue();
        Course selectedCourse = null;

        // Find the course by ID
        for (Course course : courseArrayList) {
            if (course.getCourse_id().equals(courseId)) {
                selectedCourse = course;
                break;
            }
        }

        if (selectedCourse != null) {
            // Check if the student is already registered for this course
            if (!studentDao.isStudentRegisteredForCourse(student.getStu_id(), courseId)) {
                Student_Course studentCourse = new Student_Course();
                studentCourse.setStudent(student);
                studentCourse.setCourse(selectedCourse);
                studentCourse.setRegistration_date(new java.util.Date());

                // Save the student-course relationship
                studentDao.saveStudentCourseDetails(studentCourse);
            } else {
                System.out.println("Student is already registered for this course.");
            }
        } else {
            System.out.println("Selected course not found.");
        }
        setTable();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws IOException {
        if (validateAllFields()) {
            String id = txtId.getText();
            String name = txtName.getText();
            String address = txtAddress.getText();
            String contact = txtContact.getText();
            Date date = Date.valueOf(txtDate.getText());

            User user = userDao.getUserById(comboUser.getValue());
            StudentDto studentDto = new StudentDto(id, name, address, contact, date, user);

            if (studentBo.update(studentDto)) {
                new Alert(Alert.AlertType.INFORMATION, "Student Updated Successfully").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Error updating student details").show();
            }

            setTable();
            clearFields();
            generateNewId();
        }
    }

    @FXML
    void comboCourseOnAction(ActionEvent event) throws IOException {
        String courseId = comboCourse.getValue();
        CourseDto course = courseBo.getCourse(courseId);
        if (course != null) {
            txtcourseName.setText(course.getCourse_name());
            txtDuration.setText(course.getDuration());
            txtFree.setText(String.valueOf(course.getCourse_fee()));
        }
    }

    @FXML
    void comboUserOnAction(ActionEvent event) {
        String userId = comboUser.getValue();
        UserBo userBo = (UserBo) BoFactory
                .getBoFactory()
                .getBoType(
                        BoFactory
                                .BoType
                                .USER);
        userBo.getUser(userId);
    }

    private void filterStudent() {
        FilteredList<StudentTm> filterData = new FilteredList<>(studentTmObservableList, e -> true);

        txtSearch.textProperty().addListener((observableValue, oldValue, newValue) -> {
            filterData.setPredicate(student -> {
                if (newValue == null || newValue.isEmpty() || newValue.isBlank()) {
                    return true;
                }

                String searchKeyword = newValue.toLowerCase();
                if (student.getStu_id().toLowerCase().contains(searchKeyword)) {
                    return true;
                } else if (student.getStu_phone().toLowerCase().contains(searchKeyword)) {
                    return true;
                } else if (student.getStu_name().toLowerCase().contains(searchKeyword)) {
                    return true;
                }
                return false;
            });
        });

        SortedList<StudentTm> studentTmSortedList = new SortedList<>(filterData);
        studentTmSortedList.comparatorProperty().bind(tblStudent.comparatorProperty());
        tblStudent.setItems(studentTmSortedList);
    }

    @FXML
    void txtAddressOnAction(ActionEvent event) {

    }

    @FXML
    void txtContactOnAction(ActionEvent event) {

    }

    @FXML
    void txtDateOnAction(ActionEvent event) {

    }

    @FXML
    void txtIdOnAction(ActionEvent event) {

    }

    @FXML
    void txtNameOnAction(ActionEvent event) {

    }

    private boolean validateId(String id) {
        return id.matches("^S[0-9]{3}$"); // Example: S001
    }

    private boolean validateName(String name) {
        return name.matches("^[A-Za-z ]+$"); // Only letters and spaces
    }

    private boolean validateAddress(String address) {
        return !address.isBlank(); // Address should not be empty
    }

    private boolean validateContact(String contact) {
        return contact.matches("^[0-9]{10}$"); // Exactly 10 digits
    }

    private boolean validateDate(String date) {
        return date.matches("^\\d{4}-\\d{2}-\\d{2}$"); // Format: YYYY-MM-DD
    }

    private boolean validateAllFields() {
        if (!validateId(txtId.getText())) {
            showAlert("Invalid ID", "ID must follow the format S001.");
            return false;
        }
        if (!validateName(txtName.getText())) {
            showAlert("Invalid Name", "Name can only contain letters and spaces.");
            return false;
        }
        if (!validateAddress(txtAddress.getText())) {
            showAlert("Invalid Address", "Address cannot be empty.");
            return false;
        }
        if (!validateContact(txtContact.getText())) {
            showAlert("Invalid Contact", "Contact must be exactly 10 digits.");
            return false;
        }
        if (!validateDate(txtDate.getText())) {
            showAlert("Invalid Date", "Date must follow the format YYYY-MM-DD.");
            return false;
        }
        return true;
    }

    private void showAlert(String title, String message) {
        new Alert(Alert.AlertType.ERROR, message, ButtonType.OK).showAndWait();
    }

}
