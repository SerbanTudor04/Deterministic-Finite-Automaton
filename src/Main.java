import types.State;

import java.util.Map;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        CoreUtilities appCoreUtilities = new CoreUtilities();
        try {
            appCoreUtilities.readTheInputFileLocation();
            String states_file_input= appCoreUtilities.readTheFile(appCoreUtilities.states_file_location);
            String transition_file_input= appCoreUtilities.readTheFile(appCoreUtilities.states_file_location);

            Set<State> app_states= appCoreUtilities.readStates(states_file_input);
            Map<State, Map<Character, State>> app_transitions= appCoreUtilities.readTransitions(transition_file_input);


            DFA dfa;
            dfa = new DFA(app_states, app_transitions);
            dfa.minimizeDFA();


        }catch (Exception e){
            System.out.println("An error occurred: "+e.toString());
        }

    }

}