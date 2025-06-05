package HotelProyectoFinal.vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VistaModificarUsuario extends JPanel {
    private final JTextField tfUserName;
    private final JTextField tfName;
    private final JTextField tfLastName;
    private final JPasswordField tfContra;
    private final JPasswordField tfFinalPassword;
    private final JRadioButton rbGender1, rbGender2, rbGender3;
    private final ButtonGroup bgGender;
    private final JComboBox<String> opciones;
    private final JButton btnOk, btnCancel;

    public VistaModificarUsuario() {
        setLayout(new BorderLayout(15, 15));
        setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        setBackground(new Color(250, 250, 250));

        JLabel titulo = new JLabel("Modificar Usuario");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 22));
        titulo.setForeground(new Color(33, 150, 243));
        add(titulo, BorderLayout.NORTH);

        JPanel formulario = new JPanel(new GridBagLayout());
        formulario.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        tfUserName = new JTextField(15);
        tfName = new JTextField(15);
        tfLastName = new JTextField(15);
        tfContra = new JPasswordField(15);
        tfFinalPassword = new JPasswordField(15);

        rbGender1 = new JRadioButton("Mujer");
        rbGender2 = new JRadioButton("Hombre");
        rbGender3 = new JRadioButton("Otro");
        bgGender = new ButtonGroup();
        bgGender.add(rbGender1);
        bgGender.add(rbGender2);
        bgGender.add(rbGender3);

        opciones = new JComboBox<>(new String[]{"Administrador", "Recepcionista"});

        addField(formulario, gbc, 0, "Nombre(s):", tfName);
        addField(formulario, gbc, 1, "Apellido(s):", tfLastName);
        addField(formulario, gbc, 2, "Nombre de usuario:", tfUserName);
        addField(formulario, gbc, 3, "Contraseña:", tfContra);
        addField(formulario, gbc, 4, "Confirmar contraseña:", tfFinalPassword);

        gbc.gridx = 0;
        gbc.gridy = 5;
        formulario.add(new JLabel("Género:"), gbc);
        JPanel panelGenero = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelGenero.setOpaque(false);
        panelGenero.add(rbGender1);
        panelGenero.add(rbGender2);
        panelGenero.add(rbGender3);
        gbc.gridx = 1;
        formulario.add(panelGenero, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        formulario.add(new JLabel("Tipo:"), gbc);
        gbc.gridx = 1;
        formulario.add(opciones, gbc);

        add(formulario, BorderLayout.CENTER);

        btnOk = crearBoton("Aceptar", new Color(76, 175, 80), Color.WHITE);
        btnCancel = crearBoton("Cancelar", new Color(244, 67, 54), Color.WHITE);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBotones.setOpaque(false);
        panelBotones.add(btnOk);
        panelBotones.add(btnCancel);

        add(panelBotones, BorderLayout.SOUTH);
    }

    private void addField(JPanel panel, GridBagConstraints gbc, int y, String labelText, JComponent input) {
        gbc.gridx = 0;
        gbc.gridy = y;
        panel.add(new JLabel(labelText), gbc);
        gbc.gridx = 1;
        panel.add(input, gbc);
    }

    private JButton crearBoton(String texto, Color fondo, Color textoColor) {
        JButton boton = new JButton(texto);
        boton.setFocusPainted(false);
        boton.setBackground(fondo);
        boton.setForeground(textoColor);
        boton.setFont(new Font("SansSerif", Font.BOLD, 14));
        boton.setPreferredSize(new Dimension(120, 35));
        return boton;
    }

    public void setListeners(ActionListener listener){
        btnOk.addActionListener(listener);
        btnCancel.addActionListener(listener);
    }

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

    public String getOpcion() {
        return (String) opciones.getSelectedItem();
    }

    public void setNombreUsuario(String nombre) { tfUserName.setText(nombre); }
    public void setNombre(String nombre) { tfName.setText(nombre); }
    public void setApellido(String apellido) { tfLastName.setText(apellido); }
    public void setPassword(String contra) { tfContra.setText(contra); }
    public void setFinalPassword(String finalContra) { tfFinalPassword.setText(finalContra); }

    public void setBgGender(String genero) {
        switch (genero) {
            case "Mujer" -> rbGender1.setSelected(true);
            case "Hombre" -> rbGender2.setSelected(true);
            case "Otro" -> rbGender3.setSelected(true);
        }
    }

    public void setOpcion(String opcion) {
        opciones.setSelectedItem(opcion);
    }
}

