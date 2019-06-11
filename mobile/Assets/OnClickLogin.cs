using System.Collections;
using System.Collections.Generic;
using TMPro;
using UnityEngine;
using UnityEngine.UI;

public class OnClickLogin : MonoBehaviour
{
    public enum Mode { login, register}
    [SerializeField] Mode mode;
    [SerializeField] TMP_InputField name;
    [SerializeField] TMP_InputField password;
    private void Start()
    {
        switch (mode)
        {
            case Mode.login:
                GetComponent<Button>().onClick.AddListener(StartLogin);
                break;
            case Mode.register:
                GetComponent<Button>().onClick.AddListener(StartRegister);
                break;
            default:
                break;
        }

    }

    private void StartLogin()
    {
        if(name.text != "" && password.text != "")
            GameManager.instance.Login(name.text, password.text);
    }

    private void StartRegister()
    {
        if (name.text != "" && password.text != "")
            GameManager.instance.Register(name.text, password.text);
    }
}
