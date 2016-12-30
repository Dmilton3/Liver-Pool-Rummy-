/**
 * Created by ASUS on 3/1/2016.
 */
import java.util.ArrayList;
public class DeckOfCardTest {



    public static void main(String[] args)
    {
        Deck deck = new Deck(4);
        View view = new View();

        DiscardPile pile = new DiscardPile();

        deck.shuffle();

     //  System.out.println(deck.toString());

        //deck.shuffle();

        System.out.println(deck.toString());

        System.out.println("The top card is " + deck.topCard().toString());



       // System.out.println("Your card is " + card.toString() + " with " + deck.cardsInDeck() + "cards left" + "\n");
       // System.out.println(deck.toString());





        Player player1 = new Player(1);
        Player player2 = new Player(2);
        Player player3 = new Player(3);
        Player player4 = new Player(4);

        player3.setDealer(true);
        int dealer = 3;

        Card[][] hands = deck.dealHand(4, 11);


        player1.setHand(hands[0]);
        player1.viewHand();//why?
        player2.setHand(hands[1]);
        player2.viewHand();
        player3.setHand(hands[2]);
        player3.viewHand();
        player4.setHand(hands[3]);
        player4.viewHand();
        System.out.println("Player 1 hand " + player1.viewHand());
        System.out.println("Player 2 hand " + player2.viewHand());
        System.out.println("Player 3 hand " + player3.viewHand());
        System.out.println("Player 4 hand " + player4.viewHand());

        System.out.println(deck.toString());

        pile.discardHand(player1.dumpHand());



        System.out.println(pile.toString() + " \n");

        deck.reShuffle(pile);

        System.out.println(deck.toString());

        System.out.println("Player 1 hand " + player1.viewHand());
        System.out.println("Player 2 hand " + player2.viewHand());
        System.out.println("Player 3 hand " + player3.viewHand());
        System.out.println("Player 4 hand " + player4.viewHand());

        Card c1 = new Card(8, 1);
        Card c2 = new Card(7, 1);
        Card c3 = new Card(6, 1);
        Card c4 = new Card(5, 1);
        Card c5 = new Card(2, 3);
        Card c6 = new Card(4, 3);
        Card c7 = new Card(3, 3);
        Card c8 = new Card(5, 3);
        Card c9 = new Card(6, 3);
        Card c10 = new Card(3, 2);
        Card c11 = new Card(4, 2);
        Card c12 = new Card(4, 2);
        Card c13 = new Card(9,1);
        Card c14 = new Card(4,3);

        ArrayList<Card> testPlay = new ArrayList();
        testPlay.add(c1);
        testPlay.add(c2);
        testPlay.add(c3);
        testPlay.add(c4);
        testPlay.add(c5);
        testPlay.add(c6);
        testPlay.add(c7);
        testPlay.add(c8);
        testPlay.add(c9);
        testPlay.add(c10);
        testPlay.add(c11);
        testPlay.add(c12);
        testPlay.add(c13);
        testPlay.add(c14);


        Card[] hand = new Card[testPlay.size()];

        int pos = 0;
        while(testPlay.size() != 0){
            hand[pos] = testPlay.remove(0);
            pos++;
        }
        Player player = new Player(1);
        player.setHand(hand);

        Plays[] field = new Plays[2];

        field = view.setCards(2, player);

        System.out.println("Field length " + field.length);

        System.out.println("Field one " + field[0].toString() + "\n" + "Field 2 " + field[1].toString());
        ArrayList<ArrayList<Plays>> newField = new ArrayList<ArrayList<Plays>>(1);
        for(int i = 0; i < 1; i++){
            newField.add(new ArrayList<Plays>());
        }
        newField.get(0).add(0, field[0]);
        newField.get(0).get(0).setFieldPosition(0,0);
        newField.get(0).add(0, field[1]);
        newField.get(0).get(0).setFieldPosition(0,1);
        newField = view.playCard(newField, player);




        /**   int numPlayers = 4, handSize = 11;
           String result = "";
           for(int players = 0; players < numPlayers; players++){
               for(int hand = 0; hand < handSize; hand++){
                   result += "Player " + players + " has a " + hands[players][hand] + "\n";
               }
           }

           System.out.println(result);

          System.out.println(deck.toString());
        */


    }




}
