package src;

public class Main
{
    public static void main(String[] args) 
    {
        Rede iFace = new Rede();
       iFace.createAccount();
       iFace.createAccount();
       
       
       System.out.println("Buscando informações do usuário\n");
       iFace.getAccount();

       System.out.println("Removendo conta\n");
       iFace.removeAccount();
       
       System.out.println("Verificando se a conta ainda existe\n");
       iFace.getAccount();
    } 
}