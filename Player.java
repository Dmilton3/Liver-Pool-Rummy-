import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by Dewey Milton on 3/16/2016.
 */
public class Player {

    private Boolean cpu;
    private Boolean dealer;
    private Hand hand;
    private int score;
    private int playerNum;
    private boolean isDown;


    /**
     * Constructs a new player
     */
    public Player(int playerNumber)
    {
      this.playerNum = playerNumber;
      this.score = 0;
      this.hand = new Hand();
      this.dealer = false;
      this.cpu = false;
      this.isDown = false;
    }

    /*
      @return int Player number
     */
    public int getPlayerNum(){
        return this.playerNum;
    }

    /*
       @return Card View Card
       @param int index of card
     */
    public Card viewCard(int index){
        return this.hand.viewCard(index);
    }

    /*
       @return String of List Card Positions
     */
    public String listCardPos(){

        return hand.getCardPos();
    }

    /*
       @param int Selct Card at index
     */
    public void selectCard(int selection){
        this.hand.selectCard(selection);
    }

    /**
     * Sets if player is Cpu
     * @param isCpu True or false
     */
    public void setCpu(Boolean isCpu)
    {
        this.cpu = isCpu;
    }

    /*
      @return Jlist card images
     */
    public JList<ImageIcon> getImages(){
        return this.hand.getImages();
    }

    /*
      @param Defaultlistmodel Image icons of new hand
     */
    public void updateModel(DefaultListModel<ImageIcon> _model){
        this.hand.updateModel(_model);
    }

    /*
       @return int handSize
     */
    public int handSize(){
        return this.hand.handSize();
    }
    /**
     * Returns if player is cpu
     * @return True or false is cpu
     */
    public Boolean getCpu()
    {
        return this.cpu;
    }

    /**
     * sets player as dealer if true
     * @param isDealer True or false
     */
    public void setDealer(boolean isDealer)
    {
        this.dealer = isDealer;
    }

    /*
       @param boolean whether player is down
     */
    public void setIsDown(boolean _onField){
        this.isDown = _onField;
    }

    /*
    @return boolean if player is on the field
     */
    public boolean isDown(){
        return this.isDown;
    }

    /**
     * returns true if player is the dealer
     * @return True or False
     */
    public Boolean isDealer()
    {
        return this.dealer;
    }

    /**
     * Creates a players hand
     * @param _cards A hand of cards
     */
    public void setHand(Card[] _cards)
    {
        this.hand.setHand(_cards);
    }

    /**
     * Takes one card
     * @param card placed into hand
     */
    public void drawCard(Card card)
    {
        this.hand.takeCard(card);
    }

    /*
      @return Card discard card
     */
    public Card discardCard(){

        return hand.discardCard();
    }

    /*
       @param int index of card Pos
       @return Card in Pos
     */
    public Card playCard(int index){
        return this.hand.getCard(index);
    }
    /**
     * access players hand
     * @return players current hand
     */
    public ArrayList<Card> getHand()
    {
        ArrayList<Card> tmpHand = this.hand.getHand();

        return tmpHand;
    }

    /**
     * Shows what is in the players hand
     * @return String of cards in hand
     */
    public String viewHand()
    {
        return this.hand.toString();
    }

    /**
     * Releases remaining cards in hand
     * @return Arraylist of cards
     */
    public ArrayList<Card> dumpHand()
    {
        ArrayList<Card> cards = new ArrayList<Card>(this.hand.getSize());
        Card[] remainingCards = this.hand.dumpCards();

        if(remainingCards.length > 0)
        {
            for(int i = 0; i < remainingCards.length; i++)
            {
                cards.add(remainingCards[i]);
            }
        }


        return cards;
    }

    /**
     * Gets point for finished round
     * @param addedScore points to add to the player score
     */
    public void setScore(int addedScore)
    {
        this.score += addedScore;
    }

    /**
     * returns players score
     * @return int score
     */
    public int getScore()
    {
        return this.score;
    }

    /*
       @param int Pos 1 and Pos 2 in order to swap cards in hand
     */
    public void swap(int pos1, int pos2){
        this.hand.swapCard(pos1, pos2);
    }

    /*
       @return int Card value
       @param int Card position
     */
    public int getCardValue(int index){
        return this.hand.getCardValue(index);
    }


    /*
     @param int Card Pos
      @return String card suite
     */
    public String getFace(int index){
        return this.hand.getFace(index);
    }
}

