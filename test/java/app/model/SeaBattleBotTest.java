package app.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SeaBattleBotTest {

    @Test
    void isWin() {

        SeaBattleBot bot = new SeaBattleBot(true);
        int value = bot.isWin();
        int expected = 5;
        assertEquals(expected, value);

    }

    @Test
    void finishedShips() {

        SeaBattleBot bot = new SeaBattleBot(true);
        String value = bot.finishedShips();
        String expected = "0 0";
        assertEquals(expected, value);

    }
}