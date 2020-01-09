package app.entities;

import app.controller.DataBaseInputOutput;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class GameSession {

    String callingUserName;
    String replyingUserName;
    DataBaseInputOutput dataBase;
    List<List<Integer>> callingSelf;
    List<List<Integer>> callingEnemy;
    List<List<Integer>> replyingSelf;
    List<List<Integer>> replyingEnemy;

    public GameSession(String callingUserName, String replyingUserName) {

        this.dataBase = new DataBaseInputOutput();
        this.callingUserName = callingUserName;
        this.replyingUserName = replyingUserName;

    }

    public void callAUser () {

        this.dataBase.usersBattleInitiate(this.callingUserName, this.replyingUserName);
        //this.dataBase.closeUsersBattle(this.callingUserName, this.replyingUserName);
        while (true) {
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("loop in calling");
            if (dataBase.isUsersBattleInitialized(this.callingUserName, this.replyingUserName)) {
                break;
            }
        }

    }

    public int isWin() {

        callingSelf = dataBase.getCallingSelf(this.callingUserName, this.replyingUserName);
        callingEnemy = dataBase.getCallingEnemy(this.callingUserName, this.replyingUserName);
        replyingSelf = dataBase.getReplyingSelf(this.callingUserName, this.replyingUserName);
        replyingEnemy = dataBase.getReplyingEnemy(this.callingUserName, this.replyingUserName);

        int callingSelfSum = 0;
        int replyingSelfSum = 0;
        for (int i = 0; i < callingSelf.size(); i++) {
            for (int j = 0; j < callingSelf.get(i).size(); j++) {
                if (callingSelf.get(i).get(j) == 1) {
                    callingSelfSum++;
                }
                if (replyingSelf.get(i).get(j) == 1) {
                    replyingSelfSum++;
                }
            }
        }
        System.out.println(callingSelfSum + "versus" + replyingSelfSum);
        if (callingSelfSum == 0) {
            return -5;
        }
        if (replyingSelfSum == 0) {
            return 5;
        }
        return 0;

    }

    public void stepDraw(int x , int y, String gameRole) {

        callingSelf = dataBase.getCallingSelf(this.callingUserName, this.replyingUserName);
        callingEnemy = dataBase.getCallingEnemy(this.callingUserName, this.replyingUserName);
        replyingSelf = dataBase.getReplyingSelf(this.callingUserName, this.replyingUserName);
        replyingEnemy = dataBase.getReplyingEnemy(this.callingUserName, this.replyingUserName);

        boolean change = false;

        if (gameRole.equals("calling")) {
            if (replyingSelf.get(x).get(y) == 1) {
                replyingSelf.get(x).set(y, 3);
                callingEnemy.get(x).set(y, 3);
            }

            if (replyingSelf.get(x).get(y) == 0) {
                replyingSelf.get(x).set(y, 2);
                callingEnemy.get(x).set(y, 2);
                change = true;
            }
        }
        else {
            if (callingSelf.get(x).get(y) == 1) {
                callingSelf.get(x).set(y, 3);
                replyingEnemy.get(x).set(y, 3);
            }

            if (callingSelf.get(x).get(y) == 0) {
                callingSelf.get(x).set(y, 2);
                replyingEnemy.get(x).set(y, 2);
                change = true;
            }
        }

        dataBase.updateUsersBattle(this.callingUserName, this.replyingUserName, callingEnemy, callingSelf, replyingEnemy, replyingSelf);
        this.finishedShips();

        int value = this.isWin();
        if (value != 0) {
            dataBase.changeFlag(this.callingUserName, this.replyingUserName, gameRole, 9);
            if (value == 5)
                dataBase.setWinner(this.callingUserName, this.replyingUserName, 5);
            if (value == -5)
                dataBase.setWinner(this.callingUserName, this.replyingUserName, -5);
        }
        else if (change) {
            dataBase.changeFlag(this.callingUserName, this.replyingUserName, gameRole, 0);
        }

    }

    public void finishedShips() {

        callingSelf = dataBase.getCallingSelf(this.callingUserName, this.replyingUserName);
        callingEnemy = dataBase.getCallingEnemy(this.callingUserName, this.replyingUserName);
        replyingSelf = dataBase.getReplyingSelf(this.callingUserName, this.replyingUserName);
        replyingEnemy = dataBase.getReplyingEnemy(this.callingUserName, this.replyingUserName);
        List<List<List<Integer>>> allTables = new ArrayList<>();
        List<List<List<Integer>>> secondTables = new ArrayList<>();
        allTables.add(callingEnemy);
        allTables.add(replyingSelf);
        secondTables.add(replyingEnemy);
        secondTables.add(callingSelf);

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
                                while (replyingSelf.get(i + counter).get(j) == 3) {
                                    String coords = (i + counter) + " " + j;
                                    tmpShips.add(coords);
                                    counter--;
                                    if (j + counter < 0)
                                        break;
                                }
                            }
                        }
                        if ((j + 1) <= 9) {
                            if (replyingSelf.get(i).get(j + 1) == 3) {
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
                            if (replyingSelf.get(i + 1).get(j) == 3) {
                                int counter = 0;
                                while (allTable.get(i + counter).get(j) == 3) {
                                    String coords = (i + counter) + " " + j;
                                    tmpShips.add(coords);
                                    counter++;
                                    if (j + counter > 9)
                                        break;
                                }
                            }
                        }
                        if ((j - 1) >= 0) {
                            if (replyingSelf.get(i).get(j - 1) == 3) {
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
                                if (replyingSelf.get(nowI - 1).get(nowJ) == 1)
                                    flag = false;
                            }
                            if ((nowJ + 1) <= 9) {
                                if (replyingSelf.get(nowI).get(nowJ + 1) == 1)
                                    flag = false;
                            }
                            if ((nowI + 1) <= 9) {
                                if (replyingSelf.get(nowI + 1).get(nowJ) == 1)
                                    flag = false;
                            }
                            if ((nowJ - 1) >= 0) {
                                if (replyingSelf.get(nowI).get(nowJ - 1) == 1)
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
                                    if (replyingSelf.get(nowI - 1).get(nowJ) != 3) {
                                        allTable.get(nowI - 1).set(nowJ, 2);
                                    }
                                }
                                if ((nowJ + 1) <= 9) {
                                    if (replyingSelf.get(nowI).get(nowJ + 1) != 3) {
                                        allTable.get(nowI).set(nowJ + 1, 2);
                                    }
                                }
                                if ((nowI + 1) <= 9) {
                                    if (replyingSelf.get(nowI + 1).get(nowJ) != 3) {
                                        allTable.get(nowI + 1).set(nowJ, 2);
                                    }
                                }
                                if ((nowJ - 1) >= 0) {
                                    if (replyingSelf.get(nowI).get(nowJ - 1) != 3) {
                                        allTable.get(nowI).set(nowJ - 1, 2);
                                    }
                                }
                                if (((nowI - 1) >= 0) && ((nowJ - 1) >= 0)) {
                                    if (replyingSelf.get(nowI - 1).get(nowJ - 1) != 3) {
                                        allTable.get(nowI - 1).set(nowJ - 1, 2);
                                    }
                                }
                                if (((nowI + 1) <= 9) && ((nowJ - 1) >= 0)) {
                                    if (replyingSelf.get(nowI + 1).get(nowJ - 1) != 3) {
                                        allTable.get(nowI + 1).set(nowJ - 1, 2);
                                    }
                                }
                                if (((nowI + 1) <= 9) && ((nowJ + 1) <= 9)) {
                                    if (replyingSelf.get(nowI + 1).get(nowJ + 1) != 3) {
                                        allTable.get(nowI + 1).set(nowJ + 1, 2);
                                    }
                                }
                                if (((nowI - 1) >= 0) && ((nowJ + 1) <= 9)) {
                                    if (replyingSelf.get(nowI - 1).get(nowJ + 1) != 3) {
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
                                while (callingSelf.get(i + counter).get(j) == 3) {
                                    String coords = (i + counter) + " " + j;
                                    tmpShips.add(coords);
                                    counter--;
                                }
                            }
                        }
                        if ((j + 1) <= 9) {
                            if (callingSelf.get(i).get(j + 1) == 3) {
                                int counter = 0;
                                while (secondTable.get(i).get(j + counter) == 3) {
                                    String coords = i + " " + (j + counter);
                                    tmpShips.add(coords);
                                    counter++;
                                }
                            }
                        }
                        if ((i + 1) <= 9) {
                            if (callingSelf.get(i + 1).get(j) == 3) {
                                int counter = 0;
                                while (secondTable.get(i + counter).get(j) == 3) {
                                    String coords = (i + counter) + " " + j;
                                    tmpShips.add(coords);
                                    counter++;
                                }
                            }
                        }
                        if ((j - 1) >= 0) {
                            if (callingSelf.get(i).get(j - 1) == 3) {
                                int counter = 0;
                                while (secondTable.get(i).get(j + counter) == 3) {
                                    String coords = i + " " + (j + counter);
                                    tmpShips.add(coords);
                                    counter--;
                                }
                            }
                        }

                        boolean flag = true;
                        for (String tmpShip : tmpShips) {
                            int nowI = Integer.parseInt(tmpShip.split(" ")[0]);
                            int nowJ = Integer.parseInt(tmpShip.split(" ")[1]);
                            if ((nowI - 1) >= 0) {
                                if (callingSelf.get(nowI - 1).get(nowJ) == 1)
                                    flag = false;
                            }
                            if ((nowJ + 1) <= 9) {
                                if (callingSelf.get(nowI).get(nowJ + 1) == 1)
                                    flag = false;
                            }
                            if ((nowI + 1) <= 9) {
                                if (callingSelf.get(nowI + 1).get(nowJ) == 1)
                                    flag = false;
                            }
                            if ((nowJ - 1) >= 0) {
                                if (callingSelf.get(nowI).get(nowJ - 1) == 1)
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
                                    if (callingSelf.get(nowI - 1).get(nowJ) != 3) {
                                        secondTable.get(nowI - 1).set(nowJ, 2);
                                    }
                                }
                                if ((nowJ + 1) <= 9) {
                                    if (callingSelf.get(nowI).get(nowJ + 1) != 3) {
                                        secondTable.get(nowI).set(nowJ + 1, 2);
                                    }
                                }
                                if ((nowI + 1) <= 9) {
                                    if (callingSelf.get(nowI + 1).get(nowJ) != 3) {
                                        secondTable.get(nowI + 1).set(nowJ, 2);
                                    }
                                }
                                if ((nowJ - 1) >= 0) {
                                    if (callingSelf.get(nowI).get(nowJ - 1) != 3) {
                                        secondTable.get(nowI).set(nowJ - 1, 2);
                                    }
                                }
                                if (((nowI - 1) >= 0) && ((nowJ - 1) >= 0)) {
                                    if (callingSelf.get(nowI - 1).get(nowJ - 1) != 3) {
                                        secondTable.get(nowI - 1).set(nowJ - 1, 2);
                                    }
                                }
                                if (((nowI + 1) <= 9) && ((nowJ - 1) >= 0)) {
                                    if (callingSelf.get(nowI + 1).get(nowJ - 1) != 3) {
                                        secondTable.get(nowI + 1).set(nowJ - 1, 2);
                                    }
                                }
                                if (((nowI + 1) <= 9) && ((nowJ + 1) <= 9)) {
                                    if (callingSelf.get(nowI + 1).get(nowJ + 1) != 3) {
                                        secondTable.get(nowI + 1).set(nowJ + 1, 2);
                                    }
                                }
                                if (((nowI - 1) >= 0) && ((nowJ + 1) <= 9)) {
                                    if (callingSelf.get(nowI - 1).get(nowJ + 1) != 3) {
                                        secondTable.get(nowI - 1).set(nowJ + 1, 2);
                                    }
                                }
                            }
                        }

                    }

                }
            }
        }

        dataBase.updateUsersBattle(this.callingUserName, this.replyingUserName, allTables.get(0), secondTables.get(1), secondTables.get(0), allTables.get(1));

    }

}
