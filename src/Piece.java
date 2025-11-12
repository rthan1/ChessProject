import javax.swing.*;
import java.util.ArrayList;

/********************************************************************
 * Piece.java   	    				                 	        *
 * 				                  		    		                *
 * PROGRAMMER: 	Ethan Nelson							            *
 * COURSE: 		CS201				         				        *
 * DATE: 		5/6/2024 		                  		            *
 * REQUIREMENT: Final Project   			      		            *
 * 							         				                *
 * DESCRIPTION:					         			                *
 * Program is used as a super class to all of the other pieces      *
 * * COPYRIGHT: This code is copyright (C) 2023 Ethan      	        *
 * Nelson and Dean Zeller.          			                    *
 * CREDITS: This code was written with the help of                  *
 * ChatGPT and Shawn Nelson						         			*
 *******************************************************************/
public abstract class Piece {
    private boolean captured = false;
    private boolean black = false;
    private boolean hasMoved = false;
    public Piece(boolean black) {
        this.black = black;
    }
    public boolean isBlack() {
        return this.black;
    }
    public void setBlack(boolean black) {
        this.black = black;
    }
    public void setHasMoved(boolean moved) {
        this.hasMoved = moved;
    }
    public boolean getHasMoveD() {
        return this.hasMoved;
    }
    public boolean isCaptured() {
        return this.captured;
    }
    public void setCaptured(boolean captured) {
        this.captured = captured;
    }

    public abstract boolean validMove(ChessBoard board, Spot start, Spot end);
    public abstract JLabel draw(boolean black, int size);

    public ArrayList<Spot> validMoves(ChessBoard board, Spot start) {
        ArrayList<Spot> spots = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(j != start.getY() || i != start.getX()) {
                    if (validMove(board, start, board.getSpot(i, j))) {
                        spots.add(board.getSpot(i, j));
                        if(board.getSpot(i,j).getPiece() != null && board.getSpot(i,j).getPiece().isBlack() == start.getPiece().isBlack()) {
                            spots.removeLast();
                        }
                    }
                }
            }
        }
        return spots;
    }

}
