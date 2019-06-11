using System.Collections;
using System.Collections.Generic;
using System.Linq;
using UnityEngine;
using UnityEngine.Events;
using UnityEngine.SceneManagement;

public class GameManager : MonoBehaviour
{
    public static GameManager instance;

    public int SelectedUser;
    public int SelectedProject;

    public int LoggedUser { get; private set; } = -1;
    public string UserName { get; private set; }
    public string UserPassword { get; private set; }

    public UnityEvent OnLoginAction;

    private void Awake()
    {
        if (instance)
        {
            Debug.LogError("Only one instance of Net should exist!");
            return;
        }
        instance = this;
        DontDestroyOnLoad(gameObject);
        SceneManager.LoadScene("Menu");
    }

    public void Login(string name, string password)
    {
        if(Net.instance.TryAuthorize(name, password))
        {
            var user = Net.instance.Users().FirstOrDefault(x => x.name == name);
            if(user != null)
            {
                LoggedUser = user.id;
                SelectedUser = LoggedUser;
                SceneManager.LoadScene("User");
            }
            else
            {
                LoggedUser = -1;
            }
        }
        else
        {
            SceneManager.LoadScene("Login");
        }
        OnLoginAction.Invoke();
    }

    public void Register(string name, string password)
    {
        if(Net.instance.AddUser(name, password) != null)
        {
            Login(name, password);
        }
        else
        {
            SceneManager.LoadScene("Login");
        }

    }

}
