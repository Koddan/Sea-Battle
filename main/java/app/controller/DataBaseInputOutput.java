package app.controller;

import java.sql.*;
import java.util.*;

public class DataBaseInputOutput {

    Connection connection;
    Statement statement;
    List<List<Integer>> userBot;
    List<List<Integer>> userUser;
    List<List<Integer>> botUser;
    List<List<Integer>> botBot;

    List<List<Integer>> callingEnemy;
    List<List<Integer>> callingSelf;
    List<List<Integer>> replyingEnemy;
    List<List<Integer>> replyingSelf;

    public DataBaseInputOutput() {
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://192.168.56.1:3306/sea_battle", "root", "skintr217");
            this.statement = this.connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        botBot = this.randomize();

    }

    public List<List<Integer>> randomize() {

        List<List<Integer>> map = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            map.add(new ArrayList<>());
            for (int j = 0; j < 10; j++) {
                map.get(i).add(0);
            }
        }

        boolean flag = true;
        int x = 0;
        int y = 0;

        /*while (flag) {
            int direction = (int) (Math.random() * 2);
            if (direction == 0) {
                x = (int) (Math.random() * 9);
                y = (int) (Math.random() * 6);
            } else {
                x = (int) (Math.random() * 6);
                y = (int) (Math.random() * 9);
            }
            if (map.get(x).get(y) == 0) {
                if (((y + 3) <= 9) && ((x + 3) <= 9)) {
                    if (((map.get(x).get(y + 1) == 0) && (map.get(x).get(y + 2) == 0) && (map.get(x).get(y + 3) == 0) && (direction == 0)) || ((map.get(x + 1).get(y) == 0) && (map.get(x + 2).get(y) == 0) && (map.get(x + 3).get(y) == 0) && (direction == 1))) {
                        map.get(x).set(y, 1);
                        if (direction == 0) {
                            map.get(x).set((y + 1), 1);
                            map.get(x).set((y + 2), 1);
                            map.get(x).set((y + 3), 1);
                            if ((x - 1) >= 0) {
                                map.get(x - 1).set(y, 2);
                                if ((y + 1) <= 9)
                                    map.get(x - 1).set((y + 1), 2);
                                if ((y + 2) <= 9)
                                    map.get(x - 1).set((y + 2), 2);
                                if ((y + 3) <= 9)
                                    map.get(x - 1).set((y + 3), 2);
                                if ((y + 4) <= 9)
                                map.get(x - 1).set((y + 4), 2);
                            }
                            if ((y + 4) <= 9) {
                                map.get(x).set((y + 4), 2);
                                if ((x + 1) <= 9)
                                    map.get(x + 1).set((y + 4), 2);
                            }
                            if ((x + 1) <= 9) {
                                if ((y + 3) <= 9)
                                    map.get(x + 1).set((y + 3), 2);
                                if ((y + 2) <= 9)
                                    map.get(x + 1).set((y + 2), 2);
                                if ((y + 1) <= 9)
                                    map.get(x + 1).set((y + 1), 2);
                                map.get(x + 1).set((y), 2);
                                if ((y - 1) >= 0)
                                    map.get(x + 1).set((y - 1), 2);
                            }
                            if ((y - 1) >= 0) {
                                map.get(x).set((y - 1), 2);
                                if ((x - 1) >= 0)
                                    map.get(x - 1).set((y - 1), 2);
                            }
                        }
                        else {
                            map.get(x + 1).set((y), 1);
                            map.get(x + 2).set((y), 1);
                            map.get(x + 3).set((y), 1);
                            if ((x - 1) >= 0) {
                                map.get(x - 1).set((y), 2);
                                if ((y + 1) <= 9)
                                    map.get(x - 1).set((y + 1), 2);
                            }
                            if ((y + 1) <= 9) {
                                map.get(x).set((y + 1), 2);
                                if ((x + 1) <= 9)
                                    map.get(x + 1).set((y + 1), 2);
                                if ((x + 2) <= 9)
                                    map.get(x + 2).set((y + 1), 2);
                                if ((x + 3) <= 9)
                                    map.get(x + 3).set((y + 1), 2);
                                if ((x + 4) <= 9)
                                    map.get(x + 4).set((y + 1), 2);
                            }
                            if ((x + 4) <= 9) {
                                map.get(x + 4).set((y), 2);
                                if ((y - 1) >= 0)
                                    map.get(x + 4).set((y - 1), 2);
                            }
                            if ((y - 1) >= 0) {
                                if ((x + 3) <= 9)
                                    map.get(x + 3).set((y - 1), 2);
                                if ((x + 2) <= 9)
                                    map.get(x + 2).set((y - 1), 2);
                                if ((x + 1) <= 9)
                                    map.get(x + 1).set((y - 1), 2);
                                map.get(x).set((y - 1), 2);
                                if ((x - 1) >= 0)
                                    map.get(x - 1).set((y - 1), 2);
                            }
                        }
                    }
                }
            }
        }
        flag = true;
*/
        for (int i = 0; i < 2; i++) {
            while (flag) {
                int direction = (int) (Math.random() * 2);
                if (direction == 0) {
                    x = (int) (Math.random() * 9);
                    y = (int) (Math.random() * 7);
                } else {
                    x = (int) (Math.random() * 7);
                    y = (int) (Math.random() * 9);
                }
                if (map.get(x).get(y) == 0) {
                    if (((y + 2) <= 9) && ((x + 2) <= 9)) {
                        if (((map.get(x).get(y + 1) == 0) && (map.get(x).get(y + 2) == 0) && (direction == 0)) || ((map.get(x + 1).get(y) == 0) && (map.get(x + 2).get(y) == 0) && (direction == 1))) {
                            map.get(x).set(y, 1);
                            if (direction == 0) {
                                map.get(x).set((y + 1), 1);
                                map.get(x).set((y + 2), 1);
                                if ((x - 1) >= 0) {
                                    map.get(x - 1).set(y, 2);
                                    if ((y + 1) <= 9)
                                        map.get(x - 1).set((y + 1), 2);
                                    if ((y + 2) <= 9)
                                        map.get(x - 1).set((y + 2), 2);
                                    if ((y + 3) <= 9)
                                        map.get(x - 1).set((y + 3), 2);
                                }
                                if ((y + 3) <= 9) {
                                    map.get(x).set((y + 3), 2);
                                    if ((x + 1) <= 9)
                                        map.get(x + 1).set((y + 3), 2);
                                }
                                if ((x + 1) <= 9) {
                                    if ((y + 2) <= 9)
                                        map.get(x + 1).set((y + 2), 2);
                                    if ((y + 1) <= 9)
                                        map.get(x + 1).set((y + 1),2);
                                    map.get(x + 1).set(y, 2);
                                    if ((y - 1) >= 0)
                                        map.get(x + 1).set((y - 1), 2);
                                }
                                if ((y - 1) >= 0) {
                                    map.get(x).set((y - 1), 2);
                                    if ((x - 1) >= 0)
                                        map.get(x - 1).set((y - 1), 2);
                                }
                            } else {
                                map.get(x + 1).set((y), 1);
                                map.get(x + 2).set((y), 1);
                                if ((x - 1) >= 0) {
                                    map.get(x - 1).set(y, 2);
                                    if ((y + 1) <= 9)
                                        map.get(x - 1).set((y + 1), 2);
                                }
                                if ((y + 1) <= 9) {
                                    map.get(x).set((y + 1), 2);
                                    if ((x + 1) <= 9)
                                        map.get(x + 1).set((y + 1), 2);
                                    if ((x + 2) <= 9)
                                        map.get(x + 2).set((y + 1), 2);
                                    if ((x + 3) <= 9)
                                        map.get(x + 3).set((y + 1), 2);
                                }
                                if ((x + 3) <= 9) {
                                    map.get(x + 3).set(y, 2);
                                    if ((y - 1) >= 0)
                                        map.get(x + 3).set((y - 1), 2);
                                }
                                if ((y - 1) >= 0) {
                                    if ((x + 2) <= 9)
                                        map.get(x + 2).set((y - 1), 2);
                                    if ((x + 1) <= 9)
                                        map.get(x + 1).set((y - 1), 2);
                                }
                                if ((y - 1) >= 0) {
                                    map.get(x).set((y - 1), 2);
                                    if ((x - 1) >= 0)
                                        map.get(x - 1).set((y - 1), 2);
                                }
                            }
                            flag = false;
                        }
                    }
                }
            }
            flag = true;
        }

