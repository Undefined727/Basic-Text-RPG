import java.util.LinkedList;

public class Map {
	
	LinkedList<Tile> list = new LinkedList<Tile>();
	int mapWidth = 20;
	int mapHeight = 20;
	int mapSize = mapWidth*mapHeight;
	int spawnX = 8;
	int spawnY = 4;
	int currentX = spawnX;
	int currentY = spawnY;
	Tile savedTile;
	Tile player = new Tile("sprites/tile510.png");
	
	public Map() {
		for(int i = 0; i < mapWidth; i++) {
			list.add(new Tile("sprites/tile202.png", true));
		}
		for(int i = 0; i < mapHeight-2; i++) {
			list.add(new Tile("sprites/tile202.png", true));
			for(int j = 0; j < mapWidth-2; j++) {
				list.add(new Tile());
			}
			list.add(new Tile("sprites/tile202.png", true));
		}
		for(int i = 0; i < mapWidth; i++) {
			list.add(new Tile("sprites/tile202.png", true));
		}
		savedTile = list.get(spawnY*mapWidth+spawnX);
		list.set(spawnY*mapWidth+spawnX, player);
		list.set(spawnY*mapWidth+spawnX+6, new Tile("sprites/tile1745.png", true));
	}
	
	// Add reading from file
	
	
	
	public boolean moveUp() {
		if (currentY <= 0) return false;
		if (list.get((currentY-1)*mapWidth+currentX).hasCollision) return false;
		list.set(currentY*mapWidth+currentX, savedTile);
		currentY--;
		savedTile = list.get(currentY*mapWidth+currentX);
		list.set(currentY*mapWidth+currentX, player);
		return true;	
	}
	
	public boolean moveLeft() {
		if (currentX <= 0) return false;
		if (list.get(currentY*mapWidth+currentX-1).hasCollision) return false;
		list.set(currentY*mapWidth+currentX, savedTile);
		currentX--;
		savedTile = list.get(currentY*mapWidth+currentX);
		list.set(currentY*mapWidth+currentX, player);
		return true;	
	}
	
	public boolean moveDown() {
		if (currentY >= mapHeight-1) return false;
		if (list.get((currentY+1)*mapWidth+currentX).hasCollision) return false;
		list.set(currentY*mapWidth+currentX, savedTile);
		currentY++;
		savedTile = list.get(currentY*mapWidth+currentX);
		list.set(currentY*mapWidth+currentX, player);
		return true;	
	}
	
	public boolean moveRight() {
		if (currentX >= mapWidth-1) return false;
		if (list.get(currentY*mapWidth+currentX+1).hasCollision) return false;
		list.set(currentY*mapWidth+currentX, savedTile);
		currentX++;
		savedTile = list.get(currentY*mapWidth+currentX);
		list.set(currentY*mapWidth+currentX, player);
		return true;	
	}
}
