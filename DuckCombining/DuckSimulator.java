package headfirst.designpatterns.combining.ducks;

public class DuckSimulator {
	public static void main(String[] args) {
		DuckSimulator simulator = new DuckSimulator();
		AbstractDuckFactory duckFactory = new CountingDuckFactory();
		simulator.simulate(duckFactory);
	}
  
	void simulate(AbstractDuckFactory duckFactory) {
		Quackable mallardDuck = duckFactory.createMallardDuck();
		Quackable redheadDuck = duckFactory.createRedheadDuck();
		Quackable duckCall = duckFactory.createDuckCall();
		Quackable rubberDuck = duckFactory.createRubberDuck();
		Quackable gooseDuck = new GooseAdapter(new Goose());
 
		System.out.println("\nDuck Simulator");

		Flock flockOfDucks = new Flock();
		flockOfDucks.add(redheadDuck);
		flockOfDucks.add(duckCall);
		flockOfDucks.add(rubberDuck);
		flockOfDucks.add(gooseDuck);

		Quackologist quackologist = new Quackologist();
		flockOfDucks.registerObserver(quackologist);

		System.out.println("\n오리 시뮬레이션 게임: 전체 무리");
 		flockOfDucks.quack();

		System.out.println("오리가 소리 낸 횟수: " + QuackCounter.getQuacks() +" 번");
	}
   
	void simulate(Quackable duck) {
		duck.quack();
	}
}
