package providers;

import db.DBConnection;
import model.Teacher;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TeacherProvider {

    //CRUD

    public void create(Teacher teacher) throws SQLException {
        String sql = "INSERT INTO profesores(nombre, facultad) VALUES ('$NOMBRE','$FACULTAD')";
        sql = sql.replace("$NOMBRE",teacher.getNombre());
        sql = sql.replace("$FACULTAD",teacher.getFacultad());
        DBConnection connection = new DBConnection();
        connection.connect();
        connection.commandSQL(sql);
        connection.disconnect();
    }

    public ArrayList<Teacher> getAllTeachers() throws SQLException {

        ArrayList<Teacher> output = new ArrayList<>();

        String sql = "SELECT * FROM profesores";
        DBConnection connection = new DBConnection();
        connection.connect();

        ResultSet resultSet = connection.getDataBySQL(sql);
        while(resultSet.next()){
            int id = resultSet.getInt(resultSet.findColumn("id"));
            String nombre = resultSet.getString(resultSet.findColumn("nombre"));
            String facultad = resultSet.getString(resultSet.findColumn("facultad"));

            Teacher teacher = new Teacher(id, nombre, facultad);
            output.add(teacher);
        }
        connection.disconnect();
        return output;
    }

    public void edit(Teacher teacher) throws SQLException {
        String sql = "UPDATE profesores SET nombre = '$NOMBRE', facultad='$FACULTAD' WHERE id=$ID";
        sql = sql.replace("$ID",String.valueOf(teacher.getId()));
        sql = sql.replace("$NOMBRE",teacher.getNombre());
        sql = sql.replace("$FACULTAD",teacher.getFacultad());
        DBConnection connection = new DBConnection();
        connection.connect();
        connection.commandSQL(sql);
        connection.disconnect();
    }

    public void deleteById(int id) throws SQLException {
        String sql = "DELETE FROM profesores WHERE id="+id;
        DBConnection connection = new DBConnection();
        connection.connect();
        connection.commandSQL(sql);
        connection.disconnect();
    }
}

