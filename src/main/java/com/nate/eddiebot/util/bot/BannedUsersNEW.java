package com.nate.eddiebot.util.bot;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Nate Sedler
 */
public class BannedUsersNEW {

	private HashMap<Long, String> bannedUsers = new HashMap<>();

	public void addUser(Long id , String name) {
		if(!bannedUsers.containsKey(id))
			bannedUsers.put(id, name);
	}

	public HashMap<Long, String> getMap() {
		return bannedUsers;
	}

	public String toString() {
		String r = "";
		for(Map.Entry<Long, String> entry : bannedUsers.entrySet()) {
			r += entry.getKey() + " : " + entry.getValue();
		}
		return r;
	}
}
