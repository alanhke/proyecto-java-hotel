package HotelProyectoFinal.utilities;

import javax.swing.*;
import java.awt.*;

public class Estilo {

    public static void aplicarEstiloComponente(Component comp, Font fuente, Color fondo, Color texto) {
        if (comp instanceof JPanel || comp instanceof JFrame) {
            comp.setBackground(fondo);
        }
        comp.setFont(fuente);
        if (comp instanceof JLabel || comp instanceof JTextField ||
                comp instanceof JPasswordField || comp instanceof JComboBox) {
            comp.setForeground(texto);
            comp.setBackground(fondo);
        }
        if (comp instanceof Container container) {
            for (Component hijo : container.getComponents()) {
                aplicarEstiloComponente(hijo, fuente, fondo, texto);
            }
        }
    }
    public static void aplicarEstiloGlobal(Component vista, String fuente, int tamano, String tema) {
        Font nuevaFuente = new Font(fuente, Font.PLAIN, tamano);
        Color fondo = tema.equals("Oscuro") ? new Color(40, 40, 40) : new Color(250, 250, 255);
        Color texto = tema.equals("Oscuro") ? Color.WHITE : Color.BLACK;

        aplicarEstiloComponente(vista, nuevaFuente, fondo, texto);
    }
}
