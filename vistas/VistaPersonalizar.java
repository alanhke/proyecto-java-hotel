package HotelProyectoFinal.vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VistaPersonalizar extends JPanel {
    private JComboBox<String> comboTema;
    private JComboBox<String> comboFuente;
    private JComboBox<String> comboTamanoFuente;
    private JButton btnAplicar;
    private JButton btnVolver;

    private JLabel titulo;
    private JPanel panelCentro;
    private JPanel panelBotones;

    public VistaPersonalizar() {
        setLayout(new BorderLayout());
        construirComponentes();
        aplicarTema("Claro");
        aplicarEstilos("SansSerif", 14);
    }

    private void construirComponentes() {
        titulo = new JLabel("Personalización de Interfaz", JLabel.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        add(titulo, BorderLayout.NORTH);
        panelCentro = new JPanel(new GridLayout(4, 2, 10, 10));
        panelCentro.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        panelCentro.add(new JLabel("Tema de color:"));
        comboTema = new JComboBox<>(new String[]{"Claro", "Oscuro"});
        panelCentro.add(comboTema);
        panelCentro.add(new JLabel("Tipo de fuente:"));
        comboFuente = new JComboBox<>(new String[]{"SansSerif", "Serif", "Monospaced"});
        panelCentro.add(comboFuente);
        panelCentro.add(new JLabel("Tamaño de fuente:"));
        comboTamanoFuente = new JComboBox<>(new String[]{"12", "14", "16", "18", "20"});
        panelCentro.add(comboTamanoFuente);
        add(panelCentro, BorderLayout.CENTER);

        panelBotones = new JPanel();
        btnAplicar = new JButton("✔️Aplicar Cambios");
        btnVolver = new JButton("🔙 Volver");
        panelBotones.add(btnAplicar);
        panelBotones.add(btnVolver);
        add(panelBotones, BorderLayout.SOUTH);
    }
    public void aplicarTema(String tema) {
        Color fondo, texto;
        if ("Oscuro".equals(tema)) {
            fondo = new Color(40, 40, 40);
            texto = Color.WHITE;
        } else {
            fondo = new Color(250, 250, 255);
            texto = Color.BLACK;
        }
        setBackground(fondo);
        panelCentro.setBackground(fondo);
        panelBotones.setBackground(fondo);
        titulo.setForeground(texto);
        for (Component comp : panelCentro.getComponents()) {
            if (comp instanceof JLabel) {
                comp.setForeground(texto);
            }
        }
        btnAplicar.setBackground(fondo);
        btnAplicar.setForeground(texto);
        btnVolver.setBackground(fondo);
        btnVolver.setForeground(texto);
    }

    public void aplicarEstilos(String fuente, int tamano) {
        Font nuevaFuente = new Font(fuente, Font.PLAIN, tamano);
        aplicarFuenteRecursiva(this, nuevaFuente);
    }

    private void aplicarFuenteRecursiva(Component comp, Font fuente) {
        comp.setFont(fuente);
        if (comp instanceof Container) {
            for (Component hijo : ((Container) comp).getComponents()) {
                aplicarFuenteRecursiva(hijo, fuente);
            }
        }
    }

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

    public void setListeners(ActionListener listener) {
        btnAplicar.addActionListener(listener);
        btnVolver.addActionListener(listener);
    }
}

