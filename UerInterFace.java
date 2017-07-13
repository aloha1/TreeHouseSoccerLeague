/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package treehousesoccerleague;

import java.util.*;
import java.io.*;

/**
 *
 * @author zeshengli
 */
public class UerInterFace {
    private Players mPlayers;
    private String team;
    private BufferedReader mReader;
    private Set<String> teamList;
    private List<String> freeAgent;
    private Map<String,String> teamAndCoach;
    private Map<String,String> teamAndPlayer;
    
    Player[] players = Players.load();
    
    private Map<String,String> menu;

    public UerInterFace(){
        mReader = new BufferedReader(new InputStreamReader(System.in));
        teamList = new TreeSet<>();
        teamAndCoach = new HashMap<>();
        menu = new HashMap<String,String>();
        teamAndPlayer = new HashMap<>();
        freeAgent = new ArrayList<>();
        
        menu.put("Create", "   Create a new team");
        menu.put("Add", "      Add player to a team");
        menu.put("Remove", "   Remove player from a team");
        menu.put("FreeAgent", "Free Agent available");
        menu.put("Quit", "     Exit the grogram");
    }
    
    
    private String promptAction() throws IOException{
        for(Map.Entry <String,String> dis : menu.entrySet()){
            System.out.printf("%s %s%n",dis.getKey(),dis.getValue());
        }
        String choice = mReader.readLine();
        return choice.trim().toLowerCase();
    }
    
    
    public void run() throws IOException{
        String choice = " ";
        do{
            choice = promptAction();
            switch(choice){
            case "create":
                createTeam();
                break;
            case "add":
                addPlayerToTeam();
                break;
            case "remove":
                
                break;
            case "freeagent":
                freeAgent();
                break;
            case "quit":
                System.out.println("Thank you for using!");
                break;
            default:
                System.out.printf("%s is not a vaild input %n",choice);
                break;
            
            }
        
        }while(!choice.equals("quit"));
    }

    public void freeAgent(){
        for(Player player: players){
            System.out.printf("%s,%s---%s,(%s)%n",
                              player.getFirstName(),
                              player.getLastName(),
                              player.getHeightInInches(),
                              player.isPreviousExperience());
        }
        
        
    }
    
    public void createTeam() throws IOException{
        System.out.println("--------------------------------");
        String teamName;
        //keep looping util user enter a team name that is not in the teamList
        do{
            System.out.print("What is the name of the team: ");
            teamName = mReader.readLine();
            if(teamList.contains(teamName) == true){
            System.out.print("team already exist.%n");
            }
            
        }while(teamList.contains(teamName)== true );
        
        System.out.printf("who is the coach of the team %s: ", teamName);
        String coachName = mReader.readLine();
        teamList.add(teamName);
        teamAndCoach.put(teamName, coachName);
        
        System.out.printf("%s Team created with coach %s. return to Menu. %n",
                          teamName,
                          coachName
                            );
    }
    
    public void addPlayerToTeam() throws IOException{
        /*
        List<String> firstNameList = new ArrayList<>();
        List<String> lastNameList = new ArrayList<>();
        String firstName;
        String lastName;
        int height;
        boolean exp;
        
        System.out.println("Players choose a team");
        team = mReader.readLine();
        System.out.println("Here is the free agent you can choose: ");
        freeAgent();
        do{
            System.out.println("Please Enter the First Name of the Player: ");
            firstName = mReader.readLine().toLowerCase();
            if(firstNameList.contains(firstName)==true){
                System.out.println("Can not fine first name. ");
            }
        }while(firstNameList.contains(firstName)==true);
        
        do{
            System.out.println("Please enter the Last Name of the Player: ");
            lastName = mReader.readLine().toLowerCase();
            if(firstNameList.contains(lastName)==true){
                System.out.println("Can not fine last name. ");
            }
        }while(firstNameList.contains(lastName)==true);
        */
        
        
        
        
            
        
        
        
        
       
        
    }
}
