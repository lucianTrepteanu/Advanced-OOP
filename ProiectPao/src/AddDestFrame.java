import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AddDestFrame {
    private JFrame frame;
    private JLabel label;
    private JTextField nameField;
    private JButton submitButton;

    public AddDestFrame() throws IOException {
        frame=new JFrame("Destinations");
        nameField=new JTextField("Add city name");
        nameField.setSize(50,100);
        Manager manager=Manager.getInst();
        DestinationService destinationService=DestinationService.getInst();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(3,1));
        frame.setSize(400,400);
        label=new JLabel();

        submitButton=new JButton("Submit city");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String name=nameField.getText();
                    int idx=manager.getIndex(name);
                    if(idx<0){
                        Destination dest=new Destination(name);
                        System.out.println(dest);
                        destinationService.addDestination(dest);
                        label.setText("City added successfully");
                    } else {
                        label.setText("City already added");
                    }
                } catch (Exception exception){
                    System.out.println(exception);
                }
            }
        });

        frame.add(nameField);
        frame.add(submitButton);
        frame.add(label);
        frame.setVisible(true);
    }
}
