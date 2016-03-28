package com.lukewaugh.paradiddles.authClasses;


import android.content.Context;
import android.content.SharedPreferences;



/*
Java Class for manipulating user details to
a file stored on the phone:
'SharedPreferences'.
*/
class UserLocal {
    /*
    Step 1:
    Create the variable of the file where details
    will be stored.
    */
    private static final String SP_NAME = "userDetails";
    /*
    Step 2:
    Instantiate the SharedPreferences Class
    for accessing, modifying and adding data on the users phone.
    */
    private final SharedPreferences userDB;
    /*
    Step 3:
    Get the context from the Activity which will be using this
    Class.
    */
    public UserLocal(Context context) {
        userDB = context.getSharedPreferences(SP_NAME, 0);
    }
    /*
    Step 4:
    Generate methods to retrieve and set the user data
    from the local database.
    =================================================
    4.1: Create a method to store the User data
    by instantiating a variable type of
    object SharedPreferences.
    Modifications MUST go through an
    SharedPreferences.Editor object to maintain
    consistent state and control when they are
    committed to the local storage.
    */
    public void storeUserData(User user) {
        SharedPreferences.Editor editor = userDB.edit();
        editor.putString("username", user.username);
        editor.putString("password", user.password);
        editor.putString("email", user.email);

        editor.apply();
    }
    /*
    4.3: Create a method to set whether the User
    is logged in or out, use a boolean to return
    true or false.
    */
    public void setLoggedInUser(boolean loggedIn) {
        SharedPreferences.Editor editor = userDB.edit();
        editor.putBoolean("loggedIn", loggedIn);

        editor.apply();
    }
    /*
    4.4: Create a method to return whether the
    user is logged in to the local database
    */
    public boolean getUserLoggedIn() {
        //if the user is logged in
        return userDB.getBoolean("loggedIn", false);
    }
    /*
    4.4: Create a method to clear the user data
    from the SharedPreferences object
    */
    public void clearData() {
        SharedPreferences.Editor editor = userDB.edit();
        editor.clear();
        editor.apply();
    }
    /*
    =================================================
    4.2: Create a method to return the a User
    who is logged in to the database by
    getting the attributes from the
    SharedPreferences object.
    */
    public User getLoggedInUser() {
        String username = userDB.getString("username", "");
        String email    = userDB.getString("email", "");
        String password = userDB.getString("password", "");

        return new User(username, password, email);

    }
}
