using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class MoveBackground : MonoBehaviour
{
    // Start is called before the first frame update
    public Vector3 speed;
    void Start()
    {
        this.GetComponent<Rigidbody2D>().velocity = speed;
    }

    // Update is called once per frame
    void Update()
    {
        
    }
}
