import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DynamicShapes extends JPanel {

    private final List<Object> shapes = new ArrayList<>();
    private final Random random = new Random();

    public DynamicShapes(String shape) {
        String shapeAmount = JOptionPane.showInputDialog(null,
                "How many shapes?", "Random Shapes...", JOptionPane.PLAIN_MESSAGE);
        int i = 26;
        if (!shapeAmount.equals("")) {
            i = Integer.parseInt(shapeAmount);
        }


        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(500, 440));
        int letters = i*2;
        int x = 40;

        switch (shape) {
            case "Circles":
                for (int j = 0; j < i; j++) {
                    addCircle(390, 390);
                }
                break;
            case "Stars":
                for (int j = 0; j < i; j++) {
                    addStar(380, 380);
                }
                break;
            case "Both":
                int mid = i / 2;
                for (int j = 0; j < mid; j++) {
                    addCircle(390, 390);
                }
                for (int j = mid; j < i; j++) {
                    addStar(380, 380);
                }
                break;
            case "Alpha":
                for (int j = 0; j < letters; j++) {
                    addAlpha(j, x, 80);
                }
                break;
            case "Codex":
                String seedIn = JOptionPane.showInputDialog(null,
                        "Seed", "Random Shapes...", JOptionPane.PLAIN_MESSAGE);

                Random random = new Random();
                int seed = random.nextInt(1000);
                if (!seedIn.equals("")) {
                    seed = Integer.parseInt(seedIn);
                }

                for (int j = 0; j < letters; j++) {
                    addAlpha(j, x, 80);
                }

                for (int j = 0; j < letters; j++) {
                    if ((j+1) % 2 == 0) {
                        if ((j <= 11)) {
                            addCodex(j, 40 + ((40 * (j)) - 18), 35, 10, 10, seed);
                        } else if ((j <= 23)) {
                            addCodex(j, 40 + ((40 * (j-12)) - 18), (35*3)+10, 10, 10, seed);
                        } else if ((j <= 36)) {
                            addCodex(j, 40 + ((40 * (j-24)) - 18), (35*5)+20, 10, 10, seed);
                        } else if ((j <= 48)) {
                            addCodex(j, 40 + ((40 * (j-36)) - 18), (35*7)+30, 10, 10, seed);
                        }else{
                            addCodex(j, 40 + ((40 * (j-48) - 18)), (35*9)+40, 10, 10, seed);
                        }
                    } else {
                        if (((j+1) <= 11)) {
                            addCodex(j, 40 + ((40 * j)), 35, 18, 18, seed);
                        } else if ((j <= 23)) {
                            addCodex(j, 40 + ((40 * (j-12))), (35*3)+10, 18, 18, seed);
                        } else if ((j <= 35)) {
                            addCodex(j, 40 + ((40 * (j-24))), (35*5)+20, 18, 18, seed);
                        } else if ((j <= 46)) {
                            addCodex(j, 40 + ((40 * (j-36))), (35*7)+30, 18, 18, seed);
                        }else{
                            addCodex(j, 40 + ((40 * (j-48))), (35*9)+40, 18, 18, seed);
                        }
                    }
                }
                break;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Object s : shapes) {
            if (s instanceof Circle) {
                ((Circle) s).draw(g);
            } else if (s instanceof Star) {
                ((Star) s).draw(g);
            } else if (s instanceof AlphaMaker) {
                ((AlphaMaker) s).draw(g);
            }else if (s instanceof CodexMaker) {
                ((CodexMaker) s).draw(g);
            }
        }
    }
    public void addAlpha(int j, int maxX, int maxY){
        shapes.add(new AlphaMaker(j, maxX,maxY));
        repaint();
    }

    public void addCircle(int maxX, int maxY) {
        shapes.add(new Circle(random.nextInt(maxX), random.nextInt(maxY)));
        repaint();
    }

    public void addStar(int maxX, int maxY) {
        shapes.add(new Star(random.nextInt(maxX), random.nextInt(maxY)));
        repaint();
    }

    public void addCodex(int j, int maxX, int maxY, int width, int height, int special) {
        shapes.add(new CodexMaker(j, maxX, maxY, width, height, special));
        repaint();
    }



    public static void main(String[] args) {

        String[] shapes = {"Codex", "Alpha", "Circles", "Star", "Both"};
        String shape = (String) JOptionPane.showInputDialog(null,
                "Pick the shape you want", "Random Shapes...",
                JOptionPane.PLAIN_MESSAGE, null, shapes, shapes[0]);

        JFrame frame = new JFrame();
        frame.add(new DynamicShapes(shape));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
