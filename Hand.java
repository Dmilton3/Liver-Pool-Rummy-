/**
 * Created by Dewey Milton on 3/2/2016.
 */

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
/*
  Players hand class
 */
public class Hand {

    private ArrayList<Card> hand;
    private JList<ImageIcon> cards;
    DefaultListModel<ImageIcon> model;
    private int cardCounter;

    /*
     Constructs a players hand
     */
    public Hand()
    {
        model = new DefaultListModel<>();
        cards = new JList<>(model);
        cards.setLayoutOrientation(cards.HORIZONTAL_WRAP);
        cards.setBackground(Color.green);
        cards.setSelectionBackground(Color.blue);
        cards.setVisibleRowCount(-1);
        this.hand = new ArrayList<Card>(11);
    }


    /*
      Updates the Jlist model of a players hand
     */
    public void updateModel(DefaultListModel<ImageIcon> _model){
        cards = new JList<>(_model);
        cards.setLayoutOrientation(cards.HORIZONTAL_WRAP);
        cards.setBackground(Color.green);
        cards.setSelectionBackground(Color.BLUE);
        cards.setVisibleRowCount(-1);
    }

    /*
       retrieves JList
     */
    public JList<ImageIcon> getImages(){
        return cards;
    }

    /*
     Sets a players hand from deal
     */
    public void setHand(Card[] _hand)
    {
        for(int i = 0; i < _hand.length; i++){
            this.model.addElement(_hand[i].getImage());
            this.hand.add(_hand[i]);
            this.hand.get(i).setHandPosition(i);
        }

    }

    /*
      sets hand
      */
    public void setHand(ArrayList<Card> newHand){
        this.hand = newHand;
    }

    /*
      gets the position of a card in the hand
     */
    public String getCardPos(){
         String position = "";

        for(int i = 0; i < hand.size(); i++){
            position +=  hand.get(i).handLocation() + ": " + hand.get(i).toString() + "\n";
        }
        return position;
    }
    /**
     * access players hand
     * @return players current hand
     */
    public ArrayList<Card> getHand()
    {
        return this.hand;
    }

    /**
     * updates players hand
     * @param newHand of cards
     */
    public void updateHand(ArrayList<Card> newHand)
    {
        this.hand = newHand;
    }


    /*
      Selects a card
     */
    public void selectCard(int position)
    {
        this.hand.get(position).setSelection();
    }

    /*
      @return int  hand size
     */
    public int getSize()
    {
        return this.hand.size();
    }

    /*
     Method to swap cards by text
     */
    public void swapCard(int pos1, int pos2){

        this.hand.get(pos1).setHandPosition(pos2);
        this.hand.get(pos2).setHandPosition(pos1);
        Collections.swap(this.hand, pos1, pos2);

    }

    /*
       @return int  card int value
     */
    public int getCardValue(int index){
        return this.hand.get(index).getFaceValue();
    }

    /*
      @return String suite value
     */
    public String getFace(int index){
        return this.hand.get(index).getSuit();
    }

    /*
      @return Card get a card
     */
    public Card getCard(int index){
        Card tmpCard = this.hand.remove(index);
        while(index < this.hand.size()){
            this.hand.get(index).setHandPosition(index);
            index++;
        }
        return tmpCard;
    }

    /*
      @return Card preview of card
     */
    public Card viewCard(int index){
        return this.hand.get(index);
    }
    public Card discardCard()
    {
        Card card = null;

        if(hand.size() > 0) {
            for(int i = 0; i < hand.size(); i++){
                if(this.hand.get(i).getSelection()){
                    System.out.println("Card being Removed " + this.hand.get(i).toString());
                    this.hand.get(i).setSelection();
                    System.out.println("Now in position " + i + this.hand.get(i).toString());
                    card = this.hand.remove(i);
                    while(i < hand.size()){
                        this.hand.get(i).setHandPosition(i);
                        i++;
                    }
                }
            }
        }
        else
        System.out.println("Invalid Throw exception");

        return card;
    }

    /*
      @return int Hand Size
     */
    public int handSize(){
        return this.hand.size();
    }

    /**
     * empties hand
     * @return array of cards that were in hand
     */
    public Card[] dumpCards()
    {
        Card[] cards = new Card[this.hand.size()];

        int pos = 0;
        while(this.hand.size() != 0)
        {
            cards[pos] = this.hand.remove(0);
            pos++;
        }
        return cards;
    }

    /*
       @param Card places card in the hand
     */
    public void takeCard(Card card)
    {
        Card tmpCard = card;
        card.setNewCard(true);
        this.hand.add(card);

        for(int i = 0; i < hand.size(); i++){
            if(hand.get(i).isNewCard()){
                hand.get(i).setHandPosition(i);
                hand.get(i).setNewCard(false);
                i++;
                while(i < hand.size()){
                    if(hand.get(i) != null){
                        hand.get(i).setHandPosition(i);
                    }
                }
            }
        }
    }


    /*
       @return String to String
     */
    public String toString(){
        String result = "";

        if(this.hand.size() != 0){
            for(int i = 0; i < this.hand.size(); i++){
                result += this.hand.get(i).handLocation() + ". " + this.hand.get(i).toString() + "\n";
            }
        }
        else
        result = "No cards in hand";

        return result;
    }
}
