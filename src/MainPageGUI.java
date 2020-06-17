import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;


public class MainPageGUI {

    private static final  int WIDTH = 700;
    private static final  int HEIGHT = 500;

    private static final int TOP_HEIGHT = 10;
    private static final int LEFT_WIDTH = 200;
    private static final int RIGHT_WIDTH = 10;
    private static final int BOTTOM_HEIGHT = 100;
    private static final int CENTER_HEIGHT = HEIGHT - TOP_HEIGHT - BOTTOM_HEIGHT;
    private static final int CENTER_WIDTH = WIDTH - LEFT_WIDTH - RIGHT_WIDTH;

    private final static JFrame frame = new JFrame("Your Profile");

    private static JPanel panelTop;
    private static JPanel panelLeft;

    private static JPanel panelCenter;
    private static JScrollPane scrollPanelCenter;
    
    private static JPanel panelRight;
    private static JPanel panelBottom;

    private static Profile profile;

    private static String keyword = "";

    static JLabel labelName;
    static JLabel labelImage;
    
    public static void run(Profile p) {
        
        profile = p;
        
        System.out.println("MainUI for profile: " + p);
                
        frame.setSize(WIDTH, HEIGHT);
        
        frame.setLayout(new BorderLayout());

        makeTop();
        
        makeLeft();
        
        makeRight();
        
        makeCenter();
        
        makeBottom();
        
        adjustSize();

        frame.add(panelTop, BorderLayout.NORTH);
        frame.add(panelLeft, BorderLayout.WEST);
        frame.add(scrollPanelCenter, BorderLayout.CENTER);
        frame.add(panelRight, BorderLayout.EAST);
        frame.add(panelBottom, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        frame.setResizable(false);

        frame.setVisible(true);

    }
    

    private static void makeLeft() {
        panelLeft = new JPanel();

        FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
        layout.setVgap(10);
        panelLeft.setLayout(layout);

        JLabel labelTop = new JLabel("Your Profile", JLabel.CENTER);
        labelTop.setFont(new java.awt.Font(General.FONT_NAME, 1, General.FONT_SIZE_H2));
        labelTop.setPreferredSize(new Dimension(LEFT_WIDTH, General.TEXT_HEIGHT));

        labelImage = new JLabel("", JLabel.CENTER);
        BufferedImage image = profile.getProfilePicture();
        labelImage.setIcon(new ImageIcon(image));

        labelName = new JLabel("Name: " + profile.getName(), JLabel.CENTER);
        labelName.setPreferredSize(new Dimension(General.LABEL_WIDTH, General.TEXT_HEIGHT));

        JLabel labelStatus = new JLabel("Status: " + profile.getStatus(), JLabel.CENTER);
        labelStatus.setPreferredSize(new Dimension(General.LABEL_WIDTH, General.TEXT_HEIGHT));

        JButton btnViewFriends = new JButton("View Friends");
        btnViewFriends.setPreferredSize(new Dimension(General.LABEL_WIDTH, General.TEXT_HEIGHT));
        ActionListener viewFriendsListender = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                FriendProfileGUI.run(profile);
            }
            
        };
        btnViewFriends.addActionListener(viewFriendsListender);

        JButton btnLeaveNetwork = new JButton("Leave Network");
        btnLeaveNetwork.setPreferredSize(new Dimension(General.LABEL_WIDTH, General.TEXT_HEIGHT));
        
        ActionListener leaveActionListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                Driver.pm.leave(profile);

                String message = "See you next time.";
                JOptionPane.showMessageDialog(null,  message, "Information", JOptionPane.INFORMATION_MESSAGE);

