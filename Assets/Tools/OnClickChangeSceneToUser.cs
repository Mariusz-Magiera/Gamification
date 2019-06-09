using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

public class OnClickChangeSceneToUser : MonoBehaviour
{
    private void Start()
    {
        GetComponent<Button>().onClick.AddListener(OnClicked);

        void OnClicked()
        {
            GameManager.instance.SelectedUser = GameManager.instance.LoggedUser;
            SceneManager.LoadScene("User");
        }
    }
}
