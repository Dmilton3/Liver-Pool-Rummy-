//************************************************************
//  LiverPoolRummy GUI          Author: Dewey Milton
// Version : Test
//************************************************************

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;


/*
   LiverPool Rummy GUI Class MainFrame
 */
public class MainFrame extends JPanel {

    private int WIDTH;
    private int HEIGHT;
    private JPanel panel;
    JPanel topPanel = new JPanel();
    JTextPane winLoseBox = new JTextPane();
    JButton hitButton = new JButton();
    JButton dealButton = new JButton();
    private ArrayList<ArrayList<Plays>> field;
    private ArrayList<JLabel> hand;
    private JLabel deckLabel;
    private JLabel dCardLabel;
    private JLabel p1ScoreName;
    private JTextField p1Score;
    private int p1Scores;
    private String p1Name;
    private JLabel p2ScoreName;
    private JTextField p2Score;
    private int p2Scores;
    private String p2Name;
    private JLabel p3ScoreName;
    private JTextField p3Score;
    private int p3Scores;
    private String p3Name;
    private JLabel p4ScoreName;
    private JTextField p4Score;
    private int p4Scores;
    private String p4Name;
    private int totalPlayers;
    private int numPlayers;
    private LiverPool game;
    private JScrollPane handPanel;
    private JPanel bottPanel;
    private JPanel scorePanel;
    private JPanel menu;
    private PlayerPanel p1Field;
    private PlayerPanel p2Field;
    private PlayerPanel p3Field;
    private PlayerPanel p4Field;
    private Player currentPlayer;
    private boolean gameOver;
    private boolean roundOver;
    private boolean mayI;
    private int butCount;
    private boolean turnOver;
    private boolean onField;
    private int round;
    private int globalPos;
    private boolean draw;
    private JTextArea playerUp;
    private JPanel deckPanel;
    private JPanel butPanel;
    private Timer time;
    private Player tmp;
    private int mayPhase;
    private int mayNoPhase;
    private int drawPhase;
    private int turnPhase;
    private int layPhase;
    private int playPhase;
    private int phase;
    private int discPhase;
    private int turnEndPhase;
    private boolean isNext;
    private MouseAdapter mouse;
    private JPanel p1menu;
    private JButton p11Button;
    private JButton p12Button;
    private JButton p13Button;
    private JPanel p2menu;
    private JButton p21Button;
    private JButton p22Button;
    private JButton p23Button;
    private JPanel p4menu;
    private JButton p41Button;
    private JButton p42Button;
    private JButton p43Button;
    private JPanel p3menu;
    private JButton p31Button;
    private JButton p32Button;
    private JButton p33Button;
    private JLabel p1Label;
    private JLabel p2Label;
    private JLabel p3Label;
    private JLabel p4Label;
    private boolean cancel;





    /***********************************************************
     * Constructs the screen.
     ***********************************************************/
    public MainFrame() {





        WIDTH = 1200;
        HEIGHT = 1200;
        panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        panel.setBackground(new Color(0, 122, 0));


        String options = JOptionPane.showInputDialog("How many players? 2,3,4");

        totalPlayers = Character.getNumericValue(options.charAt(0));


            numPlayers = totalPlayers;
            isNext = false;
            phase = 0;
            layPhase = 4;
            mayPhase = 0;
            mayNoPhase = 1;
            drawPhase = 2;
            turnPhase = 3;
            turnEndPhase = 5;
            playPhase = 7;
            tmp = null;
            discPhase = 6;
            draw = false;
            globalPos = 0;
            this.round = 0;
            onField = false;
            turnOver = false;
            roundOver = false;
            gameOver = false;
            this.mayI = false;
            p1Name = "DrArmSchlong";
            p2Name = "StoneCold";
            p3Name = "MrDixIt";
            p4Name = "Jim";

            repaint();
            game = new LiverPool(totalPlayers);
            field = new ArrayList<ArrayList<Plays>>(totalPlayers);

            for (int fields = 0; fields < numPlayers; fields++) {
                field.add(new ArrayList<Plays>());
            }

            GridBagConstraints c = new GridBagConstraints();
            bottPanel = new JPanel();
            bottPanel.setLayout(new GridBagLayout());

            bottPanel.setPreferredSize(new Dimension(1200, 110));
            bottPanel.setVisible(true);
            panel.add(bottPanel, BorderLayout.SOUTH);
            handPanel = new JScrollPane();
            handPanel.setSize(965, 110);
            handPanel.setPreferredSize(new Dimension(965, 110));
            handPanel.setVisible(true);

            c.fill = c.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 0;
            c.gridwidth = 2;
            bottPanel.add(handPanel, c);

                p1Scores = 0;
                p2Scores = 0;
                p3Scores = 0;
                p4Scores = 0;

            scorePanel = new JPanel(new GridLayout(2, 0));
            scorePanel.setBackground(Color.LIGHT_GRAY);
            scorePanel.setPreferredSize(new Dimension(400, 110));
            scorePanel.setVisible(true);
            c.gridx = 2;
            c.gridy = 0;
            c.gridwidth = 1;
            bottPanel.add(scorePanel, c);

            p1ScoreName = new JLabel(p1Name);

            p1Score = new JTextField(" " + p1Scores);
            p1Score.setEditable(false);
            p2ScoreName = new JLabel(p2Name);
            p2Score = new JTextField(" " + p2Scores);
            p2Score.setEditable(false);
            p3ScoreName = new JLabel(p3Name);
            p3Score = new JTextField(" " + p3Scores);
            p3Score.setEditable(false);
            p4ScoreName = new JLabel(p4Name);
            p4Score = new JTextField(" " + p4Scores);
            p4Score.setEditable(false);
            scorePanel.add(p1ScoreName);
            scorePanel.add(p1Score);
            scorePanel.add(p2ScoreName);
            scorePanel.add(p2Score);
            scorePanel.add(p3ScoreName);
            scorePanel.add(p3Score);
            scorePanel.add(p4ScoreName);
            scorePanel.add(p4Score);


            menu = new JPanel(new FlowLayout(FlowLayout.LEADING, 10, 10));
            menu.setPreferredSize(new Dimension(230, HEIGHT));
            menu.setVisible(true);
            menu.setBackground(new Color(0, 122, 0));
            panel.add(menu, BorderLayout.LINE_END);


            topPanel.setBackground(new Color(0, 122, 0));
            topPanel.setLayout(new GridLayout(2, 0));
            panel.add(topPanel, BorderLayout.CENTER);

            p1Field = new PlayerPanel();
            p1Field.setBackground(new Color(0, 122, 0));
            topPanel.add(p1Field);

            c = new GridBagConstraints();

            p1Label = new JLabel("Player 1");
            c.gridx = 0;
            c.gridy = 0;
            c.insets = new Insets(5, 5, 5, 5);
            p1Field.setMenuComp(p1Label, c);
            p11Button = new JButton();
            p11Button.setText("This is p11");
            p11Button.addActionListener(new playOn11());
            p11Button.setVisible(false);
            p11Button.setPreferredSize(new Dimension(100, 30));
            c.gridx = 1;
            c.gridy = 0;
            c.insets = new Insets(5, 5, 5, 5);
            p1Field.setMenuComp(p11Button, c);
            p12Button = new JButton();
            p12Button.addActionListener(new playOn12());
            p12Button.setVisible(false);
            p12Button.setText("THis is p12");
            p12Button.setPreferredSize(new Dimension(100, 30));
            p12Button.setMargin(new Insets(2, 2, 2, 2));
            c.gridx = 1;
            c.gridy = 1;
            c.insets = new Insets(5, 5, 5, 5);
            p1Field.setMenuComp(p12Button, c);
            p13Button = new JButton();
            p13Button.setText("This is p13");
            p13Button.addActionListener(new playOn13());
            p13Button.setPreferredSize(new Dimension(100, 30));
            p13Button.setMargin(new Insets(5, 5, 5, 5));
            p13Button.setVisible(false);
            c.gridx = 1;
            c.gridy = 2;
            c.insets = new Insets(5, 5, 5, 5);
            p1Field.setMenuComp(p13Button, c);

            p2Field = new PlayerPanel();
            p2Field.setBackground(new Color(0, 122, 0));
            topPanel.add(p2Field);

            p2Label = new JLabel("Player 2");
            c.gridx = 0;
            c.gridy = 0;
            c.insets = new Insets(5, 5, 5, 5);
            p2Field.setMenuComp(p2Label, c);
            p21Button = new JButton();
            p21Button.setText("This is p21");
            p21Button.addActionListener(new playOn21());
            p21Button.setVisible(false);
            p21Button.setPreferredSize(new Dimension(100, 30));
            c.gridx = 1;
            c.gridy = 0;
            c.insets = new Insets(5, 5, 5, 5);
            p2Field.setMenuComp(p21Button, c);
            p22Button = new JButton();
            p22Button.addActionListener(new playOn22());
            p22Button.setVisible(false);
            p22Button.setText("THis is S2");
            p22Button.setPreferredSize(new Dimension(100, 30));
            p22Button.setMargin(new Insets(2, 2, 2, 2));
            c.gridx = 1;
            c.gridy = 1;
            c.insets = new Insets(5, 5, 5, 5);
            p2Field.setMenuComp(p22Button, c);
            p23Button = new JButton();
            p23Button.setText("This is p23");
            p23Button.addActionListener(new playOn23());
            p23Button.setPreferredSize(new Dimension(100, 30));
            p23Button.setMargin(new Insets(5, 5, 5, 5));
            p23Button.setVisible(false);
            c.gridx = 1;
            c.gridy = 2;
            c.insets = new Insets(5, 5, 5, 5);
            p2Field.setMenuComp(p23Button, c);

            p3Field = new PlayerPanel();
            p3Field.setBackground(new Color(0, 122, 0));
            topPanel.add(p3Field);

            p3Label = new JLabel("Player 3");
            c.gridx = 0;
            c.gridy = 0;
            c.insets = new Insets(5, 5, 5, 5);
            p3Field.setMenuComp(p3Label, c);
            p31Button = new JButton();
            p31Button.setText("This is p31");
            p31Button.addActionListener(new playOn31());
            p31Button.setVisible(false);
            p31Button.setPreferredSize(new Dimension(100, 30));
            c.gridx = 1;
            c.gridy = 0;
            c.insets = new Insets(5, 5, 5, 5);
            p3Field.setMenuComp(p31Button, c);
            p32Button = new JButton();
            p32Button.addActionListener(new playOn32());
            p32Button.setVisible(false);
            p32Button.setText("THis is p32");
            p32Button.setPreferredSize(new Dimension(100, 30));
            p32Button.setMargin(new Insets(2, 2, 2, 2));
            c.gridx = 1;
            c.gridy = 1;
            c.insets = new Insets(5, 5, 5, 5);
            p3Field.setMenuComp(p32Button, c);
            p33Button = new JButton();
            p33Button.setText("This is 33");
            p33Button.addActionListener(new playOn33());
            p33Button.setPreferredSize(new Dimension(100, 30));
            p33Button.setMargin(new Insets(5, 5, 5, 5));
            p33Button.setVisible(false);
            c.gridx = 1;
            c.gridy = 2;
            c.insets = new Insets(5, 5, 5, 5);
            p3Field.setMenuComp(p33Button, c);

            p4Field = new PlayerPanel();
            p4Field.setBackground(new Color(0, 122, 0));
            topPanel.add(p4Field);

            c = new GridBagConstraints();
            p4menu = new JPanel(new GridBagLayout());
            p4menu.setBackground(new Color(0, 122, 0));
            p4Label = new JLabel("Player 4");
            c.gridx = 0;
            c.gridy = 0;
            c.insets = new Insets(5, 5, 5, 5);
            p4Field.setMenuComp(p4Label, c);
            p41Button = new JButton();
            p41Button.setText("This is p41");
            p41Button.addActionListener(new playOn41());
            p41Button.setVisible(false);
            p41Button.setPreferredSize(new Dimension(100, 30));
            c.gridx = 1;
            c.gridy = 0;
            c.insets = new Insets(5, 5, 5, 5);
            p4Field.setMenuComp(p41Button, c);
            p42Button = new JButton();
            p42Button.addActionListener(new playOn42());
            p42Button.setVisible(false);
            p42Button.setText("THis is p42");
            p42Button.setPreferredSize(new Dimension(100, 30));
            p42Button.setMargin(new Insets(2, 2, 2, 2));
            c.gridx = 1;
            c.gridy = 1;
            c.insets = new Insets(5, 5, 5, 5);
            p4Field.setMenuComp(p42Button, c);
            p43Button = new JButton();
            p43Button.setText("This is p43");
            p43Button.addActionListener(new playOn43());
            p43Button.setPreferredSize(new Dimension(100, 30));
            p43Button.setMargin(new Insets(5, 5, 5, 5));
            p43Button.setVisible(false);
            c.gridx = 1;
            c.gridy = 2;
            c.insets = new Insets(5, 5, 5, 5);
            p4Field.setMenuComp(p43Button, c);

            winLoseBox.setEditable(false);
            winLoseBox.setPreferredSize(new Dimension(230, 400));
            winLoseBox.setFont(new java.awt.Font
                    ("Helvetica Bold", 1, 20));

            menu.add(winLoseBox);

            deckPanel = new JPanel(new BorderLayout());
            deckPanel.setPreferredSize(new Dimension(230, 100));
            deckPanel.setVisible(true);
            deckPanel.setBackground(new Color(0, 122, 0));
            menu.add(deckPanel);

            butPanel = new JPanel(new GridLayout(1, 0));
            butPanel.setPreferredSize(new Dimension(230, 20));
            butPanel.setVisible(true);
            butPanel.setBackground(new Color(0, 122, 0));
            menu.add(butPanel);

            dealButton.setText(" Waiting");
            dealButton.addActionListener(new dealButton());
            dealButton.setEnabled(true);
            dealButton.setPreferredSize(new Dimension(25, 35));
            dealButton.setMargin(new Insets(5, 5, 5, 5));
            butPanel.add(dealButton);

            hitButton.setText("  Waiting");
            hitButton.addActionListener(new hitButton());
            hitButton.setEnabled(true);
            hitButton.setPreferredSize(new Dimension(25, 35));
            hitButton.setMargin(new Insets(5, 5, 5, 5));
            butPanel.add(hitButton);

            deckLabel = new JLabel(new ImageIcon("C:\\Users\\ASUS\\Desktop\\JavaClass\\LiverPool\\CardBack1 - Copy.png"));
            dCardLabel = new JLabel(new ImageIcon("C:\\Users\\ASUS\\Desktop\\JavaClass\\LiverPool\\CardBack1 - Copy.png"));
            dCardLabel.setVisible(false);
            deckPanel.add(deckLabel, BorderLayout.WEST);
            deckPanel.add(dCardLabel, BorderLayout.CENTER);

            playerUp = new JTextArea("          ");
            playerUp.setEditable(false);
            playerUp.setPreferredSize(new Dimension(60, 30));
            menu.add(playerUp);





            time = new Timer(100, new ActionListener() {
                @Override
            /*
             Timer loop to allow the game process to be looped
             */
                public void actionPerformed(ActionEvent e) {


                    if (roundOver) {
                        roundReset();
                    }

                    if (phase == mayPhase) {
                        checkDiscImage();
                    }

                    playerUp.setText("Player " + currentPlayer.getPlayerNum());


                    winLoseBox.setText("Round " + round + "\n" + "Player " + currentPlayer.getPlayerNum() + "\n" + game.getWinCondition());
                    repaint();

                    System.out.println("------------Round over is " + roundOver + " In Phase " + phase + " Is Next " + isNext + "----------Player " + currentPlayer.getPlayerNum());
                    //May I Step


                    switch (phase) {
                        case 0:
                            if (game.getPile().size() != 0) {
                                System.out.println("In MayI");
                                winLoseBox.setText("Player " + currentPlayer.getPlayerNum() + " Want the discard?");
                                dealButton.setVisible(true);
                                dealButton.setText("Yes");
                                hitButton.setText("NO");
                                repaint();
                                System.out.println("Asking players mayI");
                            } else if (game.getPile().size() == 0) {
                                currentPlayer.drawCard(game.drawCard());
                                phase = turnPhase;
                            }
                            break;
                        case 1:
                            if (isNext) {
                                nextCall();
                            } else {
                                handPanel.setVisible(true);
                                hitButton.setText("No");
                                repaint();
                                endNext();
                            }
                            break;
                        case 2:
                            if (isNext) {
                                nextCall();
                            } else {
                                drawStep();
                                globalPos = 0;
                            }
                            break;
                        case 3:
                            System.out.println("Starting to take Turn Phase");
                            resetButtons();
                            tmp = null;
                            takeTurn();
                            break;
                        case 4:
                            goDown();
                            break;
                        case 5:
                            System.out.println("In turn over phase " + phase);
                            phase = discPhase;
                            break;
                        case 6:
                            if (isNext) {
                                System.out.println("++++++++++++In Section 6 going to nextCall!+++++++++++");
                                nextCall();
                            } else
                                discardStep();
                            break;
                        case 7:
                            fieldButSetup();
                    }

                    checkHand();

                    if (cancel) {
                        cancPlay();
                    }

                    if (gameOver) {
                        Player[] outCome = game.getOutCome();

                        winLoseBox.setText("Winner is " + outCome[0].getPlayerNum() + " Score of " + outCome[0].getScore() + " FetalBomb is " + outCome[1].getPlayerNum() + " Score of "
                                + outCome[1].getScore());
                    }


                }
            });
            startGame();

    }

