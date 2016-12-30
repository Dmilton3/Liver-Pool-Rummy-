import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * Created by Dewey Milton on 3/16/2016.
 */
public class Plays implements Iterable<Card> {

    private ArrayList<Card> play;
    private boolean isSet;
    private boolean isRun;
    private int setValue;
    private String runSuit;
    private int fieldIndex;
    private int playerIndex;

    /*
       Constructs a play of cards for the field
     */
    public Plays(ArrayList<Card> newPlay)
    {
        this.play = newPlay;
        this.isSet = false;
        this.setValue = 0;
        this.isRun = false;
        this.runSuit = "";
        this.fieldIndex = -1;
        this.playerIndex = -1;
    }


    /*
       @return Iterator<Card>
     */
    public Iterator<Card> iterator()
    {
        return play.iterator();
    }


    /*
      Allows to see where the play is on the field;
     */
    public String getFieldPosition(){
        return "Play index " + this.fieldIndex + " on Player " + this.playerIndex + " field";
    }

    /*
     Sets the position on the field
     @param int player  player that laid the play
     @param int fieldIndex  Where in the array the play is located
     */
    public void setFieldPosition(int player, int position){
        this.playerIndex = player;
        this.fieldIndex = position;
    }

    /*
       @param Card adds to the play
     */
    public void addCard(Card card){
        this.play.add(card);
    }

    /*
      @param boolean if play is a set
     */
    public void isSet(Boolean _isSet){
        this.isSet = _isSet;
    }

    /*
       @param boolean if is run
     */
    public void isRun(Boolean _isRun){
        this.isRun = _isRun;
    }

    /*
      sorts plays in numeric order
     */
    public void runSorter() {
        int i = 0;
        boolean sorted = false;

        while (i < play.size() && !sorted) {
            int test = 0;
            for (int pos = 1; pos < play.size() - 1; pos++) {

                if (play.get(pos - 1).getFaceValue() > play.get(pos).getFaceValue()) {
                    Collections.swap(play, pos - 1, pos);
                    test++;
                } else if (play.get(pos + 1).getFaceValue() < play.get(pos).getFaceValue()) {
                    Collections.swap(play, pos + 1, pos);
                    test++;
                }
            }
            if (test == 0) {
                sorted = true;
            }
            i++;
        }
    }
    /*
      @return boolean isSet, whether or not is set
     */
    public boolean getSet(){
        return this.isSet;
    }

    /*
      @return boolean isRun to see if run
     */
    public boolean getRun(){
        return this.isRun;
    }

    /*
       @return boolean if it is a set
       @param int card value to be checked
     */
    public boolean verifySet(int cardValue){
        boolean verify = false;
        if(cardValue == play.get(0).getFaceValue()){
            verify = true;
        }

        return verify;
    }

    /*
       @return Card get card
       @param int index of card
     */
    public Card getCard(int index){
        return this.play.get(index);
    }

    /*
       @return Card remove card
     */
    public Card removeCard(int index){
        return this.play.remove(index);
    }

    /*
      @return int play size
     */
    public int getSize(){
        return this.play.size();
    }

    /*
      @return String toString of PLay
     */
    public String toString(){
        String result = getFieldPosition() + "\n";


        for(Card card : this.play){
            result += card.toString() + ", ";
        }

        return result;
    }

}
