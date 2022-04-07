public class Node 
{
    public String name;
    public User User;
    public boolean visited;

    public Node(String name)
    {
        this.name = name;
        visited = false;
    }

    public void storeUser(User User)
    {
        this.User = User;
    }

    void visit() 
    {
        visited = true;
    }

    void unvisit() 
    {
        visited = false;
    }

    boolean isVisited()
    {
        return visited;
    }
}