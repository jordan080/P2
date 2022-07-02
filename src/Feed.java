import java.util.ArrayList;
import java.io.Console;

public class Feed
{
    private ArrayList<Message> feed = new ArrayList<Message>();

    public Feed()
    {
        
    }

    public boolean feedVisibility()
    {
        Console console = System.console();
        boolean onlyFriends = true;
        String option = console.readLine("Change feed visibility to friends-only (O) or whole feed (F): ");

        if(option.equals("O"))
        {
            onlyFriends = true;
            System.out.println("Your feed visibility was set to friends-only");
        }
        else if (option.equals("F"))
        {
            onlyFriends = false;
            System.out.println("Your feed visibility was set to whole feed");
        }
        else
        {
            System.out.println("Try again.");
        }

        return onlyFriends;
    }

    public void seeFeed(ArrayList<User> friends)
    {
        boolean feedOpt = this.feedVisibility();
        if (feedOpt == true)
        {
            for(Message message: feed)
            {
                for(User friend: friends)
                {
                    if(message.getAuthor().equals(friend.getNick()))
                    {
                        System.out.println(message.getAuthor() + ": " + message.getMessage());
                    }
                }
            }
        }
        else
        {
            for(Message message: feed)
            {
                System.out.println(message.getAuthor() + ": " + message.getMessage());
            }
        }
    }

    public void sendMessagetoFeed(User userLogged) 
    {
        Console console = System.console();
        String text = console.readLine("Write your message: ");
        Message message = new Message(userLogged.getNick(), text);
        feed.add(message);
        System.out.println("Message sent to feed.");
    }
}
