package Board.Cards;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class DescendingCardStack extends CardStack {
	public static final int CARD_GAP = 40;
	
	public DescendingCardStack() {
		super();
	}
	
	@Override
	public void render(Graphics2D g2d) {
		for(Card c : cards)
			c.render(g2d);
		
		g2d.setColor(Color.black);
		g2d.draw(hitBox);
	}
	
	@Override
	public int getIndexFromMousePosition(int mouseX, int mouseY) {
		if(!hitBox.contains(mouseX, mouseY))
			return -1;
		
		if(mouseY >= CARD_GAP * cards.size())
			return cards.size() - 1;
		
		return mouseY / CARD_GAP;
	}
	
	@Override
	public void setPosition(int x, int y) {
		super.setPosition(x, y);
		
		for(int i = 0; i < cards.size(); i++)
			cards.get(i).setPosition((int) hitBox.x, (int)(hitBox.y + (CARD_GAP * i)));
	}
	
	@Override
	public boolean canAddCardStack(CardStack stack) {
		if(!(stack instanceof DescendingCardStack) || stack.getCards().size() == 0)
			return false;
		
		if(cards.size() == 0)
			return true;
		
		DescendingCardStack dStack = (DescendingCardStack) stack;
		return cards.get(cards.size() - 1).isNextCard(dStack.cards.get(0));
	}

	@Override
	public void addCards(ArrayList<Card> otherCards) {
		for(int i = 0; i < otherCards.size(); i++) {
			Card c = otherCards.get(i);
			cards.add(c);
			c.setCardStack(this);
			
			c.setPosition((int) hitBox.x, (int)(hitBox.y + (CARD_GAP * (cards.size() - 1))));
			
			if(cards.size() > 1)
				hitBox.height += CARD_GAP;
		}
		
		otherCards.clear();
	}

	@Override
	public boolean canRemoveFromIndex(int index) {
		return isDescendingAndAlternating(index);
	}
	
	@Override
	public void removeFromIndex(int index) {
		super.removeFromIndex(index);
		
		hitBox.height = Card.CARD_HEIGHT + Math.max(0, (CARD_GAP * (cards.size() - 1)));
	}
	
	private boolean isDescendingAndAlternating(int fromIndex) {
		if(fromIndex == cards.size() - 1)
			return true;
		
		Card lastCard = cards.get(fromIndex);
		
		if(lastCard.getNumber() == -1)
			return false;
		
		for(int i = fromIndex + 1; i < cards.size(); i++) {
			Card currentCard = cards.get(i);
			
			if(!lastCard.isNextCard(currentCard))
				return false;
			
			lastCard = currentCard;
		}
		
		return true;
	}
}
