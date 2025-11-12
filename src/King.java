import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/********************************************************************
 * King.java   	        				                 	        *
 * 				                  		    		                *
 * PROGRAMMER: 	Ethan Nelson							            *
 * COURSE: 		CS201				         				        *
 * DATE: 		5/6/2024 		                  		            *
 * REQUIREMENT: Final Project   			      		            *
 * 							         				                *
 * DESCRIPTION:					         			                *
 * Program is used to represent a king in the chess game            *
 * * COPYRIGHT: This code is copyright (C) 2023 Ethan      	        *
 * Nelson and Dean Zeller.          			                    *
 * CREDITS: This code was written with the help of                  *
 * ChatGPT and Shawn Nelson						         			*
 *******************************************************************/
public class King extends Piece
{
    public King(boolean black) {
        super(black);
    }

    /***********************************************************
     * METHOD: validMove (King)  				       		   *
     * DESCRIPTION:Makes sure the kings moves are allowed	   *
     * PARAMETERS: ChessBoard board, Spot start, Spot end  	   *
     * RETURN VALUE: Boolean    		 		       		   *
     **********************************************************/
    @Override
    public boolean validMove(ChessBoard board, Spot start, Spot end) {
        if (end.getPiece() != null && end.getPiece().isBlack() == this.isBlack()) {
            return false;
        }
        int x = Math.abs(start.getX() - end.getX());
        int y = Math.abs(start.getY() - end.getY());
        if ((x == 1 && y == 0) || (x == 1 && y == 1) || (x == 0 && y == 1)) {
            return true;
        }
        return x == 2 && !start.getPiece().getHasMoveD() && y == 0;
    }


    /***********************************************************
     * METHOD: draw()           				       		   *
     * DESCRIPTION: Draws the King represented as a cross      *
     * PARAMETERS: Double centerX, double centerY       	   *
     * RETURN VALUE: Void       		 		       		   *
     **********************************************************/
    @Override
    public JLabel draw(boolean black, int size) {
        JLabel label = new JLabel();
        if(black) {
            ImageIcon pieceIcon = new ImageIcon(getClass().getResource("/BlackKing.png"));
            Image img = pieceIcon.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH);
            label.setIcon(new ImageIcon(img));
        }
        else{
            ImageIcon pieceIcon = new ImageIcon(getClass().getResource("/WhiteKing.png"));
            Image img = pieceIcon.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH);
            label.setIcon(new ImageIcon(img));
        }
        return label;
    }
}

