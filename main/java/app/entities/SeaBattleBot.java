package app.entities;

import app.controller.DataBaseInputOutput;

import java.util.ArrayList;
import java.util.List;

public class SeaBattleBot {

    DataBaseInputOutput dataBase;
    List<List<Integer>> userBot;
    List<List<Integer>> userUser;
    List<List<Integer>> botUser;
    List<List<Integer>> botBot;
    String name;

    public SeaBattleBot(String name) {

        dataBase = new DataBaseInputOutput();
        this.name = name;
        userBot = dataBase.getUserBot(name);
        userUser = dataBase.getUserUser(name);
        botUser = dataBase.getBotUser(name);
        botBot = dataBase.getBotBot(name);

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

}
