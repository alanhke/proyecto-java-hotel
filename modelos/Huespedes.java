package HotelProyectoFinal.modelos;

import HotelProyectoFinal.utilities.MySQLConnection;
import HotelProyectoFinal.vistas.VistaCrearModificarHuesped;
import HotelProyectoFinal.vistas.VistaCrearModificarReserva;

import java.sql.*;
import java.util.ArrayList;

public class Huespedes {
    int id;
    String nombre;
    String correo;
    String direccion;
    String telefono;
    String documentoIdentidad;
    public Huespedes(int id, String nombre, String correo, String direccion, String telefono, String documentoIdentidad) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.direccion = direccion;
        this.telefono = telefono;
        this.documentoIdentidad = documentoIdentidad;
    }
    public Huespedes(VistaCrearModificarHuesped vista){
        this.id = 0;
        this.nombre = vista.getNombres();
        this.correo = vista.getCorreo();
        this.direccion = vista.getDireccion();
        this.telefono = vista.getTelefono();
        this.documentoIdentidad = vista.getDocumentoIdentidad();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    public void setDocumentoIdentidad(String documentoIdentidad) {
        this.documentoIdentidad = documentoIdentidad;
    }

    @Override
    public String toString() {
        return "Id: "+ id + "Nombre: "+ nombre + "\nCorreo: " + correo + "\nDireccion: " + direccion  + "\nTelefono: " + telefono + "\nDocumento de identificacion: " + documentoIdentidad + "\n";
    }

    public static ArrayList<Huespedes> obtenerHuespedes() {
        ArrayList<Huespedes> huespedes = new ArrayList<>();
        //Statement st = null;
        //ResultSet rs = null;
        String consulta = "Select * from registro_huespedes";

        try (Connection con = MySQLConnection.connect();
             Statement st = (Statement)con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = st.executeQuery(consulta);
        ){
            while (rs.next()) {
                huespedes.add(new Huespedes(rs.getInt(1),rs.getString("nombres"), rs.getString("correo"), rs.getString("direccion"),rs.getString("telefono"),rs.getString("documentoIdentidad")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return huespedes;
    }

    public static boolean eliminarHuesped(int id){
        String consulta = "Delete from registro_huespedes where idhuespedes = " + id;
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

    public static boolean agregarHuesped(Huespedes huesped){
        String query = "INSERT INTO registro_huespedes " + "(nombres, correo, direccion, telefono, documentoIdentidad) " + "VALUES(?, ?, ?, ?, ?)";
        int creados = 0;
        try (Connection conexion = MySQLConnection.connect();
             PreparedStatement pst = conexion.prepareStatement(query);
        ){
            pst.setString(1, huesped.getNombre());
            pst.setString(2, huesped.getCorreo());
            pst.setString(3, huesped.getDireccion());
            pst.setString(4,huesped.getTelefono());
            pst.setString(5,huesped.getDocumentoIdentidad());
            creados = pst.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return creados > 0;
    }

    public static boolean actualizarHuesped(Huespedes huesped, int id){
        String query = "UPDATE registro_huespedes SET nombres = ?, correo = ?, direccion = ?, telefono = ?, documentoIdentidad = ? WHERE idhuespedes = " + id;
        int actualizados = 0;
        try (
                Connection conexion = MySQLConnection.connect();
                PreparedStatement pst = conexion.prepareStatement(query);
        ){
            pst.setString(1, huesped.getNombre());
            pst.setString(2, huesped.getCorreo());
            pst.setString(3, huesped.getDireccion());
            pst.setString(4,huesped.getTelefono());
            pst.setString(5,huesped.getDocumentoIdentidad());
            actualizados = pst.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return actualizados > 0;
    }

    public static Huespedes obtenerHuesped(int id) {
        Huespedes huespedBuscado = null;
        String consulta = "SELECT * FROM registro_huespedes WHERE idhuespedes = " + id;

        try (Connection con = MySQLConnection.connect();
             Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = st.executeQuery(consulta)) {

            if (rs.next()) {
                huespedBuscado = new Huespedes(
                        rs.getInt(1),
                        rs.getString("nombres"),
                        rs.getString("correo"),
                        rs.getString("direccion"),
                        rs.getString("telefono"),
                        rs.getString("documentoIdentidad")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return huespedBuscado;
    }

}
