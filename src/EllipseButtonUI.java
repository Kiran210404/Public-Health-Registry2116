import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class EllipseButtonUI extends BasicButtonUI {

    @Override
    public void paint(Graphics g, JComponent c) {
        AbstractButton b = (AbstractButton) c;
        ButtonModel model = b.getModel();

        // Create graphics2D object
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Set button size
        int diameter = Math.min(b.getWidth(), b.getHeight());
        int x = (b.getWidth() - diameter) / 2;
        int y = (b.getHeight() - diameter) / 2;

        // Paint button background
        if (model.isArmed()) {
            g2.setColor(b.getBackground().darker());
        } else {
            g2.setColor(b.getBackground());
        }
        g2.fill(new Ellipse2D.Double(x, y, diameter, diameter));

        // Paint button border
        g2.setColor(b.getForeground());
        g2.setStroke(new BasicStroke(2));
        g2.draw(new Ellipse2D.Double(x, y, diameter, diameter));

        // Paint button text
        if (b.getIcon() == null) {
            FontMetrics metrics = g2.getFontMetrics(g2.getFont());
            String text = b.getText();
            int textX = x + (diameter - metrics.stringWidth(text)) / 2;
            int textY = y + ((diameter - metrics.getHeight()) / 2) + metrics.getAscent();
            g2.setColor(b.getForeground());
            g2.drawString(text, textX, textY);
        }
    }
}
