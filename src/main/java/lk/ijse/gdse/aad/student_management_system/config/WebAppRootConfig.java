package lk.ijse.gdse.aad.student_management_system.config;

import jakarta.persistence.EntityManagerFactory;
import jakarta.servlet.annotation.MultipartConfig;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "lk.ijse.gdse.aad.student_management_system")
@EnableWebMvc
@EnableJpaRepositories("lk.ijse.gdse.aad.student_management_system")
@EnableTransactionManagement
@MultipartConfig(
        location = "C:/Users/LENOVO/AppData",
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 50,
        fileSizeThreshold = 1024 * 1024 * 5
)


public class WebAppRootConfig {
    @Bean
    ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public DataSource dataSource() {
//        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
//        return builder.setType(EmbeddedDatabaseType.HSQL).build();

         DriverManagerDataSource dmds = new DriverManagerDataSource();
         dmds.setDriverClassName("com.mysql.cj.jdbc.Driver");
         dmds.setUrl("jdbc:mysql://localhost:3306/StudentManagementDBAAD65?createDatabaseIfNotExist=true");
         dmds.setUsername("root");
         dmds.setPassword("1234");
         return dmds;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("lk.ijse.gdse.aad.student_management_system.entity");
        factory.setDataSource(dataSource());
        return factory;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {

        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory);
        return txManager;
    }
}
