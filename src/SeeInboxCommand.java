import java.util.ArrayList;

class SeeInboxCommand implements Command
{
    private String name;
    private String password;
    private String nickname;
    private ArrayList<Community> communities = new ArrayList<Community>();
    private User userLogged = new User(name, nickname, password, communities);

    public SeeInboxCommand(User userLogged) 
    {
        this.userLogged = userLogged;
    }

    @Override
    public void executeCommand() 
    {
        userLogged.seeInbox();
    }    
}
