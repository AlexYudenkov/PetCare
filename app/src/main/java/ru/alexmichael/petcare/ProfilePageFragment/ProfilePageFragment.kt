package ru.alexmichael.petcare.ProfilePageFragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import ru.alexmichael.petcare.ProfilePageFragment.addAlarmClockPage.ClockData
import ru.alexmichael.petcare.R
import ru.alexmichael.petcare.enumIdTransition
import ru.alexmichael.petcare.extTransition.transitionContract
import ru.alexmichael.petcare.ui.theme.PetCareTheme

class ProfilePageFragment: Fragment() {

    private val profileViewModel = ProfileViewModel.profileViewModelGeneral
    private lateinit var sharedPreferences: SharedPreferences

    companion object{
        private const val DATA_SIZE_KEY = "SIZE"
        const val START_PROFILE_PADDING = 8
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        sharedPreferences = requireActivity().getSharedPreferences("ListClock", Context.MODE_PRIVATE)

        openLogClock(sharedPreferences, profileViewModel)

        val petParameters = PetParameters(R.drawable.ic_dog_icon_group,"Борис", "Котопес")
        return ComposeView(requireContext()).apply {
            setContent {
                OpenProfile(petParameters)

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        saveLogClock(sharedPreferences, profileViewModel)
    }

    private fun openLogClock(sharedPreferences: SharedPreferences, profileViewModel:ProfileViewModel){
        if(profileViewModel.logIsClose) {
            val size = sharedPreferences.getInt(DATA_SIZE_KEY, 0)
            for (i in 0 until size) {
                val stateEnable = sharedPreferences.getBoolean("STATE $i", true)
                val time = sharedPreferences.getString("TIME $i", "00:00").toString()
                profileViewModel.itemList.value!!.add(ClockData(time, stateEnable))
            }
            profileViewModel.logIsClose = false
            println(profileViewModel.logIsClose)
        }

    }

    private fun saveLogClock(sharedPreferences: SharedPreferences, profileViewModel:ProfileViewModel){
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        for (i in 0 until profileViewModel.itemList.value!!.size) {
            editor.putString("TIME $i", profileViewModel.itemList.value!![i].time)
            editor.putBoolean("STATE $i", profileViewModel.itemList.value!![i].stateEnable)
        }
        editor.putInt(DATA_SIZE_KEY, profileViewModel.itemList.value!!.size)
        editor.apply()
    }

    private fun openNextPage(id: Int) = transitionContract().navigationTransition(id)

    @Composable
    fun OpenProfile(petParameters:PetParameters) {

        Column {
            //Setting icon
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {

                Image(
                    painter = painterResource(id = R.drawable.ic_settings_profile_icon),
                    contentDescription = "Settings Icon",
                    modifier = Modifier
                        .clickable {
                            openNextPage(enumIdTransition.PROFILE_TO_SETTINGS.id_T)
                        }
                )
            }

            buildProfileCard(petParameters)

            Spacer(modifier = Modifier.padding(top = 100.dp))

            //Schedule day
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    "Режим дня",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(R.color.black_78),
                    modifier = Modifier.padding(START_PROFILE_PADDING.dp)
                )
                Canvas(modifier = Modifier.fillMaxWidth()) {
                    val paddingLine = 8
                    drawLine(
                        start = Offset(x = size.width - paddingLine, y = size.height / 2),
                        end = Offset(x = 0f + paddingLine, y = size.height / 2),
                        color = Color(R.color.black_78)
                    )
                }
            }
            //Schedule day
            Row (horizontalArrangement = Arrangement.SpaceEvenly){
                Text(
                    text = "Поможем не забыть когда покорить вашего друга",
                    fontSize = 15.sp,
                    color = Color(R.color.black_40),
                    modifier = Modifier.padding(START_PROFILE_PADDING.dp)
                )

                Image(
                    painter = painterResource(id = R.drawable.ic_plus),
                    contentDescription = "Add Icon",
                    modifier = Modifier
                        .padding(start = 4.dp)
                        .clickable {
                            openNextPage(enumIdTransition.PROFILE_TO_ADDALARM.id_T)
                        }
                )
            }

            //List Clock
            LazyColumn{
                items(profileViewModel.itemList.value!!){listClockData->
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                        //здесь должен быть вызов фрагмента для редактирования
                        },
                        horizontalArrangement = Arrangement.SpaceEvenly)
                    {
                        Text(listClockData.time)

                        val checkedState = remember { mutableStateOf(listClockData.stateEnable) }
                        Switch(
                            checked = checkedState.value,
                            onCheckedChange = {
                                checkedState.value = it
                                listClockData.stateEnable = checkedState.value
                                saveLogClock(sharedPreferences, profileViewModel)
                            }
                        )
                    }
                }
            }
        }
    }

    @Composable
    fun buildProfileCard(petParameters:PetParameters) {

        Row(modifier = Modifier.fillMaxWidth()) {

            Image(
                painter = painterResource(id = petParameters.id_icon),
                contentDescription = "PetIcon",
                modifier = Modifier
                    .padding(start = START_PROFILE_PADDING.dp)
            )


            Column {
                Text(
                    "Имя: ${petParameters.name}",
                    color = Color(R.color.black_78),
                    modifier = Modifier
                        .padding(start = START_PROFILE_PADDING.dp)
                )

                Text(
                    "Вид: ${petParameters.kind}",
                    color = Color(R.color.black_78),
                    modifier = Modifier
                        .padding(start = START_PROFILE_PADDING.dp)
                )
            }


        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        PetCareTheme {
            val petParameters = PetParameters(R.drawable.ic_dog_icon_group,"Борис", "Котопес")
            OpenProfile(petParameters)
        }
    }
}

