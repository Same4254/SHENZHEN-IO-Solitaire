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
		
		ArrayList<Card> temp = new ArrayList<Card>();
		temp.add(new Card(Color.red));
		temp.add(new Card(Color.blue));
		
		cardStacks.get(0).addCards(temp);
	}
	
	public void render(Graphics2D g2d) {
		for(CardStack s : cardStacks)
			s.render(g2d);
	}

	public ArrayList<CardStack> getCardStacks() { return cardStacks; }
}