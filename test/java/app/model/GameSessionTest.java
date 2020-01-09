package app.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameSessionTest {

    @Test
    void isWin() {

        GameSession gameSession = new GameSession(true);
        int value = gameSession.isWin();
        int expected = 5;
        assertEquals(expected, value);

    }

    @Test
    void finishedShips() {

        GameSession gameSession = new GameSession(true);
        String value = gameSession.finishedShips();
        String expected = "0 0";
        assertEquals(expected, value);

    }
}