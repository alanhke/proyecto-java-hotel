package HotelProyectoFinal.vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VistaInicioSesion extends JPanel {

    private JTextField campoUsuario;
    private JPasswordField campoContrasena;
    private JButton botonIniciar;
    private JButton botonRegistrarse;

    public VistaInicioSesion() {
        initComponentes();
    }

    private void initComponentes() {
        Color fondo = Color.WHITE;
        Color colorBoton = new Color(60, 120, 200);
        Color colorBotonRegistro = new Color(80, 180, 120);

        setLayout(new BorderLayout());
        setBackground(fondo);

        // Panel Izquierdo (Login)
        JPanel panelLogin = new JPanel();
        panelLogin.setLayout(null);
        panelLogin.setBackground(fondo);
        panelLogin.setPreferredSize(new Dimension(400, 400));

        JLabel etiquetaTitulo = new JLabel("Inicio de Sesión");
        etiquetaTitulo.setFont(new Font("SansSerif", Font.BOLD, 22));
        etiquetaTitulo.setBounds(100, 30, 250, 30);
        panelLogin.add(etiquetaTitulo);

        JLabel etiquetaUsuario = new JLabel("Usuario:");
        etiquetaUsuario.setBounds(50, 100, 80, 25);
        panelLogin.add(etiquetaUsuario);

        campoUsuario = new JTextField();
        campoUsuario.setBounds(150, 100, 200, 25);
        panelLogin.add(campoUsuario);

        JLabel etiquetaContrasena = new JLabel("Contraseña:");
        etiquetaContrasena.setBounds(50, 140, 80, 25);
        panelLogin.add(etiquetaContrasena);

        campoContrasena = new JPasswordField();
        campoContrasena.setBounds(150, 140, 200, 25);
        panelLogin.add(campoContrasena);

        botonIniciar = new JButton("Iniciar Sesión");
        botonIniciar.setBounds(150, 190, 200, 30);
        botonIniciar.setBackground(colorBoton);
        botonIniciar.setForeground(Color.WHITE);
        botonIniciar.setFocusPainted(false);
        panelLogin.add(botonIniciar);

        // Línea vertical separadora
        JSeparator linea = new JSeparator(SwingConstants.VERTICAL);
        linea.setForeground(Color.GRAY);
        linea.setPreferredSize(new Dimension(1, 400));

        // Panel Derecho (Registro)
        JPanel panelDerecho = new JPanel(new BorderLayout());
        panelDerecho.setBackground(fondo);
        panelDerecho.setPreferredSize(new Dimension(400, 400));

        JPanel panelContenido = new JPanel();
        panelContenido.setBackground(fondo);
        panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));
        panelContenido.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        JLabel textoRegistro = new JLabel("¿Aún no eres usuario de nuestro hotel?");
        textoRegistro.setFont(new Font("SansSerif", Font.PLAIN, 16));
        textoRegistro.setAlignmentX(Component.CENTER_ALIGNMENT);

        ImageIcon icono = new ImageIcon("img/hotel.png");
        Image imagenEscalada = icono.getImage().getScaledInstance(280, 160, Image.SCALE_SMOOTH);
        JLabel etiquetaImagen = new JLabel(new ImageIcon(imagenEscalada));
        etiquetaImagen.setAlignmentX(Component.CENTER_ALIGNMENT);

        botonRegistrarse = new JButton("Registrarse");
        botonRegistrarse.setBackground(colorBotonRegistro);
        botonRegistrarse.setForeground(Color.WHITE);
        botonRegistrarse.setFocusPainted(false);
        botonRegistrarse.setPreferredSize(new Dimension(160, 40));
        botonRegistrarse.setMaximumSize(new Dimension(160, 40));
        botonRegistrarse.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelContenido.add(textoRegistro);
        panelContenido.add(Box.createVerticalStrut(20));
        panelContenido.add(etiquetaImagen);
        panelContenido.add(Box.createVerticalStrut(20));
        panelContenido.add(botonRegistrarse);

        panelDerecho.add(panelContenido, BorderLayout.CENTER);

        add(panelLogin, BorderLayout.WEST);
        add(linea, BorderLayout.CENTER);
        add(panelDerecho, BorderLayout.EAST);
    }

    // Getters
    public String getUsuario() {
        return campoUsuario.getText();
    }

    public String getContrasena() {
        return new String(campoContrasena.getPassword());
    }

    public JButton getBotonIniciar() {
        return botonIniciar;
    }

    public JButton getBotonRegistrarse() {
        return botonRegistrarse;
    }

    public void setListeners(ActionListener listener) {
        botonIniciar.addActionListener(listener);
        botonRegistrarse.addActionListener(listener);
    }
}

