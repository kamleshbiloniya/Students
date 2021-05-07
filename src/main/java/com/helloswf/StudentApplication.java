package com.helloswf;

import com.google.inject.Module;
import com.helloswf.dao.StudentDao;
import com.helloswf.resource.StudentResource;
import com.helloswf.services.StudentServiceImpl;
import in.cleartax.dropwizard.sharding.transactions.UnitOfWorkModule;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.context.internal.ManagedSessionContext;
import ru.vyarus.dropwizard.guice.GuiceBundle;


public class StudentApplication extends Application<StudentConfiguration> {

    private Module studentModule;
    public static void main(final String[] args) throws Exception {
        System.out.println("staring Server..... !");
        new StudentApplication().run(args);
    }

    @Override
    public void run(StudentConfiguration studentConfiguration, Environment environment) throws Exception {
        final StudentDao studentDao = new StudentDao(getSessionFactory());
        final StudentServiceImpl studentService = new StudentServiceImpl(studentDao);
        final StudentResource studentResource = new StudentResource(studentService);
        environment.jersey().register(studentResource);
    }

    @Override
    public void initialize(Bootstrap<StudentConfiguration> bootstrap){
        studentModule = new StudentModule();
        GuiceBundle.Builder<StudentConfiguration> guiceBundleBuilder = GuiceBundle.<StudentConfiguration>builder()
                .modules(studentModule,
                        new UnitOfWorkModule()).enableAutoConfig(StudentModule.PCKGS);

    }

    private SessionFactory getSessionFactory(){
        Configuration configuration = new Configuration();
//        configuration.setProperty("hibernate.dialect",
//                "org.hibernate.dialect.MySQLDialect");
        configuration.setProperty("hibernate.connection.driver_class","com.mysql.jdbc.Driver");
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
        return sessionFactory;
    }
}
