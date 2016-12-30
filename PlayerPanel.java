import javax.swing.*;
import java.awt.*;

/**
 * Created by Dewey Milton on 4/20/2016.
 */
public class PlayerPanel extends JPanel {

        private Plays[] field;
        private JPanel menu;
        private JPanel screen1;
        private JPanel screen2;
        private JPanel screen3;
        private JScrollPane play1;
        private JScrollPane play2;
        private JScrollPane play3;
        private JList<ImageIcon> list1;
        private JList<ImageIcon> list2;
        private JList<ImageIcon> list3;
        private JLabel playLabel1;
        private JLabel playLabel2;
        private JLabel playLabel3;
        private JButton s1Button;
        private JButton s2Button;
        private JButton s3Button;
        private boolean onField;
        DefaultListModel<ImageIcon> model;

      /*
        Constructs a field panel for a player in the liver pool game
       */
       public PlayerPanel(){

             field = new Plays[3];
             onField = false;
             model = new DefaultListModel<>();
             setLayout(new GridLayout(2,0));
             setBackground(new Color(0, 122, 0));
           GridBagConstraints c = new GridBagConstraints();
          menu = new JPanel(new GridBagLayout());
            menu.setBackground(new Color(0, 122, 0));
           add(menu);

           screen1 = new JPanel(new GridLayout(1,0));
           screen1.setBackground(new Color(0, 122, 0));
           list1 = new JList<>(model);
           list1.setLayoutOrientation(list1.HORIZONTAL_WRAP);
           list1.setBackground(new Color(0, 122, 0));
          list1.setPreferredSize(new Dimension(400,110));
           list1.setVisibleRowCount(1);
           play1 = new JScrollPane(list1);
           playLabel1 = new JLabel("Play 1");


           add(screen1);
           screen1.setVisible(false);
           screen1.add(play1);
           screen1.add(playLabel1);


           screen2 = new JPanel(new GridLayout(1,0));
           screen2.setBackground(new Color(0, 122, 0));
           list2 = new JList<>(model);
           list2.setLayoutOrientation(list2.HORIZONTAL_WRAP);
           list2.setBackground(new Color(0, 122, 0));
           list2.setPreferredSize(new Dimension(400,110));
           list2.setVisibleRowCount(1);
           play2 = new JScrollPane(list2);
           playLabel2 = new JLabel(("Play 2"));

           add(screen2);
           screen2.setVisible(false);
           screen2.add(play2);
           screen2.add(playLabel2);


           screen3 = new JPanel(new GridLayout(1,0));
           screen3.setBackground(new Color(0, 122, 0));
           list3 = new JList<>(model);
           list3.setLayoutOrientation(list3.HORIZONTAL_WRAP);
          list3.setPreferredSize(new Dimension(400,110));
           list3.setBackground(new Color(0, 122, 0));
           list3.setVisibleRowCount(1);
           play3 = new JScrollPane(list3);
           playLabel3 = new JLabel("Play 3");

           add(screen3);
           screen3.setVisible(false);
           screen3.add(play3);
           screen3.add(playLabel3);



       }

     /*
          takes in a panel for the menu screen
          @param Component to add
          @param GridBagConstraints c For positioning

      */
        public void setMenuComp(Component component, GridBagConstraints c){
            this.menu.add(component, c);

        }

      /*
           @param DefaultListModel New List to be on the field
       */
        public void setField(DefaultListModel<ImageIcon> newModel, int index){
            switch(index){
                case 0:
                    list1 = new JList<>(newModel);
                    play1.setViewportView(list1);
                    screen1.setVisible(true);
                    repaint();
                    list1.setLayoutOrientation(list1.HORIZONTAL_WRAP);
                    list1.setPreferredSize(new Dimension(400,110));
                    list1.setBackground(new Color(0, 122, 0));
                    list1.setVisibleRowCount(1);
                    break;
                case 1:
                    list2 = new JList<>(newModel);
                    play2.setViewportView(list2);
                    screen2.setVisible(true);
                    repaint();
                    list2.setLayoutOrientation(list2.HORIZONTAL_WRAP);
                    list2.setPreferredSize(new Dimension(400,110));
                    list2.setBackground(new Color(0, 122, 0));
                    list2.setVisibleRowCount(1);
                    break;
                case 2:
                    list3 = new JList<>(newModel);
                    play3.setViewportView(list3);
                    screen3.setVisible(true);
                    repaint();
                    list3.setLayoutOrientation(list3.HORIZONTAL_WRAP);
                    list3.setPreferredSize(new Dimension(400,110));
                    list3.setBackground(new Color(0, 122, 0));
                    list3.setVisibleRowCount(1);
                    break;
            }

        }



}

