import java.awt.Image;
import java.util.Arrays;
import java.util.Random;

public class Character {
	Random rand = new Random();
	
	public static final int INVENTORY_SIZE = 8;
	public static final int SPELL_SLOT_COUNT = 4;
	
	String name = "null";
	boolean isPlayer = false;
	
	int health;
	int maxHealth;
	int mana;
	int maxMana;
	int level;
	int gold;

	int attack = 0;
	int defense = 0;
	int attackSpellBonus = 0;
	int attackSpellDuration = 0;
	int defenseSpellBonus = 0;
	int defenseSpellDuration = 0;
	
	Item[] inventory = new Item[INVENTORY_SIZE];
	Spell[] spells = new Spell[SPELL_SLOT_COUNT];
	Item armor = new Item();
	Item weapon = new Item();
	
	int itemCount = 0;
	int spellCount = 0;
	
	Image icon = null;
	//Constructors
	
	public Character() {
		this(null, 1);		
	}
	
	public Character(String name) {
		this(name, 1);
	}
	
	public Character(String name, int level) {
		this.name = name;
		this.level = level;
		health = 100*level;
		maxHealth = 100*level;
		mana = 100*level;
		maxMana = 100*level;
		gold = 100*level;
		Arrays.fill(inventory, 0, inventory.length - 1, new Item());
		Arrays.fill(spells, 0, spells.length - 1, new Spell());
	}
	
	
	//Combat
	
	public String takeDamage(int damageTaken) {
		if (damageTaken < 0) damageTaken = 0;
		health = health-damageTaken;
		if (health < 0) health = 0;
		return name + " Took " + damageTaken + " Damage!";
	}
	
	public String heal(int amountHealed) {
		health = health+amountHealed;
		if (health > maxHealth) health = maxHealth;
		return name + " Healed " + amountHealed + " HP!";
	}
	
	// returns true if the target is dead
	public String attack(Character target) {
		return target.takeDamage(attack + attackSpellBonus + level*10 + (rand.nextInt(10) + 1) - target.defense - target.defenseSpellBonus);
	}
	
	public String useSpell(int slot) {
		if (slot > 0 && slot <= spellCount) {
			return useSpell(spells[slot-1]);			
		}
		return "Invalid Slot!";
	}
	
	public String useSpell(Spell spell) {
		if (spell.effect == 1) {
			if (mana >= spell.manaCost) {
				heal(spell.value);
				mana -= spell.manaCost;
				return name + " Cast a Healing Spell!";
				}
				else return "Not enough Mana!";
		}
		if (spell.effect == 2 && attackSpellDuration <= 0) {
			if (mana >= spell.manaCost) {
				attackSpellDuration = spell.duration + 1;
				attackSpellBonus = spell.value;
				mana -= spell.manaCost;
				return name + " Cast an Attack Bonus Spell!.";
				}
				else return "Not enough Mana!";
		}
		if (spell.effect == 3 && defenseSpellDuration <= 0) {
			if (mana >= spell.manaCost) {
				defenseSpellDuration = spell.duration + 1;
				defenseSpellBonus = spell.value;
				mana -= spell.manaCost;
				return name + " Cast a Defense Bonus Spell!.";
				}
				else return "Not enough Mana!";
		}
		if (spell.effect == 2 && attackSpellDuration > 0) return "Attack Spell is already in action!";
		if (spell.effect == 3 && defenseSpellDuration > 0) return "Defense Spell is already in action!";
		return "Bad spell type - blame coder";
	}
	
	public String usePotion(int slot) {
		int savedHealedValue = 0;
		if (slot > 0 && slot <= itemCount) {
			if (inventory[slot-1].type.equals("potion")) {
				savedHealedValue = inventory[slot-1].value;
				inventory[slot-1] = new Item();
				itemCount--;
				sortInventory();
				return heal(savedHealedValue);
			}
			return "That's not a potion!";
		}
		return "Invalid Slot!";
	}
	
	public String listStatus() {
		return name + ": " + health + "/" + maxHealth + " HP, " + mana + "/" + maxMana + " Mana.";
	}
	
	public String equippedArmor() {
		if (armor.name.equals("null")) return "You don't have any armor equipped.";
		return "Armor: " + armor.name;
	}
	
	public String equippedWeapon() {
		if (weapon.name.equals("null")) return "You don't have a weapon equipped.";
		return "Weapon: " + weapon.name;
	}
	
	public void tickDuration() {
		if (attackSpellDuration > 0) {
			attackSpellDuration--;
		}
		else attackSpellBonus = 0;
		if (defenseSpellDuration > 0) {
			defenseSpellDuration--;
		}
		else defenseSpellBonus = 0;
	}
	
	
	
	
	// Inventory/Spells Management	
	
