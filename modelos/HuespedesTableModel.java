package HotelProyectoFinal.modelos;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class HuespedesTableModel extends AbstractTableModel {
    private ArrayList<Huespedes> huespedes = new ArrayList<Huespedes>();
    private String nombresColumnas[] =
            {"Id","Nombre", "Correo", "Direccion", "Telefono", "Documento de identidad"};

    @Override
    public int getRowCount() {
        return huespedes.size();
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
        } else if (columnIndex == 1){
            return String.class;
        } else if (columnIndex == 2) {
            return String.class;
        } else if (columnIndex == 3) {
            return String.class;
        } else if (columnIndex == 4) {
            return String.class;
        }else if (columnIndex == 5) {
            return String.class;
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
        Huespedes huesped = huespedes.get(rowIndex);

        try {
            switch(columnIndex) {
                case 0:
                    huesped.setId(Integer.parseInt(value.toString()));
                case 1:
                    huesped.setNombre((String) value);
                    break;
                case 2:
                    huesped.setCorreo(value.toString());
                    break;
                case 3:
                    huesped.setDireccion((String) value);
                    break;
                case 4:
                    huesped.setTelefono((String) value);
                    break;
                case 5:
                    huesped.setDocumentoIdentidad((String) value);
            }
            fireTableCellUpdated(rowIndex, columnIndex);
        }catch(IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Huespedes huesped = huespedes.get(rowIndex);

        switch(columnIndex) {
            case 0:
                return huesped.getId();
            case 1:
                return huesped.getNombre();
            case 2:
                return huesped.getCorreo();
            case 3:
                return huesped.getDireccion();
            case 4:
                return huesped.getTelefono();
            case 5:
                return huesped.getDocumentoIdentidad();
        }

        return null;
    }

    public void addRow(Huespedes huesped) {
        huespedes.add(huesped);
        fireTableRowsInserted(huespedes.size() - 1, huespedes.size() - 1);
    }

    public void clear() {
        int size = huespedes.size();
        if (size > 0) {
            huespedes.clear();
            fireTableRowsDeleted(0, size - 1);
        }
    }

    public Huespedes getUsuarioEnFila(int fila) {
        if (fila >= 0 && fila < huespedes.size()) {
            return huespedes.get(fila);
        }
        return null;
    }

    public void removeRow(int fila) {
        if (fila >= 0 && fila < huespedes.size()) {
            huespedes.remove(fila);
            fireTableRowsDeleted(fila, fila);
        }
    }
}
