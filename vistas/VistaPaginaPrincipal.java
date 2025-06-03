package HotelProyectoFinal.vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

public class VistaPaginaPrincipal extends JPanel {
    public JButton btnPersonalizar;
    public JButton btnPerfil;
    public JButton btnCerrarSesion;
    public JButton btnGestionar;
    public JButton btnReservas;
    public JButton btnHuespedes;
    public JButton btnReportes;
    private DefaultPieDataset graficaPie;
    private JFreeChart chart;

    public VistaPaginaPrincipal() {
        setLayout(new BorderLayout());
        setBackground(new Color(245, 245, 250));

        JLabel titulo = new JLabel("Sistema de Gestión Hotelera", JLabel.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 28));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        add(titulo, BorderLayout.NORTH);

        JPanel panelCentro = new JPanel(new BorderLayout());
        panelCentro.setBackground(new Color(245, 245, 250));
        add(panelCentro, BorderLayout.CENTER);

        // Panel izquierdo con botones
        JPanel panelIzquierdo = new JPanel();
        panelIzquierdo.setLayout(new BoxLayout(panelIzquierdo, BoxLayout.Y_AXIS));
        panelIzquierdo.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 10));
        panelIzquierdo.setBackground(new Color(245, 245, 250));

        // Colores personalizados
        Color rojoImportante = new Color(230, 57, 70);
        Color azulPrimario = new Color(30, 136, 229);
        Color verdeSecundario = new Color(76, 175, 80);
        Color blanco = Color.WHITE;

        btnPersonalizar = new JButton("🎨 Personalizar");
        btnPerfil = new JButton("👤 Ver Perfil");
        btnCerrarSesion = new JButton("⏻ Cerrar Sesión");

        configurarBoton(btnPersonalizar, verdeSecundario, blanco);
        configurarBoton(btnPerfil, verdeSecundario, blanco);
        configurarBoton(btnCerrarSesion, rojoImportante, blanco);

        btnCerrarSesion.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelIzquierdo.add(Box.createVerticalStrut(10));
        panelIzquierdo.add(btnPersonalizar);
        panelIzquierdo.add(Box.createVerticalStrut(10));
        panelIzquierdo.add(btnPerfil);
        panelIzquierdo.add(Box.createVerticalGlue());
        panelIzquierdo.add(btnCerrarSesion);

        panelCentro.add(panelIzquierdo, BorderLayout.WEST);

        // Panel derecho con botones
        JPanel panelDerecho = new JPanel();
        panelDerecho.setLayout(new BoxLayout(panelDerecho, BoxLayout.Y_AXIS));
        panelDerecho.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 20));
        panelDerecho.setBackground(new Color(245, 245, 250));

        btnGestionar = new JButton("🛏 Gestionar Habitaciones");
        btnReservas = new JButton("📅 Gestionar Reservas");
        btnHuespedes = new JButton("🧳 Gestión de Huéspedes");
        btnReportes = new JButton("📊 Ver Reportes");

        configurarBoton(btnGestionar, azulPrimario, blanco);
        configurarBoton(btnReservas, azulPrimario, blanco);
        configurarBoton(btnHuespedes, azulPrimario, blanco);
        configurarBoton(btnReportes, azulPrimario, blanco);

        panelDerecho.add(btnGestionar);
        panelDerecho.add(Box.createVerticalStrut(10));
        panelDerecho.add(btnReservas);
        panelDerecho.add(Box.createVerticalStrut(10));
        panelDerecho.add(btnHuespedes);
        panelDerecho.add(Box.createVerticalStrut(10));
        panelDerecho.add(btnReportes);

        panelCentro.add(panelDerecho, BorderLayout.EAST);

        // Gráfica en el centro
        graficaPie = new DefaultPieDataset();
        chart = ChartFactory.createPieChart(
                "Estado de Habitaciones",
                graficaPie,
                true,
                true,
                false
        );
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBackground(new Color(245, 245, 250));
        panelCentro.add(chartPanel, BorderLayout.CENTER);

        // Pie de página
        JLabel pie = new JLabel("© 2025 HotelSuite - Todos los derechos reservados", JLabel.CENTER);
        pie.setFont(new Font("SansSerif", Font.ITALIC, 12));
        pie.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(pie, BorderLayout.SOUTH);
    }

    private void configurarBoton(JButton boton, Color bgColor, Color fgColor) {
        boton.setFocusPainted(false);
        boton.setFont(new Font("SansSerif", Font.PLAIN, 14));
        boton.setBackground(bgColor);
        boton.setForeground(fgColor);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setOpaque(true);
        boton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    }

    // Igual que en VistaRegistrarse, para asignar un listener a todos los botones
    public void setListeners(ActionListener listener) {
        btnPersonalizar.addActionListener(listener);
        btnPerfil.addActionListener(listener);
        btnCerrarSesion.addActionListener(listener);
        btnGestionar.addActionListener(listener);
        btnReservas.addActionListener(listener);
        btnHuespedes.addActionListener(listener);
        btnReportes.addActionListener(listener);
    }

    public String getBotonPresionado(JButton boton) {
        if (boton == btnPersonalizar) return "personalizar";
        else if (boton == btnPerfil) return "perfil";
        else if (boton == btnCerrarSesion) return "cerrarSesion";
        else if (boton == btnGestionar) return "gestionar";
        else if (boton == btnReservas) return "reservas";
        else if (boton == btnHuespedes) return "huespedes";
        else if (boton == btnReportes) return "reportes";
        return null;
    }

    public void cargarGrafica(int ocupadas, int disponibles, int enLimpieza) {
        graficaPie.setValue("Ocupadas", ocupadas);
        graficaPie.setValue("Disponibles", disponibles);
        graficaPie.setValue("En Limpieza", enLimpieza);
    }
}
