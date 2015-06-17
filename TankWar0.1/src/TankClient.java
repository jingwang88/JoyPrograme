import java.awt.*;


public class TankClient extends Frame{
	
	public void frameLaunch () {
		this.setBounds(400, 300, 800, 600);
		this.setVisible(true);
    }
	public static void main(String[] args) {
		TankClient tankClient = new TankClient();
		tankClient.frameLaunch();
	}

}
