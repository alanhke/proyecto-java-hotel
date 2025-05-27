package HotelProyectoFinal.vistas;

import HotelProyectoFinal.controladores.ControlBotones;
import HotelProyectoFinal.modelos.DatosUsuario;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        /*JFrame ventana = new JFrame("Hotel Proyecto");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(500,500);
        VistaRegistrarse vistaRegistrarse = new VistaRegistrarse();
        DatosUsuario modeloDatos = new DatosUsuario(vistaRegistrarse);
        VistaVerUsuarios vistaVerUsuarios = new VistaVerUsuarios();
        VistaModificarUsuario vistaModificarUsuario = new VistaModificarUsuario();
        new ControlBotones(vistaRegistrarse,modeloDatos,vistaVerUsuarios,vistaModificarUsuario);
        ventana.add(vistaRegistrarse);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);*/
        JFrame ventana = new JFrame("Hotel Proyecto");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(1000,500);
        VistaInicioSesion ventanaPrincipal = new VistaInicioSesion();
        ventanaPrincipal.setVisible(true);
        ventana.add(ventanaPrincipal);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }
}
