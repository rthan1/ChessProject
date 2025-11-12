/********************************************************************
 * ChessBoard.java       				                 	        *
 * 				                  		    		                *
 * PROGRAMMER: 	Ethan Nelson							            *
 * COURSE: 		CS201				         				        *
 * DATE: 		5/6/2024 		                  		            *
 * REQUIREMENT: Final Project   			      		            *
 * 							         				                *
 * DESCRIPTION:					         			                *
 * Program is used to represent the whole board in the chess game   *
 * * COPYRIGHT: This code is copyright (C) 2023 Ethan      	        *
 * Nelson and Dean Zeller.          			                    *
 * CREDITS: This code was written with the help of                  *
 * ChatGPT and Shawn Nelson						         			*
 *******************************************************************/
import javax.swing.*;
import java.util.ArrayList;

public class ChessBoard {
    private Spot[][] board;
    public ArrayList<Piece> pieceListBlack;
    public ArrayList<Piece> pieceListWhite;
    public ChessBoard() {
        board = new Spot[8][8];
        pieceListBlack = new ArrayList<>();
        pieceListWhite = new ArrayList<>();
        this.resetBoard();
    }
    public Spot getSpot(int x, int y) throws ArrayIndexOutOfBoundsException {
        if(x<0||x>7||y<0||y>7)
            throw new ArrayIndexOutOfBoundsException("Index out of bounds");
        return this.board[x][y];
    }

    public int getKing(ArrayList<Piece> pieceList) {
        for(int i = 0; i < pieceList.size(); i++) {
            if(pieceList.get(i).getClass() == King.class)
                return i;
        }
        return -1;
    }

    public Spot findSpotOnBoard(Piece p) {
        for(int i = 0; i < 8; i ++) {
            for(int j = 0; j < 8; j++) {
                if(p.equals(board[j][i].getPiece()))
                    return board[j][i];
            }
        }
        return null;
    }

    public boolean emptyInBetweenKingAndRook(Spot kingSpot, Spot rookSpot) {
        int distanceInXDirection = Math.abs(kingSpot.getX() - rookSpot.getX());
        int startingX = kingSpot.getX();
        int xIncrement = (kingSpot.getX() < rookSpot.getX()) ? 1 : -1;
        for(int i = 0; i < distanceInXDirection - 1; i++) {
            startingX += xIncrement;
            if(getSpot(startingX, kingSpot.getY()).getPiece() != null)
                return false;
        }
        return true;
    }



    /***********************************************************
     * METHOD: resetBoard        				       		   *
     * DESCRIPTION:puts the board back into starting position  *
     * PARAMETERS: None                                 	   *
     * RETURN VALUE: Void       		 		       		   *
     **********************************************************/
    public void resetBoard() {
        board[0][0] = new Spot(new Rook(false),0,0);
        board[1][0] = new Spot(new Knight(false),1,0);
        board[2][0] = new Spot(new Bishop(false),2,0);
        board[3][0] = new Spot(new Queen(false),3,0);
        board[4][0] = new Spot(new King(false),4,0);
        board[5][0] = new Spot(new Bishop(false),5,0);
        board[6][0] = new Spot(new Knight(false),6,0);
        board[7][0] = new Spot(new Rook(false),7,0);
        board[0][1] = new Spot(new Pawn(false),0,1);
        board[1][1] = new Spot(new Pawn(false),1,1);
        board[2][1] = new Spot(new Pawn(false),2,1);
        board[3][1] = new Spot(new Pawn(false),3,1);
        board[4][1] = new Spot(new Pawn(false),4,1);
        board[5][1] = new Spot(new Pawn(false),5,1);
        board[6][1] = new Spot(new Pawn(false),6,1);
        board[7][1] = new Spot(new Pawn(false),7,1);
        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < 8; j++) {
                this.pieceListWhite.add(board[j][i].getPiece());
            }
        }

        board[0][7] = new Spot(new Rook(true),0,7);
        board[1][7] = new Spot(new Knight(true),1,7);
        board[2][7] = new Spot(new Bishop(true),2,7);
        board[3][7] = new Spot(new Queen(true),3,7);
        board[4][7] = new Spot(new King(true),4,7);
        board[5][7] = new Spot(new Bishop(true),5,7);
        board[6][7] = new Spot(new Knight(true),6,7);
        board[7][7] = new Spot(new Rook(true),7,7);
        board[0][6] = new Spot(new Pawn(true),0,6);
        board[1][6] = new Spot(new Pawn(true),1,6);
        board[2][6] = new Spot(new Pawn(true),2,6);
        board[3][6] = new Spot(new Pawn(true),3,6);
        board[4][6] = new Spot(new Pawn(true),4,6);
        board[5][6] = new Spot(new Pawn(true),5,6);
        board[6][6] = new Spot(new Pawn(true),6,6);
        board[7][6] = new Spot(new Pawn(true),7,6);

        for(int i = 7; i > 5; i--) {
            for(int j = 0; j < 8; j++) {
                this.pieceListBlack.add(board[j][i].getPiece());
            }
        }

        for(int y = 2; y<6; y++) {
            for(int x = 0; x<8; x++) {
                board[x][y] = new Spot(null,x,y);
            }
        }
    }

}
