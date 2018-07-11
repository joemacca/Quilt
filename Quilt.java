import java.awt.Color;
//import java.awt.Graphics;
//import java.awt.Graphics2D;
import java.util.*;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * Quilt class. Creates a boundless number number of squares based on input
 * @author Joseph McManamon (6021556)
 * Resources used:
 * http://www.java2s.com/Code/Java/2D-Graphics-GUI/DrawrectanglesusethedrawRectmethodTofillrectanglesusethefillRectmethod.htm
 */

public class Quilt extends JPanel {

	private static final int SIZE = 800;
	private static int frameX = (int)(SIZE*0.95);
	private static int frameY = (int)(SIZE*0.95);
	private static int midX = frameX/2;
	private static int midY = frameY/2;
	private static ArrayList<Square> squares;
	private static ArrayList<Level> levels;

    private static double initalSize;


	public static void main(String[] args) {
		int r, g, b;
		double scale; 
		Level level;
		Scanner sc = new Scanner(System.in);
		squares = new ArrayList<Square>();
		levels  = new ArrayList<Level>();
		while (sc.hasNext()){
			scale = sc.nextDouble();
			r = sc.nextInt();
			g = sc.nextInt();
			b = sc.nextInt();
			level = new Level(scale, r, g, b);
			levels.add(level);
		}

		//so we can fit all squares in our window
		adjustScales();		
		double combined = 0;

        for(Level l : levels){
            combined += l.scale;
        }
        initalSize = frameY*0.9/combined;
		generateSquares();

		Quilt quilt = new Quilt();
		JFrame frame = new JFrame("Quilt");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(quilt);
		frame.setSize(frameX+30, frameY+30);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		for(Square s : squares){
			System.out.println("x: " + s.x + " y: " + s.y + " height: " + s.height + " width: " + s.width + " depth: " + s.depth);
		}
	}

	/**
	 * Finds the level with the largest scale then adjusts all the scales
	 * to be relative.
	 */
    private static void adjustScales(){

        double maxScale = 0;
        for (int i = 0; i < levels.size(); i++){
            if (levels.get(i).getScale() > maxScale){
                maxScale = levels.get(i).getScale();
            }
        }
        for (int i = 0; i < levels.size(); i++){
            double scale = levels.get(i).getScale();
            levels.get(i).setScale(scale/maxScale);
        }
    }



	private static int getLevelScale(Level level){
		return (int)(initalSize * level.scale);
	}	


	/** 
	 * Starts the recursion. 
	 */
	private static void generateSquares(){
		generateSquares(0, levels.get(0), midX, midY);
	}

	private static void generateSquares(int depth, Level level, int midX, int midY){


        int levelDimensions = getLevelScale(level);
        //top left squares
        int x = midX - (levelDimensions/2);
        int y = midY - (levelDimensions/2);

        //bottom left squares
        int x1 = x;
        int y1 = y + levelDimensions;

        //top right squares
        int x2 = x + levelDimensions;
        int y2 = y;

        //bottom right squares
        int x3 = x + levelDimensions;
        int y3 = y2 + levelDimensions;

        /* Create instance of square, add to array, then recurse. */
		Square s = new Square(level.getColor(), depth, x, y, levelDimensions, levelDimensions);
		squares.add(s);
        if (depth == levels.size()-1){
            return;
        }else{
            generateSquares(depth+1,levels.get(depth+1), x, y);
            generateSquares(depth+1,levels.get(depth+1), x1, y1);            
            generateSquares(depth+1,levels.get(depth+1), x2, y2);
            generateSquares(depth+1,levels.get(depth+1), x3, y3);   
        }
	}	
	public void paintComponent(Graphics g) {
		int count = -1;
		for(Level level : levels){
			count++;
			for(Square s : squares){
				if (s.depth == count){
    	        	s.display(g);
				}
			}
		}
	}
}