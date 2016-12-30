import java.util.ArrayList;
import java.util.Random;

/**
 * Created by ASUS on 3/18/2016.
 */

    public class LiverPoolDemo {

        private Deck deck;
        private ArrayList<ArrayList<Plays>> field;
        private Player[] players;
        private DiscardPile discardPile;
        private Random rand;
        private int dealer;
        private final int HAND_SIZE = 11;
        private int numPlayers;
        private View view;
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
        public LiverPoolDemo(int _numPlayers) {
            numPlayers = _numPlayers;
            deck = new Deck(numPlayers);
            field = new ArrayList<ArrayList<Plays>>(numPlayers);
            players = new Player[numPlayers];
            discardPile = new DiscardPile();
            rand = new Random();
            System.out.println(numPlayers);
            dealer = rand.nextInt(numPlayers - 1);
            if (dealer < 0) {
                dealer = rand.nextInt(numPlayers);
            }
            view = new View();
            playerTurn = 0;
            this.round = 0;
            onField = false;
            this.winCondition = "";
            this.gameOver = false;
            this.winner = null;
            this.fetalBomb = null;
        }

        public void startGame() {
            for (int setup = 0; setup < players.length; setup++) {
                players[setup] = new Player(setup);
            }

            for (int fields = 0; fields < numPlayers; fields++) {
                field.add(new ArrayList<Plays>());
            }

            players[dealer].setDealer(true);



            while (!gameOver) {

                boolean roundEnd = false;

                if (round > 0) {

                    setNewDealer(players);
                    resetField();
                    resetPlayers();
                    resetDeck();
                    System.out.println(deck);
                }

                if (getDealer(players) == numPlayers) {
                    playerTurn = 0;
                } else
                    playerTurn = getDealer(players) + 1;



                deck.shuffle();
                deal();
                discardPile.discard(deck.dealCard());
                onField = false;
                while (!roundEnd) {
                    winCondition(round);
                    System.out.println("In Round " + round + " lay down " + this.winCondition + " to go down\n");

                    if (onField) {
                        viewField();
                    }

                    boolean firstDrew = playerMayI();

                    if (!firstDrew) {
                        System.out.println("Draw Step, player is on the field " + players[playerTurn].isDown());
                        drawStep();

                    }

                    boolean turnOver = false;

                    while (!turnOver) {
                        System.out.println(players[playerTurn].viewHand());
                        String options = view.takeTurn(players[playerTurn].getPlayerNum(), round, players[playerTurn].isDown());

                        switch (options.toLowerCase()) {
                            case "set":
                                goDown();
                                break;
                            case "play":
                                if (players[playerTurn].isDown()) {
                                    playCard();
                                } else
                                    System.out.println("You have not gone down yet");
                                break;
                            case "hand":
                                System.out.println(players[playerTurn].viewHand());
                                break;
                            case "field":
                                if (onField) {
                                    viewField();
                                } else
                                    System.out.println("Nothing on the field");
                                break;
                            case "swap":
                                swap();
                                break;
                            case "done":
                                turnOver = true;
                                discardStep();
                                break;
                        }

                        if (players[playerTurn].handSize() == 0) {
                            roundEnd = true;
                        }
                    }

                    if (turnOver) {
                        if (playerTurn + 1 < numPlayers) {
                            playerTurn++;
                        } else
                            playerTurn = 0;
                    }


                }

                round++;
            }

            viewField();

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

            System.out.println("Winner is " + this.winner.getPlayerNum() + " Score of " + this.winner.getScore() + " FetalBomb is " + this.fetalBomb.getPlayerNum() + " Score of "
                    + this.fetalBomb.getScore());
        }

        private String viewField() {
            String result = "";

            int pos = 0;
            while (pos < this.field.size()) {
                int i = 0;
                while (i < this.field.get(pos).size()) {
                    System.out.println(this.field.get(pos).get(i).toString());
                    i++;
                }
                pos++;
            }

            return result;
        }

        private void resetField() {
            ArrayList<ArrayList<Plays>> newField = new ArrayList<ArrayList<Plays>>();
            this.field = newField;

            for (int fields = 0; fields < numPlayers; fields++) {
                this.field.add(new ArrayList<Plays>());
            }

            this.onField = false;
        }

        private void resetPlayers() {
            for (int i = 0; i < this.players.length; i++) {
                players[i].setIsDown(false);
            }
        }

        private void resetDeck(){
            this.deck.reShuffle(discardPile);
            for(int i = 0; i < players.length; i++){
                if(players[i].handSize() != 0) {
                    this.deck.resetDeck(scoreCalc(players[i]));
                }
            }
            this.deck.shuffle();
            this.deck.shuffle();
        }

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

        private void goDown() {
            Plays[] playList;
            switch (this.round) {
                case 0:
                    playList = view.setCards(round, players[playerTurn]);
                    if(playList[0] == null || playList[1] == null){
                        System.out.println("Play Canceled");
                    }
                    else
                        set2Field(playList);

                    break;
                case 1:
                    playList = view.setCards(round, players[playerTurn]);
                    if(playList[0] == null || playList[1] == null){
                        System.out.println("Play Canceled");
                    }
                    else
                        set2Field(playList);
                    break;
                case 2:
                    playList = view.setCards(round, players[playerTurn]);
                    if(playList[0] == null || playList[1] == null){
                        System.out.println("Play Canceled");
                    }
                    else
                        set2Field(playList);
                    break;
                case 3:
                    playList = view.setCards(round, players[playerTurn]);
                    if(playList[0] == null || playList[1] == null || playList[2] == null){
                        System.out.println("Play Canceled");
                    }
                    else
                        set2Field(playList);
                    break;
                case 4:
                    playList = view.setCards(round, players[playerTurn]);
                    if(playList[0] == null || playList[1] == null || playList[2] == null){
                        System.out.println("Play Canceled");
                    }
                    else
                        set2Field(playList);
                    break;
                case 5:
                    playList = view.setCards(round, players[playerTurn]);
                    if(playList[0] == null || playList[1] == null || playList[2] == null){
                        System.out.println("Play Canceled");
                    }
                    else
                        set2Field(playList);
                    break;
                case 6:
                    playList = view.setCards(round, players[playerTurn]);
                    if(playList[0] == null || playList[1] == null || playList[2] == null){
                        System.out.println("Play Canceled");
                    }
                    else {
                        set2Field(playList);
                        gameOver = true;
                    }
                    break;
            }

        }

        /*
          Allows a player to play cards onto the field after already going down
         */
        private void playCard() {
            this.field = view.playCard(this.field, players[playerTurn]);
        }

        private void discardStep() {
            int selection = view.discard(players[playerTurn]);

            players[playerTurn].selectCard(selection);
            discardPile.discard(players[playerTurn].discardCard());
        }

        private void drawStep() {
            System.out.println("In drawStep Method");
            if (!players[playerTurn].getCpu()) {
                String choice = view.drawChoice(players[playerTurn], discardPile);

                switch (choice.toUpperCase()) {
                    case "D":
                        Card tmpCard = deck.dealCard();
                        System.out.println("Drew " + tmpCard.toString());
                        players[playerTurn].drawCard(tmpCard);
                        break;
                    case "P":
                        players[playerTurn].drawCard(discardPile.retrieve());
                        break;
                    default:
                        //IF wrong answer
                        break;
                }
            }
            //else cpu DrawStep
        }

        private boolean playerMayI() {
            boolean firstDrew = false;
            boolean mayI = false;
            int count = 0;
            int askOrder = playerTurn;

            System.out.println("In May I Step****");

            while (!mayI && count < numPlayers - 1) {
                System.out.println("Player " + askOrder + "\n");
                System.out.println(players[askOrder].viewHand());

                mayI = view.mayI(discardPile);
                System.out.println("May I " + mayI);
                if (mayI) {
                    players[askOrder].drawCard(discardPile.retrieve());
                    if (askOrder == playerTurn) {
                        firstDrew = true;
                    }

                    if (!firstDrew) {
                        players[askOrder].drawCard(deck.dealCard());
                    }
                }

                if (askOrder + 1 < numPlayers) {
                    askOrder++;
                } else
                    askOrder = 0;

                count++;
            }

            return firstDrew;
        }

        private int getDealer(Player[] players) {
            int dealer = 0;

            for (int player = 0; player < players.length; player++) {
                if (players[player].isDealer()) {
                    dealer = player;
                }
            }
            return dealer;
        }

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

        private void deal() {
            int player = getDealer(players);

            if (player + 1 < numPlayers) {
                player++;
            } else
                player = 0;

            Card[][] hands = deck.dealHand(numPlayers, HAND_SIZE);

            int handsDealt = 0;
            System.out.println("Current Player " + player);
            while (handsDealt < hands.length) {

                players[player].setHand(hands[handsDealt]);

                if (player + 1 < numPlayers) {
                    player++;
                } else
                    player = 0;

                handsDealt++;
            }

        }

        private void swap() {
            view.swapCards(players[playerTurn]);

        }

        private void set2Field(Plays[] playList) {

            System.out.println("In go down, before assigning field");
            for(int i = 0; i < playList.length; i++){
                for(Card play: playList[i]){
                    System.out.println(play);
                }
            }
            int pos = 0;
            while (pos < playList.length) {
                this.field.get(playerTurn).add(playList[pos]);
                pos++;
            }

            pos = 0;
            while (pos < field.get(playerTurn).size()) {
                field.get(playerTurn).get(pos).setFieldPosition(playerTurn, pos);
                pos++;
            }
            System.out.println("After while placing " + this.field.get(playerTurn).get(0).toString() + " and "
                    + "\n" + this.field.get(playerTurn).get(1).toString());
            onField = true;
            players[playerTurn].setIsDown(true);
        }



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


        private boolean roundCondition(int round, Plays play){
            boolean conditionMet = false;

            switch(round){
                case 1:
                    conditionMet = is2sets(play);
                    break;
                case 2:
                    conditionMet = isSetRun(play);
                    break;
                case 3:
                    conditionMet = is2Runs(play);
                    break;
                case 4:
                    conditionMet = threeSets(play);
                    break;
                case 5:
                    conditionMet = twoSets1Run(play);
                    break;
                case 6:
                    conditionMet = twoRuns1Set(play);
                    break;
                case 7:
                    conditionMet = threeRuns(play);
                    break;
            }

            return conditionMet;
        }

        public boolean is2sets(Plays play){
            boolean is2sets = false;

            int firstValue = play.getCard(0).getFaceValue();
            int secondValue = play.getCard(0).getFaceValue();
            int count = 1;
            Card[] tmpPlay = new Card[play.getSize()];

            for(int pos = 0; pos < tmpPlay.length; pos++){
                tmpPlay[pos] = play.removeCard(0);
            }

            tmpPlay = sortArray(tmpPlay);

            for(int i = 0; i < tmpPlay.length; i++){
                play.addCard(tmpPlay[i]);
            }


            for(Card card : play){
                if(count == 1) {
                    if (card.getFaceValue() != firstValue) {
                        secondValue = card.getFaceValue();
                        count++;
                    }
                }
                else if(count == 2){
                    if(card.getFaceValue() != secondValue){
                        is2sets = false;
                        count++;
                    }
                }
            }

            if(count == 2){
                is2sets = true;
            }
            return is2sets;
        }

        public boolean isSetRun(Plays play){
            boolean isSetRun = false;
            boolean isRun = false;
            boolean isSet = false;
            boolean hasRun = false;


            Card[][] suitArray = buildSuitArray(play);

            Card[][] sortedArray = sort2dArray(suitArray);

            int search = 0;

            //treverses the 2d array to find run in single array
            System.out.println("About to go into run search array length");

            while(search < sortedArray.length && !isRun){
                isRun = runSearch(sortedArray[search]);
                if(isRun){
                    hasRun = true;
                }
                search++;
            }

            isSet = setSearch(sortedArray);

            for(int pos = 0; pos < sortedArray.length; pos++){
                for(int i = 0; i < sortedArray[pos].length; i++){
                    play.addCard(sortedArray[pos][i]);
                }
            }

            System.out.println("Has run " + hasRun + " has set " + isSet);
            if(hasRun && isSet){
                isSetRun = true;
            }

            return isSetRun;
        }

        public boolean runSearch(Card[] run){
            boolean  isRun = false;

            //Looks for a run in the array
            int runCount = 0;

            System.out.println("************Looking for runs out of **********************");
            for(Card card: run){
                System.out.println(card.toString());
            }

            for(int pos = 0; pos < run.length; pos++)
            {
                if(run.length > 3 && pos + 1 < run.length){
                    System.out.println("Testing " + run[pos].getFaceValue() + " And " + run[pos + 1].getFaceValue());
                    if(run[pos].getFaceValue() + 1 == run[pos + 1].getFaceValue()){
                        runCount++;
                        System.out.println("True Run Count " + runCount);
                    }
                    else {
                        runCount = 0;
                        System.out.println("False Run count " + runCount);
                    }
                }
                if(runCount >= 3){
                    isRun = true;
                }
            }

            System.out.println("Is Run Found " + isRun);

            return isRun;
        }

        public boolean setSearch(Card[][] set){
            boolean isSet = false;

            //Looks for a set in a 2Darray
            int setCount =0;
            int setValue = 0;


            for(int pos = 0; pos < set.length; pos++)
            {
                for(int i = 0; i < set[pos].length; i++){
                    setCount = 0;
                    setValue = set[pos][i].getFaceValue();
                    for(int searchPos = 0; searchPos < set.length; searchPos++) {
                        for(int indexPos = 0; indexPos < set[searchPos].length; indexPos++) {
                            if(setValue == set[searchPos][indexPos].getFaceValue()) {
                                setCount++;
                            }
                        }
                    }
                    if(setCount > 2){
                        isSet = true;
                    }

                }

            }

            return isSet;
        }

        public Card[][] buildSuitArray(Plays play){

            int numHearts = 0;
            int numDia = 0;
            int numClubs = 0;
            int numSpades = 0;

            for(Card card : play){
                System.out.println(card.getSuit());
                if(card.getSuit().equals("Hearts")){
                    numHearts++;
                }

                if(card.getSuit().equals("Diamonds")){
                    numDia++;
                }

                if(card.getSuit().equals("Clubs")){
                    numClubs++;
                }
                if(card.getSuit().equals("Spades"))
                    numSpades++;

            }


            //Builds an array of suits
            Card[][] suits = new Card[4][];  // 0 hearts, 1 diamonds, 2 clubs, 3 spades

            suits[0] = new Card[numHearts];
            suits[1] = new Card[numDia];
            suits[2] = new Card[numClubs];
            suits[3] = new Card[numSpades];

            for(int pos = 0; pos < suits.length; pos++){
                int index = 0;
                while(index < suits[pos].length){
                    int playDex = 0;
                    boolean found = false;
                    while(playDex < play.getSize() && !found) {

                        switch (pos) {
                            case 0:
                                if (play.getCard(playDex).getSuit().equals("Hearts")) {
                                    suits[0][index] = play.removeCard(playDex);
                                    found = true;
                                }
                                break;
                            case 1:
                                if(play.getCard(playDex).getSuit().equals("Diamonds")){
                                    suits[1][index] = play.removeCard(playDex);
                                    found = true;
                                }
                                break;
                            case 2:
                                if(play.getCard(playDex).getSuit().equals("Clubs")){
                                    suits[2][index] = play.removeCard(playDex);
                                    found = true;
                                }
                                break;
                            case 3:
                                if(play.getCard(playDex).getSuit().equals("Spades")){
                                    suits[3][index] = play.removeCard(playDex);
                                    found = true;
                                }
                                break;

                        }
                        playDex++;
                    }
                    index++;
                }
            }


            return suits;
        }

        public Card[][] sort2dArray(Card[][] suits){
            //Sorts 2DArray of suits in number order
            for(int search = 0; search < suits.length; search++) {
                for (int i = search; i < suits.length; i++) {
                    if (suits[i].length > 3) {
                        for (int pos = 0; pos < suits[i].length; pos++) {
                            if (pos == 0) {
                                System.out.println("Testing " + suits[i][pos].getFaceValue() + " and " + suits[i][pos + 1]);
                                if (suits[i][pos].getFaceValue() > suits[i][pos + 1].getFaceValue()) {
                                    System.out.println("Switching " + suits[i][pos] + " With " + suits[i][pos + 1]);
                                    Card tmpCard = suits[i][pos];
                                    suits[i][pos] = suits[i][pos + 1];
                                    suits[i][pos + 1] = tmpCard;
                                }
                            }

                            if (pos > 0 && pos + 1 < suits[i].length) {
                                System.out.println("Testing " + suits[i][pos - 1].getFaceValue() + " and " + suits[i][pos + 1]);
                                if (suits[i][pos - 1].getFaceValue() > suits[i][pos + 1].getFaceValue()) {
                                    System.out.println("Switching " + suits[i][pos - 1] + " With " + suits[i][pos + 1]);
                                    Card tmpCard = suits[i][pos - 1];
                                    suits[i][pos - 1] = suits[i][pos + 1];
                                    suits[i][pos + 1] = tmpCard;
                                }
                            }
                            if (pos + 1 < suits[i].length) {
                                System.out.println("Testing " + suits[i][pos].getFaceValue() + " and " + suits[i][pos + 1]);
                                if (suits[i][pos].getFaceValue() > suits[i][pos + 1].getFaceValue()) {
                                    System.out.println("Switching " + suits[i][pos] + " With " + suits[i][pos + 1]);
                                    Card tmpCard = suits[i][pos];
                                    suits[i][pos] = suits[i][pos + 1];
                                    suits[i][pos + 1] = tmpCard;
                                }
                            }

                        }
                    }
                }
            }

            return suits;
        }

        public Card[] sortArray(Card[] sets) {
            //Sorts Array of suits in number order
            for (int i = 0; i < sets.length; i++) {

                if (i == 0) {
                    if (sets[i].getFaceValue() > sets[i + 1].getFaceValue()) {
                        Card tmpCard = sets[i];
                        sets[i] = sets[i + 1];
                        sets[i + 1] = tmpCard;
                    }
                }

                if (i > 0 && i + 1 < sets.length) {
                    if (sets[i - 1].getFaceValue() > sets[i + 1].getFaceValue()) {
                        Card tmpCard = sets[i - 1];
                        sets[i - 1] = sets[i + 1];
                        sets[i + 1] = tmpCard;
                    }
                }

                if (i + 1 < sets.length) {
                    if (sets[i].getFaceValue() > sets[i + 1].getFaceValue()) {
                        Card tmpCard = sets[i];
                        sets[i] = sets[i + 1];
                        sets[i + 1] = tmpCard;
                    }

                }

            }


            return sets;
        }

        public boolean is2Runs(Plays play){
            boolean is2Runs = false;
            int runCount = 0;

            Card[][] suitArray = buildSuitArray(play);

            for(int i = 0; i < suitArray.length; i++){
                for(int pos = 0; pos < suitArray[i].length; pos++){
                    System.out.println(suitArray[i][pos]);
                }
            }

            Card[][] sortedArray = sort2dArray(suitArray);

            for(int i = 0; i < sortedArray.length; i++){
                for(int pos = 0; pos < sortedArray[i].length; pos++){
                    System.out.println(sortedArray[i][pos]);
                }
            }

            for(int search = 0; search < sortedArray.length; search++){
                boolean isRun = runSearch(sortedArray[search]);

                if(isRun){
                    runCount++;
                }
            }

            if(runCount == 2){
                is2Runs = true;
            }

            return is2Runs;
        }

        public boolean threeSets(Plays play){
            boolean isThreeSets = false;

            Card[][] setArray = buildSetArray(play);

            System.out.println("in three set test, array length is " + setArray.length);

            for(int i = 0; i < setArray.length; i++){
                for(int pos = 0; pos < setArray[i].length; pos++){
                    System.out.println(setArray[i][pos]);
                }
            }
            if(setArray.length == 3){
                isThreeSets = true;
            }


            return isThreeSets;
        }

        private Card[][] buildSetArray(Plays play){

            int digit1 = 0;
            int digit2 = 0;
            int digit3 = 0;
            int numDigit1 = 0;
            int numDigit2 = 0;
            int numDigit3 = 0;
            int searchDigit = 0;
            int onSet = 1;
            int numSets = 0;

            System.out.println("In set builder");



            for(int i = 0; i < play.getSize(); i++){
                boolean newDigit = false;
                boolean setFound = false;
                int pos = 0;

                while(!newDigit && pos < play.getSize()) {
                    System.out.println("Testing at Begging for search Digit " + play.getCard(pos).getFaceValue());
                    if (play.getCard(pos).getFaceValue() != digit1 && play.getCard(pos).getFaceValue() != digit2 && play.getCard(pos).getFaceValue() != digit3)
                        newDigit = true;
                    if(newDigit){
                        searchDigit = play.getCard(pos).getFaceValue();
                    }
                    pos++;
                }

                int tmpPos = 0;
                System.out.println(i);
                while (tmpPos < play.getSize() && numSets != 3) {
                    System.out.println("i " + i + " Pos " + tmpPos + " search digit " + searchDigit);
                    System.out.println("Testing " + play.getCard(tmpPos).getFaceValue() + " Against " + searchDigit);
                    System.out.println(play.getCard(tmpPos).getFaceValue() == searchDigit);
                    if (play.getCard(tmpPos).getFaceValue() == searchDigit) {
                        System.out.println("******In If******");
                        switch (onSet) {
                            case 1:
                                numDigit1++;
                                if (numDigit1 == 3 && searchDigit != digit1) {
                                    System.out.println("In second IF, NUM FOUND CASE 1 " + numDigit1);
                                    digit1 = play.getCard(tmpPos).getFaceValue();
                                    numSets++;
                                    setFound = true;
                                    System.out.println("NUMDIGIT +++++++ SET CASE 1");
                                }

                                System.out.println("Found " + searchDigit + " NumDigit is " + numDigit1);
                                break;
                            case 2:
                                numDigit2++;
                                if (numDigit2 == 3 && searchDigit != digit2) {
                                    System.out.println("In second IF, NUM FOUND CASE 2 " + numDigit2);
                                    digit2 = play.getCard(tmpPos).getFaceValue();
                                    numSets++;
                                    setFound = true;

                                    System.out.println("NuM DIGIT +++++ SET Case 2");
                                    System.out.println("Adding set in case 2");
                                }
                                System.out.println("Found " + searchDigit + " NumDigit is " + numDigit2);
                                break;
                            case 3:
                                numDigit3++;
                                if (numDigit3 == 3 && searchDigit != digit3) {
                                    System.out.println("In second IF, NUM FOUND CASE 3 " + numDigit3);
                                    digit3 = play.getCard(tmpPos).getFaceValue();
                                    numSets++;
                                    setFound = true;
                                    System.out.println("NUMDIGIT+++++ CASE 3 SET");
                                }
                                System.out.println("Found " + searchDigit + " NumDigit is " + numDigit3);
                                break;
                        }
                    }
                    tmpPos++;
                }

                if(setFound) {
                    onSet++;
                    setFound = false;
                }
                else{
                    switch(onSet){
                        case 1:
                            numDigit1 = 0;
                            break;
                        case 2:
                            numDigit2 = 0;
                            break;
                        case 3:
                            numDigit3 = 0;
                            break;
                    }
                }

            }

            System.out.println("numDigit1 " + numDigit1 + " numDigit2 " + numDigit2 + "numDigit3 " + numDigit3 + "numSets "  + numSets);
            System.out.println("Digit1 " + digit1 + " Digit 2 " + digit2 + " Digit 3" + digit3);

            for(Card card : play){
                System.out.println(card);
            }

            Card[][] setArray = buildSetArray(play, numDigit1, numDigit2, numDigit3, numSets, digit1, digit2, digit3);

            return setArray;
        }

        private Card[][] buildSetArray(Plays play, int numDigit1, int numDigit2, int numDigit3, int numSets, int digit1, int digit2, int digit3){
            //Builds an array of sets
            Card[][] sets = new Card[numSets][];  // 0 first set, 1 2nd set, 2 3rd set,
            int setNum = 0;
            for(int set = 0; set < sets.length; set++) {
                if(set == 0){
                    setNum = numDigit1;
                }
                else if(set == 1){
                    setNum = numDigit2;
                }
                else if(set == 2){
                    setNum = numDigit3;
                }

                sets[set] = new Card[setNum];
            }

            for(int pos = 0; pos < sets.length; pos++){
                int index = 0;
                while(index < sets[pos].length){
                    boolean done = false;
                    int numFound = 0;
                    int playDex = 0;
                    while(playDex < play.getSize() && !done) {
                        for(Card card: play) {
                            System.out.println("Cards in play " + card.getFaceValue() + "\n");
                        }
                        System.out.println("Testing " + play.getCard(playDex).getFaceValue() + " Against " + pos );
                        System.out.println("Num found " + numFound + " playDex " + playDex + " Play size " + play.getSize());
                        switch (pos) {
                            case 0:
                                if (play.getCard(playDex).getFaceValue() == digit1) {
                                    System.out.println("Found digit in case 0 " + play.getCard(playDex).getFaceValue());
                                    sets[pos][index] = play.removeCard(playDex);
                                    System.out.println("Removed " + sets[pos][index]);
                                    numFound++;
                                    index++;
                                    if(numFound == numDigit1){
                                        done = true;
                                    }
                                }
                                else {
                                    playDex++;
                                }
                                break;
                            case 1:
                                if(play.getCard(playDex).getFaceValue() == digit2){
                                    System.out.println("Found digit in case 1 " + play.getCard(playDex).getFaceValue());
                                    sets[pos][index] = play.removeCard(playDex);
                                    numFound++;
                                    index++;
                                    if(numFound == numDigit2) {
                                        done = true;
                                    }
                                }
                                else {
                                    playDex++;
                                }
                                break;
                            case 2:
                                if(play.getCard(playDex).getFaceValue() == digit3){
                                    System.out.println("Found digit case 2" + play.getCard(playDex).getFaceValue());
                                    sets[pos][index] = play.removeCard(playDex);
                                    numFound++;
                                    index++;
                                    if(numFound == numDigit3) {
                                        done = true;
                                    }
                                }
                                else
                                    playDex++;
                                break;
                        }
                    }
                    numFound = 0;
                    index++;
                }
            }
            return sets;
        }


        public boolean twoSets1Run(Plays play){
            boolean isTwoSets1Run = false;
            boolean hasTwoSets = false;
            boolean hasRun = false;
            int runCount = 0;

            Card[][] suitArray = buildSuitArray(play);

            Card[][] sortedArray = sort2dArray(suitArray);

            int numSets = numSets(sortedArray);

            if(numSets == 2){
                hasTwoSets = true;
            }

            System.out.println("Num Sets" + numSets + "Has two sets " + hasTwoSets);
            for(int search = 0; search < sortedArray.length; search++){
                boolean isRun = runSearch(sortedArray[search]);

                if(isRun){
                    runCount++;
                }

            }

            if(runCount == 1){
                hasRun = true;
            }

            System.out.println("run count " + runCount + "has run " + hasRun);

            if(hasRun && hasTwoSets){
                isTwoSets1Run = true;
            }

            return isTwoSets1Run;
        }

        private int setValue(Card[][] _play){
            int setValue = 0;
            int setCount = 0;
            boolean foundSet = false;

            int pos = 0;
            while(pos < _play.length && !foundSet)
            {
                for(int i = 0; i < _play[pos].length; i++){
                    setCount = 0;
                    setValue = _play[pos][i].getFaceValue();
                    for(int searchPos = 0; searchPos < _play.length; searchPos++) {
                        for(int indexPos = 0; indexPos < _play[searchPos].length; indexPos++) {
                            if(setValue == _play[searchPos][indexPos].getFaceValue()) {
                                setCount++;
                            }
                        }
                        if(setCount >= 3){
                            foundSet = true;
                        }
                    }
                }
                pos++;
            }



            return setValue;
        }

        private int numSets(Card[][] _play){
            int numSets = 0;
            int setCount = 0;
            boolean foundSet = false;
            int setValue = 0;
            int firstSet = 0, secondSet = 0, thirdSet = 0;
            boolean firstFound = false, secondFound = false, thirdFound = false;

            int pos = 0;
            while(pos < _play.length)
            {
                for(int i = 0; i < _play[pos].length; i++){
                    setCount = 0;
                    System.out.println("Before set Value first value " + firstSet + " second value " + secondSet + " third value " + thirdSet);
                    if(_play[pos][i].getFaceValue() != firstSet && _play[pos][i].getFaceValue() != secondSet && _play[pos][i].getFaceValue() != thirdSet) {
                        setValue = _play[pos][i].getFaceValue();
                        System.out.println("Setting new test Value" + setValue);
                    }
                    else
                        setValue = 0;

                    for(int searchPos = 0; searchPos < _play.length; searchPos++) {
                        for(int indexPos = 0; indexPos < _play[searchPos].length; indexPos++) {
                            if(setValue == _play[searchPos][indexPos].getFaceValue()) {
                                System.out.println("Set count ++ " + setCount );
                                setCount++;
                            }
                        }
                        if(setCount >= 3){
                            setCount = 0;
                            numSets++;
                            System.out.println("Set count " + setCount + " numSets " + numSets);
                            if(!firstFound){
                                firstSet = setValue;
                                firstFound = true;
                            }
                            else if(!secondFound && firstFound){
                                secondFound = true;
                                secondSet = setValue;
                            }
                            else if(!thirdFound && secondFound && firstFound){
                                thirdFound = true;
                                thirdSet = setValue;
                            }
                        }
                    }
                }
                System.out.println("end Rotation first found " + firstFound + " Second found " + secondFound + "Third found " + thirdFound);
                pos++;
            }


            return numSets;
        }

        public boolean twoRuns1Set(Plays play){
            boolean isTwoRuns1Set = false;
            boolean hasTwoRuns = false;
            boolean foundSet = false;
            int runCount = 0;

            Card[][] suitArray = buildSuitArray(play);

            Card[][] sortedArray = sort2dArray(suitArray);

            foundSet = setSearch(sortedArray);

            for(int search = 0; search < sortedArray.length; search++){
                boolean isRun = runSearch(sortedArray[search]);

                if(isRun){
                    runCount++;
                }

            }


            if(runCount == 2){
                hasTwoRuns = true;
            }

            if(foundSet && hasTwoRuns){
                isTwoRuns1Set = true;
            }

            return isTwoRuns1Set;
        }

        public boolean threeRuns(Plays play){
            boolean isThreeRuns = false;
            int runCount = 0;

            Card[][] suitArray = buildSuitArray(play);

            Card[][] sortedArray = sort2dArray(suitArray);

            for(int search = 0; search < sortedArray.length; search++){
                boolean isRun = runSearch(sortedArray[search]);

                if(isRun){
                    runCount++;
                }
            }

            if(runCount == 3){
                isThreeRuns = true;
            }

            return isThreeRuns;
        }

    }


