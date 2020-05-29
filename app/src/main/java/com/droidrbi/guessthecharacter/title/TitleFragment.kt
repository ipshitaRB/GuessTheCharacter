package com.droidrbi.guessthecharacter.title

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.droidrbi.guessthecharacter.R
import com.droidrbi.guessthecharacter.databinding.FragmentTitleBinding


/**
 * A simple [Fragment] subclass.
 */
class TitleFragment : Fragment() {

    private lateinit var titleBinding: FragmentTitleBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        titleBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_title, container, false
        )

        return titleBinding.root
    }


}