# 디자인패턴 소개와 전략패턴

`요구사항 : 오리가 날 수 있는 기능을 추가해주세요.`

## 시도1 : 오리의 모든 행동을 구현한 슈퍼클래스에 fly()를 추가
- 슈퍼클래스를 상속한 모든 자식에 fly()가 추가됨
- 날지 못하는 오리들은 오버라이딩하여 날지 않도록 구현
- 한계 : 업데이트할 때마다 자식 클래스의 메소드를 일일이 살펴보고 오버라이딩해야함. 유지보수에 불리

## 시도2 : 추가 기능을 Flyable, Quackable 인터페이스를 사용해 해당 기능을 사용하는 오리만 인터페이스를 구현하기
- 오리마다 요구사항을 확인하고 오버라이딩하지 않아도 됨
- 한계 : 자바 인터페이스는 구현 코드가 없어 코드 재사용 불가. 행동이 바뀔 때마다 모든 코드를 일일이 고쳐야한다.
- 아이디어 : 코드에 새로운 요구 사항이 있을 때마다 바뀌는 부분이 있다면 분리한다. 바뀌지 않는 부분에는 영향을 미치지 않고 그 부분만 고치거나 확장한다.

## 시도3 : 변화하는 부분 뽑아내기
- 변화하는 부분 : fly()와 quack()는 오리 종류에 따라 달라진다.
- 변화하는 부분 설계하기 : FlyBehavior 인터페이스를 두고 구현체를 두어 행동을 구현한다. FlyWithWings, FlyNoWay 등등
- 오리 객체는 행동 객체를 가지며, 오리 객체를 생성할 때 생성자로 행동 객체를 주입한다.

시도3을 구현한 코드이다. **객체지향적인 관점에서 주목해야할 것은 변화하는 부분을 책임으로 분리해 별도의 객체에 할당한 점이다.**
- 핵심1 : 요구사항에 따라 변화하는 책임(오리의 행위)을 별도의 객체(Flyable, Quackable)에 위임하여 구현체 선택을 유연하게 만들었다.
- 핵심2 : 인터페이스에 맞춰 프로그래밍하여 책임을 수행하는 구현체가 실행 중에 바뀔 수 있다.

```Java
// 나는 행위와 관련된 객체들
// 타입은 FlyBehavior이다.
public interface FlyBehavior {
    public void fly();
}

public class FlyWithWings implements FlyBehavior{

    @Override
    public void fly() {
        System.out.println("날고 있어요!!");
    }

}

public class FlyNoWay implements FlyBehavior {

    @Override
    public void fly() {
        System.out.println("저는 못 날아요");
    }
}
```

```Java
// 우는 행위와 관련된 객체들
// 타입은 QuackBehavior이다.

public interface QuackBehavior {
    public void quack();
}

public class Quack implements QuackBehavior {

    @Override
    public void quack() {
        System.out.println("꽥");
    }

}

public class MuteQuack implements QuackBehavior {

    @Override
    public void quack() {
        System.out.println("<< 조용~ >>");
    }

}
```


```Java
// Duck 상위 객체. 필수 요구사항인 display, performFly, performQuack를 포함하고 있다.
// 오리를 생성할 떄 이 클래스를 구현하여 display를 구현하고 행동 객체를 주입한다.
public abstract class Duck {    
    FlyBehavior flyBehavior;    
    QuackBehavior quackBehavior;  

    public Duck() { }	     
    public abstract void display();    
    
    public void performFly() {        
        flyBehavior.fly();  
    }    
    
    public void performQuack() {
        quackBehavior.quack();  
    }     
    
    public void swim() {        
        System.out.println("모든 오리는 물에 뜹니다. 가짜 오리도 뜨죠");  
    }
}
```

```Java
// 행위 객체의 주입은 오리 구현체의 생성자에서 이루어진다.
public class MallardDuck extends Duck {

    public MallardDuck() {
        quackBehavior = new Quack(); // 구체적인 행위 객체를 선택한다.
        flyBehavior = new FlyNoWay(); // 구체적인 행위 객체를 선택한다.
    }

    @Override
    public void display() {
        System.out.println("저는 물오리입니다");
    }

}

public class MiniDuckSimulator {
    public static void main(String[] args) {
        Duck mallard = new MallardDuck(); // 생성자에서 행동 객체의 주입이 이루어진다.
        mallard.performQuack();
        mallard.performFly();
    }
}

```



