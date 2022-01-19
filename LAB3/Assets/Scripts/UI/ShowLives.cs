using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ShowLives : MonoBehaviour
{

    public GameObject player;

    public GameObject heartsPrefab;
    
    private Stats values;

    List<GameObject> hearts;

    // Start is called before the first frame update
    void Start()
    {
        hearts = new List<GameObject>();

        values = player.GetComponent<Stats>();

        changeHearts();
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    public void changeHearts(){
        int lives = values.getLives();

        if(hearts.Count > 0){
            for(int i = 0; i < hearts.Count; i++){
                Destroy(hearts[i]);
            }
            hearts.Clear();
        }

        for(int i = 0; i < lives; i++){
            GameObject heart = Instantiate(heartsPrefab, heartsPrefab.transform.position + this.transform.position + Vector3.right * i, this.transform.rotation);
            heart.transform.parent = this.transform;
            
            hearts.Add(heart);
            
        }

    }
}
