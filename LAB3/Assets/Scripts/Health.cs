using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Health : MonoBehaviour
{
    public float startHealth = 1;
    private float health;


    private Stats values;


    // Start is called before the first frame update
    void Start()
    {
        health = startHealth;

        values = GameObject.FindWithTag("Player").GetComponent<Stats>();
    }

    // Update is called once per frame
    void Update()
    {
        if(health <= 0){
            Destroy(this.gameObject);

            //povaecaj score
            values.increaseScore(1);

        }
    }

    public float smanjiHealth(float dmg){
        health = health - dmg;
        return health;
    }
}
