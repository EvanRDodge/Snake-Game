package Snake;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class SnakeApp {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				SnakeGameController panel = new SnakeGameController();
				JFrame mainFrame = new JFrame("Snake Game");
				//frame and panel settings
				mainFrame.setSize(600, 600);
				mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				mainFrame.setVisible(true);
				mainFrame.setResizable(false);
				panel.setFocusable(true);
				panel.requestFocusInWindow();
				//add panel to frame
				mainFrame.add(panel);
			}
		});
	}
}
