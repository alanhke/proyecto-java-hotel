package HotelProyectoFinal.controladores;

import HotelProyectoFinal.modelos.DatosUsuario;
import HotelProyectoFinal.modelos.DatosUsuarioTableModel;
import HotelProyectoFinal.vistas.*;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.DeviceGray;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.prefs.Preferences;

public class ControlBotones implements ActionListener {
    VistaRegistrarse vistaRegistrarse;
    VistaVerUsuarios vistaVerUsuarios;
    VistaModificarUsuario vistaModificarUsuario;
    VistaPaginaPrincipal vistaPaginaPrincipal;
    VistaGestionarHabitaciones vistaGestionarHabitaciones;
    VistaGestionarReservas vistaGestionarReservas;
    VistaGestionarHuespedes vistaGestionarHuespedes;
    VistaReportes vistaReportes;
    VistaPersonalizar vistaPersonalizar;

    ArrayList<DatosUsuario> datosGuardados;
    DatosUsuarioTableModel table;

    JFrame ventanaModificar;
    JFrame ventanaMostrarDatos;
    JFrame ventanaPrincipal;
    JFrame ventanaGestionarHabitaciones;
    JFrame ventanaGestionarReservas;
    JFrame ventanaGestionarHuespedes;
    JFrame ventanaReportes;
    JFrame ventanaActual;
    JFrame ventanaPersonalizar;