        for (int i = 0; i < 3; i++) {
            while (flag) {
                int direction = (int) (Math.random() * 2);
                if (direction == 0) {
                    x = (int) (Math.random() * 9);
                    y = (int) (Math.random() * 8);
                }
                else {
                    x = (int) (Math.random() * 8);
                    y = (int) (Math.random() * 9);
                }
                if (map.get(x).get(y) == 0) {
                    if (((map.get(x).get(y + 1) == 0) && (direction == 0)) || ((map.get(x + 1).get(y) == 0) && (direction == 1))) {
                        map.get(x).set(y, 1);
                        if (direction == 0) {
                            map.get(x).set((y + 1), 1);
                            if ((x - 1) >= 0) {
                                map.get(x - 1).set(y, 2);
                                if ((y + 1) <= 9)
                                    map.get(x - 1).set((y + 1), 2);
                                if ((y + 2) <= 9)
                                    map.get(x - 1).set((y + 2), 2);
                            }
                            if ((y + 2) <= 9) {
                                map.get(x).set((y + 2), 2);
                                if ((x + 1) <= 9)
                                    map.get(x + 1).set((y + 2), 2);
                            }
                            if ((x + 1) <= 9) {
                                if ((y + 1) <= 9)
                                    map.get(x + 1).set((y + 1), 2);
                                map.get(x + 1).set(y, 2);
                                if ((y - 1) >= 0)
                                    map.get(x + 1).set((y - 1), 2);
                            }
                            if ((y - 1) >= 0) {
                                map.get(x).set((y - 1), 2);
                                if ((x - 1) >= 0)
                                    map.get(x - 1).set((y - 1), 2);
                            }

                        } else {
                            map.get(x + 1).set(y, 1);
                            if ((x - 1) >= 0) {
                                map.get(x - 1).set(y, 2);
                                if ((y + 1) <= 9)
                                    map.get(x - 1).set((y + 1), 2);
                            }
                            if ((y + 1) <= 9) {
                                map.get(x).set((y + 1), 2);
                                if ((x + 1) <= 9)
                                    map.get(x + 1).set((y + 1), 2);
                                if ((x + 2) <= 9)
                                    map.get(x + 2).set((y + 1), 2);
                            }
                            if ((x + 2) <= 9) {
                                map.get(x + 2).set(y, 2);
                                if ((y - 1) >= 0)
                                    map.get(x + 2).set((y - 1), 2);
                            }
                            if ((y - 1) >= 0) {
                                if ((x + 1) <= 9)
                                    map.get(x + 1).set((y - 1), 2);
                                map.get(x).set((y - 1), 2);
                                if ((x - 1) >= 0)
                                map.get(x - 1).set((y - 1), 2);
                            }

                        }
                        flag = false;
                    }
                }
            }
            flag = true;
        }

