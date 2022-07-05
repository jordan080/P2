import java.util.ArrayList;

class SeeCommunityMessagesCommand extends Controller implements Command
{
    private String name;
    private String password;
    private String nickname;
    private ArrayList<Community> communities = new ArrayList<Community>();
    private User userLogged = new User(name, nickname, password, communities);

    public SeeCommunityMessagesCommand(User userLogged, ArrayList<Community> communities) 
    {
        super(userLogged, communities);
        this.userLogged = userLogged;
        this.communities = communities;
    }

    @Override
    public void executeCommand() 
    {
        userLogged.seeCommunityMessages(communities);
    }  
}
