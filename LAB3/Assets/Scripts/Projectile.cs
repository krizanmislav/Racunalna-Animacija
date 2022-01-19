using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Projectile : MonoBehaviour
{


    public GameObject projectile;
    public float projectileSpeed;

    private Stats values;

    // Start is called before the first frame update
    void Start()
    {
        values = this.GetComponent<Stats>();
        
    }

    // Update is called once per frame
    void Update()
    {

        //temporary
        if(Input.GetKeyDown(KeyCode.L)){
            values.addLevel(1);
        }

        //shoot
        if(Input.GetKeyDown(KeyCode.Space)){
            //stvori projektil s velociti gore
            GameObject proj = Instantiate(projectile,transform.position + Vector3.left * 0.5f, transform.rotation);
            Rigidbody2D rb = proj.GetComponent<Rigidbody2D>();
            rb.velocity = transform.TransformDirection(Vector2.up * projectileSpeed);

            GameObject proj2 = Instantiate(projectile,transform.position + Vector3.right * 0.5f, transform.rotation);
            Rigidbody2D rb2 = proj2.GetComponent<Rigidbody2D>();
            rb2.velocity = transform.TransformDirection(Vector2.up * projectileSpeed);


            if(values.getLevel() >= 1){
                proj = Instantiate(projectile,transform.position + Vector3.left * 0.2f, transform.rotation);
                rb = proj.GetComponent<Rigidbody2D>();
                rb.velocity = transform.TransformDirection(Vector2.up * projectileSpeed);

                proj2 = Instantiate(projectile,transform.position + Vector3.right * 0.2f, transform.rotation);
                rb2 = proj2.GetComponent<Rigidbody2D>();
                rb2.velocity = transform.TransformDirection(Vector2.up * projectileSpeed);
            }


            if(values.getLevel() >= 2){
                proj = Instantiate(projectile,transform.position + Vector3.left * 0.3f, transform.rotation);
                rb = proj.GetComponent<Rigidbody2D>();
                proj.transform.rotation = Quaternion.Euler(Vector3.forward * 45);
                rb.velocity = transform.TransformDirection(new Vector3(-1,1) * projectileSpeed);
                

                proj2 = Instantiate(projectile,transform.position + Vector3.right * 0.3f, transform.rotation);
                rb2 = proj2.GetComponent<Rigidbody2D>();
                proj2.transform.rotation = Quaternion.Euler(Vector3.forward * -45);
                rb2.velocity = transform.TransformDirection(new Vector3(1,1) * projectileSpeed);
                
            }

            if(values.getLevel() >= 3){
                proj = Instantiate(projectile,transform.position + Vector3.left * 0.3f, transform.rotation);
                rb = proj.GetComponent<Rigidbody2D>();
                proj.transform.rotation = Quaternion.Euler(Vector3.forward * 30);
                rb.velocity = transform.TransformDirection(new Vector3(-0.75f,1) * projectileSpeed);
                

                proj2 = Instantiate(projectile,transform.position + Vector3.right * 0.3f, transform.rotation);
                rb2 = proj2.GetComponent<Rigidbody2D>();
                proj2.transform.rotation = Quaternion.Euler(Vector3.forward * -30);
                rb2.velocity = transform.TransformDirection(new Vector3(0.75f,1) * projectileSpeed);
            }


        }
    }
}
