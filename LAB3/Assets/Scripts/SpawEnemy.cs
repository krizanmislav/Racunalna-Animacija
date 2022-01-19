using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class SpawEnemy : MonoBehaviour
{

    public GameObject enemyPrefab;

    public int brojEnemija = 10;
    private int trenutnoEnemija = 0;

    public float spawnSeconds = 3;
    private float trenutniSeconds;

    // Start is called before the first frame update
    void Start()
    {
        trenutniSeconds = spawnSeconds;
    }

    // Update is called once per frame
    void Update()
    {

        if(trenutnoEnemija < brojEnemija){
            //spawnaj enemy
            trenutniSeconds -= 1 * Time.deltaTime;

            if(trenutniSeconds <= 0){
                
                Instantiate(enemyPrefab, this.transform.position + (new Vector3(Random.Range(-5,5), Random.Range(-5,5),0)), transform.rotation);
                
                trenutnoEnemija++;
                trenutniSeconds = spawnSeconds;
            }


        }
        
        
    }
}
