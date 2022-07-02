import java.util.ArrayList;

class UpdateProfileCommand implements Command
{
    private String name;
    private String password;
    private String nickname;
    private ArrayList<Community> communities = new ArrayList<Community>();
    private User userLogged = new User(name, nickname, password, communities);

    public UpdateProfileCommand(User userLogged) 
    {
        this.userLogged = userLogged;
    }

    @Override
    public void executeCommand() 
    {
        userLogged.updateProfile();
    }  
}
