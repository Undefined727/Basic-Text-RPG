import javax.swing.JFrame;

public class GameWindow extends JFrame {
	
		JFrame frame = new JFrame("Basic RPG");
		Background bg = new Background();
		
		public GameWindow() {
			frame.add(bg);
			frame.pack();
			
			frame.setLocationRelativeTo(null);  
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
	        frame.setVisible(true);
		}
}
