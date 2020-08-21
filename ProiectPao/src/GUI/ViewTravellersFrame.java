package GUI;
import Entities.*;
import Services.*;

import javax.swing.*;
import java.io.IOException;

public class ViewTravellersFrame {
    private JFrame frame;
    private JLabel label;
    public ViewTravellersFrame() throws IOException {
        frame=new JFrame("Travellers");
        Manager manager=Manager.getInst();
        TravellerService travellerService=TravellerService.getInst();

        String allContent="<html><pre>";
        allContent+=travellerService.showTravellers();
        allContent+="</pre></html>";

        label=new JLabel(allContent);
        frame.add(label);
        frame.setSize(400,400);
        frame.setVisible(true);
    }
}
