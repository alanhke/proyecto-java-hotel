package HotelProyectoFinal.vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VistaCrearModificarHuesped extends JPanel {
    private final JTextField huespedId;
    private final JTextField nombres;
    private final JTextField correo;
    private final JTextField direccion;
    private final JTextField telefono;
    private final JTextField documentoIdentidad;
    private final JButton aceptar;
    private final JButton cancelar;

    public VistaCrearModificarHuesped() {
        setBackground(new Color(250, 250, 250));
        setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        Font fuenteCampos = new Font("SansSerif", Font.PLAIN, 14);
        Font fuenteBotones = new Font("SansSerif", Font.BOLD, 14);

        huespedId = new JTextField(20);
        huespedId.setEditable(false);
        nombres = new JTextField(20);
        correo  = new JTextField(20);
        direccion = new JTextField(20);
        telefono  = new JTextField(20);
        documentoIdentidad = new JTextField(20);

        aceptar = new JButton("Aceptar Huesped");
        cancelar = new JButton("Cancelar");

        huespedId.setFont(fuenteCampos);
        nombres.setFont(fuenteCampos);
        correo.setFont(fuenteCampos);
        direccion.setFont(fuenteCampos);
        telefono.setFont(fuenteCampos);
        documentoIdentidad.setFont(fuenteCampos);

        aceptar.setFont(fuenteBotones);
        cancelar.setFont(fuenteBotones);

        aceptar.setBackground(new Color(76, 175, 80));
        aceptar.setForeground(Color.WHITE);
        cancelar.setBackground(new Color(224, 224, 224));

        construirDisenio();
    }

    private void construirDisenio() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titulo = new JLabel("Crear / Modificar Huésped");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 18));
        titulo.setForeground(new Color(76, 175, 80));

        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(titulo, gbc);

        gbc.gridwidth = 1;

        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("ID Huésped:"), gbc);
        gbc.gridx = 1;
        add(huespedId, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("Nombres:"), gbc);
        gbc.gridx = 1;
        add(nombres, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("Correo electrónico:"), gbc);
        gbc.gridx = 1;
        add(correo, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("Dirección:"), gbc);
        gbc.gridx = 1;
        add(direccion, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("Teléfono:"), gbc);
        gbc.gridx = 1;
        add(telefono, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("Documento de identidad:"), gbc);
        gbc.gridx = 1;
        add(documentoIdentidad, gbc);

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        botones.setOpaque(false);
        botones.add(aceptar);
        botones.add(cancelar);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.EAST;
        add(botones, gbc);
    }

    public String getNombres() { return nombres.getText().trim(); }
    public void setNombres(String s) { nombres.setText(s); }

    public String getCorreo() { return correo.getText().trim(); }
    public void setCorreo(String s) { correo.setText(s); }

    public String getDireccion() { return direccion.getText().trim(); }
    public void setDireccion(String s) { direccion.setText(s); }

    public String getTelefono() { return telefono.getText().trim(); }
    public void setTelefono(String s) { telefono.setText(s); }

    public String getDocumentoIdentidad() { return documentoIdentidad.getText().trim(); }
    public void setDocumentoIdentidad(String s) { documentoIdentidad.setText(s); }

    public JButton getAceptar() { return aceptar; }
    public JButton getCancelar() { return cancelar; }

    public void setListeners(ActionListener listener) {
        aceptar.addActionListener(listener);
        cancelar.addActionListener(listener);
    }
}
