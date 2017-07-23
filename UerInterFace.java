/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package treehousesoccerleague;

import java.util.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author zeshengli
 */
public class UerInterFace {
    
    Player[] players = Players.load();
    Scanner sc = new Scanner(System.in);
    
    private Set<String> teamList;
    private List<Player> allPlayer;
    private Map<Integer,Player> numWithPlayer;
    Map<String,String> teamAndCoach;
    Map<String,Map<Integer,Player>> teamWithPlayer;
    Map<Integer, Player> temp;
    
    
    private Map<String,String> menu;

    public UerInterFace(){
        numWithPlayer = new HashMap<>();
        teamList = new TreeSet<>();
        allPlayer = new ArrayList<>();
        teamAndCoach = new HashMap<>();
        teamWithPlayer = new HashMap<>();
        temp = new HashMap<>();
        
        
        
        
        
        
        
        
        menu = new HashMap<String,String>();
        
        menu.put("Create",   "Create a new team");
        menu.put("Add", "Add player to a team");
        menu.put("remove", "Remove player from a team");
        menu.put("Roster","Roster Report");
        menu.put("HR","Height report of a team");
        menu.put("BR","League Balance Report");
        menu.put("TR","Team Roster Report");
        menu.put("Quit", "Exit the grogram");
        
        
        
        allPlayer = Arrays.asList(players);
        for(int i = 0; i<players.length;i++){
            numWithPlayer.put(i, allPlayer.get(i));
        }
    }
    
    
    public void run(){
        String choice;
        do{
            
            choice = promptAction();
            switch(choice){
            
            case "create":
                createTeam();
                break;
            
            case "hr":
                GroupByHeight();
                break;    
            
            case "br":
                BalanceReport();
                break;    
            
            case "tr":
                RosterOfTeam();
                break;
                
            case "add":
                addPlayerToTeam();
                break;
            
            case "remove":
                removePlayerFromTeam();
                break;
            
            case "roster":
                displayAllPlayer();
                break;
            
            case "quit":
                System.out.println("Thank you for using!");
                break;
            
            default:
                System.out.println("Not a valid input. Please Chose again. ");
                break;
            
            }
        
        }while(!choice.equals("quit"));
    }
    
    
    
    
    
    private String promptAction(){
        System.out.println("-------------------------------");
        for(String key : menu.keySet() ){
            System.out.printf("%s-%s\n",key,menu.get(key));
        }
        String choice = sc.nextLine();
        System.out.println("-------------------------------");
        return choice.toLowerCase();
        
    }
    
    
    

    public void displayAllPlayer(){
        
        System.out.println("-------------------------------");
        for(int i = 0; i<players.length;i++){
            System.out.printf("%s - %s\n",i,allPlayer.get(i));
        }
        System.out.println("-------------------------------");   
    }
    
    public void createTeam(){
        String teamName;
        String coachName;
        
        System.out.println("-------------------------------");
        do{
        System.out.println("What is the team name: ");
        teamName = sc.nextLine();
        }while(teamAndCoach.containsKey(teamName)== true);
        do{
        System.out.println("What is the coach name: ");
        coachName = sc.nextLine();
        }while(teamAndCoach.containsValue(coachName)== true);
        
        teamAndCoach.put(teamName, coachName);
        System.out.printf("%s with coach %s is added.\n",teamName,coachName);
        System.out.println("-------------------------------");
    }
    
    public void addPlayerToTeam(){
        String team = " ";
        int playerNumber;
        
        System.out.println("-------------------------------");
        for(int i =0; i<players.length; i++){
        System.out.printf("%s- %s\n",i,allPlayer.get(i));
        }
        //System.out.print(numWithPlayer);
        do{
            System.out.printf("%s\n",teamAndCoach.keySet());
            System.out.println("Choose a team to add player?");
            team = sc.nextLine();
            if(teamAndCoach.containsKey(team)== false)
            {
                System.out.println("Please chose a team from the list.");
            }
        }while(teamAndCoach.containsKey(team) == false);
         
        do{
            System.out.println("Please Chose a Player Number");
            playerNumber = sc.nextInt();
            if(numWithPlayer.containsKey(playerNumber) == false)
            {
                System.out.println("Please chose a number from the list.");
            }
        }while(numWithPlayer.containsKey(playerNumber) == false);
        
        
       
        
            //if teamWithPlayer is contain data do
            if(teamWithPlayer.containsKey(team)){
            Map<Integer, Player> newTemp = new HashMap<>();
            newTemp = teamWithPlayer.get(team);
            temp = newTemp;
            
            temp.put(playerNumber, numWithPlayer.get(playerNumber));
            teamWithPlayer.put(team,temp);
          
            }
            //if teamWithPlayer do no contain data do
            else
            {
            Map<Integer, Player> newTemp = new HashMap<>();
            newTemp.put(playerNumber, numWithPlayer.get(playerNumber));
            teamWithPlayer.put(team,newTemp);
            
            }
        
       
        System.out.printf("%s\n",teamWithPlayer);
        System.out.println("-------------------------------");
        
    }
    
