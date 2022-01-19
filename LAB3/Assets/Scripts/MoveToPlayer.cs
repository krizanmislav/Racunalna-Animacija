using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class MoveToPlayer : MonoBehaviour
{

    private Transform playerVal;

    public float speed = 1.0f;

    // Start is called before the first frame update
    void Start()
    {
        playerVal = GameObject.FindWithTag("Player").transform;
    }

    // Update is called once per frame
    void Update()
    {
        Vector3 smjer = playerVal.position - transform.position;
        smjer.Normalize();

        transform.position += smjer * speed * Time.deltaTime;
    }
}
