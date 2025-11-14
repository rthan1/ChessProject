//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
/********************************************************************
 * Game.java   	    				         	         	        *
 * 				                  		    		                *
 * PROGRAMMER: 	Ethan Nelson							            *
 * COURSE: 		CS201				         				        *
 * DATE: 		5/6/2024 		                  		            *
 * REQUIREMENT: Final Project   			      		            *
 * 							         				                *
 * DESCRIPTION:					         			                *
 * Program is used to hold game loop and run it                     *
 * * COPYRIGHT: This code is copyright (C) 2023 Ethan      	        *
 * Nelson and Dean Zeller.          			                    *
 * CREDITS: This code was written with the help of                  *
 * ChatGPT and Shawn Nelson						         			*
 *******************************************************************/

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.EventListener;

public class Game {
    ChessBoard board;
    private Display display;
    private boolean gameOver;
    int turn;
    ArrayList<Spot> spots = new ArrayList<Spot>();
    Player p1;
    Player p2;
    Player currentPlayer;
    private Spot startSpot = null;
    private Spot endSpot = null;

    public Game() {
        board = new ChessBoard();
        display = new Display(this);
        display.drawBoard(board);
        gameOver = false;
        turn = 0;
        p1 = new Player(false, "White");
        p2 = new Player(true, "Black");
        currentPlayer = p1;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Game());
    }

    public ArrayList<Spot> HandlePress(int row, int col) {
        startSpot = board.getSpot(row, col);
        if(startSpot.getPiece() != null && startSpot.getPiece().isBlack() == currentPlayer.isBlackSide()) {
            return getSpots();
        }
        return null;
    }

    public void HandleRelease(int row, int col) {
        endSpot = board.getSpot(row, col);
        spots.clear();
        ValidMove();
        inCheck();
    }

    public void castleKingSide() {
        if(currentPlayer.isBlackSide()) {
            Piece blackKing = board.pieceListBlack.get(board.getKing(board.pieceListBlack));
            Piece castlingBlackRook = board.getSpot(7,7).getPiece();
            if(!blackKing.getHasMoveD() && !castlingBlackRook.getHasMoveD()) {
                Spot kingSpot = board.findSpotOnBoard(blackKing);
                Spot rookSpot = board.findSpotOnBoard(castlingBlackRook);
                if(board.emptyInBetweenKingAndRook(kingSpot,rookSpot)) {
                    board.getSpot(6,7).setPiece(blackKing);
                    board.getSpot(5,7).setPiece(castlingBlackRook);
                    kingSpot.setPiece(null);
                    rookSpot.setPiece(null);
                    blackKing.setHasMoved(true);
                }
            }
        }
        else {
            Piece whiteKing = board.pieceListWhite.get(board.getKing(board.pieceListWhite));
            Piece castlingWhiteRook = board.getSpot(7,0).getPiece();
            if(!whiteKing.getHasMoveD() && !castlingWhiteRook.getHasMoveD()) {
                Spot kingSpot = board.findSpotOnBoard(whiteKing);
                Spot rookSpot = board.findSpotOnBoard(castlingWhiteRook);
                if(board.emptyInBetweenKingAndRook(kingSpot,rookSpot)) {
                    board.getSpot(6,0).setPiece(whiteKing);
                    board.getSpot(5,0).setPiece(castlingWhiteRook);
                    kingSpot.setPiece(null);
                    rookSpot.setPiece(null);
                    whiteKing.setHasMoved(true);
                }
            }
        }
    }

    public void castleQueenSide() {
        if(currentPlayer.isBlackSide()) {
            Piece blackKing = board.pieceListBlack.get(board.getKing(board.pieceListBlack));
            Piece castlingBlackRook = board.getSpot(0,7).getPiece();
            if(!blackKing.getHasMoveD() && !castlingBlackRook.getHasMoveD()) {
                Spot kingSpot = board.findSpotOnBoard(blackKing);
                Spot rookSpot = board.findSpotOnBoard(castlingBlackRook);
                if(board.emptyInBetweenKingAndRook(kingSpot,rookSpot)) {
                    board.getSpot(2,7).setPiece(blackKing);
                    board.getSpot(3,7).setPiece(castlingBlackRook);
                    kingSpot.setPiece(null);
                    rookSpot.setPiece(null);
                    blackKing.setHasMoved(true);
                }
            }
        }
        else {
            Piece whiteKing = board.pieceListWhite.get(board.getKing(board.pieceListWhite));
            Piece castlingWhiteRook = board.getSpot(0,0).getPiece();
            if(!whiteKing.getHasMoveD() && !castlingWhiteRook.getHasMoveD()) {
                Spot kingSpot = board.findSpotOnBoard(whiteKing);
                Spot rookSpot = board.findSpotOnBoard(castlingWhiteRook);
                if(board.emptyInBetweenKingAndRook(kingSpot,rookSpot)) {
                    board.getSpot(2,0).setPiece(whiteKing);
                    board.getSpot(3,0).setPiece(castlingWhiteRook);
                    kingSpot.setPiece(null);
                    rookSpot.setPiece(null);
                    whiteKing.setHasMoved(true);
                }
            }
        }
    }

    public boolean castling() {
        return (startSpot.getPiece().getClass() == King.class && Math.abs(startSpot.getX() - endSpot.getX()) == 2 && Math.abs(startSpot.getY() - endSpot.getY()) == 0 && !startSpot.getPiece().getHasMoveD());
    }

    public boolean allowedMove() {
        int pieceCaptured = 0;
        Piece movingPiece = startSpot.getPiece();
        Piece capturedPiece = endSpot.getPiece();
        endSpot.setPiece(movingPiece);
        startSpot.setPiece(null);
        if(currentPlayer == p1 && capturedPiece != null) {
            board.pieceListBlack.remove(capturedPiece);
            pieceCaptured++;
        }
        else if(capturedPiece!= null) {
            board.pieceListWhite.remove(capturedPiece);
            pieceCaptured++;
        }

        boolean moveAllowed = !inCheck();

        startSpot.setPiece(movingPiece);
        endSpot.setPiece(capturedPiece);

        if(currentPlayer == p1 && pieceCaptured == 1) {
            board.pieceListBlack.add(capturedPiece);
        }
        else if(pieceCaptured == 1) {
            board.pieceListWhite.add(capturedPiece);
        }

        return moveAllowed;
    }

    private boolean inCheck() {
        if (currentPlayer.isBlackSide()) {
            int blackKing = board.getKing(board.pieceListBlack);
            Spot spot = board.findSpotOnBoard(board.pieceListBlack.get(blackKing));
            for (Piece piece : board.pieceListWhite) {
                if (piece.validMove(board, board.findSpotOnBoard(piece), spot)) {
                    currentPlayer.setInCheck(true);
                    display.drawBoard(board);
                    return true;
                }
            }
        }
        else {
            int whiteKing = board.getKing(board.pieceListWhite);
            Spot spot = board.findSpotOnBoard(board.pieceListWhite.get(whiteKing));
            for (Piece piece : board.pieceListBlack) {
                if (piece.validMove(board, board.findSpotOnBoard(piece), spot)) {
                    currentPlayer.setInCheck(true);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean ValidMove() {
        if (startSpot.getPiece() == null || startSpot.getPiece().isBlack() != this.currentPlayer.isBlackSide()) {
            return false;
        }
        if (endSpot.getPiece() != null && endSpot.getPiece().isBlack() == this.currentPlayer.isBlackSide()) {
            return false;
        }
        if (!startSpot.getPiece().validMove(board, startSpot, endSpot)) {
            return false;
        }

        if (inCheck()) {
            if(allowedMove())
                currentPlayer.setInCheck(false);
        }

        if(!allowedMove()) {
            return false;
        }

        if(castling()) {

            if(startSpot.getPiece().getHasMoveD()) {
                return false;
            }

            else if (startSpot.getX() - endSpot.getX() < 0) {
                castleKingSide();
                turn++;
                if (turn % 2 == 1) {
                    currentPlayer = p2;
                } else {
                    currentPlayer = p1;
                }
                display.clear();
                display.drawBoard(board);
                return true;
            }

            if (startSpot.getX() - endSpot.getX() > 0) {
                castleQueenSide();
                turn++;
                if (turn % 2 == 1) {
                    currentPlayer = p2;
                } else {
                    currentPlayer = p1;
                }
                display.clear();
                display.drawBoard(board);
                return true;
            }

        }


        if (endSpot.getPiece() != null) {
            if (endSpot.getPiece().isBlack() && !currentPlayer.isBlackSide())
                board.pieceListBlack.remove(endSpot.getPiece());
            else if (!endSpot.getPiece().isBlack() && currentPlayer.isBlackSide())
                board.pieceListWhite.remove(endSpot.getPiece());


            if (endSpot.getPiece().getClass() == King.class) {
                gameOver = true;
                System.out.printf("Winner is: %s", currentPlayer.isBlackSide() ? "Black" : "White");
            }
        }

        startSpot.getPiece().setHasMoved(true);
        endSpot.setPiece(startSpot.getPiece());
        startSpot.setPiece(null);

        if (gameOver) {
            System.exit(0);
        }

        turn++;
        if (turn % 2 == 1) {
            currentPlayer = p2;
        } else {
            currentPlayer = p1;
        }
        display.clear();
        display.drawBoard(board);
        return true;
    }

    public ArrayList<Spot> getSpots() {
        spots = startSpot.getPiece().validMoves(board, startSpot);
        return spots;
    }
}