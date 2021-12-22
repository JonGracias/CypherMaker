import java.awt.*;
import java.awt.geom.GeneralPath;

public class Star {

    int x, y, width, height;

    public Star(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        int[] xPoints = {9, 15, 0, 18, 3};
        int[] yPoints = {0, 18, 6, 6, 18};

        Graphics2D g2d = (Graphics2D) g;
        GeneralPath star = new GeneralPath();

        star.moveTo(xPoints[0] + x, yPoints[0] + y);
        for (int i = 1; i < xPoints.length; i++) {
            star.lineTo(xPoints[i] + x, yPoints[i] + y);

        }
        star.closePath();

        g2d.setColor(Color.YELLOW);
        g2d.fill(star);
    }
}
