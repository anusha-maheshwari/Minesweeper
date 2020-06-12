import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MineSweeperPanel extends JPanel {

	private final int SIZE_PANEL = 600;
	private Board board;
	private int rows, cols, mines;
	Timer timer = new Timer(1000, null);
	
	
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Minesweeper!");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.add(new MineSweeperPanel());
		frame.pack();
		frame.setVisible(true);
		
	}
	public MineSweeperPanel() {
		rows = 9;
		cols = 9;
		mines = 10;
		
		board = new Board(rows, cols, mines);
		this.setPreferredSize(new Dimension(this.SIZE_PANEL,SIZE_PANEL));
		this.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent me) {
				board.handleClick(me);
				repaint();
			}		
		});
		timer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				board.tick();
				repaint();
			}
			
		});
		timer.start();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		board.draw(g);
	}
	
	
	
	

}