import java.util.ArrayList;

class Network
{
    private ArrayList<Community> communities = new ArrayList<Community>();
    private Feed feed = new Feed();
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

    public Feed getFeed()
    {
        return feed;
    }
}