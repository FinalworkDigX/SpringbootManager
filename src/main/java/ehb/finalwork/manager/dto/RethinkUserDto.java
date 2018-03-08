package ehb.finalwork.manager.dto;

public class RethinkUserDto {

    private String username;
    private String password;
    private String channel;

    public RethinkUserDto() {
    }

    public RethinkUserDto(String username, String password, String channel) {
        this.username = username;
        this.password = password;
        this.channel = channel;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }
}
