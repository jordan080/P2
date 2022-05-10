import java.util.ArrayList;
import java.io.Console;

class Community
{
    private String name;
    private String description;
    Admin admin;

    ArrayList<User> invites = new ArrayList<User>();
    ArrayList<User> members = new ArrayList<User>();
    ArrayList<Message> inbox = new ArrayList<Message>();

    public Community(String name, String description, Admin owner)
    {
        this.name = name;
        this.description = description;
        this.admin = owner;
        owner.userCommunites.add(this);
    }

    public String getComName()
    {
        return name;
    }

    public void addMember(User user)
    {
        Console console = System.console();

        for(User inviter: invites)
        {
            String option = console.readLine(inviter.getNick() + " wants to join your community. Do you accept? ");

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

    public void removeMember(User user)
    {
        Console console = System.console();
        String option = console.readLine("Nickname of the user to be removed: ");

        for(User member: members)
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
        System.out.println("Name: " + name + "\n"
                            + "Description: " + description + "\n"
                            + "Total members: " + members.size() + "\n"
                            + "Invites to join the community: " + invites.size());

        System.out.println("Users:");
        for(User user: members)
        {
            if (user.getNick().equals(admin.getNick()))
            {
                System.out.println(user.getNick() + " (adm)");
            }
            else
            {
                System.out.println(user.getNick());
            }
        }
    }
}