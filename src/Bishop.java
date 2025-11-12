import javax.swing.*;
import java.awt.*;

/********************************************************************
 * Bishop.java   	    				                 	        *
 * 				                  		    		                *
 * PROGRAMMER: 	Ethan Nelson							            *
 * COURSE: 		CS201				         				        *
 * DATE: 		5/6/2024 		                  		            *
 * REQUIREMENT: Final Project   			      		            *
 * 							         				                *
 * DESCRIPTION:					         			                *
 * Program is used to represent a bishop in the chess game          *
 * * COPYRIGHT: This code is copyright (C) 2023 Ethan      	        *
 * Nelson and Dean Zeller.          			                    *
 * CREDITS: This code was written with the help of                  *
 * ChatGPT and Shawn Nelson						         			*
 *******************************************************************/
public class Bishop extends Piece
{
    public Bishop(boolean black) {
        super(black);
    }

    /***********************************************************
     * METHOD: validMove (Bishop)  				       		   *
     * DESCRIPTION:Makes sure the bishop moves are allowed	   *
     * PARAMETERS: ChessBoard board, Spot start, Spot end  	   *
     * RETURN VALUE: Boolean    		 		       		   *
     **********************************************************/
    @Override
    public boolean validMove(ChessBoard board, Spot start, Spot end) {
        int x = Math.abs(end.getX() - start.getX());
        int y = Math.abs(end.getY() - start.getY());
        int xIncrement = (end.getX() > start.getX()) ? 1: -1;
        int yIncrement = (end.getY() > start.getY()) ? 1 : -1;
        if (x == y) {
            int currentX = start.getX() + xIncrement;
            int currentY = start.getY() + yIncrement;
            while (currentX != end.getX() || currentY != end.getY()) {
                Spot currentSpot = board.getSpot(currentX, currentY);
                Piece currentPiece = currentSpot.getPiece();
                if (currentPiece != null) {
                    if(currentPiece.isBlack() == this.isBlack())
                        return false;
                    else if(!currentSpot.equals(end)) {
                        return false;
                    }
                }
                currentX += xIncrement;
                currentY += yIncrement;
            }
            return true;
        }
        return false;
    }

    /***********************************************************
     * METHOD: draw()           				       		   *
     * DESCRIPTION: Draws the bishop which is represented as b *
     * PARAMETERS: Double centerX, double centerY       	   *
     * RETURN VALUE: Void       		 		       		   *
     **********************************************************/
    @Override
    public JLabel draw(boolean black, int size) {
        JLabel label = new JLabel();
        if(black) {
            ImageIcon pieceIcon = new ImageIcon(getClass().getResource("/BlackBishop.png"));
            Image img = pieceIcon.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH);
            label.setIcon(new ImageIcon(img));
        }
        else{
            ImageIcon pieceIcon = new ImageIcon(getClass().getResource("/WhiteBishop.png"));
            Image img = pieceIcon.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH);
            label.setIcon(new ImageIcon(img));
        }
        return label;
    }
}
