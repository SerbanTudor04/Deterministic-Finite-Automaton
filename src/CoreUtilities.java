
import types.State;
import java.io.FileNotFoundException;
import java.util.*;
import java.io.File;


public class CoreUtilities {
    public String states_file_location;
    public String transitions_file_location;

    public void readTheInputFileLocation(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("In order to validate the file we'll need some configuration files.");
        System.out.println("Please enter the states file location: ");
        this.states_file_location = scanner.nextLine();
        System.out.println("Please enter the transitions file location: ");
        this.transitions_file_location = scanner.nextLine();
        scanner.close();
    }
    public String readTheFile(String fileLocation) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(fileLocation));
        StringBuilder parsed_file;
        parsed_file = new StringBuilder();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            line=line+"%rodut.eu%";
            parsed_file.append(line);
        }


        scanner.close();
        String ouput_str=parsed_file.toString();
        ouput_str = ouput_str.replaceAll("(?<=\\b)%rodut.eu\\b", "");
        return ouput_str;
    }



    public Set<State> readStates(String parsed_file){
        Set<State> states = new HashSet<>();
        String[] lines = parsed_file.split("%rodut.eu%");

        for(String line : lines){
            String[] parts = line.split(",");
            State state = new State(parts[0]);
            states.add(state);
        }
        return states;
    }
    public Map<State, Map<Character, State>> readTransitions(String parsed_file){
        Map<State, Map<Character, State>> transitions = new HashMap<>();

        String[] lines = parsed_file.split("%rodut.eu%");
        for(String line : lines){
            String[] parts = line.split(",");
            State state = new State(parts[0]);
            Character input = parts[1].charAt(0);
            State nextState = new State(parts[2]);

            if (!transitions.containsKey(state)) {
                transitions.put(state, new HashMap<>());
            }

            transitions.get(state).put(input, nextState);
        }
        return transitions;
    }


}
