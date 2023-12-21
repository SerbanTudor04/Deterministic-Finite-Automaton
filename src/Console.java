import java.util.Scanner;

public class Console {
    DFA dfa;
    public Console(DFA dfa){
        this.dfa=dfa;
    }
    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    private void printTradeMark(){
        print("*-----------------------------------------------*");
        print("            Welcome to DFA Project!              ");
        print(" -> For the available command type: CMD help     ");
        print("*-----------------------------------------------*");
    }

    public static void print(String ln){
        System.out.println(ln);
    }

    public void open(){
        clearScreen();
        printTradeMark();
        Scanner scanner = new Scanner(System.in);
        String line;
        while(true){
            print("==> ");
            line = scanner.nextLine();
            if(line.contains("exit") || line.contains("EXIT")) {
                print("Exiting console.");
                break;
            }
            if(line.contains("CMD") || line.contains("cmd")){
                int index=line.indexOf("CMD");
                if(index!=0){
                    print("Invalid command inputted. 'CMD' should be the first, ex: ==> cmd help  ");
                    continue;
                }
                String substr=line.substring(4);
                handleCMD(substr);
                continue;
            }
            print("Invalid option.");



        }
    }

    private static void printHelp(){
        print("Available commands:");
        print("-> cmd");
        print("\t -> help");
        print("\t -> check <string>");
        print("-> exit");
    }

    private  void  handleCMD(String restOfLine){
        if((restOfLine.contains("help")|| restOfLine.contains("HELP"))&& (restOfLine.indexOf("help") == 0)){
            printHelp();
            return;
        }
        if((restOfLine.contains("check")|| restOfLine.contains("CHECK"))&& (restOfLine.indexOf("check") == 0)){
            String substr=restOfLine.substring(5);
            substr=substr.stripLeading();
            this.dfa.accepts(substr);
            return;
        }

        print("Invalid command.");

    }

}
