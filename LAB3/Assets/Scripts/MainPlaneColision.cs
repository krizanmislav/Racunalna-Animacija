using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class MainPlaneColision : MonoBehaviour
{


    //tamo di je skripta koja prikazuje hearts
    public GameObject canvas;


    private Stats values;
    private ShowLives show;

    // Start is called before the first frame update
    void Start()
    {
        values = this.GetComponent<Stats>();
        show = canvas.GetComponent<ShowLives>();
    }


    private void OnTriggerEnter2D(Collider2D collision) {
        GameObject enem = collision.gameObject;
        if(enem.CompareTag("Enemy")){
            Destroy(enem);
            values.addLives(-1);
            show.changeHearts();
        }


    }
}
