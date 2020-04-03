import java.util.List;

public class Board {
    List<List<Integer>> board;

    public Board(List<List<Integer>> grid) {
        this.board = grid;
    }
    public Board(Board board) {
        this(board.getBoard());
    }

    public Board() {
    }

    public List<List<Integer>> getBoard() {
        return board;
    }

    public void setBoard(List<List<Integer>> board) {
        this.board = board;
    }

    public void showGrid() {
        for (int i = 0; i < board.size(); i++) {
            for(int j = 0; j < board.get(i).size(); j++) {
                System.out.print(board.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }
    public boolean isCellTaken(Coordinates coordinates) {
        return board.get(coordinates.getPosX()).get(coordinates.getPosY()) != 0;
    }
    // posX, posY -> position in a row/column from 0 to 8.
    public boolean setCellValue(Coordinates coordinates, int value) {
        if(value > 9 || value < 0) return false;
        board.get(coordinates.getPosX()).set(coordinates.getPosY(), value);
        return true;
    }

    // posX, posY -> position in a row/column from 0 to 8.
    public boolean checkIfNoConflict(Coordinates coordinates, int value) {
        int posX = coordinates.getPosX(), posY = coordinates.getPosY();
        for (int i = 0; i < 9; i++) {
            if(board.get(i).get(posY) == value) return false; // check column
            if(board.get(posX).get(i) == value) return false; // check row

        }

        int square_X = posX / 3; int square_Y = posY / 3;
        square_X*=3; square_Y*=3; // that way the indexes now aim for the first cell in the right square
        for (int i = 0; i < 3; i++) { // check the square
            for (int j = 0; j < 3; j++) {
                if( board.get(square_X + i).get(square_Y + j) == value ) return false;
            }
        }

        return true;
    }

    public Coordinates getNextFreeCell() {
        for(int i = 0; i < board.size(); i++) {
            for (int j = 0; j < board.get(i).size(); j++) {
                if(board.get(i).get(j) == 0)
                    return new Coordinates(i, j);
            }
        }
        return null; // no free cells left
    }
}
