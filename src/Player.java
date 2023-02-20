public class Player {
    private double[] strategy;
    private int[] regret;
    private int gamesPlayed;
    private int numActions;

    public Player(int possibleActions) {
        this.strategy = new double[possibleActions];
        this.gamesPlayed = 0;
        this.numActions = possibleActions;
        this.regret = new int[possibleActions];
    }
    public Player(int[] regret, int gamesPlayed) {
        this.regret = regret;
        this.gamesPlayed = gamesPlayed;
        this.numActions = regret.length;
        this.strategy = new double[numActions];
        calculateStrategy();
    }

    public int chooseStrategy() {
        double rand = Math.random();
        double sum = 0;
        for (int i = 0; i < this.strategy.length; ++i) {
            sum += this.strategy[i];
            if (sum >= rand) return i;
        }
        return  ((int)(Math.random() * ((this.numActions) + 1)));
    }

    public void setGameRegret(int[] regret) {
        for (int i = 0; i < numActions; i++) {
            this.regret[i] += regret[i];
        }
        this.gamesPlayed++;
        calculateStrategy();
    }

    public void printStrategy() {
        System.out.print('<');
        for (int i = 0; i < this.numActions; i++) {
            System.out.print(this.strategy[i]);
            if (i != this.numActions-1) System.out.print(",");
        }
        System.out.println('>');
    }

    private void calculateStrategy() {
        for (int i = 0; i < numActions; ++i) {
            this.strategy[i] = (this.regret[i] / (double) (this.gamesPlayed * this.numActions));
        }
    }


}
