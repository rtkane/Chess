package ui;

import ui.websocket.NotificationHandler;

import javax.management.Notification;
import java.util.Scanner;

public class Repl implements NotificationHandler {
    private final ChessClient client;

    public Repl(String serverURL){
        client = new ChessClient(serverURL, this);
    }

    public void run(){
        System.out.println("Type 'Help' to get started: ");
        Scanner scanner = new Scanner(System.in);
        var result = "";
        while (!result.toLowerCase().equals("quit")){
            String line = scanner.nextLine();
            if (line.toLowerCase().equals("help")){
                help();
            }
        }
    }


    public void help(){
        System.out.println("""
                    - Register <Username> <Password> <Email>
                    - Login <Username> <Password>
                    - Help  - All commands
                    - Quit - quit playing chess
                    """);
    }


    @Override
    public void notify(webSocketMessages.Notification notification) {

    }
}
