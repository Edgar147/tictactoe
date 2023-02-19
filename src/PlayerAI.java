import java.util.HashMap;
import java.util.Map;
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
        int score = evaluate(t);

        if (score != 0){
            return score;
        }

        if (t.isFull()){
            return 0;
        }
        // Check if the current state is a terminal state


        // If the game is not over, search the game tree
        if (maximizingPlayer == 'x') {
            int bestValue = Integer.MIN_VALUE;

            for (int i = 1; i <= 9; i++) {
                if (t.isFree(i)) {
                    t.insertCase(i, 'x');
                    int value = playMiniMax(t, 'o');
                    bestValue = Math.max(bestValue, value);
                    t.resetCase(i);
                }
            }
            return bestValue;
        } else {
            int bestValue = Integer.MAX_VALUE;

            for (int i = 1; i <= 9; i++) {
                if (t.isFree(i)) {
                    t.insertCase(i, 'o');
                    int value = playMiniMax(t, 'x');
                    bestValue = Math.min(bestValue, value);
                    t.resetCase(i);
                }
            }
            return bestValue;
        }
    }




    public int evaluate(Tableau t) {

        // Check rows
        for (int i = 1; i <= 9; i += 3) {
            if (t.getCase(i) == t.getCase(i+1) && t.getCase(i+1) == t.getCase(i+2)) {
                if (t.getCase(i) == 'x') {


                    return  10;
                } else if (t.getCase(i) == 'o') {

                    return -10;
                }
            }
        }

        // Check columns
        for (int i = 1; i <= 3; i++) {
            if (t.getCase(i) == t.getCase(i+3) && t.getCase(i+3) == t.getCase(i+6)) {
                if (t.getCase(i) == 'x') {
                    return + 10;
                } else if (t.getCase(i) == 'o') {
                    return - 10;
                }
            }
        }

        // Check diagonals
        if (t.getCase(1) == t.getCase(5) && t.getCase(5) == t.getCase(9)) {
            if (t.getCase(1) == 'x') {

                return 10;
            } else if (t.getCase(1) == 'o') {
                return - 10;
            }
        }

        if (t.getCase(3) == t.getCase(5) && t.getCase(5) == t.getCase(7)) {
            if (t.getCase(3) == 'x') {
                return  10;
            } else if (t.getCase(3) == 'o') {
                return - 10;
            }
        }

        return 0;
    }


    public int getTheBestPosition(Tableau t){
        Map<Integer, Integer> dataMap = new HashMap<>();


        int helper=0;
        for (int i=1;i<=9;i++){
            if(t.isFree(i)){
                helper=this.playMiniMax(t,'x');
                dataMap.put(i, helper);
            }
        }
        for (Map.Entry<Integer, Integer> entry : dataMap.entrySet()) {
            System.out.println("Key: " + entry.getKey() + " Value: " + entry.getValue());
        }
        return  getKeyWithMaxValue(dataMap);
    }

    public static <K, V extends Comparable<V>> K getKeyWithMaxValue(Map<K, V> map) {
        Map.Entry<K, V> maxEntry = null;
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
                maxEntry = entry;
            }
        }
        return maxEntry.getKey();
    }


    public int findBestMove(Tableau t) {
        int bestVal = Integer.MIN_VALUE;
        int bestMove = -1;

        // Traverse all cells, evaluate minimax function
        // for all empty cells. And return the cell
        // with optimal value.
        for (int i = 1; i <= 9; i++) {
            if (t.isFree(i)) {
                // Make the move
                //t.chooseCase(i, 'x');
                int moveVal = playMiniMax(t, 'o');
                //t.resetCase(i);


                // If the value of the current move is
                // more than the best value, then update
                // best/
                if (moveVal > bestVal) {
                    bestMove = i;
                    bestVal = moveVal;
                }
            }

        }

        System.out.printf("The value of the best Move " +
                "is : %d\n\n", bestVal);
        return bestMove;
    }




















    public int getBestMove(Tableau t, char maximizingPlayer) {
        int bestMove = -1;
        int bestScore = maximizingPlayer == 'x' ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (int i = 1; i <= 9; i++) {
            if (t.isFree(i)) {
                t.insertCase(i, maximizingPlayer);
                int score = playMiniMax(t, maximizingPlayer == 'x' ? 'o' : 'x');
                t.resetCase(i);

                if (maximizingPlayer == 'x' && score > bestScore) {
                    bestMove = i;
                    bestScore = score;
                } else if (maximizingPlayer == 'o' && score < bestScore) {
                    bestMove = i;
                    bestScore = score;
                }
            }
        }

        return bestMove;
    }





}




















