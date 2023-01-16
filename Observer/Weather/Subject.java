package Weather;

// 옵저버 관리와 연락 돌리는 책임
public interface Subject {
	public void registerObserver(Observer o);
	public void removeObserver(Observer o);
	public void notifyObservers();
}
