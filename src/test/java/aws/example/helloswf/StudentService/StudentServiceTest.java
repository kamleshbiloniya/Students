package aws.example.helloswf.StudentService;

import com.helloswf.dao.StudentDao;
import com.helloswf.entities.Student;
import com.helloswf.services.StudentService;
import com.helloswf.services.StudentServiceImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.context.internal.ManagedSessionContext;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class StudentServiceTest {
    private StudentService studentService;
    private StudentDao studentDao;
    @Before
    public void setup() {
        Configuration configuration = new Configuration();
//        configuration.setProperty("hibernate.dialect",
//                "org.hibernate.dialect.MySQLDialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/demo_project?useSSL=false");
        configuration.setProperty("hibernate.connection.password","");
        configuration.setProperty("hibernate.connection.username","root");
//        configuration.setProperty("hibernate.connection.autocommit", "true");
//        configuration.setProperty("hibernate.hbm2ddl.auto", "create");
        configuration.setProperty("hibernate.current_session_context_class","org.hibernate.context.internal.ThreadLocalSessionContext");
//        configuration.setProperty("org.hibernate.envers.do_not_audit_optimistic_locking_field", "false");
//        configuration.setProperty("org.hibernate.envers.audit_table_suffix", "_aud");
//        configuration.setProperty("org.hibernate.envers.revision_on_collection_change", "true");
        configuration.addAnnotatedClass(com.helloswf.entities.Student.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session= sessionFactory.openSession();
        session.beginTransaction();
        ManagedSessionContext.bind(session);
        studentDao = new StudentDao(sessionFactory);
        studentService = new StudentServiceImpl(studentDao);
    }

    @Test
    public void testStudentService() {
        Student student = Student.builder()
                .firstName("kamlesh")
                .lastName("biloniya")
                .rollNumber(10001)
                .build();
        studentService.createOrUpdate(student);
        List<Student> expectStudent = studentService.getStudent(10001);
        Assertions.assertEquals(expectStudent.get(0), student);
    }
}
