package rhynix.eliud.loginandregister;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by eliud on 9/2/16.
 */

public class UserLocalStore {
    //userdatails

    public static final String SP_NAME = "userDetails";
    SharedPreferences userLocalDatabase;//sharedprefference is used to store data on tha phone

//constracter

    public UserLocalStore(Context context){
        userLocalDatabase = context.getSharedPreferences(SP_NAME,0);
    }

    //Methods for storing and getting data from sharedperefference
    public void storeUserData(User user){

        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putString("name",user.name);
        spEditor.putInt("age",user.age);
        spEditor.putString("username",user.username);
        spEditor.putString("password",user.password);
        spEditor.commit();
    }
    public  User getLoggedInUser(){
        String name = userLocalDatabase.getString("name","");
        int age = userLocalDatabase.getInt("age",-1);
        String username = userLocalDatabase.getString("username","");
        String password = userLocalDatabase.getString("password","");

        User storedUser = new User(name,age,username,password);

        return storedUser;

    }

    public void setUserLoggedIn(boolean loggedIn){

        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putBoolean("loggedIn",loggedIn);
        spEditor.commit();

    }
    public boolean getUserLoggedIn(){

        if (userLocalDatabase.getBoolean("loggedIn" ,false)== true){
            return true;
        }else {
            return false;
        }
    }

    public void clearUserData(){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.clear();
        spEditor.commit();

    }

}
