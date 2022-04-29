import java.util.ArrayList;
import java.io.Console;

class Community
{
    String name;
    String description;
    Admin admin;

    ArrayList<Account> invites = new ArrayList<Account>();
    ArrayList<Account> members = new ArrayList<Account>();
    ArrayList<Message> inbox = new ArrayList<Message>();

    public Community(String name, String description, Admin owner)
    {
        this.name = name;
        this.description = description;
        this.admin = owner;
        owner.userCommunites.add(this);
    }

    public String comName()
    {
        return name;
    }

    public void addMember(Account user)
    {
        Console console = System.console();

        for(Account inviter: invites)
        {
            String option = console.readLine(inviter.getNick() + " wants to join your group. Do you accept? ");

            if(option.equals("Yes") || option.equals("yes") || option.equals("y"))
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

    public void removeMember(Account user)
    {
        Console console = System.console();
        String option = console.readLine("Nickname of the user to be removed: ");

        for(Account member: members)
        {
            if(member.getNick().equals(option))
            {
                this.members.remove(member);
                member.userCommunites.remove(this);
                System.out.println("User removed.");
                this.communityInfo();
            }
        }
    }

    public void communityInfo()
    {
        System.out.println("Total members: " + members.size());
        System.out.println("Invites to join the community: " + invites.size());

        for(Account user: members)
            System.out.println("User: " + user.getNick());
    }
}