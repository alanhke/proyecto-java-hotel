package HotelProyectoFinal.vistas;

import HotelProyectoFinal.utilities.RoundedPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VistaInicioSesion extends JPanel {
    private JTextField campoUsuario;
    private JPasswordField campoContrasena;
    private JButton botonIniciar;
    private JButton botonRegistrarse;

    public VistaInicioSesion() {
        setLayout(new BorderLayout());

        PanelConFondo background = new PanelConFondo("/hotel.png");

        RoundedPanel loginPanel = new RoundedPanel(30);
        loginPanel.setPreferredSize(new Dimension(380, 320));
        loginPanel.setBackground(new Color(255, 255, 255, 220));
        loginPanel.setLayout(null);

        JLabel etiquetaTitulo = new JLabel("Inicio de Sesión");
        etiquetaTitulo.setFont(new Font("SansSerif", Font.BOLD, 22));
        etiquetaTitulo.setBounds(110, 20, 200, 30);
        loginPanel.add(etiquetaTitulo);

        JLabel etiquetaUsuario = new JLabel("Usuario:");
        etiquetaUsuario.setBounds(40, 80, 80, 25);
        loginPanel.add(etiquetaUsuario);

        campoUsuario = new JTextField();
        campoUsuario.setBounds(130, 80, 200, 25);
        loginPanel.add(campoUsuario);

        JLabel etiquetaContrasena = new JLabel("Contraseña:");
        etiquetaContrasena.setBounds(40, 120, 80, 25);
        loginPanel.add(etiquetaContrasena);

        campoContrasena = new JPasswordField();
        campoContrasena.setBounds(130, 120, 200, 25);
        loginPanel.add(campoContrasena);

        botonIniciar = new JButton("Iniciar Sesión");
        botonIniciar.setBounds(130, 170, 200, 30);
        botonIniciar.setBackground(new Color(46, 204, 113)); // Verde suave
        botonIniciar.setForeground(Color.WHITE);             // Texto blanco
        botonIniciar.setFocusPainted(false);                 // Sin borde de enfoque
        botonIniciar.setFont(new Font("SansSerif", Font.BOLD, 14));
        loginPanel.add(botonIniciar);

        botonRegistrarse = new JButton("Dar de alta");
        botonRegistrarse.setBounds(130, 210, 200, 30);
        botonRegistrarse.setBackground(new Color(52, 152, 219)); // Azul suave
        botonRegistrarse.setForeground(Color.WHITE);             // Texto blanco
        botonRegistrarse.setFocusPainted(false);                 // Sin borde de enfoque
        botonRegistrarse.setFont(new Font("SansSerif", Font.BOLD, 14));
        loginPanel.add(botonRegistrarse);

        background.add(loginPanel);
        add(background, BorderLayout.CENTER);
    }

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

    public void setListeners(java.awt.event.ActionListener listener) {
        botonIniciar.addActionListener(listener);
        botonRegistrarse.addActionListener(listener);
    }
}

class PanelConFondo extends JPanel {
    private Image imagenFondo;

    public PanelConFondo(String rutaImagen) {
        try {
            imagenFondo = new ImageIcon(getClass().getResource(rutaImagen)).getImage();
        } catch (Exception e) {
            System.err.println("No se encontró la imagen: " + rutaImagen);
        }
        setLayout(new GridBagLayout()); // para centrar el panel login
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagenFondo != null) {
            g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
        }
    }
}

