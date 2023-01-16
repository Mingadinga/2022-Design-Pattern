public class MainEntry {
    public static void main(String[] args) {
        Strings strings = new Strings();

        strings.add("Hello~");
        strings.add("My Name is Hwi Min.");
        strings.add("I am a student.");
        strings.add("Desing Pattern is powerful tool.");

        strings.print();

        Item decorator = new SideDecorator(strings, '\"');
        decorator = new LineNumberDecorator(decorator);
        decorator.print();

        decorator = new BoxDecorator(decorator);
        decorator.print();
    }
}
