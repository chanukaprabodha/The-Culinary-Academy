package lk.ijse.theculinaryacademy.BO.Custom;

import lk.ijse.theculinaryacademy.BO.SuperBo;
import lk.ijse.theculinaryacademy.DTO.CourseDto;
import lk.ijse.theculinaryacademy.Entity.Course;

import java.io.IOException;
import java.util.List;

public interface CourseBo extends SuperBo {
    public boolean save(CourseDto courseDto) throws IOException;
    public boolean update(CourseDto courseDto) throws IOException;
    public boolean delete(String id) throws IOException;
    public CourseDto getCourse(String id) throws IOException;
    public List<Course> getCourseList() throws IOException;
}
