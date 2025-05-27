package HotelProyectoFinal.vistas;

import HotelProyectoFinal.modelos.HabitacionesTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;

public class VistaGestionarHabitaciones extends JPanel {
    JButton limpiar;
    JButton modificar;
    JButton eliminar;
    JTable table;
    DefaultTableModel tableModel;
    HabitacionesTableModel habitacionesTableModel;
    JScrollPane scrollPane;
    public VistaGestionarHabitaciones() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        String[] datos = {"Numero de habitacion", "Tipo", "Estado", "Precio"};
        limpiar = new JButton("Limpiar Tabla Habitaciones");
        modificar = new JButton("Modificar habitacion");
        eliminar = new JButton("Eliminar habitacion");
        habitacionesTableModel = new HabitacionesTableModel();
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

    public void setListeners(ActionListener listener){
        limpiar.addActionListener(listener);
        modificar.addActionListener(listener);
        eliminar.addActionListener(listener);
    }
}
