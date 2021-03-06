package com.droidrbi.guessthecharacter.game

import android.os.CountDownTimer
import android.text.format.DateUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {


    companion object {

        // Time when the game is over
        private const val DONE = 0L

        // Countdown time interval
        private const val ONE_SECOND = 1000L

        // Total time for the game
        private const val COUNTDOWN_TIME = 60000L

    }

    private val timer: CountDownTimer
    // Countdown time
    private val _currentTime = MutableLiveData<Long>()
    val currentTime: LiveData<Long>
        get() = _currentTime


    private val _currentCharacter = MutableLiveData<Character>()

    val currentCharacter: LiveData<Character>
        get() = _currentCharacter

    private val _score = MutableLiveData<Int>()

    val score: LiveData<Int>
        get() = _score

    private val _gameFinished = MutableLiveData<Boolean>()

    val gameFinished: LiveData<Boolean>
        get() = _gameFinished


    // The String version of the current time
    val currentTimeString = Transformations.map(currentTime) { time ->
        DateUtils.formatElapsedTime(time)
    }
    private lateinit var _characterList: MutableList<Character>

    init {
        Log.i("GameViewModel", "GameViewModel created")

        _currentCharacter.value = Character("", "")
        _score.value = 0
        resetList()
        nextCharacter()
        // Creates a timer which triggers the end of the game when it finishes
        timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND) {

            override fun onTick(millisUntilFinished: Long) {
                _currentTime.value = millisUntilFinished / ONE_SECOND
            }

            override fun onFinish() {
                _currentTime.value = DONE
                onGameFinish()
            }
        }

        timer.start()
    }

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }

    /**
     * Resets the list of words and randomizes the order
     */
    private fun resetList() {
        _characterList = mutableListOf(
            Character(
                "Darth Vader",
                "I am Luke's father"
            ),
            Character(
                "Han Solo",
                "Millenium Falcon is my baby"
            ),
            Character(
                "Indiana Jones",
                "Professor of Archeology"
            ),
            Character(
                "Harry Potter",
                "I beat Voldemort when I was a baby"
            ),
            Character(
                "Ellen Ripley",
                "I ejected a Xenomorph into space"
            ),
            Character(
                "Elsa",
                "Let it go"
            )

        )
        _characterList.shuffle()
    }

    /**
     * Moves to the next word in the list.
     */
    private fun nextCharacter() {
        //Select and remove a word from the list
        if (!_characterList.isEmpty()) {
            _currentCharacter.value = _characterList.removeAt(0)
        } else {
            resetList()
        }
    }


    /** Methods for updating the UI **/
    fun onSkip() {
        nextCharacter()
    }

    fun onCorrect() {
        _score.value = score.value?.plus(1)
        nextCharacter()
    }

    fun isCorrect(name: String): Boolean {
        return name.equals(currentCharacter.value?.name, true)
    }

    fun onGameFinish() {
        _gameFinished.value = true
    }

    fun onGameFinishComplete() {
        _gameFinished.value = false
    }
}