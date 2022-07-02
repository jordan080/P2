import java.util.ArrayList;
import java.io.Console;
import java.io.IOException;
import java.util.Scanner;

class User
{
    private String name;
    private String password;
    private String nickname;

    private Profile profile;
    private Admin admin;
    private boolean onlyFriends = false;

    private ArrayList<User> invites = new ArrayList<User>();
    public ArrayList<User> friends = new ArrayList<User>();
    private ArrayList<Message> inbox = new ArrayList<Message>();
    public ArrayList<Community> userCommunites = new ArrayList<Community>();

    public User(String nickname, ArrayList<Community> userCommunites)
    {
        this(null, null, nickname, userCommunites);
    }

    public User(String name, String nickname)
    {
        this(name, null, nickname, null);
    }    

    public User(String name, String password, String nickname, ArrayList<Community> userCommunites)
    {
        this.name = name;
        this.password = password;
        this.nickname = nickname;
        this.userCommunites = userCommunites;
    }

    public String getNick()
    {
        return nickname;
    }

    public String getPass()
    {
        return password;
    }

    public void addMessage(Message message)
    {
        this.inbox.add(message);
    }

    public void getProfileInfo()
    {
        try
        {
            int numberFriends = friends.size();
            int numberComs = userCommunites.size();
            profile.getInfo(numberFriends, numberComs);           
        }
        catch (Exception e)
        {
            System.out.println("Profile is empty.");
        }
    }

    public void updateProfile()
    {
        if(this.profile != null)
        {
            this.profile.updateProfile();
        }
        else
        {
            this.profile = new Profile(this.name, this.nickname);
            this.profile.updateProfile();
            System.out.println("Profile created.");
        }
    }

    public void addRemoveFriend(ArrayList<User> users)
    {
        System.out.println("1. See invites\n2. Add a friend");
        Scanner userInput = new Scanner(System.in);
        Console console = System.console();

        int sub_option = userInput.nextInt();

        if (sub_option == 1)
        {
            this.checkFriendshipRequests();
        }
        else if (sub_option == 2)
        {
            String friendNick = console.readLine("Insert nickname: ");

            if(Utils.findUser(friendNick, users))
            {
                User invitedUser = Utils.getUser(friendNick, users);
                invitedUser.invites.add(this);
            }
            else
            {
                System.out.println("There is no user with given nickname.");
            }
        }
        else
        {
            System.out.println("Insert valid option.");
        }
    }

    public void checkFriendshipRequests()
    {
        System.out.println("You have " + invites.size() + " invites.");
        Console console = System.console();

        for(User sentInvite: invites)
        {
            System.out.println(sentInvite.nickname + " wants to be your friend.");
            String answer = console.readLine("Do you accept? ");

            if (answer.equals("Yes") || answer.equals("Y") || answer.equals("yes"))
            {
                this.friends.add(sentInvite);
                sentInvite.friends.add(this);
                System.out.println("Friend added.");
            }
            else if (answer.equals("No") || answer.equals("N") || answer.equals("no"))
            {
                System.out.println("Invite rejected.");
            }
        }
        this.invites.clear();
    }

    public void seeFeed(Feed feed)
    {
        try
        {
            feed.seeFeed(friends);
        }
        catch (Exception e)
        {
            System.out.println("The feed is empty.");
        }
    }

    public void seeInbox()
    {
        if (this.inbox.isEmpty())
        {
            System.out.println("Inbox is empty.");
        }
        else
        {
            for(Message message: inbox)
            {
                System.out.println(message.getAuthor() + ": " + message.getMessage()); 
            }
            this.inbox.clear();
        }
    }

    public void manageCommunity(ArrayList<Community> communities)
    {
        try
        {
            admin.addRemoveCommunityMembers(communities);
        }
        catch (Exception e)
        {
            System.out.println("You are not a community administrator.");
        }
    }

    public void joinCreateCommunity(ArrayList<Community> communities) throws IOException
    {
        System.out.println("1. Join a community\n2. Create a community");

        Console console = System.console();
        Scanner userInput = new Scanner(System.in);
        int sub_option = userInput.nextInt();

        if(sub_option == 1)
        {
            String communityName = console.readLine("Community name: ");
            Community com = Utils.getCommunity(communityName, communities);

            try
            {
                com.invites.add(this);
                System.out.println("Invite to join community sent.");
            }
            catch (Exception e)
            {
                System.out.println("Community not found.");
            }
        }
        else if (sub_option == 2)
        {
            String newCommunityName = console.readLine("New community name: ");
            Community checkCom = Utils.getCommunity(newCommunityName, communities);

            if(checkCom != null)
            {
                System.out.println("This group was already created.");
            }
            else
            {
                String newComDesc = console.readLine("New community description: ");
                this.admin = new Admin(this.nickname, userCommunites);
                Community newCom = new Community(newCommunityName, newComDesc, admin);

                newCom.members.add(this);
                userCommunites.add(newCom);
                newCom.communityInfo();
                communities.add(newCom);
            }
        }
        else
        {
            System.out.println("Insert valid option.");
        }
    }

    public void seeCommunityMessages(ArrayList<Community> communities)
    {
        Console console = System.console();
        String communityName = console.readLine("Type your community name: ");
        Community com = Utils.getCommunity(communityName, communities);

        try
        {
            for(Message message: com.inbox)
            {
                System.out.println(message.getAuthor() + ": " + message.getMessage()); 
            }
        }
        catch (Exception e)
        {
            System.out.println("Community not found.");
        }
    }
}