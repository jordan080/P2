import java.util.ArrayList;
import java.io.Console;

class Account
{
    String name;
    String password;
    String nickname;
    Profile profile = null;
    boolean onlyFriends = false;

    ArrayList<Account> invites = new ArrayList<Account>();
    ArrayList<Account> friends = new ArrayList<Account>();
    ArrayList<Message> inbox = new ArrayList<Message>();
    ArrayList<Community> userCommunites = new ArrayList<Community>();
    public static ArrayList<Message> feed = new ArrayList<Message>();

    public Account(String name, String password, String nickname)
    {
        this.name = name;
        this.password = password;
        this.nickname = nickname;
    }

    public String getNick()
    {
        return nickname;
    }

    public String getPass()
    {
        return password;
    }

    public void getInfo()
    {
        if(profile == null)
        {
            System.out.println("Profile is empty");
        }
        else
        {
            System.out.println("Name: " + this.name + "\n"
            + "Friends: " + friends.size() + "\n"
            + "City: " + this.profile.city + "\n"
            + "Birthday: " + this.profile.birthday + "\n");
        }
    }

    public boolean feedVisibility()
    {
        Console console = System.console();
        System.out.println("Your feed visibility is: " + this.onlyFriends);
        String option = console.readLine("Change feed vilibility to only-friends (O) or whole feed (F): ");

        if(option.equals("O"))
        {
            this.onlyFriends = true;
            return true;
        }
        else
        {
            this.onlyFriends = false;
            return false;
        }
    }

    public void checkFriendshipRequests()
    {
        System.out.println("You have " + invites.size() + " invites.");
        Console console = System.console();

        for(Account sentInvite: invites)
        {
            System.out.println(sentInvite.nickname + " wants to be your friend.");
            String answer = console.readLine("Do you accept? ");
            System.out.println(answer);

            if (answer.equals("Yes") || answer.equals("Y") || answer.equals("yes"))
            {
                this.friends.add(sentInvite);
                //this.invites.remove(sentInvite);
                System.out.println("Friend added");
            }
            else if (answer.equals("No") || answer.equals("N") || answer.equals("no"))
            {
                //this.invites.remove(sentInvite);
                System.out.println("Invite rejected");
            }
        }
        this.invites.clear();
    }
}