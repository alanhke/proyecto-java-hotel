package HotelProyectoFinal.vistas;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VistaPaginaPrincipal extends JPanel {
    JButton btnEstadisticas;
    JButton btnHistorial;
    JButton btnPerfil;
    JButton btnCerrarSesion;
    JButton btnGestionar;
    JButton btnReservas;
    JButton btnHuespedes;
    JButton btnReportes;
    DefaultPieDataset graficaPie;

    public VistaPaginaPrincipal() {
        setLayout(null);
        setBackground(new Color(245, 245, 250)); // Fondo suave

        // Título
        JLabel titulo = new JLabel("Sistema de Gestión Hotelera", JLabel.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 28));
        titulo.setBounds(200, 20, 600, 40);
        add(titulo);

        // Panel Izquierdo con botones de navegación
        JPanel panelIzquierdo = new JPanel(new GridLayout(3, 1, 10, 10));
        panelIzquierdo.setBounds(50, 120, 160, 140);
        panelIzquierdo.setBackground(new Color(245, 245, 250));

        btnEstadisticas = new JButton("Ver Estadísticas");
        btnHistorial = new JButton("Ver Historial");
        btnPerfil = new JButton("Ver Perfil");

        panelIzquierdo.add(btnEstadisticas);
        panelIzquierdo.add(btnHistorial);
        panelIzquierdo.add(btnPerfil);

        add(panelIzquierdo);

        // Botón de cerrar sesión abajo a la izquierda
        btnCerrarSesion = new JButton("Cerrar Sesión");
        btnCerrarSesion.setBounds(50, 300, 160, 30);
        add(btnCerrarSesion);

        // Panel Derecho con 4 botones verticales
        JPanel panelDerecho = new JPanel(new GridLayout(4, 1, 10, 10));
        panelDerecho.setBounds(760, 120, 200, 180);
        panelDerecho.setBackground(new Color(245, 245, 250));

        btnGestionar = new JButton("Gestionar Habitaciones");
        btnReservas = new JButton("Gestionar Reservas");
        btnHuespedes = new JButton("Gestión de Huéspedes");
        btnReportes = new JButton("Ver Reportes");

        Font btnFont = new Font("SansSerif", Font.PLAIN, 14);
        btnGestionar.setFont(btnFont);
        btnReservas.setFont(btnFont);
        btnHuespedes.setFont(btnFont);
        btnReportes.setFont(btnFont);

        panelDerecho.add(btnGestionar);
        panelDerecho.add(btnReservas);
        panelDerecho.add(btnHuespedes);
        panelDerecho.add(btnReportes);

        add(panelDerecho);

        // Pie de página
        JLabel pie = new JLabel("© 2025 HotelSuite - Todos los derechos reservados", JLabel.CENTER);
        pie.setFont(new Font("SansSerif", Font.ITALIC, 12));
        pie.setBounds(250, 340, 500, 30);
        add(pie);

        // Crear gráfica de pastel
        graficaPie = new DefaultPieDataset();
        graficaPie.setValue("Ocupadas", 12);
        graficaPie.setValue("Disponibles", 8);

        JFreeChart chart = ChartFactory.createPieChart(
                "Estado de Habitaciones",
                graficaPie,
                true,
                true,
                false
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBounds(250, 100, 500, 220);
        chartPanel.setBackground(new Color(245, 245, 250));
        add(chartPanel);
    }

    public void setListeners(ActionListener listener) {
        btnEstadisticas.addActionListener(listener);
        btnHistorial.addActionListener(listener);
        btnPerfil.addActionListener(listener);
        btnCerrarSesion.addActionListener(listener);
        btnGestionar.addActionListener(listener);
        btnReservas.addActionListener(listener);
        btnHuespedes.addActionListener(listener);
        btnReportes.addActionListener(listener);
    }
}
