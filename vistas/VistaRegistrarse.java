package HotelProyectoFinal.vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VistaRegistrarse extends JPanel {
    JTextField tfUserName;
    JTextField tfName;
    JTextField tfLastName;
    JPasswordField tfContra;
    JPasswordField tfFinalPassword;
    ButtonGroup bgGender;
    JButton btnOk;
    JButton btnCancel;
    JButton btnRegresar;
    JRadioButton rbGender1;
    JRadioButton rbGender2;
    JRadioButton rbGender3;
    JComboBox<String> Opciones;

    public VistaRegistrarse() {
        setLayout(new BorderLayout());
        setBackground(new Color(255, 255, 255, 230));
        JPanel panelIzquierdo = new JPanel();
        panelIzquierdo.setLayout(new BoxLayout(panelIzquierdo, BoxLayout.Y_AXIS));
        panelIzquierdo.setBackground(new Color(240, 244, 248));
        panelIzquierdo.setPreferredSize(new Dimension(300, 330));
        JLabel lblBienvenido = new JLabel("<html><center>¡Bienvenido!<br>Regístrate para continuar</center></html>", JLabel.CENTER);
        lblBienvenido.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblBienvenido.setForeground(new Color(51, 51, 51));
        lblBienvenido.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblBienvenido.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        panelIzquierdo.add(lblBienvenido);

        try {
            ImageIcon icon = new ImageIcon(getClass().getResource("/hotelEntrada.png"));
            JLabel lblImagen = new JLabel(icon);
            lblImagen.setAlignmentX(Component.CENTER_ALIGNMENT);
            panelIzquierdo.add(lblImagen);
        } catch (Exception e) {
            JLabel error = new JLabel("Imagen no encontrada");
            error.setAlignmentX(Component.CENTER_ALIGNMENT);
            panelIzquierdo.add(error);
        }

        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBackground(new Color(255, 255, 255, 230));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        panelFormulario.add(new JLabel("Nombre(s): "), gbc);
        tfName = new JTextField(10);
        gbc.gridx = 1;
        panelFormulario.add(tfName, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panelFormulario.add(new JLabel("Apellido(s): "), gbc);
        tfLastName = new JTextField(10);
        gbc.gridx = 1;
        panelFormulario.add(tfLastName, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panelFormulario.add(new JLabel("Nombre de usuario: "), gbc);
        tfUserName = new JTextField(10);
        gbc.gridx = 1;
        panelFormulario.add(tfUserName, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        panelFormulario.add(new JLabel("Contraseña: "), gbc);
        tfContra = new JPasswordField(10);
        gbc.gridx = 1;
        panelFormulario.add(tfContra, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        panelFormulario.add(new JLabel("Confirmar contraseña: "), gbc);
        tfFinalPassword = new JPasswordField(10);
        gbc.gridx = 1;
        panelFormulario.add(tfFinalPassword, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        panelFormulario.add(new JLabel("Género: "), gbc);
        JPanel panelGenero = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        rbGender1 = new JRadioButton("Mujer");
        rbGender2 = new JRadioButton("Hombre");
        rbGender3 = new JRadioButton("Otro");
        bgGender = new ButtonGroup();
        bgGender.add(rbGender1);
        bgGender.add(rbGender2);
        bgGender.add(rbGender3);
        panelGenero.add(rbGender1);
        panelGenero.add(rbGender2);
        panelGenero.add(rbGender3);
        panelGenero.setOpaque(false);
        gbc.gridx = 1;
        panelFormulario.add(panelGenero, gbc);

        gbc.gridx = 0; gbc.gridy = 6;
        panelFormulario.add(new JLabel("Rol: "), gbc);
        Opciones = new JComboBox<>(new String[]{"Administrador", "Recepcionista"});
        gbc.gridx = 1;
        panelFormulario.add(Opciones, gbc);

        gbc.gridx = 0; gbc.gridy = 7; gbc.gridwidth = 2;
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        btnOk = new JButton("Registrarse");
        btnOk.setBackground(new Color(74, 144, 226));
        btnOk.setForeground(Color.WHITE);
        btnCancel = new JButton("Limpiar");
        btnCancel.setBackground(new Color(208, 2, 27));
        btnCancel.setForeground(Color.WHITE);
        btnRegresar = new JButton("Regresar");
        btnRegresar.setBackground(new Color(150, 150, 150));
        btnRegresar.setForeground(Color.WHITE);
        Font fontBoton = new Font("SansSerif", Font.BOLD, 14);
        btnOk.setFont(fontBoton);
        btnCancel.setFont(fontBoton);
        btnRegresar.setFont(fontBoton);
        panelBotones.setOpaque(false);
        panelBotones.add(btnOk);
        panelBotones.add(btnCancel);
        panelBotones.add(btnRegresar);
        panelFormulario.add(panelBotones, gbc);
        add(panelIzquierdo, BorderLayout.WEST);
        add(panelFormulario, BorderLayout.CENTER);
    }

    public void setListeners(ActionListener listener) {
        btnOk.addActionListener(listener);
        btnCancel.addActionListener(listener);
        btnRegresar.addActionListener(listener);
    }

    public String getBotonPresionado(JButton boton) {
        if (boton == btnOk) return "ok";
        if (boton == btnCancel) return "cancel";
        if (boton == btnRegresar) return "regresar";
        return null;
    }

    public JTextField getTfUserName() { return tfUserName; }
    public JPasswordField getTfContra() { return tfContra; }
    public JPasswordField getTfFinalPassword() { return tfFinalPassword; }
    public JTextField getTfName() { return tfName; }
    public JTextField getTfLastName() { return tfLastName; }
    public String getNombre() { return tfName.getText(); }
    public String getApellido() { return tfLastName.getText(); }
    public String getNombreUsuario() { return tfUserName.getText(); }
    public String getPassword() { return new String(tfContra.getPassword()); }
    public String getFinalPassword() { return new String(tfFinalPassword.getPassword()); }
    public String getGender() {
        if (rbGender1.isSelected()) return "Mujer";
        if (rbGender2.isSelected()) return "Hombre";
        if (rbGender3.isSelected()) return "Otro";
        return "";
    }
    public ButtonGroup getBgGender() {
        return bgGender;
    }

    public JComboBox<String> getComboRol() {
        return Opciones;
    }
    public String getOpcion() {
        return (String) Opciones.getSelectedItem();
    }

    public String getTextVacio(String nombre, String contra, String finalPassword, String firstName, String lastName) {
        if (nombre.isBlank()) return "Nombre de usuario";
        if (contra.isBlank()) return "Contraseña";
        if (finalPassword.isBlank()) return "Confirmar contraseña";
        if (firstName.isBlank()) return "Nombres";
        if (lastName.isBlank()) return "Apellidos";
        if (!rbGender1.isSelected() && !rbGender2.isSelected() && !rbGender3.isSelected()) return "Género";
        return null;
    }
}





