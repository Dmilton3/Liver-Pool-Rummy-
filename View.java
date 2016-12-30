import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
/**
 * Created by ASUS on 3/29/2016.
 */
public class View {

    private Scanner scan;

    public View() {
        scan = new Scanner(System.in);
    }

    public Boolean mayI(DiscardPile pile) {
        boolean mayI = false;
        System.out.println("Would you like to pick up from discard pile? Yes:No\n" + pile.show());
        String choice = scan.next();

        switch (choice.toLowerCase()) {
            case "yes":
                mayI = true;
                break;
            case "no":
                mayI = false;
                break;
        }


        return mayI;
    }

    public String drawChoice(Player player, DiscardPile pile) {
        String choice = "";
        if (pile.size() != 0) {
            System.out.println("Player " + player.getPlayerNum() + " Hand holds \n" + player.viewHand() + "\n");
            System.out.println("Discard pile currently holds " + pile.show() + "\n");
            System.out.println("Do you want to draw from the deck or discard pile? \nD : Deck\nP : Discare Pile");
            choice = scan.next();
            scan.nextLine();
        } else {
            System.out.println("Drawing from the deck, no cards in discard pile");
            choice = "D";
        }

        return choice;
    }

    public String takeTurn(int player, int round,boolean isDown) {
        String winCond = "";

        switch (round) {
            case 0:
                winCond = "Must play two sets to go down\n";
                break;
            case 1:
                winCond = "Must play a set and a run to go down\n";
                break;
            case 2:
                winCond = "Must play two runs to go down\n";
                break;
            case 3:
                winCond = "Must play three sets to go down\n";
                break;
            case 4:
                winCond = "Must play two sets and a run to go down\n";
                break;
            case 5:
                winCond = "Must play two runs and a set to go down\n";
                break;
            case 6:
                winCond = "Must play three runs to go down\n";
                break;
        }
        System.out.println("Player " + player + " Turn\n");
        System.out.println(winCond);

        System.out.println("What would you like to do on your turn?\n");

        if(!isDown) {
            System.out.println("Set: Go Down for turn\n");
        }
        else
        System.out.println("Play: play cards\n");

        System.out.println("Swap: Swap two cards\nHand: View Hand\nField: View Field\nDone: End Turn\n");
        String option = scan.next();
        scan.nextLine();

        return option;
    }
      public Plays[] setCards(int round, Player player){

          Plays[] plays = null;

    switch(round)

        {
        case 0:
            System.out.println("Must play two sets to go down\n");
            plays = lay2Sets(player);
            break;
        case 1:
            System.out.println("Must play a set and a run to go down\n");
            plays = laySetRun(player);
            break;
        case 2:
            System.out.println("Must play two runs to go down\n");
            plays = lay2Runs(player);
            break;
        case 3:
            System.out.println("Must play three sets to go down\n");
            plays = threeSets(player);
            break;
        case 4:
            System.out.println("Must play two sets and a run to go down\n");
            plays = twoSetsAnRun(player);
            break;
        case 5:
            System.out.println("Must play two runs and a set to go down\n");
            plays = twoRunsAnSet(player);
            break;
        case 6:
            System.out.println("Must play three runs without a discard to go down\n");
            plays = threeRuns(player);
            break;
        }


          return plays;
    }

    public void swapCards(Player player){
        boolean done = false;
        int choice = 0;
        int[] cardIndex = new int[2];
        while(!done) {
            System.out.println(player.viewHand() + "\n");
            System.out.println("What card would you like to swap?");
            cardIndex[0] = scan.nextInt();
            scan.nextLine();
            System.out.println("With what card?");
            cardIndex[1] = scan.nextInt();
            scan.nextLine();
            player.swap(cardIndex[0], cardIndex[1]);

            System.out.println("Swap more cards? 1: yes, 2: no\n");
            choice = scan.nextInt();
            if(choice == 2){
                done = true;
            }
        }

    }

    public int discard(Player player){
        int selection = 0;
        System.out.println("Player " + player.getPlayerNum() + " what card would you like to discard?\n" + player.listCardPos());
        selection = scan.nextInt();

        return selection;
    }

