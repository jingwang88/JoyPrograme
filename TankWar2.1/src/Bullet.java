import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;

import org.omg.CORBA.TCKind;


public class Bullet {
	private int x, y;	
	public static final int xSpeed = 30;
	public static final int ySpeed = 30;
	public static final int Bullet_Width = 10;
	public static final int Bullet_Height = 10;
	private boolean Live = true;
	Tank.Direction dir;
	TankClient tc;
	private boolean good;
	
	public Bullet(int x, int y, Tank.Direction dir) {
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
	public Bullet(int x, int y, boolean good, Tank.Direction dir, TankClient tc) {
		this.x = x;
		this.y = y;
		this.good = good;
		this.dir = dir;
		this.tc = tc;
	}
	public void draw(Graphics g) {
		if(!Live) {
			tc.bullets.remove(this);
			return;
		}
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
			y -= ySpeed;
			break;
		}
		if(x < 0 || y < 0 || x > TankClient.GAME_WIDTH || y > TankClient.GAME_HEIGHT) {
			Live = false;
		}
	}
	public boolean isLive() {
		return Live;
	}
	
	public void setLive(boolean live) {
		Live = live;
	}
	
	public Rectangle getRectangle() {
		return new Rectangle(x, y, Bullet_Width, Bullet_Height);
	}
	
	public Boolean hitTank(Tank t) {
		if(this.isLive() && this.getRectangle().intersects(t.getRectangle()) && t.isLive() && this.good != t.getGood()) {
			t.setLive(false);
			setLive(false);
			tc.expodes.add(new Expode(x, y, tc));
			return true;
		}
		return false;
	}
	
	public boolean hitTanks(List<Tank> tanks) {
		for(int i=0; i<tanks.size(); i++) {
			if(hitTank(tanks.get(i))){
				return true;
			}
		}
		return false;
	}
	
	public boolean hitWall(Wall wall) {
		if(this.getRectangle().intersects(wall.getRectangle())) {
			setLive(false);
			return true;
		}
		return false;
	}
	

	
}
