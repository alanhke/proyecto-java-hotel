package HotelProyectoFinal.modelos;

import HotelProyectoFinal.utilities.MySQLConnection;
import HotelProyectoFinal.vistas.VistaModificarUsuario;
import HotelProyectoFinal.vistas.VistaRegistrarse;
import com.fasterxml.jackson.databind.JsonNode;
import java.sql.*;
import java.util.ArrayList;

public class DatosUsuario {
    private int id;
    private String nombre;
    private String apellido;
    private String nombreUsuario;
    private String contrasena;
    private String confirmarContrasena;
    private String genero1;
    private String tipo;
    public DatosUsuario(VistaRegistrarse vistaRegistrarse) {
        nombre = vistaRegistrarse.getNombre();
        apellido = vistaRegistrarse.getApellido();
        nombreUsuario = vistaRegistrarse.getNombreUsuario();
        contrasena = vistaRegistrarse.getPassword();
        confirmarContrasena = vistaRegistrarse.getFinalPassword();
        genero1 = vistaRegistrarse.getGender();
        tipo = vistaRegistrarse.getOpcion();
    }

    public DatosUsuario(VistaModificarUsuario vistaModificarUsuario) {
        nombre = vistaModificarUsuario.getNombre();
        apellido = vistaModificarUsuario.getApellido();
        nombreUsuario = vistaModificarUsuario.getNombreUsuario();
        contrasena = vistaModificarUsuario.getPassword();
        confirmarContrasena = vistaModificarUsuario.getFinalPassword();
        genero1 = vistaModificarUsuario.getGender();
        tipo = vistaModificarUsuario.getOpcion();
    }

    /*public DatosUsuario(JsonNode obj) {
        this.nombre = obj.get("nombre").asText();
        this.apellido = obj.get("apellido").asText();
        this.nombreUsuario = obj.get("nombreUsuario").asText();
        this.contrasena = obj.get("contrasena").asText();
        this.confirmarContrasena = obj.get("confirmarContrasena").asText();
        this.genero1 = obj.get("genero1").asText();
        this.tipo = obj.get("tipo").asText();
    }*/

    public DatosUsuario(int id, String nombre, String apellido, String nombreUsuario, String contrasena, String genero, String tipo) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.confirmarContrasena = contrasena;
        this.genero1 = genero;
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public String getNombreUsuario() {
        return nombreUsuario;
    }
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    public String getContrasena() {
        return contrasena;
    }
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    public String getConfirmarContrasena() {
        return confirmarContrasena;
    }
    public void setConfirmarContrasena(String confirmarContrasena) {
        this.confirmarContrasena = confirmarContrasena;
    }

    public String getGenero1() {
        return genero1;
    }

    public void setGenero1(String genero1) {
        this.genero1 = genero1;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Nombre: "+ nombre + "\nApellido: " + apellido + "\nNombre de usuario: " + nombreUsuario  + "\nGenero: " + genero1 + "\nTipo: " + tipo + "\n";
    }

    public static ArrayList<DatosUsuario> obtenerUsuarios() {
        ArrayList<DatosUsuario> usuarios = new ArrayList<>();
        //Statement st = null;
        //ResultSet rs = null;
        String consulta = "Select * from registro_usuarios";

        try (Connection con = MySQLConnection.connect();
             Statement st = (Statement)con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = st.executeQuery(consulta);
        ){
            while (rs.next()) {
                usuarios.add(new DatosUsuario(rs.getInt(1),rs.getString("nombres"), rs.getString("apellidos"), rs.getString("nombreUsuario"),rs.getString("contrasena"),rs.getString("genero"),rs.getString("tipo")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return usuarios;
    }

    public static boolean eliminarUsu(int id){
        String consulta = "Delete from registro_usuarios where idusuarios = " + id;
        int eliminados = 0;

        try (Connection con = MySQLConnection.connect();
             Statement st = (Statement) con.createStatement();
        ){
            eliminados = st.executeUpdate(consulta);
            System.out.println(eliminados);
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return eliminados > 0;
    }

    public static boolean agregarUsuario(DatosUsuario usuario){
        String query = "INSERT INTO registro_usuarios " + "(nombres, apellidos, nombreUsuario, contrasena, genero, tipo) " + "VALUES(?, ?, ?, ?, ?, ?)";
        int creados = 0;
        try (Connection conexion = MySQLConnection.connect();
             PreparedStatement pst = conexion.prepareStatement(query);
        ){
            pst.setString(1, usuario.getNombre());
            pst.setString(2, usuario.getApellido());
            pst.setString(3, usuario.getNombreUsuario());
            pst.setString(4,usuario.getContrasena());
            pst.setString(5,usuario.getGenero1());
            pst.setString(6,usuario.getTipo());
            creados = pst.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return creados > 0;
    }

    public static boolean actualizarUsuario(DatosUsuario usuario, int id){
        String query = "UPDATE registro_usuarios SET nombres = ?, apellidos = ?, nombreUsuario = ?, contrasena = ?, genero = ?, tipo = ? WHERE idusuarios = " + id;
        int actualizados = 0;
        try (
                Connection conexion = MySQLConnection.connect();
                PreparedStatement pst = conexion.prepareStatement(query);
        ){
            pst.setString(1, usuario.getNombre());
            pst.setString(2, usuario.getApellido());
            pst.setString(3, usuario.getNombreUsuario());
            pst.setString(4,usuario.getContrasena());
            pst.setString(5,usuario.getGenero1());
            pst.setString(6,usuario.getTipo());
            actualizados = pst.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return actualizados > 0;
    }
}
