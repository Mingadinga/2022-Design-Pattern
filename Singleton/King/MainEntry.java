public class MainEntry {
    public static void main(String[] args) {
        King king1 = King.getInstance();
        king1.say();

        King king2 = King.getInstance();
        king2.say();

        System.out.println(king1 == king2);
    }
}
