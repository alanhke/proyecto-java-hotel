package HotelProyectoFinal.vistas;


import HotelProyectoFinal.modelos.HuespedesTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;

public class VistaGestionarHuespedes extends JPanel {
    JButton limpiar;
    JButton modificar;
    JButton eliminar;
    JTable table;
    DefaultTableModel tableModel;
    HuespedesTableModel habitacionesTableModel;
    JScrollPane scrollPane;
    public VistaGestionarHuespedes() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        String[] datos = {"Nombre", "Correo", "Direccion", "Telefono", "Documento de identidad"};
        limpiar = new JButton("Limpiar Tabla Huespedes");
        modificar = new JButton("Modificar huespedes");
        eliminar = new JButton("Eliminar huespedes");
        habitacionesTableModel = new HuespedesTableModel();
        table = new JTable(habitacionesTableModel);
        scrollPane = new JScrollPane(table);
        JPanel botones = new JPanel();
        botones.add(limpiar);
        botones.add(modificar);
        botones.add(eliminar);
        botones.setLayout(new BoxLayout(botones, BoxLayout.X_AXIS));
        add(scrollPane);
        add(botones);
    }

    public void setListeners(ActionListener listener) {
        limpiar.addActionListener(listener);
        modificar.addActionListener(listener);
        eliminar.addActionListener(listener);
    }
}
