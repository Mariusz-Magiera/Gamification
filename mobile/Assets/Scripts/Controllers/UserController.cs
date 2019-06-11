using System.Collections;
using System.Collections.Generic;
using System.Linq;
using TMPro;
using UnityEngine;
using UnityEngine.UI;
public class UserController : MonoBehaviour
{

    [SerializeField] TextMeshProUGUI NameTxt;
    [SerializeField] TextMeshProUGUI PointsTxt;
    [SerializeField] TextMeshProUGUI PermissionText;

    [SerializeField] Button AchievmentButton;
    [SerializeField] Button LinksButton;

    [SerializeField] ScrollRect AchievmentScroll;
    [SerializeField] ScrollRect LinksScroll;

    [SerializeField] AchievmentButton AchievmentPartToInstantiate;
    [SerializeField] LinkButton LinksPartToInstantiate;

    User user;

    private void Awake()
    {
        AchievmentButton.onClick.AddListener(SetAchievmentActive);
        LinksButton.onClick.AddListener(SetLinksActive);
    }

    private void OnEnable()
    {
        user = Net.instance.User(GameManager.instance.SelectedUser);
        NameTxt.text = user.name;
        PointsTxt.text = $"Points: {user.achievements.Sum(x => x.points)}";
        PermissionText.text = $"Permission: {Permission.StringName(user.permission)}";
    }

    private void SetAchievmentActive()
    {
        OnEnable();
        LinksScroll.gameObject.SetActive(false);
        AchievmentScroll.gameObject.SetActive(true);

        foreach (Transform old in AchievmentScroll.content)
        {
            Destroy(old.gameObject);
        }

        if (user == null || user.achievements == null)
            return;

        foreach (var item in user.achievements)
        {
            Instantiate(AchievmentPartToInstantiate, AchievmentScroll.content).Init(item);
        }
    }

    private void SetLinksActive()
    {
        OnEnable();
        AchievmentScroll.gameObject.SetActive(false);
        LinksScroll.gameObject.SetActive(true);

        foreach (Transform old in LinksScroll.content)
        {
            Destroy(old.gameObject);
        }

        if (user == null || user.links == null)
            return;

        foreach (var item in user.links)
        {
            Instantiate(LinksPartToInstantiate, LinksScroll.content).Init(item);
        }
    }
}
