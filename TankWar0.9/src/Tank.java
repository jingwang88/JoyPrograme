import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;


public class Tank {
	private static final int xSpeed = 10;
	private static final int ySpeed = 10;
	boolean bl = false, bu = false, br = false, bd = false;
	enum Direction{l, lu, u, ru, r, rd, d, ld, stop};
	Direction direction = Direction.stop;
	private int x, y;
	public Tank(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void draw(Graphics g) {
		Color color = g.getColor();
		g.setColor(Color.RED);
		g.fillOval(x, y, 30, 30);
		g.setColor(color);
		move();
	}
	public void move() {
		switch (direction) {
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
		case stop:
			break;
		default:
			break;
		}
		//bl = false; bu = false; br = false; bd = false;
		//direction = Direction.stop;
	}
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_RIGHT: 
			br = true;
			break;
		case KeyEvent.VK_LEFT: 
			bl = true;
			break;
		case KeyEvent.VK_DOWN: 
			bd = true;
			break;
		case KeyEvent.VK_UP: 
			bu = true;
			break;
		default:
			break;
		}
		tankDirection();
	}
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_RIGHT: 
			br = false;
			break;
		case KeyEvent.VK_LEFT: 
			bl = false;
			break;
		case KeyEvent.VK_DOWN: 
			bd = false;
			break;
		case KeyEvent.VK_UP: 
			bu = false;
			break;
		default:
			break;
		}
		tankDirection();
		
	}
	public void tankDirection() {
		if(bl && !bu && !br && !bd) direction = Direction.l;
		else if(bl && bu && !br && !bd) direction = Direction.lu;
		else if(!bl && bu && !br && !bd) direction = Direction.u;
		else if(!bl && bu && br && !bd) direction = Direction.ru;
		else if(!bl && !bu && br && !bd) direction = Direction.r;
		else if(!bl && !bu && br && bd) direction = Direction.rd;
		else if(!bl && !bu && !br && bd) direction = Direction.d;
		else if(bl && !bu && !br && bd) direction = Direction.ld;
		else if(!bl && !bu && !br && !bd) direction = Direction.stop;
	}

}
