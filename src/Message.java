class Message
{
    private String message;
    private String userNick;

    public Message(String userNick, String message)
    {
        this.userNick = userNick;
        this.message = message;
    }

    public String getAuthor()
    {
        return userNick;
    }

    public String getMessage()
    {
        return message;
    }
}
