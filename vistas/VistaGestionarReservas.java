package HotelProyectoFinal.vistas;

import HotelProyectoFinal.modelos.ReservasTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;

public class VistaGestionarReservas extends JPanel {
    JButton limpiar;
    JButton modificar;
    JButton eliminar;
    JButton volver;
    JTable table;
    DefaultTableModel tableModel;
    ReservasTableModel habitacionesTableModel;
    JScrollPane scrollPane;
    public VistaGestionarReservas() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        String[] datos = {"Id", "Huesped", "Fecha entrada", "Fecha salida"};
        limpiar = new JButton("Limpiar Tabla Reservas");
        modificar = new JButton("Modificar reservas");
        eliminar = new JButton("Eliminar reservas");
        volver = new JButton("Volver");
        habitacionesTableModel = new ReservasTableModel();
        table = new JTable(habitacionesTableModel);
        scrollPane = new JScrollPane(table);
        JPanel botones = new JPanel();
        botones.add(limpiar);
        botones.add(modificar);
        botones.add(eliminar);
        botones.add(volver);
        botones.setLayout(new BoxLayout(botones, BoxLayout.X_AXIS));
        add(scrollPane);
        add(botones);
    }

    public void setListeners(ActionListener listener) {
        limpiar.addActionListener(listener);
        modificar.addActionListener(listener);
        eliminar.addActionListener(listener);
        volver.addActionListener(listener);
    }
}
