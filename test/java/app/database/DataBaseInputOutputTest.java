package app.database;

import org.junit.jupiter.api.Test;

import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataBaseInputOutputTest {


    private List<List<Integer>> getTable(boolean flag) {

        List<List<Integer>> expected = new ArrayList<>();
        if (flag) {
            for (int i = 0; i < 10; i++) {
                expected.add(new ArrayList<>());
                for (int j = 0; j < 10; j++) {
                    expected.get(i).add(0);
                }
            }
        }
        else {
            List<Integer> tmp0 = new ArrayList<>();
            tmp0.add(0);
            tmp0.add(0);
            tmp0.add(0);
            tmp0.add(0);
            tmp0.add(0);
            tmp0.add(0);
            tmp0.add(0);
            tmp0.add(0);
            tmp0.add(0);
            tmp0.add(0);
            List<Integer> tmp1 = new ArrayList<>();
            tmp1.add(0);
            tmp1.add(1);
            tmp1.add(1);
            tmp1.add(1);
            tmp1.add(1);
            tmp1.add(0);
            tmp1.add(0);
            tmp1.add(1);
            tmp1.add(0);
            tmp1.add(0);
            List<Integer> tmp2 = new ArrayList<>();
            tmp2.add(0);
            tmp2.add(0);
            tmp2.add(0);
            tmp2.add(0);
            tmp2.add(0);
            tmp2.add(0);
            tmp2.add(0);
            tmp2.add(0);
            tmp2.add(0);
            tmp2.add(0);
            List<Integer> tmp3 = new ArrayList<>();
            tmp3.add(0);
            tmp3.add(0);
            tmp3.add(0);
            tmp3.add(0);
            tmp3.add(1);
            tmp3.add(1);
            tmp3.add(0);
            tmp3.add(0);
            tmp3.add(1);
            tmp3.add(0);
            List<Integer> tmp4 = new ArrayList<>();
            tmp4.add(0);
            tmp4.add(0);
            tmp4.add(1);
            tmp4.add(0);
            tmp4.add(0);
            tmp4.add(0);
            tmp4.add(0);
            tmp4.add(0);
            tmp4.add(0);
            tmp4.add(0);
            List<Integer> tmp5 = new ArrayList<>();
            tmp5.add(0);
            tmp5.add(0);
            tmp5.add(0);
            tmp5.add(0);
            tmp5.add(1);
            tmp5.add(0);
            tmp5.add(0);
            tmp5.add(1);
            tmp5.add(0);
            tmp5.add(0);
            List<Integer> tmp6 = new ArrayList<>();
            tmp6.add(0);
            tmp6.add(1);
            tmp6.add(1);
            tmp6.add(0);
            tmp6.add(0);
            tmp6.add(0);
            tmp6.add(0);
            tmp6.add(1);
            tmp6.add(0);
            tmp6.add(0);
            List<Integer> tmp7 = new ArrayList<>();
            tmp7.add(0);
            tmp7.add(0);
            tmp7.add(0);
            tmp7.add(0);
            tmp7.add(0);
            tmp7.add(0);
            tmp7.add(0);
            tmp7.add(1);
            tmp7.add(0);
            tmp7.add(0);
            List<Integer> tmp8 = new ArrayList<>();
            tmp8.add(0);
            tmp8.add(0);
            tmp8.add(0);
            tmp8.add(0);
            tmp8.add(0);
            tmp8.add(0);
            tmp8.add(0);
            tmp8.add(0);
            tmp8.add(0);
            tmp8.add(0);
            List<Integer> tmp9 = new ArrayList<>();
            tmp9.add(0);
            tmp9.add(1);
            tmp9.add(1);
            tmp9.add(1);
            tmp9.add(0);
            tmp9.add(0);
            tmp9.add(1);
            tmp9.add(1);
            tmp9.add(0);
            tmp9.add(0);
            expected.add(tmp0);
            expected.add(tmp1);
            expected.add(tmp2);
            expected.add(tmp3);
            expected.add(tmp4);
            expected.add(tmp5);
            expected.add(tmp6);
            expected.add(tmp7);
            expected.add(tmp8);
            expected.add(tmp9);
        }
        return expected;

    }

    @Test
    void logIn() {

        DataBaseInputOutput dataBase = new DataBaseInputOutput();
        boolean flag = dataBase.logIn("NotKodan", "54321");
        assertEquals(true, flag);

    }

    @Test
    void sizeOfUsersTables() {

        DataBaseInputOutput dataBase = new DataBaseInputOutput();
        int value = dataBase.sizeOfUsersTables("Kodan", "NotKodan");
        assertEquals(41, value);

    }

    @Test
    void findUsersBattle() {

        DataBaseInputOutput dataBase = new DataBaseInputOutput();
        String value = dataBase.findUsersBattle("NotKodan");
        assertEquals("kodan_notkodan", value);

    }

    @Test
    void getCallingEnemy() {

        DataBaseInputOutput dataBase = new DataBaseInputOutput();
        List<List<Integer>> value = dataBase.getCallingEnemy("Kodan", "NotKodan");
        assertEquals(this.getTable(true), value);

    }

    @Test
    void getCallingSelf() {

        DataBaseInputOutput dataBase = new DataBaseInputOutput();
        List<List<Integer>> value = dataBase.getCallingSelf("Kodan", "NotKodan");
        assertEquals(this.getTable(false), value);

    }

    @Test
    void getReplyingEnemy() {

        DataBaseInputOutput dataBase = new DataBaseInputOutput();
        List<List<Integer>> value = dataBase.getReplyingEnemy("Kodan", "NotKodan");
        assertEquals(this.getTable(true), value);

    }

    @Test
    void getReplyingSelf() {

        DataBaseInputOutput dataBase = new DataBaseInputOutput();
        List<List<Integer>> value = dataBase.getReplyingSelf("Kodan", "NotKodan");
        assertEquals(this.getTable(false), value);

    }

    @Test
    void getUserBot() {

        DataBaseInputOutput dataBase = new DataBaseInputOutput();
        List<List<Integer>> value = dataBase.getUserBot("Kodan");
        assertEquals(this.getTable(true), value);

    }

    @Test
    void getUserUser() {

        DataBaseInputOutput dataBase = new DataBaseInputOutput();
        List<List<Integer>> value = dataBase.getUserUser("Kodan");
        assertEquals(this.getTable(false), value);

    }

    @Test
    void checkFlag() {

        DataBaseInputOutput dataBase = new DataBaseInputOutput();
        int value = dataBase.checkFlag("Kodan", "NotKodan", "calling");
        assertEquals(5, value);

    }
}