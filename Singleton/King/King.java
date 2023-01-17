public class King {
    private static King self = null;

    private King() {}

    public static King getInstance() {
        if(self == null) {
            self = new King();
        }
        return self;
    }

    public void say() {
        System.out.println("I am the only one..");
    }
}
