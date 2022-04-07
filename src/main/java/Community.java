import java.util.ArrayList;

public class Community 
{
    private String nameCom;
    private String descCom;
    private String admCom;
    public ArrayList<String> members;

    public Community(String admCom, String nameCom)
    {
        this.admCom = admCom;
        this.nameCom = nameCom;
    }

    public String getComName()
    {
        return nameCom;
    }
}
