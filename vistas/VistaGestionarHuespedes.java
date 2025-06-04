package HotelProyectoFinal.vistas;


import HotelProyectoFinal.modelos.HuespedesTableModel;
import HotelProyectoFinal.modelos.ReservasTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class VistaGestionarHuespedes extends JPanel {
    private final JButton limpiar;
    private final JButton crear;
    private final JButton modificar;
    private final JButton eliminar;
    private final JButton volver;
    private final JTable table;
    private final HuespedesTableModel huespedesTableModel;
    private final JScrollPane scrollPane;

    public VistaGestionarHuespedes() {
        setLayout(new BorderLayout(15, 15));
        setBackground(new Color(245, 245, 245));
        setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel titulo = new JLabel("Gestión de Huéspedes");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 20));
        titulo.setForeground(new Color(33, 150, 243));
        titulo.setHorizontalAlignment(SwingConstants.LEFT);
        add(titulo, BorderLayout.NORTH);

        huespedesTableModel = new HuespedesTableModel();
        table = new JTable(huespedesTableModel);
        personalizarTabla();

        scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        add(scrollPane, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        panelBotones.setOpaque(false);

        limpiar = crearBoton("Limpiar Tabla", new Color(158, 158, 158), Color.WHITE);
        crear = crearBoton("Crear Huésped", new Color(76, 175, 80), Color.WHITE);
        modificar = crearBoton("Modificar", new Color(255, 193, 7), Color.BLACK);
        eliminar = crearBoton("Eliminar", new Color(244, 67, 54), Color.WHITE);
        volver = crearBoton("Volver", new Color(224, 224, 224), Color.BLACK);

        panelBotones.add(limpiar);
        panelBotones.add(crear);
        panelBotones.add(modificar);
        panelBotones.add(eliminar);
        panelBotones.add(volver);

        add(panelBotones, BorderLayout.SOUTH);
    }

    private JButton crearBoton(String texto, Color fondo, Color textoColor) {
        JButton boton = new JButton(texto);
        boton.setFocusPainted(false);
        boton.setBackground(fondo);
        boton.setForeground(textoColor);
        boton.setFont(new Font("SansSerif", Font.BOLD, 14));
        boton.setPreferredSize(new Dimension(140, 35));
        boton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        return boton;
    }

    private void personalizarTabla() {
        table.setFillsViewportHeight(true);
        table.setRowHeight(30);
        table.setFont(new Font("SansSerif", Font.PLAIN, 13));
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(33, 150, 243));
        table.getTableHeader().setForeground(Color.WHITE);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    public void setListeners(ActionListener listener) {
        limpiar.addActionListener(listener);
        modificar.addActionListener(listener);
        eliminar.addActionListener(listener);
        volver.addActionListener(listener);
        crear.addActionListener(listener);
    }

    public HuespedesTableModel getTable() {
        return huespedesTableModel;
    }

    public JTable getTableView() {
        return table;
    }
}