    public void removePlayerFromTeam(){
       String team;
       int playerNumber;
       
       System.out.println("-------------------------------");
       System.out.println(teamAndCoach.keySet());
       System.out.println("Please chose the team you want to remove player from.");
       team = sc.nextLine();
       System.out.printf("%s\n",teamWithPlayer.get(team));
       System.out.println("Please chose the number of the player you want to remove");
       playerNumber = sc.nextInt();
       
       
        Map<Integer,Player> tempOutput = new HashMap<>();
        
        tempOutput = teamWithPlayer.get(team);
        
        tempOutput.remove(playerNumber);
        teamWithPlayer.put(team, tempOutput);
        System.out.printf("Player %s is remove from %s. Now %s will be %s\n.",
                          playerNumber,
                          team,
                          team,
                          teamWithPlayer
                          );
        
        System.out.println("-------------------------------");       
    }
    
    
    
    public void GroupByHeight(){
        String team;
        List<Player> playerHeighG1= new ArrayList<>();
        List<Player> playerHeighG2= new ArrayList<>(); 
        List<Player> playerHeighG3= new ArrayList<>();
        
       
        
        System.out.println("-------------------------------");
        System.out.println(teamWithPlayer.keySet());
        System.out.println("Which team do you want to see the report?");
        team = sc.nextLine();
        
        Map<Integer,Player> tempGBH = teamWithPlayer.get(team);
       
        for(Player player: tempGBH.values()){
            
            if(player.getHeightInInches()>= 35 && player.getHeightInInches()<=40){
                playerHeighG1.add(player);
            }
            if(player.getHeightInInches()>= 41 && player.getHeightInInches()<=46){
                playerHeighG2.add(player);
            }
            if(player.getHeightInInches()>= 47 && player.getHeightInInches()<=50){
                playerHeighG3.add(player);
            }
        }        
      
        System.out.printf("Player of %s Group By Height :\n",team);
        System.out.printf("Height 35-40: %s\n",playerHeighG1);
        System.out.printf("Height 41-46: %s\n",playerHeighG2);
        System.out.printf("Height 47-50: %s\n",playerHeighG3);
        System.out.println("-------------------------------");
    }
    
    public void BalanceReport(){
        Map<String,Integer> teamWithExp = new HashMap<>();
        Map<String,Integer> teamWithoutExp = new HashMap<>();
        
        Map<String,List<Integer>> BR = new HashMap<>();
        int[] exp;
        System.out.println("-------------------------------");
        
        for(String teamName: teamWithPlayer.keySet()){
            Map<Integer,Player> tempBR = teamWithPlayer.get(teamName);
            int withExp=0;
            int withoutExp=0;
            for(Player player : tempBR.values()){
                List<Player> playerBRG1 = new ArrayList<>();
                List<Player> playerBRG2 = new ArrayList<>();
                if(player.isPreviousExperience() == true){
                    withExp++;
                }
                else{
                    withoutExp++;
                }
                
                teamWithExp.put(teamName, withExp);
                teamWithoutExp.put(teamName, withoutExp);
            }
             
        }
       
       for(String teamName:teamWithExp.keySet()){
       System.out.printf("team:%s with Experience Player %s and with Inexperience %s\n",
               teamName,
               teamWithExp.get(teamName),
               teamWithoutExp.get(teamName));
       }
        
       System.out.println("-------------------------------");
    }
    
    public void RosterOfTeam(){
    String team;
            
    System.out.println("-------------------------------");
    do{
    System.out.println(teamWithPlayer.keySet());
    System.out.println("Which team of Roster do you want to print out?");
    team = sc.nextLine();
    if(teamWithPlayer.containsKey(team) == false){
        System.out.println("Sorry, does not have this team.");
    }
    }while(!teamWithPlayer.containsKey(team));
    
    
    System.out.printf("%s have player: ",team);
    for(Map<Integer,Player> player: teamWithPlayer.values()){
        System.out.printf("%s\n",player);
    }
    
    }
    
    
    
}
