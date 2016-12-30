/**
 * Created by Dewey Milton on 2/17/2016.
 */
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;
public class Deck {

    private Card[] deck;
    private int position;
    private ImageIcon deckBack;

    /**
     * Constructor for a deck.
     */
    public Deck()
    {
        this.deck = new Card[52];
        this.deck = deckSetup();
        this.position = 0;
        this.deckBack = new ImageIcon("CardBack2.jpg");
    }

    public ImageIcon getImage()
    {
        if(deck.length == 0){
            return null;
        }
        else
        return this.deckBack;
    }

    /*
       Creates a deck from the number of players a deck per 2 players
       @param int Numplayers
     */
    public Deck(int numPlayers)
    {
        int numDecksNeeded = 0;
        switch(numPlayers){
            case 2:
                numDecksNeeded = 1;
                break;
            case 3:
                numDecksNeeded = 2;
                break;
            case 4:
                numDecksNeeded = 2;
                break;
            case 5:
                numDecksNeeded = 3;
                break;
            case 6:
                numDecksNeeded = 3;
                break;
        }

        this.deck = new Card[52 * numDecksNeeded];
        this.deck = liverPoolSetup(numDecksNeeded);
        this.position = 0;
    }

    /**
     * Creates an initial playing card Deck without Jokers
     * @return tmpDeck a Built deck of Playing cards
     */
    private Card[] deckSetup()
    {
        Card[] tmpDeck = new Card[this.deck.length];
        int suitCounter = 0, index = 0;
        while(suitCounter < 4) {
            for (int faceValue = 2; faceValue <= 14; faceValue++) {
                Card newCard = new Card(faceValue, suitCounter);
               tmpDeck[index] = newCard ;
                index++;
            }
            suitCounter++;
        }
        return tmpDeck;
    }

    /*
       deck setup for liverpool game
       @param num of Decks needed
       @return Card[] of new Deck
     */
    private Card[] liverPoolSetup(int numOfDecks)
    {
        Card[] tmpDeck = new Card[this.deck.length];
        int index = 0;

        for(int i = 0; i < numOfDecks; i++) {
            int suitCounter = 0;
            while (suitCounter < 4) {
                for (int faceValue = 2; faceValue <= 14; faceValue++) {
                    Card newCard = new Card(faceValue, suitCounter);
                    tmpDeck[index] = newCard;
                    index++;
                }
                suitCounter++;
            }
        }
        return tmpDeck;
    }



    /**
     * Shuffles a deck array
     */
    public void shuffle()
    {

        for(int i = 0; i < deck.length * 6; i++)
        {
            Random rand = new Random();
            int index = rand.nextInt(deck.length);
            int newPos = rand.nextInt(deck.length);

            if(index >= 0 && index < deck.length && newPos >= 0 && newPos < deck.length) {
                Card tmpCard = this.deck[index];
                this.deck[index] = this.deck[newPos];
                this.deck[newPos] = tmpCard;
            }
        }
    }

    /**
     * reshuffle discard pile back into the deck
     * @param discardPile of cards to be shuffled back into the deck
     */
    public void reShuffle(DiscardPile discardPile)
    {
        while(discardPile.size() > 0)
        {
            this.deck[position] = discardPile.retrieve();
            position--;
        }

    }

    /*
       reset the deck
       @param takes in the discard pile to flip
     */
    public void resetDeck(ArrayList<Card> cards){
        if(cards.size() == 0){
            throw new EmptyCollectionException("reset Deck");
        }
        else{
            while(cards.size() != 0){
                System.out.println("Cards unloading " + cards.toString());

                System.out.println("In reset Deck, Cards size " + cards.size() + " Deck Size " + this.deck.length + " Position " + position);

                this.deck[position] = cards.remove(0);

                if(position > 0) {
                    position--;
                }
            }
        }

    }

    /**
     * This deals a single card
     * @return Card One card out of the deck, sets deck position to null.
     */
    public Card dealCard(){
        boolean dealt = false;
        Random rand = new Random();
        Card card = null;

        if(cardsInDeck() != 0 && this.position < deck.length) {
            while (!dealt) {
                if (this.deck[position] != null) {
                    card = this.deck[position];
                    this.deck[position] = null;
                    dealt = true;
                }
                position++;
            }
        }


        return card;
    }

    /*
      keeps position of the top card
      @return Card off the top
     */
    public Card topCard(){
        Card card = null;
        boolean foundTop = false;


            while(this.deck[position] != null && !foundTop)
            {
                if(this.deck[position] != null){
                    card = this.deck[position];
                    foundTop = true;
                }
            }


        return card;
    }

    /*
      @param int numplayers
      @param int HandSize needed
      @retrun Card[][] 2d array of players hands
     */
    public Card[][] dealHand(int numPlayers, int handSize)
    {
        Card[][] hands = new Card[numPlayers][handSize];

        for(int cards = 0; cards < handSize; cards++) {

            for(int player = 0; player < numPlayers; player++) {

                while (hands[player][cards] == null) {
                    hands[player][cards] = dealCard();
                }

            }

        }
        return hands;
    }



    /**
     * to String method prints out all the cards in a deck
     * @return String of all the cards in deck
     */
    public String toString()
    {
        String result = "";

        for(int index = 0; index < this.deck.length; index++)
        {
            if(this.deck[index] != null) {
                result += this.deck[index].toString() + "\n";
            }
            else
                result += "NULL\n";
        }

        return result;
    }

    /**
     * Shows how many cards left in a deck
     * @return int of how many cards
     */
    public int cardsInDeck()
    {
        int found = 0;
        for(int i = 0; i < this.deck.length; i++){
            if(this.deck[i] != null){
                found++;
            }
        }
        return found;
    }
}