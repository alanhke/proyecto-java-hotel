package HotelProyectoFinal.vistas;

import javax.swing.*;
import java.awt.*;
import com.formdev.flatlaf.FlatLaf;

public class VistaVerPerfil extends JPanel {
    private JLabel lblImagenPerfil;

    private JLabel lblNombre;
    private JLabel lblApellido;
    private JLabel lblUsuario;
    private JLabel lblGenero;
    private JLabel lblRol;

    private JTextField tfNombre;
    private JTextField tfApellido;
    private JTextField tfUsuario;
    private JTextField tfGenero;
    private JTextField tfRol;

    public VistaVerPerfil() {
        setBackground(UIManager.getColor("Panel.background"));
        setLayout(new BorderLayout(20, 20));
        setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        // Panel Imagen Perfil a la izquierda
        JPanel panelImagen = new JPanel();
        panelImagen.setBackground(UIManager.getColor("Panel.background"));
        panelImagen.setPreferredSize(new Dimension(180, 220));
        panelImagen.setLayout(new BorderLayout());

        lblImagenPerfil = new JLabel();
        lblImagenPerfil.setHorizontalAlignment(JLabel.CENTER);
        lblImagenPerfil.setVerticalAlignment(JLabel.CENTER);

        // Icono por defecto: monito gris (típico icono de info)
        ImageIcon iconPerfil = (ImageIcon) UIManager.getIcon("OptionPane.informationIcon");
        lblImagenPerfil.setIcon(iconPerfil);
        panelImagen.add(lblImagenPerfil, BorderLayout.CENTER);

        // Panel Datos a la derecha
        JPanel panelDatos = new JPanel();
        panelDatos.setBackground(UIManager.getColor("Panel.background"));
        panelDatos.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        lblNombre = new JLabel("Nombre(s):");
        lblApellido = new JLabel("Apellido(s):");
        lblUsuario = new JLabel("Nombre de usuario:");
        lblGenero = new JLabel("Género:");
        lblRol = new JLabel("Rol:");

        Font labelFont = UIManager.getFont("Label.font").deriveFont(Font.BOLD, 14f);
        lblNombre.setFont(labelFont);
        lblApellido.setFont(labelFont);
        lblUsuario.setFont(labelFont);
        lblGenero.setFont(labelFont);
        lblRol.setFont(labelFont);

        tfNombre = crearCampoTexto();
        tfApellido = crearCampoTexto();
        tfUsuario = crearCampoTexto();
        tfGenero = crearCampoTexto();
        tfRol = crearCampoTexto();

        // Agregar componentes con GridBagLayout
        gbc.gridx = 0; gbc.gridy = 0;
        panelDatos.add(lblNombre, gbc);
        gbc.gridx = 1;
        panelDatos.add(tfNombre, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panelDatos.add(lblApellido, gbc);
        gbc.gridx = 1;
        panelDatos.add(tfApellido, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panelDatos.add(lblUsuario, gbc);
        gbc.gridx = 1;
        panelDatos.add(tfUsuario, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        panelDatos.add(lblGenero, gbc);
        gbc.gridx = 1;
        panelDatos.add(tfGenero, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        panelDatos.add(lblRol, gbc);
        gbc.gridx = 1;
        panelDatos.add(tfRol, gbc);

        add(panelImagen, BorderLayout.WEST);
        add(panelDatos, BorderLayout.CENTER);
    }

    private JTextField crearCampoTexto() {
        JTextField tf = new JTextField(20);
        tf.setFont(UIManager.getFont("TextField.font"));
        tf.setEditable(false);
        tf.setBackground(UIManager.getColor("TextField.background"));
        tf.setBorder(BorderFactory.createEmptyBorder(6, 8, 6, 8));
        return tf;
    }

    // Métodos para llenar los campos con datos
    public void setNombre(String nombre) {
        tfNombre.setText(nombre);
    }

    public void setApellido(String apellido) {
        tfApellido.setText(apellido);
    }

    public void setUsuario(String usuario) {
        tfUsuario.setText(usuario);
    }

    public void setGenero(String genero) {
        tfGenero.setText(genero);
    }

    public void setRol(String rol) {
        tfRol.setText(rol);
    }

    // Método para cambiar imagen de perfil (si quieres usar imagen real)
    public void setImagenPerfil(ImageIcon icon) {
        lblImagenPerfil.setIcon(icon);
    }
}
