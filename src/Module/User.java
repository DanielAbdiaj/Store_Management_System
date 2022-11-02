package Module;

import java.io.Serializable;

public class User implements Serializable
{
    String username;
    String password;
    int ID;
    Role role;

    String roleName;

    public User()
    {
        ID = getNrOfUsers() + 1;
        role = new Role();
        role.setName("");
        roleName = "";
    }

    public static class Role implements Serializable
    {
        String name;//This is RoleName it can be Cashier Manager Admin

        public String getName() { return name; }
        public void setName(String name)
        {
            this.name = name;
        }
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public int getID() { return ID; }


    public String getRoleName()
    {
        roleName = role.getName();
        return roleName;
    }
    public void setRole(String name) { role.setName(name); }

    int getNrOfUsers()
    {
        int totalNrOfUsers = 0;
        for(Cashier c : DataBase.getCashiers())
            totalNrOfUsers++;
        for(Manager e : DataBase.getManager())
            totalNrOfUsers++;
        return totalNrOfUsers;
    }
}
