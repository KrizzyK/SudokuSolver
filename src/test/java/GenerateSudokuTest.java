import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenerateSudokuTest {
    static GenerateSudoku sudokoRequest;

    @BeforeClass
    public static void initialize() {
        sudokoRequest  = new GenerateSudoku();
    }
    @Test
    public final void correctDifficultyTest() throws Exception {
        Board board = sudokoRequest.getSudoku("easy");
        Assert.assertNotNull(board);
        board = sudokoRequest.getSudoku("medium");
        Assert.assertNotNull(board);
        board = sudokoRequest.getSudoku("hard");
        Assert.assertNotNull(board);
    }
    @Test
    public final void badDifficultyTest() throws Exception{

        Board board = sudokoRequest.getSudoku("randomtext");
        testIfEmptyArray(board);

        Board board2 = sudokoRequest.getSudoku("Hard");
        testIfEmptyArray(board);
    }

    public final void testIfEmptyArray(Board board) { // the whole array should be full of "0". (because of implementation of the generator
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                Assert.assertEquals(0, board.getBoard().get(i).get(j).intValue());
    }

}
