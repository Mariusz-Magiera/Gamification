using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using System.Linq;
using UnityEngine.SceneManagement;

public class ProjectsController : MonoBehaviour
{
    [SerializeField] Transform parentToObjects;
    [SerializeField] Button ProjectButton;
    private void OnEnable()
    {
        var projects = Net.instance.Projects();
        projects = projects.OrderByDescending(x => x.achievements.Sum(y => y.points)).ToList();

        foreach (Transform oldUser in parentToObjects.transform)
        {
            Destroy(oldUser.gameObject);
        }
    
        foreach (var project in projects)
        {
            var obj = Instantiate(ProjectButton, parentToObjects);
            obj.name = project.name;
            obj.gameObject.SetActive(false);
            obj.gameObject.SetActive(true);
            obj.GetComponent<Button>().onClick.AddListener(() => OnButtonClicked(project.id));
        }

        void OnButtonClicked(int id)
        {
            GameManager.instance.SelectedProject = id;
            SceneManager.LoadScene("Project");
        }
    }
}
