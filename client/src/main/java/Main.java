import java.util.Scanner;
import chess.*;
import ui.Repl;

public class Main {

    public static void main(String[] args) {
        var serverURL = "http://localhost:8080";
        System.out.println("♕ 240 Chess Client: \n");
        if (args.length == 1) {
            serverURL = args[0];
        }

        new Repl(serverURL).run();
    }
}
