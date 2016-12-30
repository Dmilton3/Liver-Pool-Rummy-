import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;
/**
 * Created by Dewey Milton on 3/14/2016.
 * LiverPool Game Controller
 */
public class LiverPool {

    private Deck deck;
    private Player[] players;
    private DiscardPile discardPile;
    private Random rand;
    private int dealer;
    private final int HAND_SIZE = 11;
    private int numPlayers;
    private int playerTurn;
    private int round;
    private boolean onField;
    private boolean gameOver;
    private String winCondition;
    private Player winner;
    private Player fetalBomb;


    /**
     * Constructs the Liverpool game
     *
     * @param _numPlayers Number of players playing the game
     */
    public LiverPool(int _numPlayers) {
        numPlayers = _numPlayers;
        deck = new Deck(numPlayers);

        players = new Player[numPlayers];
        System.out.println("Setting up the game");
        for (int setup = 0; setup < players.length; setup++) {
            players[setup] = new Player(setup);
        }

        discardPile = new DiscardPile();
        rand = new Random();
        dealer = rand.nextInt(numPlayers - 1);
        if (dealer < 0) {
            dealer = rand.nextInt(numPlayers);
        }
        playerTurn = 0;
        this.round = 0;
        this.winCondition = "";
        this.gameOver = false;
        this.winner = null;
        this.fetalBomb = null;

        }

    /*
      Sets up the start of the LiverPool game
     */
    public  void startGame() {


        players[dealer].setDealer(true);

            if (getDealer(players) == numPlayers) {
                playerTurn = 0;
            } else
                playerTurn = getDealer(players) + 1;


        }


    /*
      Resets round
     */
     public void roundReset(){

         deck.shuffle();
         System.out.println(deck.toString());
         deal();
         discardPile.discard(deck.dealCard());

         winCondition(round);
    }

      /*
         Gathers ending score and sets Winner/Loser
       */
       public void endGame(){
           for(int i = 0; i < numPlayers; i++){
               if(players[i].handSize() > 0){
                   scoreCalc(players[i]);
               }
           }

           for(int pos = 0; pos < 2; pos++){
               for(int j = 1; j < numPlayers - 1; j++){
                   if(players[j].getScore() < players[j -1].getScore()){
                       Player tmpPlayer = players[j];
                       players[j] = players[j -1];
                       players[j -1] = tmpPlayer;
                   }

                   if(players[j].getScore() > players[j + 1].getScore()){
                       Player tmpPlayer = players[j];
                       players[j] = players[j +1];
                       players[j +1] = tmpPlayer;
                   }
               }
           }
           this.winner = players[0];
           this.fetalBomb = players[players.length - 1];


       }

        /*
          Sends back the player for next turn
         */
       public Player nextTurn(){
           if (playerTurn + 1 < numPlayers) {
               playerTurn++;
           } else
               playerTurn = 0;

           return players[playerTurn];
       }

      /*
        Sets the dealer for each round
       */
       public void setDealer(){
           setNewDealer(players);
       }

      /*
         Finds sends back an array of the loser and winner
       */
        public Player[] getOutCome(){
            Player[] outCome = new Player[2];
            outCome[0] = this.winner;
            outCome[1] = this.fetalBomb;
            return outCome;
        }

    /*
      resets players hands for next round
     */
    public void resetPlayers() {
        for (int i = 0; i < this.players.length; i++) {
            players[i].setIsDown(false);
        }
    }

    /*
     Resets deck for next round
     */
    public void resetDeck(){
        this.deck.reShuffle(discardPile);
        for(int i = 0; i < players.length; i++){
            if(players[i].handSize() != 0) {
                this.deck.resetDeck(scoreCalc(players[i]));
            }
        }
        this.deck.shuffle();
        this.deck.shuffle();
    }

