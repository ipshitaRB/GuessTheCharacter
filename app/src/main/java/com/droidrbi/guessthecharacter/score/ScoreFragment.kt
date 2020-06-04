package com.droidrbi.guessthecharacter.score

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.droidrbi.guessthecharacter.R
import com.droidrbi.guessthecharacter.databinding.FragmentScoreBinding

/**
 * A simple [Fragment] subclass.
 */
class ScoreFragment : Fragment() {

    private lateinit var _binding: FragmentScoreBinding

    private lateinit var _viewModel: ScoreViewModel
    private lateinit var _viewModelFactory: ScoreViewModelFactory

    private lateinit var _navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_score,
            container,
            false
        )

        val args = ScoreFragmentArgs.fromBundle(requireArguments())
        _viewModelFactory = ScoreViewModelFactory(args.score)
        _viewModel = ViewModelProvider(this, _viewModelFactory)
            .get(ScoreViewModel::class.java)

        _viewModel.score.observe(viewLifecycleOwner, Observer { newScore ->
            _binding.scoreTextView.text = newScore.toString()
        })

        _binding.playAgainbutton.setOnClickListener { playAgain() }
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _navController = Navigation.findNavController(view)
    }

    private fun playAgain() {
        _navController.navigate(ScoreFragmentDirections.actionScoreFragmentToGameFragment())
    }

}
