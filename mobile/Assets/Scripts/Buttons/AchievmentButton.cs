using System.Collections;
using System.Collections.Generic;
using TMPro;
using UnityEngine;

public class AchievmentButton : MonoBehaviour
{
    [SerializeField] TextMeshProUGUI description;
    [SerializeField] TextMeshProUGUI points;

    public void Init(Achievement achievment)
    {
        description.text = achievment.description;
        points.text = $"+{achievment.points}";
    }
}
