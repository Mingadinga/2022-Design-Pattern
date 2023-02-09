package Printer;

public class MainEntry {
    public static void main(String[] args) {
        //  기능 객체화하고 실행
        Command clearCmd = new ClearCommand();
        clearCmd.run();

        Command redColorCmd = new ColorCommand(ColorCommand.Color.RED);
        redColorCmd.run();

        // 배치처럼 실행
        CommandGroup cmdGroup = new CommandGroup();

        Command moveCmd = new MoveCommand(10, 1);
        cmdGroup.add(moveCmd);

        Command printCmd = new PrintCommand("안녕하세요! 디자인패턴");
        cmdGroup.add(printCmd);

        Command moveCmd2 = new MoveCommand(15, 5);
        cmdGroup.add(moveCmd2);

        cmdGroup.run();
    }
}
