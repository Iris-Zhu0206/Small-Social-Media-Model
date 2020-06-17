import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RegisterGUI {

    private static final  int WIDTH = 600;
    private static final  int HEIGHT = 400;

    private static final int TOP_HEIGHT = 100;
    private static final int LEFT_WIDTH = 130;
    private static final int RIGHT_WIDTH = 130;
    private static final int BOTTOM_HEIGHT = 50;
    private static final int CENTER_HEIGHT = HEIGHT - TOP_HEIGHT - BOTTOM_HEIGHT;
    private static final int CENTER_WIDTH = WIDTH - LEFT_WIDTH - RIGHT_WIDTH;

    private final static JFrame frame = new JFrame("Please join the Social Network");

    private static JPanel panelTop;
    private static JPanel panelLeft;
    private static JPanel panelCenter;
    private static JPanel panelRight;
    private static JPanel panelBottom;

    static BufferedImage image;
    //the method to run the JionUI
    public static void run() throws IOException {

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
        frame.add(panelCenter, BorderLayout.CENTER);
        frame.add(panelRight, BorderLayout.EAST);
        frame.add(panelBottom, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

        // set it in the middle
        JLabel labelTop = new JLabel("Create Profile to Join Network", JLabel.CENTER);
        labelTop.setFont(new java.awt.Font(General.FONT_NAME, 1, General.FONT_SIZE_H1));
        labelTop.setPreferredSize(new Dimension(WIDTH, TOP_HEIGHT));

        panelTop.add(labelTop);
    }

    private static void makeCenter() throws IOException {
        panelCenter = new JPanel();

        FlowLayout layout = new FlowLayout(FlowLayout.LEFT);

        layout.setVgap(20);
        panelCenter.setLayout(layout);

        JLabel labelForName = new JLabel("Name: ");
        labelForName.setFont(new java.awt.Font(General.FONT_NAME, 1, General.FONT_SIZE_H2));
        labelForName.setPreferredSize(new Dimension(General.LABEL_WIDTH, General.TEXT_HEIGHT));
        
        JTextField textName = new JTextField();
        textName.setPreferredSize(new Dimension(General.LABEL_WIDTH, General.TEXT_HEIGHT));

        JLabel labelForImage = new JLabel("Image: ");
        labelForImage.setFont(new java.awt.Font(General.FONT_NAME, 1, General.FONT_SIZE_H2));
        labelForImage.setPreferredSize(new Dimension(General.LABEL_WIDTH, General.TEXT_HEIGHT));

        JComboBox<ImageIcon> comboImages = new JComboBox<ImageIcon>();
        for (String imageFile : General.IMAGES) { 
            comboImages.addItem(new ImageIcon(imageFile));
        }

        JLabel labelPadding2 = new JLabel("");
        labelPadding2.setPreferredSize(new Dimension(General.LABEL_WIDTH, General.TEXT_HEIGHT));

        JButton btnJoin = new JButton("Join");
        btnJoin.setPreferredSize(new Dimension(General.LABEL_WIDTH, General.TEXT_HEIGHT));
        
        ActionListener joinActionListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String name = textName.getText().strip();

                if (name.length() == 0) {
                    return;
                }
                

                int selectedImageIndex = comboImages.getSelectedIndex();

                File file = new File(General.IMAGES.get(selectedImageIndex));
                try {
 
                    image = ImageIO.read(file);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                Profile p = new Profile(image, name);

                Driver.pm.addProfile(p);
                
                MainPageGUI.run(p);

                frame.dispose();
            }
        };
        btnJoin.addActionListener(joinActionListener);

        panelCenter.add(labelForName);
        panelCenter.add(textName);
        panelCenter.add(labelForImage);
        panelCenter.add(comboImages);
        panelCenter.add(labelPadding2);
        panelCenter.add(btnJoin);
        
    }
    

    private static void adjustSize() {
        panelTop.setPreferredSize(new Dimension(WIDTH, TOP_HEIGHT));
        panelLeft.setPreferredSize(new Dimension(LEFT_WIDTH, CENTER_HEIGHT));
        panelRight.setPreferredSize(new Dimension(RIGHT_WIDTH, CENTER_HEIGHT));
        panelBottom.setPreferredSize(new Dimension(WIDTH, BOTTOM_HEIGHT));

    }

}
