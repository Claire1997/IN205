public class TestBoard {
    public static void main(String[] args) {
        Board B1 = new Board("B1", 5, 5);
        B1.print();
        Board B2 = new Board("B2");
        B2.setNavire('D', 2, 2);
        B2.setFrappe(true, 1, 1);
        B2.print();
    }
}