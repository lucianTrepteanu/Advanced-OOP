package GUI;
import Entities.*;
import Services.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class RecommendationsFrame {
    private JFrame frame;
    private JLabel label;
    private JTextField codeField;
    private JButton submitButton;

    public RecommendationsFrame() throws IOException {
        frame=new JFrame("Destinations");
        codeField=new JTextField("Search by Traveller code");
        codeField.setSize(50,100);
        codeField.setHorizontalAlignment(JTextField.CENTER);
        Manager manager=Manager.getInst();

        frame.setLayout(new GridLayout(3,1));
        frame.setSize(400,400);
        label=new JLabel();

        submitButton=new JButton("Search");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String code=codeField.getText();
                    Traveller asker=null;

                    for(Traveller t: manager.travellers){
                        if(t.getUniqueCode().equals(code)==true){
                            asker=t;
                        }
                    }

                    if(asker==null){
                        label.setText("No Traveller found");
                        return;
                    }
                    label.setText("<html><pre>"+asker.getRecommendations()+"</pre></html>");
                } catch (Exception exception){
                    System.out.println(exception);
                }
            }
        });

        frame.add(codeField);
        frame.add(submitButton);
        frame.add(label);
        frame.setVisible(true);
    }
}
