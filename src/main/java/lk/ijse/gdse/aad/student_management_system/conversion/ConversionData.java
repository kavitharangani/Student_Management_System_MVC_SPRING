package lk.ijse.gdse.aad.student_management_system.conversion;

import lk.ijse.gdse.aad.student_management_system.dto.StudentDTO;
import lk.ijse.gdse.aad.student_management_system.dto.TeacherDTO;
import lk.ijse.gdse.aad.student_management_system.entity.StudentEntity;
import lk.ijse.gdse.aad.student_management_system.entity.TeacherEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConversionData {
   final private ModelMapper  modelMapper;

   @Autowired
   public ConversionData(ModelMapper modelMapper){
       this.modelMapper=modelMapper;
   }

   //Student object mapping
    public StudentDTO convertToStudentDTO(StudentEntity student){
       return modelMapper.map(student,StudentDTO.class);
    }
    public StudentEntity convertToStudentEntity(StudentDTO studentDTO){
       return modelMapper.map(studentDTO,StudentEntity.class);
    }
    public List<StudentDTO> getStudentDTOList(List<StudentEntity>studentEntityList){
       return modelMapper.map(studentEntityList, List.class);
    }


    //Teacher

    public TeacherDTO convertToTeacherDTO(TeacherEntity teacher){
        return modelMapper.map(teacher,TeacherDTO.class);
    }
    public TeacherEntity convertToTeacherEntity(TeacherDTO teacherDTO){
        return modelMapper.map(teacherDTO,TeacherEntity.class);
    }
    public List<TeacherDTO> getTeacherDTOList(List<TeacherEntity>teacherEntityList){
        return modelMapper.map(teacherEntityList, List.class);
    }
}
