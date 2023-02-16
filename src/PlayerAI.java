import java.util.Random;

public class PlayerAI {
    Tableau t;

    PlayerAI(Tableau _t) {
        this.t = _t;
    }

    public void playRandom() {
        Random rand = new Random();
        int randomNum = rand.nextInt(9) + 1;

        for(boolean inserted = false; !inserted; inserted = this.t.chooseCase(randomNum, 'x')) {
        }

    }

    public int playMiniMax(Tableau t, char maximizingPlayer) {
        int bestMove = 0;
        if (t.getWinner() == 'x') {
            return 1;
        } else if (t.getWinner() == 'd') {
            return 0;
        } else if (t.getWinner() == 'o') {
            return -1;
        } else {
            int bestValue;
            int i;
            if (maximizingPlayer == 'x') {
                bestValue = Integer.MIN_VALUE;

                for(i = 1; i <= 9; ++i) {
                    if (t.chooseCase(i, 'x')) {
                        bestValue = Math.max(bestValue, this.playMiniMax(t, 'o'));
                        bestMove = i;
                        t.resetCase(i);
                    }
                }
            } else {
                bestValue = Integer.MAX_VALUE;

                for(i = 1; i <= 9; ++i) {
                    if (t.chooseCase(i, 'o')) {
                        bestValue = Math.min(bestValue, this.playMiniMax(t, 'x'));
                        bestMove = i;
                        t.resetCase(i);
                    }
                }
            }

            return bestMove;
        }
    }

    public void setT(Tableau t) {
        this.t = t;
    }
}
