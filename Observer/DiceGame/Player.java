package DiceGame;

public abstract class Player {

    private String name;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // 연락 받기 : 상태가 변경되면 호출되는 메소드
    public abstract void update(int diceNumber);
}
