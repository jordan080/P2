import java.util.ArrayList;

class ManageCommunityCommand extends Controller implements Command
{
    private String name;
    private String password;
    private String nickname;
    private ArrayList<Community> communities = new ArrayList<Community>();
    private User userLogged = new User(name, nickname, password, communities);

    public ManageCommunityCommand(User userLogged, ArrayList<Community> communities) 
    {
        super(userLogged, communities);
        this.userLogged = userLogged;
        this.communities = communities;
    }

    @Override
    public void executeCommand() 
    {
        try
        {
            userLogged.manageCommunity(communities);
        }
        catch (Exception e)
        {
            System.out.println("Community not found.");
        }
    } 
}