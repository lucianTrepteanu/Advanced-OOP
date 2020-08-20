import javax.swing.*;
import java.awt.*;

public class ViewDestFrame {
    private JFrame frame;
    private JLabel label;
    public ViewDestFrame(){
        frame=new JFrame("Destinations");
        Manager manager=Manager.getInst();
        String allContent="<html><pre>";
        for(Destination dest: manager.cities){
            allContent+=dest;
            allContent+="\n";
        }
        allContent+="</pre></html>";
        label=new JLabel(allContent);
        frame.add(label);
        frame.setSize(400,400);
        frame.setVisible(true);
    }
}
