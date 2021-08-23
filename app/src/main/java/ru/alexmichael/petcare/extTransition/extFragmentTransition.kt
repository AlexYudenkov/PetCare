package ru.alexmichael.petcare.extTransition

import androidx.fragment.app.Fragment

fun Fragment.transitionContract():AppContractTransition = requireActivity() as AppContractTransition

interface AppContractTransition {

      fun navigationTransition(id: Int)
}

