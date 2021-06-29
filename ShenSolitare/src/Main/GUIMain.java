package Main;

import javax.swing.JFrame;

import GUI.BoardPanel;

public class GUIMain {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.add(new BoardPanel());
		
		frame.setVisible(true);
	}
}
