package com.droidrbi.guessthecharacter.score

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.droidrbi.guessthecharacter.R
import com.droidrbi.guessthecharacter.databinding.FragmentScoreBinding

/**
 * A simple [Fragment] subclass.
 */
class ScoreFragment : Fragment() {

    private lateinit var _binding: FragmentScoreBinding

    private lateinit var _viewModel: ScoreViewModel
    private lateinit var _viewModelFactory: ScoreViewModelFactory

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

        _binding.scoreTextView.text = _viewModel.score.toString()

        return _binding.root
    }

}
