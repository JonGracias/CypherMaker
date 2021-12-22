import java.awt.*;
import java.awt.geom.*;
import java.util.Random;

interface MoveAction {
    Shape move(int x, int y, int width, int height);
}

public class CodexMaker{
    int j, x, y, seed, width, height, check;
    Shape cipher;
    private static final Stroke STROKE = new BasicStroke(5f);

    private final MoveAction[] moveActions = new MoveAction[] {
       /*0*/this::rectangle,
       /*1*/this::ellipse,
       /*2*/this::triangle,
       /*3*/this::arc,
    };



    public CodexMaker(int j, int x, int y, int width, int height, int seed) {
        this.j = j;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.seed = seed;
        this.check=97;
        setCodex(seed);
    }

    public void setCodex(int seed) {
        int s = seed;
        Random random = new Random();
        int k = random.nextInt(3);
        int l = random.nextInt(3);
        int m = random.nextInt(3);

        Shape shape1 = moveActions[1].move(this.x, this.y, this.width, this.height);
        Shape shape2 = moveActions[0].move(this.x, this.y, this.width, this.height);
        Shape shape3 = moveActions[1].move(this.x, this.y, this.width, this.height);

        Area area = new Area(shape1);
        /*Area area3 = new Area(shape3);

        if(seed < 300 ){
            area.add(area2);
            area.subtract(area3);
        }else if(seed < 400 ){
            area.subtract(area2);
        }
*/
        /*area.add(new Area(shape2));
        area.add(new Area(shape3));*/

        cipher = area;
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);
        Font font = new Font("Serif", Font.PLAIN, 24);
        g2d.setFont(font);
        g2d.setStroke(STROKE);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.fill(cipher);

    }

    public Area rectangle(int x,int y,int w,int h) {
        Random random = new Random();
        int xX = random.nextInt(w)+x;
        int width = w/4;
        Rectangle2D shape1 = new Rectangle2D.Double(xX, y, width, h);
        return new Area(shape1);
    }

    public Area ellipse(int x,int y,int w,int h) {
        /*Random random = new Random();
        int xX = random.nextInt(w)+x;
        int rad = random.nextInt(2)+2;
        int width = w / rad;
        int height = h / rad;*/
        Ellipse2D shape1 = new Ellipse2D.Double(x, y, w, h);
        Ellipse2D shape2 = new Ellipse2D.Double(x + (shape1.getCenterX()/2), y + (shape1.getCenterY()/2), w, h);
        Area area = new Area(shape1);
        Area area2 = new Area(shape2);
        area.add(area2);

        return area;
    }

    public Area triangle(int x, int y, int w, int h) {
        Random random = new Random();
        int xX = random.nextInt(w/5)+x;
        int yY = random.nextInt(h/5)+y;
        int width = random.nextInt(w);
        int height = random.nextInt(h);

        int[] xPoints = {xX, xX+w, xX+(width/2)};
        int[] yPoints = {yY+height, yY+height, yY};

        GeneralPath star = new GeneralPath();

        star.moveTo(xPoints[0], yPoints[0]);
        for (int i = 1; i < xPoints.length; i++) {
            star.lineTo(xPoints[i], yPoints[i]);

        }
        star.closePath();
        return new Area(star);
    }

    public Area arc(int x,int y,int w,int h) {
        Arc2D.Float arc = new Arc2D.Float(Arc2D.PIE);
        Arc2D.Float arc2 = new Arc2D.Float(Arc2D.PIE);

        double[] angle = randomAngle();

        /*arc.setAngleStart(0);
        arc.setAngleExtent(360);
        arc2.setAngleStart(0);
        arc2.setAngleExtent(360);*/

        arc.setAngleStart(angle[0]);
        arc.setAngleExtent(angle[1]);
        arc2.setAngleStart(angle[0]);
        arc2.setAngleExtent(angle[1]);

        arc.setFrame(x, y, w, h);
        arc2.setFrame(x+2.5, y+2.5, w-5, h-5);



        Area area = new Area(arc);
        area.subtract(new Area(arc2));
        return area;
    }

    public double[] randomAngle(){
        Random random = new Random();
        double extent = random.nextInt(315);
        double start;
        if(extent > 90 && extent <= 180){
            start = 180 - extent;
            return new double[]{start, extent};
        }else if(extent > 180 && extent <= 270){
            start = extent - 180;
            return new double[]{start, extent};
        }else if(extent > 270){
            start = 360-extent;
            return new double[]{start, extent};
        }
        return randomAngle();
    }
}