    private void checkHand(){
        if (currentPlayer.getHand().size() == 0) {
            System.out.println("in checkHAnd");
            if (round == 7) {
                gameOver = true;
                game.endGame();
            } else {
                roundOver = true;
                scoreUpdate();
                resetField();
            }
        }
    }

    /*
       Discard step method, changes text of buttons
     */
    private void discardStep() {
        System.out.println("In discard Step");
        dealButton.setVisible(false);
        hitButton.setText("Discard");
        repaint();
        winLoseBox.setText("Please select a card to discard.");
    }

    /***********************************************************
     * Displays the main application frame.
     ***********************************************************/
    public void display() {
        JFrame myFrame = new JFrame("LiverPool Rummy");
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        myFrame.setPreferredSize(new Dimension(WIDTH + 300, HEIGHT + 100));
        myFrame.getContentPane().add(panel);


        myFrame.pack();

        myFrame.setVisible(true);


    }




    /***********************************************************
     * Represents action listener for dealButton.
     ***********************************************************/
    private class dealButton implements ActionListener {
        /*****************************************************
         * This Button does many many things, read through the phases to find out what
         *****************************************************/
        public void actionPerformed(ActionEvent e) {


            System.out.println("In draw phase ");

            if (phase == mayPhase || phase == mayNoPhase) {
                viewHand();
                mayItrue();
            } else if (phase == drawPhase) {
                Card tmpCard = game.drawCard();
                viewHand();
                winLoseBox.setText("Player " + currentPlayer.getPlayerNum() + "\n Drew " + tmpCard.toString());
                currentPlayer.drawCard(tmpCard);
                phase = turnPhase;
            } else if (phase == turnPhase && !currentPlayer.isDown()) {
                phase = layPhase;
                goDown();
            } else if(phase == turnPhase && currentPlayer.isDown()){
              phase = playPhase;
                fieldButSetup();
              }else if (phase == layPhase) {
                cancel = true;
                System.out.println("Cancel is " + cancel);
                phase = turnPhase;
            }else if(phase == playPhase){
                viewHand();
                hitButton.setVisible(true);
                hitButton.setEnabled(true);
                resetButtons();
                repaint();
                phase = turnPhase;
            }
        }
    }

    /*
        Method to adjust the discard image every change
     */
    private void checkDiscImage() {
        if (game.getPile().size() != 0) {
            deckPanel.remove(dCardLabel);
            dCardLabel = new JLabel(new ImageIcon(game.getPile().showPlayable().getImageName()));
            dCardLabel.setVisible(true);
            deckPanel.add(dCardLabel);
            repaint();

        } else {
            dCardLabel.setVisible(false);
            deckPanel.remove(dCardLabel);
            deckPanel.revalidate();
        }
    }

    /*
       Refreshes players hand on the screen
     */
    private void viewHand() {


        DefaultListModel<ImageIcon> model = new DefaultListModel<>();

        for (int i = 0; i < currentPlayer.handSize(); i++) {
            model.addElement(currentPlayer.viewCard(i).getImage());
        }

        currentPlayer.updateModel(model);
        mouse = new ReorderListener(currentPlayer.getImages(), currentPlayer.getHand());
        currentPlayer.getImages().addMouseListener(mouse);
        currentPlayer.getImages().addMouseMotionListener(mouse);
        currentPlayer.getImages().setBackground(Color.green);
        currentPlayer.getImages().setSelectionBackground(Color.BLUE);
        handPanel.setViewportView(currentPlayer.getImages());


    }

    /*
       setup for the hand panel Jlist and swap mouseListener
     */
    private void setupPanel() {
        mouse = new ReorderListener(currentPlayer.getImages(), currentPlayer.getHand());
        currentPlayer.getImages().addMouseListener(mouse);
        currentPlayer.getImages().addMouseMotionListener(mouse);
        handPanel.add(currentPlayer.getImages());
        handPanel.setViewportView(currentPlayer.getImages());

    }


