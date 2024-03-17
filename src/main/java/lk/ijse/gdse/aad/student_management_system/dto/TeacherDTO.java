package lk.ijse.gdse.aad.student_management_system.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TeacherDTO implements SuperDTO {
    @Null(message = "Id is auto generated")
    private String id;
    @NotNull(message = "First Name cannot be null")
    private String firstName;
    @NotNull(message = "Last Name cannot be null")
    private String lastName;
    @NotNull(message = "Email cannot be null")
    private String email;
    @NotNull(message = "Salary cannot be null")
    private Double salary;
    @NotNull(message = "Address cannot be null")
    private String address;
    @NotNull(message = "JoinDate cannot be null")
    private Date joinDate;
    @NotNull(message = "Profile Pic cannot be null")
    private String profilePic;
}
