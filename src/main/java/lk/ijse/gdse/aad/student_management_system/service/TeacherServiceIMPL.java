package lk.ijse.gdse.aad.student_management_system.service;

import jakarta.transaction.Transactional;
import lk.ijse.gdse.aad.student_management_system.conversion.ConversionData;
import lk.ijse.gdse.aad.student_management_system.dto.TeacherDTO;
import lk.ijse.gdse.aad.student_management_system.entity.TeacherEntity;
import lk.ijse.gdse.aad.student_management_system.exception.NotFoundException;
import lk.ijse.gdse.aad.student_management_system.repository.TeacherDAO;
import lk.ijse.gdse.aad.student_management_system.util.UtilMatters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TeacherServiceIMPL implements TeacherService{
    @Autowired
    private TeacherDAO teacherDAO;
    @Autowired
    private ConversionData convert;

    @Override
    public TeacherDTO saveTeacher(TeacherDTO teacher) {
        teacher.setId(UtilMatters.generateID());
        return convert.convertToTeacherDTO(teacherDAO.save(convert.convertToTeacherEntity(teacher)));
    }

    @Override
    public List<TeacherDTO> getAllTeacher() {
        return convert.getTeacherDTOList(teacherDAO.findAll());
    }

    @Override
    public TeacherDTO getSelectedTeacher(String id) {
        if (!teacherDAO.existsById(id)) throw new NotFoundException("TEACHER NOT FOUND");
        return convert.convertToTeacherDTO(teacherDAO.getReferenceById(id));
    }

    @Override
    public void deleteTeacher(String id) {
        if (!teacherDAO.existsById(id)) throw new NotFoundException("TEACHER NOT FOUND");
        teacherDAO.deleteById(id);
    }

    @Override
    public void updateTeacher(String id, TeacherDTO teacher) {
        Optional<TeacherEntity> tmpTeacher = teacherDAO.findById(id);
        if (!tmpTeacher.isPresent()) throw new NotFoundException("TEACHER NOT FOUND");
        tmpTeacher.get().setFirstName(teacher.getFirstName());
        tmpTeacher.get().setLastName(teacher.getLastName());
        tmpTeacher.get().setAddress(teacher.getAddress());
        tmpTeacher.get().setEmail(teacher.getEmail());
        tmpTeacher.get().setSalary(teacher.getSalary());
        tmpTeacher.get().setJoinDate(teacher.getJoinDate());
        tmpTeacher.get().setProfilePic(teacher.getProfilePic());
    }
}
