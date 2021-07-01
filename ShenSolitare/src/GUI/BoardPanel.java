package GUI;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

import Board.Board;
import Board.Cards.Card;
import Board.Cards.CardStack;
import Board.Cards.DescendingCardStack;

public class BoardPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private Board board;
	
	private DescendingCardStack heldCards;
	
	public BoardPanel() {
		this.board = new Board();
		this.heldCards = new DescendingCardStack();
		
		for(int i = 0; i <= 7; i++)
			board.getCardStacks().get(i).setPosition(Card.CARD_WDITH * i, 0);
		
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
		private CardStack originalStack;
		private int offsetX, offsetY;
		
		@Override
		public void mousePressed(MouseEvent e) {
			for(CardStack s : board.getCardStacks()) {
				int startIndex = s.getIndexFromMousePosition(e.getX(), e.getY());
				if(startIndex == -1 || !s.canRemoveFromIndex(startIndex))
					continue;
				
				originalStack = s;
				
				ArrayList<Card> temp = new ArrayList<>();
				for(int i = startIndex; i < s.getCards().size(); i++)
					temp.add(s.getCards().get(i));

				heldCards.setPosition((int) s.getCards().get(startIndex).getHitBox().x, (int) s.getCards().get(startIndex).getHitBox().y);
				heldCards.addCards(temp);
				
				offsetX = (int) (e.getX() - heldCards.getCards().get(0).getHitBox().x);
				offsetY = (int) (e.getY() - heldCards.getCards().get(0).getHitBox().y);
				
				s.removeFromIndex(startIndex);
				
				break;
			}
		}
		
		@Override
		public void mouseDragged(MouseEvent e) {
			heldCards.setPosition(e.getX() - offsetX, e.getY() - offsetY);
			
			repaint();
		}
		
		@Override
		public void mouseReleased(MouseEvent e) {
			for(CardStack stack : board.getCardStacks()) {
				if(!stack.getHitBox().intersects(heldCards.getHitBox()))
					continue;
				
				if(stack.canAddCardStack(heldCards)) {
					stack.addCardStack(heldCards);
					repaint();
					return;
				}
			}

			if(heldCards.getCards().size() > 0) {
				originalStack.addCardStack(heldCards);
				repaint();
			}
		}
	}
}
