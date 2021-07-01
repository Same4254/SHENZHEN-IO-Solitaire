package Board.Cards;

import java.awt.Graphics2D;
import java.util.ArrayList;

public abstract class SingleCardVisibleStack extends CardStack {
	public SingleCardVisibleStack() {
		
	}
	
	@Override
	public void render(Graphics2D g2d) {
		if(cards.size() > 0)
			cards.get(cards.size() - 1).render(g2d);
	}

	@Override
	public int getIndexFromMousePosition(int mouseX, int mouseY) {
		return hitBox.contains(mouseX, mouseY) ? cards.size() - 1 : -1;
	}

	public void addCards(ArrayList<Card> otherCards) {
		super.addCards(otherCards);
		
		for(Card c : otherCards)
			c.setPosition((int) hitBox.x, (int) hitBox.y);
	}
}
