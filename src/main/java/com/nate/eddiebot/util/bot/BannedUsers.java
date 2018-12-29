package com.nate.eddiebot.util.bot;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BannedUsers {

    /**
     * Gets the text file filled with user ids
     * 
     * @return The text file filled with user ids
     * @author Nate Sedler
     */
    private File getFile() {
        return new File("BannedUsersIDs.txt");
    }

    /**
     * Reads through the text file from getFile()
     * 
     * @return A String list with all banned users
     * @author Nate Sedler
     */
    public List<String> readBannedUsers() {

        List<String> lines = new ArrayList<String>();

        try{

            BufferedReader bReader = new BufferedReader(new FileReader(getFile()));
            for (String line; (line = bReader.readLine()) != null;) {
                lines.add(line);
            }
            bReader.close();

        }catch(FileNotFoundException e){
            // process errors
        }catch(IOException e){
            // process errors
        }
        return lines;
    }

    /**
     * Adds a banned users id to the BannedUsersIDs.txt file 
     *
     * @author Nate Sedler
     */
    public void addBannedUser(Long userID) {

        try {
            FileWriter fw = new FileWriter(getFile().getPath(), true);
            if(checkUser(userID)) {
                fw.write(userID + "\n");
                fw.close();
            }
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
        }
    }
    
    /**
     * Removes a banned users if from the BannedUsersIDs.txt file
     * 
     * @author Nate Sedler
     */
    public void removeBannedUser(Long userID){
        
        List<String> ids = new ArrayList<String>();

        try{

        	BufferedReader bReader = new BufferedReader(new FileReader(getFile()));

            for (String line; (line = bReader.readLine()) != null;) {
                ids.add(line);
            }

            ids.removeIf(e -> e.equals(String.valueOf(userID)));

            FileWriter fw = new FileWriter(getFile().getPath(), false);

            for(String id : ids){
                fw.write(id + "\n");
                fw.close();
            }
            bReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks if a users id is already inside the BannedUsersIDs.txt file
     * 
     * @return If the id is inside BannedUsersIDs.txt
     * @author Nate Sedler
     */
    private boolean checkUser(Long userID){

        try {
            BufferedReader bReader = new BufferedReader(new FileReader(getFile()));

            for (String line; (line = bReader.readLine()) != null;) {
                if(line.equals(String.valueOf(userID)))
                    return false;
            }
            bReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
