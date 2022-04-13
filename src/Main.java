import java.io.Console;
import java.util.ArrayList;
import java.util.Scanner;

class Main
{
    static Account userLogged = null;
    public static ArrayList<Community> communities = new ArrayList<Community>();
    public static ArrayList<Message> feed = new ArrayList<Message>();
    public static ArrayList<Account> users = new ArrayList<Account>();

    public static void main(String[] args) 
    {

        boolean isLogged = false;

        Scanner userInp = new Scanner(System.in);
        Console console = System.console();

        while(true)
        {
            if (!isLogged)
            {
                menuNotLogged();
                int option = userInp.nextInt();

                if (option == 1)
                {
                    System.out.println("Creating account");
                    String userName = console.readLine("Insert your name: ");
                    String userNickname = console.readLine("Insert your a nickname: ");
                    String userPassword = console.readLine("Insert your password: ");

                    if(findUser(userNickname, users))
                    {
                        System.out.println("This user was already created.");
                    }
                    else
                    {
                        Account newUser = new Account(userName, userPassword, userNickname);
                        users.add(newUser);
                        System.out.println("User created.");
                    }
                }
                else if (option == 2)
                {
                    System.out.println("Insert your information.");
                    String userNickname = console.readLine("Nickname: ");
                    String userPassword = console.readLine("Password: ");

                    if(findUser(userNickname, users))
                    {
                        if(validateLogin(userNickname, userPassword, users))
                        {
                            isLogged = !isLogged;

                                for(Account user: users)
                                {
                                    if(user.getNick().equals(userNickname))
                                    {
                                        userLogged = user;
                                    }
                                }
                        }
                        else
                        {
                            System.out.println("The password is incorrect.");
                        }
                    }
                    else
                    {
                        System.out.println("User doesn't exists. Try again or create a new account.");
                    }
                }
                else if (option == 3)
                {
                    break;
                }
                else
                {
                    System.out.println("Try again.");
                }
            }
            else
            {
                menuLogged();
                int option = userInp.nextInt();

                if (option == 1)
                {
                    sendMessage();
                }
                else if (option == 2)
                {
                    seeFeed();
                }
                else if (option == 3)
                {
                    getMessages();
                }
                else if (option == 4)
                {
                    getProfileInfo();
                }
                else if (option == 5)
                {
                    updateProfile();
                }
                else if (option == 6)
                {
                    addRemoveCommunity();
                }
                else if (option == 7)
                {
                    addRemoveCommunityMembers();
                }
                else if(option == 8)
                {
                    getComMessages();
                }
                else if (option == 9)
                {
                    addRemoveFriend();
                }
                else if (option == 10)
                {
                    deleteUser(isLogged);
                }
                else if (option == 11)
                {
                    System.out.println("Logging out...");
                    isLogged = !isLogged;
                    userLogged = null;
                }
                else
                {
                    System.out.println("Try again.");
                }
            }
        }
    }

    public static void getMessages()
    {
        for(Message message: userLogged.inbox)
        {
            System.out.println(message.author.nickname + ": " + message.message + "\n"); 
        }
    }

    public static void getComMessages()
    {
        Console console = System.console();
        String comName = console.readLine("Type your community name: ");
        Community com = findCommunity(comName, communities);

        if (com != null)
        {
            for(Message message: com.inbox)
            {
                System.out.println(message.author.nickname + ": " + message.message + "\n"); 
            }
        }
        else
        {
            System.out.println("Community not found");
        }
    }

    public static void addRemoveCommunityMembers()
    {
        System.out.println("1. Check invites\n2. Remove members\n");
        Console console = System.console();
        Scanner userInp = new Scanner(System.in);
        int sub_option = userInp.nextInt();

        if (sub_option == 1)
        {
            String comName = console.readLine("Type your community name: ");
            Community com = findCommunity(comName, communities);

            if (com != null)
            {
                com.addMember(userLogged);
            }
            else
            {
                System.out.println("Community not found");
            }
        }
        if (sub_option == 2)
        {
            String comName = console.readLine("Type your community name: ");
            Community com = findCommunity(comName, communities);

            if (com != null)
            {
                com.removeMember(userLogged);
            }
            else
            {
                System.out.println("Community not found");
            }
        }
    }

