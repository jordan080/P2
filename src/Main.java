package src;

public class Main
{
    public static void main(String[] args) 
    {
        Rede iFace = new Rede();
       iFace.createAccount();
       iFace.createAccount();

       iFace.getAccount("Jordan");

       iFace.removeAccount("Jordan");
       
       iFace.getAccount("Jordan");
    } 
}