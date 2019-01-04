package edu.ucdenver.diamond.jeremy.firstapp;

import android.os.AsyncTask;

import java.io.IOException;

public class NetworkBackgroundRunTest extends AsyncTask<Void,Void,Void> {
    togglAccount myAccount = new togglAccount();
    @Override
    protected Void doInBackground(Void... voids) {
        try {
            myAccount.readAllProjects();
            myAccount.startATimer(0,0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

