import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class TankClient extends Frame{
	
	private static final int GAME_WIDTH = 800;
	private static final int GAME_HEIGHT = 600;
	Tank myTank = new Tank(50, 50);
	Image offScreenImage = null;

	public void paint(Graphics g) {
		myTank.draw(g);
	}

	public void update(Graphics g) {
		if (offScreenImage == null) {
			offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
		}
		Graphics gOffScreen = offScreenImage.getGraphics();
		Color color = gOffScreen.getColor();
		gOffScreen.setColor(Color.GREEN);
		gOffScreen.fillRect(0, 0, 800, 600);
		gOffScreen.setColor(color);
		paint(gOffScreen);
		g.drawImage(offScreenImage, 0, 0, null);
	}
	public void frameLaunch () {
		this.setBounds(400, 300, GAME_WIDTH, GAME_HEIGHT);
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
					Thread.sleep(1000);
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
