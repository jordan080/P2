public class Main
{
    public static void main(String[] args) 
    {
        Network iFace = new Network();

        System.out.println("Criando e adicionando uma conta\n"); 
        iFace.createAccount();

        System.out.println("Editando informações da conta\n");
        iFace.editAccount();

        System.out.println("Removendo uma conta\n");
        iFace.removeAccount();
       
        System.out.println("Verificando se uma conta ainda existe\n");
        iFace.getAccount();
        
    } 
}