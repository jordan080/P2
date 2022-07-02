import java.util.Scanner;
import java.util.ArrayList;

public class SendMessageCommand implements Command
{
    private String name;
    private String password;
    private String nickname;

    private ArrayList<User> users = new ArrayList<User>();
    private ArrayList<Community> communities = new ArrayList<Community>();
    private User userLogged = new User(name, nickname, password, communities);
    private Utils utils = new Utils(users, communities);
    private Feed feed = new Feed();

    public SendMessageCommand(User userLogged, Utils utils, Feed feed)
    {
        this.userLogged = userLogged;
        this.utils = utils;
        this.feed = feed;
    }

    @Override
    public void executeCommand() 
    {
        System.out.println("1. To a user\n2. To a community\n3. To the feed");
        Scanner userInput = new Scanner(System.in);
        int sub_option = userInput.nextInt();

        if (sub_option == 1)
        {
            utils.sendMessagetoUser(userLogged);
        }  
        else if (sub_option == 2)
        {
            utils.sendMessagetoCommunity(userLogged);
        }
        else if (sub_option == 3)
        {
            feed.sendMessagetoFeed(userLogged);
        }
        else
        {
            System.out.println("Try again.");
        }
    }
}
