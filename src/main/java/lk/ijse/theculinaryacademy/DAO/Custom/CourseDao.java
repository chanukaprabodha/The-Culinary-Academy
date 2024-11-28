package lk.ijse.theculinaryacademy.DAO.Custom;

import lk.ijse.theculinaryacademy.DAO.CrudDao;
import lk.ijse.theculinaryacademy.Entity.Course;

import java.io.IOException;
import java.util.List;

public interface CourseDao extends CrudDao<Course> {
    String getCurrentId() throws IOException;

    List<String> getCourseId();

    List<String> getCourseIds();

    Course getCourseById(String courseId);
    int getProgramCount();
}
