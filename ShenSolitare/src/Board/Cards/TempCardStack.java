package Board.Cards;

import Board.Cards.Card.SUIT;

public class TempCardStack extends SingleCardVisibleStack {

	@Override
	public boolean canAddCardStack(CardStack stack) {
		return cards.size() == 0 && stack.getCards().size() == 1;
	}

	@Override
	public boolean canRemoveFromIndex(int index) {
		if(index != 0 || cards.size() == 0 || cards.get(0).getSuit() == SUIT.FLIPPED)
			return false;
		return true;
	}
}
