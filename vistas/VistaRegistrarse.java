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
    JButton verGuardados;
    JRadioButton rbGender1;
    JRadioButton rbGender2;
    JRadioButton rbGender3;
    JComboBox<String> Opciones;

    public VistaRegistrarse() {
        setLayout(null);
        setBackground(new Color(255, 255, 255, 230));

        // Panel de bienvenida (parte derecha)
        JPanel panelDerecho = new JPanel(null);
        panelDerecho.setBounds(400, 0, 300, 330);
        panelDerecho.setBackground(new Color(240, 244, 248)); // azul claro suave

        JLabel lblBienvenido = new JLabel("<html><center>¡Bienvenido!<br>Regístrate para continuar</center></html>");
        lblBienvenido.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblBienvenido.setForeground(new Color(51, 51, 51)); // texto oscuro
        lblBienvenido.setBounds(30, 20, 240, 60);

        try {
            ImageIcon icon = new ImageIcon(getClass().getResource("/hotel.png"));
            JLabel lblImagen = new JLabel(icon);
            lblImagen.setBounds(50, 100, 200, 200);
            panelDerecho.add(lblImagen);
        } catch (Exception e) {
            JLabel error = new JLabel("Imagen no encontrada");
            error.setBounds(50, 150, 200, 30);
            panelDerecho.add(error);
        }

        panelDerecho.add(lblBienvenido);
        add(panelDerecho);

        // Labels
        JLabel lblFirstName = new JLabel("Nombre(s): ");
        JLabel lblLastName = new JLabel("Apellido(s): ");
        JLabel lblName = new JLabel("Nombre de usuario: ");
        JLabel lblContra = new JLabel("Contraseña: ");
        JLabel lblFinalPassword = new JLabel("Confirmar contraseña: ");

        lblFirstName.setBounds(20, 20, 120, 25);
        tfName = new JTextField(10);
        tfName.setBounds(150, 20, 220, 25);

        lblLastName.setBounds(20, 55, 120, 25);
        tfLastName = new JTextField(10);
        tfLastName.setBounds(150, 55, 220, 25);

        lblName.setBounds(20, 90, 130, 25);
        tfUserName = new JTextField(10);
        tfUserName.setBounds(150, 90, 220, 25);

        lblContra.setBounds(20, 125, 120, 25);
        tfContra = new JPasswordField(10);
        tfContra.setBounds(150, 125, 220, 25);

        lblFinalPassword.setBounds(20, 160, 150, 25);
        tfFinalPassword = new JPasswordField(10);
        tfFinalPassword.setBounds(170, 160, 200, 25);

        // Género
        rbGender1 = new JRadioButton("Mujer");
        rbGender2 = new JRadioButton("Hombre");
        rbGender3 = new JRadioButton("Otro");
        bgGender = new ButtonGroup();
        bgGender.add(rbGender1);
        bgGender.add(rbGender2);
        bgGender.add(rbGender3);

        rbGender1.setBounds(20, 195, 100, 25);
        rbGender2.setBounds(120, 195, 100, 25);
        rbGender3.setBounds(220, 195, 100, 25);

        // Rol
        String[] opciones = {"Administrador", "Recepcionista"};
        Opciones = new JComboBox<>(opciones);
        Opciones.setBounds(150, 230, 220, 25);

        // Botones con colores llamativos
        btnOk = new JButton("Registrarse");
        btnOk.setBackground(new Color(74, 144, 226)); // azul brillante
        btnOk.setForeground(Color.WHITE);

        btnCancel = new JButton("Limpiar");
        btnCancel.setBackground(new Color(208, 2, 27)); // rojo claro
        btnCancel.setForeground(Color.WHITE);

        verGuardados = new JButton("Ver guardados");
        verGuardados.setBackground(new Color(100, 100, 100));
        verGuardados.setForeground(Color.WHITE);

        btnOk.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnCancel.setFont(new Font("SansSerif", Font.BOLD, 14));
        verGuardados.setFont(new Font("SansSerif", Font.BOLD, 14));

        btnOk.setBounds(20, 270, 120, 30);
        btnCancel.setBounds(150, 270, 120, 30);
        verGuardados.setBounds(280, 270, 120, 30);

        // Agregar componentes
        add(lblFirstName); add(tfName);
        add(lblLastName); add(tfLastName);
        add(lblName); add(tfUserName);
        add(lblContra); add(tfContra);
        add(lblFinalPassword); add(tfFinalPassword);

        add(rbGender1); add(rbGender2); add(rbGender3);
        add(Opciones);

        add(btnOk); add(btnCancel); add(verGuardados);
    }

    public void setListeners(ActionListener listener) {
        btnOk.addActionListener(listener);
        btnCancel.addActionListener(listener);
        verGuardados.addActionListener(listener);
    }

    public String getBotonPresionado(JButton boton) {
        if (boton == btnOk) return "ok";
        if (boton == btnCancel) return "cancel";
        if (boton == verGuardados) return "verGuardados";
        return null;
    }

    public JTextField getTfUserName() {
        return tfUserName;
    }

    public JPasswordField getTfContra() {
        return tfContra;
    }

    public JPasswordField getTfFinalPassword() {
        return tfFinalPassword;
    }

    public JTextField getTfName() {
        return tfName;
    }

    public JTextField getTfLastName() {
        return tfLastName;
    }

    public String getNombre() {
        return tfName.getText();
    }

    public String getApellido() {
        return tfLastName.getText();
    }

    public String getNombreUsuario() {
        return tfUserName.getText();
    }

    public String getPassword() {
        return new String(tfContra.getPassword());
    }

    public String getFinalPassword() {
        return new String(tfFinalPassword.getPassword());
    }

    public String getGender() {
        if (rbGender1.isSelected()) return "Mujer";
        if (rbGender2.isSelected()) return "Hombre";
        if (rbGender3.isSelected()) return "Otro";
        return "";
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
