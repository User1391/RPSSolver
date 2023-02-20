public class RPSSolver {
    // 0 = rock, 1 = paper, 2 = scissors
    public static int evaluateGame(int p1Strat, int p2Strat) {
        if (p1Strat == p2Strat) return 0;
        else if ((p1Strat + 1) % 3 == p2Strat) return -1;
        else return 1;
    }
    public static int[] findStrategyRegret(int opponentStrategy, int numStrategies) {
        int[] regretArray = new int[numStrategies];
        for (int i = 0; i < numStrategies; i++) {
            regretArray[i] = 1 - evaluateGame(i, opponentStrategy);
        }
        return regretArray;
    }

    public static void main(String[] args) {
        Player p1 = new Player(3);
        Player p2 = new Player(3);

        int p1Plays;
        int p2Plays;
        int evalGame;
        int p1Wins = 0;
        int p2Wins = 0;
        int ties = 0;
        for (int i = 0; i < 7000000; i++) {
            p1Plays = p1.chooseStrategy();
            p2Plays = p2.chooseStrategy();
            evalGame = evaluateGame(p1Plays, p2Plays);
            if (evalGame == 1) p1Wins++;
            else if (evalGame == 0) ties++;
            else p2Wins++;

            p1.setGameRegret(findStrategyRegret(p2Plays, 3));
            p2.setGameRegret(findStrategyRegret(p1Plays, 3));
        }
        p1.printStrategy();
        p2.printStrategy();
        System.out.println("P1 Wins: " + p1Wins + " Ties: " + ties + " P2 Wins: " + p2Wins);


    }
}
