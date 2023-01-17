public class SingletonClient {
    public static void main(String[] args) {
        SingletonEnum singleton1 = SingletonEnum.UNIQUE_INSTANCE;
        singleton1.say();

        SingletonEnum singleton2 = SingletonEnum.UNIQUE_INSTANCE;
        singleton2.say();

        System.out.println(singleton1 == singleton2);
    }
}
