package com.android.distilled.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.distilled.databinding.BottomMenuBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetDialog(private val onClick: (SORTING_OPTIONS, SORTING_TYPE) -> Unit) :
    BottomSheetDialogFragment() {

    lateinit var binding: BottomMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = BottomMenuBinding.inflate(inflater)

        binding.tvAlphabeticalAsc.setOnClickListener {
            dialog!!.dismiss()
            onClick.invoke(SORTING_OPTIONS.ALPHABETICAL, SORTING_TYPE.ASC)
        }

        binding.tvAlphabeticalDesc.setOnClickListener {
            dialog!!.dismiss()
            onClick.invoke(SORTING_OPTIONS.ALPHABETICAL, SORTING_TYPE.DESC)
        }

        binding.tvRatingAsc.setOnClickListener {
            dialog!!.dismiss()
            onClick.invoke(SORTING_OPTIONS.RATING, SORTING_TYPE.ASC)
        }

        binding.tvRatingDesc.setOnClickListener {
            dialog!!.dismiss()
            onClick.invoke(SORTING_OPTIONS.RATING, SORTING_TYPE.DESC)
        }

        binding.tvClear.setOnClickListener {
            dialog!!.dismiss()
            onClick.invoke(SORTING_OPTIONS.CLEAR, SORTING_TYPE.ASC)
        }

        return binding.root
    }

    enum class SORTING_OPTIONS {
        ALPHABETICAL,
        RATING,
        CLEAR
    }

    enum class SORTING_TYPE {
        DESC,
        ASC
    }
}