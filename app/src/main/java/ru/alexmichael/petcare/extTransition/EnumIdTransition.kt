package ru.alexmichael.petcare

enum class enumIdTransition(val id_T:Int) {
    PROFILE_TO_SETTINGS(1),
    PROFILE_TO_ADDALARM(2),
    ADDALARM_TO_PROFILE(3),
}