import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class AllProfileGUI {

    private JPanel frame;

    private Profile profile;

    private  JPanel panelLeft;
    private  JPanel panelCenter;
    private  JPanel panelRight;

    private  final int LEFT_WIDTH = 100;
    private  final int RIGHT_WIDTH = 200;

    private int frameHeight;

    private Profile sessionProfile;

    private boolean isShowPanelRight;
    

    AllProfileGUI(Profile p, int width, int height, Profile sp, boolean showPanelRight) {
        profile = p;
        sessionProfile = sp;
        isShowPanelRight = showPanelRight;
        
        frame = new JPanel();
        frame.setLayout(new BorderLayout());
        frame.setPreferredSize(new Dimension(width, height));
        frameHeight = height;
        
    }
    

    public JPanel build() {

        makeLeft();

        makeRight();

        makeCenter();

        adjustSize();

        frame.add(panelLeft, BorderLayout.WEST);
        frame.add(panelCenter, BorderLayout.CENTER);
        if (isShowPanelRight) {
            frame.add(panelRight, BorderLayout.EAST);
        }


        return frame;
    }
    

    private void makeLeft() {
        panelLeft = new JPanel();

        FlowLayout layout = new FlowLayout(FlowLayout.LEFT);
        layout.setVgap(5);
        panelLeft.setLayout(layout);


        JLabel labelImage = new JLabel("", JLabel.CENTER);
        BufferedImage image = profile.getProfilePicture();
        labelImage.setIcon(new ImageIcon(image));
        panelLeft.add(labelImage);
    }
    

    private void makeCenter() {
        panelCenter = new JPanel();

        FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
        layout.setVgap(0);
        panelCenter.setLayout(layout);


        JLabel labelName = new JLabel("Name: " + profile.getName(), JLabel.CENTER);
        labelName.setPreferredSize(new Dimension(General.LABEL_WIDTH, General.TEXT_HEIGHT));

        JLabel labelStatus = new JLabel("Status: " + profile.getStatus(), JLabel.CENTER);
        labelStatus.setPreferredSize(new Dimension(General.LABEL_WIDTH, General.TEXT_HEIGHT));

        panelCenter.add(labelName);
        panelCenter.add(labelStatus);
    }
    

    private void makeRight() {
        panelRight = new JPanel();

        FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
        layout.setVgap(5);
        panelRight.setLayout(layout);


        JButton btnViewFriends = new JButton("View Friends");
        btnViewFriends.setPreferredSize(new Dimension(General.LABEL_WIDTH, General.TEXT_HEIGHT));
        ActionListener viewFriendsListender = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                FriendProfileGUI.run(profile);
                
            }
            
        };
        btnViewFriends.addActionListener(viewFriendsListender);

        JButton btnAddFriend = new JButton("Add Friend");
        btnAddFriend.setPreferredSize(new Dimension(General.LABEL_WIDTH, General.TEXT_HEIGHT));

        if (Driver.pm.hasFriendship(profile, sessionProfile)) {

            btnAddFriend.setText("Already Friend.");
            btnAddFriend.setEnabled(false);
        }
        

        if (Driver.pm.isSelf(profile, sessionProfile)) {

            btnAddFriend.setText("Yourself.");
            btnAddFriend.setEnabled(false);

        }

        ActionListener addFriendListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                Driver.pm.createFriendship(profile, sessionProfile);

                btnAddFriend.setText("Already Friend.");
                btnAddFriend.setEnabled(false);

                String message = "Add " + profile + " as your friend successfully.";
                JOptionPane.showMessageDialog(null,  message, "Information", JOptionPane.INFORMATION_MESSAGE);
            }
            
        };
        btnAddFriend.addActionListener(addFriendListener);
        
        panelRight.add(btnAddFriend);
        panelRight.add(btnViewFriends);
    }
    

    private void adjustSize() {
        panelLeft.setPreferredSize(new Dimension(LEFT_WIDTH, frameHeight));
        panelRight.setPreferredSize(new Dimension(RIGHT_WIDTH, frameHeight));
    }

}

