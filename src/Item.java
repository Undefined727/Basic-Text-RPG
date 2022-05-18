
public class Item {
	
	String name = "null";
	// Weapon, Armor, Potion, Spell, Misc
	String type = "null";
	int value = 0;
	int goldValue = 0;
	
	Item(String name, String type, int value, int goldValue) {
		try {
		if (!type.equals("weapon") && !type.equals("armor") && !type.equals("potion") && !type.equals("spell") && !type.equals("misc")) throw new Exception("Not a valid item type!");
		this.name = name;
		this.type = type;
		this.value = value;
		this.goldValue = goldValue;
		}
		catch (Exception e) {
			System.out.println(e.toString());
		}
		
	}
	
	Item(Item item) {
		this(item.name, item.type, item.value, item.goldValue);
	}
	
	Item() {}
	
	public String toString() {
		return name;
	}
}
