package HotelProyectoFinal.vistas;

import HotelProyectoFinal.modelos.HabitacionesTableModel;
import HotelProyectoFinal.modelos.HuespedesTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VistaGestionarHabitaciones extends JPanel {
    private final JButton limpiar, crear, modificar, eliminar, volver;
    private final JTable table;
    private final HabitacionesTableModel habitacionesTableModel;
    private final JScrollPane scrollPane;

    public VistaGestionarHabitaciones() {
        setLayout(new BorderLayout(20, 20));
        setBackground(new Color(245, 248, 250));
        setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        Font fuenteGeneral = new Font("Segoe UI", Font.PLAIN, 14);
        Font fuenteBoton = new Font("Segoe UI", Font.BOLD, 13);
        Font fuenteTitulo = new Font("Segoe UI", Font.BOLD, 20);

        // ===== Título =====
        JLabel titulo = new JLabel("Gestión de Habitaciones");
        titulo.setFont(fuenteTitulo);
        titulo.setForeground(new Color(33, 150, 243));
        add(titulo, BorderLayout.NORTH);

        // ===== Tabla =====
        habitacionesTableModel = new HabitacionesTableModel();
        table = new JTable(habitacionesTableModel);
        table.setFont(fuenteGeneral);
        table.setRowHeight(26);
        table.setGridColor(new Color(230, 230, 230));
        table.setSelectionBackground(new Color(200, 230, 255));
        table.setSelectionForeground(Color.BLACK);
        table.getTableHeader().setFont(fuenteBoton);
        table.getTableHeader().setBackground(new Color(230, 240, 255));
        table.getTableHeader().setReorderingAllowed(false);

        scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                new EmptyBorder(10, 10, 10, 10)
        ));
        add(scrollPane, BorderLayout.CENTER);

        // ===== Botones =====
        limpiar = crearBoton("🧹 Limpiar", new Color(96, 125, 139));
        crear = crearBoton("➕ Crear", new Color(76, 175, 80));
        modificar = crearBoton("✏️ Modificar", new Color(255, 193, 7));
        eliminar = crearBoton("🗑️ Eliminar", new Color(244, 67, 54));
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
    }

    public HabitacionesTableModel getTable() {
        return habitacionesTableModel;
    }

    public JTable getTableView() {
        return table;
    }
}


