package Weather;

// 연락을 받아 변경된 상태를 받아오는 책임
public interface Observer {
	public void update(float temp, float humidity, float pressure);
}
