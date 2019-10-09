package board;

import com.google.common.collect.ImmutableMap;
import pieces.Piece;

import java.util.HashMap;
import java.util.Map;

public abstract class Tile
{

    protected final int tileCoordinate;
    private static final Map<Integer,EmptyTile> EMPTY_TILES= createAllPossibleEmptyTile();



    private static Map<Integer, EmptyTile> createAllPossibleEmptyTile()
    {
        final Map<Integer, EmptyTile> emptyTileMap= new HashMap<>();

        for (int i=0;i<64;i++)
        {
            emptyTileMap.put(i, new EmptyTile(i));
        }
      return ImmutableMap.copyOf(emptyTileMap);
    }

    public static Tile createTile(final int tileCoordinate, final Piece piece)
    {
        return piece != null ? new OccupiedTile(tileCoordinate,piece): EMPTY_TILES.get(tileCoordinate);
    }



    private Tile(int tileCoordinate)
    {
        this.tileCoordinate=tileCoordinate;
    }

    public abstract boolean isTileOccupied();

    public abstract Piece getPiece();




    //SubClass that represents the tile is occupied or Empty

    public static final class EmptyTile extends Tile
    {
        EmptyTile(final int coordinate)
        {
            super(coordinate);
        }

        @Override
        public boolean isTileOccupied()
        {
            return false;
        }
        @Override
        public Piece getPiece()
        {
            return null;
        }
    }





    public static final class OccupiedTile extends Tile
    {
        private final Piece pieceOnTile;

        OccupiedTile(int tileCoordinate, Piece pieceOnTile)
        {
            super(tileCoordinate);

            this.pieceOnTile=pieceOnTile;
        }

        @Override
        public boolean isTileOccupied()
        {
            return true;
        }

        @Override
        public Piece getPiece()
        {
            return this.pieceOnTile;
        }
    }

}
