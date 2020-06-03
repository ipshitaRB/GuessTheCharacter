package com.droidrbi.guessthecharacter.score

import android.util.Log
import androidx.lifecycle.ViewModel

class ScoreViewModel(finalScore: Int) : ViewModel() {
    var score = finalScore

    init {
        Log.i("ScoreViewModel", "ScoreViewModel created")
    }
}