package GUI;
import Entities.*;
import Services.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class EditTravellerFrame {
    private JFrame frame;
    private JTextField codeField;
    private JTextField cityField;
    private JTextField nameField;

    private JButton searchButton;
    private JButton submitButton;

    public EditTravellerFrame() throws IOException {
        frame = new JFrame("Edit traveller");
        codeField = new JTextField("Traveller code");
        codeField.setBounds(0,0,100,200);
        //codeField.setSize(50, 100);
        codeField.setHorizontalAlignment(JTextField.CENTER);
        nameField = new JTextField("Traveller name");
        nameField.setBounds(100,0,100,200);
        //nameField.setSize(50, 100);
        nameField.setHorizontalAlignment(JTextField.CENTER);
        cityField = new JTextField("Traveller city");
        cityField.setBounds(200,0,100,200);
        //cityField.setSize(50, 100);
        cityField.setHorizontalAlignment(JTextField.CENTER);

        Manager manager = Manager.getInst();
        DestinationService destinationService = DestinationService.getInst();

        frame.setLayout(null);
        frame.setSize(305, 305);

        searchButton = new JButton("Search");
        submitButton = new JButton("Submit");

        searchButton.setBounds(0,200,150,100);
        submitButton.setBounds(150,200,150,100);
        searchButton.setHorizontalAlignment(JButton.CENTER);
        submitButton.setHorizontalAlignment(JButton.CENTER);
        searchButton.setVerticalAlignment(JButton.CENTER);
        submitButton.setVerticalAlignment(JButton.CENTER);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String code = codeField.getText();
                    String newName = "";
                    String newCity = "";
                    for (Traveller t : manager.travellers) {
                        if (t.getUniqueCode().equals(code) == true) {
                            newName = t.getName();
                            newCity = t.getCity();
                        }
                    }
                    nameField.setText(newName);
                    cityField.setText(newCity);
                } catch (Exception exception) {
                    System.out.println(exception);
                }
            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String code = codeField.getText();
                    String newName = nameField.getText();
                    String newCity = cityField.getText();

                    TravellerService travellerService = TravellerService.getInst();
                    travellerService.editTraveller(code, newName, newCity);
                } catch (Exception exception) {
                    System.out.println(exception);
                }
            }
        });

        frame.add(codeField);
        frame.add(nameField);
        frame.add(cityField);

        frame.add(searchButton);
        frame.add(submitButton);

        frame.setVisible(true);
    }
}
