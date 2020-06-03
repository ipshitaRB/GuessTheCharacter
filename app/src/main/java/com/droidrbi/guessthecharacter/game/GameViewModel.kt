package com.droidrbi.guessthecharacter.game

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    val currentCharacter = MutableLiveData<Character>()

    val score = MutableLiveData<Int>()


    private lateinit var _characterList: MutableList<Character>

    init {
        Log.i("GameViewModel", "GameViewModel created")
        currentCharacter.value = Character("", "")
        score.value = 0
        resetList()
        nextCharacter()
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel", "OnCleared called - GameViewModel Destroyed!!!")
    }

    /**
     * Resets the list of words and randomizes the order
     */
    private fun resetList() {
        _characterList = mutableListOf(
            Character("Darth Vader", "I am Luke's father"),
            Character("Han Solo", "Millenium Falcon is my most prized possession"),
            Character("Indian Jones", "I can not do without my whip and fedora"),
            Character("Harry Potter", "I beat Voldemort when I was a baby"),
            Character(
                "Ellen Ripley",
                "I am the sole survivor of the Nostromo spaceship in the movie Alien"
            ),
            Character("Mickey Mouse", "Walt Disney created me and I have large ears like a mouse")

        )
        _characterList.shuffle()
    }

    /**
     * Moves to the next word in the list.
     */
    private fun nextCharacter() {
        //Select and remove a word from the list
        if (!_characterList.isEmpty()) {
            currentCharacter.value = _characterList.removeAt(0)
        }
    }


    /** Methods for updating the UI **/
    fun onSkip() {
        nextCharacter()
    }

    fun onCorrect() {
        score.value = score.value?.plus(1)
        nextCharacter()
    }

    fun isCorrect(name: String): Boolean {
        return name.equals(currentCharacter.value?.name, true)
    }
}