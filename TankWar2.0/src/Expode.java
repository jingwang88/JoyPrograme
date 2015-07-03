import java.awt.Color;
import java.awt.Graphics;


public class Expode {
	private boolean Live = true;
	int x, y;
	int[] diameter = {5, 10, 15, 20, 25, 35, 40};
	int step = 0;
	TankClient tc;
	
	public Expode(int x, int y, TankClient tc) {
		this.x = x;
		this.y = y;
		this.tc = tc;
	}
	
	public void draw(Graphics g) {
		if(!Live) {
			tc.expodes.remove(this);
			return;
		}
		if(step == diameter.length) {
			Live = false;
			step = 0;
			return;
		}
 		Color color = g.getColor();
		g.setColor(Color.DARK_GRAY);
		g.fillOval(x, y, diameter[step], diameter[step]);
		step++;
		g.setColor(color);
	}
}
