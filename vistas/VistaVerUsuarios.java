package HotelProyectoFinal.vistas;

import HotelProyectoFinal.modelos.DatosUsuarioTableModel;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class VistaVerUsuarios extends JPanel {
    JButton limpiar;
    JButton pdf;
    JButton modificar;
    JButton eliminar;
    JTable table;
    DatosUsuarioTableModel registrosTableModel;
    JScrollPane scrollPane;

    public VistaVerUsuarios() {
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(245, 247, 250)); // Fondo suave

        registrosTableModel = new DatosUsuarioTableModel();
        table = new JTable(registrosTableModel);
        table.setFillsViewportHeight(true);
        table.setFont(new Font("SansSerif", Font.PLAIN, 14));
        scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));

        add(scrollPane, BorderLayout.CENTER);

        limpiar = crearBoton("Limpiar", new Color(52, 152, 219));
        pdf = crearBoton("Exportar a PDF", new Color(231, 76, 60));
        modificar = crearBoton("Modificar Usuario", new Color(39, 174, 96));
        eliminar = crearBoton("Eliminar Usuario", new Color(192, 57, 43));

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panelBotones.setBackground(new Color(245, 247, 250));
        panelBotones.add(limpiar);
        panelBotones.add(pdf);
        panelBotones.add(modificar);
        panelBotones.add(eliminar);

        add(panelBotones, BorderLayout.SOUTH);
    }

    private JButton crearBoton(String texto, Color colorFondo) {
        JButton boton = new JButton(texto);
        boton.setBackground(colorFondo);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setFont(new Font("SansSerif", Font.BOLD, 14));
        return boton;
    }

    public void setListeners(ActionListener listener){
        limpiar.addActionListener(listener);
        pdf.addActionListener(listener);
        eliminar.addActionListener(listener);
        modificar.addActionListener(listener);
    }

    public DatosUsuarioTableModel getTable(){
        return registrosTableModel;
    }

    public JTable getTableView(){
        return table;
    }
}

