import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class TankClient extends Frame{
	
	private int x = 50;
	private int y = 50;
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		Color color = g.getColor();
		g.setColor(Color.RED);validate();
		g.fillOval(x, y, 30, 30);
		g.setColor(color);
		y += 5;
	}

	public void frameLaunch () {
		this.setBounds(400, 300, 800, 600);
		this.setTitle("TankWar");
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		this.addKeyListener(new KeyAdapter() {
			
		});
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
}
