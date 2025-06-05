package HotelProyectoFinal.vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.table.DefaultTableModel;

import HotelProyectoFinal.modelos.Habitaciones;
import HotelProyectoFinal.modelos.Huespedes;
import HotelProyectoFinal.modelos.Reservas;
import com.itextpdf.layout.properties.TextAlignment;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Table;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.VerticalAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Image;


public class VistaReportes extends JPanel {
    JComboBox<String> selectorReporte;
    JButton btnGenerar;
    JButton btnExportarPDF;
    JButton btnVolver;
    ChartPanel panelGrafica;
    JPanel panelGraficaWrapper;
    JTable tablaDatos;
    String tipoReporteActual;

    public VistaReportes() {
        setLayout(new BorderLayout());
        setBackground(new Color(245, 247, 250));
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelSuperior.setBackground(new Color(245, 247, 250));
        JLabel titulo = new JLabel("Reportes del Hotel");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 28));
        titulo.setForeground(new Color(45, 62, 80));
        panelSuperior.add(titulo);
        add(panelSuperior, BorderLayout.NORTH);

        JPanel panelCentro = new JPanel(new BorderLayout(15, 15));
        panelCentro.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        panelCentro.setBackground(new Color(245, 247, 250));

        JPanel panelSelector = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panelSelector.setBackground(new Color(245, 247, 250));

        selectorReporte = new JComboBox<>(new String[]{
                "Reservas realizadas",
                "Ocupación de Habitaciones",
                "Huespedes registrados"
        });
        selectorReporte.setPreferredSize(new Dimension(200, 30));

        btnGenerar = crearBoton("Generar Reporte", new Color(52, 152, 219));
        btnExportarPDF = crearBoton("Exportar PDF", new Color(231, 76, 60));
        btnVolver = crearBoton("🔙 Volver", new Color(149, 165, 166));

        panelSelector.add(selectorReporte);
        panelSelector.add(btnGenerar);
        panelSelector.add(btnExportarPDF);
        panelSelector.add(btnVolver);

        panelCentro.add(panelSelector, BorderLayout.NORTH);

        panelGraficaWrapper = new JPanel(new BorderLayout());
        panelGraficaWrapper.setBackground(new Color(245, 247, 250));
        panelCentro.add(panelGraficaWrapper, BorderLayout.CENTER);

        add(panelCentro, BorderLayout.CENTER);
    }

    private JButton crearBoton(String texto, Color colorFondo) {
        JButton boton = new JButton(texto);
        boton.setBackground(colorFondo);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setFont(new Font("SansSerif", Font.BOLD, 14));
        return boton;
    }

    public void mostrarTablaReservadasRealizadas(ArrayList<Reservas> reservasRealizadas) {
        String[] columnas = {"ID", "Huesped", "Habitación", "Check-In", "Check-Out"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        for (Reservas r : reservasRealizadas) {
            Object[] fila = {
                    r.getId(),
                    r.getHuespedId(),
                    r.getHabitacionId(),
                    r.getFechaEntrada(),
                    r.getFechaSalida()
            };
            modelo.addRow(fila);
        }

        tablaDatos = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(tablaDatos);
        panelGraficaWrapper.removeAll();
        panelGraficaWrapper.setLayout(new BorderLayout());
        panelGraficaWrapper.add(scrollPane, BorderLayout.CENTER);
        panelGraficaWrapper.revalidate();
        panelGraficaWrapper.repaint();
        panelGrafica = null;
        tipoReporteActual = "Reservas realizadas";
    }

    public void mostrarGraficaOcupacion(ArrayList<Habitaciones> habitacionesOcupadas, ArrayList<Habitaciones> habitaciones) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        int ocupadas = habitacionesOcupadas.size();
        int total = habitaciones.size();
        int libres = total - ocupadas;

        dataset.setValue("Ocupadas", ocupadas);
        dataset.setValue("Libres", libres);

        JFreeChart chart = ChartFactory.createPieChart(
                "Ocupación de Habitaciones",
                dataset,
                true,
                true,
                false
        );
        tablaDatos = null;
        tipoReporteActual = "Ocupación de Habitaciones";
        actualizarGrafica(chart);
    }

    public void mostrarTablaHuespedesRegistrados(ArrayList<Huespedes> huespedesRegistrados) {
        String[] columnas = {"ID", "Nombre", "Correo", "Dirección", "Teléfono", "Documento"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        for (Huespedes h : huespedesRegistrados) {
            Object[] fila = {
                    h.getId(),
                    h.getNombre(),
                    h.getCorreo(),
                    h.getDireccion(),
                    h.getTelefono(),
                    h.getDocumentoIdentidad()
            };
            modelo.addRow(fila);
        }

        tablaDatos = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(tablaDatos);
        panelGraficaWrapper.removeAll();
        panelGraficaWrapper.setLayout(new BorderLayout());
        panelGraficaWrapper.add(scrollPane, BorderLayout.CENTER);
        panelGraficaWrapper.revalidate();
        panelGraficaWrapper.repaint();
        panelGrafica = null;
        tipoReporteActual = "Huespedes registrados";
    }

    private void actualizarGrafica(JFreeChart chart) {
        panelGraficaWrapper.removeAll();
        panelGrafica = new ChartPanel(chart);
        panelGraficaWrapper.add(panelGrafica, BorderLayout.CENTER);
        panelGraficaWrapper.revalidate();
        panelGraficaWrapper.repaint();
        tablaDatos = null;
    }

    public void setListeners(ActionListener listener) {
        btnGenerar.addActionListener(listener);
        btnExportarPDF.addActionListener(listener);
        btnVolver.addActionListener(listener);
    }

    public String getTipoReporteSeleccionado() {
        return (String) selectorReporte.getSelectedItem();
    }

    public void exportarGraficaAPDF(String rutaArchivo) {
        try {
            PdfWriter writer = new PdfWriter(rutaArchivo);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);
            DeviceRgb azulMarino = new DeviceRgb(25, 42, 86);
            String fecha = LocalDate.now().toString();
            Paragraph titulo = new Paragraph("Reporte de " + tipoReporteActual).setFontSize(20).setBold().setFontColor(azulMarino).setTextAlignment(TextAlignment.CENTER);
            Paragraph fechaTexto = new Paragraph("Fecha de generación: " + fecha).setFontSize(11).setFontColor(ColorConstants.DARK_GRAY).setTextAlignment(TextAlignment.CENTER);
            document.add(titulo);
            document.add(fechaTexto);
            document.add(new Paragraph("\n"));

            if (panelGrafica != null) {
                BufferedImage imagen = panelGrafica.getChart().createBufferedImage(600, 400);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(imagen, "png", baos);
                Image img = new Image(ImageDataFactory.create(baos.toByteArray()));
                img.setAutoScale(true);
                img.setTextAlignment(TextAlignment.CENTER);
                document.add(img);
            } else if (tablaDatos != null) {
                Table pdfTable = new Table(tablaDatos.getColumnCount());
                pdfTable.setWidth(UnitValue.createPercentValue(100));
                for (int i = 0; i < tablaDatos.getColumnCount(); i++) {
                    Cell header = new Cell().add(new Paragraph(tablaDatos.getColumnName(i))).setBackgroundColor(azulMarino).setFontColor(ColorConstants.WHITE).setBold().setTextAlignment(TextAlignment.CENTER).setBorder(new SolidBorder(ColorConstants.LIGHT_GRAY, 0.5f));pdfTable.addHeaderCell(header);
                }
                for (int fila = 0; fila < tablaDatos.getRowCount(); fila++) {
                    for (int col = 0; col < tablaDatos.getColumnCount(); col++) {
                        Object valor = tablaDatos.getValueAt(fila, col);
                        Cell cell = new Cell().add(new Paragraph(valor != null ? valor.toString() : "")).setTextAlignment(TextAlignment.CENTER).setBorder(new SolidBorder(ColorConstants.LIGHT_GRAY, 0.5f));pdfTable.addCell(cell);
                    }
                }
                document.add(pdfTable);
            } else {
                JOptionPane.showMessageDialog(this, "No hay contenido para exportar.");
                document.close();
                return;
            }
            document.add(new Paragraph("\n"));
            Paragraph pie = new Paragraph("Hotel Management System © 2025. Todos los derechos reservados.").setFontSize(9).setFontColor(ColorConstants.GRAY).setTextAlignment(TextAlignment.CENTER);
            document.add(pie);
            document.close();
            JOptionPane.showMessageDialog(this, "Reporte exportado exitosamente a: " + rutaArchivo);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al exportar el PDF: " + e.getMessage());
        }
    }
}
