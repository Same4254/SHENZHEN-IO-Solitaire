package Board.Cards;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public abstract class CardStack {
	protected Rectangle2D.Double hitBox;
	protected ArrayList<Card> cards;
	
	public CardStack() {
		this.hitBox = new Rectangle2D.Double(0, 0, Card.CARD_WDITH, Card.CARD_HEIGHT);
		this.cards = new ArrayList<>();
	}

	public abstract void render(Graphics2D g2d);
	
	public abstract int getIndexFromMousePosition(int mouseX, int mouseY);
	public void setPosition(int x, int y) { hitBox.setRect(x, y, hitBox.width, hitBox.height); }
	
	public abstract boolean canAddCards(CardStack stack);
	public abstract void addCardStack(CardStack stack);
	
	public abstract boolean canRemoveFromIndex(int index);
	public void removeFromIndex(int index) {
		for(int i = cards.size(); i >= index; i--)
			cards.remove(i);
	}
	
	public Rectangle2D.Double getHitBox() { return hitBox; }
	public ArrayList<Card> getCards() { return cards; }
}
