using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlayGame : MonoBehaviour
{

    public static bool isPaused = true;

    public GameObject gamePanel;

    void Start(){
        pauseGame();
    }

    // Update is called once per frame
    void Update()
    {
        if(Input.GetKeyDown(KeyCode.Escape)){
            if(isPaused){
                resumeGame();
            }else{
                pauseGame();
            }
        }
    }

    public void resumeGame(){
        gamePanel.SetActive(false);
        Time.timeScale = 1.0f;
        isPaused = false;
    }

    void pauseGame(){
        gamePanel.SetActive(true);
        Time.timeScale = 0f;
        isPaused = true;
    }
}
