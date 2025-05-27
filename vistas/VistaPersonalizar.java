package HotelProyectoFinal.vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VistaPersonalizar extends JPanel {
    JComboBox<String> comboTema;
    JComboBox<String> comboFuente;
    JComboBox<String> comboTamanoFuente;
    JButton btnAplicar;
    JButton btnVolver;

    public VistaPersonalizar() {
        setLayout(new BorderLayout());
        setBackground(new Color(250, 250, 255));

        JLabel titulo = new JLabel("Personalización de Interfaz", JLabel.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        add(titulo, BorderLayout.NORTH);

        JPanel panelCentro = new JPanel(new GridLayout(4, 2, 10, 10));
        panelCentro.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        panelCentro.setBackground(new Color(250, 250, 255));

        // Tema
        panelCentro.add(new JLabel("Tema de color:"));
        comboTema = new JComboBox<>(new String[]{"Claro", "Oscuro"});
        panelCentro.add(comboTema);

        // Fuente
        panelCentro.add(new JLabel("Tipo de fuente:"));
        comboFuente = new JComboBox<>(new String[]{"SansSerif", "Serif", "Monospaced"});
        panelCentro.add(comboFuente);

        // Tamaño fuente
        panelCentro.add(new JLabel("Tamaño de fuente:"));
        comboTamanoFuente = new JComboBox<>(new String[]{"12", "14", "16", "18", "20"});
        panelCentro.add(comboTamanoFuente);

        add(panelCentro, BorderLayout.CENTER);

        // Botones
        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(new Color(250, 250, 255));
        btnAplicar = new JButton("Aplicar Cambios");
        btnVolver = new JButton("Volver");
        panelBotones.add(btnAplicar);
        panelBotones.add(btnVolver);
        add(panelBotones, BorderLayout.SOUTH);
    }

    // Getters para usar desde el controlador
    public JButton getBtnAplicar() {
        return btnAplicar;
    }

    public JButton getBtnVolver() {
        return btnVolver;
    }

    public String getTemaSeleccionado() {
        return (String) comboTema.getSelectedItem();
    }

    public String getFuenteSeleccionada() {
        return (String) comboFuente.getSelectedItem();
    }

    public int getTamanoFuenteSeleccionado() {
        return Integer.parseInt((String) comboTamanoFuente.getSelectedItem());
    }

    public void setListeners(ActionListener listener){
        btnAplicar.addActionListener(listener);
        btnVolver.addActionListener(listener);
    }
}
