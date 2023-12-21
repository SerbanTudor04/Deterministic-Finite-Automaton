package types;

import java.util.HashMap;
import java.util.Map;

public class State {
    private final String name;
    private final Map<Character, State> transitions;

    public State(String name) {
        this.name = name;
        this.transitions = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public Map<Character, State> getTransitions() {
        return transitions;
    }

    public void addTransition(Character input, State nextState) {
        transitions.put(input, nextState);
    }

    public State transition(Character input) {
        return transitions.get(input);
    }
}
