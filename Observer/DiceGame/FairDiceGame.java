package DiceGame;

import java.util.Iterator;
import java.util.Random;

public class FairDiceGame extends DiceGame {
    private Random random = new Random();

    @Override
    public void play() {
        int diceNumber = random.nextInt(6) + 1; // 상태 변경 발생
        System.out.println("주사위 던졌다~ " + diceNumber);

        // 연락하기 : 상태 변경을 알리기 위해 메소드 호출
        Iterator<Player> iter = playerList.iterator();
        while(iter.hasNext()) {
            iter.next().update(diceNumber);
        }
    }
}
