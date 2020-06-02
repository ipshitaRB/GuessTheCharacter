package com.droidrbi.guessthecharacter.game

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.droidrbi.guessthecharacter.R
import com.droidrbi.guessthecharacter.databinding.FragmentGameBinding

/**
 * A simple [Fragment] subclass.
 */
class GameFragment : Fragment() {

    private lateinit var _viewModel: GameViewModel

    private lateinit var _binding: FragmentGameBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate view and obtain an instance of the binding class
        _binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_game,
            container,
            false
        )
        _viewModel = ViewModelProvider(this).get(GameViewModel::class.java)
        Log.i("GameFragment", "ViewModelProvider called")

        _binding.submitButton.setOnClickListener { onSubmit() }
        _binding.skipButton.setOnClickListener { onSkip() }
        _binding.endGameButton.setOnClickListener { onEnd() }

        updateCharacterHint()
        updateScoreText()
        // Inflate the layout for this fragment
        return _binding.root
    }

    private fun onEnd() {

    }

    private fun onSkip() {
        _viewModel.onSkip()
        updateCharacterHint()
    }

    private fun onSubmit() {
        if (_viewModel.isCorrect(_binding.characterEditText.text.toString())) {
            _viewModel.onCorrect()
            Toast.makeText(context, getString(R.string.correct_answer), Toast.LENGTH_SHORT).show()
            updateCharacterHint()
            updateScoreText()
        } else {
            Toast.makeText(context, getString(R.string.try_again), Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateScoreText() {
        _binding.scoreTextView.text = getString(R.string.score, _viewModel.score)
    }

    private fun updateCharacterHint() {
        _binding.characterEditText.text?.clear()
        _binding.textInputLayout.hint = getString(R.string.hint, _viewModel.currentCharacter.hint)
    }

}
