package com.droidrbi.guessthecharacter.game

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.droidrbi.guessthecharacter.R

/**
 * A simple [Fragment] subclass.
 */
class GameFragment : Fragment() {

    private lateinit var _viewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _viewModel = ViewModelProvider(this).get(GameViewModel::class.java)
        Log.i("GameFragment", "ViewModelProvider called")
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

}
