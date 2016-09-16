import java.util.ArrayList;


public class Pair {
	private int a;
	private int b;
	private ArrayList <Integer> w;
	private ArrayList <ArrayList <Integer>> lambda;
	
	@Override
	public String toString() {
		return "Pair [a=" + a + ", b=" + b + ", w=" + w + ", lambda=" + lambda
				+ "]";
	}

	public Pair(int a, int b) {
		super();
		w = new ArrayList <Integer>();
		lambda = new ArrayList <ArrayList <Integer>>();
		this.a = a;
		this.b = b;
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}
	
	public ArrayList<Integer> getW() {
		return w;
	}

	public void setW(ArrayList<Integer> w) {
		this.w = w;
	}

	public ArrayList<ArrayList<Integer>> getLambda() {
		return lambda;
	}

	public void setLambda(ArrayList<ArrayList<Integer>> lambda) {
		this.lambda = lambda;
	}


}
