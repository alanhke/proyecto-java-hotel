package HotelProyectoFinal.vistas;


import HotelProyectoFinal.modelos.HuespedesTableModel;
import HotelProyectoFinal.modelos.ReservasTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;

public class VistaGestionarHuespedes extends JPanel {
    JButton limpiar;
    JButton crear;
    JButton modificar;
    JButton eliminar;
    JButton volver;
    JTable table;
    DefaultTableModel tableModel;
    HuespedesTableModel huespedesTableModel;
    JScrollPane scrollPane;
    public VistaGestionarHuespedes() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        String[] datos = {"Nombre", "Correo", "Direccion", "Telefono", "Documento de identidad"};
        limpiar = new JButton("Limpiar Tabla Huespedes");
        modificar = new JButton("Modificar huespedes");
        eliminar = new JButton("Eliminar huespedes");
        volver = new JButton("Volver");
        crear = new JButton("Crear huesped");
        huespedesTableModel = new HuespedesTableModel();
        table = new JTable(huespedesTableModel);
        scrollPane = new JScrollPane(table);
        JPanel botones = new JPanel();
        botones.add(limpiar);
        botones.add(crear);
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
        crear.addActionListener(listener);
    }

    public HuespedesTableModel getTable(){
        return huespedesTableModel;
    }
    public JTable getTableView(){
        return table;
    }
}
