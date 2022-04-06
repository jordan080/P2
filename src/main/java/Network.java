import java.util.ArrayList;
import java.util.Scanner;

public class Network 
{
    public Graph usuarios = new Graph(false);
    public static CacheMap<String, String> feed = new CacheMap<String, String>(20);
    public static ArrayList<Community> communities = new ArrayList<Community>();

    public Network()
    {
        
    }

    public void createAccount()
    {
        Scanner reader = new Scanner(System.in);
        System.out.println("Insira seu nome: ");
        String nameUs = reader.next();

        System.out.println("Insira seu apelido: ");
        String nickUs = reader.next();

        System.out.println("Insira sua senha: ");
        String passUs = reader.next();

        User newUser = new User(nameUs, passUs, nickUs);

        Node nodeUs = new Node(nickUs);
        nodeUs.storeUser(newUser);
        usuarios.addEdge(nodeUs, nodeUs);

        nodeUs.User.setGraph(usuarios);
    }

    public void editAccount()
    {
    	Scanner readerGet = new Scanner(System.in);
    	System.out.println("Insira o apelido do usuário a ser buscado:");
    	String user = readerGet.next();

        Node userSr = usuarios.searchNode(user);

        if (userSr == null)
        {
            System.out.println("Usuário não encontrado\n");
            return;
        }
        else
        {
            userSr.User.editInfo();
        }
    }

    public void getAccount()
    {
    	Scanner readerGet = new Scanner(System.in);
    	System.out.println("Insira o apelido do usuário a ser buscado:");
    	String user = readerGet.next();

        Node userSr = usuarios.searchNode(user);
        
        if (userSr == null)
        {
            System.out.println("Usuário não encontrado\n");
            return;
        }
        else
        {
            System.out.println(userSr.User.getInfo());
            return;
        }
    }

    public void removeAccount()
    {
    	Scanner readerGet = new Scanner(System.in);
    	System.out.println("Insira o apelido do usuário a ser removido:");
    	String user = readerGet.next();
    	//reader_rem.close();

        Node userSr = usuarios.searchNode(user);
        
        if (userSr == null)
        {
            System.out.println("Usuário não encontrado\n");
            return;
        }
        else
        {
            userSr = null;    
            return;
        }
    }
}