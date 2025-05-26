package HotelProyectoFinal.vistas;

import HotelProyectoFinal.modelos.ReservasTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class VistaGestionarReservas extends JPanel {
    JButton limpiar;
    JButton pdf;
    JButton modificar;
    JButton eliminar;
    JTable table;
    DefaultTableModel tableModel;
    ReservasTableModel habitacionesTableModel;
    JScrollPane scrollPane;
    public VistaGestionarReservas() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        String[] datos = {"Id", "Huesped", "Fecha entrada", "Fecha salida"};
        limpiar = new JButton("Limpiar Tabla Reservas");
        pdf = new JButton("Exportar reservas a PDF");
        modificar = new JButton("Modificar reservas");
        eliminar = new JButton("Eliminar reservas");
        habitacionesTableModel = new ReservasTableModel();
        table = new JTable(habitacionesTableModel);
        scrollPane = new JScrollPane(table);
        JPanel botones = new JPanel();
        botones.add(limpiar);
        botones.add(pdf);
        botones.add(modificar);
        botones.add(eliminar);
        botones.setLayout(new BoxLayout(botones, BoxLayout.X_AXIS));
        add(scrollPane);
        add(botones);
    }
}
