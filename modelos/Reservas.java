package HotelProyectoFinal.modelos;

import HotelProyectoFinal.utilities.MySQLConnection;
import HotelProyectoFinal.vistas.VistaCrearModificarReserva;

import javax.swing.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Reservas {
    int id;
    int huespedId;
    int habitacionId;
    Date fechaEntrada;
    Date fechaSalida;

    public Reservas(int id, int huespedid,int habitacionId1,Date fechaEntrada, Date fechaSalida) {
        this.id = id;
        this.huespedId = huespedid;
        this.habitacionId = habitacionId1;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
    }
    public Reservas(int id, int idHuesped, int habitacionId1,String fechaentrada, String fechasalida) {
        this.id = id;
        this.huespedId = idHuesped;
        this.habitacionId = habitacionId1;
        SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
        try {
            fechaEntrada = formato.parse(fechaentrada);
            fechaSalida  = formato.parse(fechasalida);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Formato de fecha inválido. Usa yyyy/MM/dd");
            e.printStackTrace();
        }
    }
    public Reservas(VistaCrearModificarReserva vistaCrearModificarReserva){
        //id = Integer.parseInt(vistaCrearModificarReserva.getReservaId());
        try{
            huespedId = Integer.parseInt(vistaCrearModificarReserva.getIdHuesped());
        } catch (Exception e){
            e.printStackTrace();
        }
        try{
            habitacionId = Integer.parseInt(vistaCrearModificarReserva.getNumeroHabitacion());
        } catch (Exception e){
            e.printStackTrace();
        }
        SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
        try {
            fechaEntrada = formato.parse(vistaCrearModificarReserva.getFechaEntrada());
            fechaSalida  = formato.parse(vistaCrearModificarReserva.getFechaSalida());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Formato de fecha inválido. Usa yyyy/MM/dd");
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHuespedId() {
        return huespedId;
    }

    public void setHuespedId(int huesped) {
        this.huespedId = huesped;
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

    public int getHabitacionId() {
        return habitacionId;
    }
    public void setHabitacionId(int habitacionId) {
        this.habitacionId = habitacionId;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }
    @Override
    public String toString() {
        return "Id: "+ id + "\nHuesped id: " + huespedId + "\nFecha entrada: " + fechaEntrada  + "\nfecha salida: " + fechaSalida + "\n";
    }

    public static ArrayList<Reservas> obtenerReservas() {
        ArrayList<Reservas> reservas = new ArrayList<>();
        String consulta = "SELECT * FROM registro_reservas";
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy/MM/dd");
        try (Connection con = MySQLConnection.connect();
             Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = st.executeQuery(consulta)) {
            while (rs.next()) {
                int id = rs.getInt(1);
                int idHuesped = rs.getInt("idHuesped");
                int idHabitacion = rs.getInt("idHabitacion");
                Date fechaEntrada = rs.getDate("fechaEntrada");
                Date fechaSalida = rs.getDate("fechaSalida");
                String fechaEntradaFormateada = formatoFecha.format(fechaEntrada);
                String fechaSalidaFormateada = formatoFecha.format(fechaSalida);
                reservas.add(new Reservas(id, idHuesped, idHabitacion,fechaEntradaFormateada, fechaSalidaFormateada));
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
        String query = "INSERT INTO registro_reservas " + "(idHuesped, idHabitacion,fechaEntrada, fechaSalida) " + "VALUES(?, ?, ?, ?)";
        int creados = 0;
        try (Connection conexion = MySQLConnection.connect();
             PreparedStatement pst = conexion.prepareStatement(query);
        ){
            pst.setInt(1, reserva.getHuespedId());
            pst.setInt(2, reserva.getHabitacionId());
            pst.setDate(3, new java.sql.Date(reserva.getFechaEntrada().getTime()));
            pst.setDate(4, new java.sql.Date(reserva.getFechaSalida().getTime()));
            creados = pst.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return creados > 0;
    }

    public static boolean actualizarReserva(Reservas reserva, int id){
        String query = "UPDATE registro_reservas SET idHuesped = ?, idHabitacion = ?, fechaEntrada = ?, fechaSalida = ? WHERE idreservas = " + id;
        int actualizados = 0;
        try (
                Connection conexion = MySQLConnection.connect();
                PreparedStatement pst = conexion.prepareStatement(query);
        ){
            pst.setInt(1, reserva.getHuespedId());
            pst.setInt(2, reserva.getHabitacionId());
            pst.setDate(3, new java.sql.Date(reserva.getFechaEntrada().getTime()));
            pst.setDate(4, new java.sql.Date(reserva.getFechaSalida().getTime()));
            actualizados = pst.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return actualizados > 0;
    }
}
