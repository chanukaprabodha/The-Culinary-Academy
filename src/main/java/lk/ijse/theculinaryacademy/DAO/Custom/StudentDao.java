package lk.ijse.theculinaryacademy.DAO.Custom;

import lk.ijse.theculinaryacademy.DAO.CrudDao;
import lk.ijse.theculinaryacademy.Entity.Student;
import lk.ijse.theculinaryacademy.Entity.Student_Course;

import java.io.IOException;

public interface StudentDao extends CrudDao<Student> {
    String getCurrentId() throws IOException;

    Student getStudentById(String text);

    void saveStudentCourseDetails(Student_Course studentCourse) throws IOException;

    boolean isStudentRegisteredForCourse(String stuId, String courseId) throws IOException;

    int getStudentCount();
}
