package com.helloswf.resource;

import com.helloswf.entities.Student;
import com.helloswf.services.StudentService;
import lombok.RequiredArgsConstructor;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("StudentResource")
@Produces(MediaType.APPLICATION_JSON)
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class StudentResource {
    private final StudentService studentService;

    @GET
    @Path("student/{roll_number}")
    public Response getStudents(@PathParam("roll_number") int rollNumber) {
        return Response.ok(studentService.getStudent(rollNumber)).build();
    }

    @POST
    @Path("student")
    public Response createOrUpdate(Student student) {
        return Response.ok(studentService.createOrUpdate(student)).build();
    }
}
