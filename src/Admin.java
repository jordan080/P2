import java.util.ArrayList;
import java.io.Console;
import java.util.Scanner;

public class Admin extends User
{
    private String nickname;
    public ArrayList<Community> userCommunites = new ArrayList<Community>();

    public Admin(String nickname, ArrayList<Community> userCommunites)
    {
        super(nickname, userCommunites);    
    }

    public void addRemoveCommunityMembers(ArrayList<Community> communities)
    {
        System.out.println("1. Check invites\n2. Remove members\n3. Community information\n");
        Console console = System.console();
        Scanner userInput = new Scanner(System.in);
        int sub_option = userInput.nextInt();

        if (sub_option == 1)
        {
            String communityName = console.readLine("Type your community name: ");
            Community com = getCommunity(communityName, communities);

            try
            {
                com.addMember(this);
            }
            catch (Exception e)
            {
                System.out.println("Community not found");
            }
        }
        if (sub_option == 2)
        {
            String communityName = console.readLine("Type your community name: ");
            Community com = getCommunity(communityName, communities);

            try
            {
                com.removeMember(this);
            }
            catch (Exception e)
            {
                System.out.println("Community not found");
            }
        }
        if (sub_option == 3)
        {
            String communityName = console.readLine("Type your community name: ");
            Community com = getCommunity(communityName, communities);

            try
            {
                com.communityInfo();
            }
            catch (Exception e)
            {
                System.out.println("Community not found");
            }
        }
    }
}
