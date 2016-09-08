package rhynix.eliud.loginandregister;

/**
 * Created by eliud on 9/2/16.
 */

public class User {
    String name,username,password;
    int age;

    public User(String name,int age,String username,String password){

        //storing parameters into variables

        this.name = name;
        this.age = age;
        this.username = username;
        this.password = password;
    }
    public  User(String username,String password){
        this.username = username;
        this.password = password;
        this.age = -1;
        this.name = "";

    }
}
