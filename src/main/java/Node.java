public class Node 
{
    String name;
    User User;
    boolean visited;

    Node(String name)
    {
        this.name = name;
        visited = false;
    }

    void storeUser(User User)
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