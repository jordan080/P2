import java.util.ArrayList;

class AddRemoveFriendCommand extends Controller implements Command 
{
    private String name;
    private String password;
    private String nickname;
    private ArrayList<Community> communities = new ArrayList<Community>();
    private User userLogged = new User(name, nickname, password, communities);

    private ArrayList<User> users = new ArrayList<User>();

    public AddRemoveFriendCommand(ArrayList<User> users, User userLogged)
    {
        super(users, userLogged);
        this.users = users;
        this.userLogged = userLogged;
    }

    @Override
    public void executeCommand() 
    {
        userLogged.addRemoveFriend(users);
    }
}
