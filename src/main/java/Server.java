import java.util.ArrayList;
import java.io.*;
import java.net.*;
import java.util.Map;
import java.util.HashMap;

public class Server 
{
    public Graph network = new Graph(false);
    public static CacheMap<String, String> feed = new CacheMap<String, String>(20);
    public static ArrayList<Community> communities = new ArrayList<Community>();

    public Server() throws IOException, ClassNotFoundException
    {
        ServerSocket listener = new ServerSocket(7777);
        System.out.println("Servidor escutando na porta 7777"); 

        while(true)
        {
            Socket clientSocket = listener.accept();
            System.out.println("Recebida conexão");

            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());

            int option = (int) in.readObject();

            if (option == 1)
            {
                Node nodeUs = (Node) in.readObject();
                String nodeName = nodeUs.name;
                Node userSr = network.searchNode(nodeName);

                if (userSr == null)
                {
                    out.writeObject(0);
                }
                else
                {
                    out.writeObject(1);
                }
            }
            else if (option == 2)
            {
                Node newUser = (Node) in.readObject();
                addNewUser(newUser);
            }
            else if (option == 3)
            {
                String userName = (String) in.readObject();
                Node userSr = network.searchNode(userName);

                if (userSr == null)
                {
                    out.writeObject(0);
                }
                else
                {
                    out.writeObject(1);
                    out.writeObject(userSr);
                }
            }
            else if (option == 4)
            {
                Node User = (Node) in.readObject();
                removeAccount(User);
            }
            else if (option == 5)
            {
                String admCom = (String) in.readObject();
                String comName = (String) in.readObject();

                createCommunity(admCom, comName);
            }
            else if (option == 6)
            {
                String nickUs = (String) in.readObject();
                String comName = (String) in.readObject();

                boolean joinedCom = joinCommunity(nickUs, comName);

                if(!joinedCom)
                {
                    out.writeObject(0);
                }
                else
                {
                    out.writeObject(1);
                }
            }
            else if (option == 7)
            {
                String nickUs = (String) in.readObject();
                String message = (String) in.readObject();

                feed.put(nickUs, message);
            }
            else if (option == 8)
            {
                ArrayList<String> friendList = (ArrayList<String>) in.readObject();
                boolean onlyFriends = (boolean) in.readObject();

                Map<String, String> copyFeed = getFeed(feed, friendList, onlyFriends);
                out.writeObject(copyFeed);
            }
        }
    }

    public void addNewUser(Node newUser)
    {
        network.addEdge(newUser, newUser);
    }

    public void removeAccount(Node User)
    {     
        network.addEdge(User, null);
        User = null;    
    }

    public void createCommunity(String nickUs, String comName)
    {
        Community newCom = new Community(nickUs, comName);
        communities.add(newCom);
    }

    public boolean joinCommunity(String nickUs, String comName)
    {
        for(Community com: communities)
        {
            if (com.getComName() == comName)
            {
                com.members.add(nickUs);
                return true;
            }
        }

        return false;
    }

    public Map<String, String> getFeed(CacheMap<String, String> feed, ArrayList<String> friendList, boolean onlyFriends)
    {
        Map<String, String> copyOfFeed = new HashMap<String, String>();

        if (onlyFriends)
        {
            for(CacheMap.Entry<String, String> m : feed.entrySet())
            {
                if(friendList.contains(m.getKey()))
                {
                    copyOfFeed.put(m.getKey(), m.getValue());
                }
            }
        }
        else
        {
            copyOfFeed = feed;
        }

        return copyOfFeed;
    }
}