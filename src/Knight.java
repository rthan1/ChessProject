import javax.swing.*;
import java.awt.*;

/********************************************************************
 * Knight.java   	    				                 	        *
 * 				                  		    		                *
 * PROGRAMMER: 	Ethan Nelson							            *
 * COURSE: 		CS201				         				        *
 * DATE: 		5/6/2024 		                  		            *
 * REQUIREMENT: Final Project   			      		            *
 * 							         				                *
 * DESCRIPTION:					         			                *
 * Program is used to represent a knight in the chess game          *
 * * COPYRIGHT: This code is copyright (C) 2023 Ethan      	        *
 * Nelson and Dean Zeller.          			                    *
 * CREDITS: This code was written with the help of                  *
 * ChatGPT and Shawn Nelson						         			*
 *******************************************************************/
public class Knight extends Piece
{
    public Knight(boolean black) {
        super(black);
    }

    /***********************************************************
     * METHOD: validMove (Knight)  				       		   *
     * DESCRIPTION:Makes sure the Knights moves are allowed	   *
     * PARAMETERS: ChessBoard board, Spot start, Spot end  	   *
     * RETURN VALUE: Boolean    		 		       		   *
     **********************************************************/
    @Override
    public boolean validMove(ChessBoard board, Spot start, Spot end) {
        if(end.getPiece() != null && end.getPiece().isBlack() == this.isBlack()) {
            return false;
        }
        int x = Math.abs(start.getX()-end.getX());
        int y = Math.abs(start.getY()-end.getY());
        if (x*y == 2)
            return true;
        else
            return false;
    }

    /***********************************************************
     * METHOD: draw()           				       		   *
     * DESCRIPTION: Draws the Knight which is represented as k *
     * PARAMETERS: Double centerX, double centerY       	   *
     * RETURN VALUE: Void       		 		       		   *
     **********************************************************/
    @Override
    public JLabel draw(boolean black, int size) {
        JLabel label = new JLabel();
        if(black) {
            ImageIcon pieceIcon = new ImageIcon(getClass().getResource("/BlackKnight.png"));
            Image img = pieceIcon.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH);
            label.setIcon(new ImageIcon(img));
        }
        else{
            ImageIcon pieceIcon = new ImageIcon(getClass().getResource("/WhiteKnight.png"));
            Image img = pieceIcon.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH);
            label.setIcon(new ImageIcon(img));
        }
        return label;
    }

}
