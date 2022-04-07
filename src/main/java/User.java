import java.util.Scanner;
import java.util.ArrayList;

public class User 
{
    private String name;
    private String password;
    private String nickName;
    private String city = null;
    private String birthday = null;
    private ArrayList<String> inbox = new ArrayList<String>();
    private ArrayList<String> friendList = new ArrayList<String>();
    private ArrayList<String> invites = new ArrayList<String>();

    public User(String name, String password, String nickName)
    {
        this.name = name;
        this.password = password;
        this.nickName = nickName;
    }

    public String getName()
    {
        return name;
    }

    public String getNick()
    {
        return nickName;
    }

    public String getInfo()
    {
        return name + "\n"
        + nickName + "\n"
        + city + "\n"
        + birthday + "\n";
    }

    public ArrayList<String> getFriends()
    {
        return friendList;
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

    public void addInvite(String name)
    {
        invites.add(name);
    }

    public boolean checkInvites()
    {
        return invites.isEmpty();
    }

    public void acceptFriendRequest()
    {
        Scanner scanner = new Scanner(System.in);

        for(String nameIn: invites)
        {
            System.out.println("Aceitar pedido de amizade de " + nameIn + "?");
            String answer = scanner.next();

            while (answer != "sim" || answer != "não")
            {
                System.out.println("Tente novamente");
                answer = scanner.next();
            }
    
            if (answer == "yes")
            {
                friendList.add(nameIn);
            }
        }
    }

    public void receiveMessage(String message)
    {
        inbox.add(message);
    }

    public boolean checkInbox()
    {
        return inbox.isEmpty();
    }

    public void readMessages()
    {
        for(String message: inbox)
        {
            System.out.println(message);
            inbox.remove(message);
        }
    }
}