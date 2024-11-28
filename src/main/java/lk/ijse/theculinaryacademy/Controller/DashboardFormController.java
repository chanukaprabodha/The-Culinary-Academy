package lk.ijse.theculinaryacademy.Controller;

import javafx.fxml.FXML;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.ijse.theculinaryacademy.DAO.Custom.CourseDao;
import lk.ijse.theculinaryacademy.DAO.Custom.StudentDao;
import lk.ijse.theculinaryacademy.DAO.Custom.UserDao;
import lk.ijse.theculinaryacademy.DAO.DaoFactory;
import lk.ijse.theculinaryacademy.Entity.Payment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DashboardFormController {

    @FXML
    private AnchorPane anpDash;

    @FXML
    private Label lblStudent;

    @FXML
    private Label lblUser;

    @FXML
    private Label program;

    private int programCount;

    private int studentCount;

    private int userCount;

    @FXML
    private AreaChart<String, Number> dashboardIncomeChart;

    ArrayList<Payment> paymentArrayList = new ArrayList<>();

    public void initialize() {
        programCountCal();
        studentCountCal();
        userCountCal();
        incomeChart();
    }

    private void incomeChart() {
        // Create mockup data for payments
        Payment payment1 = new Payment();
        payment1.setPay_id("P001");
        payment1.setPay_date("2024-01-01");
        payment1.setPay_amount(200.0);
        paymentArrayList.add(payment1);

        Payment payment2 = new Payment();
        payment2.setPay_id("P002");
        payment2.setPay_date("2024-02-01");
        payment2.setPay_amount(300.0);
        paymentArrayList.add(payment2);

        Payment payment3 = new Payment();
        payment3.setPay_id("P003");
        payment3.setPay_date("2024-03-01");
        payment3.setPay_amount(250.0);
        paymentArrayList.add(payment3);


        // Generate data for the chart
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Monthly Income");

        // Group payments by month and sum the amounts
        Map<String, Double> monthlyIncomeMap = new HashMap<>();

        for (Payment payment : paymentArrayList) {
            String month = payment.getPay_date().substring(0, 7); // Extract "YYYY-MM"
            double amount = payment.getPay_amount();
            monthlyIncomeMap.put(month, monthlyIncomeMap.getOrDefault(month, 0.0) + amount);
        }

        // Add data points to the series
        for (Map.Entry<String, Double> entry : monthlyIncomeMap.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }

        // Set the chart data
        dashboardIncomeChart.getData().clear();
        dashboardIncomeChart.getData().add(series);
    }

    private void userCountCal() {
        UserDao userDao = (UserDao) DaoFactory
                .getDaoFactory()
                .getDaoType(DaoFactory.DaoType.USER);

        userCount = userDao.getUserCount();
        setUserCount(userCount);
    }

    private void setUserCount(int userCount) {
        lblUser.setText(String.valueOf(userCount));
    }

    private void studentCountCal() {
        StudentDao studentDao = (StudentDao) DaoFactory
                .getDaoFactory()
                .getDaoType(DaoFactory.DaoType.STUDENT);

        studentCount = studentDao.getStudentCount();
        setStudentCount(studentCount);
    }

    private void setStudentCount(int studentCount) {
        lblStudent.setText(String.valueOf(studentCount));
    }

    public void programCountCal() {
        CourseDao courseDao = (CourseDao) DaoFactory
                .getDaoFactory()
                .getDaoType(DaoFactory.DaoType.COURSE);

        programCount = courseDao.getProgramCount();
        setProgramCount(programCount);
    }

    public void setProgramCount(int programCount) {
        program.setText(String.valueOf(programCount));
    }
}
