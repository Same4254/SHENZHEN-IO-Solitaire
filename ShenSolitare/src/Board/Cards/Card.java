package Board.Cards;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Card {
	public static final int CARD_WDITH = 122, CARD_HEIGHT = 237;
	
	public static enum SUIT {
		BLACK,
		RED,
		GREEN,
		
		BLACK_JUNK,
		RED_JUNK,
		GREEN_JUNK,
		
		FLOWER,
		FLIPPED
	}
	
	private CardStack cardStack;
	
	private Rectangle2D.Double hitBox;
	private BufferedImage texture;
	
	private SUIT suit;
	private int number;
	
	public Card(BufferedImage texture, SUIT suit, int number) {
		this.texture = texture;
		this.suit = suit;
		this.number = number;
		
		hitBox = new Rectangle2D.Double(0, 0, CARD_WDITH, CARD_HEIGHT);
	}
	
	public boolean isNextCard(Card otherCard) {
		if(number == -1 || otherCard.number == -1)
			return false;
		if(otherCard.number == number - 1 && suit != otherCard.suit)
			return true;
		
		return false;
	}
	
	public void render(Graphics2D g2d) {
		g2d.drawImage(texture, (int)hitBox.x, (int)hitBox.y, CARD_WDITH, CARD_HEIGHT, null);
	}

	public CardStack getCardStack() { return cardStack; }
	public void setCardStack(CardStack cardStack) { this.cardStack = cardStack; }
	
	public Rectangle2D.Double getHitBox() { return hitBox; }
	public void setPosition(int x, int y) { hitBox.setRect(x, y, hitBox.width, hitBox.height); }

	public SUIT getSuit() { return suit; }
	public int getNumber() { return number; }
}
