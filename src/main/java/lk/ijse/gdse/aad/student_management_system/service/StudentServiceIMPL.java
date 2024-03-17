package lk.ijse.gdse.aad.student_management_system.service;

import jakarta.transaction.Transactional;
import lk.ijse.gdse.aad.student_management_system.conversion.ConversionData;
import lk.ijse.gdse.aad.student_management_system.dto.StudentDTO;
import lk.ijse.gdse.aad.student_management_system.entity.StudentEntity;
import lk.ijse.gdse.aad.student_management_system.exception.NotFoundException;
import lk.ijse.gdse.aad.student_management_system.repository.StudentDao;
import lk.ijse.gdse.aad.student_management_system.util.UtilMatters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StudentServiceIMPL implements StudentService {
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private ConversionData convert;

//    List<StudentDTO> saveStudent = new ArrayList<>();

    @Override
    public StudentDTO saveStudent(StudentDTO student) {
        student.setId(UtilMatters.generateID());
        return convert.convertToStudentDTO(studentDao.save(convert.convertToStudentEntity(student)));

//        StudentEntity studentEntity=convert.convertToStudentEntity(student);
//        studentEntity = studentDao.save(studentEntity);
//        return convert.convertToStudentDTO(studentEntity);

    }

    @Override
    public List<StudentDTO> getAllStudent() {
        return convert.getStudentDTOList(studentDao.findAll());
    }

    @Override
    public StudentDTO getSelectedStudent(String id) {
        if (!studentDao.existsById(id)) throw new NotFoundException("STUDENT NOT FOUND");
            return convert.convertToStudentDTO(studentDao.getReferenceById(id));

    }

    @Override
    public void deleteStudent(String id) {
        if (!studentDao.existsById(id)) throw new NotFoundException("STUDENT NOT FOUND");
        studentDao.deleteById(id);

    }

    @Override
    public void updateStudent(String id, StudentDTO student) {
        Optional<StudentEntity> tmpStudent = studentDao.findById(id);
        if (!tmpStudent.isPresent()) throw new NotFoundException("Student Not Found");
        tmpStudent.get().setFirstName(student.getFirstName());
        tmpStudent.get().setLastName(student.getLastName());
        tmpStudent.get().setLevel(student.getLevel());
        tmpStudent.get().setProfilePic(student.getProfilePic());

    }




//    @Override
//    public StudentDTO saveStudent(StudentDTO student) {
//        saveStudent.add(student);
//        System.out.println(student);
//        return null;
//    }

//    @Override
//    public List<StudentDTO> getAllStudent() {
//        return saveStudent;
//    }

//    @Override
//    public StudentDTO getSelectedStudent(String id) {
//        for (StudentDTO studentDTO : saveStudent) {
//            if (studentDTO.getId().equals(id)) {
//                return studentDTO;
//            }
//        }
//        return null;
//    }

//    @Override
//    public void deleteStudent(String id) {
//        Iterator<StudentDTO> studentIterator = saveStudent.iterator();
//        while (studentIterator.hasNext()) {
//            StudentDTO nextStudent = studentIterator.next();
//            if (nextStudent.getId().equals(id)) {
//                studentIterator.remove();
//            }
//        }
//    }

//    @Override
//    public void updateStudent(String id, StudentDTO student) {
//    }

}