구현체인 오리는 FlyBehavior 타입의 객체에 메시지를 보내  오리가 나는 행위를 수행하도록 요청한다.<br>
이때 **나는 행동과 관련된 행위를 수행할 수 있는 책임만 가지고 있는 객체**라면 어떤 객체든 이 메시지를 수신할 수 있다. 날개로 날아도 되고, 날지 않아도 된다.<br>
따라서 오리 객체가 어떤 FlyBehavior 객체를 선택하냐에 따라 그 오리가 수행하는 나는 행위가 달라진다.<br>

## 시도4
- 생성자 주입 방식으로 생성자를 통해 객체를 주입할 수 있다.
- 실행 중에 의존관계에 있는 객체를 변경하기 위해 수정자를 사용한다.

```Java
// Duck에 수정자를 추가한다.
public abstract class Duck {

    FlyBehavior flyBehavior;
    QuackBehavior quackBehavior;

    public void setFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }

    public void setQuackBehavior(QuackBehavior quackBehavior) {
        this.quackBehavior = quackBehavior;
    }

    // ... 
}

```

```Java
// 새로운 오리를 만든다. 생성자로 주입된다.
public class ModelDuck extends Duck {

    public ModelDuck() {
        flyBehavior = new FlyNoWay();
        quackBehavior = new Quack();
    }

    @Override
    public void display() {
        System.out.println("저는 모형 오리입니다");
    }
}
```

```Java
// 오리를 사용하는 호출부에서 수정자로 의존관계를 바꿔 주입한다.
public class MiniDuckSimulator {
    public static void main(String[] args) {
        // 생성자로 주입 후 수정자로 수정 주입
        Duck model = new ModelDuck();
        model.performFly();
        model.setFlyBehavior(new FlyRocketPowered());
        model.performFly();
    }
}
```

### 🤔 이 코드에 대한 의견<br>
수정자로 객체의 의존관계를 수정하는 것은 좋은 방법이 아니다. 객체의 내부 상태를 외부에 공개하는 꼴이기 때문이다.
실행 중에 의존관계를 변경하고 싶다면, 의존관계 주입 책임을 별도의 객체에 할당하는 것이 더 유연하다.


# is-a 보다는 has-a를!
오리 예제에서 상속으로 구현된 코드를 인터페이스 구성 코드로 고쳤다.
<br><br>
상속은 요구사항 변경에 유연하게 대응할 수 없었던 반면, 구성을 사용하면 책임을 객체에 할당하여 코드 변경 없이 구현체를 바꿔치기할 수 있다.
오리의 행동(변화하는 부분)을 알고리즘 군으로 추상했다. 오리의 행동을 선택하는 오리 객체가 원하는 행동 객체를 주입했다.
이러한 접근 방식을 전략 패턴이라고 한다. 변화하는 부분을 알고리즘으로 분리하여 기존 객체들과 독립적으로 변경할 수 있다.
<br><br>
상속(is-a) 보다는 구성(has-a)를 사용하는 것이 더 객체지향적인 방법이다.
애플리케이션의 책임이라는 거대한 덩어리를 작은 책임으로 나누어 객체에게 할당하고, 객체들은 내부 구현을 모르는 채로 다른 객체의 책임 명세(메시지)만 보면서 상호작용하여 협력한다.
자바는 책임의 명세를 프로그래밍 언어에서 표현하기 위한 방법으로 인터페이스를 선택했다. 인터페이스를 두면 다른 객체는 이 인터페이스를 구현한 객체가 어떤 책임을 가지며, 내 요청을 받아들일 수 있는지 확인할 수 있다.
구성 방식으로 객체의 상호작용을 구성하면 훨씬 유연하게 의존관계를 구성할 수 있다. 메시지만 수신할 수 있다면 어떤 행동을 하는지는 객체의 몫이기 때문이다.