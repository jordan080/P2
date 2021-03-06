import java.io.Console;
import java.util.ArrayList;
import java.util.Scanner;

class Main
{
    static User userLogged = null;
    static Network iFace = new Network();
    static ArrayList<User> users = iFace.getUsers();
    static ArrayList<Community> communities = iFace.getComs();

    static Feed feed = iFace.getFeed();
    static Utils utils = new Utils(users, communities);
    static Scanner userInput = new Scanner(System.in);
    static Console console = System.console();
    static Controller controller = new Controller(feed, utils, communities);

    public static void main(String[] args) 
    {
        boolean isLogged = false;
        try
        {  
            while(true)
            {
                if (!isLogged)
                {
                    menuNotLogged();

                    try
                    {
                        int option = userInput.nextInt();

                        if (option == 1)
                        {
                            createAccount();
                        }
                        else if (option == 2)
                        {
                            System.out.println("Logging in the network.");
                            String userNickname = console.readLine("Nickname: ");
                            String userPassword = console.readLine("Password: ");

                            if(Utils.findUser(userNickname, users))
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
                            System.out.println("Exiting...");
                            break;
                        }
                        else
                        {
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
                    controller.setUsers(users);
                    Scanner userInput = new Scanner(System.in);
                    try
                    {
                        controller.setUserLogged(userLogged);
                        controller.setOptions();
                        menuLogged();
                        int option = userInput.nextInt();

                        if (option <= 9)
                        {
                            controller.executeCommand(option);
                        }
                        else if (option == 10)
                        {
                            isLogged = deleteAccount(isLogged);
                        }
                        else if (option == 11)
                        {
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
        finally
        {
            userInput.close();
        }
    }

    public static void createAccount()
    {
        System.out.println("Creating account");
        String userName = console.readLine("Insert your name: ");
        String userNickname = console.readLine("Insert your a nickname: ");
        String userPassword = console.readLine("Insert your password: ");

        if(Utils.findUser(userNickname, users))
        {
            System.out.println("This user was already created.");
        }
        else
        {
            ArrayList<Community> userCommunites = new ArrayList<Community>();
            User newUser = new User(userName, userPassword, userNickname, userCommunites);
            users.add(newUser);
            System.out.println("User created.");
        } 
    }

    public static boolean deleteAccount(boolean isLogged)
    {
        System.out.println("This action cannot be undone. Are you sure?");
        String answer = console.readLine();

        if (answer.equals("Yes") || answer.equals("yes") || answer.equals("y"))
        {
            userLogged.friends.clear();

            for(Community community: userLogged.userCommunites)
            {
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
                    return isLogged;
                }
                else
                {
                    community.members.remove(userLogged);
                }
            }
            
            users.remove(userLogged);
            userLogged = null;
            isLogged = !isLogged;
        }
        else if (answer.equals("No") || answer.equals("no") || answer.equals("n"))
        {
            System.out.println("Operation cancelled.");
        }

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