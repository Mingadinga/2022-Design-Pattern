public class MiniDuckSimulator {
    public static void main(String[] args) {
        // 생성자 주입 방식
        Duck mallard = new MallardDuck();
        mallard.performQuack();
        mallard.performFly();

        // 생성자로 주입 후 수정자로 수정 주입
        Duck model = new ModelDuck();
        model.performFly();
        model.setFlyBehavior(new FlyRocketPowered());
        model.performFly();
    }
}