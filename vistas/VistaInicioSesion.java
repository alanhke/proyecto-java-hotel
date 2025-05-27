package HotelProyectoFinal.vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VistaInicioSesion extends JFrame {

    private JTextField campoUsuario;
    private JPasswordField campoContrasena;
    private JButton botonIniciar;
    private JButton botonRegistrarse;

    public VistaInicioSesion() {
        setTitle("HotelSys - Inicio de Sesión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        initComponentes();

        pack(); // Ajusta tamaño al contenido
        setLocationRelativeTo(null); // Centrar ventana
    }

    private void initComponentes() {
        Color colorLinea = Color.GRAY; // Línea visible
        Color fondo = Color.WHITE;

        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(fondo);

        // Panel izquierdo: login
        JPanel panelLogin = new JPanel(null);
        panelLogin.setBackground(fondo);
        panelLogin.setPreferredSize(new Dimension(400, 400));

        // Posiciones ajustadas un poco más a la izquierda
        JLabel etiquetaTitulo = new JLabel("Inicio de Sesión");
        etiquetaTitulo.setFont(new Font("SansSerif", Font.BOLD, 22));
        etiquetaTitulo.setBounds(50, 30, 250, 30);
        panelLogin.add(etiquetaTitulo);

        JLabel etiquetaUsuario = new JLabel("Usuario:");
        etiquetaUsuario.setBounds(20, 100, 80, 25);
        panelLogin.add(etiquetaUsuario);

        campoUsuario = new JTextField();
        campoUsuario.setBounds(110, 100, 200, 25);
        panelLogin.add(campoUsuario);

        JLabel etiquetaContrasena = new JLabel("Contraseña:");
        etiquetaContrasena.setBounds(20, 140, 80, 25);
        panelLogin.add(etiquetaContrasena);

        campoContrasena = new JPasswordField();
        campoContrasena.setBounds(110, 140, 200, 25);
        panelLogin.add(campoContrasena);

        botonIniciar = new JButton("Iniciar Sesión");
        botonIniciar.setBounds(110, 190, 200, 30);
        botonIniciar.setBackground(new Color(60, 120, 200));
        botonIniciar.setForeground(Color.WHITE);
        botonIniciar.setFocusPainted(false);
        panelLogin.add(botonIniciar);

        // Línea divisoria visible y fina
        JSeparator linea = new JSeparator(SwingConstants.VERTICAL);
        linea.setPreferredSize(new Dimension(2, 400));
        linea.setForeground(colorLinea);

        // Panel derecho
        JPanel panelDerecho = new JPanel(new BorderLayout());
        panelDerecho.setBackground(fondo);
        panelDerecho.setPreferredSize(new Dimension(400, 400));

        JLabel textoRegistro = new JLabel();
        textoRegistro.setText("<html><div style='text-align: left;'>¿Aún no eres usuario de nuestro hotel?</div></html>");
        textoRegistro.setFont(new Font("SansSerif", Font.PLAIN, 16));
        textoRegistro.setBorder(BorderFactory.createEmptyBorder(20, 40, 10, 40));
        textoRegistro.setMaximumSize(new Dimension(Integer.MAX_VALUE, textoRegistro.getPreferredSize().height));


        ImageIcon icono = new ImageIcon("img/hotel.png");
        Image imagenEscalada = icono.getImage().getScaledInstance(320, 180, Image.SCALE_SMOOTH);
        JLabel etiquetaImagen = new JLabel(new ImageIcon(imagenEscalada));
        etiquetaImagen.setAlignmentX(Component.CENTER_ALIGNMENT);
        etiquetaImagen.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));

        botonRegistrarse = new JButton("Registrarse");
        botonRegistrarse.setBackground(new Color(80, 180, 120));
        botonRegistrarse.setForeground(Color.WHITE);
        botonRegistrarse.setFocusPainted(false);
        botonRegistrarse.setPreferredSize(new Dimension(160, 40));

        JPanel contenedorBoton = new JPanel();
        contenedorBoton.setBackground(fondo);
        contenedorBoton.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        contenedorBoton.add(botonRegistrarse);

        JPanel panelContenido = new JPanel();
        panelContenido.setBackground(fondo);
        panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));
        panelContenido.add(textoRegistro);
        panelContenido.add(Box.createVerticalStrut(20));
        panelContenido.add(etiquetaImagen);
        panelContenido.add(Box.createVerticalStrut(20));
        panelContenido.add(contenedorBoton);

        panelDerecho.add(panelContenido, BorderLayout.CENTER);

        panelPrincipal.add(panelLogin, BorderLayout.WEST);
        panelPrincipal.add(linea, BorderLayout.CENTER);
        panelPrincipal.add(panelDerecho, BorderLayout.EAST);

        getContentPane().add(panelPrincipal);
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
