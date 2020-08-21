package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Entities.*;
import Services.*;

public class MainFrame {
    private JFrame frame;
    private JButton b1,b2,b3,b4;

    public MainFrame(){
        frame=new JFrame("Main Menu");
        b1=new JButton("View destinations");
        b2=new JButton("View routes");
        b3=new JButton("Add destination");
        b4=new JButton("View Traveller Menu");

        frame.add(b1);
        frame.add(b2);
        frame.add(b3);
        frame.add(b4);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(4,1));
        frame.setSize(400,400);
        frame.setVisible(true);

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    new ViewDestFrame();
                } catch (Exception exception){
                    System.out.println(exception);
                }
            }
        });

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    new ViewRoutesFrame();
                } catch (Exception exception){
                    System.out.println(exception);
                }
            }
        });

        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    new AddDestFrame();
                } catch (Exception exception){
                    System.out.println(exception);
                }
            }
        });

        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    new TravellerMenu();
                } catch (Exception exception){
                    System.out.println(exception);
                }
            }
        });
    }
}
