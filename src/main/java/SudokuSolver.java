public class SudokuSolver {

    public static void main(String[] args) {
        GenerateSudoku generator = new GenerateSudoku();
        try {
            Board board = generator.getSudoku("hard"); // easy/medium/hard

            board.showGrid();
            solve(board);
            System.out.println();
            board.showGrid();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean solve(Board board) {

        Coordinates nextCell = board.getNextFreeCell();

        if(nextCell == null) return true; // solved, because there is no free cells left

        for (int nextValue = 1; nextValue <= 9 ; nextValue++) {
            if(board.checkIfNoConflict(nextCell, nextValue)) { //check if the value is possible

                board.setCellValue(nextCell, nextValue); // set the next possible value

                if(solve(board)) return true; // if found the solution through stop condition -> end all recursive calls
                // if recursive calls returned false, algorithm havent found the solution yet

                board.setCellValue(nextCell, 0); // resetting the value, because the algorithm now backtracks to previous cells - this one should be empty again
            }
        }
        // out of the loop means that this cell cant be filled with 1-9 => the previously filled cells are not correct
        // returning to the last called "solve" to increment the number in the last cell
        return false; // no solution to this grid
    }
}
