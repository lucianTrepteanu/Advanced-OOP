package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import Services.*;
import Entities.*;

public class AddTravellerFrame {
    private JFrame frame;
    private JLabel label;
    private JTextField nameField;
    private JTextField codeField;
    private JTextField cityField;
    private JTextField fearField;

    private JButton submitButton;

    public AddTravellerFrame() throws IOException {
        frame=new JFrame("Destinations");

        nameField=new JTextField("Traveller name");
        nameField.setSize(50,100);
        codeField=new JTextField("Traveller unique code");
        codeField.setSize(50,100);
        cityField=new JTextField("Traveller city");
        cityField.setSize(50,100);
        fearField=new JTextField("Fly fear? yes/no");
        fearField.setSize(50,100);

        Manager manager=Manager.getInst();
        TravellerService travellerService=TravellerService.getInst();

        frame.setLayout(new GridLayout(2,3));
        frame.setSize(400,400);
        label=new JLabel();

        submitButton=new JButton("Submit traveller");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String name=nameField.getText();
                    String code=codeField.getText();
                    String city=cityField.getText();
                    String fear=fearField.getText();

                    if(fear.equals("yes")==false && fear.equals("no")==false){
                        label.setText("Invalid answer");
                        return;
                    }
                    Traveller traveller=null;
                    if(fear.equals("yes")==true){
                        traveller=new AirFearTraveller(name,code,city);
                    } else {
                        traveller=new AllRoutesTraveller(name,code,city);
                    }

                    travellerService.addTraveller(traveller);
                    label.setText("Traveller added!");
                } catch (Exception exception){
                    System.out.println(exception);
                }
            }
        });

        frame.add(nameField);
        frame.add(codeField);
        frame.add(cityField);
        frame.add(fearField);
        frame.add(submitButton);
        frame.add(label);

        frame.setVisible(true);
    }
}
