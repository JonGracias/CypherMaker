import java.awt.*;

public class AlphaMaker {

    int x, y, j;
    char[] alpha = new char[52];

    public AlphaMaker(int j, int x, int y) {
        this.x = x;
        this.y = y;
        this.j = j;
        setAlpha();
    }

    public void setAlpha(){
        int upper = 65;
        int lower = 97;
        int Case = 1;
        for (int i=0; i < 52;i++) {
            if (Case % 2 == 0) {
                alpha[i] = (char) lower;
                lower++;
            }else{
                alpha[i] = (char) upper;
                upper++;
            }
            Case++;
        }
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        Font font = new Font("Serif", Font.PLAIN, 25);
        g2d.setFont(font);

        for (int i=0; i < j;i+=2) {

            if((i <= 10)) {
                g2d.drawChars(new char[]{alpha[i], alpha[i + 1]}, 0, 2, this.x + (this.x * i), this.y );
            }else if((i <= 22)){
                g2d.drawChars(new char[]{alpha[i], alpha[i + 1]}, 0, 2, this.x + (this.x * (i-12)), this.y * 2);
            }else if((i <= 34)){
                g2d.drawChars(new char[]{alpha[i], alpha[i + 1]}, 0, 2, this.x + (this.x * (i-24)), this.y * 3);
            }else if((i <= 46)){
                g2d.drawChars(new char[]{alpha[i], alpha[i + 1]}, 0, 2, this.x + (this.x * (i-36)), this.y * 4);
            }else{
                g2d.drawChars(new char[]{alpha[i], alpha[i + 1]}, 0, 2, this.x + (this.x * (i-48)), this.y * 5);

            }

        }

    }

}