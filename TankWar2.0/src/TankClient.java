import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;



public class TankClient extends Frame{
	
	public static final int GAME_WIDTH = 800;
	public static final int GAME_HEIGHT = 600;
	Tank myTank = new Tank(50, 50, true, Tank.Direction.stop, this);
	List<Tank> tanks = new ArrayList<Tank>();
	 List<Expode> expodes = new ArrayList<Expode>();
	List<Bullet> bullets = new ArrayList<Bullet>();
	Image offScreenImage = null;
	Wall wall1 = new Wall(300, 150, 25, 200, this);
	Wall wall2 = new Wall(320, 120, 200, 25, this);

	public void paint(Graphics g) {
		g.drawString("bullet numbers"+bullets.size(), 60, 40);
		g.drawString("expode numbers"+expodes.size(), 60, 60);
		g.drawString("Tank numbers"+tanks.size(), 60, 80);
		
		for(int i=0; i<bullets.size(); i++) {
			Bullet bullet = bullets.get(i);
			bullet.hitTanks(tanks);
			bullet.hitWall(wall1);
			bullet.hitWall(wall2);
			bullet.hitTank(myTank);
			bullet.draw(g);
		}
		for(int i=0; i<expodes.size(); i++) {
			expodes.get(i).draw(g);
		}
		for(int i=0; i<tanks.size(); i++) {
			Tank tank = tanks.get(i);
			tank.collidesWithWall(wall1);
			tank.collidesWithWall(wall2);
			tank.draw(g);
		}
		myTank.draw(g);
		wall1.draw(g);
		wall2.draw(g);
		
	}

	public void update(Graphics g) {
		if (offScreenImage == null) {
			offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
		}
		Graphics gOffScreen = offScreenImage.getGraphics();
		Color color = gOffScreen.getColor();
		gOffScreen.setColor(Color.GREEN);
		gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		gOffScreen.setColor(color);
		paint(gOffScreen);
		g.drawImage(offScreenImage, 0, 0, null);
	}
	public void frameLaunch() {
		for(int i=0; i<10; i++) {
			tanks.add(new Tank(50+40*(i+1), 50, false, Tank.Direction.d, this));
		}
		
		this.setBounds(300, 200, GAME_WIDTH, GAME_HEIGHT);
		this.setTitle("TankWar");
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}	
		});
		this.addKeyListener(new KeyMonitor());
		this.setResizable(false);
		this.setBackground(Color.GREEN);
		this.setVisible(true);
		new Thread(new PaintThread()).start();
    }
	
	public static void main(String[] args) {
		TankClient tankClient = new TankClient();
		tankClient.frameLaunch();
	}
	
	 private class PaintThread implements Runnable {
	    public void run() {
	    	while (true) {
	    		repaint();
	    		try {
					Thread.sleep(150);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
	    		
	    	}
	    }

	}
	private class KeyMonitor extends  KeyAdapter {
		public void keyPressed(KeyEvent e) {
				myTank.keyPressed(e);
			}
		public void keyReleased(KeyEvent e) {
				myTank.keyReleased(e);
		}
		
	}
	
}
