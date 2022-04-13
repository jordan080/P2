public class Profile 
{
    String nickname;
    String city;
    String birthday;

    public Profile(String nickname, String city, String birthday)
    {
        this.nickname = nickname;
        this.city = city;
        this.birthday = birthday;       
    }

    public void updateProfile(String city, String birthday)
    {
        this.city = city;
        this.birthday = birthday;
    }   
}
