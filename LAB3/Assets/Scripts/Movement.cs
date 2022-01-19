using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Movement : MonoBehaviour
{

    Rigidbody2D contr;
    public float force;


    // Start is called before the first frame update
    void Start()
    {
        contr = GetComponent<Rigidbody2D>();
        
    }

    // Update is called once per frame
    void Update()
    {

        if(Input.GetKey(KeyCode.A)){
            contr.AddForce(Vector2.left * force, ForceMode2D.Impulse);
        }
        if(Input.GetKey(KeyCode.D)){
            contr.AddForce(Vector2.right * force, ForceMode2D.Impulse);
        }
        if(Input.GetKey(KeyCode.W)){
            contr.AddForce(Vector2.up * force, ForceMode2D.Impulse);
        }
        if(Input.GetKey(KeyCode.S)){
            contr.AddForce(Vector2.down * force, ForceMode2D.Impulse);
        }
        if(Input.GetKey(KeyCode.E)){
            transform.rotation *= Quaternion.Euler(Vector3.forward * -1);
        }

        if(Input.GetKey(KeyCode.Q)){
            transform.rotation *= Quaternion.Euler(Vector3.forward * 1);
        }

        transform.position = new Vector3(Mathf.Clamp(transform.position.x,-10f,10f), Mathf.Clamp(transform.position.y,-4.3f,4.3f),transform.position.y);


    }
}
