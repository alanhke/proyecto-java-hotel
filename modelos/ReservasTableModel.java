package HotelProyectoFinal.modelos;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ReservasTableModel extends AbstractTableModel {
    private ArrayList<Reservas> reservas = new ArrayList<Reservas>();
    private String nombresColumnas[] =
            {"Id", "Huesped", "Habitacion","Fecha entrada", "Fecha salida"};

    @Override
    public int getRowCount() {
        return reservas.size();
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
        if (columnIndex == 0 || columnIndex == 1 || columnIndex == 2){
            return Integer.class;
        }else if (columnIndex == 3 || columnIndex == 4) {
            return String.class;
        }
        return Object.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if(columnIndex == 3 || columnIndex == 4) {
            return true;
        }
        return false;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        Reservas reserva = reservas.get(rowIndex);
        try {
            switch(columnIndex) {
                case 0:
                    reserva.setId(Integer.parseInt(value.toString()));
                    break;
                case 1:
                    reserva.setHuespedId(Integer.parseInt(value.toString()));
                    break;
                case 2:
                    reserva.setHabitacionId(Integer.parseInt(value.toString()));
                    break;
                case 3:
                    reserva.setFechaEntrada((Date) value);
                    break;
                case 4:
                    reserva.setFechaSalida((Date) value);
                    break;
            }
            fireTableCellUpdated(rowIndex, columnIndex);
        }catch(IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Reservas reserva = reservas.get(rowIndex);
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy/MM/dd");
        switch(columnIndex) {
            case 0:
                return reserva.getId();
            case 1:
                return reserva.getHuespedId();
            case 2:
                return reserva.getHabitacionId();
            case 3:
                return formatoFecha.format(reserva.getFechaEntrada());
            case 4:
                return formatoFecha.format(reserva.getFechaSalida());
        }
        return null;
    }

    public void addRow(Reservas reserva) {
        reservas.add(reserva);
        fireTableRowsInserted(reservas.size() - 1, reservas.size() - 1);
    }

    public void clear() {
        int size = reservas.size();
        if (size > 0) {
            reservas.clear();
            fireTableRowsDeleted(0, size - 1);
        }
    }

    public Reservas getUsuarioEnFila(int fila) {
        if (fila >= 0 && fila < reservas.size()) {
            return reservas.get(fila);
        }
        return null;
    }

    public void removeRow(int fila) {
        if (fila >= 0 && fila < reservas.size()) {
            reservas.remove(fila);
            fireTableRowsDeleted(fila, fila);
        }
    }
}
