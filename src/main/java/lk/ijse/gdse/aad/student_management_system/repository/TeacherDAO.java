package lk.ijse.gdse.aad.student_management_system.repository;

import lk.ijse.gdse.aad.student_management_system.entity.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherDAO extends JpaRepository<TeacherEntity,String> {
}
