package Controller;

public class Random {
	public static int ran() {
		int code = (int) Math.floor(((Math.random() * 899999) + 100000));
		return code;
	}
}
