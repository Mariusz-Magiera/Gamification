using System;

[Serializable]
public class Permission
{
    Permission(int value) => type = value;
    public int type;

    public static Permission USER = new Permission(1);
    public static Permission MOD = new Permission(2);
    public static Permission ADMIN = new Permission(3);

    public static string StringName(Permission permission)
    {
        switch (permission.type)
        {
            case 1:
                return "User";
                break;
            case 2:
                return "Moderator";
                break;
            case 3:
                return "Administrator";
                break;
            default:
                return "Unknow";
                break;
        }
    }
}