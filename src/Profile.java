import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.Console;

public class Profile extends Account 
{
    private String name;
    private String nickname;
    private String city;
    LocalDate bDay;
    DateTimeFormatter parser = DateTimeFormatter.ofPattern("[dd/MM/yyyy]");

    public Profile(String name, String nickname)
    {
        super(name, nickname); 
        this.name = name;
        this.nickname = nickname;
    }

    public void updateProfile() 
    {
        Console console = System.console();
        this.city = console.readLine("Please, insert your birth city: ");

        String birthday = console.readLine("Please, insert your birthday following the format DD/MM/YYYY: ");
        bDay = LocalDate.parse(birthday, parser);
    }
    
    public void getInfo(int numberFriends)
    {
        System.out.println("Name: " + this.name + "\n"
        + "Nickname: " + this.nickname + "\n"
        + "Friends: " + numberFriends + "\n"
        + "City: " + this.city + "\n"
        + "Date of Birth: " + parser.format(bDay));
    }
}
