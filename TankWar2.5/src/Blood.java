import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


public class Blood {
	int x, y, w, h;
	int position[][] = {
		{420, 200}, {420, 300}, {420, 400}, {360, 400}, {360, 300}, {360, 200}	
	};
	int step = 0;
	private boolean Live = true; 


	public Blood() {
		x = position[0][0];
		y = position[0][1];
		w = h = 10;
	}
	
	
	public void draw(Graphics g) {
		if(!Live) return;
		Color color = g.getColor();
		g.setColor(Color.PINK);
		g.fillRect(x, y, w, h);
		g.setColor(color);
		move();
	}
	
	public void move() {
		step++;
		if(step == position.length) {
			step = 0;
		}
		x = position[step][0];
		y = position[step][1];
	}
	
	public Rectangle getRectangle() {
		return new Rectangle(x, y, w, h);
	}
	
	public boolean isLive() {
		return Live;
	}


	public void setLive(boolean live) {
		Live = live;
	}

	
	
}
