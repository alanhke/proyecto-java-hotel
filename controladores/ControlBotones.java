package HotelProyectoFinal.controladores;

import HotelProyectoFinal.modelos.*;
import HotelProyectoFinal.utilities.Estilo;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class ControlBotones implements ActionListener {
    VistaInicioSesion vistaInicioSesion;
    VistaRegistrarse vistaRegistrarse;
    VistaVerUsuarios vistaVerUsuarios;
    VistaModificarUsuario vistaModificarUsuario;
    VistaPaginaPrincipal vistaPaginaPrincipal;
    VistaGestionarHabitaciones vistaGestionarHabitaciones;
    VistaGestionarReservas vistaGestionarReservas;
    VistaGestionarHuespedes vistaGestionarHuespedes;
    VistaReportes vistaReportes;
    VistaPersonalizar vistaPersonalizar;
    VistaCrearModificarHabitacion vistaCrearModificarHabitacion;
    Estilo estilo;
    VistaCrearModificarReserva vistaCrearModificarReserva;
    VistaCrearModificarHuesped vistaCrearModificarHuesped;
    VistaVerPerfil vistaVerPerfil;

    ArrayList<DatosUsuario> datosGuardados;
    DatosUsuarioTableModel table;

    ArrayList<Habitaciones> habitaciones;
    HabitacionesTableModel habitacionesTableModel;
    Habitaciones habitacionSeleccionada;

    ArrayList<Huespedes> huespedes;
    HuespedesTableModel huespedesTableModel;
    Huespedes huespedSeleccionado;

    ArrayList<Reservas> reservas;
    ReservasTableModel reservasTableModel;
    Reservas reservaSeleccionada;

    JFrame ventanaInicioSesion;
    JFrame ventanaRegistro;
    JFrame ventanaModificar;
    JFrame ventanaMostrarDatos;
    JFrame ventanaPrincipal;
    JFrame ventanaGestionarHabitaciones;
    JFrame ventanaGestionarReservas;
    JFrame ventanaGestionarHuespedes;
    JFrame ventanaReportes;
    JFrame ventanaActual;
    JFrame ventanaPersonalizar;
    JFrame ventanaCrearModificarHabitacion;
    JFrame ventanCrearModificarReservas;
    JFrame ventanaCrearModificarHuespedes;
    JFrame ventanaVerPerfil;

    String crearOModificar;

    int filaSeleccionada;
    private static final String RUTA_ARCHIVO = "ultimaRuta";
    public ControlBotones(VistaRegistrarse panel, DatosUsuario datos, VistaVerUsuarios panel2, VistaModificarUsuario panel3, VistaPaginaPrincipal paginaPrincipal, VistaGestionarHabitaciones vistaGestionarHabitaciones1, VistaGestionarReservas vistaGestionarReservas1, VistaGestionarHuespedes vistaGestionarHuespedes1, VistaReportes vistaReportes1, VistaPersonalizar vistaPersonalizar1, Estilo estilo1, VistaInicioSesion vistaInicioSesion1, VistaCrearModificarHabitacion vistaCrearModificarHabitacion1, VistaCrearModificarReserva vistaCrearModificarReserva1, VistaCrearModificarHuesped vistaCrearModificarHuesped1, VistaVerPerfil vistaVerPerfil1) {
        vistaRegistrarse = panel;
        vistaVerUsuarios = panel2;
        vistaModificarUsuario = panel3;
        vistaPaginaPrincipal = paginaPrincipal;
        vistaGestionarHabitaciones = vistaGestionarHabitaciones1;
        vistaGestionarReservas = vistaGestionarReservas1;
        vistaGestionarHuespedes = vistaGestionarHuespedes1;
        vistaReportes = vistaReportes1;
        vistaPersonalizar = vistaPersonalizar1;
        estilo = estilo1;
        vistaInicioSesion = vistaInicioSesion1;
        vistaCrearModificarHabitacion = vistaCrearModificarHabitacion1;
        vistaCrearModificarReserva = vistaCrearModificarReserva1;
        vistaCrearModificarHuesped = vistaCrearModificarHuesped1;
        vistaVerPerfil = vistaVerPerfil1;

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
        vistaInicioSesion.setListeners(this);
        vistaCrearModificarHabitacion.setListeners(this);
        vistaCrearModificarReserva.setListeners(this);
        vistaCrearModificarHuesped.setListeners(this);
        vistaVerPerfil.setListeners(this);


        datosGuardados = new ArrayList<>();

        habitaciones = new ArrayList<>();
        habitacionesTableModel = vistaGestionarHabitaciones.getTable();
        huespedes = new ArrayList<>();
        huespedesTableModel = vistaGestionarHuespedes.getTable();
        reservas = new ArrayList<>();
        reservasTableModel = vistaGestionarReservas.getTable();

        cargarVentanas();

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
            boolean isGenderSelected = !vistaRegistrarse.getGender().isEmpty();
            if (!nombre.isEmpty() && !contra.isEmpty() && !finalPassword.isEmpty() && !firstName.isEmpty() && isGenderSelected && contra.equals(finalPassword) && !lastName.isEmpty()) {
                DatosUsuario rD = new DatosUsuario(vistaRegistrarse);
                guardarDatos(rD);
                vistaRegistrarse.getTfUserName().setText("");
                vistaRegistrarse.getTfContra().setText("");
                vistaRegistrarse.getTfFinalPassword().setText("");
                vistaRegistrarse.getTfName().setText("");
                vistaRegistrarse.getTfLastName().setText("");
                JOptionPane.showMessageDialog(vistaRegistrarse, "Los datos fueron enviados correctamente");
            } else if (!contra.equals(finalPassword)) {
                //throw new Errores("las contraseñas no son iguales");
                JOptionPane.showMessageDialog(vistaRegistrarse, "Las contraseñas no son iguales", "Error", JOptionPane.WARNING_MESSAGE);
            } else {
                String vacio = vistaRegistrarse.getTextVacio(nombre, contra, finalPassword, firstName, lastName);
                //throw new Errores("El campo " + vacio + " esta vacio.");
                JOptionPane.showMessageDialog(vistaRegistrarse, "El campo " + vacio + " esta vacio.", "Error", JOptionPane.WARNING_MESSAGE);
            }
        } else if (textoBotonPresionado.equals("limpiar")) {
            vistaRegistrarse.getTfUserName().setText("");
            vistaRegistrarse.getTfContra().setText("");
            vistaRegistrarse.getTfFinalPassword().setText("");
            vistaRegistrarse.getTfName().setText("");
            vistaRegistrarse.getTfLastName().setText("");
        } else if (textoBotonPresionado.equals("Ver guardados")) {
            verDatos();
        } else if (textoBotonPresionado.equals("Limpiar")) {
            table.clear();
        } else if (textoBotonPresionado.equals("Exportar a PDF")) {
            generarPDF();
        } else if (textoBotonPresionado.equals("Eliminar Usuario")) {
            boolean eliminado = eliminarUsuario();
            if (eliminado) {
                JOptionPane.showMessageDialog(vistaRegistrarse, "El usuario se ha eliminado", "Usuario eliminado", JOptionPane.OK_OPTION);
            } else {
                JOptionPane.showMessageDialog(vistaRegistrarse, "El usuario no se ha eliminado", "Error", JOptionPane.WARNING_MESSAGE);
            }
        } else if (textoBotonPresionado.equals("Modificar Usuario")) {
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

            ventanaModificar.add(vistaModificarUsuario);
            ventanaModificar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            ventanaModificar.setLocationRelativeTo(null);
            ventanaModificar.setSize(500, 500);
            ventanaModificar.setVisible(true);
        } else if (textoBotonPresionado.equals("Aceptar")) {
            modificarUsuario();
        } else if (textoBotonPresionado.equals("🛏 Gestionar Habitaciones")) {
            mostrarVentanaGestionar();
        } else if (textoBotonPresionado.equals("📅 Gestionar Reservas")) {
            mostrarVentanaReservas();
        } else if (textoBotonPresionado.equals("🧳 Gestión de Huéspedes")) {
            mostrarVentanaHuespedes();
        } else if (textoBotonPresionado.equals("📊 Ver Reportes")) {
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
        } else if (textoBotonPresionado.equals("🔙 Volver")) {
            volverAPaginaPrincipal();
        } else if (textoBotonPresionado.equals("🎨 Personalizar")) {
            mostrarVentanaPersonalizar();
        }else if (textoBotonPresionado.equals("👤 Ver Perfil")) {
            mostrarVentanaVerPerfil();
        }else if (textoBotonPresionado.equals("⏻ Cerrar Sesión")) {
            volverAInicioDeSesion();
        } else if (textoBotonPresionado.equals("Aplicar Cambios")) {
            aplicarPersonalizacion();
        } else if (textoBotonPresionado.equals("Iniciar Sesión")) {
            mostrarVentanaPrincipal();
        }//Botones vista habitaciones
        else if (textoBotonPresionado.equals("Dar de alta")) {
           mostarVentanaRegistarse();
        }
        else if (textoBotonPresionado.equals("🧹 Limpiar Tabla Habitaciones")) {
            habitacionesTableModel.clear();
        } else if (textoBotonPresionado.equals("✏️ Modificar Habitacion")) {
            //filaSeleccionada = vistaGestionarHabitaciones.getTableView().getSelectedRow();
            crearOModificar = "Modificar";
            mostrarVentanaCrearModificarHabitacion();
        } else if (textoBotonPresionado.equals("🗑️ Eliminar Habitacion")) {
            eliminarHabitacion();
        } else if (textoBotonPresionado.equals("➕ Crear Habitacion")) {
            filaSeleccionada = vistaGestionarHabitaciones.getTableView().getSelectedRow();
            crearOModificar = "Crear";
            mostrarVentanaCrearModificarHabitacion();
        }//Botones vista crear modificar habitacion
        else if (textoBotonPresionado.equals("✔️Aceptar habitación")) {
            if (crearOModificar.equalsIgnoreCase("Crear")) {
                guardarHabitacion();
            } else if (crearOModificar.equalsIgnoreCase("Modificar")) {
                modificarHabitacion();
            }
        } else if (textoBotonPresionado.equals("❌ Cancelar habitación")) {
            volverAGestionarHabitaciones();
        }//Botones vista reservas
        else if (textoBotonPresionado.equals("🧹 Limpiar tabla reservas")) {
            reservasTableModel.clear();
        } else if (textoBotonPresionado.equals("➕ Crear Reserva")) {
            crearOModificar = "Crear";
            mostrarVentanaCrearModificarReserva();
        } else if (textoBotonPresionado.equals("✏️ Modificar Reserva")) {
            crearOModificar = "Modificar";
            mostrarVentanaCrearModificarReserva();
        } else if (textoBotonPresionado.equals("🗑️ Eliminar Reserva")) {
            eliminarReserva();
        }//botones vista crear modificar reservas
        else if (textoBotonPresionado.equals("✔️Aceptar reserva")) {
            if (crearOModificar.equalsIgnoreCase("Crear")) {
                guardarReserva();
            } else if (crearOModificar.equalsIgnoreCase("Modificar")) {
                modificarReserva();
            }
        } else if (textoBotonPresionado.equals("❌ Cancelar reserva")) {
            volverAGestionarReservas();
        }
        //botones vista huespedes
        else if (textoBotonPresionado.equals("🧹 Limpiar tabla huéspedes")) {
            huespedesTableModel.clear();
        } else if (textoBotonPresionado.equals("➕ Crear Huésped")) {
            crearOModificar = "Crear";
            mostrarVentanaCrearModificarHuesped();
        }else if (textoBotonPresionado.equals("✏️ Modificar Huésped")) {
            crearOModificar = "Modificar";
            mostrarVentanaCrearModificarHuesped();
        }else if (textoBotonPresionado.equals("🗑️ Eliminar Huésped")) {
            eliminarHuesped();
        }//Botones vista crear modificar huesped
        else if (textoBotonPresionado.equals("✔️Aceptar Huésped")) {
            if (crearOModificar.equalsIgnoreCase("Crear")) {
                guardarHuesped();
            } else if (crearOModificar.equalsIgnoreCase("Modificar")) {
                modificarHuesped();
            }
        } else if (textoBotonPresionado.equals("❌ Cancelar Huésped")) {
            volverAGestionarHuespedes();
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
        boolean isGenderSelected = !vistaRegistrarse.getGender().isEmpty();
        if (!nombre.isEmpty() && !contra.isEmpty() && !finalPassword.isEmpty() && !firstName.isEmpty() && isGenderSelected && contra.equals(finalPassword) && !lastName.isEmpty()) {
            DatosUsuario rD = new DatosUsuario(vistaRegistrarse);
            guardarDatos(rD);
            vistaRegistrarse.getTfUserName().setText("");
            vistaRegistrarse.getTfContra().setText("");
            vistaRegistrarse.getTfFinalPassword().setText("");
            vistaRegistrarse.getTfName().setText("");
            vistaRegistrarse.getTfLastName().setText("");
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
        ventanaGestionarHabitaciones.add(vistaGestionarHabitaciones);
        ventanaGestionarHabitaciones.setSize(1000,500);
        //ventanaGestionarHabitaciones.pack();
        ventanaGestionarHabitaciones.setLocationRelativeTo(null);
        //ventanaGestionarHabitaciones.setDefaultCloseOperation();
        ventanaGestionarHabitaciones.setVisible(true);
        ventanaActual = ventanaGestionarHabitaciones;
        ventanaPrincipal.dispose();
        cargarHabitaciones();
    }
    public void mostrarVentanaCrearModificarHabitacion(){
        if (crearOModificar.equals("Modificar")){
            filaSeleccionada = vistaGestionarHabitaciones.getTableView().getSelectedRow();

            if (filaSeleccionada == -1) {
                JOptionPane.showMessageDialog(vistaGestionarHabitaciones, "Debes seleccionar una habitacion para modificar", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
            habitacionSeleccionada = habitaciones.get(filaSeleccionada);
            vistaCrearModificarHabitacion.setTipo(habitacionSeleccionada.getTipo());
            vistaCrearModificarHabitacion.setEstado(habitacionSeleccionada.getEstado());
            vistaCrearModificarHabitacion.setPrecio(String.valueOf(habitacionSeleccionada.getPrecio()));
        }
        ventanaCrearModificarHabitacion.setSize(500,500);
        ventanaCrearModificarHabitacion.add(vistaCrearModificarHabitacion);
        ventanaCrearModificarHabitacion.setLocationRelativeTo(null);
        ventanaCrearModificarHabitacion.setVisible(true);
        //ventanaActual.dispose();
        ventanaActual = ventanaCrearModificarHabitacion;
    }

    public void mostrarVentanaReservas(){
        ventanaGestionarReservas.add(vistaGestionarReservas);
        ventanaGestionarReservas.setSize(1000,500);
        //ventanaGestionarReservas.pack();
        ventanaGestionarReservas.setLocationRelativeTo(null);
        ventanaGestionarReservas.setVisible(true);
        ventanaActual = ventanaGestionarReservas;
        ventanaPrincipal.dispose();
        cargarReservas();
    }
    public void mostrarVentanaCrearModificarReserva(){
        if (crearOModificar.equals("Modificar")){
            filaSeleccionada = vistaGestionarReservas.getTableView().getSelectedRow();

            if (filaSeleccionada == -1) {
                JOptionPane.showMessageDialog(vistaGestionarReservas, "Debes seleccionar una reserva para modificar", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
            reservaSeleccionada = reservas.get(filaSeleccionada);
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy/MM/dd");
            vistaCrearModificarReserva.setReservaIdJtextField(String.valueOf(reservaSeleccionada.getId()));
            vistaCrearModificarReserva.setIdHuesped(String.valueOf(reservaSeleccionada.getHuespedId()));
            vistaCrearModificarReserva.setFechaEntrada(reservaSeleccionada.getFechaEntrada());
            vistaCrearModificarReserva.setFechaSalida(reservaSeleccionada.getFechaSalida());
        }
        ventanCrearModificarReservas.add(vistaCrearModificarReserva);
        ventanCrearModificarReservas.setSize(1000,500);
        //ventanaGestionarHuespedes.pack();
        ventanCrearModificarReservas.setLocationRelativeTo(null);
        ventanCrearModificarReservas.setVisible(true);
        ventanaGestionarReservas.dispose();
        ventanaActual = ventanCrearModificarReservas;
    }

    public void mostrarVentanaHuespedes(){
        ventanaGestionarHuespedes.add(vistaGestionarHuespedes);
        ventanaGestionarHuespedes.setSize(1000,500);
        //ventanaGestionarHuespedes.pack();
        ventanaGestionarHuespedes.setLocationRelativeTo(null);
        ventanaGestionarHuespedes.setVisible(true);
        ventanaActual = ventanaGestionarHuespedes;
        ventanaPrincipal.dispose();
        cargarHuespedes();
    }
    public void mostrarVentanaCrearModificarHuesped(){
        if (crearOModificar.equals("Modificar")){
            filaSeleccionada = vistaGestionarHuespedes.getTableView().getSelectedRow();

            if (filaSeleccionada == -1) {
                JOptionPane.showMessageDialog(vistaGestionarHuespedes, "Debes seleccionar un huesped para modificar", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
            huespedSeleccionado = huespedes.get(filaSeleccionada);
            vistaCrearModificarHuesped.setHuespedId(String.valueOf(huespedSeleccionado.getId()));
            vistaCrearModificarHuesped.setNombres(huespedSeleccionado.getNombre());
            vistaCrearModificarHuesped.setCorreo(huespedSeleccionado.getCorreo());
            vistaCrearModificarHuesped.setDireccion(huespedSeleccionado.getDireccion());
            vistaCrearModificarHuesped.setTelefono(huespedSeleccionado.getTelefono());
            vistaCrearModificarHuesped.setDocumentoIdentidad(huespedSeleccionado.getDocumentoIdentidad());
        }
        ventanaCrearModificarHuespedes.add(vistaCrearModificarHuesped);
        ventanaCrearModificarHuespedes.setSize(1000,500);
        //ventanaGestionarHuespedes.pack();
        ventanaCrearModificarHuespedes.setLocationRelativeTo(null);
        ventanaCrearModificarHuespedes.setVisible(true);
        ventanaGestionarHuespedes.dispose();
        ventanaActual = ventanaCrearModificarHuespedes;
    }

    public void mostrarVentanaReportes(){
        ventanaReportes.setSize(1000,500);
        ventanaReportes.add(vistaReportes);
        //ventanaReportes.pack();
        ventanaReportes.setLocationRelativeTo(null);
        ventanaReportes.setVisible(true);
        ventanaActual = ventanaReportes;
        ventanaPrincipal.dispose();
    }

    public void mostrarVentanaPersonalizar(){
        ventanaPersonalizar.setSize(1000,500);
        ventanaPersonalizar.add(vistaPersonalizar);
        //ventanaReportes.pack();
        ventanaPersonalizar.setLocationRelativeTo(null);
        ventanaPersonalizar.setVisible(true);
        ventanaActual = ventanaPersonalizar;
        ventanaPrincipal.dispose();
    }

    public void mostrarVentanaVerPerfil(){
        ventanaVerPerfil.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaVerPerfil.setSize(800, 600);
        ventanaVerPerfil.setLocationRelativeTo(null);
        ventanaVerPerfil.add(vistaVerPerfil);
        ventanaVerPerfil.setVisible(true);
        ventanaActual = ventanaVerPerfil;
        ventanaPrincipal.dispose();
    }

    public void volverAPaginaPrincipal(){
        ventanaPrincipal.setVisible(true);
        ventanaActual.dispose();
    }
    public void volverAInicioDeSesion(){
        ventanaPrincipal.dispose();
        ventanaInicioSesion.setVisible(true);
    }
    public void volverAGestionarHabitaciones(){
        ventanaGestionarHabitaciones.setVisible(true);
        ventanaActual.dispose();
        cargarHabitaciones();
    }
    public void volverAGestionarReservas(){
        ventanaGestionarReservas.setVisible(true);
        ventanaActual.dispose();
        cargarReservas();
    }
    public void volverAGestionarHuespedes(){
        ventanaGestionarHuespedes.setVisible(true);
        ventanaActual.dispose();
        cargarHuespedes();
    }

    public void aplicarPersonalizacion() {
        String tema = vistaPersonalizar.getTemaSeleccionado();
        String fuente = vistaPersonalizar.getFuenteSeleccionada();
        int tamano = vistaPersonalizar.getTamanoFuenteSeleccionado();
        ArrayList<Component> vistas = new ArrayList<>();
        vistas.add(vistaRegistrarse);
        vistas.add(vistaVerUsuarios);
        vistas.add(vistaModificarUsuario);
        vistas.add(vistaPaginaPrincipal);
        vistas.add(vistaGestionarHabitaciones);
        vistas.add(vistaGestionarReservas);
        vistas.add(vistaGestionarHuespedes);
        vistas.add(vistaReportes);
        vistas.add(vistaPersonalizar);
        vistas.add(vistaCrearModificarHabitacion);
        vistas.add(vistaCrearModificarReserva);
        vistas.add(vistaCrearModificarHuesped);
        for (Component vist : vistas) {
            Estilo.aplicarEstiloGlobal(vist, fuente, tamano, tema);
        }
        SwingUtilities.updateComponentTreeUI(ventanaPrincipal);
        SwingUtilities.updateComponentTreeUI(ventanaGestionarHabitaciones);
        SwingUtilities.updateComponentTreeUI(ventanaGestionarReservas);
        SwingUtilities.updateComponentTreeUI(ventanaGestionarHuespedes);
        SwingUtilities.updateComponentTreeUI(ventanaReportes);
        SwingUtilities.updateComponentTreeUI(ventanaMostrarDatos);
        SwingUtilities.updateComponentTreeUI(ventanaModificar);
        SwingUtilities.updateComponentTreeUI(ventanaPersonalizar);
    }

    public void cargarVentanas(){
        ventanaInicioSesion = new JFrame("Inicio Sesion");
        ventanaInicioSesion.add(vistaInicioSesion);
        ventanaInicioSesion.setSize(1000,500);
        //ventanaPrincipal.pack();
        ventanaInicioSesion.setLocationRelativeTo(null);
        ventanaInicioSesion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaInicioSesion.setVisible(true);

        //Demas ventanas
        ventanaPrincipal = new JFrame("Hotel Proyecto");
        ventanaVerPerfil = new JFrame("Perfil del Usuario");
        ventanaGestionarHabitaciones = new JFrame("Gestionar Habitaciones");
        ventanaGestionarReservas = new JFrame("Gestionar Reservas");
        ventanaGestionarHuespedes = new JFrame("Gestionar Huespedes");
        ventanaReportes = new JFrame("Reportes");
        ventanaPersonalizar = new JFrame("Personalizar");
        ventanaModificar = new JFrame("Modificar usuario");
        ventanaMostrarDatos = new JFrame();
        ventanaCrearModificarHabitacion = new JFrame("Habitacion");
        ventanCrearModificarReservas = new JFrame("Reservas");
        ventanaCrearModificarHuespedes = new JFrame("Huespedes");
    }

    public void mostrarVentanaPrincipal(){
        habitaciones = Habitaciones.obtenerHabitaciones();
        int ocupadas = 0;
        int disponibles = 0;
        int enLimpieza = 0;
        for (Habitaciones h : habitaciones) {
            String estado = h.getEstado();
            if (estado.equalsIgnoreCase("ocupado")) {
                ocupadas++;
            }else if (estado.equalsIgnoreCase("disponible")) {
                disponibles++;
            }else if (estado.equalsIgnoreCase("enLimpieza")) {
                enLimpieza++;
            }
        }
        vistaPaginaPrincipal.cargarGrafica(ocupadas, disponibles, enLimpieza);
        ventanaPrincipal.add(vistaPaginaPrincipal);
        ventanaPrincipal.setSize(1000,500);
        //ventanaPrincipal.pack();
        ventanaPrincipal.setLocationRelativeTo(null);
        ventanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaPrincipal.setVisible(true);
        ventanaInicioSesion.dispose();

    }

    public void mostarVentanaRegistarse(){
        vistaRegistrarse = new VistaRegistrarse();
        vistaRegistrarse.setListeners(this);

        ventanaRegistro = new JFrame("Registro de Usuario");
        ventanaRegistro.setSize(1000, 500);
        ventanaRegistro.setLocationRelativeTo(null);
        ventanaRegistro.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventanaRegistro.add(vistaRegistrarse);
        ventanaRegistro.setVisible(true);
    }

    public void cargarHabitaciones(){
        habitaciones = Habitaciones.obtenerHabitaciones();
        habitacionesTableModel.clear();
        for (Habitaciones h : habitaciones) {
            habitacionesTableModel.addRow(h);
        }
    }

    public void cargarReservas(){
        reservas = Reservas.obtenerReservas();
        reservasTableModel.clear();
        for (Reservas r : reservas) {
            reservasTableModel.addRow(r);
        }
    }

    public void cargarHuespedes(){
        huespedes = Huespedes.obtenerHuespedes();
        huespedesTableModel.clear();
        for (Huespedes hu : huespedes) {
            huespedesTableModel.addRow(hu);
        }
    }

    public void guardarHabitacion() {
        String tipo = vistaCrearModificarHabitacion.getTipoText().trim().toLowerCase();
        String estado = vistaCrearModificarHabitacion.getEstadoText().trim().toLowerCase();
        String precioText = vistaCrearModificarHabitacion.getPrecioText().trim();

        if (tipo.isEmpty() || estado.isEmpty() || precioText.isEmpty()) {
            JOptionPane.showMessageDialog(vistaCrearModificarHabitacion, "Todos los campos deben estar llenos.", "Campos incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!estado.equals("en limpieza") && !estado.equals("ocupada") && !estado.equals("disponible")) {
            JOptionPane.showMessageDialog(vistaCrearModificarHabitacion, "Estado inválido. Debe ser: 'en limpieza', 'ocupada' o 'disponible'.", "Estado inválido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!tipo.equals("simple") && !tipo.equals("doble") && !tipo.equals("suite")) {
            JOptionPane.showMessageDialog(vistaCrearModificarHabitacion, "Tipo inválido. Debe ser: 'simple', 'doble' o 'suite'.", "Tipo inválido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        double precio;
        try {
            precio = Double.parseDouble(precioText);
            if (precio < 0) {
                JOptionPane.showMessageDialog(vistaCrearModificarHabitacion, "El precio debe ser un número positivo.", "Precio inválido", JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vistaCrearModificarHabitacion, "El precio debe ser un número válido.", "Precio inválido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Habitaciones ha = new Habitaciones(vistaCrearModificarHabitacion);
        Habitaciones.agregarHabitacion(ha);

        vistaCrearModificarHabitacion.getTipo().setText("");
        vistaCrearModificarHabitacion.getEstado().setText("");
        vistaCrearModificarHabitacion.getPrecio().setText("");
        JOptionPane.showMessageDialog(vistaCrearModificarHabitacion, "Habitación creada con éxito.", "Creado", JOptionPane.INFORMATION_MESSAGE);
        volverAGestionarHabitaciones();
    }
    public void modificarHabitacion(){
        String tipo = vistaCrearModificarHabitacion.getTipoText().trim().toLowerCase();
        String estado = vistaCrearModificarHabitacion.getEstadoText().trim().toLowerCase();
        String precioText = vistaCrearModificarHabitacion.getPrecioText().trim();

        if (tipo.isEmpty() || estado.isEmpty() || precioText.isEmpty()) {
            JOptionPane.showMessageDialog(vistaCrearModificarHabitacion, "Todos los campos deben estar llenos.", "Campos incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!estado.equals("en limpieza") && !estado.equals("ocupada") && !estado.equals("disponible")) {
            JOptionPane.showMessageDialog(vistaCrearModificarHabitacion, "Estado inválido. Debe ser: 'en limpieza', 'ocupada' o 'disponible'.", "Estado inválido", JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        if (!tipo.equals("simple") && !tipo.equals("doble") && !tipo.equals("suite")) {
            JOptionPane.showMessageDialog(vistaCrearModificarHabitacion, "Tipo inválido. Debe ser: 'simple', 'doble' o 'suite'.", "Tipo inválido", JOptionPane.WARNING_MESSAGE);
            return;
        }
        double precio;
        try {
            precio = Double.parseDouble(precioText);
            if (precio < 0) {
                JOptionPane.showMessageDialog(vistaCrearModificarHabitacion, "El precio debe ser un número positivo.", "Precio inválido", JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vistaCrearModificarHabitacion, "El precio debe ser un número válido.", "Precio inválido", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (habitacionSeleccionada == null) {
            JOptionPane.showMessageDialog(vistaCrearModificarHabitacion, "No hay una habitación seleccionada para modificar.", "Error de selección", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Habitaciones ha = new Habitaciones(vistaCrearModificarHabitacion, habitacionSeleccionada.getNumero());
        Habitaciones.actualizarHabitaciones(ha, habitacionSeleccionada.getNumero());
        vistaCrearModificarHabitacion.getTipo().setText("");
        vistaCrearModificarHabitacion.getEstado().setText("");
        vistaCrearModificarHabitacion.getPrecio().setText("");
        JOptionPane.showMessageDialog(vistaCrearModificarHabitacion, "Habitación actualizada con éxito.", "Modificado", JOptionPane.INFORMATION_MESSAGE);
        volverAGestionarHabitaciones();
    }
    public boolean eliminarHabitacion(){
        int filaSeleccionada = vistaGestionarHabitaciones.getTableView().getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(vistaGestionarHabitaciones, "Debes seleccionar una habitación para eliminar.", "Selección requerida", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        Habitaciones habitacionSeleccionada = habitaciones.get(filaSeleccionada);
        if (habitacionSeleccionada == null) {
            JOptionPane.showMessageDialog(vistaGestionarHabitaciones, "La habitación seleccionada no existe.", "Error interno", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        int confirmacion = JOptionPane.showConfirmDialog(vistaGestionarHabitaciones, "¿Estás seguro de eliminar la habitación número " + habitacionSeleccionada.getNumero() + "?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            Habitaciones.eliminarHabitacion(habitacionSeleccionada.getNumero());
            habitaciones.remove(filaSeleccionada);
            habitacionesTableModel.removeRow(filaSeleccionada);
            JOptionPane.showMessageDialog(vistaGestionarHabitaciones, "Habitación eliminada exitosamente.", "Eliminación exitosa", JOptionPane.INFORMATION_MESSAGE);
            return true;
        }
        return false;
    }

    public void guardarReserva() {
        String huespedStr       = vistaCrearModificarReserva.getIdHuesped().trim();
        String fechaEntradaStr  = vistaCrearModificarReserva.getFechaEntrada().trim();
        String fechaSalidaStr   = vistaCrearModificarReserva.getFechaSalida().trim();

        if (huespedStr.isEmpty() || fechaEntradaStr.isEmpty() || fechaSalidaStr.isEmpty()) {
            JOptionPane.showMessageDialog(vistaCrearModificarReserva, "Todos los campos deben estar llenos.", "Campos incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int huespedId;
        try {
            huespedId = Integer.parseInt(huespedStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vistaCrearModificarReserva, "El ID del huésped debe ser un número entero válido.", "ID inválido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (Huespedes.obtenerHuesped(huespedId) == null) {JOptionPane.showMessageDialog(vistaCrearModificarReserva, "El huésped con ID " + huespedId + " no existe.", "ID de huésped inexistente", JOptionPane.WARNING_MESSAGE);
            return;
        }

        SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd");
        f.setLenient(false);
        Date fechaEntrada, fechaSalida;

        try {
            fechaEntrada = f.parse(fechaEntradaStr);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(vistaCrearModificarReserva, "La fecha de entrada tiene un formato inválido. Usa yyyy/MM/dd.", "Fecha de entrada inválida", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            fechaSalida  = f.parse(fechaSalidaStr);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(vistaCrearModificarReserva, "La fecha de salida tiene un formato inválido. Usa yyyy/MM/dd.", "Fecha de salida inválida", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!fechaEntrada.before(fechaSalida)) {
            JOptionPane.showMessageDialog(vistaCrearModificarReserva, "La fecha de entrada debe ser anterior a la fecha de salida.", "Rango de fechas inválido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Reservas nueva = new Reservas(vistaCrearModificarReserva);
        Reservas.agregarReserva(nueva);
        vistaCrearModificarReserva.getIdHuespedField().setText("");
        vistaCrearModificarReserva.getFechaEntradaChooser().setDate(null);
        vistaCrearModificarReserva.getFechaSalidaChooser().setDate(null);
        JOptionPane.showMessageDialog(vistaCrearModificarReserva, "Reserva creada con éxito.", "Reserva guardada", JOptionPane.INFORMATION_MESSAGE);
        volverAGestionarReservas();
    }
    public void modificarReserva() {
        String huespedStr       = vistaCrearModificarReserva.getIdHuesped().trim();
        String fechaEntradaStr  = vistaCrearModificarReserva.getFechaEntrada().trim();
        String fechaSalidaStr   = vistaCrearModificarReserva.getFechaSalida().trim();

        if (huespedStr.isEmpty() || fechaEntradaStr.isEmpty() || fechaSalidaStr.isEmpty()) {
            JOptionPane.showMessageDialog(vistaCrearModificarReserva, "Todos los campos deben estar llenos.", "Campos incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int huespedId;
        try { huespedId = Integer.parseInt(huespedStr); }
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vistaCrearModificarReserva, "El ID del huésped debe ser un número entero válido.", "ID inválido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (Huespedes.obtenerHuesped(huespedId) == null) {
            JOptionPane.showMessageDialog(vistaCrearModificarReserva, "El huésped con ID " + huespedId + " no existe.", "ID de huésped inexistente", JOptionPane.WARNING_MESSAGE);
            return;
        }

        SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd");
        f.setLenient(false);
        Date fechaEntrada, fechaSalida;

        try {
            fechaEntrada = f.parse(fechaEntradaStr);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(vistaCrearModificarReserva, "La fecha de entrada tiene un formato inválido. Usa yyyy/MM/dd.", "Fecha de entrada inválida", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            fechaSalida  = f.parse(fechaSalidaStr);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(vistaCrearModificarReserva, "La fecha de salida tiene un formato inválido. Usa yyyy/MM/dd.", "Fecha de salida inválida", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!fechaEntrada.before(fechaSalida)) {
            JOptionPane.showMessageDialog(vistaCrearModificarReserva, "La fecha de entrada debe ser anterior a la fecha de salida.", "Rango de fechas inválido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Reservas modificada = new Reservas(vistaCrearModificarReserva);
        Reservas.actualizarReserva(modificada, huespedId);
        vistaCrearModificarReserva.getIdHuespedField().setText("");
        vistaCrearModificarReserva.getFechaEntradaChooser().setDate(null);
        vistaCrearModificarReserva.getFechaSalidaChooser().setDate(null);
        JOptionPane.showMessageDialog(vistaCrearModificarReserva, "Reserva modificada con éxito.", "Reserva actualizada", JOptionPane.INFORMATION_MESSAGE);
        volverAGestionarReservas();
    }
    public boolean eliminarReserva() {
        int fila = vistaGestionarReservas.getTableView().getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vistaGestionarReservas, "Debes seleccionar una reserva para eliminar.", "Selección requerida", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        Reservas sel = reservas.get(fila);
        int ok = JOptionPane.showConfirmDialog(vistaGestionarReservas, "¿Estás seguro de eliminar la reserva #" + sel.getId() + " del huésped: " + sel.getHuespedId() + "?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (ok == JOptionPane.YES_OPTION) {
            Reservas.eliminarReserva(sel.getId());
            reservas.remove(fila);
            reservasTableModel.removeRow(fila);
            return true;
        }
        return false;
    }


    public void guardarHuesped() {
        String nombres = vistaCrearModificarHuesped.getNombres().trim();
        String correo = vistaCrearModificarHuesped.getCorreo().trim();
        String direccion = vistaCrearModificarHuesped.getDireccion().trim();
        String telefono = vistaCrearModificarHuesped.getTelefono().trim();
        String documentoIdentidad = vistaCrearModificarHuesped.getDocumentoIdentidad().trim();

        if (nombres.isEmpty() || correo.isEmpty() || direccion.isEmpty() || telefono.isEmpty() || documentoIdentidad.isEmpty()) {
            JOptionPane.showMessageDialog(vistaCrearModificarHuesped, "Todos los campos deben estar llenos.", "Campos incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Huespedes nuevoHuesped = new Huespedes(vistaCrearModificarHuesped);
        boolean exito = Huespedes.agregarHuesped(nuevoHuesped);

        if (exito) {
            vistaCrearModificarHuesped.getHuespedIdJTextField().setText("");
            vistaCrearModificarHuesped.getNombresJTextField().setText("");
            vistaCrearModificarHuesped.getCorreoJTextField().setText("");
            vistaCrearModificarHuesped.getDireccionJTextField().setText("");
            vistaCrearModificarHuesped.getTelefonoJTextField().setText("");
            vistaCrearModificarHuesped.getDocumentoIdentidadJTextField().setText("");
            JOptionPane.showMessageDialog(vistaCrearModificarHuesped, "Huésped registrado con éxito.", "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);
            volverAGestionarHuespedes();
        } else {
            JOptionPane.showMessageDialog(vistaCrearModificarHuesped, "Ocurrió un error al registrar el huésped.", "Error", JOptionPane.ERROR_MESSAGE);

            JOptionPane.showMessageDialog(vistaCrearModificarHuesped, "Habitación creada con éxito", "Creado", JOptionPane.OK_OPTION);
            volverAGestionarHuespedes();
        }
    }
    public void modificarHuesped() {
        String idHuespedStr = vistaCrearModificarHuesped.getHuespedId();
        String nombres = vistaCrearModificarHuesped.getNombres();
        String correo = vistaCrearModificarHuesped.getCorreo();
        String direccion = vistaCrearModificarHuesped.getDireccion();
        String telefono = vistaCrearModificarHuesped.getTelefono();
        String documentoIdentidad = vistaCrearModificarHuesped.getDocumentoIdentidad();

        if (nombres.isEmpty() || correo.isEmpty() || direccion.isEmpty() || telefono.isEmpty() || documentoIdentidad.isEmpty()) {
            JOptionPane.showMessageDialog(vistaCrearModificarReserva, "Todos los campos deben estar llenos", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int idHuesped;
        try {
            idHuesped = Integer.parseInt(idHuespedStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vistaCrearModificarReserva, "ID inválido", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Huespedes huespedExistente = Huespedes.obtenerHuesped(idHuesped);
        if (huespedExistente == null) {
            JOptionPane.showMessageDialog(vistaCrearModificarReserva, "El huésped con ID " + idHuesped + " no existe", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Huespedes huespedActualizado = new Huespedes(idHuesped, nombres, correo, direccion, telefono, documentoIdentidad);
        boolean actualizado = Huespedes.actualizarHuesped(huespedActualizado, idHuesped);

        if (actualizado) {
            JOptionPane.showMessageDialog(vistaCrearModificarHuesped, "Huésped modificado con éxito", "Modificado", JOptionPane.OK_OPTION);
            vistaCrearModificarHuesped.getHuespedIdJTextField().setText("");
            vistaCrearModificarHuesped.getNombresJTextField().setText("");
            vistaCrearModificarHuesped.getCorreoJTextField().setText("");
            vistaCrearModificarHuesped.getDireccionJTextField().setText("");
            vistaCrearModificarHuesped.getTelefonoJTextField().setText("");
            vistaCrearModificarHuesped.getDocumentoIdentidadJTextField().setText("");
            volverAGestionarHuespedes();
        } else {
            JOptionPane.showMessageDialog(vistaCrearModificarReserva, "Error al modificar el huésped", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public boolean eliminarHuesped() {
        int filaSeleccionada = vistaGestionarHuespedes.getTableView().getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(vistaGestionarHuespedes, "Debes seleccionar un huesped para eliminar.", "Selección requerida", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        Huespedes huespedSeleccionado = huespedes.get(filaSeleccionada);
        int confirmacion = JOptionPane.showConfirmDialog(vistaGestionarHuespedes, "¿Estás seguro de eliminar al huesped #" + huespedSeleccionado.getId() + " de nombre: " + huespedSeleccionado.getNombre() + "?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            Huespedes.eliminarHuesped(huespedSeleccionado.getId());
            huespedes.remove(filaSeleccionada);
            huespedesTableModel.removeRow(filaSeleccionada);
            return true;
        }
        return false;
    }

}

