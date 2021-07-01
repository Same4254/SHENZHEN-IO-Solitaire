package Board.Cards;

import Board.Cards.Card.SUIT;

public class SuitCardStack extends SingleCardVisibleStack {
	private SUIT suit;
	
	public SuitCardStack(SUIT suit) {
		super();
		
		this.suit = suit;
	}
	
	@Override
	public boolean canAddCardStack(CardStack stack) {
		if(stack.getCards().size() != 1)
			return false;
		
		Card c = stack.getCards().get(0);
		
		if(c.getSuit() != suit)
			return false;
		
		if(cards.size() == 0 && c.getNumber() == 1)
			return true;
		
		if(cards.size() > 0 && c.getNumber() == cards.get(cards.size() - 1).getNumber() + 1)
			return true;
		
		return false;
	}

	@Override
	public boolean canRemoveFromIndex(int index) {
		return index != -1 && index == cards.size() - 1;
	}
}
