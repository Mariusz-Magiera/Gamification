using System.Collections;
using System.Collections.Generic;
using TMPro;
using UnityEngine;
using UnityEngine.UI;


public class ConfigController : MonoBehaviour
{
    [SerializeField] TMP_InputField ipField;
    [SerializeField] TMP_InputField portField;
    [SerializeField] Button acceptButton;

    private void OnEnable()
    {
        ipField.text = Net.instance.Ip;
        portField.text = Net.instance.Port;

        acceptButton.onClick.AddListener(SetNewAdress);

        void SetNewAdress()
        {
            Net.instance.Ip = ipField.text;
            Net.instance.Port = portField.text;
        }
    }
}
