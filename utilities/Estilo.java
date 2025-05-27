package HotelProyectoFinal.utilities;

import javax.swing.*;
import java.awt.*;

public class Estilo {
    public static void aplicarEstiloComponente(Component comp, Font fuente, Color fondo, Color texto) {
        if (comp instanceof JPanel || comp instanceof JFrame) {
            comp.setBackground(fondo);
        }
        if (comp instanceof JLabel || comp instanceof JButton || comp instanceof JTextField || comp instanceof JPasswordField || comp instanceof JComboBox) {
            comp.setFont(fuente);
            comp.setForeground(texto);
            comp.setBackground(fondo);
        }

        if (comp instanceof Container) {
            for (Component hijo : ((Container) comp).getComponents()) {
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
