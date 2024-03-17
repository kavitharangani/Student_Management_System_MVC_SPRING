package lk.ijse.gdse.aad.student_management_system.controller;

import jakarta.validation.Valid;
import lk.ijse.gdse.aad.student_management_system.dto.StudentDTO;
import lk.ijse.gdse.aad.student_management_system.service.StudentService;
import lk.ijse.gdse.aad.student_management_system.util.UtilMatters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/v1/students")
public class Student {
    @Autowired
    StudentService studentService;


    @GetMapping("/health")
    public String healthCheckStatus(){
        return "Health Check Student";
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void saveStudent(@Valid
                                @RequestPart("firstName") String firstName ,
                                @RequestPart("lastName") String lastName ,
                                @RequestPart("level") String level ,
                                @RequestPart("profilePic") String profilePic,
        Errors errors){
        if (errors.hasFieldErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    errors.getFieldErrors().get(0).getDefaultMessage());
        }
        //Build Base64 image
        String base64ProPic = UtilMatters.convertBase64(profilePic);

        StudentDTO buildStudent = new StudentDTO();
        buildStudent.setFirstName(firstName);
        buildStudent.setLastName(lastName);
        buildStudent.setLevel(level);
        buildStudent.setProfilePic(base64ProPic);

        studentService.saveStudent(buildStudent);
        //save student;
    }

    @GetMapping(value = "/{id}",produces = "application/json")
    ResponseEntity<StudentDTO> getSelectedStudent(@PathVariable ("id") String id){
        StudentDTO selectedStudent = studentService.getSelectedStudent(id);
        return selectedStudent != null ? ResponseEntity.ok(selectedStudent) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping(produces = "application/json")
    List<StudentDTO> getAllStudent(){
        return studentService.getAllStudent();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id:S\\d{3}}")
    public void deleteStudent(@PathVariable ("id") String id){
        studentService.deleteStudent(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void updateStudent(@Valid @RequestPart("firstName") String firstName ,
                              @RequestPart("lastName") String lastName ,
                              @RequestPart("level") String level ,
                              @RequestPart("profilePic") String profilePic,
                              @RequestParam("id") String id,
                              Errors errors){
        if (errors.hasFieldErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    errors.getFieldErrors().get(0).getDefaultMessage());
        }

        String updatedBase64ProPic = UtilMatters.convertBase64(profilePic);

        StudentDTO updatedBuildStudent = new StudentDTO();
        updatedBuildStudent.setFirstName(firstName);
        updatedBuildStudent.setLastName(lastName);
        updatedBuildStudent.setLevel(level);
        updatedBuildStudent.setProfilePic(updatedBase64ProPic);
        studentService.updateStudent(id,updatedBuildStudent);
    }
}