    /***********************************************************
     * Represents an action listener for hitButton.
     ***********************************************************/
    private class hitButton implements ActionListener {
        /*****************************************************
         * Yet another extremely long button that helps navigate the player through phases
         *
         * @param e Hit button pressed
         *****************************************************/
        public void actionPerformed(ActionEvent e) {

            if (phase == mayPhase) {
                System.out.println("At beginning of no MayI");
                viewHand();
                tmp = currentPlayer;
                currentPlayer = game.nextTurn();
                isNext = true;

                globalPos++;
                phase = mayNoPhase;
                System.out.println("GOing to mayNoPhase ");

            } else if (phase == mayNoPhase && globalPos == totalPlayers) {
                viewHand();
                        System.out.println("At end of no may i");
                phase = drawPhase;
                isNext = true;
                currentPlayer = tmp;
            } else if (phase == discPhase && isNext) {
                viewHand();
                isNext = false;
                phase = mayPhase;
                currentPlayer = game.nextTurn();
                endNext();
            } else if (isNext) {
                viewHand();
                isNext = false;
                endNext();
            } else if (phase == mayNoPhase && globalPos < totalPlayers) {
                viewHand();
                System.out.println("In middle may i");
                currentPlayer = game.nextTurn();
                isNext = true;
                globalPos++;
            } else if (phase == drawPhase) {
                viewHand();
                currentPlayer.drawCard(game.getPile().retrieve());
                checkDiscImage();
                phase = turnPhase;
            } else if (phase == turnPhase) {
                viewHand();
                turnOver = true;
                System.out.println("Turn Over " + turnOver);
                phase = turnEndPhase;
            } else if (phase == discPhase && !isNext) {
                int index = currentPlayer.getImages().getSelectedIndex();
                ImageIcon tmp = currentPlayer.getImages().getModel().getElementAt(index);
                for (int i = 0; i < currentPlayer.getHand().size(); i++) {
                    if (tmp.equals(currentPlayer.getHand().get(i).getImage())) {
                        game.getPile().discard(currentPlayer.getHand().remove(i));
                    }
                }
                viewHand();
                isNext = true;
            }else if(phase == layPhase){
                System.out.println("In Lay Phase Button, Attempting to place on field");
                if(field.get(currentPlayer.getPlayerNum()) != null){

                    switch(round){
                        case 0:
                            field.get(currentPlayer.getPlayerNum()).get(0).isSet(game.isSet(field.get(currentPlayer.getPlayerNum()).get(0)));
                            field.get(currentPlayer.getPlayerNum()).get(1).isSet(game.isSet(field.get(currentPlayer.getPlayerNum()).get(1)));
                            if(field.get(currentPlayer.getPlayerNum()).get(0).getSet() && field.get(currentPlayer.getPlayerNum()).get(1).getSet() ){
                                System.out.println("Placing 2 sets");
                                placeOnField();
                                phase = turnPhase;
                            }
                            else{
                                retToHand();
                                winLoseBox.setText("Invalid Plays");
                                phase = layPhase;
                            }

                            break;
                        case 1:
                            field.get(currentPlayer.getPlayerNum()).get(0).isSet(game.isSet(field.get(currentPlayer.getPlayerNum()).get(0)));
                            field.get(currentPlayer.getPlayerNum()).get(1).runSorter();
                            field.get(currentPlayer.getPlayerNum()).get(1).isRun(game.isRun(field.get(currentPlayer.getPlayerNum()).get(1)));

                            if(field.get(currentPlayer.getPlayerNum()).get(0).getSet() && field.get(currentPlayer.getPlayerNum()).get(1).getRun()){
                                placeOnField();
                                phase = turnPhase;
                            }
                            else {
                                retToHand();
                                winLoseBox.setText("Invalid Plays");
                                phase = layPhase;
                            }
                            break;
                        case 2:
                            field.get(currentPlayer.getPlayerNum()).get(0).runSorter();
                            field.get(currentPlayer.getPlayerNum()).get(0).isRun(game.isRun(field.get(currentPlayer.getPlayerNum()).get(0)));
                            field.get(currentPlayer.getPlayerNum()).get(1).runSorter();
                            field.get(currentPlayer.getPlayerNum()).get(1).isRun(game.isRun(field.get(currentPlayer.getPlayerNum()).get(1)));

                            if(field.get(currentPlayer.getPlayerNum()).get(0).getRun() && field.get(currentPlayer.getPlayerNum()).get(1).getRun()){
                                placeOnField();
                                phase = turnPhase;
                            }
                            else {
                                retToHand();
                                winLoseBox.setText("Invalid Plays");
                                phase = layPhase;
                            }
                            break;
                        case 3:
                            field.get(currentPlayer.getPlayerNum()).get(0).isSet(game.isSet(field.get(currentPlayer.getPlayerNum()).get(0)));
                            field.get(currentPlayer.getPlayerNum()).get(1).isSet(game.isSet(field.get(currentPlayer.getPlayerNum()).get(1)));
                            field.get(currentPlayer.getPlayerNum()).get(1).isSet(game.isSet(field.get(currentPlayer.getPlayerNum()).get(2)));
                             if(field.get(currentPlayer.getPlayerNum()).get(0).getRun() && field.get(currentPlayer.getPlayerNum()).get(1).getSet() && field.get(currentPlayer.getPlayerNum()).get(2).getSet()){
                                 placeOnField();
                                 phase = turnPhase;
                             }
                             else{
                                 retToHand();
                                 winLoseBox.setText("Invalid Plays");
                                 phase = layPhase;
                                 phase = turnPhase;
                             }
                            break;
                        case 4:
                            field.get(currentPlayer.getPlayerNum()).get(0).isSet(game.isSet(field.get(currentPlayer.getPlayerNum()).get(0)));
                            field.get(currentPlayer.getPlayerNum()).get(1).isSet(game.isSet(field.get(currentPlayer.getPlayerNum()).get(1)));
                            field.get(currentPlayer.getPlayerNum()).get(2).runSorter();
                            field.get(currentPlayer.getPlayerNum()).get(2).isRun(game.isRun(field.get(currentPlayer.getPlayerNum()).get(2)));

                             if(field.get(currentPlayer.getPlayerNum()).get(0).getRun() && field.get(currentPlayer.getPlayerNum()).get(1).getSet() && field.get(currentPlayer.getPlayerNum()).get(2).getRun()){
                                 placeOnField();
                                 phase = turnPhase;
                             }
                             else{
                                 retToHand();
                                 winLoseBox.setText("Invalid Plays");
                                 phase = layPhase;
                             }
                            break;
                        case 5:
                            field.get(currentPlayer.getPlayerNum()).get(0).runSorter();
                            field.get(currentPlayer.getPlayerNum()).get(0).isRun(game.isRun(field.get(currentPlayer.getPlayerNum()).get(0)));
                            field.get(currentPlayer.getPlayerNum()).get(1).runSorter();
                            field.get(currentPlayer.getPlayerNum()).get(1).isRun(game.isRun(field.get(currentPlayer.getPlayerNum()).get(1)));

                            field.get(currentPlayer.getPlayerNum()).get(2).isSet(game.isSet(field.get(currentPlayer.getPlayerNum()).get(2)));
                            if(field.get(currentPlayer.getPlayerNum()).get(0).getRun() && field.get(currentPlayer.getPlayerNum()).get(1).getRun() && field.get(currentPlayer.getPlayerNum()).get(2).getSet()){
                                placeOnField();
                                phase = turnPhase;
                            }
                            else{
                                retToHand();
                                winLoseBox.setText("Invalid Plays");
                                phase = layPhase;
                            }
                            break;
                        case 6:
                            field.get(currentPlayer.getPlayerNum()).get(0).runSorter();
                            field.get(currentPlayer.getPlayerNum()).get(0).isRun(game.isRun(field.get(currentPlayer.getPlayerNum()).get(0)));
                            field.get(currentPlayer.getPlayerNum()).get(1).runSorter();
                            field.get(currentPlayer.getPlayerNum()).get(1).isRun(game.isRun(field.get(currentPlayer.getPlayerNum()).get(1)));
                            field.get(currentPlayer.getPlayerNum()).get(2).runSorter();
                            field.get(currentPlayer.getPlayerNum()).get(2).isRun(game.isRun(field.get(currentPlayer.getPlayerNum()).get(2)));

                             if(field.get(currentPlayer.getPlayerNum()).get(0).getRun() && field.get(currentPlayer.getPlayerNum()).get(1).getRun() && field.get(currentPlayer.getPlayerNum()).get(2).getSet()){
                                 placeOnField();
                                 phase = turnPhase;
                             }
                             else{
                                 retToHand();
                                 winLoseBox.setText("Invalid Plays");
                                 phase = layPhase;
                             }

                            break;
                    }

                }


            }


        }
    }

    /*
       method to refresh the field screen of cards placed on the table
     */
    private void placeOnField(){
        currentPlayer.setIsDown(true);
        System.out.println("In field builder with\n");
        switch(currentPlayer.getPlayerNum()){
            case 0:
                for(int i = 0; i < field.get(currentPlayer.getPlayerNum()).size(); i++){

                        DefaultListModel<ImageIcon> model = new DefaultListModel<>();
                        for(int j = 0; j < field.get(currentPlayer.getPlayerNum()).get(i).getSize(); j++){
                            model.addElement(field.get(currentPlayer.getPlayerNum()).get(i).getCard(j).getImage());
                        }
                        p1Field.setField(model, i);
                }
                break;
            case 1:
                for(int i = 0; i < field.get(currentPlayer.getPlayerNum()).size(); i++){

                        DefaultListModel<ImageIcon> model = new DefaultListModel<>();
                        for(int j = 0; j < field.get(currentPlayer.getPlayerNum()).get(i).getSize(); j++){
                            model.addElement(field.get(currentPlayer.getPlayerNum()).get(i).getCard(j).getImage());
                        }
                        p2Field.setField(model, i);

                }
                break;
            case 2:
                for(int i = 0; i < field.get(currentPlayer.getPlayerNum()).size(); i++){

                        DefaultListModel<ImageIcon> model = new DefaultListModel<>();
                        for(int j = 0; j < field.get(currentPlayer.getPlayerNum()).get(i).getSize(); j++){
                            model.addElement(field.get(currentPlayer.getPlayerNum()).get(i).getCard(j).getImage());
                        }
                        p3Field.setField(model, i);

                }
                break;
            case 3:
                for(int i = 0; i < field.get(currentPlayer.getPlayerNum()).size(); i++){

                        DefaultListModel<ImageIcon> model = new DefaultListModel<>();
                        for(int j = 0; j < field.get(currentPlayer.getPlayerNum()).get(i).getSize(); j++){
                            model.addElement(field.get(currentPlayer.getPlayerNum()).get(i).getCard(j).getImage());
                        }
                        p4Field.setField(model, i);

                }
                break;
        }

            resetButtons();
        phase = turnPhase;
        hitButton.setVisible(true);
        viewHand();
        repaint();
    }


    private void updateField(int playerField, int fieldNum) {

        System.out.println("In field builder with\n");
        switch (playerField) {
            case 0:
                    DefaultListModel<ImageIcon> model = new DefaultListModel<>();
                    for (int j = 0; j < field.get(0).get(fieldNum).getSize(); j++) {
                        model.addElement(field.get(0).get(fieldNum).getCard(j).getImage());
                    }
                    p1Field.setField(model, fieldNum);

                break;
            case 1:
                model = new DefaultListModel<>();
                for (int j = 0; j < field.get(1).get(fieldNum).getSize(); j++) {
                    model.addElement(field.get(1).get(fieldNum).getCard(j).getImage());
                }
                p2Field.setField(model, fieldNum);

                break;
            case 2:
                model = new DefaultListModel<>();
                for (int j = 0; j < field.get(2).get(fieldNum).getSize(); j++) {
                    model.addElement(field.get(2).get(fieldNum).getCard(j).getImage());
                }
                p3Field.setField(model, fieldNum);

                break;
            case 3:
                model = new DefaultListModel<>();
                for (int j = 0; j < field.get(3).get(fieldNum).getSize(); j++) {
                    model.addElement(field.get(3).get(fieldNum).getCard(j).getImage());
                }
                p4Field.setField(model, fieldNum);
                break;
        }
        resetButtons();
        phase = turnPhase;
        hitButton.setVisible(true);
        viewHand();
        repaint();
    }