    public static void addRemoveCommunity()
    {
        System.out.println("1. Join a community\n2. Create a community");

        Console console = System.console();
        Scanner userInp = new Scanner(System.in);
        int sub_option = userInp.nextInt();

        if(sub_option == 1)
        {
            String comName = console.readLine("Community name: ");
            Community com = findCommunity(comName, communities);

            if(com != null)
            {
                com.invites.add(userLogged);
                com.communityInfo();
            }
            else
            {
                System.out.println("Community not found");
            }
        }
        else if (sub_option == 2)
        {
            String newComName = console.readLine("New community name: ");
            Community checkCom = findCommunity(newComName, communities);

            if(checkCom != null)
            {
                System.out.println("This group was already created");
            }
            else
            {
                String newComDesc = console.readLine("New community description: ");
                Community newCom = new Community(newComName, newComDesc, userLogged);

                newCom.members.add(userLogged);
                newCom.communityInfo();
                communities.add(newCom);
            }
        }
        else
        {
            System.out.println("Insert valid option.");
        }
    }

    public static void addRemoveFriend()
    {
        System.out.println("1. See invites\n2. Add a friend");
        Scanner userInp = new Scanner(System.in);
        Console console = System.console();

        int sub_option = userInp.nextInt();

        if (sub_option == 1)
        {
            userLogged.checkFriendshipRequests();
        }
        else if (sub_option == 2)
        {
            String friendNick = console.readLine("Insert nickname: ");

            if(findUser(friendNick, users))
            {
                Account invitedUser = getUser(friendNick, users);
                invitedUser.invites.add(userLogged);
            }
            else
            {
                System.out.println("There is no user with given nickname.");
            }
        }
        else
        {
            System.out.println("Insert valid option.");
        }
    }

    public static void getProfileInfo()
    {
        Console console = System.console();
        String userNickname = console.readLine("User nickname: ");

        if(findUser(userNickname, users))
        {
            Account user = getUser(userNickname, users);
            if (user != null)
            {
                user.getInfo();
            }
            else
            {
                System.out.println("This user doesn't have a profile.");
            }
        }
        else
        {
            System.out.println("User not found.");
        }
    }

    public static void updateProfile()
    {
        Console console = System.console();
        String city = console.readLine("Please, insert your birth city: ");
        String birthday = console.readLine("Please, insert your birthday following the template DD-MM-YYYY: ");

        if(userLogged.profile != null)
        {
            userLogged.profile.updateProfile(city, birthday);
        }
        else
        {
            userLogged.profile = new Profile(userLogged.nickname, city, birthday);
            System.out.println("Profile created.");
        }
    }

    public static void deleteUser(boolean isLogged)
    {
        Console console = System.console();
        System.out.println("This action cannot be undone. Are you sure?");
        String answer = console.readLine();

        if (answer == "Yes" || answer == "yes" || answer == "y")
        {
            userLogged.friends.clear();

            for(Community com: userLogged.userCommunites)
            {
                //User is owner of a group
                if(com.comOwner.nickname.equals(userLogged.nickname))
                {
                    for(Account user: com.members)
                    {
                        user.userCommunites.remove(com);
                    }

                    communities.remove(com);
                }
                else
                {
                    com.members.remove(userLogged);
                }
            }

            users.remove(userLogged);
            userLogged = null;
            isLogged = !isLogged;
        }
        else if (answer == "No" || answer == "no" || answer == "n")
        {
            System.out.println("Operation cancelled.");
        }
    }

