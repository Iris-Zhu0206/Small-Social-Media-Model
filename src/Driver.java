import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;

public class Driver {

    // profile manager
    public static ProfileManager pm = new ProfileManager();
    
    // The main method to run the program
    public static void main(String[] args) {
        boolean ok = makeMockData();
        if (!ok) {
            return;
        }
        
        try {
            RegisterGUI.run();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    

    private static boolean makeMockData() {
        
        // images
        ArrayList<String> images = General.IMAGES;
        
        // names
        ArrayList<String> arrNames = new ArrayList<String>();
        arrNames.add("Zoey");
        arrNames.add("Clara");
        arrNames.add("Jenny");
        arrNames.add("Joyce");
        arrNames.add("Tony");
        
        if (!checkImageFiles(images)) {
            System.err.println("please check whether all images files exist.");
            return false;
        }

        ArrayList<Profile> arrProfiles = new ArrayList<Profile>();
        
        try {
            for (int i = 0; i < arrNames.size(); i++) {
                File file = new File(images.get(i));
                BufferedImage image = ImageIO.read(file);
                
                Profile profile = new Profile(image, arrNames.get(i));
                arrProfiles.add(profile);
                
                pm.addProfile(profile);
            }
        } catch (Exception e) {
            System.out.println("open test images failed.");
        }
        
        for (int i = 1; i < arrProfiles.size(); i++) {
            pm.createFriendship(arrProfiles.get(0), arrProfiles.get(i));
        }
        
        pm.leave(arrProfiles.get(1));
        
        return true;
    }


    private static boolean checkImageFiles(ArrayList<String> arrPath) {
        for (String path : arrPath) {
            File file = new File(path);
            if (!file.exists()) {
                return false;
            }
        }        
        return true;
    }

}
