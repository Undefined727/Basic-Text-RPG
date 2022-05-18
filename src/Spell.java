
public class Spell {
	
	int manaCost = 0;
	int value = 0;
	
	// Number of turns, if -1 is permanent, if fight ends duration is cancelled.
	int duration = 0;
	String name = "null";
	
	// 1: Heal value, 2: Increase Flat ATK by value, 3: Increase Flat DEF by value
	int effect = 1;
	
	public Spell(int manaCost, int value, int duration, String name, int effect) {
		try {
			if (effect < 1 || effect > 3) throw new Exception("This Spell's effect doesn't exist!");
			this.manaCost = manaCost;
			this.value = value;
			this.duration = duration;
			this.name = name;
			this.effect = effect;
		}
		catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	public Spell(Spell spell) {
		this(spell.manaCost, spell.value, spell.duration, spell.name, spell.effect);
	}
	
	public Spell() {}
	
	public String toString() {
		return name + " / " + manaCost + " mana";
	}

}
