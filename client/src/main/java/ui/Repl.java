package ui;

import ui.websocket.NotificationHandler;
import websocket.messages.ServerMessage;

import java.util.Scanner;

import static ui.EscapeSequences.*;

public class Repl implements NotificationHandler {
    private final ChessClient client;

    public Repl(String serverURL){
        client = new ChessClient(serverURL, this);
    }

    public void run(){
        System.out.println("Type 'Help' to get started ");
        System.out.println("Type a command: ");
        Scanner scanner = new Scanner(System.in);
        var result = "";
        while (!result.toLowerCase().equals("quit")){
            String line = scanner.nextLine();

            try {
                result = client.eval(line);
                System.out.println(BLUE + result);
            } catch (Throwable e) {
                var msg = e.toString();
                System.out.print(msg);
            }
        }
        System.out.println();
    }







    @Override
    public void notify(ServerMessage notification) {

    }
}
