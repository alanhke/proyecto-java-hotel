package HotelProyectoFinal.vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VistaCrearModificarHabitacion extends JPanel {
    private JTextField tipo;
    private JTextField estado;
    private JTextField precio;
    private JButton aceptar;
    private JButton cancelar;

    public VistaCrearModificarHabitacion() {
        setBackground(UIManager.getColor("Panel.background"));
        setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        setLayout(new GridBagLayout());

        Font fuenteCampos = UIManager.getFont("TextField.font").deriveFont(Font.PLAIN, 14f);
        Font fuenteBotones = UIManager.getFont("Button.font").deriveFont(Font.BOLD, 14f);
        Font fuenteTitulo = UIManager.getFont("Label.font").deriveFont(Font.BOLD, 20f);

        tipo    = new JTextField(15);
        estado  = new JTextField(15);
        precio  = new JTextField(15);
        aceptar = new JButton("Aceptar habitación");
        cancelar = new JButton("Cancelar habitación");

        tipo.setFont(fuenteCampos);
        estado.setFont(fuenteCampos);
        precio.setFont(fuenteCampos);

        aceptar.setFont(fuenteBotones);
        cancelar.setFont(fuenteBotones);

        aceptar.setBackground(new Color(33, 150, 243));
        aceptar.setForeground(Color.WHITE);
        aceptar.setFocusPainted(false);

        cancelar.setBackground(new Color(240, 240, 240));
        cancelar.setForeground(Color.DARK_GRAY);
        cancelar.setFocusPainted(false);

        construirDisenio(fuenteTitulo);
    }

    private void construirDisenio(Font fuenteTitulo) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 10, 12, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titulo = new JLabel("Crear / Modificar Habitación");
        titulo.setFont(fuenteTitulo);
        titulo.setForeground(new Color(33, 150, 243));

        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(titulo, gbc);

        gbc.gridwidth = 1;

        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("Tipo:"), gbc);
        gbc.gridx = 1;
        add(tipo, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("Estado:"), gbc);
        gbc.gridx = 1;
        add(estado, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("Precio:"), gbc);
        gbc.gridx = 1;
        add(precio, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.EAST;

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        botones.setOpaque(false);
        botones.add(aceptar);
        botones.add(cancelar);

        add(botones, gbc);
    }

    public JTextField getTipo() {
        return tipo;
    }

    public String getTipoText(){
        return tipo.getText();
    }

    public void setTipo(String tipo) {
        this.tipo.setText(tipo);
    }

    public JTextField getEstado() {
        return estado;
    }

    public String getEstadoText(){
        return estado.getText();
    }

    public void setEstado(String estado) {
        this.estado.setText(estado);
    }

    public JTextField getPrecio() {
        return precio;
    }

    public String getPrecioText(){
        return precio.getText();
    }

    public void setPrecio(String precio) {
        this.precio.setText(precio);
    }

    public JButton getAceptar() {
        return aceptar;
    }

    public void setAceptar(JButton aceptar) {
        this.aceptar = aceptar;
    }

    public JButton getCancelar() {
        return cancelar;
    }

    public void setCancelar(JButton cancelar) {
        this.cancelar = cancelar;
    }

    public void setListeners(ActionListener listener){
        aceptar.addActionListener(listener);
        cancelar.addActionListener(listener);
    }
}