        for (int i = 0; i < 4; i++) {
            while (flag) {
                x = (int) (Math.random() * 9);
                y = (int) (Math.random() * 9);
                if (map.get(x).get(y) == 0) {
                    map.get(x).set(y, 1);
                    if ((x - 1) >= 0) {
                        map.get(x - 1).set(y, 2);
                        if ((y + 1) <= 9)
                            map.get(x - 1).set((y + 1), 2);
                    }
                    if ((y + 1) <= 9) {
                        map.get(x).set((y + 1), 2);
                        if ((x + 1) <= 9)
                            map.get(x + 1).set((y + 1), 2);
                    }
                    if ((x + 1) <= 9) {
                        map.get(x + 1).set(y, 2);
                        if ((y - 1) >= 0)
                            map.get(x + 1).set((y - 1), 2);
                    }
                    if ((y - 1) >= 0) {
                        map.get(x).set((y - 1), 2);
                        if ((x - 1) >= 0)
                            map.get(x - 1).set((y - 1), 2);
                    }

                    flag = false;
                }
            }
            flag = true;
        }

        boolean shipFlag = true;
        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.get(i).size(); j++) {
                if ((j + 3) <= 9) {
                    if ((map.get(i).get(j) == 0) && (map.get(i).get(j + 1) == 0) && (map.get(i).get(j + 2) == 0) && (map.get(i).get(j + 3) == 0) && shipFlag) {
                        map.get(i).set(j, 1);
                        map.get(i).set((j + 1), 1);
                        map.get(i).set((j + 2), 1);
                        map.get(i).set((j + 3), 1);
                        shipFlag = false;
                        break;
                    }
                }
                if ((i + 3) <= 9) {
                    if ((map.get(i).get(j) == 0) && (map.get(i + 1).get(j) == 0) && (map.get(i + 2).get(j) == 0) && (map.get(i + 3).get(j) == 0) && shipFlag) {
                        map.get(i).set(j, 1);
                        map.get(i + 1).set((j), 1);
                        map.get(i + 2).set((j), 1);
                        map.get(i + 3).set((j), 1);
                        shipFlag = false;
                        break;
                    }
                }
            }
        }


        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.get(i).size(); j++) {
                if (map.get(i).get(j) == 2) {
                    map.get(i).set(j, 0);
                }
            }
        }
        return map;
    }

    public boolean findSameLogin(String name) {

        boolean answer = true;
        try {
            String findQuery = "SELECT * FROM authorization WHERE name='" + name + "';";
            ResultSet resultSet = statement.executeQuery(findQuery);
            answer = resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return answer;

    }

    public void addNewUser(String name, String password) {

        String sizeQuery = "SELECT COUNT( * ) FROM  `authorization`";
        try {
            ResultSet resultSet = statement.executeQuery(sizeQuery);
            resultSet.next();
            int size = resultSet.getInt(1);
            String insertQuery = "INSERT INTO sea_battle.authorization (id, name, password)VALUES (" + (size + 1) + ", '" + name + "', '" + password + "');";
            statement.executeUpdate(insertQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void changeUserName(String name, String password, String newName) {

        String findQuery = "SELECT * FROM authorization WHERE name='" + name + "' and password='" + password + "';";
        try {
            ResultSet resultSet = statement.executeQuery(findQuery);
            if (resultSet.next()) {
                String query = "UPDATE authorization SET name = '" + newName + "' WHERE password='" + password + "';";
                statement.executeUpdate(query);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void changeUserPassword(String name, String password, String newPassword) {

        String findQuery = "SELECT * FROM authorization WHERE name='" + name + "' and password='" + password + "';";
        try {
            ResultSet resultSet = statement.executeQuery(findQuery);
            if (resultSet.next()) {
                String query = "UPDATE authorization SET password = '" + newPassword + "' WHERE password='" + password + "';";
                statement.executeUpdate(query);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public boolean logIn(String name, String password) {

        String findQuery = "SELECT * FROM authorization WHERE name='" + name + "' and password='" + password + "';";
        boolean answer = false;
        try {
            ResultSet resultSet = statement.executeQuery(findQuery);
            answer = resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return answer;

    }

    public List<String> getUsersNames() {

        List<String> names = new ArrayList<>();
        String findQuery = "select id, name, password from authorization";
        try {
            ResultSet resultSet = statement.executeQuery(findQuery);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                names.add(resultSet.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return names;
    }

    public void usersBattleInitiate(String callingUserName, String replyingUserName) {

        String createQuery = "CREATE TABLE " + callingUserName + "_" + replyingUserName + "(" +
                " id INT," + " zero INT," + " one INT," + " two INT," + " three INT," + " four INT," +
                " five INT," + " six INT," + " seven INT," + " eight INT," + " nine INT" + ");";
        try {
            this.statement.executeUpdate(createQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void replyingAnswerBattle(String dbName) {

        String insertQuery = "INSERT INTO " + dbName + "(id, zero, one, two, three, four, five, six, seven, eight, nine)VALUES ('1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');";
        try {
            this.statement.executeUpdate(insertQuery);

            System.out.println(dbName);
            String query = "select id, zero, one, two, three, four, five, six, seven, eight, nine from " + dbName;
            ResultSet resultSet = null;
            resultSet = statement.executeQuery(query);
            resultSet.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void createCallingTables(String callingUserName, String replyingUserName, List<List<Integer>> callingTable) {

        this.callingSelf = callingTable;
        try {
            String sizeQuery = "SELECT COUNT( * ) FROM  `" + callingUserName + "_" + replyingUserName + "`";
            ResultSet resultSet = statement.executeQuery(sizeQuery);
            resultSet.next();
            int size = resultSet.getInt(1);
            System.out.println("size in createCallingTables = " + size);

            if (size == 0) {
                for (int i = 0; i < 10; i++) {
                    String insertQuery = "INSERT INTO " + callingUserName + "_" + replyingUserName + "(id, zero, one, two, three, four, five, six, seven, eight, nine)VALUES ('" + i + "', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');";
                    this.statement.executeUpdate(insertQuery);
                }

                for (int i = 0; i < 10; i++) {
                    String insertQuery = "INSERT INTO " + callingUserName + "_" + replyingUserName + "(id, zero, one, two, three, four, five, six, seven, eight, nine)VALUES (" + (i + 10) + ", " +
                            callingSelf.get(i).get(0) + ", " + callingSelf.get(i).get(1) + ", " + callingSelf.get(i).get(2) + ", " + callingSelf.get(i).get(3) + ", " +
                            callingSelf.get(i).get(4) + ", " + callingSelf.get(i).get(5) + ", " + callingSelf.get(i).get(6) + ", " + callingSelf.get(i).get(7) + ", " +
                            callingSelf.get(i).get(8) + ", " + callingSelf.get(i).get(9) + ");";
                    this.statement.executeUpdate(insertQuery);
                }
            }
            else {

                String[] coords = new String[]{"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        String query = "UPDATE " + callingUserName + "_" + replyingUserName + " SET " + coords[j] + " = '" + 0 + "' WHERE id = '" + (i) + "';";
                        statement.executeUpdate(query);
                    }
                }

                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        String query = "UPDATE " + callingUserName + "_" + replyingUserName + " SET " + coords[j] + " = '" + callingSelf.get(i).get(j) + "' WHERE id = '" + (i + 10) + "';";
                        statement.executeUpdate(query);
                    }
                }

                String insertQuery = "INSERT INTO " + callingUserName + "_" + replyingUserName + "(id, zero, one, two, three, four, five, six, seven, eight, nine)VALUES ('" + 40 + "', '5', '0', '0', '0', '0', '0', '0', '0', '0', '0');";
                this.statement.executeUpdate(insertQuery);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void createReplyingTables(String callingUserName, String replyingUserName, List<List<Integer>> replyingTable) {

        this.replyingSelf = replyingTable;
        try {
            String sizeQuery = "SELECT COUNT( * ) FROM  `" + callingUserName + "_" + replyingUserName + "`";
            ResultSet resultSet = statement.executeQuery(sizeQuery);
            resultSet.next();
            int size = resultSet.getInt(1);
            System.out.println(size);

            if (size != 0) {
                for (int i = 0; i < 10; i++) {
                    String insertQuery = "INSERT INTO " + callingUserName + "_" + replyingUserName + "(id, zero, one, two, three, four, five, six, seven, eight, nine)VALUES (" + (i + 20) + ", '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');";
                    this.statement.executeUpdate(insertQuery);
                }

                for (int i = 0; i < 10; i++) {
                    String insertQuery = "INSERT INTO " + callingUserName + "_" + replyingUserName + "(id, zero, one, two, three, four, five, six, seven, eight, nine)VALUES (" + (i + 30) + ", " +
                            replyingSelf.get(i).get(0) + ", " + replyingSelf.get(i).get(1) + ", " + replyingSelf.get(i).get(2) + ", " + replyingSelf.get(i).get(3) + ", " +
                            replyingSelf.get(i).get(4) + ", " + replyingSelf.get(i).get(5) + ", " + replyingSelf.get(i).get(6) + ", " + replyingSelf.get(i).get(7) + ", " +
                            replyingSelf.get(i).get(8) + ", " + replyingSelf.get(i).get(9) + ");";
                    this.statement.executeUpdate(insertQuery);
                }

                String insertQuery = "INSERT INTO " + callingUserName + "_" + replyingUserName + "(id, zero, one, two, three, four, five, six, seven, eight, nine)VALUES ('" + 40 + "', '5', '0', '0', '0', '0', '0', '0', '0', '0', '0');";
                this.statement.executeUpdate(insertQuery);

            }
            else {

                for (int i = 0; i < 10; i++) {
                    String insertQuery = "INSERT INTO " + callingUserName + "_" + replyingUserName + "(id, zero, one, two, three, four, five, six, seven, eight, nine)VALUES (" + (i) + ", '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');";
                    this.statement.executeUpdate(insertQuery);
                }

                for (int i = 0; i < 10; i++) {
                    String insertQuery = "INSERT INTO " + callingUserName + "_" + replyingUserName + "(id, zero, one, two, three, four, five, six, seven, eight, nine)VALUES (" + (i + 10) + ", '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');";
                    this.statement.executeUpdate(insertQuery);
                }

                for (int i = 0; i < 10; i++) {
                    String insertQuery = "INSERT INTO " + callingUserName + "_" + replyingUserName + "(id, zero, one, two, three, four, five, six, seven, eight, nine)VALUES (" + (i + 20) + ", '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');";
                    this.statement.executeUpdate(insertQuery);
                }

                for (int i = 0; i < 10; i++) {
                    String insertQuery = "INSERT INTO " + callingUserName + "_" + replyingUserName + "(id, zero, one, two, three, four, five, six, seven, eight, nine)VALUES (" + (i + 30) + ", " +
                            replyingSelf.get(i).get(0) + ", " + replyingSelf.get(i).get(1) + ", " + replyingSelf.get(i).get(2) + ", " + replyingSelf.get(i).get(3) + ", " +
                            replyingSelf.get(i).get(4) + ", " + replyingSelf.get(i).get(5) + ", " + replyingSelf.get(i).get(6) + ", " + replyingSelf.get(i).get(7) + ", " +
                            replyingSelf.get(i).get(8) + ", " + replyingSelf.get(i).get(9) + ");";
                    this.statement.executeUpdate(insertQuery);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public int sizeOfUsersTables(String callingUserName, String replyingUserName) {

        String sizeQuery = "SELECT COUNT( * ) FROM  `" + callingUserName + "_" + replyingUserName + "`";
        ResultSet resultSet = null;
        int size = 0;
        try {
            resultSet = statement.executeQuery(sizeQuery);
            resultSet.next();
            size = resultSet.getInt(1);
            System.out.println("size in sizeOfUsersTables = " + size);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return size;

    }

    public String findUsersBattle(String replyingUserName) {
        //String selectQuery = "SHOW TABLES LIKE '_"+ replyingUserName + "';";
        String selectQuery = "SHOW TABLES;";
        ResultSet resultSet = null;
        boolean answer = false;
        String dbName = "";

        try {
            resultSet = statement.executeQuery(selectQuery);
            resultSet.next();
            if (resultSet.getString(1) != null) {
                while (resultSet.getString(1) != null) {
                    if (resultSet.getString(1).split("_").length > 1) {
                        if ((resultSet.getString(1).split("_")[1].equals(replyingUserName.toLowerCase()))) {
                            System.out.println(resultSet.getString(1));
                            break;
                        }
                    }
                    resultSet.next();
                }
                dbName = resultSet.getString(1);
                answer = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dbName;
    }

    public boolean isUsersBattleInitialized(String callingUserName, String replyingUserName) {

        String sizeQuery = "SELECT COUNT( * ) FROM  `" + callingUserName + "_" + replyingUserName + "`";
        ResultSet resultSet = null;
        boolean answer = false;
        try {
            resultSet = statement.executeQuery(sizeQuery);
            resultSet.next();
            int size = resultSet.getInt(1);
            if (size != 0) {
                answer = true;
                String deleteQuery = "DELETE FROM  `" + callingUserName + "_" + replyingUserName + "` WHERE id=1;";
                statement.executeUpdate(deleteQuery);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return answer;
    }

    public List<List<Integer>> getCallingEnemy(String callingUserName, String replyingUserName) {

        List<List<Integer>> answer = new ArrayList<List<Integer>>();
        String query = "select id, zero, one, two, three, four, five, six, seven, eight, nine from " + callingUserName + "_" + replyingUserName;
        ResultSet resultSet = null;

        try {
            resultSet = statement.executeQuery(query);

            for (int i = 0; i < 10; i++) {
                resultSet.next();
                answer.add(new ArrayList<>());
                for (int j = 2; j < 12; j++) {
                    String coord = resultSet.getString(j);
                    answer.get(i).add(Integer.parseInt(coord));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return answer;

    }

    public List<List<Integer>> getCallingSelf(String callingUserName, String replyingUserName) {

        List<List<Integer>> answer = new ArrayList<List<Integer>>();
        String query = "select id, zero, one, two, three, four, five, six, seven, eight, nine from " + callingUserName + "_" + replyingUserName;
        ResultSet resultSet = null;

        try {
            resultSet = statement.executeQuery(query);

            for (int i = 0; i < 10; i++) {
                resultSet.next();
            }

            for (int i = 0; i < 10; i++) {
                resultSet.next();
                answer.add(new ArrayList<>());
                for (int j = 2; j < 12; j++) {
                    String coord = resultSet.getString(j);
                    answer.get(i).add(Integer.parseInt(coord));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return answer;

    }

    public List<List<Integer>> getReplyingEnemy(String callingUserName, String replyingUserName) {

        List<List<Integer>> answer = new ArrayList<List<Integer>>();
        String query = "select id, zero, one, two, three, four, five, six, seven, eight, nine from " + callingUserName + "_" + replyingUserName;
        ResultSet resultSet = null;

        try {
            resultSet = statement.executeQuery(query);

            for (int i = 0; i < 20; i++) {
                resultSet.next();
            }

            for (int i = 0; i < 10; i++) {
                resultSet.next();
                answer.add(new ArrayList<>());
                for (int j = 2; j < 12; j++) {
                    String coord = resultSet.getString(j);
                    answer.get(i).add(Integer.parseInt(coord));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return answer;

    }

    public List<List<Integer>> getReplyingSelf(String callingUserName, String replyingUserName) {

        List<List<Integer>> answer = new ArrayList<List<Integer>>();
        String query = "select id, zero, one, two, three, four, five, six, seven, eight, nine from " + callingUserName + "_" + replyingUserName;
        ResultSet resultSet = null;

        try {
            resultSet = statement.executeQuery(query);

            for (int i = 0; i < 30; i++) {
                resultSet.next();
            }

            for (int i = 0; i < 10; i++) {
                resultSet.next();
                answer.add(new ArrayList<>());
                for (int j = 2; j < 12; j++) {
                    String coord = resultSet.getString(j);
                    answer.get(i).add(Integer.parseInt(coord));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return answer;

    }

    public void closeUsersBattle(String callingUserName, String replyingUserName) {
        String deleteQuery = "DROP TABLE " + callingUserName + "_" + replyingUserName + ";";
        try {
            statement.executeUpdate(deleteQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void battleInitiate(String name, List<List<Integer>> userTable) {

        this.userUser = userTable;
        String createQuery = "CREATE TABLE " + name + "(" +
                " id INT," + " zero INT," + " one INT," + " two INT," + " three INT," + " four INT," +
                " five INT," + " six INT," + " seven INT," + " eight INT," + " nine INT" + ");";
        try {
            this.statement.executeUpdate(createQuery);
            String sizeQuery = "SELECT COUNT( * ) FROM  `" + name + "`";
            ResultSet resultSet = statement.executeQuery(sizeQuery);
            resultSet.next();
            int size = resultSet.getInt(1);
            for (int i = 0; i < 10; i++) {
                String insertQuery = "INSERT INTO " + name + "(id, zero, one, two, three, four, five, six, seven, eight, nine)VALUES ('" + i + "', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');";
                this.statement.executeUpdate(insertQuery);
            }

            for (int i = 0; i < 10; i++) {
                String insertQuery = "INSERT INTO " + name + "(id, zero, one, two, three, four, five, six, seven, eight, nine)VALUES (" + (i + 10) + ", " +
                        userUser.get(i).get(0) + ", " + userUser.get(i).get(1) + ", " + userUser.get(i).get(2) + ", " + userUser.get(i).get(3) + ", " +
                        userUser.get(i).get(4) + ", " + userUser.get(i).get(5) + ", " + userUser.get(i).get(6) + ", " + userUser.get(i).get(7) + ", " +
                        userUser.get(i).get(8) + ", " + userUser.get(i).get(9) + ");";
                this.statement.executeUpdate(insertQuery);
            }

            for (int i = 0; i < 10; i++) {
                String insertQuery = "INSERT INTO " + name + "(id, zero, one, two, three, four, five, six, seven, eight, nine)VALUES (" + (i + 20) + ", '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');";
                this.statement.executeUpdate(insertQuery);
            }

            for (int i = 0; i < 10; i++) {
                String insertQuery = "INSERT INTO " + name + "(id, zero, one, two, three, four, five, six, seven, eight, nine)VALUES (" + (i + 30) + ", " +
                        botBot.get(i).get(0) + ", " + botBot.get(i).get(1) + ", " + botBot.get(i).get(2) + ", " + botBot.get(i).get(3) + ", " +
                        botBot.get(i).get(4) + ", " + botBot.get(i).get(5) + ", " + botBot.get(i).get(6) + ", " + botBot.get(i).get(7) + ", " +
                        botBot.get(i).get(8) + ", " + botBot.get(i).get(9) + ");";
                this.statement.executeUpdate(insertQuery);
            }

            /*String query = "select id, zero, one, two, three, four, five, six, seven, eight, nine from " + name;
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                for (int j = 1; j < 12; j++) {
                    String coord = resultSet.getString(j);
                    System.out.print(coord + " ");
                }
                System.out.println();
            }*/

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void closeBattle(String name) {
        String deleteQuery = "DROP TABLE " + name + ";";
        try {
            statement.executeUpdate(deleteQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<List<Integer>> getUserBot(String name) {

        List<List<Integer>> answer = new ArrayList<List<Integer>>();
        String query = "select id, zero, one, two, three, four, five, six, seven, eight, nine from " + name;
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery(query);

            for (int i = 0; i < 10; i++) {
                resultSet.next();
                answer.add(new ArrayList<>());
                for (int j = 2; j < 12; j++) {
                    String coord = resultSet.getString(j);
                    answer.get(i).add(Integer.parseInt(coord));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return answer;
    }

    public List<List<Integer>> getUserUser(String name) {

        List<List<Integer>> answer = new ArrayList<List<Integer>>();
        String query = "select id, zero, one, two, three, four, five, six, seven, eight, nine from " + name;
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery(query);

            for (int i = 0; i < 10; i++) {
                resultSet.next();
            }

            for (int i = 0; i < 10; i++) {
                resultSet.next();
                answer.add(new ArrayList<>());
                for (int j = 2; j < 12; j++) {
                    String coord = resultSet.getString(j);
                    answer.get(i).add(Integer.parseInt(coord));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return answer;

    }

    public List<List<Integer>> getBotUser(String name) {

        List<List<Integer>> answer = new ArrayList<List<Integer>>();
        String query = "select id, zero, one, two, three, four, five, six, seven, eight, nine from " + name;
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery(query);

            for (int i = 0; i < 20; i++) {
                resultSet.next();
            }

            for (int i = 0; i < 10; i++) {
                resultSet.next();
                answer.add(new ArrayList<>());
                for (int j = 2; j < 12; j++) {
                    String coord = resultSet.getString(j);
                    answer.get(i).add(Integer.parseInt(coord));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return answer;

    }

    public List<List<Integer>> getBotBot(String name) {

        List<List<Integer>> answer = new ArrayList<List<Integer>>();
        String query = "select id, zero, one, two, three, four, five, six, seven, eight, nine from " + name;
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery(query);

            for (int i = 0; i < 30; i++) {
                resultSet.next();
            }

            for (int i = 0; i < 10; i++) {
                resultSet.next();
                answer.add(new ArrayList<>());
                for (int j = 2; j < 12; j++) {
                    String coord = resultSet.getString(j);
                    answer.get(i).add(Integer.parseInt(coord));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return answer;

    }

    public void update(String name, List<List<Integer>> userBot, List<List<Integer>> userUser, List<List<Integer>> botUser, List<List<Integer>> botBot) {

        String[] coords = new String[]{"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        try {

            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    String query = "UPDATE " + name + " SET " + coords[j] + " = '" + userBot.get(i).get(j) + "' WHERE id = '" + (i) + "';";
                    statement.executeUpdate(query);
                }
            }

            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    String query = "UPDATE " + name + " SET " + coords[j] + " = '" + userUser.get(i).get(j) + "' WHERE id = '" + (i + 10) + "';";
                    statement.executeUpdate(query);
                }
            }

            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    String query = "UPDATE " + name + " SET " + coords[j] + " = '" + botUser.get(i).get(j) + "' WHERE id = '" + (i + 20) + "';";
                    statement.executeUpdate(query);
                }
            }

            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    String query = "UPDATE " + name + " SET " + coords[j] + " = '" + botBot.get(i).get(j) + "' WHERE id = '" + (i + 30) + "';";
                    statement.executeUpdate(query);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUsersBattle(String callingUserName, String replyingUserName, List<List<Integer>> callingEnemy, List<List<Integer>> callingSelf, List<List<Integer>> replyingEnemy, List<List<Integer>> replyingSelf) {

        String[] coords = new String[]{"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        try {

            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    String query = "UPDATE " + callingUserName + "_" + replyingUserName + " SET " + coords[j] + " = '" + callingEnemy.get(i).get(j) + "' WHERE id = '" + (i) + "';";
                    statement.executeUpdate(query);
                }
            }

            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    String query = "UPDATE " + callingUserName + "_" + replyingUserName + " SET " + coords[j] + " = '" + callingSelf.get(i).get(j) + "' WHERE id = '" + (i + 10) + "';";
                    statement.executeUpdate(query);
                }
            }

            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    String query = "UPDATE " + callingUserName + "_" + replyingUserName + " SET " + coords[j] + " = '" + replyingEnemy.get(i).get(j) + "' WHERE id = '" + (i + 20) + "';";
                    statement.executeUpdate(query);
                }
            }

            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    String query = "UPDATE " + callingUserName + "_" + replyingUserName + " SET " + coords[j] + " = '" + replyingSelf.get(i).get(j) + "' WHERE id = '" + (i + 30) + "';";
                    statement.executeUpdate(query);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void changeFlag(String callingUserName, String replyingUserName, String gameRole, int endgame) {

        if (gameRole.equals("calling")) {
            String str = "-5";
            if (endgame == 9) {
                str = "9";
            }
            String query = "UPDATE " + callingUserName + "_" + replyingUserName + " SET zero = '" + str + "' WHERE id = '40';";
            try {
                statement.executeUpdate(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else {
            String str = "5";
            if (endgame == 9) {
                str = "9";
            }
            String query = "UPDATE " + callingUserName + "_" + replyingUserName + " SET zero = '" + str + "' WHERE id = '40';";
            try {
                statement.executeUpdate(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public int checkFlag(String callingUserName, String replyingUserName, String gameRole) {

        String query = "select id, zero, one, two, three, four, five, six, seven, eight, nine from " + callingUserName + "_" + replyingUserName;
        ResultSet resultSet = null;
        int answer = 0;
        String coord = "";
        try {
            resultSet = statement.executeQuery(query);

            for (int i = 0; i < 41; i++) {
                resultSet.next();
            }

            coord += resultSet.getString(2);
            answer = (Integer.parseInt(coord));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("IN CHECKFLAG = " + coord);
        return answer;

    }

    public void setWinner(String callingUserName, String replyingUserName, int flag) {

        String createQuery = "CREATE TABLE " + callingUserName + "win" + replyingUserName + "(id INT);";
        String insertQuery = "INSERT INTO " + callingUserName + "win" + replyingUserName + "(id)VALUES ('" + flag + "');";
        try {
            this.statement.executeUpdate(createQuery);
            this.statement.executeUpdate(insertQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public int getWinner(String callingUserName, String replyingUserName) {

        String query = "select id from " + callingUserName + "win" + replyingUserName + ";";
        String delete = "DROP TABLE " + callingUserName + "win" + replyingUserName + ";";
        ResultSet resultSet = null;
        int answer = 0;
        String coord = "";
        try {
            resultSet = statement.executeQuery(query);
            resultSet.next();

            coord += resultSet.getString(1);
            answer = (Integer.parseInt(coord));
            statement.executeUpdate(delete);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("IN CHECKWINNER = " + coord);
        return answer;

    }

}
