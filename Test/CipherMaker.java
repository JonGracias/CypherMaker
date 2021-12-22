import javax.swing.*;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;


public class CipherMaker extends JPanel{
    public Shape r;

    public CipherMaker() {
        Rectangle2D shape1 = new Rectangle2D.Double(40, 40, 12, 12);
        this.r = new Area(shape1);

    }
}

/*    public static Shape arcShape(){
        Arc2D arc = new Arc2D.Float(Arc2D.PIE);
        Area area = new Area(arc);
        return area;

    }

    public Shape getShape(int x, int y, int width, int height){
        Ellipse2D shape1 = new Ellipse2D.Double(x, y, width, height);
        Rectangle2D shape2 = new Rectangle2D.Double(x, y, width/2.0, height);

        Area area = new Area(shape1);
        area.add(new Area(shape2));
        return area;

    }*/



