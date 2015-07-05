import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Wall {
    int x, y, Wall_WIDTH, Wall_HEIGHT; 
	TankClient tc;
	public Wall(int x, int y, int Wall_WIDTH, int Wall_HEIGHT, TankClient tc) {
		this.x = x;
		this.y = y;
		this.Wall_WIDTH = Wall_WIDTH;
		this.Wall_HEIGHT = Wall_HEIGHT; 
		this.tc = tc;
	}
	public void draw(Graphics g) {
		Color color = g.getColor();
		g.setColor(Color.ORANGE);
		g.fillRect(x, y, Wall_WIDTH, Wall_HEIGHT);
		g.setColor(color);
	}
	
	public Rectangle getRectangle() {
		return new Rectangle(x, y, Wall_WIDTH, Wall_HEIGHT);
	}

}
