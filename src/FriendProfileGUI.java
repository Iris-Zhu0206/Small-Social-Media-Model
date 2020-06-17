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

public class FriendProfileGUI {
    private static final int LABEL_WIDTH = 150;
    private static final String FONT_NAME = "Dialog";
    private static final int FONT_SIZE_H1 = 32;
    private static final int FONT_SIZE_H2 = 16;

    private static final  int WIDTH = 500;
    private static final  int HEIGHT = 500;
    
    private static final int TOP_HEIGHT = 10;
    private static final int LEFT_WIDTH = 10;
    private static final int RIGHT_WIDTH = 10;
    private static final int BOTTOM_HEIGHT = 10;
    private static final int CENTER_HEIGHT = HEIGHT - TOP_HEIGHT - BOTTOM_HEIGHT;

    private static final int CENTER_WIDTH = WIDTH - LEFT_WIDTH - RIGHT_WIDTH;
    private static final int ITEM_HEIGHT = 100;
    
    private static final int TEXT_HEIGHT = 30;

    // frame and window title.
    private static JFrame frame;
    
    // panel
    private static JPanel panelTop;
    private static JPanel panelLeft;

    private static JPanel panelCenter;
    private static JScrollPane scrollPanelCenter;
    
    private static JPanel panelRight;
    private static JPanel panelBottom;
    
    private static Profile profile;

    private static String keyword = "";
        
    public static void run(Profile p) {
        
        frame = new JFrame();
        profile = p;
        
        System.out.println("FriendsUI for profile: " + p);
        frame.setTitle(p + "'s friends");
                
        frame.setSize(WIDTH, HEIGHT);
        
        frame.setLocation(100, 10);

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
        
    }
    
    private static void makeRight() {
        panelRight = new JPanel();
    }
    
    private static void makeBottom() {
        panelBottom = new JPanel();
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


        ArrayList<Profile> arrResults = Driver.pm.getFriendship(profile);
        
        int count = 0;
        for (Profile p : arrResults) {
            JPanel panel = new AllProfileGUI(p, CENTER_WIDTH - 50, ITEM_HEIGHT, profile, false).build();
            panelCenter.add(panel);
            System.out.println("+showing: " + p);

            count++;
        }
        
        panelCenter.setPreferredSize(new Dimension(CENTER_WIDTH, ITEM_HEIGHT * (count + 1)));

    }
    
    private static void adjustSize() {
        panelTop.setPreferredSize(new Dimension(WIDTH, TOP_HEIGHT));
        panelLeft.setPreferredSize(new Dimension(LEFT_WIDTH, CENTER_HEIGHT));
        panelCenter.setPreferredSize(new Dimension(CENTER_WIDTH, CENTER_HEIGHT));

        panelRight.setPreferredSize(new Dimension(RIGHT_WIDTH, CENTER_HEIGHT));
        panelBottom.setPreferredSize(new Dimension(WIDTH, BOTTOM_HEIGHT));
    }


}
