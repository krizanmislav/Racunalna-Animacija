using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class Stats : MonoBehaviour
{


    private int lives = 3;
    private int score = 0;

    private int amountForLevelUp = 25;
    private int level = 0;
    public void addLives(int value){
        lives += value;

        //ako su zivoti ispod 0 onda resetaj ili nest
        if(lives <= 0){
            Debug.Log("IZGUBIO");
            SceneManager.LoadScene(SceneManager.GetActiveScene().name);
        }
    }

    public int getLives(){
        return lives;
    }

    public int getScore(){
        return score;
    }

    public void increaseScore(int value){
        score+=value;
        
        //da poveca level
        if(score >= ((level+1)*amountForLevelUp)){
            addLevel(1);
        }
    }

    public int getLevel(){
        return level;
    }

    public void addLevel(int val){
        level += val;
    }
}