    int filaSeleccionada;
    private static final String RUTA_ARCHIVO = "ultimaRuta";
    public ControlBotones(VistaRegistrarse panel, DatosUsuario datos, VistaVerUsuarios panel2, VistaModificarUsuario panel3, VistaPaginaPrincipal paginaPrincipal, VistaGestionarHabitaciones vistaGestionarHabitaciones1, VistaGestionarReservas vistaGestionarReservas1, VistaGestionarHuespedes vistaGestionarHuespedes1, VistaReportes vistaReportes1, VistaPersonalizar vistaPersonalizar1) {
        vistaRegistrarse = panel;
        vistaVerUsuarios = panel2;
        vistaModificarUsuario = panel3;
        vistaPaginaPrincipal = paginaPrincipal;
        vistaGestionarHabitaciones = vistaGestionarHabitaciones1;
        vistaGestionarReservas = vistaGestionarReservas1;
        vistaGestionarHuespedes = vistaGestionarHuespedes1;
        vistaReportes = vistaReportes1;
        vistaPersonalizar = vistaPersonalizar1;

        table = vistaVerUsuarios.getTable();
        vistaRegistrarse.setListeners(this);
        vistaVerUsuarios.setListeners(this);
        vistaModificarUsuario.setListeners(this);
        vistaPaginaPrincipal.setListeners(this);
        vistaGestionarHabitaciones.setListeners(this);
        vistaGestionarReservas.setListeners(this);
        vistaGestionarHuespedes.setListeners(this);
        vistaReportes.setListeners(this);
        vistaPersonalizar.setListeners(this);

        datosGuardados = new ArrayList<>();

        ventanaPrincipal = new JFrame("Hotel Proyecto");
        ventanaPrincipal.add(vistaPaginaPrincipal);
        ventanaPrincipal.pack();
        ventanaPrincipal.setLocationRelativeTo(null);
        ventanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaPrincipal.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String textoBotonPresionado = ((JButton) e.getSource()).getText();
         if (textoBotonPresionado.equals("Registrarse")) {
            String nombre = vistaRegistrarse.getNombreUsuario();
            String contra = vistaRegistrarse.getPassword().trim();
            String finalPassword = vistaRegistrarse.getFinalPassword().trim();
            String firstName = vistaRegistrarse.getNombre();
            String lastName = vistaRegistrarse.getApellido().trim();
            String genero = vistaRegistrarse.getGender().trim();
            String tipo = vistaRegistrarse.getOpcion();
            boolean isGenderSelected = vistaRegistrarse.getGender1().isSelected() || vistaRegistrarse.getGender2().isSelected() || vistaRegistrarse.getGender3().isSelected();
            if (!nombre.isEmpty() && !contra.isEmpty() && !finalPassword.isEmpty() && !firstName.isEmpty() && isGenderSelected && contra.equals(finalPassword) && !lastName.isEmpty()) {
                DatosUsuario rD = new DatosUsuario(vistaRegistrarse);
                guardarDatos(rD);
                vistaRegistrarse.getTfUserName().setText("");
                vistaRegistrarse.getTfContra().setText("");
                vistaRegistrarse.getTfFinalPassword().setText("");
                vistaRegistrarse.getTfName().setText("");
                vistaRegistrarse.getTfLastName().setText("");
                JOptionPane.showMessageDialog(vistaRegistrarse, "Los datos fueron enviados correctamente");
            }else if (!contra.equals(finalPassword)) {
                //throw new Errores("las contraseñas no son iguales");
                JOptionPane.showMessageDialog(vistaRegistrarse, "Las contraseñas no son iguales", "Error", JOptionPane.WARNING_MESSAGE);
            }else {
                String vacio = vistaRegistrarse.getTextVacio(nombre,contra,finalPassword,firstName, lastName);
                //throw new Errores("El campo " + vacio + " esta vacio.");
                JOptionPane.showMessageDialog(vistaRegistrarse, "El campo " + vacio + " esta vacio.", "Error", JOptionPane.WARNING_MESSAGE);
            }
        }else if (textoBotonPresionado.equals("limpiar")){
            vistaRegistrarse.getTfUserName().setText("");
            vistaRegistrarse.getTfContra().setText("");
            vistaRegistrarse.getTfFinalPassword().setText("");
            vistaRegistrarse.getTfName().setText("");
            vistaRegistrarse.getTfLastName().setText("");
        }else if (textoBotonPresionado.equals("Ver guardados")){
            verDatos();
        } else if (textoBotonPresionado.equals("Limpiar")) {
            table.clear();
        } else if (textoBotonPresionado.equals("Exportar a PDF")) {
            generarPDF();
        } else if (textoBotonPresionado.equals("Eliminar Usuario")) {
            boolean eliminado = eliminarUsuario();
            if (eliminado) {
                JOptionPane.showMessageDialog(vistaRegistrarse, "El usuario se ha eliminado", "Usuario eliminado", JOptionPane.OK_OPTION);
            }else {
                JOptionPane.showMessageDialog(vistaRegistrarse, "El usuario no se ha eliminado", "Error", JOptionPane.WARNING_MESSAGE);
            }
        }else if (textoBotonPresionado.equals("Modificar Usuario")) {
            filaSeleccionada = vistaVerUsuarios.getTableView().getSelectedRow();
            if (filaSeleccionada == -1) {
                JOptionPane.showMessageDialog(vistaVerUsuarios, "Debes seleccionar un usuario de la tabla.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            DatosUsuario usuarioSeleccionado = datosGuardados.get(filaSeleccionada);

            vistaModificarUsuario.setNombre(usuarioSeleccionado.getNombre());
            vistaModificarUsuario.setApellido(usuarioSeleccionado.getApellido());
            vistaModificarUsuario.setNombreUsuario(usuarioSeleccionado.getNombreUsuario());
            vistaModificarUsuario.setPassword(usuarioSeleccionado.getContrasena());
            vistaModificarUsuario.setFinalPassword(usuarioSeleccionado.getConfirmarContrasena());
            vistaModificarUsuario.setBgGender(usuarioSeleccionado.getGenero1());
            vistaModificarUsuario.setOpcion(usuarioSeleccionado.getTipo());

            ventanaModificar = new JFrame("Modificar usuario");
            ventanaModificar.add(vistaModificarUsuario);
            ventanaModificar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            ventanaModificar.setLocationRelativeTo(null);
            ventanaModificar.setSize(500,500);
            ventanaModificar.setVisible(true);
        }else if (textoBotonPresionado.equals("Aceptar")){
            modificarUsuario();
        }else if (textoBotonPresionado.equals("Gestionar Habitaciones")) {
             mostrarVentanaGestionar();
         } else if (textoBotonPresionado.equals("Gestionar Reservas")) {
            mostrarVentanaReservas();
         } else if (textoBotonPresionado.equals("Gestión de Huéspedes")) {
             mostrarVentanaHuespedes();
         } else if (textoBotonPresionado.equals("Ver Reportes")) {
            mostrarVentanaReportes();
         } else if (textoBotonPresionado.equals("Generar Reporte")) {
             String tipo = vistaReportes.getTipoReporteSeleccionado();
             if (tipo.equals("Ingresos por Mes")) {
                 vistaReportes.mostrarGraficaIngresos();
             } else if (tipo.equals("Ocupación de Habitaciones")) {
                 vistaReportes.mostrarGraficaOcupacion();
             }
         } else if (textoBotonPresionado.equals("Exportar a PDF")) {
             JFileChooser selector = new JFileChooser();
             selector.setDialogTitle("Guardar Reporte como PDF");
             int resultado = selector.showSaveDialog(null);
             if (resultado == JFileChooser.APPROVE_OPTION) {
                 File archivo = selector.getSelectedFile();
                 String rutaPDF = archivo.getAbsolutePath();
                 if (!rutaPDF.toLowerCase().endsWith(".pdf")) {
                     rutaPDF += ".pdf";
                 }
                 vistaReportes.exportarGraficaAPDF(rutaPDF);
             }
         } else if (textoBotonPresionado.equals("Volver")) {
             volverAPaginaPrincipal();
         } else if (textoBotonPresionado.equals("Personalizar")) {
            mostrarVentanaPersonalizar();
         }
    }

    public void guardarDatos(DatosUsuario registroDatos2) {
        DatosUsuario.agregarUsuario(registroDatos2);
    }

    public void verDatos(){
        datosGuardados = DatosUsuario.obtenerUsuarios();
        mostrarDatos();
    }

    public void mostrarDatos(){
        ventanaMostrarDatos = new JFrame();
        ventanaMostrarDatos.add(vistaVerUsuarios);
        ventanaMostrarDatos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventanaMostrarDatos.setLocationRelativeTo(null);
        ventanaMostrarDatos.pack();
        ventanaMostrarDatos.setVisible(true);
        ventanaActual = ventanaMostrarDatos;
        table.clear();
        for (DatosUsuario v : datosGuardados) {
            table.addRow(v);
        }
    }

    public void generarPDF(){
        // Personalizar el texto del botón "Cancelar"
        UIManager.put("FileChooser.cancelButtonText", "Cancelar");

        // Abrir un selector de archivos para guardar el PDF
        JFileChooser fileChooser = new JFileChooser("/Users/alan-urias/Documents/Escuela/programacion/JavaVisual/Unidad4/src/ejercicio1");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter pdfs = new FileNameExtensionFilter("Documentos PDF","pdf");
        fileChooser.addChoosableFileFilter(pdfs);
        fileChooser.setFileFilter(pdfs);

        int respuesta = fileChooser.showDialog(vistaVerUsuarios, "Generar PDF");

        if(respuesta == JFileChooser.CANCEL_OPTION) {
            JOptionPane.showMessageDialog(vistaVerUsuarios, "Se canceló la exportación");
            return;
        }

        // Crear el documento PDF
        try (
                PdfDocument pdfDoc = new PdfDocument(new PdfWriter(fileChooser.getSelectedFile()));
                Document doc = new Document(pdfDoc, PageSize.LETTER); // .rotate() Página en horizontal
        ){
            // Agregar una imagen
            InputStream is = getClass().getClassLoader().getResourceAsStream("ejercicio1/resources/uabcs-logo.png");
            System.out.println(is);
            if (is != null) {
                ImageData data = ImageDataFactory.create(is.readAllBytes());
                Image img = new Image(data).scaleAbsolute(50, 50); // Escalar la imagen

                float altoPagina = PageSize.LETTER.getHeight(); //.rotate()
                float margen = 40;
                img.setFixedPosition(margen, altoPagina - margen - 50);

                doc.add(img);
            }
            is = getClass().getClassLoader().getResourceAsStream("ejercicio1/resources/dasc-logo.jpg");
            System.out.println(is);
            if (is != null) {
                ImageData data = ImageDataFactory.create(is.readAllBytes());
                Image img = new Image(data).scaleAbsolute(50, 50); // Escalar la imagen

                float anchoPagina = PageSize.LETTER.getWidth();
                float altoPagina = PageSize.LETTER.getHeight(); //.rotate()
                float margen = 40;
                float anchoImagen = 50;
                img.setFixedPosition(anchoPagina - margen - anchoImagen, altoPagina - margen - 50);

                doc.add(img);
            }

            // Se añade un título al documento
            doc.add(new Paragraph("Registro de usuarios UABCS").setBold().setFontSize(18).setTextAlignment(TextAlignment.CENTER).setMarginBottom(20));
            doc.add(new Paragraph("").setMarginTop(30)); // Se añade un espacio debajo

            LocalDate fechaActual = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String fechaFormateada = fechaActual.format(formatter);

            doc.add(new Paragraph("Fecha: " + fechaFormateada).setBold().setFontSize(12).setTextAlignment(TextAlignment.LEFT));
            doc.add(new Paragraph("").setMarginTop(10));
            doc.add(new LineSeparator(new SolidLine()).setMarginBottom(20));

            /* CREACIÓN DE LA TABLA*/

            // Definir el ancho de las columnas
            float[] anchoColumnas = {1,2,2,3,4,2};
            Table tabla = new Table(UnitValue.createPercentArray(anchoColumnas)).useAllAvailableWidth();

            PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);

            // Encabezado principal del documento
            Cell cell = new Cell(1, 7)
                    .add(new Paragraph("Usuarios UABCS"))
                    .setFont(font)
                    .setFontSize(14)
                    .setFontColor(DeviceGray.WHITE)
                    .setBackgroundColor(new DeviceRgb(45,111,164))
                    .setTextAlignment(TextAlignment.CENTER);
            tabla.addHeaderCell(cell);

            // Crear encabezado y pie de tabla
            for(int i = 0; i < 2; i++) {
                Cell[] headerFooter = new Cell[] {
                        new Cell().setTextAlignment(TextAlignment.CENTER).setBorderTop(new SolidBorder(1f)).setBackgroundColor(new DeviceGray(0.80f)).add(new Paragraph("Nombre")),
                        new Cell().setTextAlignment(TextAlignment.CENTER).setBorderTop(new SolidBorder(1f)).setBackgroundColor(new DeviceGray(0.80f)).add(new Paragraph("Apellidos")),
                        new Cell().setTextAlignment(TextAlignment.CENTER).setBorderTop(new SolidBorder(1f)).setBackgroundColor(new DeviceGray(0.80f)).add(new Paragraph("Nombre de usuario")),
                        new Cell().setTextAlignment(TextAlignment.CENTER).setBorderTop(new SolidBorder(1f)).setBackgroundColor(new DeviceGray(0.80f)).add(new Paragraph("Mensaje")),
                        new Cell().setTextAlignment(TextAlignment.CENTER).setBorderTop(new SolidBorder(1f)).setBackgroundColor(new DeviceGray(0.80f)).add(new Paragraph("Genero")),
                        new Cell().setTextAlignment(TextAlignment.CENTER).setBorderTop(new SolidBorder(1f)).setBackgroundColor(new DeviceGray(0.80f)).add(new Paragraph("Tipo")),
                };

                for(Cell celda : headerFooter) {
                    if(i == 0) {
                        tabla.addHeaderCell(celda);
                    }/*else {
                        tabla.addFooterCell(celda);
                    }*/
                }
            }

            // Llenar la tabla con los datos de ventas
            int indice = 1;
            double total = 0;

            boolean alternarColor = false;
            for(DatosUsuario d : datosGuardados) {
                DeviceRgb filaColor = alternarColor ? new DeviceRgb(245, 245, 245) : (DeviceRgb) DeviceRgb.WHITE;
                tabla.addCell(new Cell().setBackgroundColor(filaColor).setTextAlignment(TextAlignment.CENTER).add(new Paragraph(d.getNombre())));
                tabla.addCell(new Cell().setBackgroundColor(filaColor).setTextAlignment(TextAlignment.CENTER).add(new Paragraph(d.getApellido())));
                tabla.addCell(new Cell().setBackgroundColor(filaColor).setTextAlignment(TextAlignment.CENTER).add(new Paragraph(d.getNombreUsuario())));
                tabla.addCell(new Cell().setBackgroundColor(filaColor).setTextAlignment(TextAlignment.CENTER).add(new Paragraph(d.getGenero1())));
                tabla.addCell(new Cell().setBackgroundColor(filaColor).setTextAlignment(TextAlignment.CENTER).add(new Paragraph(d.getTipo())));
                indice++;
                alternarColor = !alternarColor;
            }

            doc.add(tabla); // Añadir la tabla al documento
            doc.add(new Paragraph("").setMarginTop(30));

            doc.add(new Paragraph("Informacion de contacto:").setBold().setFontSize(12).setTextAlignment(TextAlignment.LEFT));
            doc.add(new Paragraph("Número telefonico: 612 123 8800").setFontSize(12).setTextAlignment(TextAlignment.LEFT));
            doc.add(new Paragraph("Pagina web: https://www.uabcs.mx/").setFontSize(12).setTextAlignment(TextAlignment.LEFT));
            doc.add(new Paragraph("Dirección: Sur KM 5.5, Universidad Autónoma de Baja California Sur, 23085 La Paz, B.C.S.").setFontSize(12).setTextAlignment(TextAlignment.LEFT));
            doc.add(new Paragraph("").setMarginTop(30));
            doc.add(new LineSeparator(new SolidLine()).setMarginTop(30));

            doc.add(new Paragraph("Documento generado automáticamente por el sistema UABCS").setFontSize(10).setTextAlignment(TextAlignment.CENTER).setFontColor(DeviceGray.GRAY));


            // Intentar abrir el PDF automáticamente al finalizar
            if(Desktop.isDesktopSupported()) {
                try {
                    Desktop.getDesktop().open(fileChooser.getSelectedFile());
                }catch(IOException ex) {
                    //JOptionPane.showMessageDialog(this, "No se pudo abrir el archivo");
                }
            }

        }catch(IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(vistaVerUsuarios, "No se pudo exportar el PDF");
        }
    }

    public boolean eliminarUsuario(){
        filaSeleccionada = vistaVerUsuarios.getTableView().getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(vistaVerUsuarios, "Debes seleccionar un usuario para eliminar", "Error", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        DatosUsuario usuario = datosGuardados.get(filaSeleccionada);

        int confirmacion = JOptionPane.showConfirmDialog(vistaVerUsuarios, "¿Estás seguro de eliminar a " + usuario.getNombreUsuario() + "?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            DatosUsuario.eliminarUsu(filaSeleccionada+1);
            datosGuardados.remove(filaSeleccionada);
            table.removeRow(filaSeleccionada);
            return true;
        }

        return false;
    }

    public void modificarUsuario(){
        String nombre = vistaModificarUsuario.getNombreUsuario();
        String contra = vistaModificarUsuario.getPassword().trim();
        String finalPassword = vistaModificarUsuario.getFinalPassword().trim();
        String firstName = vistaModificarUsuario.getNombre();
        String lastName = vistaModificarUsuario.getApellido().trim();
        String genero = vistaModificarUsuario.getGender().trim();
        String tipo = vistaModificarUsuario.getOpcion();
        boolean isGenderSelected = vistaModificarUsuario.getGender1().isSelected() || vistaModificarUsuario.getGender2().isSelected() || vistaModificarUsuario.getGender3().isSelected();
        if (!nombre.isEmpty() && !contra.isEmpty() && !finalPassword.isEmpty() && !firstName.isEmpty() && isGenderSelected && contra.equals(finalPassword) && !lastName.isEmpty()) {
            DatosUsuario rD = new DatosUsuario(vistaModificarUsuario);
            DatosUsuario.actualizarUsuario(rD, filaSeleccionada+1);
            vistaModificarUsuario.getTfUserName().setText("");
            vistaModificarUsuario.getTfContra().setText("");
            vistaModificarUsuario.getTfFinalPassword().setText("");
            vistaModificarUsuario.getTfName().setText("");
            vistaModificarUsuario.getTfLastName().setText("");
            ventanaMostrarDatos.dispose();
            verDatos();
            JOptionPane.showMessageDialog(vistaModificarUsuario, "Usuario actualizado con exito","Modificado",JOptionPane.OK_OPTION);
            ventanaModificar.dispose();
        }else if (!contra.equals(finalPassword)) {
            //throw new Errores("las contraseñas no son iguales");
            JOptionPane.showMessageDialog(vistaModificarUsuario, "Las contraseñas no son iguales", "Error", JOptionPane.WARNING_MESSAGE);
        }else {
            String vacio = vistaModificarUsuario.getTextVacio(nombre,contra,finalPassword,firstName, lastName);
            //throw new Errores("El campo " + vacio + " esta vacio.");
            JOptionPane.showMessageDialog(vistaModificarUsuario, "El campo " + vacio + " esta vacio.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void mostrarVentanaGestionar(){
        ventanaGestionarHabitaciones = new JFrame("Gestionar Habitaciones");
        ventanaGestionarHabitaciones.add(vistaGestionarHabitaciones);
        ventanaGestionarHabitaciones.setSize(1000,500);
        ventanaGestionarHabitaciones.pack();
        ventanaGestionarHabitaciones.setLocationRelativeTo(null);
        ventanaGestionarHabitaciones.setVisible(true);
        ventanaActual = ventanaGestionarHabitaciones;
        ventanaPrincipal.setVisible(false);
    }

    public void mostrarVentanaReservas(){
        ventanaGestionarReservas = new JFrame("Gestionar Reservas");
        ventanaGestionarReservas.add(vistaGestionarReservas);
        ventanaGestionarReservas.setSize(1000,500);
        ventanaGestionarReservas.pack();
        ventanaGestionarReservas.setLocationRelativeTo(null);
        ventanaGestionarReservas.setVisible(true);
        ventanaActual = ventanaGestionarReservas;
        ventanaPrincipal.setVisible(false);
    }

    public void mostrarVentanaHuespedes(){
        ventanaGestionarHuespedes = new JFrame("Gestionar Huespedes");
        ventanaGestionarHuespedes.add(vistaGestionarHuespedes);
        ventanaGestionarHuespedes.setSize(1000,500);
        ventanaGestionarHuespedes.pack();
        ventanaGestionarHuespedes.setLocationRelativeTo(null);
        ventanaGestionarHuespedes.setVisible(true);
        ventanaPrincipal.setVisible(false);
    }

    public void mostrarVentanaReportes(){
        ventanaReportes = new JFrame("Reportes");
        ventanaReportes.setSize(500,500);
        ventanaReportes.add(vistaReportes);
        //ventanaReportes.pack();
        ventanaReportes.setLocationRelativeTo(null);
        ventanaReportes.setVisible(true);
        ventanaActual = ventanaReportes;
        ventanaPrincipal.setVisible(false);
    }

    public void mostrarVentanaPersonalizar(){
        ventanaPersonalizar = new JFrame("Personalizar");
        ventanaPersonalizar.setSize(500,500);
        ventanaPersonalizar.add(vistaPersonalizar);
        //ventanaReportes.pack();
        ventanaPersonalizar.setLocationRelativeTo(null);
        ventanaPersonalizar.setVisible(true);
        ventanaActual = ventanaPersonalizar;
        ventanaPrincipal.setVisible(false);
    }

    public void volverAPaginaPrincipal(){
        ventanaPrincipal.setVisible(true);
        ventanaActual.setVisible(false);
    }

}
