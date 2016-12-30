import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Borrrowed by Dewey Milton  on 4/21/2016.
 * Found code listed by darrylburke on community.Oracle.com/thread/1357246
 */
public class ReorderListener extends MouseAdapter {
    private JList list;
    private int pressIndex;
    private int relIndex;
    private int pos1;
    private int pos2;
    private ArrayList<Card> tmpHand;

    /*
      constructs an Order listener for the hand panel
     */
    public ReorderListener(JList list, ArrayList<Card> newHand){
        pressIndex = 0;
        relIndex = 0;
        pos1 = pressIndex;
        pos2 = relIndex;
        this.tmpHand = new ArrayList<Card>(11);

        if(!(list.getModel() instanceof DefaultListModel)) {
            throw new IllegalArgumentException("List must have a DefaultListModel");
        }
            this.list = list;

        if(newHand != null){
            this.tmpHand = newHand;
        }


    }




    /*
       Event of when mouse is pressed
     */
    public void mousePressed(MouseEvent e){
        pressIndex = list.locationToIndex(e.getPoint());
        pos1 = list.locationToIndex(e.getPoint());
    }

    /*
      Event for mouse released
     */
    public void mouseReleased(MouseEvent e){
        relIndex = list.locationToIndex(e.getPoint());
        pos2 = list.locationToIndex(e.getPoint());
        if(relIndex != pressIndex && relIndex != -1){
            reorder();
            resort();
        }
    }

    /*
      Event to move index in jlist
     */
    public void mouseDragged(MouseEvent e) {
        mouseReleased(e);
        pressIndex = relIndex;
        pos1 = pos2;
    }

    /*
       Repositions elements in jlist model
     */
    private void reorder(){
        DefaultListModel model = (DefaultListModel) list.getModel();
        Object dragee = model.elementAt(pressIndex);
        model.removeElementAt(pressIndex);
        model.insertElementAt(dragee, relIndex);

    }

    private void resort(){
       Hand tmp = new Hand();
        tmp.setHand(tmpHand);
        tmp.swapCard(pos1, pos2);
    }
}
