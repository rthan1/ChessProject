/********************************************************************
 * Spot.java   	    				                 	            *
 * 				                  		    		                *
 * PROGRAMMER: 	Ethan Nelson							            *
 * COURSE: 		CS201				         				        *
 * DATE: 		5/6/2024 		                  		            *
 * REQUIREMENT: Final Project   			      		            *
 * 							         				                *
 * DESCRIPTION:					         			                *
 * Program is used to represent one square of the chess board       *
 * * COPYRIGHT: This code is copyright (C) 2023 Ethan      	        *
 * Nelson and Dean Zeller.          			                    *
 * CREDITS: This code was written with the help of                  *
 * ChatGPT and Shawn Nelson						         			*
 *******************************************************************/
public class Spot
{
    private Piece piece;
    private int x;
    private int y;
    public Spot(Piece piece, int x, int y) {
        this.piece = piece;
        this.x = x;
        this.y = y;
    }
    public Piece getPiece() {
        return this.piece;
    }
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
    public void setPiece(Piece piece) {
        this.piece = piece;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setX(int x) {
        this.x = x;
    }

    /***********************************************************
     * METHOD: equals()            				       		   *
     * DESCRIPTION:used to check if a spot is equal to another *
     * PARAMETERS: Object obj                           	   *
     * RETURN VALUE: Boolean    		 		       		   *
     **********************************************************/
    @Override
    public boolean equals(Object object) {
        if(object.getClass() == Spot.class)
            return false;

        Spot spot = (Spot) object;
        return spot.getX() == this.getX() && spot.getY() == this.getY();
    }

}


