package Bamboo.TestingAPI;

import Bamboo.controller.Agent;

public class TesterAgentFactory {
    public static Agent getAgentReference(Tester t, TesterAgent a){
        return switch(a){
            case AGENT_1 -> t.getAgent1();
            case AGENT_2 -> t.getAgent2();
        };
    }
}
