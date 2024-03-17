package lk.ijse.gdse.aad.student_management_system.config;

import jakarta.servlet.annotation.MultipartConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Configuration
@ComponentScan(basePackages = "lk.ijse.gdse.aad.student_management_system")
@EnableWebMvc
@MultipartConfig
public class WebAppConfig {

}

