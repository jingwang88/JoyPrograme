import java.awt.Color;
import java.awt.Graphics;


public class Bullet {
	private int x, y;	
	public static final int xSpeed = 30;
	public static final int ySpeed = 30;
	public static final int Bullet_Width = 10;
	public static final int Bullet_Height = 10;
	Tank.Direction dir;
	public Bullet(int x, int y, Tank.Direction dir) {
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
	public void draw(Graphics g) {
		Color color = g.getColor();
		g.setColor(Color.BLACK);
		g.fillOval(x, y, Bullet_Width, Bullet_Height);
		g.setColor(color);
		
		move();
	}
	private void move() {
		switch (dir) {
		case l:
			x -= xSpeed;
			break;
		case lu:
			x -= xSpeed;
			y -= ySpeed;
			break;
		case u:
			y -= ySpeed;
			break;
		case ru:
			x += xSpeed;
			y -= ySpeed;
			break;
		case r:
			x += xSpeed;
			break;
		case rd:
			x += xSpeed;
			y += ySpeed;
			break;
		case d:
			y += ySpeed;
			break;
		case ld:
			x -= xSpeed;
			y += ySpeed;
			break;
		default:
			break;
		}
	}
	
}
