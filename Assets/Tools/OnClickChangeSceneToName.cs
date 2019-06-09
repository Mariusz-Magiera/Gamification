using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;


public class OnClickChangeSceneToName : MonoBehaviour
{
    private void Start()
    {
        GetComponent<Button>().onClick.AddListener(OnClicked);

        void OnClicked()
        {
            if (transform.name == "Exit")
            {
                Application.Quit();
            }
            else
            {
                SceneManager.LoadScene(transform.name);
            }
        }
    }
}
