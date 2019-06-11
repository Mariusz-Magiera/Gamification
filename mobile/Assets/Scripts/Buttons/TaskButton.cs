using System.Collections;
using System.Collections.Generic;
using TMPro;
using UnityEngine;

public class TaskButton : MonoBehaviour
{
    [SerializeField] TextMeshProUGUI description;
    [SerializeField] TextMeshProUGUI points;

    public void Init(Task task)
    {
        description.text = task.description;
        points.text = $"+{task.points}";
    }
}
