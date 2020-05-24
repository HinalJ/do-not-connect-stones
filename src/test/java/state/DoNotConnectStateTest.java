package state;

import donotconnect.state.DoNotConnectState;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoNotConnectStateTest {

    @Test
    void testIsFinished() {
        assertAll(
            () -> assertEquals(false, new DoNotConnectState(new char[][]{
                {'x',' ', ' ', ' ', ' '},
                {'o',' ', ' ', ' ', ' '},
                {'x',' ', ' ', ' ', ' '},
                {'o',' ', ' ', ' ', ' '},
                {'x',' ', ' ', ' ', ' '}}).isFinished(4,0)),

            () ->assertEquals(true, new DoNotConnectState(new char[][]{
                {'x','o', 'o', ' ', ' '},
                {'o','x', ' ', ' ', ' '},
                {'x',' ', 'x', ' ', ' '},
                {'o',' ', ' ', ' ', ' '},
                {'x',' ', ' ', ' ', ' '}}).isFinished(0,0)),

            () ->assertEquals(true,new DoNotConnectState(new char[][]{
                {'x','o', 'o', ' ', ' '},
                {'o','x', ' ', ' ', ' '},
                {'x',' ', 'x', ' ', ' '},
                {'o',' ', ' ', ' ', ' '},
                {'x',' ', ' ', ' ', ' '}}).isFinished(1,1)),

            () ->assertEquals(true,new DoNotConnectState(new char[][]{
                {'x','o', 'o', ' ', ' '},
                {'o','x', ' ', ' ', ' '},
                {'x',' ', 'x', ' ', ' '},
                {'o',' ', ' ', ' ', ' '},
                {'x',' ', ' ', ' ', ' '}}).isFinished(2,2)),

            () ->assertEquals(true,new DoNotConnectState(new char[][]{
                {'x','o', 'o', 'o', ' '},
                {'o','x', ' ', ' ', ' '},
                {'x',' ', ' ', ' ', ' '},
                {'o',' ', ' ', ' ', ' '},
                {'x',' ', 'x', ' ', ' '}}).isFinished(0,1)),

            () ->assertEquals(true,new DoNotConnectState(new char[][]{
                {'x','o', 'o', 'o', ' '},
                {'o','x', ' ', ' ', ' '},
                {'x',' ', ' ', ' ', ' '},
                {'o',' ', ' ', ' ', ' '},
                {'x',' ', 'x', ' ', ' '}}).isFinished(0,2)),

            () ->assertEquals(true,new DoNotConnectState(new char[][]{
                {'x','o', 'o', 'o', ' '},
                {'o','x', ' ', ' ', ' '},
                {'x',' ', ' ', ' ', ' '},
                {'o',' ', ' ', ' ', ' '},
                {'x',' ', 'x', ' ', ' '}}).isFinished(0,3)),

            () ->assertEquals(true,new DoNotConnectState(new char[][]{
                {'x','o', ' ', ' ', ' '},
                {'o','o', ' ', ' ', ' '},
                {'x','o', ' ', ' ', ' '},
                {'o',' ', ' ', 'x', ' '},
                {'x',' ', ' ', 'x', ' '}}).isFinished(0,1)),

            () ->assertEquals(true,new DoNotConnectState(new char[][]{
                {'x','o', ' ', ' ', ' '},
                {'o','o', ' ', ' ', ' '},
                {'x','o', ' ', ' ', ' '},
                {'o',' ', ' ', 'x', ' '},
                {'x',' ', ' ', 'x', ' '}}).isFinished(1,1)),

            () ->assertEquals(true,new DoNotConnectState(new char[][]{
                {'x','o', ' ', ' ', ' '},
                {'o','o', ' ', ' ', ' '},
                {'x','o', ' ', ' ', ' '},
                {'o',' ', ' ', 'x', ' '},
                {'x',' ', ' ', 'x', ' '}}).isFinished(2,1)),

            () ->assertEquals(true, new DoNotConnectState(new char[][]{
                {'x','o', ' ', ' ', ' '},
                {'o','x', 'o', ' ', ' '},
                {'x','o', ' ', 'x', ' '},
                {'o',' ', ' ', ' ', ' '},
                {'x',' ', ' ', ' ', ' '}}).isFinished(3,0)),

            () ->assertEquals(true,new DoNotConnectState(new char[][]{
                {'x','o', ' ', ' ', ' '},
                {'o','x', 'o', ' ', ' '},
                {'x','o', ' ', 'x', ' '},
                {'o',' ', ' ', ' ', ' '},
                {'x',' ', ' ', ' ', ' '}}).isFinished(2,1)),

            () ->assertEquals(true,new DoNotConnectState(new char[][]{
                {'x','o', ' ', ' ', ' '},
                {'o','x', 'o', ' ', ' '},
                {'x','o', ' ', 'x', ' '},
                {'o',' ', ' ', ' ', ' '},
                {'x',' ', ' ', ' ', ' '}}).isFinished(1,2)));
    }

    @Test
    void testIsValidMove() {
        assertTrue(new DoNotConnectState(new char[][]{
                {'x',' ', ' ', ' ', ' '},
                {'o',' ', ' ', ' ', ' '},
                {'x',' ', ' ', ' ', ' '},
                {'o',' ', ' ', ' ', ' '},
                {'x',' ', ' ', ' ', ' '}}).isValidMove(2,3));
        assertFalse(new DoNotConnectState(new char[][]{
                {'x',' ', ' ', ' ', ' '},
                {'o',' ', ' ', ' ', ' '},
                {'x',' ', ' ', ' ', ' '},
                {'o',' ', ' ', ' ', ' '},
                {'x',' ', ' ', ' ', ' '}}).isValidMove(0,0));
    }

    @Test
    void testDraw() {
        DoNotConnectState state = new DoNotConnectState();
        assertEquals('o', state.draw(2,3,'o'));
        assertEquals('x', state.draw(3,4,'x'));
    }

    @Test
    void testIsAllFilled() {
        assertFalse(new DoNotConnectState().isAllFilled());
        assertTrue(new DoNotConnectState(new char[][] {
                {'x','o','x','o','x'},
                {'x','o','x','o','x'},
                {'o','x','o','x','o'},
                {'o','x','o','x','o'},
                {'x','o','x','o','x'}}).isAllFilled());
    }

    @Test
    void testGetBoard() {
        DoNotConnectState state = new DoNotConnectState();
        char[][] board = new char[][]{
                {' ',' ', ' ', ' ', ' '},
                {' ',' ', ' ', ' ', ' '},
                {' ',' ', ' ', ' ', ' '},
                {' ',' ', ' ', ' ', ' '},
                {' ',' ', ' ', ' ', ' '}};
        state.setBoard(board);
        assertEquals(board, state.getBoard());
    }
}