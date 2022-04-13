import java.util.ArrayList;
import java.io.Console;

class Community
{
    String name;
    String description;
    Account comOwner;

    ArrayList<Account> invites = new ArrayList<Account>();
    ArrayList<Account> members = new ArrayList<Account>();
    ArrayList<Message> inbox = new ArrayList<Message>();

    public Community(String name, String description, Account owner)
    {
        this.name = name;
        this.description = description;
        this.comOwner = owner;
        owner.userCommunites.add(this);
    }

    public String comName()
    {
        return name;
    }

    public void addMember(Account user)
    {
        Console console = System.console();
        if (user.nickname.equals(this.comOwner.nickname))
        {
            for(Account inviter: invites)
            {
                String option = console.readLine(inviter.nickname + " wants to join your group. Do you accept? ");

                if(option == "Yes" || option == "yes" || option == "y")
                {
                    this.members.add(inviter);
                    inviter.userCommunites.add(this);
                    this.communityInfo();
                }
                else
                {
                    System.out.println("User not accepted.");

                    this.communityInfo();
                }
            }

            invites.clear();
        }
        else
        {
            System.out.println("You don't have the privileges to add/remove members.");
        }
    }   

    public void removeMember(Account user)
    {
        Console console = System.console();
        String option = console.readLine("Nickname of the user to be removed: ");

        if(user.nickname.equals(this.comOwner.nickname))
        {
            for(Account member: members)
            {
                if(member.nickname.equals(option))
                {
                    this.members.remove(member);
                    member.userCommunites.remove(this);
                    System.out.println("User removed.");
                    this.communityInfo();
                }
            }
        } 
        else 
        {
            System.out.println("You don't have the privilege to add/remove members.");
        }
    }

    public void communityInfo()
    {
        System.out.println("Total members: " + members.size());
        System.out.println("Invites to join the community: " + invites.size());

        for(Account user: members)
            System.out.println("User: " + user.nickname);
    }
}