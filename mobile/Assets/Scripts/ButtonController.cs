using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class ButtonController : MonoBehaviour
{
    GameObject objectToActivate;
    private void Awake()
    {
        GetComponent<Button>().onClick.AddListener(Activate);

        void Activate()
        {
            objectToActivate.SetActive(false);
            objectToActivate.SetActive(true);
            objectToActivate.transform.SetAsFirstSibling();
        }
    }
}
