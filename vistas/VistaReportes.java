package HotelProyectoFinal.vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import javax.imageio.ImageIO;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;

public class VistaReportes extends JPanel {
    JComboBox<String> selectorReporte;
    JButton btnGenerar;
    JButton btnExportarPDF;
    JButton btnVolver;
    ChartPanel panelGrafica;
    JPanel panelGraficaWrapper;

    public VistaReportes() {
        setLayout(new BorderLayout());
        setBackground(new Color(245, 247, 250)); // Fondo muy suave

        // Encabezado
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelSuperior.setBackground(new Color(245, 247, 250));
        JLabel titulo = new JLabel("Reportes del Hotel");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 28));
        titulo.setForeground(new Color(45, 62, 80)); // Texto oscuro suave
        panelSuperior.add(titulo);
        add(panelSuperior, BorderLayout.NORTH);

        // Centro (selector + gráfica)
        JPanel panelCentro = new JPanel(new BorderLayout(15, 15));
        panelCentro.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        panelCentro.setBackground(new Color(245, 247, 250));

        // Panel selector con combo y botones
        JPanel panelSelector = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panelSelector.setBackground(new Color(245, 247, 250));

        selectorReporte = new JComboBox<>(new String[]{
                "Ingresos por Mes",
                "Ocupación de Habitaciones"
        });
        selectorReporte.setPreferredSize(new Dimension(200, 30));

        btnGenerar = crearBoton("Generar Reporte", new Color(52, 152, 219));
        btnExportarPDF = crearBoton("Exportar a PDF", new Color(231, 76, 60));
        btnVolver = crearBoton("🔙 Volver", new Color(149, 165, 166));

        panelSelector.add(selectorReporte);
        panelSelector.add(btnGenerar);
        panelSelector.add(btnExportarPDF);
        panelSelector.add(btnVolver);

        panelCentro.add(panelSelector, BorderLayout.NORTH);

        // Panel para la gráfica
        panelGraficaWrapper = new JPanel(new BorderLayout());
        panelGraficaWrapper.setBackground(new Color(245, 247, 250));
        panelCentro.add(panelGraficaWrapper, BorderLayout.CENTER);

        add(panelCentro, BorderLayout.CENTER);

        mostrarGraficaIngresos();
    }

    private JButton crearBoton(String texto, Color colorFondo) {
        JButton boton = new JButton(texto);
        boton.setBackground(colorFondo);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setFont(new Font("SansSerif", Font.BOLD, 14));
        return boton;
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



