import java.util.Stack;

public class Tableau {
    private char[][] charArray;
    private boolean gameIsOver = false;
    private char winner = 'n';


    Tableau(char[][] _charArray) {
        this.charArray = _charArray;
        this.ceateTable();
    }

    public void ceateTable() {
        for(int i = 0; i < this.charArray.length; ++i) {
            for(int j = 0; j < this.charArray.length; ++j) {
                if (j != 2 && j != 5) {
                    System.out.print(this.charArray[i][j] + "|");
                } else {
                    System.out.println(this.charArray[i][j] + "|");
                }
            }
        }
        System.out.println("--------");

    }

    public boolean chooseCase(int number, char player) {
        int c = 1;

        for(int i = 0; i < this.charArray.length; ++i) {
            for(int j = 0; j < this.charArray.length; ++j) {
                if (c == number && this.charArray[i][j] == '-') {
                    this.charArray[i][j] = player;
                    return true;
                }

                c++;
            }
        }

        return false;
    }

    public void resetCase(int number) {
        int c = 1;

        for(int i = 0; i < this.charArray.length; ++i) {
            for(int j = 0; j < this.charArray.length; ++j) {
                if (c == number) {
                    this.charArray[i][j] = '-';
                }

                c++;
            }
        }

    }

    public boolean checkWin(char player) {
        if ((this.charArray[0][0] != player || this.charArray[0][1] != player || this.charArray[0][2] != player) && (this.charArray[1][0] != player || this.charArray[1][1] != player || this.charArray[1][2] != player) && (this.charArray[2][0] != player || this.charArray[2][1] != player || this.charArray[2][2] != player) && (this.charArray[0][0] != player || this.charArray[1][0] != player || this.charArray[2][0] != player) && (this.charArray[0][1] != player || this.charArray[1][1] != player || this.charArray[2][1] != player) && (this.charArray[0][2] != player || this.charArray[1][2] != player || this.charArray[2][2] != player) && (this.charArray[0][0] != player || this.charArray[1][1] != player || this.charArray[2][2] != player) && (this.charArray[0][2] != player || this.charArray[1][1] != player || this.charArray[2][0] != player)) {
            return false;
        } else {
            System.out.println("Dear " + player + " you won!");
            this.gameIsOver = true;
            this.winner = player;
            return true;
        }
    }

    public boolean isFull() {
        for(int i = 0; i < this.charArray.length; ++i) {
            for(int j = 0; j < this.charArray.length; ++j) {
                if (this.charArray[i][j] == '-') {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean checkDraw(char player) {
        if (!this.checkWin(player) && this.isFull()) {
            System.out.println("It's a draw!!!");
            this.winner = 'd';
            return true;
        } else {
            return false;
        }
    }

    public boolean checkLoose(char player) {
        return player == 'x' ? this.checkWin('o') : false;
    }

    public boolean isGameIsOver() {
        return this.gameIsOver;
    }

    public void setGameIsOver(boolean gameIsOver) {
        this.gameIsOver = gameIsOver;
    }

    public char getWinner() {
        return this.winner;
    }

    public void setWinner(char winner) {
        this.winner = winner;
    }




    public int countFreeCase() {
        int c = 0;

        for (int i = 0; i < this.charArray.length; ++i) {
            for (int j = 0; j < this.charArray.length; ++j) {
                if (charArray[i][j] == '-') {
                    c++;
                }

            }
        }
    return  c+1;
    }

    public boolean isFree(int number) {
        int c = 1;

        for(int i = 0; i < this.charArray.length; ++i) {
            for(int j = 0; j < this.charArray.length; ++j) {
                if (c == number && this.charArray[i][j] == '-') {
                    return true;
                }

                c++;
            }
        }

        return false;
    }





    public char getCase(int number){
        int c = 1;
        char result=0;
        for(int i = 0; i < this.charArray.length; ++i) {
            for(int j = 0; j < this.charArray.length; ++j) {
                if (c == number ) {
                    result = charArray[i][j];
                }

                c++;
            }
        }
        return result;
    }

    public void insertCase(int number, char player) {
        int c = 1;

        for(int i = 0; i < this.charArray.length; ++i) {
            for(int j = 0; j < this.charArray.length; ++j) {
                if (c == number && this.charArray[i][j] == '-') {
                    this.charArray[i][j] = player;
                }

                c++;
            }
        }

    }

}
