package Board;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.imageio.ImageIO;

import Board.Cards.Card;
import Board.Cards.Card.SUIT;
import Board.Cards.CardStack;
import Board.Cards.DescendingCardStack;

public class Board {
	private ArrayList<CardStack> cardStacks;
	
	public Board() {
		this.cardStacks = new ArrayList<>();

		for(int i = 0; i <= 7; i++)
			cardStacks.add(new DescendingCardStack());
		
		ArrayList<Card> allCards = new ArrayList<Card>();
		
		String[] mainCardNames = { "bamboo", "char", "coins" };
		SUIT[] mainSuits = { SUIT.GREEN, SUIT.BLACK, SUIT.RED };
		
		String[] dragonNames = { "dragon_green", "dragon_red", "dragon_white" };
		SUIT[] dragonSuits = { SUIT.GREEN_JUNK, SUIT.RED_JUNK, SUIT.BLACK_JUNK };
		
		for(int i = 0; i < mainCardNames.length; i++) {
			String name = mainCardNames[i];
			SUIT suit = mainSuits[i];
			
			for(int num = 1; num <= 9; num++) {
				BufferedImage image = null;
				
				try {
					image = ImageIO.read(new File("textures/" + name + "_" + num + ".png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				allCards.add(new Card(image, suit, num));
			}
		}
		
		for(int i = 0; i < dragonNames.length; i++) {
			String name = dragonNames[i];
			SUIT suit = dragonSuits[i];
			
			BufferedImage image = null;
			try {
				image = ImageIO.read(new File("textures/" + name + ".png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			for(int j = 0; j <= 3; j++)
				allCards.add(new Card(image, suit, -1));
		}
		
		BufferedImage flowerImage = null;
		try {
			flowerImage = ImageIO.read(new File("textures/flower.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		allCards.add(new Card(flowerImage, SUIT.FLOWER, -1));
		
		Collections.shuffle(allCards);
		
		for(int i = 0; i <= 7; i++) {
			ArrayList<Card> temp = new ArrayList<>();
			for(int j = 0; j <= 4; j++) {
				temp.add(allCards.get(allCards.size() - 1));
				allCards.remove(allCards.size() - 1);
			}
			
			cardStacks.get(i).addCards(temp);
		}
	}
	
	public void render(Graphics2D g2d) {
		for(CardStack s : cardStacks)
			s.render(g2d);
	}

	public ArrayList<CardStack> getCardStacks() { return cardStacks; }
}