
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

class MyFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = -5253291974307356605L;
	private JTextArea textArea = new JTextArea(10, 60);
    private JTextArea textAreaRes = new JTextArea();
    private JButton ok = new JButton("Преобразовать");
    private JMenuBar menuBar = new JMenuBar();
    private JMenuItem opfile = new JMenuItem("Открыть файл");
    private JMenuItem defile = new JMenuItem("Выбрать файл по умолчанию");
    private JMenuItem svfile = new JMenuItem("Сохранить преобразованный файл");
    private JMenu file = new JMenu("Файл");

    private JFileChooser fc = new JFileChooser();
	int i, u, k, q;
    private ArrayList<ArrayList<Integer>> a = new ArrayList<>();
    private ArrayList<Integer> alpha = new ArrayList<>();
    private List<Pair> lp = new ArrayList<>();

    MyFrame() {
		menuBar.add(file);
		file.add(opfile);
		file.add(defile);
		file.add(svfile);
		opfile.addActionListener(this);
		defile.addActionListener(this);
		svfile.addActionListener(this);

		this.setLayout(new BorderLayout());
		JPanel dataPanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		dataPanel.setLayout(new BorderLayout());
		buttonPanel.setLayout(new BorderLayout());

		JPanel mpPanel = new JPanel();
		mpPanel.setLayout(new GridLayout(1, 2));

		mpPanel.add(new JScrollPane(textArea));
		mpPanel.add(new JScrollPane(textAreaRes));
		dataPanel.add(mpPanel, BorderLayout.CENTER);
		buttonPanel.add(ok, BorderLayout.SOUTH);
		dataPanel.add(buttonPanel, BorderLayout.SOUTH);
		dataPanel.add(menuBar, BorderLayout.NORTH);
		this.add(dataPanel);
		ok.addActionListener(this);

		try {
			printText(new File("input.txt"));
		} catch (FileNotFoundException e1) {
			JOptionPane.showMessageDialog(null, "Файл не найден!");
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "Ошибка!");
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == ok) {
			try {
				parse();
				createTex();
				//textAreaRes.setText("");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (e.getSource() == opfile) {
			int returnVal = fc.showOpenDialog(MyFrame.this);
			File openFile = fc.getSelectedFile();
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				try {
					printText(openFile);
				} catch (FileNotFoundException e1) {
					JOptionPane.showMessageDialog(null, "Файл не найден!");
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Ошибка!");
				}
			}
		} else if (e.getSource() == defile) {
			try {

				printText(new File("input.txt"));

			} catch (FileNotFoundException e1) {
				JOptionPane.showMessageDialog(null, "Файл не найден!");
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, "Ошибка!");
			}

		} else if (e.getSource() == svfile) {
			int returnVal = fc.showSaveDialog(MyFrame.this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {

				try {
                    FileWriter writer = new FileWriter(fc.getSelectedFile());
					writer.write(textAreaRes.getText());

					writer.flush();
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Ошибка во время сохранения!");
				}
			}

		}
	}

    private void printText(File f) throws IOException {

		String line;
        BufferedReader bufferedReader = new BufferedReader(new FileReader(f));
		while ((line = bufferedReader.readLine()) != null)
			textArea.append(line + '\n');

	}

	private void createTex() {
		String res = "";
		res += "\\documentclass{article}" + '\n';
		res += "\\usepackage{amssymb,amsmath}" + '\n';
		res += "\\usepackage[english]{babel}" + '\n';
		res += "\\usepackage{graphicx}" + '\n';
		res += "\\begin{document}" + '\n';
		res += "\\begin{center}" + '\n';
		res += "\\includegraphics{image}" + '\n';

		for (int m = 0; m < k; m++) {
			for (int e = 0; e < i; e++) {
				String sa = "$";
				for (int j = 0; j < u; j++)
					if (e + 1 == lp.get(j).getA() && lp.get(j).getW().get(m) == 1) {
						if (sa.length() != 1)
							sa += "+";
						sa += "x_{" + lp.get(j).getA() + lp.get(j).getB() + "}^{" + (m + 1) + "}";
					}
				for (int j = 0; j < u; j++)
					if (e + 1 == lp.get(j).getB() && lp.get(j).getW().get(m) == 1) {
						sa += "-x_{" + lp.get(j).getA() + lp.get(j).getB() + "}^{" + (m + 1) + "}";
					}
				res += sa + "=" + a.get(m).get(e) + "$\\\\" + '\n';
			}
			res += "\\bigskip" + '\n';
		}

		for (int l = 0; l < q; l++) {
			String sa = "$";
			for (int j = 0; j < u; j++)
				for (int m = 0; m < k; m++)
					if (lp.get(j).getLambda().get(m).get(l) != 0) {
						if (lp.get(j).getLambda().get(m).get(l) != 1)
							sa += lp.get(j).getLambda().get(m).get(l);
						sa += "x_{" + lp.get(j).getA() + lp.get(j).getB() + "}^{" + (m + 1) + "}+";
					}
			sa = sa.substring(0, sa.length() - 1);
			sa += "=" + alpha.get(l) + "$\\\\" + '\n';
			res += sa + "\\bigskip" + '\n';
		}

		for (int j = 0; j < u; j++)
			if (lp.get(j).getW().get(k) != 0) {
				String sa = "";
				sa += "$";
				for (int m = 0; m < k; m++)
					sa += "x_{" + lp.get(j).getA() + lp.get(j).getB() + "}^{" + (m + 1) + "}+";
				sa = sa.substring(0, sa.length() - 1);
				sa += "=" + lp.get(j).getW().get(k) + "$\\\\" + '\n';
				res += sa;
			}
		res += "\\bigskip" + '\n' + "\\begin{tabular}{|l|";
		for (int j = 0; j < u; j++) {
			res += "|";
			for (int m = 0; m < k; m++)
				res += "c|";
		}
		res += "}" + '\n' + "\\hline$(i, j)$ ";
		for (int j = 0; j < u; j++)
			res += " &   \\multicolumn{2}{|c||}{$(" + lp.get(j).getA() + ", " + lp.get(j).getB() + ")$}";
		res += "\\\\\\hline" + '\n' + "$k$";
		for (int j = 0; j < u; j++)
			for (int m = 0; m < k; m++)
				res += "&" + (m + 1);
		res += "\\\\\\hline" + '\n' + "\\hline$U^k$";
		for (int j = 0; j < u; j++)
			for (int m = 0; m < k; m++) {
				res += "&";
				if (lp.get(j).getW().get(m) == 1)
					res += "+";
			}
		res += "\\\\\\hline" + '\n' + "$U_0$";
		for (int j = 0; j < u; j++) {
			res += "   &   \\multicolumn{2}{|c||}{$";
			if (lp.get(j).getW().get(k) != 0)
				res += "+";
			res += "$}";
		}
		res += "\\\\\\hline" + '\n' + "\\hline$K(i,j)$";
		for (int j = 0; j < u; j++) {
			res += "   &   \\multicolumn{2}{|c||}{$\\{";
			for (int m = 0; m < k; m++) {
				if (lp.get(j).getW().get(m) != 0)
					res += (m + 1) + ",";
			}
			res = res.substring(0, res.length() - 1);
			res += "\\}$}";
		}
		res += "\\\\\\hline" + '\n' + "$K_0(i,j)$";
		for (int j = 0; j < u; j++) {
			res += "   &   \\multicolumn{2}{|c||}{$";

			if (lp.get(j).getW().get(k) != 0) {
				res += "\\{";

				for (int m = 0; m < k; m++)
					res += (m + 1) + ",";
				res = res.substring(0, res.length() - 1);
				res += "\\}";
				//res = res.substring(0, res.length()-1);
			}
			res += "$}";
		}
		res += "\\\\\\hline" + '\n';
		res += "\\end{tabular}" + '\n';
		res += "\\\\\\bigskip" + '\n';
		res += "\\end{center}" + '\n';
		res += "\\end{document}" + '\n';
		textAreaRes.setText(res);
	}

	private void parse() throws Exception {
		String s = textArea.getText();
		i = Integer.valueOf(s.substring(7, s.indexOf('\n'))); // 7 - "/*|I|*/"
		s = s.substring(s.indexOf("/*|U|*/"));
		u = Integer.valueOf(s.substring(7, s.indexOf("\n"))); // 7 - "/*|U|*/"
		for (int i = 0; i < u; i++) {
			s = s.substring(s.indexOf("{"));
			String v = s.substring(1, s.indexOf("}"));
			s = s.substring(1);
			lp.add(new Pair(Integer.valueOf(v.substring(0, v.indexOf(","))),
					Integer.valueOf(v.substring(v.indexOf(",") + 1))));
		}
		s = s.substring(s.indexOf("/*|K|*/"));
		k = Integer.valueOf(s.substring(7, s.indexOf("\n"))); // 7 - "/*|K|*/"
		for (int i = 0; i < k + 1; i++) {
			s = s.substring(14); // 14 - "/*widetilde{U}"
			for (int j = 0; j < u; j++) {
				s = s.substring(s.indexOf("/*"));
				s = s.substring(s.indexOf("*/"));
				lp.get(j).getW().add(Integer.valueOf(s.substring(2, s.indexOf("\n")))); // 2 - "*/"
			}
		}
		s = s.substring(s.indexOf("/*q*/"));
		q = Integer.valueOf(s.substring(5, s.indexOf("\n"))); // 5 - "/*q*/"
		s = s.substring(s.indexOf("/*lambda"));
		for (int l = 0; l < u; l++)
			for (int i = 0; i < q; i++)
				lp.get(l).getLambda().add(new ArrayList<Integer>());
		for (int i = 0; i < q; i++)
			for (int j = 0; j < k; j++)
				for (int l = 0; l < u; l++) {
					s = s.substring(s.indexOf("/*"));
					s = s.substring(s.indexOf("*/"));
					lp.get(l).getLambda().get(i).add(Integer.valueOf(s.substring(2, s.indexOf("\n"))));
				}
		for (int l = 0; l < u; l++)
			if (lp.get(l).getW().get(k) == 1) {
				s = s.substring(s.indexOf("/*d_"));
				s = s.substring(s.indexOf("0*/"));
				lp.get(l).getW().set(k, Integer.valueOf(s.substring(3, s.indexOf("\n"))));
			}
		for (int l = 0; l < k; l++) {
			a.add(new ArrayList<Integer>());
		}
		for (int j = 0; j < k; j++)
			for (int l = 0; l < i; l++) {
				s = s.substring(s.indexOf("/*a_"));
				s = s.substring(s.indexOf("*/"));
				a.get(j).add(Integer.valueOf(s.substring(2, s.indexOf("\n"))));
			}
		for (int l = 0; l < q; l++) {
			s = s.substring(s.indexOf("/*alpha_"));
			s = s.substring(s.indexOf("*/"));
			alpha.add(Integer.valueOf(s.substring(2, s.indexOf("\n"))));
		}
	}
}
