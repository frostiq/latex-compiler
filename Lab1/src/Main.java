import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		MyFrame frame = new MyFrame();
		frame.setSize(1350, 600);
		frame.setTitle("");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE & JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

}