/**
 * Created by Dewey Milton  on 3/2/2016.
 */
import java.util.ArrayList;
import java.util.Stack;

public class  DiscardPile {


    private Stack<Card> discardPile;
    private Card playable;
    /**
     * Creates an empty discard Pile of Cards
     */
    public DiscardPile(){
        this.discardPile = new Stack<Card>();
        this.playable = null;
    }

    /**
     * Puts a card into the discardPile
     * @param _card placed into discardPile
     */
    public void discard(Card _card){
        this.playable = _card;
        this.discardPile.push(_card);
    }

    /**
     * Discards remaining hand
     * param cards arraylist of cards
     */
    public void discardHand(ArrayList<Card> remHand)
    {
        while(remHand.size() != 0 ) {
            this.discardPile.push(remHand.remove(0));
        }
    }

    /**
     * shows top card in the discard pile
     * @return the top card of the discard pile
     * @throws EmptyCollectionException if nothing in discard pile
     */
    public Card show() throws EmptyCollectionException{
        if(isEmpty())
            throw new EmptyCollectionException("discardPile is empty");

        return this.discardPile.peek();
    }

    /**
     * return playable card
     * @return playable
     */
    public Card showPlayable()
    {
        Card tmpCard = null;
        if(this.playable != null){
            tmpCard = this.playable;
        }

        return tmpCard;
    }

    /**
     * Take a card from the discardPile
     * @return Card from the discardPile
     * @throws EmptyCollectionException if discard pile is empty
     */
    public Card retrieve() throws EmptyCollectionException {
        Card tmpCard = null;
        if(isEmpty())
            throw new EmptyCollectionException("discardPile is empty");
        else {
            tmpCard = this.discardPile.pop();

            if(this.discardPile.size() != 0){
                this.playable = this.discardPile.peek();
            }
        }

        return tmpCard;
    }

    /**
     * Checks if the discardPile is empty
     * @return isEmpty if empty is true or false
     */
    private boolean isEmpty(){
        boolean isEmpty = false;

        if(this.discardPile.size() == 0){
            isEmpty = true;
        }
        return isEmpty;
    }

    /**
     * Gives back how big the discard pile is
     * @return int of discard pile size
     */
    public int size()
    {
        return this.discardPile.size();
    }

    /**
     * A string of all that is in the discard pile
     * @return String of what is in the discard pile
     * @throws EmptyCollectionException if discard pile is empty
     */
    public String toString() throws EmptyCollectionException{
        if(isEmpty())
            throw new EmptyCollectionException("DiscardPile is Empty");

        String result = "";

        for(int i = 0; i < discardPile.size(); i++){
            result += discardPile.get(i).toString() + "\n";
        }

        return result;
    }
}

