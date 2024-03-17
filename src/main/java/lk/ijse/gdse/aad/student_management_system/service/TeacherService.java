package lk.ijse.gdse.aad.student_management_system.service;

import lk.ijse.gdse.aad.student_management_system.dto.StudentDTO;
import lk.ijse.gdse.aad.student_management_system.dto.TeacherDTO;

import java.util.List;

public interface TeacherService {
    TeacherDTO saveTeacher(TeacherDTO teacher);
    List<TeacherDTO> getAllTeacher();
    TeacherDTO getSelectedTeacher(String id);
    void deleteTeacher(String id);
    void updateTeacher(String id,TeacherDTO teacher);
}
