package HotelProyectoFinal.modelos;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class HabitacionesTableModel extends AbstractTableModel {
    private ArrayList<Habitaciones> habitaciones = new ArrayList<Habitaciones>();
    private String nombresColumnas[] =
            {"Numero de habitacion", "Tipo", "Estado", "Precio"};

    @Override
    public int getRowCount() {
        return habitaciones.size();
    }

    @Override
    public int getColumnCount() {
        return nombresColumnas.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return nombresColumnas[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex){
        if (columnIndex == 0){
            return Integer.class;
        } else if (columnIndex == 1) {
            return String.class;
        } else if (columnIndex == 2) {
            return String.class;
        } else if (columnIndex == 3) {
            return Double.class;
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if(columnIndex == 2 || columnIndex == 1) {
            return true;
        }

        return false;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        Habitaciones habitacion = habitaciones.get(rowIndex);

        try {
            switch(columnIndex) {
                case 0:
                    habitacion.setNumero(Integer.parseInt(value.toString()));
                    break;
                case 1:
                    habitacion.setTipo((String) value);
                    break;
                case 2:
                    habitacion.setEstado((String) value);
                    break;
                case 3:
                    habitacion.setPrecio((Double)value);
                    break;
            }
            fireTableCellUpdated(rowIndex, columnIndex);
        }catch(IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Habitaciones habitacion = habitaciones.get(rowIndex);

        switch(columnIndex) {
            case 0:
                return habitacion.getNumero();
            case 1:
                return habitacion.getTipo();
            case 2:
                return habitacion.getEstado();
            case 3:
                return habitacion.getPrecio();
        }

        return null;
    }

    public void addRow(Habitaciones habitacion) {
        habitaciones.add(habitacion);
        fireTableRowsInserted(habitaciones.size() - 1, habitaciones.size() - 1);
    }

    public void clear() {
        int size = habitaciones.size();
        if (size > 0) {
            habitaciones.clear();
            fireTableRowsDeleted(0, size - 1);
        }
    }

    public Habitaciones getUsuarioEnFila(int fila) {
        if (fila >= 0 && fila < habitaciones.size()) {
            return habitaciones.get(fila);
        }
        return null;
    }

    public void removeRow(int fila) {
        if (fila >= 0 && fila < habitaciones.size()) {
            habitaciones.remove(fila);
            fireTableRowsDeleted(fila, fila);
        }
    }
}
