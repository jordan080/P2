import java.util.ArrayList;
import java.io.Console;
import java.util.Scanner;

class User
{
    private String name;
    private String password;
    private String nickname;

    private Profile profile;
    private Admin admin;
    private boolean onlyFriends = false;

    public ArrayList<User> invites = new ArrayList<User>();
    public ArrayList<User> friends = new ArrayList<User>();
    public ArrayList<Message> inbox = new ArrayList<Message>();
    public ArrayList<Community> userCommunites = new ArrayList<Community>();
    public ArrayList<Message> feed = new ArrayList<Message>();

    public User(String nickname, ArrayList<Community> userCommunites)
    {
        this.nickname = nickname;
        this.userCommunites = userCommunites;
    }

    public User(String name, String nickname)
    {
        this.name = name;
        this.nickname = nickname;
    }    

    public User(String name, String password, String nickname)
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

    public static Community getCommunity(String comName, ArrayList<Community> communities)
    {
        Community searchedCom = null;

        for(Community community: communities)
        {
            if(community.comName().equals(comName))
            {
                searchedCom = community;
                return searchedCom;
            }
        }

        return searchedCom;
    }

    public void getInfo()
    {
        if(profile == null)
        {
            System.out.println("Profile is empty");
        }
        else
        {
            int numberFriends = friends.size();
            profile.getInfo(numberFriends);
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

    public void checkFriendshipRequests()
    {
        System.out.println("You have " + invites.size() + " invites.");
        Console console = System.console();

        for(User sentInvite: invites)
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

    public void seeFeed(ArrayList<Message> feed)
    {
        if (feed.isEmpty())
        {
            System.out.println("The feed is empty.");
        }
        else
        {
            boolean feedOpt = this.feedVisibility();
            if (feedOpt == true)
            {
                for(Message message: feed)
                {
                    for(User friend: this.friends)
                    {
                        if(message.author.getNick().equals(friend.nickname))
                        {
                            System.out.println(message.author.nickname + ": " + message.message + "\n");
                        }
                    }
                }
            }
            else
            {
                for(Message message: feed)
                {
                    System.out.println(message.author.nickname + ": " + message.message + "\n");
                }
            }
        }
    }

    public void sendMessagetoFeed(ArrayList<Message> feed)
    {
        //Message to feed
        Console console = System.console();
        String text = console.readLine("Write your message: ");
        Message message = new Message(this, text);
        feed.add(message);
        System.out.println("Message sent to feed");
    }

    public void getMessages()
    {
        for(Message message: inbox)
        {
            System.out.println(message.author.nickname + ": " + message.message); 
        }

        this.inbox.clear();
    }

    public void manageCommunity(ArrayList<Community> communities)
    {
        if (admin != null)
        {
            admin.addRemoveCommunityMembers(communities);
        }
        else
        {
            System.out.println("You are not a community administrator.");
        }
    }

    public void addRemoveCommunity(ArrayList<Community> communities)
    {
        System.out.println("1. Join a community\n2. Create a community");

        Console console = System.console();
        Scanner userInput = new Scanner(System.in);
        int sub_option = userInput.nextInt();

        if(sub_option == 1)
        {
            String communityName = console.readLine("Community name: ");
            Community com = getCommunity(communityName, communities);

            if(com != null)
            {
                com.invites.add(this);
                com.communityInfo();
            }
            else
            {
                System.out.println("Community not found");
            }
        }
        else if (sub_option == 2)
        {
            String newCommunityName = console.readLine("New community name: ");
            Community checkCom = getCommunity(newCommunityName, communities);

            if(checkCom != null)
            {
                System.out.println("This group was already created");
            }
            else
            {
                String newComDesc = console.readLine("New community description: ");
                this.admin = new Admin(this.nickname, userCommunites);
                Community newCom = new Community(newCommunityName, newComDesc, admin);

                newCom.members.add(this);
                newCom.communityInfo();
                communities.add(newCom);
            }
        }
        else
        {
            System.out.println("Insert valid option.");
        }
    }

    public void getComMessages(ArrayList<Community> communities)
    {
        Console console = System.console();
        String communityName = console.readLine("Type your community name: ");
        Community com = getCommunity(communityName, communities);

        if (com != null)
        {
            for(Message message: com.inbox)
            {
                System.out.println(message.author.nickname + ": " + message.message); 
            }
        }
        else
        {
            System.out.println("Community not found");
        }
    }
}