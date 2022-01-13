package oop.amazon_problems.two_player_chess;


import java.util.*;

public class Player {
    String name;
    Color color;
    List<Piece> pawns = new ArrayList<>();
    List<Piece> bishops = new ArrayList<>();
    List<Piece> knights = new ArrayList<>();
    List<Piece> rooks = new ArrayList<>();
    King king;
    Queen queen;

    public Player(String name, Color color) {
        this.name = name;
        this.color = color;
        populatePieces();
    }

    private void populatePieces() {
        for (int i = 0; i <8 ; i++) {
            pawns.add(PieceFactory.getPiece("pawn", Color.WHITE, new Position(1, 0)));
        }
    }
}
