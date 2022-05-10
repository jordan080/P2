import java.io.Console;
import java.util.ArrayList;
import java.util.Scanner;

class Main
{
    static User userLogged = null;
    static Network iFace = new Network();
    static ArrayList<User> users = iFace.getUsers();
    static ArrayList<Community> communities = iFace.getComs();
    static ArrayList<Message> feed = iFace.getFeed();

    public static void main(String[] args) 
    {
        boolean isLogged = false;
        Scanner userInput = new Scanner(System.in);
        Console console = System.console();

        while(true)
        {
            if (!isLogged)
            {
                menuNotLogged();
                try
                {
                    int option = userInput.nextInt();

                    if (option == 1)
                    //Create a account
                    {
                        createAccount();
                    }
                    else if (option == 2)
                    {
                    //Login
                        System.out.println("Insert your information.");
                        String userNickname = console.readLine("Nickname: ");
                        String userPassword = console.readLine("Password: ");

                        if(findUser(userNickname, users))
                        {
                            if(validateLogin(userNickname, userPassword, users))
                            {
                                isLogged = !isLogged;

                                    for(User user: users)
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
                    //Exit
                        break;
                    }
                    else
                    {
                    //Error
                        System.out.println("Try again.");
                    }
                }
                catch (Exception InputMismatchException)
                {
                    System.out.println("Invalid option.");
                    userInput.nextLine();
                }
            }
            else
            {
                //User logged
                try
                {
                    menuLogged();
                    int option = userInput.nextInt();

                    if (option == 1)
                    {
                    //Send message to feed, user or community
                        sendMessage();
                    }
                    else if (option == 2)
                    {
                    //See user's feed
                        userLogged.seeFeed(feed);
                    }
                    else if (option == 3)
                    {
                    //See user's inbox
                        userLogged.getMessages();
                    }
                    else if (option == 4)
                    {
                    //User's profile info
                        userLogged.getInfo();
                    }
                    else if (option == 5)
                    {
                    //Update user's profile info
                        userLogged.updateProfile();
                    }
                    else if (option == 6)
                    {
                    //create or remove community
                        userLogged.addRemoveCommunity(communities);
                    }
                    else if (option == 7)
                    {
                    //Add or remove community members
                        userLogged.manageCommunity(communities);
                    }
                    else if(option == 8)
                    {
                    //See community messages
                        userLogged.getComMessages(communities);
                    }
                    else if (option == 9)
                    {
                    //Add or remove friends
                        addRemoveFriend();
                    }
                    else if (option == 10)
                    {
                    //Delete account
                        isLogged = deleteAccount(isLogged);
                    }
                    else if (option == 11)
                    {
                    //Exit
                        System.out.println("Logging out...");
                        isLogged = !isLogged;
                        userLogged = null;
                    }
                    else
                    {
                        System.out.println("Option not found. Try again.");
                    }
                    
                }
                catch (Exception InputMismatchException)
                {
                    System.out.println("Invalid option.");
                    userInput.nextLine();
                }
            }
        }
    }

    public static boolean findUser(String nickname, ArrayList<User> users)
    {
        for (User user: users)
        {
            if (user.getNick().equals(nickname))
            {
                return true;
            }
        }
        return false;
    }

    public static User getUser(String nickname, ArrayList<User> users)
    {
        User searchedUser = null;
        {
            for (User user: users)
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

    public static Community getCommunity(String comName, ArrayList<Community> communities)
    {
        Community searchedCom = null;

        for(Community community: communities)
        {
            if(community.getComName().equals(comName))
            {
                searchedCom = community;
                return searchedCom;
            }
        }

        return searchedCom;
    }

    public static void sendMessage()
    {
        Console console = System.console();
        
        System.out.println("1. To a user\n2. To a community\n3. To the feed");
        Scanner userInput = new Scanner(System.in);
        int sub_option = userInput.nextInt();

        if (sub_option == 1)
        {
            //Message to a user
            String destination = console.readLine("Insert the nickname of the user you want to send a message: ");

            if(findUser(destination, users))
            {
                String text = console.readLine("Write your message: ");
                Message message = new Message(userLogged.getNick(), text);
                User destinyUser = getUser(destination, users);
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
            Community com = getCommunity(destination, communities);

            if (com != null)
            {
                String text = console.readLine("Write your message: ");
                Message message = new Message(userLogged.getNick(), text);
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
            userLogged.sendMessagetoFeed(feed);
        }
        else
        {
            System.out.println("Insert valid option.");
        }
    }

    public static void addRemoveFriend()
    {
        System.out.println("1. See invites\n2. Add a friend");
        Scanner userInput = new Scanner(System.in);
        Console console = System.console();

        int sub_option = userInput.nextInt();

        if (sub_option == 1)
        {
            userLogged.checkFriendshipRequests();
        }
        else if (sub_option == 2)
        {
            String friendNick = console.readLine("Insert nickname: ");

            if(findUser(friendNick, users))
            {
                User invitedUser = getUser(friendNick, users);
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

    public static void createAccount()
    {
        Console console = System.console();

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
            User newUser = new User(userName, userPassword, userNickname);
            users.add(newUser);
            System.out.println("User created.");
        } 
    }

    public static boolean deleteAccount(boolean isLogged)
    {
        Console console = System.console();
        System.out.println("This action cannot be undone. Are you sure?");
        String answer = console.readLine();
        System.out.println("a");


        if (answer.equals("Yes") || answer.equals("yes") || answer.equals("y"))
        {
            System.out.println("b");
            userLogged.friends.clear();

            for(Community community: userLogged.userCommunites)
            {
                System.out.println("c");
                //User is owner of a community
                if(community.admin.getNick().equals(userLogged.getNick()))
                {
                    System.out.println(community.admin.getNick());
                    for(User user: community.members)
                    {
                        user.userCommunites.remove(community);
                    }
                    
                    communities.remove(community);
                    users.remove(userLogged);
                    userLogged = null;
                    isLogged = !isLogged;
                    System.out.println("e");
                    return isLogged;
                }
                else
                {
                    System.out.println("d");
                    community.members.remove(userLogged);
                }
            }
            
            users.remove(userLogged);
            userLogged = null;
            isLogged = !isLogged;
            System.out.println("e");
        }
        else if (answer.equals("No") || answer.equals("no") || answer.equals("n"))
        {
            System.out.println("Operation cancelled.");
        }

        System.out.println("g");
        return isLogged;
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
        System.out.println("Select one option bellow:");  
        System.out.println("1. Send a message\n" +
            "2. See feed\n" +
            "3. See inbox\n" +
            "4. Recover user information\n" +
            "5. Edit profile\n" +
            "6. Join/Create a community\n" + 
            "7. Manage communities\n" +
            "8. Community messages\n" +
            "9. Add friends\n" +
            "10. Remove account\n" +
            "11. Log out");

        System.out.println("------------------------------");

    }

    public static boolean validateLogin(String userNickname, String userPassword, ArrayList<User> users)
    {
        int result = 0;

        for (int i = 0; i < users.size(); i++)
        {
            User user = users.get((i));

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