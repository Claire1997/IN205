public class Aircraft_Carrier extends AbstractShip {
    public Aircraft_Carrier(Orientation o) {
        super('C', "Aircraft-Carrier", 5, o);
    }

    public Aircraft_Carrier() {
        super('C', "Aircraft-Carrier", 5, Orientation.EAST);
    }
}