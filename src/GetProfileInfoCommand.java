import java.util.ArrayList;

class GetProfileInfoCommand extends Controller implements Command
{
    private String name;
    private String password;
    private String nickname;
    private ArrayList<Community> communities = new ArrayList<Community>();
    private User userLogged = new User(name, nickname, password, communities);

    public GetProfileInfoCommand(User userLogged) 
    {
        super(userLogged);
        this.userLogged = userLogged;
    }

    @Override
    public void executeCommand() 
    {
        
        userLogged.getProfileInfo();
    }     
}
