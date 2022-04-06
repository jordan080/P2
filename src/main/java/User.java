import java.util.Scanner;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class User 
{
    public Graph usuarios = new Graph(false);

    private String name;
    private String password;
    private String nickName;
    private String city;
    private String birthday;
    private ArrayList<String> messages = new ArrayList<String>();
    private ArrayList<String> friendList = new ArrayList<String>();

    public static CacheMap<String, String> feed = new CacheMap<String, String>(20);

    public User()
    {
        
    }

    public User(String name, String password, String nickName)
    {
        this.name = name;
        this.password = password;
        this.nickName = nickName;
    }
    
    void setGraph(Graph usuarios)
    {
        this.usuarios = usuarios;
    }

    public void editInfo()
    {
    	System.out.println("Selecione as informações que deseja editar: ");
    	System.out.println("1- Cidade onde mora: ");
    	System.out.println("2- Data de nascimento: ");
    	
    	
    	Scanner reader = new Scanner(System.in);
    	int option = reader.nextInt();
    	
		if (option == 1)
		{
			System.out.println("Insira a cidade onde mora: ");
	        this.city = reader.next();
	        System.out.println("Informação adicionada");
		}
		else if (option == 2)
		{
			System.out.println("Insira a data de nascimento: ");
	        this.birthday = reader.next();
	        System.out.println("Informação adicionada");
		}
    }

    public String getInfo()
    {
        return name + "\n" + nickName;
    }

    public boolean recieveFriendRequest(String name)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Aceitar pedido de amizade de " + name + "?");
        String answer = scanner.next();

        while (answer != "sim" || answer != "não")
        {
            System.out.println("Tente novamente");
            answer = scanner.next();
        }

        if (answer == "yes")
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void sendFriedRequest(String name)
    {
    	Scanner readerGet = new Scanner(System.in);
    	System.out.println("Insira o apelido do usuário a ser buscado:");
    	String user = readerGet.next();

        Node userSource = usuarios.searchNode(name);
        Node userDestination = usuarios.searchNode(user);
        
        if (userDestination == null)
        {
            System.out.println("Usuário não encontrado\n");
            return;
        }
        else
        {
            boolean answer = userDestination.User.recieveFriendRequest(name);
            if (answer)
            {
                friendList.add(userDestination.name);
                usuarios.addEdge(userSource, userDestination);
            }
            else
            {
                System.out.println("Usuário " + user + "recusou a solicitação de amizade");
            }
            return;
        }  
    }
    
    public void sendMessage(String nameSource, String nameDestination)
    {
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("Escreva a mensagem a ser enviada para " + nameDestination + " :");
    	String message = scanner.next();

        Node userDestination = usuarios.searchNode(nameDestination);
        userDestination.User.recieveMessage(nameSource, message);
    }

    public void recieveMessage(String name, String message)
    {
        String messageRecieved = "Usuário " + name + "disse: \n" + message;
        messages.add(messageRecieved);

        return;
    }

    public void sendToFeed(String name)
    {
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("Escreva a mensagem a ser enviada para o feed de notícias:");
    	String message = scanner.next() + "\n";

        feed.put(name, message);
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

    public void readFeed()
    {
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("Deseja ver mensagens de somente amigos?:");
    	String answer = scanner.next();

        while (answer != "sim" || answer != "não")
        {
            System.out.println("Tente novamente. Deseja ver mensagens de somente amigos?:");
            answer = scanner.next();
        }

        boolean onlyFriends = false;
        if (answer == "sim")
        {
            onlyFriends = true;
        }

        Map<String, String> curFeed = getFeed(feed, friendList, onlyFriends);
        for(Map.Entry<String, String> m : curFeed.entrySet())
        {
            System.out.println(m.getKey() + ":" + m.getValue());
        }
    }
}