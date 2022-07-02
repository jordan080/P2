import java.util.ArrayList;
import java.io.Console;

class Utils 
{
    private ArrayList<User> users = new ArrayList<User>();
    private ArrayList<Community> communities = new ArrayList<Community>();

    public Utils(ArrayList<User> users, ArrayList<Community> communities)
    {
        this.users = users;
        this.communities = communities;
    }

    public static Community getCommunity(String comName, ArrayList<Community> communities)
    {
        Community searchedCom = null;

        for(Community community: communities)
        {
            if(community.getCommunityName().equals(comName))
            {
                searchedCom = community;
                return searchedCom;
            }
        }

        return searchedCom;
    }

    public static boolean findUser(String nickname, ArrayList<User> users)
    {
        for (User user: users)
        {
            if (user.getNick().equals(nickname))
            {
                return true;
            }
        }
        return false;
    }

    public static User getUser(String nickname, ArrayList<User> users)
    {
        User searchedUser = null;
        {
            for (User user: users)
            {
                if (user.getNick().equals(nickname))
                {
                    searchedUser = user;
                    return searchedUser;
                }
            }
    
            return searchedUser;
        } 
    }

    public void sendMessagetoUser(User userLogged)
    {
        Console console = System.console();

        String text = console.readLine("Write your message: ");
        String nickname = userLogged.getNick();
        Message message = new Message(nickname, text);
        String destination = console.readLine("Insert the nickname of the user you want to send a message: ");

        if(Utils.findUser(destination, users))
        {
            User destinyUser = Utils.getUser(destination, users);
            destinyUser.addMessage(message);
            System.out.println("Message sent");
        }
        else
        {
            System.out.println("User not found.");
        }
    }

    public void sendMessagetoCommunity(User userLogged)
    {
        Console console = System.console();

        String text = console.readLine("Write your message: ");
        String nickname = userLogged.getNick();
        Message message = new Message(nickname, text);

        String destination = console.readLine("Insert the name of the community you want to send a message: ");
        Community com = Utils.getCommunity(destination, communities);

        try
        {
            String comName = com.getCommunityName();
            com.inbox.add(message);
            System.out.println("Message sent to community " + comName);
        }
        catch (Exception e)
        {
            System.out.println("Community not found.");
        }
    }
}
