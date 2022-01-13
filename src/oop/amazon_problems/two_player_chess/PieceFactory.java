package oop.amazon_problems.two_player_chess;

public class PieceFactory {
    public static Piece getPiece(String type, Color color, Position position){
        switch (type){
            case "pawn" : return new Pawn(color, position);
            default: return null;
        }
    }
}