    /*
     setup for the laying down phase buttons
     */
    private void goDown() {

        dealButton.setText("Cancel Play");
        hitButton.setText("Submit Plays");


        switch (round)

        {
            case 0:
                winLoseBox.setText("Must play two sets to go down\n");

                switch (currentPlayer.getPlayerNum()) {

                    case 0:
                        p11Button.setText("Build Set");
                        p11Button.setVisible(true);
                        p11Button.setEnabled(true);
                        p12Button.setText("Build Set");
                        p12Button.setVisible(true);
                        p12Button.setEnabled(true);
                        break;
                    case 1:
                        p21Button.setText("Build Set");
                        p21Button.setVisible(true);
                        p21Button.setEnabled(true);
                        p22Button.setText("Build Set");
                        p22Button.setVisible(true);
                        p22Button.setEnabled(true);
                        break;
                    case 2:
                        p31Button.setText("Build Set");
                        p31Button.setVisible(true);
                        p31Button.setEnabled(true);
                        p32Button.setText("Build Set");
                        p32Button.setVisible(true);
                        p32Button.setEnabled(true);
                        break;
                    case 3:
                        p41Button.setText("Build Set");
                        p41Button.setVisible(true);
                        p41Button.setEnabled(true);
                        p42Button.setText("Build Set");
                        p42Button.setVisible(true);
                        p42Button.setEnabled(true);
                        break;
                }

                repaint();
                break;
            case 1:
                winLoseBox.setText("Must play a set and a run to go down\n");

                switch (currentPlayer.getPlayerNum()) {
                    case 0:
                        p11Button.setText("Build Run");
                        p11Button.setVisible(true);
                        p11Button.setEnabled(true);
                        p12Button.setText("Build Set");
                        p12Button.setVisible(true);
                        p12Button.setEnabled(true);
                        break;
                    case 1:
                        p21Button.setText("Build Run");
                        p21Button.setVisible(true);
                        p21Button.setEnabled(true);
                        p22Button.setText("Build Set");
                        p22Button.setVisible(true);
                        p22Button.setEnabled(true);
                        break;
                    case 2:
                        p31Button.setText("Build Run");
                        p31Button.setVisible(true);
                        p31Button.setEnabled(true);
                        p32Button.setText("Build Set");
                        p32Button.setVisible(true);
                        p32Button.setEnabled(true);
                        break;
                    case 3:
                        p41Button.setText("Build Run");
                        p41Button.setVisible(true);
                        p41Button.setEnabled(true);
                        p42Button.setText("Build Set");
                        p42Button.setVisible(true);
                        p42Button.setEnabled(true);
                        break;
                }
                repaint();
                break;
            case 2:
                winLoseBox.setText("Must play two runs to go down\n");

                switch (currentPlayer.getPlayerNum()) {
                    case 0:
                        p11Button.setText("Build Run");
                        p11Button.setVisible(true);
                        p12Button.setText("Build Run");
                        p12Button.setVisible(true);
                        break;
                    case 1:
                        p21Button.setText("Build Run");
                        p21Button.setVisible(true);
                        p22Button.setText("Build Run");
                        p22Button.setVisible(true);
                        break;
                    case 2:
                        p31Button.setText("Build Run");
                        p31Button.setVisible(true);
                        p32Button.setText("Build Run");
                        p32Button.setVisible(true);
                        break;
                    case 3:
                        p41Button.setText("Build Run");
                        p41Button.setVisible(true);

                        p42Button.setText("Build Run");
                        p42Button.setVisible(true);

                        break;
                }

                repaint();
                break;
            case 3:
                winLoseBox.setText("Must play three sets to go down\n");

                switch (currentPlayer.getPlayerNum()) {
                    case 0:
                        p11Button.setText("Build Set");
                        p11Button.setVisible(true);

                        p12Button.setText("Build Set");
                        p12Button.setVisible(true);

                        p13Button.setText("Build Set");
                        p13Button.setVisible(true);

                        break;
                    case 1:
                        p21Button.setText("Build Set");
                        p21Button.setVisible(true);

                        p22Button.setText("Build Set");
                        p22Button.setVisible(true);

                        p23Button.setText("Build Set");
                        p23Button.setVisible(true);

                        break;
                    case 2:
                        p31Button.setText("Build Set");
                        p31Button.setVisible(true);

                        p32Button.setText("Build Set");
                        p32Button.setVisible(true);

                        p33Button.setText("Build Set");
                        p33Button.setVisible(true);

                        break;
                    case 3:
                        p41Button.setText("Build Set");
                        p41Button.setVisible(true);

                        p42Button.setText("Build Set");
                        p42Button.setVisible(true);

                        p43Button.setText("Build Set");
                        p43Button.setVisible(true);

                        break;
                }
                repaint();
                break;
            case 4:
                winLoseBox.setText("Must play two sets and a run to go down\n");

                switch (currentPlayer.getPlayerNum()) {
                    case 0:
                        p11Button.setText("Build Set");
                        p11Button.setVisible(true);
                        p12Button.setText("Build Set");
                        p12Button.setVisible(true);
                        p13Button.setText("Build Run");
                        p13Button.setVisible(true);
                        break;
                    case 1:
                        p21Button.setText("Build Set");
                        p21Button.setVisible(true);
                        p22Button.setText("Build Set");
                        p22Button.setVisible(true);
                        p23Button.setText("Build Run");
                        p23Button.setVisible(true);
                        break;
                    case 2:
                        p31Button.setText("Build Set");
                        p31Button.setVisible(true);
                        p32Button.setText("Build Set");
                        p32Button.setVisible(true);
                        p33Button.setText("Build Run");
                        p33Button.setVisible(true);
                        break;
                    case 3:
                        p41Button.setText("Build Set");
                        p41Button.setVisible(true);
                        p42Button.setText("Build Set");
                        p42Button.setVisible(true);
                        p43Button.setText("Build Run");
                        p43Button.setVisible(true);
                        break;
                }

                break;
            case 5:
                winLoseBox.setText("Must play two runs and a set to go down\n");
                switch (currentPlayer.getPlayerNum()) {
                    case 0:
                        p11Button.setText("Build Run");
                        p11Button.setVisible(true);
                        p12Button.setText("Build Run");
                        p12Button.setVisible(true);
                        p13Button.setText("Build Set");
                        p13Button.setVisible(true);
                        break;
                    case 1:
                        p21Button.setText("Build Run");
                        p21Button.setVisible(true);
                        p22Button.setText("Build Run");
                        p22Button.setVisible(true);
                        p23Button.setText("Build Set");
                        p23Button.setVisible(true);
                        break;
                    case 2:
                        p31Button.setText("Build Run");
                        p31Button.setVisible(true);
                        p32Button.setText("Build Run");
                        p32Button.setVisible(true);
                        p33Button.setText("Build Set");
                        p33Button.setVisible(true);
                        break;
                    case 3:
                        p41Button.setText("Build Run");
                        p41Button.setVisible(true);
                        p42Button.setText("Build Run");
                        p42Button.setVisible(true);
                        p43Button.setText("Build Set");
                        p43Button.setVisible(true);
                        break;
                }

                break;
            case 6:
                switch (currentPlayer.getPlayerNum()) {
                    case 0:
                        p11Button.setText("Build Run");
                        p11Button.setVisible(true);
                        p12Button.setText("Build Run");
                        p12Button.setVisible(true);
                        p13Button.setText("Build Run");
                        p13Button.setVisible(true);
                        break;
                    case 1:
                        p21Button.setText("Build Run");
                        p21Button.setVisible(true);
                        p22Button.setText("Build Run");
                        p22Button.setVisible(true);
                        p23Button.setText("Build Run");
                        p23Button.setVisible(true);
                        break;
                    case 2:
                        p31Button.setText("Build Run");
                        p31Button.setVisible(true);
                        p32Button.setText("Build Run");
                        p32Button.setVisible(true);
                        p33Button.setText("Build Run");
                        p33Button.setVisible(true);
                        break;
                    case 3:
                        p41Button.setText("Build Run");
                        p41Button.setVisible(true);
                        p42Button.setText("Build Run");
                        p42Button.setVisible(true);
                        p43Button.setText("Build Run");
                        p43Button.setVisible(true);
                        break;
                }
                winLoseBox.setText("Must play three runs without a discard to go down\n");
                break;
        }
        repaint();



    }

    /*
      Method used if the cancel button is clicked what trying to lay cards on the table
     */
    private void cancPlay(){

            resetButtons();
            System.out.println("In cancel");

                retToHand();

            phase = turnPhase;
            viewHand();
            cancel = false;

    }

    /*
      Method used to place cards back into a players hand if a play is canceled or invalid
     */
    private void retToHand(){
        if(field.get(currentPlayer.getPlayerNum()) != null) {
            for (int j = 0; j < field.get(currentPlayer.getPlayerNum()).size(); j++) {
                if (field.get(currentPlayer.getPlayerNum()).get(j) != null) {
                    for (int i = 0; i < field.get(currentPlayer.getPlayerNum()).get(j).getSize(); i++) {
                        currentPlayer.drawCard(field.get(currentPlayer.getPlayerNum()).get(j).removeCard(0));
                    }
                }
            }
        }
    }

    /*
       resets the field buttons from showing in non play steps
     */
    private void resetButtons(){
        System.out.println("IN BUTTON RESET");
        p11Button.setVisible(false);
        p12Button.setVisible(false);
        p13Button.setVisible(false);
        p21Button.setVisible(false);
        p22Button.setVisible(false);
        p23Button.setVisible(false);
        p31Button.setVisible(false);
        p32Button.setVisible(false);
        p33Button.setVisible(false);
        repaint();
    }

    /*
       Method used to help switch between players during turns
     */
    private void nextCall() {
        hitButton.setText("Next Player");

        dealButton.setVisible(false);
        handPanel.setVisible(false);
    }

    /*
      ends the the nextCall method
     */
    private void endNext() {
        dealButton.setVisible(true);
        handPanel.setVisible(true);
        viewHand();
        repaint();
    }


    /*
       gets the current player for this turn
     */
    public void setCurrentPlayer() {
        this.currentPlayer = game.getCurrentPlayer();
    }


    /*
       Starts the liver pool game and the controller
     */
    private void startGame() {
        game.startGame();
        setCurrentPlayer();
        setupPanel();
        repaint();
        game.roundReset();
        time.start();

    }

