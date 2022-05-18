import javax.swing.ImageIcon;

public class Tile {
	ImageIcon image;
	boolean hasCollision = false;
	
	public Tile() {
		image = new ImageIcon("sprites/tile005.png");
	}
	
	public Tile(String fileName) {
		image = new ImageIcon(fileName);
	}
	
	public Tile(String fileName, boolean collision) {
		this(fileName);
		hasCollision = collision;
	}
}
