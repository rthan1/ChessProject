import javax.swing.*;
import java.awt.*;

/********************************************************************
 * Pawn.java   	    				                 	            *
 * 				                  		    		                *
 * PROGRAMMER: 	Ethan Nelson							            *
 * COURSE: 		CS201				         				        *
 * DATE: 		5/6/2024 		                  		            *
 * REQUIREMENT: Final Project   			      		            *
 * 							         				                *
 * DESCRIPTION:					         			                *
 * Program is used to represent a Pawn in the chess game            *
 * * COPYRIGHT: This code is copyright (C) 2023 Ethan      	        *
 * Nelson and Dean Zeller.          			                    *
 * CREDITS: This code was written with the help of                  *
 * ChatGPT and Shawn Nelson						         			*
 *******************************************************************/
public class Pawn extends Piece
{
    private boolean hasMoved = false;
    public Pawn (boolean black) {
        super(black);
    }

    /***********************************************************
     * METHOD: validMove (Pawn)  				       		   *
     * DESCRIPTION:Makes sure the Pawns moves are allowed	   *
     * PARAMETERS: ChessBoard board, Spot start, Spot end  	   *
     * RETURN VALUE: Boolean    		 		       		   *
     **********************************************************/
    @Override
    public boolean validMove(ChessBoard board, Spot start, Spot end) {
        int y = end.getY() - start.getY();
        int x = end.getX() - start.getX();

        if (start.getPiece().isBlack()) {
            if (!getHasMoveD()) {
                if (y == -2 && x == 0) {
                    return true;
                }
            }
            if ((x == 1 || x == -1) && end.getPiece() != null && y == -1 && end.getPiece().isBlack() != this.isBlack()) {
                return true;
            }
            if (end.getPiece() != null) {
                return false;
            }


            if (y == -1 && x == 0) {
                return true;
            } else
                return false;
        }


        else {
            if (!getHasMoveD()) {
                if (y == 2 && x == 0) {
                    return true;
                }
            }
            if ((x == 1 || x == -1) && end.getPiece() != null && y == 1 && end.getPiece().isBlack() != this.isBlack()) {
                return true;
            }
            if (end.getPiece() != null) {
                return false;
            }


            if (y == 1 && x == 0) {
                return true;
            } else
                return false;
        }
    }

    /***********************************************************
     * METHOD: draw()           				       		   *
     * DESCRIPTION: Draws the Pawn which is represented as P   *
     * PARAMETERS: Double centerX, double centerY       	   *
     * RETURN VALUE: Void       		 		       		   *
     **********************************************************/
    @Override
    public JLabel draw(boolean black, int size) {
        JLabel label = new JLabel();
        if(black) {
            ImageIcon pieceIcon = new ImageIcon(getClass().getResource("/BlackPawn.png"));
            Image img = pieceIcon.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH);
            label.setIcon(new ImageIcon(img));
        }
        else{
            ImageIcon pieceIcon = new ImageIcon(getClass().getResource("/WhitePawn.png"));
            Image img = pieceIcon.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH);
            label.setIcon(new ImageIcon(img));
        }
        return label;
    }
}
