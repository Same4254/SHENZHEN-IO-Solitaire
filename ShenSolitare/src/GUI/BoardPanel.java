package GUI;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import Board.Board;
import Board.Cards.CardStack;
import Board.Cards.DescendingCardStack;

public class BoardPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private Board board;
	
	private DescendingCardStack heldCards;
	
	public BoardPanel() {
		this.board = new Board();
		this.heldCards = new DescendingCardStack();
		
		board.getCardStacks().get(1).setPosition(100, 0);
		
		MouseAdpt adpt = new MouseAdpt();
		
		addMouseListener(adpt);
		addMouseMotionListener(adpt);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		
		board.render(g2d);
		
		if(heldCards.getCards().size() > 0)
			heldCards.render(g2d);
	}
	
	private class MouseAdpt extends MouseAdapter {
		private int offsetX, offsetY;
		
		@Override
		public void mousePressed(MouseEvent e) {
			for(CardStack s : board.getCardStacks()) {
				int startIndex = s.getIndexFromMousePosition(e.getX(), e.getY());
				if(startIndex == -1)
					continue;
				
				for(int i = startIndex; i < s.getCards().size(); i++)
					heldCards.addCard(s.getCards().get(i));
				
				offsetX = (int) (e.getX() - heldCards.getCards().get(0).getHitBox().x);
				offsetY = (int) (e.getY() - heldCards.getCards().get(0).getHitBox().y);
				
				s.removeFromIndex(startIndex);
				
				break;
			}
		}
		
		@Override
		public void mouseDragged(MouseEvent e) {
			heldCards.setPosition(e.getX() - offsetX, e.getY() - offsetY);
		}
		
		@Override
		public void mouseReleased(MouseEvent e) {
			
		}
	}
}