    /*
      Method used for the main Turn phase controls
     */
    private void takeTurn() {

        System.out.println("In take turn Method---------------------");
        System.out.println(currentPlayer.isDown());
        if (currentPlayer.isDown()) {
            winLoseBox.setText(game.getWinCondition() + "\n" + currentPlayer.getPlayerNum() + "\n Place cards on other plays until you have no"
                    + " cards in your hand or you are done with your turn");
            dealButton.setText("Play Cards");
            hitButton.setEnabled(true);
            hitButton.setText("End Turn");
            repaint();
        } else {
            winLoseBox.setText(game.getWinCondition() + "\n" + currentPlayer.getPlayerNum()
                    + "\nLay down plays or end your turn");
            dealButton.setText("Go Down");
            hitButton.setEnabled(true);
            hitButton.setVisible(true);
            hitButton.setText("End Turn");
            repaint();
        }

    }

    /*
      Resets the round when a players hand is empty
     */
    private void roundReset() {
        game.setDealer();
        game.resetPlayers();
        game.resetDeck();
        currentPlayer = game.nextTurn();
        round++;
        roundOver = false;
        phase = mayPhase;
    }



    /*
       Method used for a player to draw a card
     */
    private void drawStep() {

        if (game.getPile().size() != 0) {
            winLoseBox.setText(game.getWinCondition() + "\n Draw from the Deck or Discard Pile?");
            dealButton.setVisible(true);
            dealButton.setText("Deck");
            hitButton.setVisible(true);
            hitButton.setText("Discard Pile");
            repaint();
        } else {
            winLoseBox.setText(game.getWinCondition() + "\n Draw from the Deck");
            dealButton.setText("Deck");
            //       butPanel.add(dealButton);
            hitButton.setVisible(false);
            repaint();
        }
    }

    /*
     Method used if a player choses to take the discard card at the beginning of a turn
     */
    private void mayItrue() {
        if (tmp == null) {
            viewHand();
            currentPlayer.drawCard(game.getPile().retrieve());
            phase = turnPhase;
            viewHand();
            System.out.println("Leaving May I True First Player Phase " + phase);
            checkDiscImage();
        } else {
            viewHand();
            currentPlayer.drawCard(game.getPile().retrieve());
            currentPlayer.drawCard(game.drawCard());
            currentPlayer = tmp;
            phase = drawPhase;
            viewHand();
            System.out.println("Leaving mayI true oppisite player Phase " + phase);
            checkDiscImage();
        }


        viewHand();
        repaint();
    }

    /*
    Resets the field after each Round
     */
    private void resetField() {
        ArrayList<ArrayList<Plays>> newField = new ArrayList<ArrayList<Plays>>();
        this.field = newField;

        for (int fields = 0; fields < numPlayers; fields++) {
            this.field.add(new ArrayList<Plays>());
        }


    }


    /*
      Setup for the field buttons when a player wants to play cards
     */
    private void fieldButSetup() {

        dealButton.setText("Done");
        hitButton.setVisible(false);
        for(int i = 0; i < field.get(currentPlayer.getPlayerNum()).size(); i++) {
            System.out.println(field.get(currentPlayer.getPlayerNum()).size() + " field size");
        }
        Player[] players = game.getPlayers();
        for(int i = 0; i < totalPlayers; i++) {
            if(players[i].isDown()) {
                switch (players[i].getPlayerNum()) {
                    case 0:
                        if (field.get(i).size() >= 0) {
                            if (field.get(players[i].getPlayerNum()).get(0).getRun()) {
                                p11Button.setText("Play on Run 1");
                                p11Button.setVisible(true);
                                p11Button.setEnabled(true);
                                repaint();
                            } else {
                                p11Button.setText("Play on Set 1");
                                p11Button.setVisible(true);
                                p11Button.setEnabled(true);
                                repaint();
                            }
                        }
                        if (field.get(players[i].getPlayerNum()).size() > 1) {
                            if (field.get(currentPlayer.getPlayerNum()).get(1).getRun()) {
                                p12Button.setText("Play on Run 2");
                                p12Button.setVisible(true);
                                p12Button.setEnabled(true);
                                repaint();
                            } else
                                p12Button.setText("Play on Set 2");
                            p12Button.setVisible(true);
                            p12Button.setEnabled(true);
                            repaint();
                        }
                        if (field.get(i).size() > 2) {
                            if (field.get(players[i].getPlayerNum()).get(2).getRun()) {
                                p13Button.setText("Play on Run 3");
                                p13Button.setVisible(true);
                                p13Button.setEnabled(true);
                                repaint();
                            } else
                                p13Button.setText("Play on Set 3");
                            p13Button.setVisible(true);
                            p13Button.setEnabled(true);
                            repaint();
                        }
                        break;
                    case 1:
                        if (field.get(i).size() >= 0) {
                            p21Button.setEnabled(true);
                            if (field.get(players[i].getPlayerNum()).get(0).getRun()) {
                                p21Button.setText("Play on Run 1");
                                p21Button.setVisible(true);
                                repaint();
                            } else {
                                p21Button.setText("Play on Set 1");
                                p21Button.setVisible(true);
                                repaint();
                            }
                        }
                        if (field.get(i).size() > 1) {
                            p22Button.setEnabled(true);
                            if (field.get(players[i].getPlayerNum()).get(1).getRun()) {
                                p22Button.setText("Play on Run 2");
                                p22Button.setVisible(true);
                                repaint();
                            } else
                                p22Button.setText("Play on Set 2");
                            p22Button.setVisible(true);
                            repaint();
                        }
                        if (field.get(players[i].getPlayerNum()).size() > 2) {
                            p23Button.setEnabled(true);
                            if (field.get(currentPlayer.getPlayerNum()).get(2).getRun()) {
                                p23Button.setText("Play on Run 3");
                                p23Button.setVisible(true);
                                repaint();
                            } else
                                p13Button.setText("Play on Set 3");
                            p23Button.setVisible(true);
                            repaint();
                        }
                        break;
                    case 2:
                        if (field.get(players[i].getPlayerNum()).size() >= 0) {
                            p31Button.setEnabled(true);
                            if (field.get(currentPlayer.getPlayerNum()).get(0).getRun()) {
                                p31Button.setText("Play on Run 1");
                                p31Button.setVisible(true);
                                repaint();
                            } else {
                                p31Button.setText("Play on Set 1");
                                p31Button.setVisible(true);
                                repaint();
                            }
                        }
                        if (field.get(players[i].getPlayerNum()).size() > 1) {
                            p32Button.setEnabled(true);
                            if (field.get(currentPlayer.getPlayerNum()).get(1).getRun()) {
                                p32Button.setText("Play on Run 2");
                                p32Button.setVisible(true);
                                repaint();
                            } else
                                p32Button.setText("Play on Set 2");
                            p32Button.setVisible(true);
                            repaint();
                        }
                        if (field.get(players[i].getPlayerNum()).size() > 2) {
                            p32Button.setEnabled(true);
                            if (field.get(currentPlayer.getPlayerNum()).get(2).getRun()) {
                                p33Button.setText("Play on Run 3");
                                p33Button.setVisible(true);
                                repaint();
                            } else
                                p33Button.setText("Play on Set 3");
                            p33Button.setVisible(true);
                            repaint();
                        }
                        break;
                    case 3:
                        if (field.get(players[i].getPlayerNum()).size() >= 0) {
                            p41Button.setEnabled(true);
                            if (field.get(currentPlayer.getPlayerNum()).get(0).getRun()) {
                                p41Button.setText("Play on Run 1");
                                p41Button.setVisible(true);
                                repaint();
                            } else {
                                p41Button.setText("Play on Set 1");
                                p41Button.setVisible(true);
                                repaint();
                            }
                        }
                        if (field.get(players[i].getPlayerNum()).size() > 1) {
                            p42Button.setEnabled(true);
                            if (field.get(currentPlayer.getPlayerNum()).get(1).getRun()) {
                                p42Button.setText("Play on Run 2");
                                p42Button.setVisible(true);
                                repaint();
                            } else
                                p42Button.setText("Play on Set 2");
                            p42Button.setVisible(true);
                            repaint();
                        }
                        if (field.get(players[i].getPlayerNum()).size() > 2) {
                            p43Button.setEnabled(true);
                            if (field.get(currentPlayer.getPlayerNum()).get(2).getRun()) {
                                p43Button.setText("Play on Run 3");
                                p43Button.setVisible(true);
                                repaint();
                            } else
                                p43Button.setText("Play on Set 3");
                            p43Button.setVisible(true);
                            repaint();
                        }
                        break;
                }
            }
        }
    }



    /*
     Updates the score after each round
     */
    private void scoreUpdate() {
        int[] scores = game.getScores();

        for (int i = 0; i < scores.length; i++) {
            switch (i) {
                case 0:
                    p1Score.setText(" " + scores[i]);
                    break;
                case 1:
                    p2Score.setText(" " + scores[i]);
                    break;
                case 2:
                    p3Score.setText(" " + scores[i]);
                    break;
                case 3:
                    p4Score.setText(" " + scores[i]);
                    break;
            }
        }
    }

    /***********************************************************
     * Player 1 field Button 1
     ***********************************************************/
    private class playOn11 implements ActionListener {
        /*****************************************************
         *
         *****************************************************/
        public void actionPerformed(ActionEvent e) {



            if (phase == layPhase) {
                switch (round)

                {
                    case 0:
                        Plays tmp = game.fieldSetup(currentPlayer);
                        tmp.isSet(true);
                        System.out.println(tmp.toString());
                        tmp.setFieldPosition(currentPlayer.getPlayerNum(), 0);
                        field.get(currentPlayer.getPlayerNum()).add(0,tmp);
                        viewHand();
                        break;
                    case 1:
                        tmp = game.fieldSetup(currentPlayer);
                        tmp.isSet(true);
                        System.out.println(tmp.toString());
                        tmp.setFieldPosition(currentPlayer.getPlayerNum(), 0);
                        field.get(currentPlayer.getPlayerNum()).add(0,tmp);
                        viewHand();
                        break;
                    case 2:
                        tmp = game.fieldSetup(currentPlayer);
                        tmp.isRun(true);
                        System.out.println(tmp.toString());
                        tmp.setFieldPosition(currentPlayer.getPlayerNum(), 0);
                        field.get(currentPlayer.getPlayerNum()).add(0,tmp);
                        viewHand();
                        break;
                    case 3:
                        tmp = game.fieldSetup(currentPlayer);
                        tmp.isSet(true);
                        tmp.setFieldPosition(currentPlayer.getPlayerNum(), 0);
                        field.get(currentPlayer.getPlayerNum()).add(0,tmp);
                        viewHand();
                        break;
                    case 4:
                        tmp = game.fieldSetup(currentPlayer);
                        tmp.isSet(true);
                        tmp.setFieldPosition(currentPlayer.getPlayerNum(), 0);
                        field.get(currentPlayer.getPlayerNum()).add(0,tmp);
                        viewHand();
                        break;
                    case 5:
                        tmp = game.fieldSetup(currentPlayer);
                        tmp.isRun(true);
                        tmp.setFieldPosition(currentPlayer.getPlayerNum(), 0);
                        field.get(currentPlayer.getPlayerNum()).add(0,tmp);
                        viewHand();
                        break;
                    case 6:
                        tmp = game.fieldSetup(currentPlayer);
                        tmp.isRun(true);
                        tmp.setFieldPosition(currentPlayer.getPlayerNum(), 0);
                        field.get(currentPlayer.getPlayerNum()).add(0,tmp);
                        viewHand();
                        break;
                }

                p11Button.setEnabled(false);
                p11Button.setVisible(false);
                repaint();
            }

            if(phase == playPhase){
                ImageIcon tmp = currentPlayer.getImages().getSelectedValue();

                for(int i = 0; i < currentPlayer.handSize(); i++){
                    if(tmp.equals(currentPlayer.viewCard(i).getImage())){
                        boolean valid = game.checkPlay(currentPlayer.viewCard(i), field.get(0).get(0));
                        if(valid) {
                            field.get(0).get(0).addCard(currentPlayer.playCard(i));
                            updateField(0,0);
                        }
                        else
                            winLoseBox.setText("Invalid Play");
                            phase = turnPhase;
                            repaint();
                    }
                }

                if(field.get(0).get(0).getRun()){
                    field.get(0).get(0).runSorter();
                }

                viewHand();
            }
            repaint();

        }
    }

