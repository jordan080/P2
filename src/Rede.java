package src;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;

public class Rede 
{
    private String name;
    private String password;
    private String nickName;
    private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

    public Rede()
    {
        
    }

    public Rede(String name, String password, String nickName)
    {
        this.name = name;
        this.password = password;
        this.nickName = nickName;
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

        Usuario newUser = new Usuario(nameUs, passUs, nickUs);
        usuarios.add(newUser);
    }
    
    public void editAccount()
    {
    	Scanner reader_bus = new Scanner(System.in);
    	System.out.println("Insira o apelido do usuário a ser buscado:");
    	String user = reader_bus.next();
    	
    	Iterator<Usuario> iter = usuarios.iterator();

        while(iter.hasNext())
        {
            Usuario u = iter.next();
            if(u.getNick().equals(user))
            {
            	u.editInfo();
            	return;
            }
        }
        
        System.out.println("Usuário não encontrado\n");
    }

    public void getAccount()
    {
    	Scanner reader_bus = new Scanner(System.in);
    	System.out.println("Insira o apelido do usuário a ser buscado:");
    	String user = reader_bus.next();
    	//reader_bus.close();
        
        Iterator<Usuario> iter = usuarios.iterator();

        while(iter.hasNext())
        {
            Usuario u = iter.next();
            if(u.getNick().equals(user))
            {
            	System.out.println(u.getName());
            	return;
            }
        }
        
        System.out.println("Usuário não encontrado\n");
    }

    public void removeAccount()
    {
    	Scanner reader_rem = new Scanner(System.in);
    	System.out.println("Insira o apelido do usuário a ser removido:");
    	String user = reader_rem.next();
    	//reader_rem.close();
    	
        Iterator<Usuario> iter = usuarios.iterator();

        while(iter.hasNext())
        {
            Usuario u = iter.next();
            if(u.getNick().equals(user))
            {
                iter.remove();
                System.out.println("Usuário removido\n");
                return;
            }
        }
        
        System.out.println("Usuário não encontrado\n");
    }
}