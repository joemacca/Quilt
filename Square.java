import java.awt.*;
import javax.swing.*;

public class Square{

    private Color color;
    public int x;
    public int y;
    public int depth;
    public int width;
    public int height;    
    
    public Square(Color color, int depth, int x, int y, int width, int height){
        this.color = color;
        //this.mid = (int) mid;
        this.x = x;
        this.y = y;
        this.depth = depth;
        this.width = width;
        this.height = height;

    }
    public void display(Graphics g){
        g.setColor(color);
        g.fillRect(x, y, height, width);
    }
    
}
