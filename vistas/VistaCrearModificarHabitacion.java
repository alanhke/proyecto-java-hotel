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
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel tipoLabel = new JLabel("Tipo:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(tipoLabel, gbc);

        tipo = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        add(tipo, gbc);

        JLabel estadoLabel = new JLabel("Estado:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        add(estadoLabel, gbc);

        estado = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        add(estado, gbc);

        JLabel precioLabel = new JLabel("Precio:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        add(precioLabel, gbc);

        precio = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        add(precio, gbc);

        aceptar = new JButton("Aceptar Habitacion");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0.5;
        add(aceptar, gbc);

        cancelar = new JButton("Cancelar Habitacion");
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 0.5;
        add(cancelar, gbc);
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
