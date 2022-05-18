import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Background extends JPanel implements KeyListener{
	
	Map map = new Map();

	public Background() {
		this.setLayout(new GridLayout(map.mapHeight, map.mapWidth));
		this.addKeyListener(this);
		this.setFocusable(true);
		reloadMap();
	}
	
	private void reloadMap() {
		this.removeAll();
		this.revalidate();
		for (int i = 0; i < map.mapSize; i++) {
			JLabel label = new JLabel(map.list.get(i).image);
			this.add(label);
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		switch(e.getKeyChar()) {
		case 'w': map.moveUp(); reloadMap(); break;
		case 'a': map.moveLeft(); reloadMap(); break;
		case 'd': map.moveRight(); reloadMap(); break;
		case 's': map.moveDown(); reloadMap(); break;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
