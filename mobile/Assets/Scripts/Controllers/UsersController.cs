using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using System.Linq;
using UnityEngine.SceneManagement;

public class UsersController : MonoBehaviour
{
    [SerializeField] Transform parentToObjects;
    [SerializeField] Button UserButton;
    private void OnEnable()
    {
        var users = Net.instance.Users();
        users = users.OrderByDescending(x => x.achievements.Sum(y => y.points)).ToList();

        foreach (Transform oldUser in parentToObjects.transform)
        {
            Destroy(oldUser.gameObject);
        }
    
        foreach (var user in users)
        {
            var obj = Instantiate(UserButton, parentToObjects);
            obj.name = user.name;
            obj.gameObject.SetActive(false);
            obj.gameObject.SetActive(true);

            obj.GetComponent<Button>().onClick.AddListener(() => OnButtonClicked(user.id));
        }

        void OnButtonClicked(int id)
        {
            GameManager.instance.SelectedUser = id;
            SceneManager.LoadScene("User");
        }
        
    }
}