    /***********************************************************
     * Player 1 field button 2
     ***********************************************************/
    private class playOn12 implements ActionListener {
        /*****************************************************
         *
         *****************************************************/
        public void actionPerformed(ActionEvent e) {




            if (phase == layPhase) {
                switch (round)

                {
                    case 0:
                        Plays tmp = game.fieldSetup(currentPlayer);
                        tmp.isSet(true);
                        tmp.setFieldPosition(currentPlayer.getPlayerNum(), 1);
                        field.get(currentPlayer.getPlayerNum()).add(1,tmp);
                        viewHand();
                        break;
                    case 1:
                        tmp = game.fieldSetup(currentPlayer);
                        tmp.isRun(true);
                        tmp.setFieldPosition(currentPlayer.getPlayerNum(), 1);
                        field.get(currentPlayer.getPlayerNum()).add(1,tmp);
                        viewHand();
                        break;
                    case 2:
                        tmp = game.fieldSetup(currentPlayer);
                        tmp.isRun(true);
                        tmp.setFieldPosition(currentPlayer.getPlayerNum(), 1);
                        field.get(currentPlayer.getPlayerNum()).add(1,tmp);
                        viewHand();
                        break;
                    case 3:
                        tmp = game.fieldSetup(currentPlayer);
                        tmp.isRun(true);
                        tmp.setFieldPosition(currentPlayer.getPlayerNum(), 1);
                        field.get(currentPlayer.getPlayerNum()).add(1,tmp);
                        viewHand();
                        break;
                    case 4:
                        tmp = game.fieldSetup(currentPlayer);
                        tmp.isSet(true);
                        tmp.setFieldPosition(currentPlayer.getPlayerNum(), 1);
                        field.get(currentPlayer.getPlayerNum()).add(1,tmp);
                        viewHand();
                        break;
                    case 5:
                        tmp = game.fieldSetup(currentPlayer);
                        tmp.isRun(true);
                        tmp.setFieldPosition(currentPlayer.getPlayerNum(), 1);
                        field.get(currentPlayer.getPlayerNum()).add(1,tmp);
                        viewHand();
                        break;
                    case 6:
                        tmp = game.fieldSetup(currentPlayer);
                        tmp.isRun(true);
                        tmp.setFieldPosition(currentPlayer.getPlayerNum(), 1);
                        field.get(currentPlayer.getPlayerNum()).add(1,tmp);
                        viewHand();
                        break;
                }
                p12Button.setEnabled(false);
                p12Button.setVisible(false);
                repaint();
            }

            if(phase == playPhase){
                ImageIcon tmp = currentPlayer.getImages().getSelectedValue();

                for(int i = 0; i < currentPlayer.handSize(); i++){
                    if(tmp.equals(currentPlayer.viewCard(i).getImage())){
                        boolean valid = game.checkPlay(currentPlayer.viewCard(i), field.get(0).get(1));
                        if(valid) {
                            field.get(0).get(1).addCard(currentPlayer.playCard(i));
                            updateField(0,1);
                        }
                        else
                            winLoseBox.setText("Invalid Play");
                        phase = turnPhase;
                        repaint();
                    }
                }

                if(field.get(0).get(1).getRun()){
                    field.get(0).get(1).runSorter();
                }

                viewHand();
            }

        }
    }

    /***********************************************************
     *  Player 1 field button 3
     ***********************************************************/
    private class playOn13 implements ActionListener {
        /*****************************************************
         * Places cards on the field
         *****************************************************/
        public void actionPerformed(ActionEvent e) {



            if (phase == layPhase) {
                switch (round)

                {
                    case 3:
                        Plays tmp = game.fieldSetup(currentPlayer);
                        tmp.isSet(true);
                        tmp.setFieldPosition(currentPlayer.getPlayerNum(), 2);
                        field.get(currentPlayer.getPlayerNum()).add(2,tmp);
                        viewHand();
                        break;
                    case 4:
                        tmp = game.fieldSetup(currentPlayer);
                        tmp.isRun(true);
                        tmp.setFieldPosition(currentPlayer.getPlayerNum(), 2);
                        field.get(currentPlayer.getPlayerNum()).add(2,tmp);
                        viewHand();
                        break;
                    case 5:
                        tmp = game.fieldSetup(currentPlayer);
                        tmp.isSet(true);
                        tmp.setFieldPosition(currentPlayer.getPlayerNum(), 2);
                        field.get(currentPlayer.getPlayerNum()).add(2,tmp);
                        viewHand();
                        break;
                    case 6:
                        tmp = game.fieldSetup(currentPlayer);
                        tmp.isRun(true);
                        tmp.setFieldPosition(currentPlayer.getPlayerNum(), 2);
                        field.get(currentPlayer.getPlayerNum()).add(2,tmp);
                        viewHand();
                        break;
                }

                p13Button.setEnabled(false);
                p13Button.setVisible(false);
                viewHand();
                repaint();
            }

            if(phase == playPhase){
                ImageIcon tmp = currentPlayer.getImages().getSelectedValue();

                for(int i = 0; i < currentPlayer.handSize(); i++){
                    if(tmp.equals(currentPlayer.viewCard(i).getImage())){
                        boolean valid = game.checkPlay(currentPlayer.viewCard(i), field.get(0).get(2));
                        if(valid) {
                            field.get(0).get(2).addCard(currentPlayer.playCard(i));
                            updateField(0,2);
                        }
                        else
                            winLoseBox.setText("Invalid Play");
                        phase = turnPhase;
                        repaint();
                    }
                }

                if(field.get(0).get(2).getRun()){
                    field.get(0).get(2).runSorter();
                }

                viewHand();
            }


        }
    }

    /***********************************************************
     *  Player 2 field button 1
     ***********************************************************/
    private class playOn21 implements ActionListener {
        /*****************************************************
         *
         *****************************************************/
        public void actionPerformed(ActionEvent e) {


            if (phase == layPhase) {
                switch (round)

                {
                    case 0:
                        Plays tmp = game.fieldSetup(currentPlayer);
                        tmp.isSet(true);
                        tmp.setFieldPosition(currentPlayer.getPlayerNum(), 0);
                        field.get(currentPlayer.getPlayerNum()).add(0,tmp);
                        viewHand();
                        break;
                    case 1:
                        tmp = game.fieldSetup(currentPlayer);
                        tmp.isSet(true);
                        tmp.setFieldPosition(currentPlayer.getPlayerNum(), 0);
                        field.get(currentPlayer.getPlayerNum()).add(0,tmp);
                        viewHand();
                        break;
                    case 2:
                        tmp = game.fieldSetup(currentPlayer);
                        tmp.isRun(true);
                        tmp.setFieldPosition(currentPlayer.getPlayerNum(), 0);
                        field.get(currentPlayer.getPlayerNum()).add(0,tmp);
                        viewHand();
                        break;
                    case 3:
                        tmp = game.fieldSetup(currentPlayer);
                        tmp.isSet(true);
                        tmp.setFieldPosition(currentPlayer.getPlayerNum(), 0);
                        field.get(currentPlayer.getPlayerNum()).add(0,tmp);
                        viewHand();
                        break;
                    case 4:
                        tmp = game.fieldSetup(currentPlayer);
                        tmp.isSet(true);
                        tmp.setFieldPosition(currentPlayer.getPlayerNum(), 0);
                        field.get(currentPlayer.getPlayerNum()).add(0,tmp);
                        viewHand();
                        break;
                    case 5:
                        tmp = game.fieldSetup(currentPlayer);
                        tmp.isRun(true);
                        tmp.setFieldPosition(currentPlayer.getPlayerNum(), 0);
                        field.get(currentPlayer.getPlayerNum()).add(0,tmp);
                        viewHand();
                        break;
                    case 6:
                        tmp = game.fieldSetup(currentPlayer);
                        tmp.isRun(true);
                        tmp.setFieldPosition(currentPlayer.getPlayerNum(), 0);
                        field.get(currentPlayer.getPlayerNum()).add(0,tmp);
                        viewHand();
                        break;
                }
                p21Button.setEnabled(false);
                p21Button.setVisible(false);
//
                repaint();
            }

            if(phase == playPhase){
                ImageIcon tmp = currentPlayer.getImages().getSelectedValue();

                for(int i = 0; i < currentPlayer.handSize(); i++){
                    if(tmp.equals(currentPlayer.viewCard(i).getImage())){
                        boolean valid = game.checkPlay(currentPlayer.viewCard(i), field.get(1).get(0));
                        if(valid) {
                            field.get(1).get(0).addCard(currentPlayer.playCard(i));
                            updateField(1,0);
                        }
                        else
                            winLoseBox.setText("Invalid Play");
                        phase = turnPhase;
                        repaint();
                    }
                }

                if(field.get(1).get(0).getRun()){
                    field.get(1).get(0).runSorter();
                }

                viewHand();
            }

        }
    }

