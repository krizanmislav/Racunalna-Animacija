using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ScoreText : MonoBehaviour
{

    public TMPro.TextMeshProUGUI textValue;

    public GameObject player;
    
    private Stats values;

    // Start is called before the first frame update
    void Start()
    {
        values = player.GetComponent<Stats>();
    }

    // Update is called once per frame
    void Update()
    {

        textValue.text = "SCORE: " + values.getScore();
    }
}