                frame.dispose();
            }
            
        };
        
        btnLeaveNetwork.addActionListener(leaveActionListener);

        JButton btnModifyProfile = new JButton("Modify Profile");
        btnModifyProfile.setPreferredSize(new Dimension(General.LABEL_WIDTH, General.TEXT_HEIGHT));

        ActionListener modifyActionListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                ModifyProfileGUI.run(profile);
            }
            
        };
        btnModifyProfile.addActionListener(modifyActionListener);
        
        panelLeft.add(labelTop);
        panelLeft.add(labelImage);
        panelLeft.add(labelName);
        panelLeft.add(labelStatus);
        
        panelLeft.add(btnViewFriends);
        panelLeft.add(btnLeaveNetwork);
        panelLeft.add(btnModifyProfile);
        
    }

    private static void makeRight() {
        panelRight = new JPanel();
    }
    
    private static void makeBottom() {
        panelBottom = new JPanel();
        
        FlowLayout layout = new FlowLayout(FlowLayout.LEFT);
        layout.setVgap(20);
        panelBottom.setLayout(layout);

        JLabel labelPadding = new JLabel("");
        labelPadding.setPreferredSize(new Dimension(LEFT_WIDTH, General.TEXT_HEIGHT));
        
        JLabel labelForName = new JLabel("Name Search: ");
        labelForName.setFont(new java.awt.Font(General.FONT_NAME, 1, General.FONT_SIZE_H2));
        labelForName.setPreferredSize(new Dimension(General.LABEL_WIDTH, General.TEXT_HEIGHT));
        
        JTextField textName = new JTextField();
        textName.setPreferredSize(new Dimension(General.LABEL_WIDTH, General.TEXT_HEIGHT));
        
        JButton btnSearch = new JButton("Search");
        
        ActionListener searchActionListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                panelCenter.removeAll();
                
                keyword = textName.getText().strip();
                
                ArrayList<Profile> arrSearchResults = Driver.pm.search(keyword);
                
                int count = 0;
                for (Profile p : arrSearchResults) {
                    JPanel panel = new AllProfileGUI(p, CENTER_WIDTH - 50, General.PROFILE_HEIGHT, profile, true).build();
                    panelCenter.add(panel);
                    System.out.println("-showing: " + p);

                    count++;
                }
                
                panelCenter.setPreferredSize(new Dimension(CENTER_WIDTH, General.PROFILE_HEIGHT * (count+1)));
                panelCenter.revalidate();
            }
            
        };
        btnSearch.addActionListener(searchActionListener);

        panelBottom.add(labelPadding);
        panelBottom.add(labelForName);
        panelBottom.add(textName);
        panelBottom.add(btnSearch);

    }
    

    private static void makeTop() {
        panelTop = new JPanel();
        
    }
    

    private static void makeCenter() {
        panelCenter = new JPanel();
        FlowLayout layout = new FlowLayout(FlowLayout.LEFT);
        panelCenter.setLayout(layout);


        scrollPanelCenter = new JScrollPane(panelCenter);

        scrollPanelCenter.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        ArrayList<Profile> arrSearchResults = Driver.pm.search(keyword);
        
        int count = 0;

        for (Profile p : arrSearchResults) {

            JPanel panel = new AllProfileGUI(p, CENTER_WIDTH - 50, General.PROFILE_HEIGHT, profile, true).build();
            panelCenter.add(panel);
            System.out.println("showing: " + p);

            count++;
        }
        

        panelCenter.setPreferredSize(new Dimension(CENTER_WIDTH, General.PROFILE_HEIGHT * (count + 1)));

    }
    

    private static void adjustSize() {
        panelTop.setPreferredSize(new Dimension(WIDTH, TOP_HEIGHT));
        panelLeft.setPreferredSize(new Dimension(LEFT_WIDTH, CENTER_HEIGHT));
        panelRight.setPreferredSize(new Dimension(RIGHT_WIDTH, CENTER_HEIGHT));
        panelBottom.setPreferredSize(new Dimension(WIDTH, BOTTOM_HEIGHT));
    }


    public static void refreshPanelLeft() {
        
        labelName.setText("Name: " + profile.getName());

        BufferedImage image = profile.getProfilePicture();
        labelImage.setIcon(new ImageIcon(image));
    }


    public static void setProfile(Profile p) {
        profile = p;
    }

}
