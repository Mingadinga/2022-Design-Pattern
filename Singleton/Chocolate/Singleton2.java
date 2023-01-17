public class Singleton2 {
    private static Singleton2 uniqueInstance = new Singleton2();

    private Singleton2() {}

    public static Singleton2 getInstance() {
        return uniqueInstance;
    }

    public void say() {
        System.out.println("I am the only one..");
    }
}
