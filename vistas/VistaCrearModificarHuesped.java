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
        setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        Font fuenteCampos = new Font("SansSerif", Font.PLAIN, 15);
        Font fuenteBotones = new Font("SansSerif", Font.BOLD, 14);

        huespedId = new JTextField(20);
        huespedId.setEditable(false);
        nombres = new JTextField(20);
        correo  = new JTextField(20);
        direccion = new JTextField(20);
        telefono  = new JTextField(20);
        documentoIdentidad = new JTextField(20);

        aceptar = new JButton("Aceptar Huésped");
        cancelar = new JButton("Cancelar Huésped");

        JTextField[] campos = {huespedId, nombres, correo, direccion, telefono, documentoIdentidad};
        for (JTextField campo : campos) {
            campo.setFont(fuenteCampos);
            campo.setBorder(BorderFactory.createCompoundBorder(
                    campo.getBorder(),
                    BorderFactory.createEmptyBorder(5, 8, 5, 8)
            ));
        }

        aceptar.setFont(fuenteBotones);
        cancelar.setFont(fuenteBotones);

        aceptar.setBackground(new Color(76, 175, 80));
        aceptar.setForeground(Color.WHITE);
        aceptar.setFocusPainted(false);
        cancelar.setBackground(new Color(240, 240, 240));
        cancelar.setFocusPainted(false);

        construirDisenio();
    }

    private void construirDisenio() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 12, 10, 12);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titulo = new JLabel("Crear / Modificar Huésped");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 20));
        titulo.setForeground(new Color(56, 142, 60));

        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(titulo, gbc);

        gbc.gridwidth = 1;

        agregarCampo(gbc, "ID Huésped:", huespedId, 1);
        agregarCampo(gbc, "Nombres:", nombres, 2);
        agregarCampo(gbc, "Correo electrónico:", correo, 3);
        agregarCampo(gbc, "Dirección:", direccion, 4);
        agregarCampo(gbc, "Teléfono:", telefono, 5);
        agregarCampo(gbc, "Documento de identidad:", documentoIdentidad, 6);

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 0));
        botones.setOpaque(false);
        botones.add(aceptar);
        botones.add(cancelar);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.EAST;
        add(botones, gbc);
    }

    private void agregarCampo(GridBagConstraints gbc, String label, JTextField field, int row) {
        gbc.gridy = row;
        gbc.gridx = 0;
        add(new JLabel(label), gbc);
        gbc.gridx = 1;
        add(field, gbc);
    }

    public String getHuespedId() {
        return huespedId.getText();
    }
    public JTextField getHuespedIdJTextField() {
        return huespedId;
    }
    public void setHuespedId(String huespedId) {
        this.huespedId.setText(huespedId);
    }

    public String getNombres() { return nombres.getText().trim(); }
    public JTextField getNombresJTextField() {
        return nombres;
    }
    public void setNombres(String s) { nombres.setText(s); }

    public String getCorreo() { return correo.getText().trim(); }
    public JTextField getCorreoJTextField() {
        return correo;
    }
    public void setCorreo(String s) { correo.setText(s); }

    public String getDireccion() { return direccion.getText().trim(); }
    public JTextField getDireccionJTextField() {
        return direccion;
    }
    public void setDireccion(String s) { direccion.setText(s); }

    public String getTelefono() { return telefono.getText().trim(); }
    public JTextField getTelefonoJTextField() {
        return telefono;
    }
    public void setTelefono(String s) { telefono.setText(s); }

    public String getDocumentoIdentidad() { return documentoIdentidad.getText().trim(); }
    public JTextField getDocumentoIdentidadJTextField() {
        return documentoIdentidad;
    }
    public void setDocumentoIdentidad(String s) { documentoIdentidad.setText(s); }

    public JButton getAceptar() { return aceptar; }
    public JButton getCancelar() { return cancelar; }

    public void setListeners(ActionListener listener) {
        aceptar.addActionListener(listener);
        cancelar.addActionListener(listener);
    }
}