    /*
      Calculated the score for each round
     */
    private ArrayList<Card> scoreCalc(Player player){
        ArrayList<Card> cardsLeft = new ArrayList<>();
        int score = player.getScore();
        String scoreType = "";
        while(player.handSize() != 0){
            Card tmpCard = player.playCard(0);
            if(tmpCard.getFaceValue() == 14){
                scoreType = "high";
            }
            else if(tmpCard.getFaceValue() >= 10 && tmpCard.getFaceValue() < 14){
                scoreType = "mid";
            }
            else
            scoreType = "low";

            switch(scoreType){
                case "high":
                    score += 15;
                    break;
                case "mid":
                    score += 10;
                    break;
                case "low":
                    score += 5;
                    break;
            }
            cardsLeft.add(tmpCard);
        }

        player.setScore(score);

        return cardsLeft;
    }

    /*
       @param Plays play checks this play to see if it is a Set
       @return Boolean true or false
     */
    public boolean isSet(Plays plays){
        boolean isSet = false;

        int check = 0;
        int count = 0;

        int pos = 0;
        while(plays != null && pos < plays.getSize()){
            if(pos == 0){
                check = plays.getCard(0).getFaceValue();
            }
            else{
                if(plays.getCard(pos).getFaceValue() == check)
                    count++;
            }
            pos++;
        }

        if(count >= 2){
            isSet = true;
        }


        return isSet;
    }


    /*
      @param Plays plays checks to see if this play is a Run
      @return Boolean
     */
    public boolean isRun(Plays play){
        boolean isRun = false;

        if(play != null) {
            if (play.getSize() >= 4) {

                isRun = runChecker(play);
                System.out.println("Run Test Passed " + isRun);
            }
        }

        return isRun;
    }

    /*
       PLay validator
       @param Card card to test whether or not a set
       @param Plays plays the play to check against
       @return boolean
     */
    public boolean checkPlay(Card card, Plays play){
        boolean valid = false;

        if(play.getRun()){
            if(card.getSuit().equalsIgnoreCase(play.getCard(0).getSuit())){
                if(card.getFaceValue() == 14){
                    if(play.getCard(0).getFaceValue() == 2 || play.getCard(play.getSize() - 1).getFaceValue() == 13){
                        valid = true;
                    }
                }else if(card.getFaceValue() + 1 == play.getCard(0).getFaceValue() || card.getFaceValue() - 1 == play.getCard(play.getSize() - 1).getFaceValue()){
                    valid = true;
                }
            }
        }
        else{
            if(card.getFaceValue() == play.getCard(0).getFaceValue()){
                valid = true;
            }
        }

        return valid;
    }

    /*
       @param Plays play to check for run
       @return boolean
     */
    private boolean runChecker(Plays play){
        int pos = 0;
        boolean checker = true;
        while (pos < play.getSize() - 1) {
            if (play.getCard(pos).getFaceValue() + 1 != play.getCard(pos + 1).getFaceValue()) {
                checker = false;
            }
            pos++;
        }
        return checker;
    }


    /*
       @return int[] gets scores of players
     */
    public int[] getScores(){
        int[] scores = new int[numPlayers];
        for(int i = 0; i < players.length; i++){
            scores[i] = players[i].getScore();
        }
        return scores;
    }



    /*
       @return Card draws card for player
     */
    public Card drawCard() {

       return deck.dealCard();

    }


    /*
      @return int player number
      @param Player[] players to find out which player is the dealer
     */
    private int getDealer(Player[] players) {
        int dealer = 0;

        for (int player = 0; player < players.length; player++) {
            if (players[player].isDealer()) {
                dealer = player;
            }
        }
        return dealer;
    }

    /*
      @param Player[] players Sets up next dealer
     */
    private void setNewDealer(Player[] players) {
        for (int player = 0; player < players.length; player++) {
            if (players[player].isDealer()) {
                if (player + 1 == numPlayers) {
                    players[0].setDealer(true);
                } else
                    players[player + 1].setDealer(true);

                players[player].setDealer(false);
            }
        }
    }

