package com.lukewaugh.paradiddles.authClasses;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;




import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import org.json.JSONObject;
import java.util.ArrayList;




/*
A Class for handling ServerHandler requests
*/
@SuppressWarnings("ALL")
public class ServerHandler {
    /*
    Step 1: Create a loading dialog
    while users wait for the connection
    to the Server.
    */
    ProgressDialog progressDialog;
    /*
    Step 2: Instantiate a variable to
    indicate whether the Server connection
    has times out (in milliseconds).
    */
    public static final int CONNECTION_TIMEOUT = 1000 * 15;
    /*
    Step 3: Instantiate the ServerHandler address to make
    the connection.
    */
    public static final String SERVER_ADDRESS = "http://www.paradiddles.esy.es/";
    /*
    Step 4: Create the constructor to
    instantiate the progress dialog,
    passing context for the Activities
    to use, and setting the attributes.
    */
    public ServerHandler(Context context) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Processing...");
        progressDialog.setMessage("Please Wait");
    }
    /*
    Step 5: Create a method to store user data
    in the Server.
    Start the process dialog to let users know
    their account is being made.
    5.1: Start storing the data in the background
    using the Async class created below.
    The userCallback is invoked when the users
    data is finished storing to the Server
    (in the background).
    */
    public void setUserData(User user, GetUser userCallback) {
        progressDialog.show();
        new StoreUserDataAsyncTask(user, userCallback).execute();
    }
    /*
    Step 6: Create a method to retrieve user
    data from the Server, start the process dialog
    to let users know their account is being
    made.
    The userCallback is invoked when the users data
    is finished retrieving from the Server
    */
    public void getUserData(User user, GetUser userCallback) {
        progressDialog.show();
        new getUserDataAsyncTask(user, userCallback).execute();

    }
    /*
    A Class which stores user data to the
    Server and extends AsyncTask to do so in
    the background and marks the types used by
    the AsyncTask as unused.
    */
public class StoreUserDataAsyncTask extends AsyncTask<Void,Void,Void>{
        User user;
        GetUser userCallback;
        /*
        Step 7: Create a constructor to update when
        the background task has finished.
        */
        public StoreUserDataAsyncTask(User user, GetUser userCallback) {
            this.user = user;
            this.userCallback = userCallback;

        }
        /*
        Step 8: Override a method to execute the
        storage of user data in the background
        by creating the data to be sent to the
        Server in the correct format.
        */
        @Override
        protected Void doInBackground(Void... params) {
            //Todo Find out what Void... params is
            ArrayList<NameValuePair> dataToSend = new ArrayList<>();

            dataToSend.add(new BasicNameValuePair("username", user.username));
            dataToSend.add(new BasicNameValuePair("password", user.password));
            dataToSend.add(new BasicNameValuePair("email", user.email));


            HttpParams httpRequestParams = getHttpRequestParams();


            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost   post = new HttpPost(SERVER_ADDRESS + "Register.php");

            try {
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                client.execute(post);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
        private HttpParams getHttpRequestParams() {
            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams,
                    CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams,
                    CONNECTION_TIMEOUT);
            return httpRequestParams;
        }
        /*
        Step 9: When the AsyncTask has finished
        Override a method to dismiss the process
        dialog and tell the GetUser the task
        has finished.
        */
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            progressDialog.dismiss();
            userCallback.processDone(null);
        }
    }
    /*
    Class for logging in the user in the background
    //Todo
    */
    public class getUserDataAsyncTask extends AsyncTask<Void,Void,User> {
        User user;
        GetUser userCallback;

        public getUserDataAsyncTask(User user, GetUser userCallback) {
            this.user = user;
            this.userCallback = userCallback;
        }

        @Override
        protected User doInBackground(Void... params) {
            ArrayList<NameValuePair> dataToSend = new ArrayList<>();


            dataToSend.add(new BasicNameValuePair("username", user.username));
            dataToSend.add(new BasicNameValuePair("password", user.password));

            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost   post = new HttpPost(SERVER_ADDRESS + "FetchUserData.php");

            User returnedUser = null;
            try {
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                HttpResponse httpResponse = client.execute(post);

                HttpEntity entity = httpResponse.getEntity();
                String result = EntityUtils.toString(entity);

                JSONObject jsonObject = new JSONObject(result);


                if (jsonObject.length() != 0) {
                    Log.v("happened", "2");
                    String email = jsonObject.getString("email");
                    returnedUser = new User(user.username, user.password, email);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return returnedUser;
        }


        @Override
        protected void onPostExecute(User returnedUser) {
            super.onPostExecute(returnedUser);
            progressDialog.dismiss();
            userCallback.processDone(returnedUser);
        }

    }
}
