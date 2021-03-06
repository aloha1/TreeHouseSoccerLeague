/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package treehousesoccerleague;

/**
 *
 * @author zeshengli
 *
 */

import java.io.Serializable;
import java.util.*;

public class Player implements Comparable<Player>, Serializable {
  private static final long serialVersionUID = 1L;

  private String firstName;
  private String lastName;
  private String fullName;
  private int heightInInches;
  private boolean previousExperience;
  
  

  public Player(String firstName, String lastName, int heightInInches, boolean previousExperience) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.fullName = firstName + lastName;
    this.heightInInches = heightInInches;
    this.previousExperience = previousExperience;
  }

 
  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getFullName() {
    return fullName;
  }
   
  public int getHeightInInches() {
    return heightInInches;
  }

  public boolean isPreviousExperience() {
    return previousExperience;
  }
  
  
  
  @Override
  public String toString(){
      String value;
      value =this.fullName + " Height: " + this.heightInInches 
            + " Pervious Experience: " + this.previousExperience;
      return value;
  }
   
  @Override
  public int compareTo(Player other) {
    // We always want to sort by last name then first name
    return 0;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Player)) return false;

    Player player = (Player) o;

    if (heightInInches != player.heightInInches) return false;
    if (previousExperience != player.previousExperience) return false;
    if (!firstName.equals(player.firstName)) return false;
    return lastName.equals(player.lastName);

  }

  @Override
  public int hashCode() {
    int result = firstName.hashCode();
    result = 31 * result + lastName.hashCode();
    result = 31 * result + heightInInches;
    result = 31 * result + (previousExperience ? 1 : 0);
    return result;
  }
}


