package HotelProyectoFinal.modelos;


import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class DatosUsuarioTableModel extends AbstractTableModel {
    private ArrayList<DatosUsuario> datos = new ArrayList<DatosUsuario>();
    private String nombresColumnas[] =
            {"Nombre", "Apellidos", "Nombre de usuario", "Genero", "Tipo"};

    @Override
    public int getRowCount() {
        return datos.size();
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
        return String.class;
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
        DatosUsuario datosUsuario = datos.get(rowIndex);

        try {
            switch(columnIndex) {
                case 0:
                    datosUsuario.setNombre((String) value);
                    break;
                case 1:
                    datosUsuario.setApellido((String)value);
                    break;
                case 2:
                    datosUsuario.setNombreUsuario((String)value);
                    break;
                case 3:
                    datosUsuario.setGenero1((String)value);
                    break;
                case 4:
                    datosUsuario.setTipo((String)value);
            }
            fireTableCellUpdated(rowIndex, columnIndex);
        }catch(IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        DatosUsuario datosUsuario = datos.get(rowIndex);

        switch(columnIndex) {
            case 0:
                return datosUsuario.getNombre();
            case 1:
                return datosUsuario.getApellido();
            case 2:
                return datosUsuario.getNombreUsuario();
            case 3:
                return datosUsuario.getGenero1();
            case 4:
                return datosUsuario.getTipo();
        }

        return null;
    }

    public void addRow(DatosUsuario datosUsuario) {
        datos.add(datosUsuario);
        fireTableRowsInserted(datos.size() - 1, datos.size() - 1);
    }

    public void clear() {
        int size = datos.size();
        if (size > 0) {
            datos.clear();
            fireTableRowsDeleted(0, size - 1);
        }
    }

    public DatosUsuario getUsuarioEnFila(int fila) {
        if (fila >= 0 && fila < datos.size()) {
            return datos.get(fila);
        }
        return null;
    }

    public void removeRow(int fila) {
        if (fila >= 0 && fila < datos.size()) {
            datos.remove(fila);
            fireTableRowsDeleted(fila, fila);
        }
    }
}
