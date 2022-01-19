using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ProjectileScript : MonoBehaviour
{

    public float dmg = 1.0f;

    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    //ako se coliida onda smanji health
    private void OnTriggerEnter2D(Collider2D collision) {
        //Debug.Log("COl");
        GameObject enem = collision.gameObject;
        if(enem.CompareTag("Projectile")){
            //Debug.Log("EnemyCol");
            Destroy(enem);
            this.GetComponent<Health>().smanjiHealth(this.dmg);
            
        }


    }

}
