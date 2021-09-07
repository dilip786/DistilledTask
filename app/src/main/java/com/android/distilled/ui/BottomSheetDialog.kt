package com.android.distilled.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.distilled.databinding.BottomMenuBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetDialog(val onClick: (SORTING_OPTIONS) -> Unit) : BottomSheetDialogFragment() {

    lateinit var binding: BottomMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = BottomMenuBinding.inflate(inflater)

        binding.tvAlphabetical.setOnClickListener {
            dialog!!.dismiss()
            onClick.invoke(SORTING_OPTIONS.ALPHABETICAL)
        }

        binding.tvRating.setOnClickListener {
            dialog!!.dismiss()
            onClick.invoke(SORTING_OPTIONS.RATING)
        }

        binding.tvClear.setOnClickListener {
            dialog!!.dismiss()
            onClick.invoke(SORTING_OPTIONS.CLEAR)
        }

        return binding.root
    }
    enum class SORTING_OPTIONS {
        ALPHABETICAL,
        RATING,
        CLEAR
    }
}