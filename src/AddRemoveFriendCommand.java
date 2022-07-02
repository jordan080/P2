import java.util.ArrayList;

class AddRemoveFriendCommand implements Command 
{
    private String name;
    private String password;
    private String nickname;
    private ArrayList<Community> communities = new ArrayList<Community>();
    private User userLogged = new User(name, nickname, password, communities);

    private ArrayList<User> users = new ArrayList<User>();

    public AddRemoveFriendCommand(User userLogged, ArrayList<User> users)
    {
        this.userLogged = userLogged;
        this.users = users;
    }

    @Override
    public void executeCommand() 
    {
        userLogged.addRemoveFriend(users);
    }
}
