import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class PaintGraphic extends JPanel {
    private ArrayList<Point2D.Double> points = new ArrayList<>();

    private final Point.Double windowMax = new Point.Double(10.25, 10.25);
    private final Point.Double windowMin = new Point.Double(-1, -1);
    private Point.Double viewPortMax;
    private final Point.Double viewPortMin = new Point.Double(0, 0);
    private final Point.Double ejeWidth = new Point.Double(windowMax.x - 0.25, windowMax.y - 0.25);

    public PaintGraphic(){
        super();
    }

    public void setList(Integer[] popularity) {
        points.clear();
        for(int i = 1880; i <= 2010; i+=10){
            points.add(new Point2D.Double((double) (i-1880)/14,
                    popularity[(i-1880)/10] != 0 ? ejeWidth.y - (double)popularity[(i-1880)/10]/100 : 0));
        }
    }

    public void paintComponent(Graphics g)
    {
        viewPortMax = new Point.Double(getWidth(), getHeight());
        Graphics2D g2 = (Graphics2D) g;


        RenderingHints hints = g2.getRenderingHints();
        hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        hints.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.addRenderingHints(hints);

        Transforma t = new Transforma(windowMin.x, windowMin.y, windowMax.x, windowMax.y,   // ventana
                viewPortMin.x, viewPortMin.y, viewPortMax.x, viewPortMax.y);                     // viewport

        // Datos
        for (Point2D p : points) {
            Point q = t.proyecta(p.getX(), p.getY());

            g2.fill(new Ellipse2D.Double(q.x, q.y, 5, 5));

            q = t.proyecta(p.getX() + 0.1, p.getY() + 0.10);
            g2.drawString(String.valueOf(Math.round(p.getY() != 0 ? (ejeWidth.getY() - p.getY()) * 100 : 0)), q.x, q.y);
        }


        for(int i = 1880; i <= 2010; i+=10){
           Point.Double p = new Point.Double((double)(i-1880)/14, 0.1);
           Point q = t.proyecta(p.getX(), p.getY());

           g2.fill(new Rectangle2D.Double(q.x, q.y, 2, 10));

           q = t.proyecta(p.getX(), p.getY()-0.4);
           g2.drawString(String.valueOf(i),q.x,q.y);

        }

        for(int i = 0; i <= 10; i++){
            Point.Double p = new Point.Double(-0.1, 10 - i);
            Point q = t.proyecta(p.getX(), p.getY());

            g2.fill(new Rectangle2D.Double(q.x, q.y,10, 2));

            q = t.proyecta(p.getX() - 0.5, p.getY() - 0.1);
            g2.drawString(String.valueOf(i == 0 ? 1 : i * 100), q.x, q.y);
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
        Point p1 = t.proyecta(0, 0);
        Point p2 = t.proyecta(10, 0);
        g2.draw(new Line2D.Double(p1.x, p1.y, p2.x, p2.y));
        p2 = t.proyecta(0, 10);
        g2.draw(new Line2D.Double(p1.x, p1.y, p2.x, p2.y));

        // Etiquetas
        g2.setPaint(Color.black);
        g2.setFont(new Font("Helvetica", Font.BOLD, 22));
        p1 = t.proyecta(9, -0.8);
        g2.drawString("Año", p1.x, p1.y);
        p1 = t.proyecta(-1, -0.8);
        g2.drawString("Rank", p1.x, p1.y);
    }
}
