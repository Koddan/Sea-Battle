package app.model;

import app.database.DataBaseInputOutput;

import java.util.ArrayList;
import java.util.List;

public class SeaBattleBot {

    DataBaseInputOutput dataBase;
    List<List<Integer>> userBot;
    List<List<Integer>> userUser;
    List<List<Integer>> botUser;
    List<List<Integer>> botBot;
    String name;
    boolean flag = false;

    public SeaBattleBot(String name) {

        dataBase = new DataBaseInputOutput();
        this.name = name;
        userBot = dataBase.getUserBot(name);
        userUser = dataBase.getUserUser(name);
        botUser = dataBase.getBotUser(name);
        botBot = dataBase.getBotBot(name);

    }

    public SeaBattleBot(boolean flag) {

        this.flag = true;
        this.userBot = new ArrayList<>();
        this.userUser = new ArrayList<>();
        this.botUser = new ArrayList<>();
        this.botBot = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            botBot.add(new ArrayList<>());
            for (int j = 0; j < 10; j++) {
                botBot.get(i).add(0);
            }
        }

        for (int i = 0; i < 10; i++) {
            userBot.add(new ArrayList<>());
            for (int j = 0; j < 10; j++) {
                userBot.get(i).add(0);
            }
        }

        for (int i = 0; i < 10; i++) {
            botUser.add(new ArrayList<>());
            for (int j = 0; j < 10; j++) {
                botUser.get(i).add(0);
            }
        }

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
        userUser.add(tmp0);
        userUser.add(tmp1);
        userUser.add(tmp2);
        userUser.add(tmp3);
        userUser.add(tmp4);
        userUser.add(tmp5);
        userUser.add(tmp6);
        userUser.add(tmp7);
        userUser.add(tmp8);
        userUser.add(tmp9);

    }

    public int isWin() {

        int userShipsSum = 0;
        int botShipsSum = 0;
        for (int i = 0; i < userUser.size(); i++) {
            for (int j = 0; j < userUser.get(i).size(); j++) {
                if (userUser.get(i).get(j) == 1) {
                    userShipsSum++;
                }
                if (botBot.get(i).get(j) == 1) {
                    botShipsSum++;
                }
            }
        }
        if (userShipsSum == 0) {
            return -5;
        }
        if (botShipsSum == 0) {
            return 5;
        }

        return 0;
    }

    public int botAnswer(int x, int y) {

        if (botBot.get(x).get(y) == 1) {
            botBot.get(x).set(y, 3);
            userBot.get(x).set(y, 3);
        }
        if (botBot.get(x).get(y) == 0) {
            botBot.get(x).set(y, 2);
            userBot.get(x).set(y, 2);

            boolean flag = true;
            int botX = 0;
            int botY = 0;
            while (flag) {
                botX = (int) (Math.random() * 9);
                botY = (int) (Math.random() * 9);
                if (botUser.get(botX).get(botY) == 0) {
                    flag = false;
                }
            }

            if (userUser.get(botX).get(botY) == 1) {
                userUser.get(botX).set(botY, 3);
                botUser.get(botX).set(botY, 3);

                boolean got = true;
                while (got) {
                    if (((botX - 1) >= 0) && ((botY - 1) >= 0) && ((botX + 1) <= 9) && ((botY + 1) <= 9)) {
                        if ((userUser.get(botX - 1).get(botY) != 1) && (userUser.get(botX - 1).get(botY + 1) != 1) && (userUser.get(botX).get(botY + 1) != 1) && (userUser.get(botX + 1).get(botY + 1) != 1)
                                && (userUser.get(botX + 1).get(botY) != 1) && (userUser.get(botX + 1).get(botY - 1) != 1) && (userUser.get(botX).get(botY - 1) != 1) && (userUser.get(botX - 1).get(botY - 1) != 1)) {
                            got = false;
                        } else {
                            int direction = (int) (Math.random() * 3);
                            if (direction == 0) {
                                botX--;
                            }
                            if (direction == 1) {
                                botY++;
                            }
                            if (direction == 2) {
                                botX++;
                            }
                            if (direction == 3) {
                                botY--;
                            }
                            if (userUser.get(botX).get(botY) == 1) {
                                userUser.get(botX).set(botY, 3);
                                botUser.get(botX).set(botY, 3);
                                if (((botX - 1) >= 0) && ((botY - 1) >= 0) && ((botX + 1) <= 9) && ((botY + 1) <= 9)) {
                                    if ((userUser.get(botX - 1).get(botY) != 1) && (userUser.get(botX - 1).get(botY + 1) != 1) && (userUser.get(botX).get(botY + 1) != 1) && (userUser.get(botX + 1).get(botY + 1) != 1)
                                            && (userUser.get(botX + 1).get(botY) != 1) && (userUser.get(botX + 1).get(botY - 1) != 1) && (userUser.get(botX).get(botY - 1) != 1) && (userUser.get(botX - 1).get(botY - 1) != 1)) {
                                        got = false;
                                    } else {
                                        if (direction == 0) {
                                            botX--;
                                        }
                                        if (direction == 1) {
                                            botY++;
                                        }
                                        if (direction == 2) {
                                            botX++;
                                        }
                                        if (direction == 3) {
                                            botY--;
                                        }
                                        if (userUser.get(botX).get(botY) == 1) {
                                            userUser.get(botX).set(botY, 3);
                                            botUser.get(botX).set(botY, 3);
                                            if (((botX - 1) >= 0) && ((botY - 1) >= 0) && ((botX + 1) <= 9) && ((botY + 1) <= 9)) {
                                                if ((userUser.get(botX - 1).get(botY) != 1) && (userUser.get(botX - 1).get(botY + 1) != 1) && (userUser.get(botX).get(botY + 1) != 1) && (userUser.get(botX + 1).get(botY + 1) != 1)
                                                        && (userUser.get(botX + 1).get(botY) != 1) && (userUser.get(botX + 1).get(botY - 1) != 1) && (userUser.get(botX).get(botY - 1) != 1) && (userUser.get(botX - 1).get(botY - 1) != 1)) {
                                                    got = false;
                                                } else {
                                                    if (direction == 0) {
                                                        botX--;
                                                    }
                                                    if (direction == 1) {
                                                        botY++;
                                                    }
                                                    if (direction == 2) {
                                                        botX++;
                                                    }
                                                    if (direction == 3) {
                                                        botY--;
                                                    }
                                                    if (userUser.get(botX).get(botY) == 1) {
                                                        userUser.get(botX).set(botY, 3);
                                                        botUser.get(botX).set(botY, 3);
                                                        got = false;
                                                    } else if (userUser.get(botX).get(botY) == 0) {
                                                        userUser.get(botX).set(botY, 2);
                                                        botUser.get(botX).set(botY, 2);
                                                        got = false;
                                                    }
                                                }
                                            }
                                        } else if (userUser.get(botX).get(botY) == 0) {
                                            userUser.get(botX).set(botY, 2);
                                            botUser.get(botX).set(botY, 2);
                                            got = false;
                                        }
                                    }
                                }
                            } else if (userUser.get(botX).get(botY) == 0) {
                                userUser.get(botX).set(botY, 2);
                                botUser.get(botX).set(botY, 2);
                                got = false;
                            }
                        }
                    }
                }
            }

            if (userUser.get(botX).get(botY) == 0) {
                userUser.get(botX).set(botY, 2);
                botUser.get(botX).set(botY, 2);
            }
        }
        dataBase.update(name, userBot, userUser, botUser, botBot);

        return this.isWin();
    }

    public String finishedShips() {

        if (!this.flag) {
            userBot = dataBase.getUserBot(name);
            userUser = dataBase.getUserUser(name);
            botUser = dataBase.getBotUser(name);
            botBot = dataBase.getBotBot(name);
        }
        List<List<List<Integer>>> allTables = new ArrayList<>();
        List<List<List<Integer>>> secondTables = new ArrayList<>();

        allTables.add(userBot);
        allTables.add(botBot);
        secondTables.add(botUser);
        secondTables.add(userUser);

        for (List<List<Integer>> allTable : allTables) {
            List<String> markedShips = new ArrayList<>();
            for (int i = 0; i < allTable.size(); i++) {
                for (int j = 0; j < allTable.get(i).size(); j++) {
                    List<String> tmpShips = new ArrayList<>();
                    String tmpCoord = i + " " + j;
                    if (allTable.get(i).get(j) == 3 && (markedShips.indexOf(tmpCoord) == -1)) {
                        int step = 0;
                        tmpShips.add(tmpCoord);
                        if ((i - 1) >= 0) {
                            if (allTable.get(i - 1).get(j) == 3) {
                                int counter = 0;
                                while (botBot.get(i + counter).get(j) == 3) {
                                    String coords = (i + counter) + " " + j;
                                    tmpShips.add(coords);
                                    counter--;
                                    if (i + counter < 0)
                                        break;
                                }
                            }
                        }
                        if ((j + 1) <= 9) {
                            if (botBot.get(i).get(j + 1) == 3) {
                                int counter = 0;
                                while (allTable.get(i).get(j + counter) == 3) {
                                    String coords = i + " " + (j + counter);
                                    tmpShips.add(coords);
                                    counter++;
                                    if (j + counter > 9)
                                        break;
                                }
                            }
                        }
                        if ((i + 1) <= 9) {
                            if (botBot.get(i + 1).get(j) == 3) {
                                int counter = 0;
                                while (allTable.get(i + counter).get(j) == 3) {
                                    String coords = (i + counter) + " " + j;
                                    tmpShips.add(coords);
                                    counter++;
                                    if (i + counter > 9)
                                        break;
                                }
                            }
                        }
                        if ((j - 1) >= 0) {
                            if (botBot.get(i).get(j - 1) == 3) {
                                int counter = 0;
                                while (allTable.get(i).get(j + counter) == 3) {
                                    String coords = i + " " + (j + counter);
                                    tmpShips.add(coords);
                                    counter--;
                                    if (j + counter < 0)
                                        break;
                                }
                            }
                        }

                        boolean flag = true;
                        for (String tmpShip : tmpShips) {
                            int nowI = Integer.parseInt(tmpShip.split(" ")[0]);
                            int nowJ = Integer.parseInt(tmpShip.split(" ")[1]);
                            if ((nowI - 1) >= 0) {
                                if (botBot.get(nowI - 1).get(nowJ) == 1)
                                    flag = false;
                            }
                            if ((nowJ + 1) <= 9) {
                                if (botBot.get(nowI).get(nowJ + 1) == 1)
                                    flag = false;
                            }
                            if ((nowI + 1) <= 9) {
                                if (botBot.get(nowI + 1).get(nowJ) == 1)
                                    flag = false;
                            }
                            if ((nowJ - 1) >= 0) {
                                if (botBot.get(nowI).get(nowJ - 1) == 1)
                                    flag = false;
                            }
                        }
                        if (flag) {
                            for (String tmpShip : tmpShips) {
                                int nowI = Integer.parseInt(tmpShip.split(" ")[0]);
                                int nowJ = Integer.parseInt(tmpShip.split(" ")[1]);
                                String toAdd = nowI + " " + nowJ;
                                markedShips.add(toAdd);
                                if ((nowI - 1) >= 0) {
                                    if (botBot.get(nowI - 1).get(nowJ) != 3) {
                                        allTable.get(nowI - 1).set(nowJ, 2);
                                    }
                                }
                                if ((nowJ + 1) <= 9) {
                                    if (botBot.get(nowI).get(nowJ + 1) != 3) {
                                        allTable.get(nowI).set(nowJ + 1, 2);
                                    }
                                }
                                if ((nowI + 1) <= 9) {
                                    if (botBot.get(nowI + 1).get(nowJ) != 3) {
                                        allTable.get(nowI + 1).set(nowJ, 2);
                                    }
                                }
                                if ((nowJ - 1) >= 0) {
                                    if (botBot.get(nowI).get(nowJ - 1) != 3) {
                                        allTable.get(nowI).set(nowJ - 1, 2);
                                    }
                                }
                                if (((nowI - 1) >= 0) && ((nowJ - 1) >= 0)) {
                                    if (botBot.get(nowI - 1).get(nowJ - 1) != 3) {
                                        allTable.get(nowI - 1).set(nowJ - 1, 2);
                                    }
                                }
                                if (((nowI + 1) <= 9) && ((nowJ - 1) >= 0)) {
                                    if (botBot.get(nowI + 1).get(nowJ - 1) != 3) {
                                        allTable.get(nowI + 1).set(nowJ - 1, 2);
                                    }
                                }
                                if (((nowI + 1) <= 9) && ((nowJ + 1) <= 9)) {
                                    if (botBot.get(nowI + 1).get(nowJ + 1) != 3) {
                                        allTable.get(nowI + 1).set(nowJ + 1, 2);
                                    }
                                }
                                if (((nowI - 1) >= 0) && ((nowJ + 1) <= 9)) {
                                    if (botBot.get(nowI - 1).get(nowJ + 1) != 3) {
                                        allTable.get(nowI - 1).set(nowJ + 1, 2);
                                    }
                                }
                            }
                        }

                    }

                }
            }
        }

        for (List<List<Integer>> secondTable : secondTables) {
            List<String> markedShips = new ArrayList<>();
            for (int i = 0; i < secondTable.size(); i++) {
                for (int j = 0; j < secondTable.get(i).size(); j++) {
                    List<String> tmpShips = new ArrayList<>();
                    String tmpCoord = i + " " + j;
                    if (secondTable.get(i).get(j) == 3 && (markedShips.indexOf(tmpCoord) == -1)) {
                        int step = 0;
                        tmpShips.add(tmpCoord);
                        if ((i - 1) >= 0) {
                            if (secondTable.get(i - 1).get(j) == 3) {
                                int counter = 0;
                                while (userUser.get(i + counter).get(j) == 3) {
                                    String coords = (i + counter) + " " + j;
                                    tmpShips.add(coords);
                                    counter--;
                                    if (i + counter < 0)
                                        break;
                                }
                            }
                        }
                        if ((j + 1) <= 9) {
                            if (userUser.get(i).get(j + 1) == 3) {
                                int counter = 0;
                                while (secondTable.get(i).get(j + counter) == 3) {
                                    String coords = i + " " + (j + counter);
                                    tmpShips.add(coords);
                                    counter++;
                                    if (j + counter > 9)
                                        break;
                                }
                            }
                        }
                        if ((i + 1) <= 9) {
                            if (userUser.get(i + 1).get(j) == 3) {
                                int counter = 0;
                                while (secondTable.get(i + counter).get(j) == 3) {
                                    String coords = (i + counter) + " " + j;
                                    tmpShips.add(coords);
                                    counter++;
                                    if (i + counter > 9)
                                        break;
                                }
                            }
                        }
                        if ((j - 1) >= 0) {
                            if (userUser.get(i).get(j - 1) == 3) {
                                int counter = 0;
                                while (secondTable.get(i).get(j + counter) == 3) {
                                    String coords = i + " " + (j + counter);
                                    tmpShips.add(coords);
                                    counter--;
                                    if (j + counter < 0)
                                        break;
                                }
                            }
                        }

                        boolean flag = true;
                        for (String tmpShip : tmpShips) {
                            int nowI = Integer.parseInt(tmpShip.split(" ")[0]);
                            int nowJ = Integer.parseInt(tmpShip.split(" ")[1]);
                            if ((nowI - 1) >= 0) {
                                if (userUser.get(nowI - 1).get(nowJ) == 1)
                                    flag = false;
                            }
                            if ((nowJ + 1) <= 9) {
                                if (userUser.get(nowI).get(nowJ + 1) == 1)
                                    flag = false;
                            }
                            if ((nowI + 1) <= 9) {
                                if (userUser.get(nowI + 1).get(nowJ) == 1)
                                    flag = false;
                            }
                            if ((nowJ - 1) >= 0) {
                                if (userUser.get(nowI).get(nowJ - 1) == 1)
                                    flag = false;
                            }
                        }
                        if (flag) {
                            for (String tmpShip : tmpShips) {
                                int nowI = Integer.parseInt(tmpShip.split(" ")[0]);
                                int nowJ = Integer.parseInt(tmpShip.split(" ")[1]);
                                String toAdd = nowI + " " + nowJ;
                                markedShips.add(toAdd);
                                if ((nowI - 1) >= 0) {
                                    if (userUser.get(nowI - 1).get(nowJ) != 3) {
                                        secondTable.get(nowI - 1).set(nowJ, 2);
                                    }
                                }
                                if ((nowJ + 1) <= 9) {
                                    if (userUser.get(nowI).get(nowJ + 1) != 3) {
                                        secondTable.get(nowI).set(nowJ + 1, 2);
                                    }
                                }
                                if ((nowI + 1) <= 9) {
                                    if (userUser.get(nowI + 1).get(nowJ) != 3) {
                                        secondTable.get(nowI + 1).set(nowJ, 2);
                                    }
                                }
                                if ((nowJ - 1) >= 0) {
                                    if (userUser.get(nowI).get(nowJ - 1) != 3) {
                                        secondTable.get(nowI).set(nowJ - 1, 2);
                                    }
                                }
                                if (((nowI - 1) >= 0) && ((nowJ - 1) >= 0)) {
                                    if (userUser.get(nowI - 1).get(nowJ - 1) != 3) {
                                        secondTable.get(nowI - 1).set(nowJ - 1, 2);
                                    }
                                }
                                if (((nowI + 1) <= 9) && ((nowJ - 1) >= 0)) {
                                    if (userUser.get(nowI + 1).get(nowJ - 1) != 3) {
                                        secondTable.get(nowI + 1).set(nowJ - 1, 2);
                                    }
                                }
                                if (((nowI + 1) <= 9) && ((nowJ + 1) <= 9)) {
                                    if (userUser.get(nowI + 1).get(nowJ + 1) != 3) {
                                        secondTable.get(nowI + 1).set(nowJ + 1, 2);
                                    }
                                }
                                if (((nowI - 1) >= 0) && ((nowJ + 1) <= 9)) {
                                    if (userUser.get(nowI - 1).get(nowJ + 1) != 3) {
                                        secondTable.get(nowI - 1).set(nowJ + 1, 2);
                                    }
                                }
                            }
                        }

                    }

                }
            }
        }

        int replyingSelfCircleNumber = 0;
        int callingSelfCircleNumber = 0;
        for (int i = 0; i < userUser.size(); i++) {
            for (int j = 0; j < userUser.get(i).size(); j++) {
                if (userUser.get(i).get(j) == 2) {
                    callingSelfCircleNumber++;
                }
                if (botBot.get(i).get(j) == 2) {
                    replyingSelfCircleNumber++;
                }
            }
        }
        String numbers = replyingSelfCircleNumber + " " + callingSelfCircleNumber;

        if (!flag)
            dataBase.update(name, userBot, userUser, botUser, botBot);

        return numbers;
    }

}
