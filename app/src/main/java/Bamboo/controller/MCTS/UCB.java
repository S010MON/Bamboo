package Bamboo.controller.MCTS;

public class UCB
{

    public static double C = 0.5;
    public static double calculate(int wins, int plays, int n_p, int n_c)
    {
        double x_bar;
        if(plays == 0)
            x_bar = 1;
        else
            x_bar = wins/plays;
        return x_bar + (C * Math.sqrt(Math.log(n_p) / n_c));
    }
}
