package Board;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import Board.Cards.Card;
import Board.Cards.CardStack;
import Board.Cards.DescendingCardStack;

public class Board {
	private ArrayList<CardStack> cardStacks;
	
	public Board() {
		this.cardStacks = new ArrayList<>();
		
		cardStacks.add(new DescendingCardStack());
		cardStacks.add(new DescendingCardStack());
		
		cardStacks.get(0).addCard(new Card(Color.red));
		cardStacks.get(0).addCard(new Card(Color.blue));
	}
	
	public void render(Graphics2D g2d) {
		for(CardStack s : cardStacks)
			s.render(g2d);
	}

	public ArrayList<CardStack> getCardStacks() { return cardStacks; }
}