package ru.alexmichael.petcare.ProfilePageFragment.addAlarmClockPage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import com.google.android.material.timepicker.MaterialTimePicker
import ru.alexmichael.petcare.DaggerDaggerComponent
import ru.alexmichael.petcare.ProfilePageFragment.ProfileViewModel
import ru.alexmichael.petcare.ui.theme.PetCareTheme
import ru.alexmichael.petcare.R
import ru.alexmichael.petcare.enumIdTransition
import ru.alexmichael.petcare.extTransition.transitionContract

class AddAlarmClock: Fragment() {

    private var Time = mutableStateOf("00:00")
    private val profileViewModel = ProfileViewModel.profileViewModelGeneral

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                addAlarm()
            }
        }
    }

    private fun getBackInProfile(id: Int) = transitionContract().navigationTransition(id)

    private fun timePickerShow(){
        val picker = MaterialTimePicker.Builder().build()

        picker.show(requireActivity().supportFragmentManager, picker.toString())
        picker.addOnCancelListener {  }
        picker.addOnPositiveButtonClickListener {
            Time.value = "${picker.hour}:${picker.minute}"
        }
        picker.addOnNegativeButtonClickListener {  }
    }

    @Composable
    fun addAlarm(){

        Column{

            Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 50.dp),
                horizontalArrangement = Arrangement.Center)
            {
                Text("Время",modifier = Modifier,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold)
            }

            Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                    horizontalArrangement = Arrangement.Center)
            {

                Text(Time.value, modifier = Modifier.clickable {timePickerShow()},
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Blue)
                
            }
            
            Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 50.dp),
                    horizontalArrangement = Arrangement.Center
                    )
            {

                Button(
                        onClick = {
                            profileViewModel.listClockData.add(ClockData(Time.value, true))
                            getBackInProfile(enumIdTransition.ADDALARMtoPROFILE.id_T)
                                  },
                        colors = ButtonDefaults.textButtonColors(
                                backgroundColor = Color(R.color.black_40)
                        ))
                {
                    Text("Добавить", color = Color(R.color.white))
                }
            }


        }
    }




    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        PetCareTheme {
            addAlarm()
        }
    }
}