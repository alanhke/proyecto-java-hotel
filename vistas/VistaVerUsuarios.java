package HotelProyectoFinal.vistas;

import HotelProyectoFinal.modelos.DatosUsuarioTableModel;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;

public class VistaVerUsuarios extends JPanel{
    JButton limpiar;
    JButton pdf;
    JButton modificar;
    JButton eliminar;
    JTable table;
    DefaultTableModel tableModel;
    DatosUsuarioTableModel registrosTableModel;
    JScrollPane scrollPane;
    public VistaVerUsuarios() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        String[] datos = {"Nombre", "Apellidos", "Nombre de usuario", "Mensaje", "Genero", "Tipo"};
        limpiar = new JButton("Limpiar");
        pdf = new JButton("Exportar a PDF");
        modificar = new JButton("Modificar Usuario");
        eliminar = new JButton("Eliminar Usuario");
        registrosTableModel = new DatosUsuarioTableModel();
        table = new JTable(registrosTableModel);
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
