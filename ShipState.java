
public class ShipState {
	public AbstractShip ship;
	public boolean struck;
	protected int count_strike;
	
	public void addStrike() {
		struck=true;
		count_strike++;
	}
	
	public boolean isStruck() {
		return struck;
	}
	
	public String toString() {
		if(struck)
			System.out.print(ColorUtil.colorize(ship.label,ColorUtil.Color.RED));
		else
			System.out.println(ship.label);
		return ship.name;
	}
	
	public boolean isSunk() {
		if(count_strike>=ship.taille)
			return true;
		else
			return false;
	}
	
	
	public  AbstractShip getship() {
		// ship.name= toString();
		if(isSunk())
			System.out.println("It is sunk");
		return ship;
	}
	
}
