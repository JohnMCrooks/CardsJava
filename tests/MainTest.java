import com.crooks.Card;
import com.crooks.Main;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;

/**
 * Created by johncrooks on 7/12/16.
 */
public class MainTest {
   public HashSet<Card> tempHash = new HashSet<>();

    @Test
    public  void aFlushTest() {

        Card card = new Card(Card.Suit.SPADES, Card.Rank.EIGHT);
        Card card1 = new Card(Card.Suit.SPADES, Card.Rank.NINE);
        Card card2 = new Card(Card.Suit.SPADES, Card.Rank.QUEEN);
        Card card3 = new Card(Card.Suit.SPADES, Card.Rank.SEVEN);

        tempHash.add(card);
        tempHash.add(card1);
        tempHash.add(card2);
        tempHash.add(card3);

        boolean testIsTrue = Main.isFlush(tempHash);
        Assert.assertTrue(testIsTrue);

        tempHash.remove(card1);
        tempHash.add(new Card(Card.Suit.CLUBS, Card.Rank.EIGHT));
        boolean testIsfalse = Main.isFlush(tempHash);
        Assert.assertFalse(testIsfalse);
    }

    @Test
    public void bFourOfAKindTest(){
        Card card = new Card(Card.Suit.SPADES, Card.Rank.EIGHT);
        Card card1 = new Card(Card.Suit.HEARTS, Card.Rank.EIGHT);
        Card card2 = new Card(Card.Suit.DIAMONDS, Card.Rank.EIGHT);
        Card card3 = new Card(Card.Suit.CLUBS, Card.Rank.EIGHT);

        tempHash.add(card);
        tempHash.add(card1);
        tempHash.add(card2);
        tempHash.add(card3);

        boolean testIsTrue = Main.isSameRank(tempHash);
        Assert.assertTrue(testIsTrue);

        tempHash.remove(card1);
        tempHash.add(new Card(Card.Suit.CLUBS, Card.Rank.NINE));
        testIsTrue = Main.isSameRank(tempHash);
        Assert.assertFalse(testIsTrue);
    }

    @Test
    public void cthreeofKind (){
        Card card = new Card(Card.Suit.SPADES, Card.Rank.SEVEN);
        Card card1 = new Card(Card.Suit.HEARTS, Card.Rank.EIGHT);
        Card card2 = new Card(Card.Suit.DIAMONDS, Card.Rank.EIGHT);
        Card card3 = new Card(Card.Suit.CLUBS, Card.Rank.EIGHT);

        tempHash.add(card);
        tempHash.add(card1);
        tempHash.add(card2);
        tempHash.add(card3);

        boolean testIsTrue = Main.isThreeofKind(tempHash);
        Assert.assertTrue(testIsTrue);

        tempHash.remove(card1);
        tempHash.add(new Card(Card.Suit.CLUBS, Card.Rank.NINE));
        testIsTrue = Main.isSameRank(tempHash);
        Assert.assertFalse(testIsTrue);


    }
}

