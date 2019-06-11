using System;
using System.Collections;
using System.Collections.Generic;
using System.Collections.Specialized;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Text;
using UnityEngine;
using UnityEngine.Networking;

public class Net : MonoBehaviour
{
    public static Net instance;

    public string Ip { get; set; } = "35.238.239.157";
    public string Port { get; set; } = "8888";
    public string Address => "http://" + Ip + ":" + Port;

    private void Awake()
    {
        if (instance)
        {
            Debug.LogError("Only one instance of Net should exist!");
            return;
        }
        instance = this;
        DontDestroyOnLoad(gameObject);
    }

    public List<User> Users()
    {
        var json = new WebClient().DownloadString($"{Address}/users");

        if (json.ToLower() == "null")
            return null;

        json = "{\"List\":" + json + "}";

        return JsonUtility.FromJson<Users>(json).List;
    }

    public List<Project> Projects()
    {
        var json = new WebClient().DownloadString($"{Address}/projects");

        if (json.ToLower() == "null")
            return null;

        json = "{\"List\":" + json + "}";

        return JsonUtility.FromJson<Projects>(json).List;
    }

    public User User(int id)
    {
        var json = new WebClient().DownloadString($"{Address}/users/id/{id}");

        if (json.ToLower() == "null")
            return null;

        return JsonUtility.FromJson<User>(json);
    }

    public Project Project(int id)
    {
        var json = new WebClient().DownloadString($"{Address}/projects/id/{id}");

        if (json.ToLower() == "null")
            return null;

        return JsonUtility.FromJson<Project>(json);
    }

    public bool AddProject(Project project)
    {
        string jsonName = $"{{\"name\": \"{project.name}\"}}";

        var client = new WebClient();
        client.Headers[HttpRequestHeader.ContentType] = "application/json";
        string response = client.UploadString($"{Address}/projects/add", jsonName);
       
        return response == "true" ? true : false;
    }

    public string AddUser(string name, string password)
    {
        var json = JsonUtility.ToJson(new UserRegister(name, password, Permission.USER));

        try
        {
            var client = new WebClient();
            client.Headers[HttpRequestHeader.ContentType] = "application/json";
            return client.UploadString($"{Address}/users/add", json);
        }
        catch
        {
            return null;
        }

    }

    public bool TryAuthorize(string name, string password)
    {
        var json = JsonUtility.ToJson(new UserAuthorize(name,password));

        var client = new WebClient();
        client.Headers[HttpRequestHeader.ContentType] = "application/json";
        string response = client.UploadString($"{Address}/authorize", json);

        return response == "true" ? true : false;
    }


}

