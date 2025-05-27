package HotelProyectoFinal.vistas;

import HotelProyectoFinal.controladores.ControlBotones;
import HotelProyectoFinal.modelos.DatosUsuario;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame ventana = new JFrame("Hotel Proyecto");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(1000,500);

        VistaRegistrarse vistaRegistrarse = new VistaRegistrarse();
        DatosUsuario modeloDatos = new DatosUsuario(vistaRegistrarse);

        VistaVerUsuarios vistaVerUsuarios = new VistaVerUsuarios();
        VistaModificarUsuario vistaModificarUsuario = new VistaModificarUsuario();
        VistaPaginaPrincipal vistaPaginaPrincipal = new VistaPaginaPrincipal();
        VistaGestionarHabitaciones vistaGestionarHabitaciones = new VistaGestionarHabitaciones();
        VistaGestionarReservas vistaGestionarReservas = new VistaGestionarReservas();
        VistaGestionarHuespedes vistaGestionarHuespedes = new VistaGestionarHuespedes();
        VistaReportes vistaReportes = new VistaReportes();

        new ControlBotones(vistaRegistrarse,modeloDatos,vistaVerUsuarios,vistaModificarUsuario, vistaPaginaPrincipal,vistaGestionarHabitaciones, vistaGestionarReservas,vistaGestionarHuespedes, vistaReportes);
        ventana.add(vistaPaginaPrincipal);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
        /*JFrame ventana = new JFrame("Hotel Proyecto");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(1000,500);
        VistaPaginaPrincipal ventanaPrincipal = new VistaPaginaPrincipal();
        ventanaPrincipal.setVisible(true);
        ventana.add(ventanaPrincipal);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);*/
    }
}
