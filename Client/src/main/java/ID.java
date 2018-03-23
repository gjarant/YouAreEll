public class ID {

    private String userid;
    private String name;
    private String github;

    public ID(String userid, String name, String github) {
        this.userid = userid;
        this.name = name;
        this.github = github;
    }

    public ID(String name, String github) {
        this.name = name;
        this.github = github;
    }

    public ID(){

    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    @Override
    public String toString(){
        return "UserID: " + userid + " Name: " + name + " Github: " + github;
    }
}
