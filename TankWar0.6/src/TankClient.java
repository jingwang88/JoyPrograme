import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class TankClient extends Frame{
	
	private static final int GAME_WIDTH = 800;
	private static final int GAME_HEIGHT = 600;
	private int x = 50;
	private int y = 50;
	Image offScreenImage = null;
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		Color color = g.getColor();
		g.setColor(Color.RED);validate();
		g.fillOval(x, y, 30, 30);
		g.setColor(color);
	}
	@Override
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
			int key = e.getKeyCode();
			/*
			if (key == e.VK_RIGHT) {
				x += 5;
			}
			if (key == e.VK_LEFT) {
				x -= 5;
			}
			if (key == e.VK_UP) {
				y -= 5;
			}
			if (key == e.VK_DOWN) {
				y += 5;
			}*/
			switch (key) {
			case KeyEvent.VK_RIGHT: 
				x += 5;
				break;
			case KeyEvent.VK_LEFT: 
				x -= 5;
				break;
			case KeyEvent.VK_DOWN: 
				y += 5;
				break;
			case KeyEvent.VK_UP: 
				y -= 5;
				break;
			default:
				break;
			}
			
		}
	}
	
}