    public ArrayList<ArrayList<Plays>> playCard(ArrayList<ArrayList <Plays>> field, Player player){
        int choice = 0;
        boolean done = false;
        while(!done) {
            System.out.println("Currently on the field\n");
            int pos = 0;
            while(pos < field.size()) {
                int i = 0;
                while(i < field.get(pos).size()) {
                    System.out.println(field.get(pos).get(i).toString());
                    i++;
                }
                pos++;
            }
            System.out.println(player.viewHand() + "\n");
            System.out.println("What card would you like to lay down? -1 to finish\n");
            choice = scan.nextInt();

            if(choice == -1){
                done = true;
            }
            else{
                System.out.println("Where would you like to place the card?\n");
                pos = 0;
                while(pos < field.size()) {
                    int i = 0;
                    while(i < field.get(pos).size()) {
                        System.out.println(field.get(pos).get(i).toString());
                        i++;
                    }
                    pos++;
                }
                System.out.println("Player: ");
                int playerPos = scan.nextInt();

                System.out.println("On play: ");
                int playPos = scan.nextInt();

                Card holdCard = player.playCard(choice);

               boolean isSet = field.get(playerPos).get(playPos).getSet();

                if(isSet){
                    boolean verify = field.get(playerPos).get(playPos).verifySet(holdCard.getFaceValue());

                    if(verify){
                        field.get(playerPos).get(playPos).addCard(holdCard);
                    }
                    else {
                        System.out.println("Invalid play on set");
                        player.drawCard(holdCard);
                    }
                }
                else if(field.get(playerPos).get(playPos).getRun()){
                    if(field.get(playerPos).get(playPos).getCard(0).getSuit().equals(holdCard.getSuit())) {
                        if (field.get(playerPos).get(playPos).getCard(0).getFaceValue() - 1 == holdCard.getFaceValue()) {
                            field.get(playerPos).get(playPos).addCard(holdCard);
                            field.get(playerPos).get(playPos).runSorter();
                            System.out.println("Add to front");
                        } else if (field.get(playerPos).get(playPos).getCard(field.get(playerPos).get(playPos).getSize() - 1).getFaceValue() + 1 == holdCard.getFaceValue()) {
                            field.get(playerPos).get(playPos).addCard(holdCard);
                            System.out.println("Add to rear");
                        }
                    }
                    else{
                        System.out.println("Invalid Play on Run");
                        player.drawCard(holdCard);
                    }
                }



                pos = 0;
                while(pos < field.size()) {
                    int i = 0;
                    while(i < field.get(pos).size()) {
                        System.out.println(field.get(pos).get(i).toString());
                        i++;
                    }
                    pos++;
                }
            }
        }
        return field;
    }

