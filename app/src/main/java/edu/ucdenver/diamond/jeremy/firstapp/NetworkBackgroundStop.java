package edu.ucdenver.diamond.jeremy.firstapp;

import android.os.AsyncTask;

import java.io.IOException;


public class NetworkBackgroundStop extends AsyncTask <Void,Void,Void>{
togglAccount myAccount = new togglAccount();
    @Override
    protected Void doInBackground(Void... voids) {
        try {
            myAccount.stopCurrent();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
