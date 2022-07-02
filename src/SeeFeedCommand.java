import java.util.ArrayList;

class SeeFeedCommand implements Command
{
    private String name;
    private String password;
    private String nickname;
    private ArrayList<Community> communities = new ArrayList<Community>();
    private User userLogged = new User(name, nickname, password, communities);

    private Feed feed = new Feed();

    public SeeFeedCommand(User userLogged, Feed feed) 
    {
        this.userLogged = userLogged;
        this.feed = feed;
    }

    @Override
    public void executeCommand() 
    {
        userLogged.seeFeed(feed);
    }
}
