package login;

import read_writer.DictionaryReadWriter;

import java.io.File;
import java.util.HashMap;

public class SetIDandPasswordsGateway {

    /**
     * Set username and password to the dictionary
     * @param username string username
     * @param password string password
     */

    public void setUsernamePasswordHash(String username, String password){
        File file = new File("Main/username_password.ser");
        if (file.length() == 0){
            DictionaryReadWriter rw = new DictionaryReadWriter();
            HashMap<String, String> newHashMap = new HashMap<>();
            newHashMap.put(username, password);
            rw.saveToFile("Main/username_password.ser", newHashMap);
        }
        else{
            // access the serialized file for this user.
            DictionaryReadWriter rw = new DictionaryReadWriter();
            HashMap<String, Object> usersPassSavedDict = rw.readFromFile("Main/username_password.ser");
            usersPassSavedDict.put(username, password);
            rw.saveToFile("Main/username_password.ser", usersPassSavedDict);
        }

    }
}
