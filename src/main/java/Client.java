import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class Client {

    public static void main(String arg[]) throws IOException, ClassNotFoundException
    {
        boolean isLogged = false;
        Socket socket = new Socket("localhost", 7777);
        System.out.println("Acessou o servidor na porta 7777");
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream()); 
        
        String nameUs = "";
        String nickUs = "";
        String passUs = "";
        User User = new User(nameUs, nickUs, passUs);

        boolean hasFriendResquest = false;
        boolean hasMessages = false;

        while(isLogged == false)
        {
            menuNotLogged();
            Scanner reader = new Scanner(System.in);
            System.out.print("Select option: ");
            int option = reader.nextInt();

            if (option == 1)
            {
                Scanner readerSel = new Scanner(System.in);
                System.out.println("Insira seu apelido: ");
                nickUs = readerSel.next();
        
                System.out.println("Insira sua senha: ");
                passUs = readerSel.next();

                Node nodeUs = new Node(nickUs);

                out.writeInt(1);
                out.writeObject(nodeUs);

                int responseServer = (int) in.readObject();

                if (responseServer == 0)
                {
                    System.out.println("Usuário não encontrado.");
                }
                else
                {
                    System.out.println("Logado com sucesso.");
                    User = nodeUs.User;
                    isLogged = true;
                    break;
                }
            }
            else if (option == 2)
            {
                Node newUser = createAccount();
                out.writeObject(2);
                out.writeObject(newUser);
            }
            else
            {
                socket.close();
                System.exit(0);
            }
        }
        while(isLogged == true)
        {
            if (!hasFriendResquest)
            {
                boolean checkInvites = User.checkInvites();
                if(checkInvites)
                {
                    hasFriendResquest = true;
                }
            }
            else
            {
                User.acceptFriendRequest();
            }

            if (!hasMessages)
            {
                boolean checkInbox = User.checkInbox();
                if(checkInbox)
                {
                    hasMessages = true;
                }
            }
            else
            {
                User.readMessages();
            }

            Scanner reader = new Scanner(System.in);
            System.out.print("Select option: ");
            int option = reader.nextInt();

            if (option == 1)
            {
                Scanner readerGet = new Scanner(System.in);
                System.out.println("Insira o apelido do usuário a ser buscado:");
                String user = readerGet.next();

                out.writeObject(3);
                out.writeObject(user);

                int responseServer = (int) in.readObject();

                if (responseServer == 0)
                {
                    System.out.println("Usuário não encontrado.");
                }
                else
                {
                    Node userNode = (Node) in.readObject();
                    userNode.User.editInfo();
                } 
            }
            else if (option == 2)
            {
                Scanner readerGet = new Scanner(System.in);
                System.out.println("Insira o apelido do usuário a ser adicionado como amigo:");
                String user = readerGet.next();

                out.writeObject(3);
                out.writeObject(user);

                int responseServer = (int) in.readObject();

                if (responseServer == 0)
                {
                    System.out.println("Usuário não encontrado.");
                }
                else
                {
                    Node userNode = (Node) in.readObject();
                    userNode.User.addInvite(nickUs);
                } 
            }
            else if (option == 3)
            {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Escreva o nome do destinatário:");
                String userDes = scanner.next();
                System.out.println("Escreva a mensagem a ser enviada:");
                String message = scanner.next();

                out.writeObject(3);
                out.writeObject(userDes);

                int responseServer = (int) in.readObject();

                if (responseServer == 0)
                {
                    System.out.println("Usuário não encontrado.");
                }
                else
                {
                    Node userNode = (Node) in.readObject();
                    userNode.User.receiveMessage(message);
                } 
            }
            else if (option == 4)
            {
                Scanner readerGet = new Scanner(System.in);
                System.out.println("Insira o nome da comunidade:");
                String comName = readerGet.next(); 
                
                out.writeObject(5);
                out.writeObject(User.getNick());
                out.writeObject(comName);
            }
            else if (option == 5)
            {
                Scanner readerGet = new Scanner(System.in);
                System.out.println("Insira o nome da comunidade:");
                String comName = readerGet.next();
                
                out.writeObject(6);
                out.writeObject(User.getNick());
                out.writeObject(comName);

                int responseServer = (int) in.readObject();

                if (responseServer == 0)
                {
                    System.out.println("Comunidade não encontrada.");
                }
                else
                {
                    System.out.println("Se juntou à comunidade." + comName + "\n");
                } 
            }
            else if (option == 6)
            {
                System.out.println(User.getInfo());
            }
            else if (option == 7)
            {
                out.writeObject(4);
                out.writeObject(User);
                socket.close();
            }
            else if (option == 8)
            {
                Scanner readerGet = new Scanner(System.in);
                System.out.println("Mensagem ao feed:");
                String message = readerGet.next();

                out.writeObject(7);
                out.writeObject(User.getNick());
                out.writeObject(message);
            }
            else if (option == 9)
            {
                boolean onlyFriends = false;
                Scanner readerGet = new Scanner(System.in);
                System.out.println("Gostaria de filtrar somente mensagens de amigos?");
                String answer = readerGet.next();

                if (answer == "sim")
                {
                    onlyFriends = true;
                }

                out.writeObject(8);
                out.writeObject(User.getFriends());
                out.writeObject(onlyFriends);

                Map<String, String> curFeed = (Map<String, String>) in.readObject();

                for(Map.Entry<String, String> m : curFeed.entrySet())
                {
                    System.out.println(m.getKey() + ":" + m.getValue());
                }
            }
            else if (option == 10)
            {
                socket.close();
                System.exit(0);
            }
        }
    }

    public static void menuNotLogged()
    {
        System.out.println("------------------------------");

        System.out.println("1. Entrar\n" +
            "2. Cadastrar\n" +
            "3. Sair\n");

        System.out.println("------------------------------");
    }

    public static void menuLogged(){

        System.out.println("------------------------------");

        System.out.println("MENU");
        System.out.println("1. Editar perfil\n" +
            "2. Adicionar amigos\n" +
            "3. Ver mensagens\n" +
            "4. Criar uma comunidade\n" +
            "5. Se juntar à uma comunidade\n" +
            "6. Ver informações do usuário\n" +
            "7. Remover conta\n" +
            "8. Enviar mensagem ao feed\n" +
            "9. Ver feed\n" +
            "10. Sair");

        System.out.println("------------------------------");
    }

    public static Node createAccount()
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
        return nodeUs;
    }
}
