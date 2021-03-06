/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucdenver.diamond.jeremy.firstapp;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.ArrayList;

/**
 *
 * @author jeremy
 */
public class togglAccount {
                            
    private String uName = "1234567890abcdefghijklmnopqrstuv";
    private String password = "api_token";
    private ArrayList <togglProject> projects = new ArrayList();
    
    private Universal_JSON_Body_Http_Methods utility = new Universal_JSON_Body_Http_Methods() ;
    
    public void currentTimer () throws ProtocolException, IOException
    {
        String output = utility.Universal_Get("https://www.toggl.com/api/v8/time_entries/current",uName,password);
        //System.out.println(output);
        
        if ("{\"data\":null}".equals(output))
        {
            System.out.println("no current timer");
            
        }
        else
        {
        
        int startLoc = output.indexOf("description");
        startLoc += ("description".length() + 3);
        int endLoc = output.indexOf("\"", startLoc);
        
        String description = output.substring(startLoc, endLoc);
        //System.out.println(description);
        
        startLoc = output.indexOf("pid");
        startLoc += ("pid".length() + 2);
        endLoc = output.indexOf(",", startLoc);
        
        String pid = output.substring(startLoc, endLoc);
        //System.out.println(pid);
        
        startLoc = output.indexOf("duration");
        startLoc += ("duration".length() + 2);
        endLoc = output.indexOf(",", startLoc);
        
        
        String duration = output.substring(startLoc, endLoc);
        long negitiveDuration = Long.parseLong(duration);
        long epoch = System.currentTimeMillis()/1000;
        
        long numofsec = epoch + negitiveDuration ;
        long numofmin = numofsec/60;
        numofsec = numofsec - (numofmin*60);
        long numofhr = numofmin/60;
        numofmin = numofmin - (numofhr*60);
        
        
        output = utility.Universal_Get("https://www.toggl.com/api/v8/projects/"+pid,uName,password);
        //System.out.println(output);
        
        startLoc = output.indexOf("name");
        startLoc += ("name".length() + 3);
        endLoc = output.indexOf("\"", startLoc);
        
        String name = output.substring(startLoc, endLoc);
        System.out.println(name);
        
        System.out.println(numofhr + ":" + numofmin + ":" + numofsec);
        }
    }
    
    public void stopCurrent () throws ProtocolException, IOException
    {
        String output = Universal_JSON_Body_Http_Methods.Universal_Get("https://www.toggl.com/api/v8/time_entries/current",uName,password);
        System.out.println(output);
        
        if ("{\"data\":null}".equals(output))
        {
            System.out.println("no current timer");
            
        }
        else
        {
        int startLoc = output.indexOf("id");
        startLoc += ("id".length() + 2);
        int endLoc = output.indexOf(",", startLoc);
        
        String id = output.substring(startLoc, endLoc);
        
        output = Universal_JSON_Body_Http_Methods.Universal_Get("https://www.toggl.com/api/v8/time_entries/"+id+"/stop",uName,password);
        System.out.println(output);
        }
    }
    
    public void readAllProjects() throws ProtocolException, IOException
    {
        togglProject tempProject;
        String id;
        String name;
        
        String output  = Universal_JSON_Body_Http_Methods.Universal_Get("https://www.toggl.com/api/v8/me",uName,password);
        //System.out.println(output); 
        
        int startLoc = output.indexOf("wid");
        startLoc += ("wid".length() + 2);
        int endLoc = output.indexOf(",", startLoc);
        
        String wid = output.substring(startLoc, endLoc);
        
        output = Universal_JSON_Body_Http_Methods.Universal_Get("https://www.toggl.com/api/v8/workspaces/"+ wid +"/projects",uName,password);
        
        //System.out.println(output);
        
        while (output.contains("name"))
        {
        
            startLoc = output.indexOf("id");
            startLoc += ("id".length() + 2);
            endLoc = output.indexOf(",", startLoc);

            id = output.substring(startLoc, endLoc);

            startLoc = output.indexOf("name");
            startLoc += ("name".length() + 3);
            endLoc = output.indexOf("\"", startLoc);

            name = output.substring(startLoc, endLoc);

            output = output.substring(endLoc);
            
            tempProject = new togglProject(id,name);
            projects.add(tempProject);
        }
    }
    
    public void startATimer(int projectIndex, int desriptionIndex) throws MalformedURLException, IOException
    {
        Universal_JSON_Body_Http_Methods.Universal_Post("https://www.toggl.com/api/v8/time_entries/start","{\"time_entry\":{\"description\" : \""+projects.get(projectIndex).descriptions.get(desriptionIndex)+"\", \"pid\" : "+ projects.get(projectIndex).id + ", \"created_with\" : \"java\"}}", uName, password);
    }
}
