package HotelProyectoFinal.vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VistaCrearModificarReserva extends JPanel {
    private final JTextField reservaId;
    private final JTextField idHuesped;
    private final JTextField fechaEntrada;
    private final JTextField fechaSalida;
    private final JButton aceptar;
    private final JButton cancelar;

    public VistaCrearModificarReserva() {
        setBackground(UIManager.getColor("Panel.background"));
        setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        Font fuenteGeneral = new Font("SansSerif", Font.PLAIN, 15);
        Font fuenteTitulo = new Font("SansSerif", Font.BOLD, 20);
        Font fuenteBoton = new Font("SansSerif", Font.BOLD, 14);

        reservaId = crearCampo(fuenteGeneral, false);
        idHuesped = crearCampo(fuenteGeneral, true);
        fechaEntrada = crearCampo(fuenteGeneral, true);
        fechaSalida = crearCampo(fuenteGeneral, true);

        aceptar = new JButton("Aceptar reserva");
        cancelar = new JButton("Cancelar reserva");

        aceptar.setFont(fuenteBoton);
        cancelar.setFont(fuenteBoton);

        aceptar.setBackground(new Color(33, 150, 243));
        aceptar.setForeground(Color.WHITE);
        aceptar.setFocusPainted(false);

        cancelar.setBackground(new Color(230, 230, 230));
        cancelar.setFocusPainted(false);

        construirDisenio(fuenteTitulo);
    }

    private JTextField crearCampo(Font fuente, boolean editable) {
        JTextField campo = new JTextField(20);
        campo.setFont(fuente);
        campo.setEditable(editable);
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(6, 8, 6, 8)
        ));
        return campo;
    }

    private void construirDisenio(Font fuenteTitulo) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 12, 10, 12);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titulo = new JLabel("📅 Crear / Modificar Reserva");
        titulo.setFont(fuenteTitulo);
        titulo.setForeground(new Color(33, 150, 243));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titulo, gbc);

        gbc.gridwidth = 1;
        agregarCampo(gbc, "ID Reserva:", reservaId, 1);
        agregarCampo(gbc, "ID Huésped:", idHuesped, 2);
        agregarCampo(gbc, "Fecha de entrada (dd/MM/yyyy):", fechaEntrada, 3);
        agregarCampo(gbc, "Fecha de salida (dd/MM/yyyy):", fechaSalida, 4);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 0));
        panelBotones.setOpaque(false);
        panelBotones.add(aceptar);
        panelBotones.add(cancelar);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.EAST;
        add(panelBotones, gbc);
    }

    private void agregarCampo(GridBagConstraints gbc, String etiqueta, JTextField campo, int fila) {
        gbc.gridy = fila;
        gbc.gridx = 0;
        add(new JLabel(etiqueta), gbc);
        gbc.gridx = 1;
        add(campo, gbc);
    }

    // ==== Métodos getters/setters originales ====

    public String getReservaId() {
        return reservaId.getText();
    }

    public JTextField getReservaIdJTextField() {
        return reservaId;
    }

    public void setReservaIdJtextField(String id) {
        reservaId.setText(id);
    }

    public String getIdHuesped() {
        return idHuesped.getText().trim();
    }

    public JTextField getIdHuespedField() {
        return idHuesped;
    }

    public void setIdHuesped(String s) {
        idHuesped.setText(s);
    }

    public String getFechaEntrada() {
        return fechaEntrada.getText().trim();
    }

    public JTextField getFechaEntradaField() {
        return fechaEntrada;
    }

    public void setFechaEntrada(String fecha) {
        fechaEntrada.setText(fecha);
    }

    public String getFechaSalida() {
        return fechaSalida.getText().trim();
    }

    public JTextField getFechaSalidaField() {
        return fechaSalida;
    }

    public void setFechaSalida(String fecha) {
        fechaSalida.setText(fecha);
    }

    public JButton getAceptar() {
        return aceptar;
    }

    public JButton getCancelar() {
        return cancelar;
    }

    public void setListeners(ActionListener listener) {
        aceptar.addActionListener(listener);
        cancelar.addActionListener(listener);
    }
}

