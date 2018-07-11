import java.util.*;
import java.awt.Color;


public class Level{

	Color color;
	double scale;

	public Level(double scale, int r, int g, int b){
		this.scale = scale;
		this.color = new Color(r, g, b);
	}

	public Color getColor(){
		return this.color;
	}

	public double getScale(){
		return this.scale;
	}
	public void setScale(double scale){
		this.scale = scale;
	}


}
