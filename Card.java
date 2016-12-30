import javax.swing.*;

/**
 * Created by Dewey Milton on 2/17/2016.
 */
public class Card extends JLabel {

    private int faceValue;
    private String suit;
    boolean selected;
    private int handPosition;
    private boolean newCard;
    private ImageIcon cardPic;
    private int xPos;
    private String imgName;

    /*
      constructs a card
      @param int Face value
      @param int suite Counter
      @return Card that was made
     */
    public Card(int _faceValue, int suitCounter)
    {
        this.imgName = null;
        this.cardPic = null;
        this.selected = false;
        this.handPosition = -1;
        this.newCard = false;
        this.xPos = 0;
        if(_faceValue >= 2 && _faceValue <= 14) {
            this.faceValue =  _faceValue;
        }



        switch(suitCounter)
        {
            case 0:
                this.suit = "Hearts";
                break;
            case 1:
                this.suit = "Diamonds";
                break;
            case 2:
                this.suit = "Clubs";
                break;
            case 3:
                this.suit = "Spades";
                break;
        }

        setImage();

    }

    /*
       Creates a card image
     */
    private void setImage(){
        System.out.println(this.getFaceValue() + " " + this.suit);
        String input = "";
        switch(this.getFaceValue()){
            case 11:
                input = "jack";
                break;
            case 12:
                input = "queen";
                break;
            case 13:
                input = "king";
                break;
            case 14:
                input = "ace";
                break;
            default:
                input += this.faceValue;
                break;
        }

        String suit = this.suit.toLowerCase();
        input += suit.charAt(0) + ".jpg";
        this.imgName = input;
        this.cardPic = new ImageIcon(input);



    }

    /*
      @return ImageIcon get cards images
     */
    public ImageIcon getImage(){
        return this.cardPic;
    }

    /*
       @return String of Card image
     */
    public String getImageName(){
        return this.imgName;
    }

    /*
       @param boolean if card is new
     */
    public void setNewCard(boolean isNewCard){
        this.newCard = isNewCard;
    }

    /*
    @return boolean if card is new
     */
    public boolean isNewCard(){
        return this.newCard;
    }
    /**
     * Sets position in hand
     * @param position of where located in hand
     */
    public void setHandPosition(int position)
    {
        this.handPosition = position;
    }

    /**
     * Hand location
     * @return int handPosition
     */
    public int handLocation()
    {
        return this.handPosition;
    }

    public String getSuit()
    {
        String result = this.suit;
        return result;
    }

    /*
        @return boolean if card is selected
     */
    public boolean getSelection()
    {
        return this.selected;
    }

    /*
       sets card to be selected
     */
    public void setSelection()
    {
        if(this.selected == false){
            this.selected = true;
        }
        else
            this.selected = false;
    }

    /*
      @return int card value
     */
    public int getFaceValue()
    {
        int result = this.faceValue;
        return result;
    }

    /*
      @return String Card toString
     */
    public String toString()
    {
        String tmpFace = "";
        if(this.faceValue >= 11 && this.faceValue <= 14) {
            switch (this.faceValue) {
                case 11:
                    tmpFace = "Jack";
                    break;
                case 12:
                    tmpFace = "Queen";
                    break;
                case 13:
                    tmpFace = "King";
                    break;
                case 14:
                    tmpFace = "Ace";
                    break;
            }
        }
        else
            tmpFace += this.faceValue;

        return "The " + tmpFace + " of " + this.suit;
    }

}
