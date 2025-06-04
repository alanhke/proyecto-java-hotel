package HotelProyectoFinal.utilities;

import javax.swing.*;
import java.awt.*;

public class RoundedPanel extends JPanel {
    private int cornerRadius;

    public RoundedPanel(int radius) {
        super();
        this.cornerRadius = radius;
        setOpaque(false); // Importante para ver la forma redondeada
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        // Antialiasing para bordes suaves
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Color de fondo del panel
        g2.setColor(getBackground());

        // Dibuja el panel con esquinas redondeadas
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);

        g2.dispose();

        // Llama a super.paintComponent solo si querés pintar componentes hijos correctamente
        super.paintComponent(g);

    }
}
