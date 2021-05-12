package services;

import model.Message;
import model.Teacher;
import providers.TeacherProvider;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("profesores")
public class TeacherServices {

    //api/profesores/echo
    @GET
    @Path("echo")
    public String echo(){
        return "echo";
    }

    @POST
    @Path("create")
    @Consumes("application/json")
    public Response create(Teacher teacher){

        try {
            TeacherProvider provider = new TeacherProvider();
            provider.create(teacher);
            return Response.ok(new Message("Operacion exitosa"))
                    .header("Content-Type","application/json")
                    .build();

        } catch (SQLException exception) {
            exception.printStackTrace();
            return Response
                    .status(500)
                    .entity(new Message("Operacion fallida"))
                    .header("Content-Type","application/json")
                    .build();
        }
    }

    @GET
    @Path("all")
    public Response getAll(){
        try {
            TeacherProvider provider = new TeacherProvider();
            ArrayList<Teacher> teachers = provider.getAllTeachers();
            return Response
                    .ok(teachers)
                    .header("Content-Type","application/json")
                    .build();
        } catch (SQLException exception) {
            exception.printStackTrace();
            return Response
                    .status(500)
                    .entity(new Message("Operacion fallida"))
                    .header("Content-Type","application/json")
                    .build();
        }
    }

    @PUT
    @Path("edit")
    @Consumes("application/json")
    public Response edit(Teacher teacher){

        try {
            TeacherProvider provider = new TeacherProvider();
            provider.edit(teacher);
            return Response
                    .ok(new Message("Operacion exitosa"))
                    .header("Content-Type","application/json")
                    .build();
        } catch (SQLException exception) {
            exception.printStackTrace();
            return Response
                    .status(500)
                    .entity(new Message("Operacion fallida"))
                    .header("Content-Type","application/json")
                    .build();
        }

    }

    @DELETE
    @Path("delete/{id}")
    public Response delete(@PathParam("id")int id){

        try {
            TeacherProvider provider = new TeacherProvider();
            provider.deleteById(id);
            return Response
                    .ok(new Message("Operacion exitosa"))
                    .header("Content-Type","application/json")
                    .build();
        } catch (SQLException exception) {
            exception.printStackTrace();
            return Response
                    .status(500)
                    .entity(new Message("Operacion fallida"))
                    .header("Content-Type","application/json")
                    .build();
        }
    }

}
