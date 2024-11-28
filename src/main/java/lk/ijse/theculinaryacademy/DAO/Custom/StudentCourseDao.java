package lk.ijse.theculinaryacademy.DAO.Custom;

import lk.ijse.theculinaryacademy.DAO.CrudDao;
import lk.ijse.theculinaryacademy.Entity.Student_Course;

public interface StudentCourseDao extends CrudDao<Student_Course> {
    Student_Course getStudentCourseById(Long value);
}
