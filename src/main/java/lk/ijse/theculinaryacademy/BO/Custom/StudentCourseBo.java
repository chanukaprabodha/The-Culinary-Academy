package lk.ijse.theculinaryacademy.BO.Custom;

import lk.ijse.theculinaryacademy.BO.SuperBo;
import lk.ijse.theculinaryacademy.Entity.Student_Course;

import java.io.IOException;
import java.util.List;

public interface StudentCourseBo extends SuperBo {
    public List<Student_Course> getStudentCourseList() throws IOException;

}
