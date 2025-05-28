package HotelProyectoFinal.modelos;

import HotelProyectoFinal.utilities.MySQLConnection;
import HotelProyectoFinal.vistas.VistaCrearModificarHabitacion;

import java.sql.*;
import java.util.ArrayList;

public class Habitaciones {
    int numero;
    String tipo;
    String estado;
    double precio;
    public Habitaciones(int numero, String tipo, String estado, double precio) {
        this.numero = numero;
        this.tipo = tipo;
        this.estado = estado;
        this.precio = precio;
    }

    public Habitaciones(VistaCrearModificarHabitacion vista, int numeroH) {
        numero = numeroH;
        tipo = vista.getTipoText();
        estado = vista.getEstadoText();
        precio = Double.parseDouble(vista.getPrecioText());

    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Numero de habitacion: "+ numero + "\nTipo: " + tipo + "\nEstado: " + estado  + "\nPrecio: " + precio + "\n";
    }

    public static ArrayList<Habitaciones> obtenerHabitaciones() {
        ArrayList<Habitaciones> habitaciones = new ArrayList<>();
        //Statement st = null;
        //ResultSet rs = null;
        String consulta = "Select * from registro_habitaciones";

        try (Connection con = MySQLConnection.connect();
             Statement st = (Statement)con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = st.executeQuery(consulta);
        ){
            while (rs.next()) {
                habitaciones.add(new Habitaciones(rs.getInt(1),rs.getString("tipo"), rs.getString("estado"), rs.getDouble("precio")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return habitaciones;
    }

    public static boolean eliminarHabitacion(int numeroHabitacion){
        String consulta = "Delete from registro_habitaciones where idhabitaciones = " + numeroHabitacion;
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

    public static boolean agregarHabitacion(Habitaciones habitacion){
        String query = "INSERT INTO registro_habitaciones " + "(numero, tipo, estado, precio) " + "VALUES(?, ?, ?, ?)";
        int creados = 0;
        try (Connection conexion = MySQLConnection.connect();
             PreparedStatement pst = conexion.prepareStatement(query);
        ){
            pst.setInt(1, habitacion.getNumero());
            pst.setString(2, habitacion.getTipo());
            pst.setString(3, habitacion.getEstado());
            pst.setDouble(4,habitacion.getPrecio());
            creados = pst.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return creados > 0;
    }

    public static boolean actualizarHabitaciones(Habitaciones habitacion, int id){
        String query = "UPDATE registro_habitaciones SET numero = ?, tipo = ?, estado = ?, precio = ? WHERE numeroHabitaciones = " + id;
        int actualizados = 0;
        try (
                Connection conexion = MySQLConnection.connect();
                PreparedStatement pst = conexion.prepareStatement(query);
        ){
            pst.setInt(1, habitacion.getNumero());
            pst.setString(2, habitacion.getTipo());
            pst.setString(3, habitacion.getEstado());
            pst.setDouble(4,habitacion.getPrecio());
            actualizados = pst.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return actualizados > 0;
    }
}
