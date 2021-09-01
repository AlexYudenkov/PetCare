package ru.alexmichael.petcare.ProfilePageFragment


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.alexmichael.petcare.ProfilePageFragment.addAlarmClockPage.ClockData


class ProfileViewModel: ViewModel() {

    companion object{
        val profileViewModelGeneral:ProfileViewModel = ProfileViewModel()

    }
    var LogIsClose = true
    var ItemList: MutableLiveData<MutableList<ClockData>> = MutableLiveData()
    var listClockData: MutableList<ClockData>

    init{

        ItemList.value = mutableListOf()

        listClockData = ItemList.value!!



    }





}