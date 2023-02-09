public class TigerAdaper extends Animal {

    private Tiger tiger;

    public TigerAdaper(String name) {
        super(name);

        tiger = new Tiger();
        tiger.setName(name);
    }

    @Override
    public void sound() {
        System.out.println(tiger.getName() + " ");
        tiger.roar();
    }
}
