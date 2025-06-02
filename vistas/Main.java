package HotelProyectoFinal.vistas;

import HotelProyectoFinal.controladores.ControlBotones;
import HotelProyectoFinal.modelos.DatosUsuario;
import HotelProyectoFinal.utilities.Estilo;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        FlatLightLaf.setup();

        VistaRegistrarse vistaRegistrarse = new VistaRegistrarse();
        DatosUsuario modeloDatos = new DatosUsuario(vistaRegistrarse);
        VistaVerUsuarios vistaVerUsuarios = new VistaVerUsuarios();
        VistaModificarUsuario vistaModificarUsuario = new VistaModificarUsuario();

        VistaPaginaPrincipal vistaPaginaPrincipal = new VistaPaginaPrincipal();
        VistaGestionarHabitaciones gestionarHabitaciones = new VistaGestionarHabitaciones();
        VistaGestionarReservas vistaGestionarRes = new VistaGestionarReservas();
        VistaGestionarHuespedes vistaGestionarHuespedes = new VistaGestionarHuespedes();
        VistaReportes vistaReportes = new VistaReportes();
        VistaPersonalizar VistaPersonalizar = new VistaPersonalizar();
        Estilo estilo = new Estilo();
        VistaInicioSesion vistaInicioSesion = new VistaInicioSesion();
        VistaCrearModificarHabitacion vistaCrearModificarHabitacion = new VistaCrearModificarHabitacion();
        VistaCrearModificarReserva vistaCrearModificarReserva = new VistaCrearModificarReserva();
        VistaCrearModificarHuesped vistaCrearModificarHuesped = new VistaCrearModificarHuesped();


        new ControlBotones(vistaRegistrarse,modeloDatos,vistaVerUsuarios,vistaModificarUsuario, vistaPaginaPrincipal, gestionarHabitaciones,vistaGestionarRes
        , vistaGestionarHuespedes, vistaReportes, VistaPersonalizar, estilo, vistaInicioSesion, vistaCrearModificarHabitacion, vistaCrearModificarReserva,
                vistaCrearModificarHuesped);
    }
}
