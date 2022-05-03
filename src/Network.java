import java.util.ArrayList;

class Network
{
    private ArrayList<Community> communities = new ArrayList<Community>();
    private ArrayList<Message> feed = new ArrayList<Message>();
    private ArrayList<User> users = new ArrayList<User>(); 

    public Network()
    {
  
    }

    public ArrayList<User> getUsers()
    {
        return users;
    }

    public ArrayList<Community> getComs()
    {
        return communities;
    }

    public ArrayList<Message> getFeed()
    {
        return feed;
    }
}