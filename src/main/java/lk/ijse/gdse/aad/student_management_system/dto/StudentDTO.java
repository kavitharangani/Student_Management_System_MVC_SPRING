package lk.ijse.gdse.aad.student_management_system.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentDTO implements SuperDTO{
//    @NotNull(message = "Id cannot be null")
    @Null(message = "Id is auto generated")
    private String id;
    @NotNull(message = "First Name cannot be null")
    private String firstName;
    @NotNull(message = "Last Name cannot be null")
    private String lastName;
    @NotNull(message = "Level cannot be null")
    private String level;
    private String profilePic;
}
