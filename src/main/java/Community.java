import java.util.ArrayList;

public class Community 
{
    public Graph usuarios = new Graph(false);

    private String nameCommunity;
    private String descCommunity;
    private String admCommunity;
    private ArrayList<String> members;

    public Community(String name, Graph usuarios)
    {
        this.admCommunity = name;
        this.usuarios = usuarios;
    }
    
    public void addMember(String name)
    {
        Node user = usuarios.searchNode(name);

        if (user == null)
        {
            System.out.println("Usuário não encontrado\n");
            return;
        }
        else
        {
            members.add(name);
        }
    }
}
