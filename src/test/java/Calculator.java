
public class Calculator {

	public static Calculator given(int a, int b) {

		return new Calculator();
	}

	public int then(int a, int b) {

		return a - b;
	}

	public static void main(String[] args) {
		given(1, 2).then(1, 2);

	}
}
