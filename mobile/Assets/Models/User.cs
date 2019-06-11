using System;
using System.Collections.Generic;

[System.Serializable]
public class User
{
    public int id;
    public Permission permission;
    public string name;
    public List<ProfileLink> links;
    public List<Achievement> achievements;
}

[System.Serializable]
public class Users
{
    public List<User> List = new List<User>();
}

[System.Serializable]
public class UserAuthorize
{
    public UserAuthorize(string name, string password)
    {
        this.name = name;
        this.password = password;
    }

    public string name;
    public string password;
}


[System.Serializable]
public class UserRegister
{
    public UserRegister(string name, string password, Permission permission)
    {
        this.name = name;
        this.password = password;
        this.permission = permission;
    }

    public string name;
    public string password;
    public Permission permission;
}