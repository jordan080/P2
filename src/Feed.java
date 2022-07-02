import java.util.ArrayList;
import java.io.Console;

public class Feed implements SendMessage
{
    private ArrayList<Message> feed = new ArrayList<Message>();

    public Feed(ArrayList<Message> feed)
    {
        this.feed = feed;
    }

    public boolean feedVisibility(boolean onlyFriends)
    {
        Console console = System.console();
        System.out.println("Your feed visibility is: " + onlyFriends);
        String option = console.readLine("Change feed visibility to only-friends (O) or whole feed (F): ");

        if(option.equals("O"))
        {
            onlyFriends = true;
            return true;
        }
        else
        {
            onlyFriends = false;
            return false;
        }
    }

    public void seeFeed(boolean onlyFriends, ArrayList<User> friends, String nickname)
    {
        boolean feedOpt = this.feedVisibility(onlyFriends);
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

    public void addMessage(Message message)
    {
        feed.add(message);
    }

    @Override
    public void sendMessage(String nickname) 
    {
        Console console = System.console();
        String text = console.readLine("Write your message: ");
        Message message = new Message(nickname, text);
        feed.add(message);
        System.out.println("Message sent to feed.");
    }
}