	public void sortInventory() {
		int stackedItems = 0;
		for(int i = 0; i < INVENTORY_SIZE && stackedItems < itemCount; i++) {
			if (!inventory[i].name.equals("null")) {
				inventory[stackedItems] = inventory[i];
				stackedItems++;
			}
		}
	}
	
	public void sortSpells() {
		int stackedItems = 0;
		for(int i = 0; i < SPELL_SLOT_COUNT && stackedItems < spellCount; i++) {
			if (!spells[i].name.equals("null")) {
				spells[stackedItems] = spells[i];
				stackedItems++;
			}
		}
	}
	
	// returns true if item was picked up
	public boolean giveItem(Item item) {
		if (itemCount < INVENTORY_SIZE) {
			inventory[itemCount] = item;
			itemCount++;
			return true;
		}
		else return false;
	}
	
	public String sellItem(int slot) {
		if (itemCount >= slot && slot > 0) {
			gold += inventory[slot-1].goldValue;
			inventory[slot-1] = new Item();
			itemCount--;
			sortInventory();
			return "Item Sold.";
		}
		return "Invalid Slot.";
	}
	
	public Item removeItem(int slot) {
		Item returnedItem = new Item(inventory[slot-1]);
		if (itemCount >= slot && slot > 0) {
			inventory[slot-1] = new Item();
			itemCount--;
			sortInventory();
			return returnedItem;
		}
		return null;
	}
	
	public String equipItem(int slot) {
		if (itemCount >= slot && slot > 0) {
			if (inventory[slot-1].type.equals("armor") && inventory[slot-1].value > armor.value) {
				if (armor.name.equals("null")) itemCount--;
				armor = inventory[slot-1];
				inventory[slot-1] = armor;
				sortInventory();
				defense = armor.value;
				return armor.name + " Equipped!";
			}
			else if (inventory[slot-1].type.equals("weapon") && inventory[slot-1].value > weapon.value) {
				if (weapon.name.equals("null")) itemCount--;
				weapon = inventory[slot-1];
				inventory[slot-1] = weapon;
				sortInventory();
				attack = weapon.value;
				return weapon.name + " Equipped!";
			}
			else if (inventory[slot-1].type.equals("armor")) return "You cannot equip worse armor!";
			else if (inventory[slot-1].type.equals("weapon")) return "You cannot equip a worse weapon!";
			else return "This item is not equippable!" + " It's a " + inventory[slot-1].type;
		}
		else return "Invalid Slot.";
	}
	
	public String equipSpell(Spell spell) {
		if (spellCount >= SPELL_SLOT_COUNT) return "No more space for spells!";
		spells[spellCount] = spell;
		spellCount++;
		return spell.name + " equipped!";
	}
	
	public String equipSpell(int inventorySlot) {
		Item savedItem = new Item();
		if (inventorySlot > 0 && inventorySlot <= itemCount) {
		if (inventory[inventorySlot-1].type.equals("spell")) {
			if (spellCount < SPELL_SLOT_COUNT) savedItem = removeItem(inventorySlot);
			if (savedItem.name.equals("Spell of Healing - 50 HP")) return equipSpell(new Spell(10, 50, 1, "Spell of Healing - 50 HP", 1));
			// Add more spells here
			
			return "The coder forgot to add in this spell :P";
			}
		return "That's not a spell!";
		}
		return "Invalid Slot.";
	}
	
	public String removeSpell(int slot) {
		if (slot <= 0 || slot > spellCount) return "Invalid Slot.";
		spells[slot-1] = new Spell();
		sortSpells();
		return "Spell Removed.";
	}
	
	public String listInventory() {
		String returned = "Inventory: [";
		if (itemCount == 0) return "Your Inventory is Empty!";
		for(int i = 1; i < itemCount+1; i++) returned+= "Slot " + i + ": " + inventory[i-1].toString() + ", ";
		returned = returned.substring(0, returned.length()-2);
		return returned + "]";
	}
	
	public String listSpells() {
		String returned = "Spells: [";
		if (spellCount == 0) return "You Have No Spells!";
		for(int i = 1; i < spellCount+1; i++) returned+= "Slot " + i + ": " + spells[i-1].toString() + ", ";
		returned = returned.substring(0, returned.length()-2);
		return returned + "]";
	}
	
	
	
	
	
	
	
	
	
	
	// Helpful Extras
	
	public boolean hasPotion() {
		for(int i = 0; i < itemCount; i++) if (inventory[i].type.equals("potion")) return true;
		return false;
	}
	
	public boolean hasSpell() {
		return listSpells().equals("You Have No Spells!");
	}

}