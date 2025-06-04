package HotelProyectoFinal.vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Date;
import com.toedter.calendar.JDateChooser;

public class VistaCrearModificarReserva extends JPanel {
    private final JTextField reservaId;
    private final JTextField idHuesped;
    private final JDateChooser fechaEntrada;
    private final JDateChooser fechaSalida;
    private final JButton aceptar;
    private final JButton cancelar;

    public VistaCrearModificarReserva() {
        setBackground(UIManager.getColor("Panel.background"));
        setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        Font fuenteGeneral = new Font("SansSerif", Font.PLAIN, 15);
        Font fuenteTitulo = new Font("SansSerif", Font.BOLD, 20);
        Font fuenteBoton = new Font("SansSerif", Font.BOLD, 14);

        reservaId = crearCampoTexto(fuenteGeneral, false);
        idHuesped = crearCampoTexto(fuenteGeneral, true);

        fechaEntrada = crearDateChooser(fuenteGeneral);
        fechaSalida = crearDateChooser(fuenteGeneral);

        aceptar = new JButton("✔️Aceptar reserva");
        cancelar = new JButton("❌ Cancelar reserva");

        aceptar.setFont(fuenteBoton);
        cancelar.setFont(fuenteBoton);

        aceptar.setBackground(new Color(33, 150, 243));
        aceptar.setForeground(Color.WHITE);
        aceptar.setFocusPainted(false);

        cancelar.setBackground(new Color(230, 230, 230));
        cancelar.setFocusPainted(false);

        construirDisenio(fuenteTitulo);
    }

    private JTextField crearCampoTexto(Font fuente, boolean editable) {
        JTextField campo = new JTextField(20);
        campo.setFont(fuente);
        campo.setEditable(editable);
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(6, 8, 6, 8)
        ));
        return campo;
    }

    private JDateChooser crearDateChooser(Font fuente) {
        JDateChooser dateChooser = new JDateChooser();
        dateChooser.setFont(fuente);
        dateChooser.setDateFormatString("yyyy/MM/dd");
        dateChooser.setPreferredSize(new Dimension(200, 28));
        return dateChooser;
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
        agregarCampo(gbc, "Fecha de entrada:", fechaEntrada, 3);
        agregarCampo(gbc, "Fecha de salida:", fechaSalida, 4);

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

    private void agregarCampo(GridBagConstraints gbc, String etiqueta, JComponent campo, int fila) {
        gbc.gridy = fila;
        gbc.gridx = 0;
        add(new JLabel(etiqueta), gbc);
        gbc.gridx = 1;
        add(campo, gbc);
    }

    // ==== Métodos getters/setters actualizados ====

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

    public Date getFechaEntradaDate() {
        return fechaEntrada.getDate();
    }

    public String getFechaEntrada() {
        Date fecha = fechaEntrada.getDate();
        if (fecha != null) {
            return new java.text.SimpleDateFormat("yyyy/MM/dd").format(fecha);
        }
        return "";
    }

    public JDateChooser getFechaEntradaChooser() {
        return fechaEntrada;
    }

    public void setFechaEntrada(Date fecha) {
        fechaEntrada.setDate(fecha);
    }

    public Date getFechaSalidaDate() {
        return fechaSalida.getDate();
    }

    public String getFechaSalida() {
        Date fecha = fechaSalida.getDate();
        if (fecha != null) {
            return new java.text.SimpleDateFormat("yyyy/MM/dd").format(fecha);
        }
        return "";
    }

    public JDateChooser getFechaSalidaChooser() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fecha) {
        fechaSalida.setDate(fecha);
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