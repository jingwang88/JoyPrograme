import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.Random;


public class Tank {
	public static final int xSpeed = 10;
	public static final int ySpeed = 10;
	public static final int Tank_Width = 30;
	public static final int Tank_Height = 30;
	boolean bl = false, bu = false, br = false, bd = false;
	enum Direction{l, lu, u, ru, r, rd, d, ld, stop};
	Direction direction = Direction.stop;
	private int x, y;
	private boolean Live = true;
	TankClient tc;
	Direction paoTongDir = Direction.d;
	private Boolean good;

	private static Random r = new Random();
	private int step = r.nextInt(12)+3;
	public Tank(int x, int y, Boolean good) {
		this.x = x;
		this.y = y;
		this.good = good;
	}
	
	public Tank(int x, int y, Boolean good, Direction direction, TankClient tc) {
		this(x, y, good);
		this.direction = direction;
		this.tc = tc;
	}
	
	public void draw(Graphics g) {
		if(!Live) {
			if(good) {
				
			}else {
				tc.tanks.remove(this);
			}
		    return;
		}
		Color color = g.getColor();
		if(good == true) {
			g.setColor(Color.RED);
		}else {
			g.setColor(Color.BLUE);			
		}
		g.fillOval(x, y, Tank_Width, Tank_Height);
		g.setColor(color);
		switch (paoTongDir) {
		case l:
			g.drawLine(x+Tank_Width/2, y+Tank_Height/2, x, y+Tank_Height/2);
			break;
		case lu:
			g.drawLine(x+Tank_Width/2, y+Tank_Height/2, x, y);
			break;
		case u:
			g.drawLine(x+Tank_Width/2, y+Tank_Height/2, x+Tank_Width/2, y);
			break;
		case ru:
			g.drawLine(x+Tank_Width/2, y+Tank_Height/2, x+Tank_Width, y);
			break;
		case r:
			g.drawLine(x+Tank_Width/2, y+Tank_Height/2, x+Tank_Width, y+Tank_Height/2);
			break;
		case rd:
			g.drawLine(x+Tank_Width/2, y+Tank_Height/2, x+Tank_Width, y+Tank_Height);
			break;
		case d:
			g.drawLine(x+Tank_Width/2, y+Tank_Height/2, x+Tank_Width/2, y+Tank_Height);
			break;
		case ld:
			g.drawLine(x+Tank_Width/2, y+Tank_Height/2, x, y+Tank_Height);
			break;
		default:
			break;
		}

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
		if(x<0) x = 0;
		if(x>TankClient.GAME_WIDTH-Tank_Width) x = TankClient.GAME_WIDTH-Tank_Width;
		if(y<0) y = 0;
		if(y>TankClient.GAME_HEIGHT-Tank_Height) y = TankClient.GAME_HEIGHT-Tank_Height;
		if (this.direction != Direction.stop) {
			this.paoTongDir = this.direction;
		}
		if(!good) {
			Direction[] directions = Direction.values();
			if(step == 0) {
				int randomNum = r.nextInt(directions.length);
				direction = directions[randomNum];
				step = r.nextInt(12)+3;
			}
			if(r.nextInt(40)>35) {
				this.fire();
			}
			step--;
		}
		
		//bl = false; bu = false; br = false; bd = false;
		//direction = Direction.stop;
	}
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_CONTROL:
			fire();
			break;
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
		case KeyEvent.VK_CONTROL:
			break;
		default:
			break;
		}
		tankDirection();
		
	}
	
	public Bullet fire() {
		if(!Live) return null;
		int x = this.x + Tank_Width/2 - Bullet.Bullet_Width/2;
		int y = this.y + Tank_Height/2 - Bullet.Bullet_Height/2;//在前几个版本中我犯了一个错误：把this.y 写成了this.x
		Bullet bullet = new Bullet(x, y, good, paoTongDir, tc);
		tc.bullets.add(bullet);
		return bullet;
	}
	
	public Rectangle getRectangle() {
		return new Rectangle(x, y, Tank_Width, Tank_Height);
	}
	
	public boolean isLive() {
		return Live;
	}

	public void setLive(boolean live) {
		Live = live;
	}
	
	public Boolean getGood() {
		return good;
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
