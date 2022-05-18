import java.util.Scanner;

public class Main {
	
	public static Character player;
	public static Scanner keyboard = new Scanner(System.in);

	public static void main(String[] args) {
		
		
		Item map = new Item("Map", "misc", 0, 10);
		Item basicSword = new Item("Basic Sword - 15 ATK", "weapon", 15, 30);
		Item healingSpell = new Item("Spell of Healing - 50 HP", "spell", 50, 0);
		Item potion = new Item("Healing Potion - 50 HP", "potion", 50, 40);
		Character enemy1 = new Character("Zombie");

		GameWindow window = new GameWindow();
		
		
		
		
		
		
		
		
		
		 String playerName = keyboard.next();
		player = new Character(playerName);
		player.giveItem(map);
		player.giveItem(basicSword);
		player.equipItem(2);
		player.giveItem(healingSpell);
		player.equipSpell(2);
		player.giveItem(potion);
		battle(enemy1);
		
		

	}
	
	public static void battle(Character enemy) {
		char inputChar = 'f';
		int inputInt = 0;
		boolean badInput = true;
		
		System.out.println(enemy.name + " Approaches!");
		while(true) {
			badInput = true;
			System.out.println(enemy.listStatus());
			System.out.println(player.listStatus());
			System.out.println(player.equippedArmor());
			System.out.println(player.equippedWeapon());
			System.out.println(player.listSpells());
			System.out.println(player.listInventory());
			System.out.println("Press A to attack, P to use a potion, or S to cast a spell.");
			inputChar = keyboard.next().charAt(0);
			
			switch (inputChar){
			case 'A':
				System.out.println(player.attack(enemy));
				player.tickDuration();
				break;
			case 'a':
				System.out.println(player.attack(enemy));
				player.tickDuration();
				break;
			case 'P':
				if (player.hasPotion()) {
					System.out.println("Please choose the slot of the potion you want to use.");
					System.out.println(player.listInventory());
					inputInt = keyboard.nextInt();
					
					System.out.println(player.usePotion(inputInt));
				}
				else System.out.println("You don't have any potions!");
				player.tickDuration();
				break;
			case 'p':
				if (player.hasPotion()) {
					System.out.println("Please choose the slot of the potion you want to use.");
					System.out.println(player.listInventory());
					inputInt = keyboard.nextInt();
					
					System.out.println(player.usePotion(inputInt));
				}
				else System.out.println("You don't have any potions!");
				player.tickDuration();
				break;
			case 'S':
				if (!player.hasSpell()) {
				System.out.println("Please choose the slot of the Spell you want to use.");
				System.out.println(player.listSpells());
				inputInt = keyboard.nextInt();
				
				System.out.println(player.useSpell(inputInt));
				}
				else System.out.println("You don't have any spells!");
				player.tickDuration();
				break;
			case 's':
				if (!player.hasSpell()) {
				System.out.println("Please choose the slot of the Spell you want to use.");
				System.out.println(player.listSpells());
				inputInt = keyboard.nextInt();
				
				System.out.println(player.useSpell(inputInt));
				}
				else System.out.println("You don't have any spells!");
				player.tickDuration();
				break;
			default:
				System.out.println("That's not a valid response!");
				player.tickDuration();
				break;
			}
			
			if (enemy.health == 0) {
				System.out.println(enemy.name + " has been defeated!");
				break;
			}
			
			// Edit in AI
			System.out.println(enemy.attack(player));
			enemy.tickDuration();
			
			if (player.health == 0) {
				System.out.println(player.name + " has been defeated!");
				break;
			}
			
		}
	}

}
