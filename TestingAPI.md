# Testing API

## Instantiating tester
To instantiate the tester, there are two options:

You can either pass it an agent type and a grid size


    Tester tester = new Tester(AgentType.MCTS,4);

Or use the default constructor and assign these things later

    Tester tester = new Tester();

## Specifying Agents

In the testing API, you have agent 1 (red) and agent 2 (blue). When referring to these, use the TesterAgent enum.

    TesterAgent agent1 = TesterAgent.AGENT_1;
    TesterAgent agent2 = TesterAgent.AGENT_2;

By default, all (both) agents are of the Random class. If you want to set them to something else, use

    Tester tester = new Tester();
    tester.setAgent1(AgentType.MCTS);
    tester.setAgent2(AgentType.MiniMax);

This would let MCTS (red) play against MiniMax (blue). Unless you specify values for parameters, all available ones are set to the defaults that can be found in the classes themselves.

##Run Experiment

Once you have both agents set up, you can let them play against each other. To do so, specify how many games they should play, using the setReplications method.

    Tester tester = new Tester();
    tester.setAgent1(AgentType.MCTS);
    tester.setAgent2(AgentType.MiniMax);
    tester.setReplications(100);

This would let the tester run 100 games of MCTS vs MiniMax. To run:

    tester.run();

###Experiment settings

If you want to specify starting percentages, do so by using:

    tester.setRedStartingPercentage(0.9f);

This sets the starting percentage of the red player to whatever you pass it (here 90%).

##Saving Results

The tester will automatically save your results in a file named after Agent1's type.
You can change this by using

    tester.setFileName("MyFile.csv");

##Move Logging

If you want to log moves (for Nnet training data), activate logging using

    tester.setMoveLogging(true);

By default, the tester will save logged moves in a file called
"log.csv". If you want to change the name:

    tester.setLogFileName("MyLoggingFile.csv");

If you want to log moves for only one color (e.g. leave out random moves),
use

    tester.setLoggedColor(Color.BLUE);
    OR
    tester.setLoggedColor(Color.RED);

##Set up test variables
The tester can record win rates for as many variables as youd like to add.
To add one, you must first decide for what agent you want to use it. 
Get the right agent through the TesterAgent enum, then add the variable type you want to add.
You then have three options of choosing values for it:

    #1 constant
    tester.addVariable(TesterAgent.AGENT_1, Variable.C, 0.5f);
    
    #2 float array
    tester.addVariable(TesterAgent.AGENT_2, Variable.C, new float[]{0,2f,0.6f,0.76f});
    
    #3 using range specifiers (from "min" to "max" with step "step")
    tester.addVariable(TesterAgent.AGENT_1, Variable.C, 0.2f, 1.8f, 0.2f);

You can choose from the agent-specific variables
- search depth
- C
- Iterations
- Hidden layer size (not yet)
- Hidden layer count (not yet)

You can also vary the grid size. Since this does not require an agent, you can use

    tester.addVariable(Variable.GRID_SIZE,4);

Also, the three options from above can be used for the grid size.

##Results

Once all variables, agents and replications are set, the tester runs the experiment and outputs the results.
Results are in the form of

    V1, V2, ...,Vn,Result,
    x,y,...,z,win percentage,
    x,y,...,z,win percentage,

