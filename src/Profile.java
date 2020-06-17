import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.*;

public class Profile {
	private String status;
	private BufferedImage picture;
	private String name;
	
	private int index;
	
	/** Constructor for an instance of a profile. */
	public Profile(BufferedImage imageParam, String nameParam) {
		picture = imageParam;
		name = nameParam;
		
		status = "Offline";
		index = -1;
	}
	
	/** Returns the picture associated with the profile.
	@return  The profile's picture. */
	public BufferedImage getProfilePicture() {
		return picture;
	}
	
	/** Sets the picture associated with the profile to the given picture.
	@param newPicture  The new picture associated with the profile. */
	public void setProfilePicture(BufferedImage newPicture) {
		picture = newPicture;
	}
	
	/** Sets the name associated with the profile to the given name.
    @param firstName  The first name for the profile.
    @param lastName   The last name for the profile. */
	public void setName(String name) {
		this.name = name;
	}
	
	/** Returns the name associated with the profile.
    @return  The profile's name. */
	public String getName() {
		return name;
	}
	
	/** Sets the current status of the profile to the given string.
	@param stat  The new status for the profile. */
	public void setStatus(String stat) {
		status = stat;
	}
	
	/** Returns the status associated with the profile.
	@return  The profile's status.*/
	public String getStatus() {
		return status;
	}
	
	public void setIndex(int index) {
		this.index = index;
	}
	
	public int getIndex() {
		return index;
	}
	
	public String toString() {
		return name;
	}

}
