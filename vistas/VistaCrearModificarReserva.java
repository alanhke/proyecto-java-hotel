package HotelProyectoFinal.vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VistaCrearModificarReserva extends JPanel {
    private final JTextField idHuesped;
    private final JTextField fechaEntrada;
    private final JTextField fechaSalida;
    private final JButton aceptar;
    private final JButton cancelar;

    public VistaCrearModificarReserva() {
        setBackground(new Color(245, 245, 245));
        setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        Font fuenteCampos = new Font("SansSerif", Font.PLAIN, 14);
        Font fuenteBotones = new Font("SansSerif", Font.BOLD, 14);
        idHuesped    = new JTextField(15);
        fechaEntrada = new JTextField(10);
        fechaSalida  = new JTextField(10);
        aceptar      = new JButton("Aceptar");
        cancelar     = new JButton("Cancelar");

        idHuesped.setFont(fuenteCampos);
        fechaEntrada.setFont(fuenteCampos);
        fechaSalida.setFont(fuenteCampos);
        aceptar.setFont(fuenteBotones);
        cancelar.setFont(fuenteBotones);

        aceptar.setBackground(new Color(33, 150, 243));
        aceptar.setForeground(Color.WHITE);
        cancelar.setBackground(new Color(224, 224, 224));

        construirDisenio();
    }
    private void construirDisenio() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets  = new Insets(8, 10, 8, 10);
        gbc.anchor  = GridBagConstraints.WEST;
        gbc.fill    = GridBagConstraints.HORIZONTAL;

        JLabel titulo = new JLabel("Crear / Modificar Reserva");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 18));
        titulo.setForeground(new Color(33, 150, 243));

        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(titulo, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        add(new JLabel("ID Huésped:"), gbc);

        gbc.gridx = 1;
        add(idHuesped, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Fecha de entrada (dd/MM/yyyy):"), gbc);

        gbc.gridx = 1;
        add(fechaEntrada, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Fecha de salida (dd/MM/yyyy):"), gbc);

        gbc.gridx = 1;
        add(fechaSalida, gbc);

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        botones.setOpaque(false);
        botones.add(aceptar);
        botones.add(cancelar);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.EAST;
        add(botones, gbc);
    }
    public String getIdHuesped()         { return idHuesped.getText().trim(); }
    public void   setIdHuesped(String s) { idHuesped.setText(s); }

    public String getFechaEntrada()            { return fechaEntrada.getText().trim(); }
    public void   setFechaEntrada(String fecha){ fechaEntrada.setText(fecha); }

    public String getFechaSalida()             { return fechaSalida.getText().trim(); }
    public void   setFechaSalida(String fecha) { fechaSalida.setText(fecha); }

    public JButton getAceptar()  { return aceptar; }
    public JButton getCancelar() { return cancelar; }

    public void setListeners(ActionListener listener) {
        aceptar.addActionListener(listener);
        cancelar.addActionListener(listener);
    }
}
