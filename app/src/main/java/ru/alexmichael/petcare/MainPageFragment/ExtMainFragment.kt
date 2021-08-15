package ru.alexmichael.petcare.MainPageFragment

import androidx.fragment.app.Fragment
import ru.alexmichael.petcare.R
import java.lang.RuntimeException


fun Fragment.transitionContract():AppContractTransition = requireActivity() as AppContractTransition

interface AppContractTransition {

      fun navigationTransition(id: Int)
}

