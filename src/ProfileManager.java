import java.util.ArrayList;

import java.util.Iterator;


import java.awt.image.*;

/**
An implementation of a profile manager on a simple social network.

@author Jesse Grabowski
@author Joseph Erickson
@version 5.0 */
public class ProfileManager {
	private UndirectedGraph<Profile> allProfiles;
	
	/** Constructor for an instance of a profile manager. */
	ProfileManager(){
		allProfiles = new UndirectedGraph<>();
	}
	
	/** Adds a profile to the social network.
	@param p  The profile to be added to the network. */
	public void addProfile(Profile p) {
		p.setStatus("Online");
		int index = allProfiles.addVertex(p);
		p.setIndex(index);
	}
	
	/** Creates a friendship between two users on the social network.
	@param a  The first profile in the friendship.
	@param b  The second profile in the friendship. */
	public void createFriendship(Profile a, Profile b)
	{
		int indexa = a.getIndex();
		int indexb = b.getIndex();
		allProfiles.addEdge(indexa, indexb);
	} // end createFriendship
	
	public ArrayList<Profile> search(String keyword){
		return allProfiles.search(keyword);
	}
	
	public boolean hasFriendship(Profile a, Profile b) {
		int indexa = a.getIndex();
		int indexb = b.getIndex();
		
		return allProfiles.hasEdge(indexa, indexb);
	}
	
	public void leave(Profile profile) {
		profile.setStatus("Offline");
		int index = profile.getIndex();
		allProfiles.updateVertex(index, profile);
	}
	
	public ArrayList<Profile> getFriendship(Profile profile){
		ArrayList<Profile> results = new ArrayList<Profile>();
		int index = profile.getIndex();
		results = allProfiles.getAdjacentVertexes(index);
		return results;
	}
	
	public boolean isSelf(Profile a, Profile b) {
		return(a.getIndex() == b.getIndex());
	}
}
