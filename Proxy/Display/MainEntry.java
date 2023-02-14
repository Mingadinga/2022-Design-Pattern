public class MainEntry {
    public static void main(String[] args) {
//        Display display = new ScreenDisplay();
        Display display = new BufferDisplay(5);

        display.print("안녕하세요.");
        display.print("소프트웨어 설계를 위한 디자인패턴");
        display.print("프록시~~");
        display.print("안녕하세요.");
        display.print("소프트웨어 설계를 위한 디자인패턴");
        display.print("프록시~~");

        ((BufferDisplay) display).flush();
    }
}
