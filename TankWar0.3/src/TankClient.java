import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class TankClient extends Frame{
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		Color color = g.getColor();
		g.setColor(Color.RED);validate();
		g.fillOval(50, 50, 30, 30);
		g.setColor(color);
	}

	public void frameLaunch () {
		this.setBounds(400, 300, 800, 600);
		this.setTitle("TankWar");
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		this.setResizable(false);
		this.setBackground(Color.GREEN);
		this.setVisible(true);
    }
	
	public static void main(String[] args) {
		TankClient tankClient = new TankClient();
		tankClient.frameLaunch();
	}
}
