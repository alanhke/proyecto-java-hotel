package HotelProyectoFinal.vistas;

import HotelProyectoFinal.utilities.RoundedPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VistaVerPerfil extends JPanel {

    private JLabel imagenPerfil;
    private JLabel lblNombre, lblApellidos, lblUsuario, lblContrasena, lblGenero, lblTipo;
    private JLabel valorNombre, valorApellidos, valorUsuario, valorContrasena, valorGenero, valorTipo;
    private JButton botonVolver;
    private JButton botonEditar;
    private JButton botonEliminar;

    public VistaVerPerfil() {
        setLayout(new BorderLayout());
        PanelConFondo fondo = new PanelConFondo("/hotelNoche.png");
        fondo.setLayout(new GridBagLayout());
        add(fondo, BorderLayout.CENTER);
        RoundedPanel panelPrincipal = new RoundedPanel(30);
        panelPrincipal.setPreferredSize(new Dimension(420, 700));
        panelPrincipal.setBackground(new Color(255, 255, 255, 230));
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        imagenPerfil = new JLabel();
        imagenPerfil.setHorizontalAlignment(SwingConstants.CENTER);
        imagenPerfil.setPreferredSize(new Dimension(150, 150));
        ImageIcon iconoUsuario = new ImageIcon(getClass().getResource("/perfil.png"));
        Image imgEscalada = iconoUsuario.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        imagenPerfil.setIcon(new ImageIcon(imgEscalada));
        imagenPerfil.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        panelPrincipal.add(imagenPerfil, BorderLayout.NORTH);
        JPanel panelDatos = new JPanel(new GridLayout(6, 2, 10, 10));
        panelDatos.setOpaque(false);

        lblNombre = new JLabel("Nombre:");
        lblApellidos = new JLabel("Apellidos:");
        lblUsuario = new JLabel("Usuario:");
        lblContrasena = new JLabel("Contraseña:");
        lblGenero = new JLabel("Género:");
        lblTipo = new JLabel("Tipo:");
        valorNombre = new JLabel("");
        valorApellidos = new JLabel("");
        valorUsuario = new JLabel("");
        valorContrasena = new JLabel("");
        valorGenero = new JLabel("");
        valorTipo = new JLabel("");
        Font fuenteEtiquetas = new Font("SansSerif", Font.BOLD, 16);
        Font fuenteValores = new Font("SansSerif", Font.PLAIN, 16);
        JLabel[] etiquetas = {lblNombre, lblApellidos, lblUsuario, lblContrasena, lblGenero, lblTipo};
        JLabel[] valores = {valorNombre, valorApellidos, valorUsuario, valorContrasena, valorGenero, valorTipo};
        for (JLabel etiqueta : etiquetas) {
            etiqueta.setFont(fuenteEtiquetas);
            etiqueta.setForeground(new Color(50, 50, 50));
        }
        for (JLabel valor : valores) {
            valor.setFont(fuenteValores);
            valor.setForeground(new Color(80, 80, 80));
        }
        panelDatos.add(lblNombre); panelDatos.add(valorNombre);
        panelDatos.add(lblApellidos); panelDatos.add(valorApellidos);
        panelDatos.add(lblUsuario); panelDatos.add(valorUsuario);
        panelDatos.add(lblContrasena); panelDatos.add(valorContrasena);
        panelDatos.add(lblGenero); panelDatos.add(valorGenero);
        panelDatos.add(lblTipo); panelDatos.add(valorTipo);
        panelPrincipal.add(panelDatos, BorderLayout.CENTER);
        botonEditar = new JButton("✏️ Editar");
        botonEditar.setPreferredSize(new Dimension(120, 40));
        botonEditar.setFont(new Font("SansSerif", Font.BOLD, 14));
        botonEditar.setBackground(new Color(46, 204, 113));
        botonEditar.setForeground(Color.WHITE);
        botonEditar.setFocusPainted(false);
        botonVolver = new JButton("🔙 Volver");
        botonVolver.setPreferredSize(new Dimension(120, 40));
        botonVolver.setFont(new Font("SansSerif", Font.BOLD, 14));
        botonVolver.setBackground(new Color(52, 152, 219));
        botonVolver.setForeground(Color.WHITE);
        botonVolver.setFocusPainted(false);
        botonEliminar = new JButton("🗑️ Eliminar perfil");
        botonEliminar.setPreferredSize(new Dimension(180, 40));
        botonEliminar.setFont(new Font("SansSerif", Font.BOLD, 14));
        botonEliminar.setBackground(new Color(231, 76, 60));
        botonEliminar.setForeground(Color.WHITE);
        botonEliminar.setFocusPainted(false);
        JPanel panelBotones = new JPanel();
        panelBotones.setOpaque(false);
        panelBotones.setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0)); // Más espacio abajo
        panelBotones.add(botonEditar);
        panelBotones.add(Box.createRigidArea(new Dimension(15, 0)));
        panelBotones.add(botonVolver);
        panelBotones.add(Box.createRigidArea(new Dimension(15, 0)));
        panelBotones.add(botonEliminar);

        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(40, 20, 40, 20);
        fondo.add(panelPrincipal, gbc);
    }

    public void setDatos(String nombre, String apellidos, String usuario, String contrasena, String genero, String tipo) {
        valorNombre.setText(nombre);
        valorApellidos.setText(apellidos);
        valorUsuario.setText(usuario);
        valorContrasena.setText(contrasena);
        valorGenero.setText(genero);
        valorTipo.setText(tipo);
    }

    public JButton getBotonVolver() {
        return botonVolver;
    }

    public JButton getBotonEditar() {
        return botonEditar;
    }

    public JButton getBotonEliminar() {
        return botonEliminar;
    }

    public void setListeners(ActionListener listener) {
        botonVolver.addActionListener(listener);
        botonEditar.addActionListener(listener);
        botonEliminar.addActionListener(listener);
    }
}





