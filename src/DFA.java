import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Map;

import types.State;

public class DFA {
    private final Set<State> states;
    private Map<State, Map<Character, State>> transitions;

    public DFA(Set<State> states, Map<State, Map<Character, State>> transitions) {
        this.states = states;
        this.transitions = transitions;
    }

    public boolean accepts(String str) {
        State currentState = states.iterator().next();
        for (char c : str.toCharArray()) {
            currentState = transitions.get(currentState).get(c);
            if (currentState == null) {
                return false;
            }
        }
        return currentState == states.iterator().next() && !str.isEmpty();
    }

    public State transition(State currentState, char c) {
        return transitions.get(currentState).get(c);
    }
    public void minimizeDFA() {
        // Find the ε-closure of each state
        Set<Set<State>> eClosures = new HashSet<>();
        for (State state : states) {
            Set<State> eClosure = new HashSet<>();
            eClosure.add(state);

            // Add all states that can be reached from the current state by following ε-transitions
            Queue<State> q = new LinkedList<>();
            q.add(state);

            while (!q.isEmpty()) {
                State currentState = q.poll();

                for (State nextState : transitions.get(currentState).values()) {
                    if (!eClosure.contains(nextState)) {
                        eClosure.add(nextState);
                        q.add(nextState);
                    }
                }
            }

            eClosures.add(eClosure);
        }

        // Update the transitions of the DFA
        Map<State, Map<Character, State>> newTransitions = new HashMap<>();
        for (State state : states) {
            Map<Character, State> newStateTransitions = new HashMap<>();

            for (Character input : transitions.get(state).keySet()) {
                State nextState = null;
                for (Set<State> closure : eClosures) {
                    if (closure.contains(transitions.get(state).get(input))) {
                        nextState = (State) closure;
                        break;
                    }
                }
                newStateTransitions.put(input, nextState);
            }

            newTransitions.put(state, newStateTransitions);
        }

        this.transitions = newTransitions;
    }
}