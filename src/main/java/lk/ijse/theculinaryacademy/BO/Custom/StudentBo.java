package lk.ijse.theculinaryacademy.BO.Custom;

import lk.ijse.theculinaryacademy.BO.SuperBo;
import lk.ijse.theculinaryacademy.DTO.StudentDto;
import lk.ijse.theculinaryacademy.Entity.Student;

import java.io.IOException;
import java.util.List;

public interface StudentBo extends SuperBo {
    public boolean save(StudentDto studentDto) throws IOException;
    public boolean update(StudentDto studentDto) throws IOException;
    public boolean delete(String id) throws IOException;
    public StudentDto getStudent(String id);
    public List<Student> getStudentList() throws IOException;
}
