package Bamboo.controller.MCTS;

public class UCB
{

    public static final double C = 1;
    public static double calculate(int wins, int plays, int n_p, int n_c)
    {
        assert plays != 0;
        double x_bar = wins/plays;
        return x_bar + (C * Math.sqrt(Math.log(n_p) / n_c));
    }
}
