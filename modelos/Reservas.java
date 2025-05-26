package HotelProyectoFinal.modelos;

import HotelProyectoFinal.utilities.MySQLConnection;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Reservas {
    int id;
    Huespedes huesped;
    Date fechaEntrada;
    Date fechaSalida;

    public Reservas(int id, Huespedes huesped,Date fechaEntrada, Date fechaSalida) {
        this.id = id;
        this.huesped = huesped;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
    }
    public Reservas(int id, int idHuesped,String fechaEntrada, String fechaSalida) {
        this.id = id;
        this.huesped = Huespedes.obtenerHuesped(idHuesped);
        try {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            this.fechaEntrada = formato.parse(fechaEntrada);
            this.fechaSalida = formato.parse(fechaSalida);
        } catch (ParseException e) {
            e.printStackTrace();
            this.fechaEntrada = null;
            this.fechaSalida = null;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Huespedes getHuesped() {
        return huesped;
    }

    public void setHuesped(Huespedes huesped) {
        this.huesped = huesped;
    }

    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }
    @Override
    public String toString() {
        return "Id: "+ id + "\nHuesped: " + huesped.toString() + "\nFecha entrada: " + fechaEntrada  + "\nfecha salida: " + fechaSalida + "\n";
    }

    public static ArrayList<Reservas> obtenerReservas() {
        ArrayList<Reservas> reservas = new ArrayList<>();
        //Statement st = null;
        //ResultSet rs = null;
        String consulta = "Select * from registro_reservas";

        try (Connection con = MySQLConnection.connect();
             Statement st = (Statement)con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = st.executeQuery(consulta);
        ){
            while (rs.next()) {
                reservas.add(new Reservas(rs.getInt(1),rs.getInt("idHuesped"), rs.getString("fechaEntrada"), rs.getString("fechaSalida")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return reservas;
    }

    public static boolean eliminarReserva(int id){
        String consulta = "Delete from registro_reservas where idreservas = " + id;
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

    public static boolean agregarReserva(Reservas reserva){
        String query = "INSERT INTO registro_reservas " + "(idHuesped, fechaEntrada, fechaSalida) " + "VALUES(?, ?, ?)";
        int creados = 0;
        try (Connection conexion = MySQLConnection.connect();
             PreparedStatement pst = conexion.prepareStatement(query);
        ){
            pst.setInt(1, reserva.getHuesped().getId());
            pst.setDate(2, new java.sql.Date(reserva.getFechaEntrada().getTime()));
            pst.setDate(3, new java.sql.Date(reserva.getFechaSalida().getTime()));
            creados = pst.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return creados > 0;
    }

    public static boolean actualizarReserva(Reservas reserva, int id){
        String query = "UPDATE registro_reservas SET idHuesped = ?, fechaEntrada = ?, fechaSalida = ? WHERE idreservas = " + id;
        int actualizados = 0;
        try (
                Connection conexion = MySQLConnection.connect();
                PreparedStatement pst = conexion.prepareStatement(query);
        ){
            pst.setInt(1, reserva.getHuesped().getId());
            pst.setDate(2, new java.sql.Date(reserva.getFechaEntrada().getTime()));
            pst.setDate(3, new java.sql.Date(reserva.getFechaSalida().getTime()));
            actualizados = pst.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return actualizados > 0;
    }
}