    /***********************************************************
     * Player 2 field button 2
     ***********************************************************/
    private class playOn22 implements ActionListener {
        /*****************************************************
         *
         *****************************************************/
        public void actionPerformed(ActionEvent e) {



            if (phase == layPhase) {
                switch (round)

                {
                    case 0:
                        Plays tmp = game.fieldSetup(currentPlayer);
                        tmp.isSet(true);
                        tmp.setFieldPosition(currentPlayer.getPlayerNum(), 1);
                        field.get(currentPlayer.getPlayerNum()).add(1,tmp);
                        viewHand();
                        break;
                    case 1:
                        tmp = game.fieldSetup(currentPlayer);
                        tmp.isRun(true);
                        tmp.setFieldPosition(currentPlayer.getPlayerNum(), 1);
                        field.get(currentPlayer.getPlayerNum()).add(1,tmp);
                        viewHand();
                        break;
                    case 2:
                        tmp = game.fieldSetup(currentPlayer);
                        tmp.isRun(true);
                        tmp.setFieldPosition(currentPlayer.getPlayerNum(), 1);
                        field.get(currentPlayer.getPlayerNum()).add(1,tmp);
                        viewHand();
                        break;
                    case 3:
                        tmp = game.fieldSetup(currentPlayer);
                        tmp.isSet(true);
                        tmp.setFieldPosition(currentPlayer.getPlayerNum(), 1);
                        field.get(currentPlayer.getPlayerNum()).add(1,tmp);
                        viewHand();
                        break;
                    case 4:
                        tmp = game.fieldSetup(currentPlayer);
                        tmp.isSet(true);
                        tmp.setFieldPosition(currentPlayer.getPlayerNum(), 1);
                        field.get(currentPlayer.getPlayerNum()).add(1,tmp);
                        viewHand();
                        break;
                    case 5:
                        tmp = game.fieldSetup(currentPlayer);
                        tmp.isRun(true);
                        tmp.setFieldPosition(currentPlayer.getPlayerNum(), 1);
                        field.get(currentPlayer.getPlayerNum()).add(1,tmp);
                        viewHand();
                        break;
                    case 6:
                        tmp = game.fieldSetup(currentPlayer);
                        tmp.isRun(true);
                        tmp.setFieldPosition(currentPlayer.getPlayerNum(), 1);
                        field.get(currentPlayer.getPlayerNum()).add(1,tmp);
                        viewHand();
                        break;
                }

                p22Button.setEnabled(false);
                p22Button.setEnabled(false);
                repaint();
            }

            if(phase == playPhase){
                ImageIcon tmp = currentPlayer.getImages().getSelectedValue();

                for(int i = 0; i < currentPlayer.handSize(); i++){
                    if(tmp.equals(currentPlayer.viewCard(i).getImage())){
                        boolean valid = game.checkPlay(currentPlayer.viewCard(i), field.get(1).get(1));
                        if(valid) {

                            field.get(1).get(1).addCard(currentPlayer.playCard(i));
                            updateField(1,1);
                        }
                        else
                            winLoseBox.setText("Invalid Play");
                        phase = turnPhase;
                        repaint();
                    }
                }

                if(field.get(1).get(1).getRun()){
                    field.get(1).get(1).runSorter();
                }

                viewHand();
            }

        }
    }

    /***********************************************************
     * Player 2 field button 3
     ***********************************************************/
    private class playOn23 implements ActionListener {
        /*****************************************************
         *
         *****************************************************/
        public void actionPerformed(ActionEvent e) {



            if (phase == layPhase) {
                switch (round)

                {
                    case 3:
                        Plays tmp = game.fieldSetup(currentPlayer);
                        tmp.isSet(true);
                        tmp.setFieldPosition(currentPlayer.getPlayerNum(), 2);
                        field.get(currentPlayer.getPlayerNum()).add(2,tmp);
                        viewHand();
                        break;
                    case 4:
                        tmp = game.fieldSetup(currentPlayer);
                        tmp.isRun(true);
                        tmp.setFieldPosition(currentPlayer.getPlayerNum(), 2);
                        field.get(currentPlayer.getPlayerNum()).add(2,tmp);
                        viewHand();
                        break;
                    case 5:
                        tmp = game.fieldSetup(currentPlayer);
                        tmp.isSet(true);
                        tmp.setFieldPosition(currentPlayer.getPlayerNum(), 2);
                        field.get(currentPlayer.getPlayerNum()).add(2,tmp);
                        viewHand();
                        break;
                    case 6:
                        tmp = game.fieldSetup(currentPlayer);
                        tmp.isRun(true);
                        tmp.setFieldPosition(currentPlayer.getPlayerNum(), 2);
                        field.get(currentPlayer.getPlayerNum()).add(2,tmp);
                        viewHand();
                        break;
                }
                p23Button.setEnabled(false);
                p23Button.setEnabled(false);
                repaint();
            }

            if(phase == playPhase){
                ImageIcon tmp = currentPlayer.getImages().getSelectedValue();

                for(int i = 0; i < currentPlayer.handSize(); i++){
                    if(tmp.equals(currentPlayer.viewCard(i).getImage())){
                        boolean valid = game.checkPlay(currentPlayer.viewCard(i), field.get(1).get(2));
                        if(valid) {

                            field.get(1).get(2).addCard(currentPlayer.playCard(i));
                            updateField(1,2);
                        }
                        else
                            winLoseBox.setText("Invalid Play");
                        phase = turnPhase;
                        repaint();
                    }
                }

                if(field.get(1).get(2).getRun()){
                    field.get(1).get(2).runSorter();
                }

                viewHand();
            }

        }
    }

    /***********************************************************
     * Player 3 field button 1
     ***********************************************************/
    private class playOn31 implements ActionListener {
        /*****************************************************
         *
         *****************************************************/
        public void actionPerformed(ActionEvent e) {



            if (phase == layPhase) {
                switch (round)

                {
                    case 0:
                        Plays tmp = game.fieldSetup(currentPlayer);
                        tmp.setFieldPosition(currentPlayer.getPlayerNum(), 0);
                        field.get(currentPlayer.getPlayerNum()).add(0,tmp);
                        viewHand();
                        break;
                    case 1:
                        tmp = game.fieldSetup(currentPlayer);
                        tmp.isSet(true);
                        tmp.setFieldPosition(currentPlayer.getPlayerNum(), 0);
                        field.get(currentPlayer.getPlayerNum()).add(0,tmp);
                        viewHand();
                        break;
                    case 2:
                        tmp = game.fieldSetup(currentPlayer);
                        tmp.isRun(true);
                        tmp.setFieldPosition(currentPlayer.getPlayerNum(), 0);
                        field.get(currentPlayer.getPlayerNum()).add(0,tmp);
                        viewHand();
                        break;
                    case 3:
                        tmp = game.fieldSetup(currentPlayer);
                        tmp.isSet(true);
                        tmp.setFieldPosition(currentPlayer.getPlayerNum(), 0);
                        field.get(currentPlayer.getPlayerNum()).add(0,tmp);
                        viewHand();
                        break;
                    case 4:
                        tmp = game.fieldSetup(currentPlayer);
                        tmp.isSet(true);
                        tmp.setFieldPosition(currentPlayer.getPlayerNum(), 0);
                        field.get(currentPlayer.getPlayerNum()).add(0,tmp);
                        viewHand();
                        break;
                    case 5:
                        tmp = game.fieldSetup(currentPlayer);
                        tmp.isRun(true);
                        tmp.setFieldPosition(currentPlayer.getPlayerNum(), 0);
                        field.get(currentPlayer.getPlayerNum()).add(0,tmp);
                        viewHand();
                        break;
                    case 6:
                        tmp = game.fieldSetup(currentPlayer);
                        tmp.isRun(true);
                        tmp.setFieldPosition(currentPlayer.getPlayerNum(), 0);
                        field.get(currentPlayer.getPlayerNum()).add(0,tmp);
                        viewHand();
                        break;
                }

                p31Button.setEnabled(false);
                p31Button.setEnabled(false);
                repaint();
            }

            if(phase == playPhase){
                ImageIcon tmp = currentPlayer.getImages().getSelectedValue();

                for(int i = 0; i < currentPlayer.handSize(); i++){
                    if(tmp.equals(currentPlayer.viewCard(i).getImage())){
                        boolean valid = game.checkPlay(currentPlayer.viewCard(i), field.get(2).get(0));
                        if(valid) {

                            field.get(2).get(0).addCard(currentPlayer.playCard(i));
                            updateField(2,0);
                        }
                        else
                            winLoseBox.setText("Invalid Play");
                        phase = turnPhase;
                        repaint();
                    }
                }

                if(field.get(2).get(0).getRun()){
                    field.get(2).get(0).runSorter();
                }

                viewHand();
            }


        }
    }


    /***********************************************************
     * Player 3 field button 1
     ***********************************************************/
    private class playOn32 implements ActionListener {
        /*****************************************************
         *
         *****************************************************/
        public void actionPerformed(ActionEvent e) {



            if (phase == layPhase) {
                switch (round)

                {
                    case 0:
                        Plays tmp = game.fieldSetup(currentPlayer);
                        tmp.isSet(true);
                        tmp.setFieldPosition(currentPlayer.getPlayerNum(), 1);
                        field.get(currentPlayer.getPlayerNum()).add(1,tmp);
                        viewHand();
                        break;
                    case 1:
                        tmp = game.fieldSetup(currentPlayer);
                        tmp.isRun(true);
                        tmp.setFieldPosition(currentPlayer.getPlayerNum(), 1);
                        field.get(currentPlayer.getPlayerNum()).add(1,tmp);
                        viewHand();
                        break;
                    case 2:
                        tmp = game.fieldSetup(currentPlayer);
                        tmp.isRun(true);
                        tmp.setFieldPosition(currentPlayer.getPlayerNum(), 1);
                        field.get(currentPlayer.getPlayerNum()).add(1,tmp);
                        viewHand();
                        break;
                    case 3:
                        tmp = game.fieldSetup(currentPlayer);
                        tmp.isSet(true);
                        tmp.setFieldPosition(currentPlayer.getPlayerNum(), 1);
                        field.get(currentPlayer.getPlayerNum()).add(1,tmp);
                        viewHand();
                        break;
                    case 4:
                        tmp = game.fieldSetup(currentPlayer);
                        tmp.isSet(true);
                        tmp.setFieldPosition(currentPlayer.getPlayerNum(), 1);
                        field.get(currentPlayer.getPlayerNum()).add(1,tmp);
                        viewHand();
                        break;
                    case 5:
                        tmp = game.fieldSetup(currentPlayer);
                        tmp.isRun(true);
                        tmp.setFieldPosition(currentPlayer.getPlayerNum(), 1);
                        field.get(currentPlayer.getPlayerNum()).add(1,tmp);
                        viewHand();
                        break;
                    case 6:
                        tmp = game.fieldSetup(currentPlayer);
                        tmp.isRun(true);
                        tmp.setFieldPosition(currentPlayer.getPlayerNum(), 1);
                        field.get(currentPlayer.getPlayerNum()).add(1,tmp);
                        viewHand();
                        break;
                }
                p32Button.setEnabled(false);
                p32Button.setEnabled(false);

                repaint();
            }

            if(phase == playPhase){
                ImageIcon tmp = currentPlayer.getImages().getSelectedValue();

                for(int i = 0; i < currentPlayer.handSize(); i++){
                    if(tmp.equals(currentPlayer.viewCard(i).getImage())){
                        boolean valid = game.checkPlay(currentPlayer.viewCard(i), field.get(2).get(1));
                        if(valid) {
                            field.get(2).get(1).addCard(currentPlayer.playCard(i));
                            updateField(2,1);
                        }
                        else
                            winLoseBox.setText("Invalid Play");
                        phase = turnPhase;
                        repaint();
                    }
                }

                if(field.get(2).get(1).getRun()){
                    field.get(2).get(1).runSorter();
                }

                viewHand();
            }

        }
    }

