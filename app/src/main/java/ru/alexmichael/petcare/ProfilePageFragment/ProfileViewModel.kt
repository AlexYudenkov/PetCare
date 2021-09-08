package ru.alexmichael.petcare.ProfilePageFragment


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.alexmichael.petcare.ProfilePageFragment.addAlarmClockPage.ClockData


class ProfileViewModel: ViewModel() {

    companion object{
        val profileViewModelGeneral:ProfileViewModel = ProfileViewModel()
    }

    var logIsClose = true
    var itemList: MutableLiveData<MutableList<ClockData>> = MutableLiveData()

    init{
        itemList.value = mutableListOf()
    }
}