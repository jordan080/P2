import java.util.ArrayList;

class Network
{
    private ArrayList<Community> communities = new ArrayList<Community>();
    private ArrayList<Message> feed = new ArrayList<Message>();
    private ArrayList<Account> users = new ArrayList<Account>(); 

    public Network()
    {
  
    }

    public ArrayList<Account> getUsers()
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