package src;

import java.util.Scanner;
import java.util.ArrayList;

public class Usuario 
{
    private String name;
    private String password;
    private String nickName;

    public Usuario()
    {
        
    }

    public Usuario(String name, String password, String nickName)
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
    
}