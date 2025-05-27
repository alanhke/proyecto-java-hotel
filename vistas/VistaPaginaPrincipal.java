package HotelProyectoFinal.vistas;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VistaPaginaPrincipal extends JPanel {
    JButton btnHistorial;
    JButton btnPerfil;
    JButton btnCerrarSesion;
    JButton btnGestionar;
    JButton btnReservas;
    JButton btnHuespedes;
    JButton btnReportes;
    DefaultPieDataset graficaPie;

    public VistaPaginaPrincipal() {
        setLayout(new BorderLayout());
        setBackground(new Color(245, 245, 250));

        // ---------- Título superior ----------
        JLabel titulo = new JLabel("Sistema de Gestión Hotelera", JLabel.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 28));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        add(titulo, BorderLayout.NORTH);

        // ---------- Panel central ----------
        JPanel panelCentro = new JPanel(new BorderLayout());
        panelCentro.setBackground(new Color(245, 245, 250));
        add(panelCentro, BorderLayout.CENTER);

        // ---------- Panel izquierdo (navegación) ----------
        JPanel panelIzquierdo = new JPanel();
        panelIzquierdo.setLayout(new BoxLayout(panelIzquierdo, BoxLayout.Y_AXIS));
        panelIzquierdo.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 10));
        panelIzquierdo.setBackground(new Color(245, 245, 250));

        btnHistorial = new JButton("Ver Historial");
        btnPerfil = new JButton("Ver Perfil");

        btnCerrarSesion = new JButton("Cerrar Sesión");
        btnCerrarSesion.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelIzquierdo.add(Box.createVerticalStrut(10));
        panelIzquierdo.add(btnHistorial);
        panelIzquierdo.add(Box.createVerticalStrut(10));
        panelIzquierdo.add(btnPerfil);
        panelIzquierdo.add(Box.createVerticalGlue());
        panelIzquierdo.add(btnCerrarSesion);

        panelCentro.add(panelIzquierdo, BorderLayout.WEST);

        // ---------- Panel derecho (botones de gestión) ----------
        JPanel panelDerecho = new JPanel();
        panelDerecho.setLayout(new BoxLayout(panelDerecho, BoxLayout.Y_AXIS));
        panelDerecho.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 20));
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
        panelDerecho.add(Box.createVerticalStrut(10));
        panelDerecho.add(btnReservas);
        panelDerecho.add(Box.createVerticalStrut(10));
        panelDerecho.add(btnHuespedes);
        panelDerecho.add(Box.createVerticalStrut(10));
        panelDerecho.add(btnReportes);

        panelCentro.add(panelDerecho, BorderLayout.EAST);

        // ---------- Panel gráfico al centro ----------
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
        chartPanel.setBackground(new Color(245, 245, 250));
        panelCentro.add(chartPanel, BorderLayout.CENTER);

        // ---------- Pie ----------
        JLabel pie = new JLabel("© 2025 HotelSuite - Todos los derechos reservados", JLabel.CENTER);
        pie.setFont(new Font("SansSerif", Font.ITALIC, 12));
        pie.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(pie, BorderLayout.SOUTH);
    }

    public void setListeners(ActionListener listener) {
        btnHistorial.addActionListener(listener);
        btnPerfil.addActionListener(listener);
        btnCerrarSesion.addActionListener(listener);
        btnGestionar.addActionListener(listener);
        btnReservas.addActionListener(listener);
        btnHuespedes.addActionListener(listener);
        btnReportes.addActionListener(listener);
    }
}