    /*
      deals the hands to all the players
     */
    private void deal() {
        int player = getDealer(players);

        if (player + 1 < numPlayers) {
            player++;
        } else
            player = 0;

      Card[][] hands = deck.dealHand(numPlayers, HAND_SIZE);

      /* Card[][] hands = new Card[4][];

        for(int i = 0; i < hands.length; i++){
            hands[i] = new Card[11];

            Card c01 = new Card(4,1);
            Card c02 = new Card(4,2);
            Card c03 = new Card(6,3);
            Card c04 = new Card(6,3);
            Card c05 = new Card(4,1);
            Card c06 = new Card(6,2);
            Card c07 = new Card(4,3);
            Card c08 = new Card(4,3);
            Card c09 = new Card(4,3);
            Card c10 = new Card(6,2);
            Card c11 = new Card(6,2);

            hands[i][0] = c01;
            hands[i][1] = c02;
            hands[i][2] = c03;
            hands[i][3] = c04;
            hands[i][4] = c05;
            hands[i][5] = c06;
            hands[i][6] = c07;
            hands[i][7] = c08;
            hands[i][8] = c09;
            hands[i][9] = c10;
            hands[i][10] = c11;

        } */

        int handsDealt = 0;

        while (handsDealt < hands.length) {

            players[player].setHand(hands[handsDealt]);

            if (player + 1 < numPlayers) {
                player++;
            } else
                player = 0;

            handsDealt++;
        }

    }


    /*
     displays the winCondition for each round
     @param int Round
     */
    private void winCondition(int round){


            switch(round){
                case 0:
                    this.winCondition = "Two Sets";
                    break;
                case 1:
                    this.winCondition = "Set and a Run";
                    break;
                case 2:
                    this.winCondition = "Two Runs";
                    break;
                case 3:
                    this.winCondition = "Three Sets";
                    break;
                case 4:
                    this.winCondition = "Two Sets and a Run";
                    break;
                case 5:
                    this.winCondition = "Two Runs and a Set";
                    break;
                case 6:
                    this.winCondition = "Three Runs";
                    break;
            }


        }

       /*
           fieldSetup for GUI Images of cards to go on the field
           @param gets current player
           @return PLays to placed on the field
        */
        public Plays fieldSetup(Player currentPlayer){
            int[] choices = currentPlayer.getImages().getSelectedIndices();
            ImageIcon[] selected = new ImageIcon[choices.length];

            for(int p = 0; p < choices.length; p++){
                selected[p] = currentPlayer.viewCard(choices[p]).getImage();
            }

            ArrayList<Card> tmpPlay = new ArrayList<>();
            for (int j = 0; j < selected.length; j++) {
                System.out.println(selected[j].toString());
                for (int i = 0; i < currentPlayer.getHand().size(); i++) {
                    if (selected[j].equals(currentPlayer.viewCard(i).getImage())){
                        System.out.println("Adding to the play " + currentPlayer.getHand().get(i).toString());
                        tmpPlay.add(currentPlayer.playCard(i));
                        System.out.println(tmpPlay.get(j));
                        i = currentPlayer.getHand().size() - 1;
                    }
                }
            }
            Plays tmp = new Plays(tmpPlay);
            System.out.println("Play that was built" + tmp.toString());
            DefaultListModel<ImageIcon> newModel = new DefaultListModel<>();

            System.out.println("Hand built adter making play");
            for(int i = 0; i < currentPlayer.getHand().size(); i++){
                System.out.println(currentPlayer.viewCard(i).toString());
                newModel.addElement(currentPlayer.getHand().get(i).getImage());
            }
            currentPlayer.updateModel(newModel);
            return tmp;
        }

        /*
          @return String of wincondition
         */
        public String getWinCondition(){
            return this.winCondition;
        }

        /*
         @return Player currently taking the turn
         */
        public Player getCurrentPlayer(){
            return players[playerTurn];
        }

       /*
          @return Discard Pile Access
        */
        public DiscardPile getPile(){
            return this.discardPile;
        }

       /*
         @return Player[] players access
        */
        public Player[] getPlayers(){
            return players;
        }


}