    public static void sendMessage()
    {
        Console console = System.console();
        
        System.out.println("1. To a user\n2. To a community\n3. News Feed");
        Scanner userInp = new Scanner(System.in);
        int sub_option = userInp.nextInt();

        if (sub_option == 1)
        {
            //Message to a user
            String destination = console.readLine("Insert the nickname of the user you want to send a message: ");

            if(findUser(destination, users))
            {
                String text = console.readLine("Write your message: ");
                Message message = new Message(userLogged, text);
                Account destinyUser = getUser(destination, users);
                destinyUser.inbox.add(message);
                System.out.println("Message sent");
            }
            else
            {
                System.out.println("User not found.");
            }
        }
        else if (sub_option == 2)
        {
            //Message to a community
            String destination = console.readLine("Insert the name of the community you want to send a message: ");
            Community com = findCommunity(destination, communities);

            if (com != null)
            {
                String text = console.readLine("Write your message: ");
                Message message = new Message(userLogged, text);
                com.inbox.add(message);
                System.out.println("Message sent to community");
            }
            else
            {
                System.out.println("Community not found.");
            }
        }
        else if (sub_option == 3)
        {
            //Message to feed
            String text = console.readLine("Write your message: ");
            Message message = new Message(userLogged, text);
            feed.add(message);
            System.out.println("Message sent to feed");
        }
        else
        {
            System.out.println("Insert valid option.");
        }
    }

    public static void seeFeed()
    {
        boolean feedOpt = userLogged.feedVisibility();
        if (feedOpt == true)
        {
            for(Message message: feed)
            {
                for(Account friend: userLogged.friends)
                {
                    if(message.author.getNick().equals(friend.nickname))
                    {
                        System.out.println(message.author.nickname + ": " + message.message + "\n");
                    }
                }
            }
        }
        else
        {
            for(Message message: feed)
            {
                System.out.println(message.author.nickname + ": " + message.message + "\n");
            }
        }
    }

    public static boolean findUser(String nickname, ArrayList<Account> users)
    {
        for (Account user: users)
        {
            if (user.getNick().equals(nickname))
            {
                return true;
            }
        }
        return false;
    }

    public static Community findCommunity(String comName, ArrayList<Community> communities)
    {
        Community searchedCom = null;

        for(Community community: communities)
        {
            if(community.comName().equals(comName))
            {
                searchedCom = community;
                return searchedCom;
            }
        }

        return searchedCom;
    }

    public static Account getUser(String nickname, ArrayList<Account> users)
    {
        Account searchedUser = null;
        {
            for (Account user: users)
            {
                if (user.getNick().equals(nickname))
                {
                    searchedUser = user;
                    return searchedUser;
                }
            }
    
            return searchedUser;
        } 
    }

    public static void menuNotLogged()
    {

        System.out.println("------------------------------");
        System.out.println("1. Sign up\n2. Sign in\n3. Exit");
        System.out.println("------------------------------");

    }

    public static void menuLogged()
    {

        System.out.println("------------------------------");
        System.out.println("MENU");  
        System.out.println("1. Send a message\n" +
            "2. See feed\n" +
            "3. See inbox\n" +
            "4. Recover user information\n" +
            "5. Edit profile\n" +
            "6. Join/Create a community\n" + 
            "7. Add/Remove community members\n" +
            "8. Group messages\n" +
            "9. Add friends\n" +
            "10. Remove account\n" +
            "11. Log out");

        System.out.println("------------------------------");

    }

    public static boolean validateLogin(String userNickname, String userPassword, ArrayList<Account> users)
    {
        int result = 0;

        for (int i = 0; i < users.size(); i++)
        {
            Account user = users.get((i));

            if(user.getNick().equals(userNickname))
            {
                if(user.getPass().equals(userPassword))
                {
                    result = 1;
                    break;
                }
                else
                {
                    break;
                }
            }
        }

        if(result == 1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}