    private boolean isSet(Plays plays){
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
    private Plays[] lay2Sets(Player player){
        Plays[] plays = new Plays[2];

        for(int i = 0; i < plays.length; i++){
            plays[i] = makeSet(player);
        }

        boolean firstSet = isSet(plays[0]);
        boolean nextSet = isSet(plays[1]);

        if(!firstSet){
            if(plays[1] != null && plays[1].getSize() > 0){
                while(plays[1].getSize() != 0){
                    player.drawCard(plays[1].removeCard(0));
                }
            }
        }
        else if(!nextSet){
            if(plays[0] != null && plays[0].getSize() > 0){
                while(plays[0].getSize() != 0){
                    player.drawCard(plays[0].removeCard(0));
                }
            }
        }

        return plays;
    }

    private Plays[] laySetRun(Player player){
        Plays[] plays = new Plays[2];

        plays[0] = makeSet(player);
        plays[1] = makeRun(player);

        boolean isSet = isSet(plays[0]);
        boolean isRun = isRun(plays[1]);
        if(isSet && !isRun){
            System.out.println("Invalid Run, returning cards to hand");
            while(plays[0].getSize() != 0){
                player.drawCard(plays[0].removeCard(0));
            }
        }
        else if(isRun && !isSet){
            System.out.println("Invalid Set, returning cards to hand");
            while(plays[1].getSize() != 0){
                player.drawCard(plays[1].removeCard(0));
            }
        }

        return plays;
    }

    private Plays[] lay2Runs(Player player){
        Plays[] plays = new Plays[2];

        plays[0] = makeRun(player);
        plays[1] = makeRun(player);

        boolean isRun1 = isRun(plays[0]);
        boolean isRun2 = isRun(plays[1]);
        if(isRun1 && !isRun2){
            System.out.println("Invalid Second Run, returning cards to hand");
            while(plays[0].getSize() != 0){
                player.drawCard(plays[0].removeCard(0));
            }
        }
        else if(isRun2 && !isRun1){
            System.out.println("Invalid First Run, returning cards to hand");
            while(plays[1].getSize() != 0){
                player.drawCard(plays[1].removeCard(0));
            }
        }

        return plays;
    }

    private Plays[] threeSets(Player player){
        Plays[] plays = new Plays[3];

        plays[0] = makeSet(player);
        plays[1] = makeSet(player);
        plays[2] = makeSet(player);

        boolean isSet1 = isSet(plays[0]);
        boolean isSet2 = isSet(plays[1]);
        boolean isSet3 = isSet(plays[2]);
        boolean complete = false;

        if(isSet1 && isSet2 && isSet3){
            complete = true;
        }


        if(!complete) {
            System.out.println("Invalid play, returning cards to hand");
            if (isSet1) {
                while (plays[0].getSize() != 0) {
                    player.drawCard(plays[0].removeCard(0));
                }
            }

                if(isSet2) {
                while (plays[1].getSize() != 0) {
                    player.drawCard(plays[1].removeCard(0));
                 }
               }

            if(isSet3){
                while (plays[1].getSize() != 0) {
                    player.drawCard(plays[2].removeCard(0));
                }
            }
        }

        return plays;
    }

    private Plays[] twoSetsAnRun(Player player){
        Plays[] plays = new Plays[3];

        plays[0] = makeSet(player);
        plays[1] = makeSet(player);
        plays[2] = makeRun(player);

        boolean isSet1 = isSet(plays[0]);
        boolean isSet2 = isSet(plays[1]);
        boolean isRun = isRun(plays[2]);
        boolean complete = false;

        if(isSet1 && isSet2 && isRun){
            complete = true;
        }


        if(!complete) {
            System.out.println("Invalid play, returning cards to hand");
            if (isSet1) {
                while (plays[0].getSize() != 0) {
                    player.drawCard(plays[0].removeCard(0));
                }
            }

            if(isSet2) {
                while (plays[1].getSize() != 0) {
                    player.drawCard(plays[1].removeCard(0));
                }
            }

            if(isRun){
                while (plays[1].getSize() != 0) {
                    player.drawCard(plays[2].removeCard(0));
                }
            }
        }

        return plays;
    }

    private Plays[] twoRunsAnSet(Player player){
        Plays[] plays = new Plays[3];

        plays[0] = makeRun(player);
        plays[1] = makeRun(player);
        plays[2] = makeSet(player);

        boolean isRun1 = isRun(plays[0]);
        boolean isRun2 = isRun(plays[1]);
        boolean isSet = isSet(plays[2]);
        boolean complete = false;

        if(isRun1 && isRun2 && isSet){
            complete = true;
        }


        if(!complete) {
            System.out.println("Invalid play, returning cards to hand");
            if (isRun1) {
                while (plays[0].getSize() != 0) {
                    player.drawCard(plays[0].removeCard(0));
                }
            }

            if(isRun2) {
                while (plays[1].getSize() != 0) {
                    player.drawCard(plays[1].removeCard(0));
                }
            }

            if(isSet){
                while (plays[1].getSize() != 0) {
                    player.drawCard(plays[2].removeCard(0));
                }
            }
        }

        return plays;
    }

    private Plays[] threeRuns(Player player){
        Plays[] plays = new Plays[3];

        plays[0] = makeRun(player);
        plays[1] = makeRun(player);
        plays[2] = makeRun(player);

        boolean isRun1 = isRun(plays[0]);
        boolean isRun2 = isRun(plays[1]);
        boolean isRun3 = isRun(plays[2]);
        boolean complete = false;

        if(isRun1 && isRun2 && isRun3 && player.handSize() == 0){
            complete = true;
        }


        if(!complete) {
            if(player.handSize() > 0){
                System.out.println("Must not have any cards left over");
            }
            System.out.println("Invalid play, returning cards to hand");
            if (isRun1) {
                while (plays[0].getSize() != 0) {
                    player.drawCard(plays[0].removeCard(0));
                }
            }

            if(isRun2) {
                while (plays[1].getSize() != 0) {
                    player.drawCard(plays[1].removeCard(0));
                }
            }

            if(isRun3){
                while (plays[1].getSize() != 0) {
                    player.drawCard(plays[2].removeCard(0));
                }
            }
        }

        return plays;
    }

    private boolean isRun(Plays play){
        boolean isRun = false;

        if(play != null) {
            if (play.getSize() >= 4) {

                isRun = runChecker(play);
                System.out.println("Run Test Passed " + isRun);
            }
        }

        return isRun;
    }

    private Plays makeRun(Player player){
        ArrayList<Card> play = new ArrayList<>();
        int runCount = 0;
        String suiteCheck = "";
        boolean done = false;
        boolean isRun = false;

        int choice = 0;
        while(!done){
            System.out.println(player.viewHand() + "\n");
            System.out.println("What is the first card to your run? -1 to exit");
            choice = scan.nextInt();

            if(choice != -1){

                if(suiteCheck == ""){
                    suiteCheck = player.getFace(choice);
                    play.add(player.playCard(choice));
                    runCount++;
                }
                else if(!player.getFace(choice).equals(suiteCheck)){
                    while(!done && !player.getFace(choice).equals(suiteCheck)) {
                        System.out.println("The chosen card is of the wrong suite. Please choose another card or -1 to quit");
                        choice = scan.nextInt();

                        if(choice == -1){
                            done = true;
                        }
                        else if(player.getFace(choice).equals(suiteCheck)){
                            play.add(player.playCard(choice));
                            runCount++;
                        }
                    }
                }
                else{
                    play.add(player.playCard(choice));
                        runCount++;
                    }
                }
            else{
                done = true;
            }

            if(done && play.size() < 4){
                System.out.println("Your run is to short, would you like to add another card or cancel run?\nA: to add\nC: to Cancel\n");
                String option = scan.next();

                switch(option.toLowerCase()){
                    case "a":
                        done = false;
                        System.out.println("Currently in the run\n");
                        for(Card card: play){
                            System.out.println(card + "\n");
                        }
                        break;
                    case "c":
                        while(play.size() != 0){
                            player.drawCard(play.remove(0));
                            runCount--;
                        }
                        break;
                }
            }
            else {
                if (done && play.size() >= 3) {

                    play = runSorter(play);

                    if (runChecker(play)) {
                        isRun = true;

                        int pos = 0;
                        while(pos < play.size()){
                            System.out.println(play.get(pos));
                            pos++;
                        }
                    }
                    if (!isRun) {
                        System.out.println("This Run is not valid!!");
                        done = false;
                        while (play.size() != 0) {
                            player.drawCard(play.remove(0));
                            runCount = 0;
                        }
                    }
                }
            }

        }


        Plays tmpPlay = null;
        if(isRun){
            tmpPlay = new Plays(play);
            tmpPlay.isRun(true);
            for(int i = 0; i < play.size(); i++){
                System.out.println("Run Holds " + play.get(i));
            }
        }


        return tmpPlay;
    }


    private ArrayList<Card> runSorter(ArrayList<Card> play){
        int i = 0;
        boolean sorted = false;

        while(i < play.size() && !sorted) {
            int test = 0;
            for(int pos = 1; pos < play.size() - 1; pos++){

                if(play.get(pos - 1).getFaceValue() > play.get(pos).getFaceValue()){
                    Collections.swap(play, pos - 1, pos);
                    test++;
                }
                else if(play.get(pos + 1).getFaceValue() < play.get(pos).getFaceValue()){
                    Collections.swap(play, pos + 1, pos);
                    test++;
                }
            }
            if(test == 0){
                sorted = true;
            }
            i++;
        }

        return play;
    }
    private boolean runChecker(ArrayList<Card> play){
        int pos = 0;
        boolean checker = true;
        while (pos < play.size() - 1) {
            if (play.get(pos).getFaceValue() + 1 != play.get(pos + 1).getFaceValue()) {
                checker = false;
            }
            pos++;
        }
        return checker;
    }

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
    private Plays makeSet(Player player){
        int checkValue = 0;
        int selection = 0;
        int setCount = 0;
        ArrayList<Card> play = new ArrayList<>();

        boolean done = false;
        boolean isSet = false;


        while(!done) {
            System.out.println(player.viewHand() + "\n");
            System.out.println("Please select a card to be in your set, use -1 when done\n");
            selection = scan.nextInt();
            if (selection != -1) {

                if (checkValue == 0) {
                    checkValue = player.getCardValue(selection);
                    play.add(player.playCard(selection));
                } else if (checkValue != player.getCardValue(selection)) {
                    while (!done && checkValue != player.getCardValue(selection)) {
                        System.out.println("That card does not match " + checkValue + ", please choose a card that matches " + checkValue + " or cancel with -1");
                        selection = scan.nextInt();

                        if (selection == -1) {
                            done = true;
                        }
                    }
                } else if (player.getCardValue(selection) == checkValue) {
                    play.add(player.playCard(selection));
                    setCount++;
                }
            }
            else
                done = true;

            if(done && setCount < 2){
                System.out.println("This is not enough cards to be a set.\nPress A: to add a card\n Press C: to cancel the set");
                String choice = scan.next();
                switch (choice.toLowerCase()){
                    case "a":
                        done = false;
                        break;
                    case "c":
                        while(play.size() != 0){
                            player.drawCard(play.remove(0));
                        }
                }
            }
            else
                isSet = true;
        }



        Plays tmpPlay = null;
        if(isSet){
            tmpPlay = new Plays(play);
            tmpPlay.isSet(true);
        }

        return tmpPlay;
    }
}
