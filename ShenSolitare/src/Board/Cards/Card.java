package Board.Cards;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Card {
	public static final int CARD_WDITH = 40, CARD_HEIGHT = 100;

	private CardStack cardStack;
	
	private Rectangle2D.Double hitBox;
//	private BufferedImage texture;
	
	Color c;
	public Card(Color c) {
		this.c = c;
		hitBox = new Rectangle2D.Double(0, 0, CARD_WDITH, CARD_HEIGHT);
	}
	
	public void render(Graphics2D g2d) {
		
//		g2d.drawImage(texture, (int)hitBox.x, (int)hitBox.y, CARD_WDITH, CARD_HEIGHT, null);
		
		g2d.setColor(c);
		g2d.fill(hitBox);
	}

	public CardStack getCardStack() { return cardStack; }
	public void setCardStack(CardStack cardStack) { this.cardStack = cardStack; }
	
	public Rectangle2D.Double getHitBox() { return hitBox; }
	public void setPosition(int x, int y) { hitBox.setRect(x, y, hitBox.width, hitBox.height); }
}
