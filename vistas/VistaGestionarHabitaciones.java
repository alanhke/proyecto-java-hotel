package HotelProyectoFinal.vistas;

import HotelProyectoFinal.modelos.HabitacionesTableModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionListener;

public class VistaGestionarHabitaciones extends JPanel {
    private final JButton limpiar, crear, modificar, eliminar, volver, buscar;
    private final JTable table;
    private final HabitacionesTableModel habitacionesTableModel;
    private final JScrollPane scrollPane;

    private final JTextField campoNumero;
    private final JTextField campoTipo;
    private final JTextField campoEstado;
    private final JTextField campoPrecio;

    public VistaGestionarHabitaciones() {
        setLayout(new BorderLayout(20, 20));
        setBackground(new Color(245, 248, 250));
        setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        Font fuenteGeneral = new Font("Segoe UI", Font.PLAIN, 14);
        Font fuenteBoton = new Font("Segoe UI", Font.BOLD, 13);
        Font fuenteTitulo = new Font("Segoe UI", Font.BOLD, 20);

        JLabel titulo = new JLabel("Gestión de Habitaciones");
        titulo.setFont(fuenteTitulo);
        titulo.setForeground(new Color(33, 150, 243));

        // --- Panel de búsqueda con FlowLayout (horizontal) ---
        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 5));
        panelBusqueda.setOpaque(false);

        campoNumero = new JTextField(8);
        campoTipo = new JTextField(8);
        campoEstado = new JTextField(8);
        campoPrecio = new JTextField(8);

        campoNumero.setFont(fuenteGeneral);
        campoTipo.setFont(fuenteGeneral);
        campoEstado.setFont(fuenteGeneral);
        campoPrecio.setFont(fuenteGeneral);

        panelBusqueda.add(new JLabel("Número:"));
        panelBusqueda.add(campoNumero);
        panelBusqueda.add(new JLabel("Tipo:"));
        panelBusqueda.add(campoTipo);
        panelBusqueda.add(new JLabel("Estado:"));
        panelBusqueda.add(campoEstado);
        panelBusqueda.add(new JLabel("Precio por noche:"));
        panelBusqueda.add(campoPrecio);

        buscar = new JButton("🔎 Buscar");
        buscar.setFont(fuenteBoton);
        buscar.setBackground(new Color(33, 150, 243));
        buscar.setForeground(Color.WHITE);
        buscar.setFocusPainted(false);
        buscar.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(25, 118, 210), 1),
                new EmptyBorder(6, 20, 6, 20)
        ));
        panelBusqueda.add(buscar);

        JPanel panelSuperior = new JPanel(new BorderLayout(10, 10));
        panelSuperior.setOpaque(false);
        panelSuperior.add(titulo, BorderLayout.NORTH);
        panelSuperior.add(panelBusqueda, BorderLayout.CENTER);
        add(panelSuperior, BorderLayout.NORTH);

        habitacionesTableModel = new HabitacionesTableModel();
        table = new JTable(habitacionesTableModel);
        table.setFont(fuenteGeneral);
        table.setRowHeight(26);
        table.setGridColor(new Color(230, 230, 230));
        table.setSelectionBackground(new Color(200, 230, 255));
        table.setSelectionForeground(Color.BLACK);
        JTableHeader header = table.getTableHeader();
        header.setFont(fuenteBoton);
        header.setBackground(new Color(230, 240, 255));
        header.setReorderingAllowed(false);

        scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                new EmptyBorder(10, 10, 10, 10)
        ));
        add(scrollPane, BorderLayout.CENTER);

        limpiar = crearBoton("🧹 Limpiar Tabla Habitaciones", new Color(96, 125, 139));
        crear = crearBoton("➕ Crear Habitacion", new Color(76, 175, 80));
        modificar = crearBoton("✏️ Modificar Habitacion", new Color(255, 193, 7));
        eliminar = crearBoton("🗑️ Eliminar Habitacion", new Color(244, 67, 54));
        volver = crearBoton("🔙 Volver", new Color(158, 158, 158));

        JPanel panelBotones = new JPanel(new GridBagLayout());
        panelBotones.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 15, 8, 15);

        gbc.gridx = 0;
        panelBotones.add(crear, gbc);
        gbc.gridx++;
        panelBotones.add(modificar, gbc);
        gbc.gridx++;
        panelBotones.add(eliminar, gbc);
        gbc.gridx++;
        panelBotones.add(limpiar, gbc);
        gbc.gridx++;
        panelBotones.add(volver, gbc);

        add(panelBotones, BorderLayout.SOUTH);
    }

    private JButton crearBoton(String texto, Color colorFondo) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 13));
        boton.setForeground(Color.WHITE);
        boton.setBackground(colorFondo);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(colorFondo.darker(), 1),
                new EmptyBorder(6, 16, 6, 16)
        ));
        return boton;
    }

    public void setListeners(ActionListener listener) {
        limpiar.addActionListener(listener);
        modificar.addActionListener(listener);
        eliminar.addActionListener(listener);
        volver.addActionListener(listener);
        crear.addActionListener(listener);
        buscar.addActionListener(listener);
    }

    public HabitacionesTableModel getTable() {
        return habitacionesTableModel;
    }

    public JTable getTableView() {
        return table;
    }

    public JTextField getCampoNumero() {
        return campoNumero;
    }
    public int getNumero() {
        if (campoNumero.getText().equals("")){
            return 0;
        }
        return Integer.parseInt(campoNumero.getText());
    }

    public JTextField getCampoTipo() {
        return campoTipo;
    }
    public String getTipo() {
        if (campoTipo.getText().equals("")){
            return null;
        }
        return campoTipo.getText();
    }

    public JTextField getCampoEstado() {
        return campoEstado;
    }
    public String getEstado() {
        if (campoEstado.getText().equals("")){
            return null;
        }
        return campoEstado.getText();
    }

    public JTextField getCampoPrecio() {
        return campoPrecio;
    }
    public double getPrecio() {
        if (campoPrecio.getText().equals("")){
            return 0;
        }
        return Double.parseDouble(campoPrecio.getText());
    }

    public JButton getBuscar() {
        return buscar;
    }
}
