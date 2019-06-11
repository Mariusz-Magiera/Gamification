using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class LoginController : MonoBehaviour
{
    [SerializeField] bool IsMenuPanel;
    [SerializeField] Button user;
    [SerializeField] Button login;
    [SerializeField] Button register;
    [SerializeField] ScrollRect loginScroll;
    [SerializeField] ScrollRect registerScroll;
    private void Start()
    {
        if(IsMenuPanel)
        {
            OnLoginAction();
            //GameManager.instance.OnLoginAction.AddListener(OnLoginAction);
        }
        else
        {
            SetLoginActive();
            login.onClick.AddListener(SetLoginActive);
            register.onClick.AddListener(SetRegisterActive);
        }
    }

    private void OnLoginAction()
    {
        if (!this || !isActiveAndEnabled)
            return;

        var userId = GameManager.instance.LoggedUser;
        if (userId < 0)
        {
            user.gameObject.SetActive(false);
            login.gameObject.SetActive(true);
        }
        else
        {
            user.gameObject.SetActive(false);
            user.name = Net.instance.User(userId).name;
            user.gameObject.SetActive(true);
            login.gameObject.SetActive(true);
        }
    }

    private void SetLoginActive()
    {
        registerScroll.gameObject.SetActive(false);
        loginScroll.gameObject.SetActive(true);
    }

    private void SetRegisterActive()
    {
        loginScroll.gameObject.SetActive(false);
        registerScroll.gameObject.SetActive(true);
    }
}
