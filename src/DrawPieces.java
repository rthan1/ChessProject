/********************************************************************
 * DrawPieces.java   	    				               	        *
 * 				                  		    		                *
 * PROGRAMMER: 	Ethan Nelson							            *
 * COURSE: 		CS201				         				        *
 * DATE: 		5/6/2024 		                  		            *
 * REQUIREMENT: Final Project   			      		            *
 * 							         				                *
 * DESCRIPTION:					         			                *
 * Program is used to draw the chess board in the chess game        *
 * * COPYRIGHT: This code is copyright (C) 2023 Ethan      	        *
 * Nelson and Dean Zeller.          			                    *
 * CREDITS: This code was written with the help of                  *
 * ChatGPT and Shawn Nelson						         			*
 *******************************************************************/
//public class DrawPieces {

    /***********************************************************
     * METHOD: drawBoard         				       		   *
     * DESCRIPTION:draws the chess board with proper colors	   *
     * PARAMETERS: ChessBoard board                     	   *
     * RETURN VALUE: void       		 		       		   *
     **********************************************************/
/*    public static void drawBoard(ChessBoard board) {
        StdDraw.line(0, 0, 8, 0);
        StdDraw.line(0, 0, 0, 8);
        StdDraw.line(0, 8, 8, 8);
        StdDraw.line(8, 8, 8, 0);
        for (double j = 1.5; j < 8; j = j + 2) {
            StdDraw.setPenColor(210, 180, 140);
            for (double i = 0.5; i < 8; i = i + 2) {
                StdDraw.filledSquare(j, i, 0.5);
            }
            StdDraw.setPenColor(150, 75, 10);
            for (double i = 1.5; i < 8; i = i + 2) {
                StdDraw.filledSquare(j, i, 0.5);
            }
        }
        for (double j = 0.5; j < 8; j = j + 2) {
            StdDraw.setPenColor(150, 75, 10);
            for (double i = 0.5; i < 8; i = i + 2) {
                StdDraw.filledSquare(j, i, 0.5);
            }
            StdDraw.setPenColor(210, 180, 140);
            for (double i = 1.5; i < 8; i = i + 2) {
                StdDraw.filledSquare(j, i, 0.5);
            }
        }
        StdDraw.setPenRadius(0.01);
        for(int x = 0; x<=7; x++) {
            for(int y = 0; y<=7; y++) {
                Piece p = board.getSpot(x,y).getPiece();
                if(p != null ) {
                    if(p.isBlack())
                        p.draw(true, );
                    else
                        p.draw(false);
                }
            }
        }
    }
} */