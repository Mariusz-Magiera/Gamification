using System.Collections;
using System.Collections.Generic;
using TMPro;
using UnityEngine;
using UnityEngine.UI;

public class LinkButton : MonoBehaviour
{
    public void Init(ProfileLink link)
    {
        GetComponentInChildren<TextMeshProUGUI>().text = link.url;
        GetComponentInChildren<Button>().onClick.AddListener(() => Application.OpenURL(link.url));
    }
}
