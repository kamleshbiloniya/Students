package com.helloswf;

import com.google.inject.AbstractModule;
import com.helloswf.services.StudentService;
import com.helloswf.services.StudentServiceImpl;

public class StudentModule extends AbstractModule {
    public static final String[] PCKGS = {"com.helloswf"};
    @Override
    protected void configure() {
        bind(StudentService.class).to(StudentServiceImpl.class);
    }
}
