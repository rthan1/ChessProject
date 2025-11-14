/********************************************************************
 * Player.java   	    				                 	        *
 * 				                  		    		                *
 * PROGRAMMER: 	Ethan Nelson							            *
 * COURSE: 		CS201				         				        *
 * DATE: 		5/6/2024 		                  		            *
 * REQUIREMENT: Final Project   			      		            *
 * 							         				                *
 * DESCRIPTION:					         			                *
 * Program is used to show off players color and name               *
 * * COPYRIGHT: This code is copyright (C) 2023 Ethan      	        *
 * Nelson and Dean Zeller.          			                    *
 * CREDITS: This code was written with the help of                  *
 * ChatGPT and Shawn Nelson						         			*
 *******************************************************************/
public class Player {
    public boolean blackSide;
    public String name;
    public boolean inCheck;
    public Player(boolean isBlack, String name) {
        this.name = name;
        blackSide = isBlack;
        inCheck = false;
    }

    /***********************************************************
     * METHOD: isBlackSide()      				       		   *
     * DESCRIPTION:Checks the color of the player        	   *
     * PARAMETERS: none                                 	   *
     * RETURN VALUE: Boolean    		 		       		   *
     **********************************************************/
    public boolean isBlackSide() {
        return this.blackSide;
    }
    public void setInCheck(boolean check) {
        this.inCheck = check;
    }
    public boolean isInCheck() {
        return this.inCheck;
    }
}
