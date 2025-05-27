package HotelProyectoFinal.vistas;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.layout.element.Image;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.io.File;

public class VistaReportes extends JPanel {
    JComboBox<String> selectorReporte;
    JButton btnGenerar;
    JButton btnExportarPDF;
    JButton btnVolver;
    ChartPanel panelGrafica;

    JPanel panelGraficaWrapper;

    public VistaReportes() {
        setLayout(new BorderLayout());
        setBackground(new Color(250, 250, 255));

        // ---------- Encabezado ----------
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelSuperior.setBackground(new Color(250, 250, 255));

        JLabel titulo = new JLabel("Reportes del Hotel");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 24));
        panelSuperior.add(titulo);

        add(panelSuperior, BorderLayout.NORTH);

        // ---------- Centro (Selector + Gráfica) ----------
        JPanel panelCentro = new JPanel();
        panelCentro.setLayout(new BorderLayout(10, 10));
        panelCentro.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panelCentro.setBackground(new Color(250, 250, 255));

        // Selector + botón
        JPanel panelSelector = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelSelector.setBackground(new Color(250, 250, 255));

        selectorReporte = new JComboBox<>(new String[]{
                "Ingresos por Mes",
                "Ocupación de Habitaciones"
        });

        btnGenerar = new JButton("Generar Reporte");
        panelSelector.add(selectorReporte);
        panelSelector.add(btnGenerar);

        panelCentro.add(panelSelector, BorderLayout.NORTH);

        // Panel para la gráfica
        panelGraficaWrapper = new JPanel(new BorderLayout());
        panelGraficaWrapper.setBackground(new Color(250, 250, 255));
        panelCentro.add(panelGraficaWrapper, BorderLayout.CENTER);

        add(panelCentro, BorderLayout.CENTER);

        // ---------- Pie de página (botones de acción) ----------
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelInferior.setBackground(new Color(250, 250, 255));

        btnExportarPDF = new JButton("Exportar a PDF");
        btnVolver = new JButton("Volver");

        panelInferior.add(btnExportarPDF);
        panelInferior.add(btnVolver);

        add(panelInferior, BorderLayout.SOUTH);

        // Gráfica inicial
        mostrarGraficaIngresos();
    }

    public void mostrarGraficaIngresos() {
        panelGraficaWrapper.removeAll();

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(12000, "Ingresos", "Enero");
        dataset.addValue(15000, "Ingresos", "Febrero");
        dataset.addValue(17000, "Ingresos", "Marzo");
        dataset.addValue(14000, "Ingresos", "Abril");
        dataset.addValue(18000, "Ingresos", "Mayo");

        JFreeChart chart = ChartFactory.createBarChart(
                "Ingresos por Mes", "Mes", "Monto (MXN)", dataset
        );

        panelGrafica = new ChartPanel(chart);
        panelGraficaWrapper.add(panelGrafica, BorderLayout.CENTER);

        revalidate();
        repaint();
    }

    public void mostrarGraficaOcupacion() {
        panelGraficaWrapper.removeAll();

        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Habitaciones Ocupadas", 12);
        dataset.setValue("Habitaciones Disponibles", 8);

        JFreeChart chart = ChartFactory.createPieChart(
                "Ocupación de Habitaciones", dataset, true, true, false
        );

        panelGrafica = new ChartPanel(chart);
        panelGraficaWrapper.add(panelGrafica, BorderLayout.CENTER);

        revalidate();
        repaint();
    }

    public void setListeners(java.awt.event.ActionListener listener) {
        btnGenerar.addActionListener(listener);
        btnExportarPDF.addActionListener(listener);
        btnVolver.addActionListener(listener);
    }

    public String getTipoReporteSeleccionado() {
        return (String) selectorReporte.getSelectedItem();
    }

    public void exportarGraficaAPDF(String rutaArchivo) {
        try {
            BufferedImage imagen = panelGrafica.getChart().createBufferedImage(600, 400);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(imagen, "png", baos);

            PdfWriter writer = new PdfWriter(rutaArchivo);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            Image img = new Image(ImageDataFactory.create(baos.toByteArray()));
            img.setAutoScale(true);
            document.add(img);
            document.close();

            JOptionPane.showMessageDialog(this, "Reporte exportado exitosamente a: " + rutaArchivo);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al exportar el PDF: " + e.getMessage());
        }
    }
}
