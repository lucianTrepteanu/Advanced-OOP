import javax.swing.*;
import java.awt.*;

public class ViewRoutesFrame {
    private JFrame frame;
    private JLabel label;
    public ViewRoutesFrame(){
        frame=new JFrame("Routes");
        Manager manager=Manager.getInst();
        String allContent="<html><pre>";
        for(Route route: manager.routes){
            allContent+=route.getfDest()+"-"+route.getsDest()+" "+route.getDistance()+" "+route.getTime()+" "+route.getMoneyCost();
            allContent+="\n";
        }
        allContent+="</pre></html>";
        label=new JLabel(allContent);
        frame.add(label);
        frame.setSize(400,400);
        frame.setVisible(true);
    }
}
