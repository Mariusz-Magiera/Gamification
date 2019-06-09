using System.Collections;
using System.Collections.Generic;
using System.Linq;
using TMPro;
using UnityEngine;
using UnityEngine.UI;

public class ProjectController : MonoBehaviour
{
    [SerializeField] TextMeshProUGUI NameTxt;
    [SerializeField] TextMeshProUGUI DescriptionTxt;

    [SerializeField] Button AchievmentButton;
    [SerializeField] Button TasksButton;

    [SerializeField] ScrollRect AchievmentScroll;
    [SerializeField] ScrollRect TasksScroll;

    [SerializeField] AchievmentButton AchievmentPartToInstantiate;
    [SerializeField] TaskButton TasksPartToInstantiate;

    Project project;
    private void Awake()
    {
        AchievmentButton.onClick.AddListener(SetAchievmentActive);
        TasksButton.onClick.AddListener(SetTasksActive);
    }

    private void OnEnable()
    {
        project = Net.instance.Project(GameManager.instance.SelectedProject);
        NameTxt.text = project.name;
        DescriptionTxt.text = project.description;
    }

    private void SetAchievmentActive()
    {
        OnEnable();
        TasksScroll.gameObject.SetActive(false);
        AchievmentScroll.gameObject.SetActive(true);

        foreach (Transform old in AchievmentScroll.content)
        {
            Destroy(old.gameObject);
        }

        if (project == null || project.achievements == null)
            return;

        foreach (var item in project.achievements)
        {
            Instantiate(AchievmentPartToInstantiate, AchievmentScroll.content).Init(item);
        }
    }

    private void SetTasksActive()
    {
        OnEnable();
        AchievmentScroll.gameObject.SetActive(false);
        TasksScroll.gameObject.SetActive(true);

        foreach (Transform old in TasksScroll.content)
        {
            Destroy(old.gameObject);
        }

        if (project == null || project.tasks == null)
            return;

        foreach (var item in project.tasks)
        {
            Instantiate(TasksPartToInstantiate, TasksScroll.content).Init(item);
        }
    }
}
