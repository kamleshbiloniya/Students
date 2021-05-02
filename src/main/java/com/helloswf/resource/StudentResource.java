package com.helloswf.resource;

import com.helloswf.entities.Student;
import com.helloswf.services.StudentServiceImpl;
import lombok.RequiredArgsConstructor;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("v0.1/roll_number/{roll_number}/student")
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class StudentResource {
    private StudentServiceImpl studentService;

    @GET
    public Response getStudents(int rollNumber){
        return Response.ok(studentService.getStudent(rollNumber)).build();
    }

    @POST
    public Response createOrUpdate(Student student) {
        return Response.ok(studentService.createOrUpdate(student)).build();
    }
}