    /***********************************************************
     * Player 3 field button 2
     ***********************************************************/
    private class playOn33 implements ActionListener {
        /*****************************************************
         *
         *****************************************************/
        public void actionPerformed(ActionEvent e) {



            if (phase == layPhase) {
                switch (round)

                {
                    case 3:
                        Plays tmp = game.fieldSetup(currentPlayer);
                        tmp.isSet(true);
                        tmp.setFieldPosition(currentPlayer.getPlayerNum(), 2);
                        field.get(currentPlayer.getPlayerNum()).add(2,tmp);
                        viewHand();
                        break;
                    case 4:
                        tmp = game.fieldSetup(currentPlayer);
                        tmp.isRun(true);
                        tmp.setFieldPosition(currentPlayer.getPlayerNum(), 2);
                        field.get(currentPlayer.getPlayerNum()).add(2,tmp);
                        viewHand();
                        break;
                    case 5:
                        tmp = game.fieldSetup(currentPlayer);
                        tmp.isSet(true);
                        tmp.setFieldPosition(currentPlayer.getPlayerNum(), 2);
                        field.get(currentPlayer.getPlayerNum()).add(2,tmp);
                        viewHand();
                        break;
                    case 6:
                        tmp = game.fieldSetup(currentPlayer);
                        tmp.isRun(true);
                        tmp.setFieldPosition(currentPlayer.getPlayerNum(), 2);
                        field.get(currentPlayer.getPlayerNum()).add(2,tmp);
                        viewHand();
                        break;
                }
                p33Button.setEnabled(false);
                p33Button.setEnabled(false);
               // viewHand();
                repaint();
            }

            if(phase == playPhase){
                ImageIcon tmp = currentPlayer.getImages().getSelectedValue();

                for(int i = 0; i < currentPlayer.handSize(); i++){
                    if(tmp.equals(currentPlayer.viewCard(i).getImage())){
                        boolean valid = game.checkPlay(currentPlayer.viewCard(i), field.get(2).get(2));
                        if(valid) {
                            field.get(2).get(2).addCard(currentPlayer.playCard(i));
                            updateField(2,2);
                        }
                        else
                            winLoseBox.setText("Invalid Play");
                        phase = turnPhase;
                        repaint();
                    }
                }

                if(field.get(2).get(2).getRun()){
                    field.get(2).get(2).runSorter();
                }

                viewHand();
            }

        }
    }

    /***********************************************************
     * Player 4 field button 1
     ***********************************************************/
    private class playOn41 implements ActionListener {
        /*****************************************************
         *
         *****************************************************/
        public void actionPerformed(ActionEvent e) {


            if (phase == layPhase) {
                switch (round)

                {
                    case 0:
                       Plays tmp = game.fieldSetup(currentPlayer);
                        tmp.isSet(true);
                        tmp.setFieldPosition(currentPlayer.getPlayerNum(), 0);
                        field.get(currentPlayer.getPlayerNum()).add(0,tmp);
                        viewHand();
                        break;
                    case 1:
                        tmp = game.fieldSetup(currentPlayer);
                        tmp.isSet(true);
                        tmp.setFieldPosition(currentPlayer.getPlayerNum(), 0);
                        field.get(currentPlayer.getPlayerNum()).add(0,tmp);
                        viewHand();
                        break;
                    case 2:
                        tmp = game.fieldSetup(currentPlayer);
                        tmp.isRun(true);
                        tmp.setFieldPosition(currentPlayer.getPlayerNum(), 0);
                        field.get(currentPlayer.getPlayerNum()).add(0,tmp);
                        viewHand();
                        break;
                    case 3:
                        tmp = game.fieldSetup(currentPlayer);
                        tmp.isSet(true);
                        tmp.setFieldPosition(currentPlayer.getPlayerNum(), 0);
                        field.get(currentPlayer.getPlayerNum()).add(0,tmp);
                        viewHand();
                        break;
                    case 4:
                        tmp = game.fieldSetup(currentPlayer);
                        tmp.isSet(true);
                        tmp.setFieldPosition(currentPlayer.getPlayerNum(), 0);
                        field.get(currentPlayer.getPlayerNum()).add(0,tmp);
                        viewHand();
                        break;
                    case 5:
                        tmp = game.fieldSetup(currentPlayer);
                        tmp.isRun(true);
                        tmp.setFieldPosition(currentPlayer.getPlayerNum(), 0);
                        field.get(currentPlayer.getPlayerNum()).add(0,tmp);
                        viewHand();
                        break;
                    case 6:
                        tmp = game.fieldSetup(currentPlayer);
                        tmp.isRun(true);
                        tmp.setFieldPosition(currentPlayer.getPlayerNum(), 0);
                        field.get(currentPlayer.getPlayerNum()).add(0,tmp);
                        viewHand();
                        break;
                }
                p41Button.setEnabled(false);
                p41Button.setEnabled(false);
             //   viewHand();
                repaint();
            }

            if(phase == playPhase){
                ImageIcon tmp = currentPlayer.getImages().getSelectedValue();

                for(int i = 0; i < currentPlayer.handSize(); i++){
                    if(tmp.equals(currentPlayer.viewCard(i).getImage())){
                        boolean valid = game.checkPlay(currentPlayer.viewCard(i), field.get(3).get(0));
                        if(valid) {
                            field.get(3).get(0).addCard(currentPlayer.playCard(i));
                            updateField(3,0);
                        }
                        else
                            winLoseBox.setText("Invalid Play");
                        phase = turnPhase;
                        repaint();
                    }
                }

                if(field.get(3).get(0).getRun()){
                    field.get(3).get(0).runSorter();
                }

                viewHand();
            }


        }

    }


    /***********************************************************
     * Player 4 field button 2
     ***********************************************************/
    private class playOn42 implements ActionListener {
        /*****************************************************
         *
         *****************************************************/
        public void actionPerformed(ActionEvent e) {



            if (phase == layPhase) {
                switch (round)

                {
                    case 0:
                        Plays tmp = game.fieldSetup(currentPlayer);
                        tmp.isSet(true);
                        tmp.setFieldPosition(currentPlayer.getPlayerNum(), 1);
                        field.get(currentPlayer.getPlayerNum()).add(1,tmp);
                        viewHand();
                        break;
                    case 1:
                        tmp = game.fieldSetup(currentPlayer);
                        tmp.isRun(true);
                        tmp.setFieldPosition(currentPlayer.getPlayerNum(), 1);
                        field.get(currentPlayer.getPlayerNum()).add(1,tmp);
                        viewHand();
                        break;
                    case 2:
                        tmp = game.fieldSetup(currentPlayer);
                        tmp.isRun(true);
                        tmp.setFieldPosition(currentPlayer.getPlayerNum(), 1);
                        field.get(currentPlayer.getPlayerNum()).add(1,tmp);
                        viewHand();
                        break;
                    case 3:
                        tmp = game.fieldSetup(currentPlayer);
                        tmp.isSet(true);
                        tmp.setFieldPosition(currentPlayer.getPlayerNum(), 1);
                        field.get(currentPlayer.getPlayerNum()).add(1,tmp);
                        viewHand();
                        break;
                    case 4:
                        tmp = game.fieldSetup(currentPlayer);
                        tmp.isSet(true);
                        tmp.setFieldPosition(currentPlayer.getPlayerNum(), 1);
                        field.get(currentPlayer.getPlayerNum()).add(1,tmp);
                        viewHand();
                        break;
                    case 5:
                        tmp = game.fieldSetup(currentPlayer);
                        tmp.isRun(true);
                        tmp.setFieldPosition(currentPlayer.getPlayerNum(), 1);
                        field.get(currentPlayer.getPlayerNum()).add(1,tmp);
                        viewHand();
                        break;
                    case 6:
                        tmp = game.fieldSetup(currentPlayer);
                        tmp.isRun(true);
                        tmp.setFieldPosition(currentPlayer.getPlayerNum(), 1);
                        field.get(currentPlayer.getPlayerNum()).add(1,tmp);
                        viewHand();
                        break;
                }
                p42Button.setEnabled(false);
                p42Button.setEnabled(false);
                repaint();
            }

            if(phase == playPhase){
                ImageIcon tmp = currentPlayer.getImages().getSelectedValue();

                for(int i = 0; i < currentPlayer.handSize(); i++){
                    if(tmp.equals(currentPlayer.viewCard(i).getImage())){
                        boolean valid = game.checkPlay(currentPlayer.viewCard(i), field.get(3).get(1));
                        if(valid) {
                            field.get(3).get(1).addCard(currentPlayer.playCard(i));
                            updateField(3,1);
                        }
                        else
                            winLoseBox.setText("Invalid Play");
                        phase = turnPhase;
                        repaint();
                    }
                }

                if(field.get(3).get(1).getRun()){
                    field.get(3).get(1).runSorter();
                }

                viewHand();
            }

        }
    }

    /***********************************************************
     * Player 4 field button 3
     ***********************************************************/
    private class playOn43 implements ActionListener {
        /*****************************************************
         *
         *****************************************************/
        public void actionPerformed(ActionEvent e) {



            if (phase == layPhase) {
                switch (round)

                {
                    case 3:
                        Plays tmp = game.fieldSetup(currentPlayer);
                        tmp.isSet(true);
                        tmp.setFieldPosition(currentPlayer.getPlayerNum(), 2);
                        field.get(currentPlayer.getPlayerNum()).add(2,tmp);
                        viewHand();
                        break;
                    case 4:
                        tmp = game.fieldSetup(currentPlayer);
                        tmp.isRun(true);
                        tmp.setFieldPosition(currentPlayer.getPlayerNum(), 2);
                        field.get(currentPlayer.getPlayerNum()).add(2,tmp);
                        viewHand();
                        break;
                    case 5:
                        tmp = game.fieldSetup(currentPlayer);
                        tmp.isSet(true);
                        tmp.setFieldPosition(currentPlayer.getPlayerNum(), 2);
                        field.get(currentPlayer.getPlayerNum()).add(2,tmp);
                        viewHand();
                        break;
                    case 6:
                        tmp = game.fieldSetup(currentPlayer);
                        tmp.isRun(true);
                        tmp.setFieldPosition(currentPlayer.getPlayerNum(), 2);
                        field.get(currentPlayer.getPlayerNum()).add(2,tmp);
                        viewHand();
                        break;
                }
                p43Button.setEnabled(false);
                p43Button.setVisible(false);
                repaint();
            }

            if(phase == playPhase){
                ImageIcon tmp = currentPlayer.getImages().getSelectedValue();

                for(int i = 0; i < currentPlayer.handSize(); i++){
                    if(tmp.equals(currentPlayer.viewCard(i).getImage())){
                        boolean valid = game.checkPlay(currentPlayer.viewCard(i), field.get(3).get(2));
                        if(valid) {
                            field.get(3).get(2).addCard(currentPlayer.playCard(i));
                            updateField(3,2);
                        }
                        else
                            winLoseBox.setText("Invalid Play");
                        phase = turnPhase;
                        repaint();
                    }
                }

                if(field.get(3).get(2).getRun()){
                    field.get(3).get(2).runSorter();
                }

                viewHand();
            }

        }
    }





}

