import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BoardTest {

    static List<List<Integer>> testArray;

    @BeforeClass
    public static void initializeTestArray(){
        testArray = new ArrayList<List<Integer>>(9);
        for (int i = 0; i <9; i++) {
            ArrayList<Integer> row = new ArrayList<>(9);
            for (int j = 0; j < 9; j++) {
                row.add(  ((i+j)%9 ) + 1 ); // 1,2,3,4,5,6,7,8,9 -> 2,3,4,5,6,7,8,9,1 -> ...
            }
            testArray.add(row);
        }
    }
    @Test
    public final void setCellAndIsCellTakenTest() {
        Board board = new Board(testArray);
        board.setCellValue(new Coordinates(0,1), 0);
        Assert.assertFalse( board.isCellTaken(new Coordinates(0,1)) );
        board.setCellValue(new Coordinates(0,1), 9);
        Assert.assertTrue( board.isCellTaken(new Coordinates(0,1)) );

        board.setCellValue(new Coordinates(2,2), 0);
        Assert.assertFalse( board.setCellValue(new Coordinates(2,2),2000) ); // too high value, should not change
        Assert.assertFalse( board.isCellTaken(new Coordinates(2,2)) );

        Assert.assertTrue( board.isCellTaken(new Coordinates(8,8)) );
        Assert.assertTrue( board.isCellTaken(new Coordinates(0,8)) );

    }
    @Test
    public final void chechIfNoConflictTest() {
        Board board = new Board(testArray);
        for (int i = 1; i <= 9; i++)  {
            Assert.assertFalse( board.checkIfNoConflict(new Coordinates(0,0), i) );
            Assert.assertFalse( board.checkIfNoConflict(new Coordinates(2,4), i) );
        }
        board.setCellValue(new Coordinates(0,0), 0);
        Assert.assertTrue(board.checkIfNoConflict(new Coordinates(0,0),1));
    }
    @Test
    public final void nextFreeCellTest(){
        Board board = new Board(testArray);
        Assert.assertNotNull(board.getNextFreeCell());
    }

}
