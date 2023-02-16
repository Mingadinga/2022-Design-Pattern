package headfirst.designpatterns.combining.ducks;

public interface QuackObservable {
    public void registerObserver(Observer observer);
    public void notifyObservers();
}
