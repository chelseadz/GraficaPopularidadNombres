import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class PaintGraphic extends JPanel {
    private ArrayList<Point2D.Double> points = new ArrayList<>();

    public PaintGraphic(){
        super();
    }

    public void setList(Integer[] popularity) {
        points.clear();
        for(int i = 1880; i <= 2010; i+=10){
            points.add(new Point2D.Double((double) (i-1880)/10, popularity[(i-1880)/10]/100));
        }
    }

    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;


        RenderingHints hints = g2.getRenderingHints();
        hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        hints.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.addRenderingHints(hints);

        //Transforma t = new Transforma(minX * 0.6, minY * 0.6, maxX * 1.2, maxY * 1.2,   // ventana
        Transforma t = new Transforma(-1, -1, 6.25, 6.25,   // ventana
                0, 0, getWidth(), getHeight());                     // viewport

        // Datos
        for (Point2D p : points) {
            Point q = t.proyecta(p.getX(), p.getY());

            g2.fill(new Ellipse2D.Double(q.x, q.y, 5, 5));
        }

        for (int i = 0; i < points.size() - 1; i++) {
            Point2D.Double p1 = points.get(i);
            Point2D.Double p2 = points.get(i + 1);
            Point q1 = t.proyecta(p1.x, p1.y);
            Point q2 = t.proyecta(p2.x, p2.y);
            g2.drawLine(q1.x, q1.y, q2.x, q2.y);
        }

        // Ejes
        g2.setPaint(Color.black);
        g2.setStroke(new BasicStroke(3));
        Point p1 = t.proyecta(-0.3, 0);
        Point p2 = t.proyecta(6, 0);
        g2.draw(new Line2D.Double(p1.x, p1.y, p2.x, p2.y));
        p2 = t.proyecta(-0.3, 6);
        g2.draw(new Line2D.Double(p1.x, p1.y, p2.x, p2.y));

        // Etiquetas
        g2.setPaint(Color.black);
        g2.setFont(new Font("Helvetica", Font.BOLD, 22));
        p1 = t.proyecta(5.7, -0.5);
        g2.drawString("Año", p1.x, p1.y);
        p1 = t.proyecta(-0.9, 5.7);
        g2.drawString("Rank", p1.x, p1.y);
    }
}
