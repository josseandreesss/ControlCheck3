package es.us.dp1.chess.tournament.match;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import es.us.dp1.chess.tournament.model.BaseEntity;

import java.lang.Cloneable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ChessBoard extends BaseEntity implements Cloneable {
    boolean creatorTurn;
    LocalDateTime currentTurnStart;
    boolean onCheck;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    List<Piece> pieces;

    
    List<Piece> getCaptures(){return pieces.stream().filter(p -> Boolean.TRUE.equals(p.getCaptured())).toList();}

    public void addPiece(Piece piece) {
        piece.setBoard(this);
        if(!pieces.contains(piece))
            pieces.add(piece);
    }

    public ChessBoard clone() {
        ChessBoard board = new ChessBoard();
        board.setCreatorTurn(this.isCreatorTurn());
        board.setCurrentTurnStart(this.getCurrentTurnStart());
        board.setOnCheck(this.isOnCheck());
        board.pieces = new ArrayList<>(pieces.size());
        for(Piece piece : this.getPieces()) {
            Piece newPiece=piece.clone();
            newPiece.setBoard(board);
            board.pieces.add(newPiece);
        }
        return board;
    }


}
