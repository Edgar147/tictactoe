import java.util.Scanner;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        char human = 'o';
        char ai = 'x';
        char[][] mytable = new char[3][3];

        int choice;
        for(int i = 0; i < mytable.length; ++i) {
            for(choice = 0; choice < mytable.length; ++choice) {
                mytable[i][choice] = '-';
            }
        }

        Tableau t = new Tableau(mytable);
        choice = 0;
        int turn = 0;
        boolean correctCase = false;
        boolean endGame = false;
        PlayerAI playerAI = new PlayerAI(t);

        do {
            Scanner scanner = new Scanner(System.in);
            if (turn == 0) {
                System.out.println("Please choose your case from 1 to 9, you are " + turn);
                choice = scanner.nextInt();
                correctCase = t.chooseCase(choice, 'o');
                if (correctCase) {
                    if (!t.checkWin('o') && !t.checkDraw('o')) {
                        turn = 1;
                    } else {
                        endGame = true;
                        turn = 0;
                    }
                }
            } else {
                playerAI.setT(t);
                int movei = playerAI.playMiniMax(t, 'x');
                correctCase = t.chooseCase(movei, 'x');
                if (correctCase) {
                    if (!t.checkWin('x') && !t.checkDraw('x')) {
                        turn = 0;
                    } else {
                        endGame = true;
                        turn = 1;
                    }
                }
            }

            t.ceateTable();
            if (t.checkLoose('o')) {
                System.out.println("The human has lost!!");
            } else if (t.checkLoose('x')) {
                System.out.println("The AI has lost!!");
            }
        } while(choice <= 9 && !endGame);

    }
}
