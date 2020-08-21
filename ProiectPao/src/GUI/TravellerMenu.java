package GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Entities.*;
import Services.*;

public class TravellerMenu {
    private JFrame frame;
    private JButton b1,b2,b3,b4;

    public TravellerMenu(){
        frame=new JFrame("Traveller");
        b1=new JButton("View Travellers");
        b2=new JButton("Add Traveller");
        b3=new JButton("Edit Traveller");
        b4=new JButton("View Recommandations");

        frame.setSize(400,400);
        frame.setLayout(new GridLayout(4,1));
        frame.add(b1);
        frame.add(b2);
        frame.add(b3);
        frame.add(b4);

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    new ViewTravellersFrame();
                } catch (Exception exception){
                    System.out.println(exception);
                }
            }
        });

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    new AddTravellerFrame();
                } catch (Exception exception){
                    System.out.println(exception);
                }
            }
        });

        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    new EditTravellerFrame();
                } catch (Exception exception){
                    System.out.println(exception);
                }
            }
        });

        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    new RecommendationsFrame();
                } catch (Exception exception){
                    System.out.println(exception);
                }
            }
        });

        frame.setVisible(true);
    }
}
