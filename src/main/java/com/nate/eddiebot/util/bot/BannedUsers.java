package com.nate.eddiebot.util.bot;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BannedUsers {

    private File getFile() {
        return new File("BannedUsersIDs.txt");
    }

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
