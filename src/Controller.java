import java.util.ArrayList;

class Controller 
{
    private String name;
    private String password;
    private String nickname;
    private ArrayList<User> users = new ArrayList<User>();
    private ArrayList<Community> communities = new ArrayList<Community>();
    private User userLogged = new User(name, nickname, password, communities);

    private Utils utils = new Utils(users, communities);
    private Feed feed = new Feed();
    private Command[] options = new Command[10];

    public Controller(Feed feed, Utils utils, ArrayList<Community> communities)
    {
        this.feed = feed;
        this.utils = utils;
        this.communities = communities;
    }

    public Controller(User userLogged)
    {
        this.userLogged = userLogged;
    }

    public Controller(User userLogged, Feed feed)
    {
        this.userLogged = userLogged;
        this.feed = feed;
    }

    public Controller(User userLogged, ArrayList<Community> communities)
    {
        this.userLogged = userLogged;
        this.communities = communities;
    }

    public Controller(ArrayList<User> users, User userLogged)
    {
        this.users = users;
        this.userLogged = userLogged;
    }


    public Controller(User userLogged, Utils utils, Feed feed)
    {
        this.userLogged = userLogged;
        this.utils = utils;
        this.feed = feed;
    }

    public void setUserLogged(User userLogged)
    {
        this.userLogged = userLogged;
    }

    public void setUsers(ArrayList<User> users)
    {
        this.users = users;
    }

    public void setOptions()
    {
        options[1] = new SendMessageCommand(userLogged, utils, feed);
        options[2] = new SeeFeedCommand(userLogged, feed);
        options[3] = new SeeInboxCommand(userLogged);
        options[4] = new GetProfileInfoCommand(userLogged);
        options[5] = new UpdateProfileCommand(userLogged);
        options[6] = new JoinCreateCommunity(userLogged, communities);
        options[7] = new ManageCommunityCommand(userLogged, communities);
        options[8] = new SeeCommunityMessagesCommand(userLogged, communities);
        options[9] = new AddRemoveFriendCommand(users, userLogged);
    }

    public void executeCommand(int option)
    {
        options[option].executeCommand();
    }
}
