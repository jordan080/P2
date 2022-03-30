package src;

import java.util.Scanner;
import java.util.ArrayList;

public class Usuario 
{
    private String name;
    private String password;
    private String nickName;
    private String city;
    private String birthday;

    public Usuario()
    {
        
    }

    public Usuario(String name, String password, String nickName)
    {
        this.name = name;
        this.password = password;
        this.nickName = nickName;
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

    public String getName()
    {
        return name;
    }
    
    public String getNick()
    {
        return nickName;
    }
    
}