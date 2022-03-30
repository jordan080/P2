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

        System.out.println("Insira seu nome de usuário: ");
        String nickUs = reader.next();

        System.out.println("Insira sua senha: ");
        String passUs = reader.next();

        Usuario newUser = new Usuario(nameUs, passUs, nickUs);
        usuarios.add(newUser);
    }

    public void getAccount(String name)
    {
    	/*
        Usuario u1 = usuarios.get(0);
        Usuario u2 = usuarios.get(1);

        System.out.println(u1.getName());
        System.out.println(u2.getName());
        */
        
        Iterator<Usuario> iter = usuarios.iterator();

        while(iter.hasNext())
        {
            Usuario u = iter.next();
            if(u.getName().equals(name))
            {
            	System.out.println(u.getNick());
            	return;
            }
        }
        
        System.out.println("Usuário não encontrado");
    }

    public void removeAccount(String name)
    {
        Iterator<Usuario> iter = usuarios.iterator();

        while(iter.hasNext())
        {
            Usuario u = iter.next();
            if(u.getName().equals(name))
            {
                iter.remove();
                System.out.println("Usuário removido");
                return;
            }
        }
        
        System.out.println("Usuário não encontrado");
    }
}