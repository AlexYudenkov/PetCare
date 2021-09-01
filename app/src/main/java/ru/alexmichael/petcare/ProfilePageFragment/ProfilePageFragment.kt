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

        private fun openLogClock(sharedPreferences: SharedPreferences, profileViewModel:ProfileViewModel){
            if(profileViewModel.LogIsClose) {
                val size = sharedPreferences.getInt(DATA_SIZE_KEY, 0)
                for (i in 0 until size) {
                    val stateEnable = sharedPreferences.getBoolean("STATE $i", true)
                    val time = sharedPreferences.getString("TIME $i", "00:00").toString()
                    profileViewModel.listClockData.add(ClockData(time, stateEnable))
                }
                profileViewModel.LogIsClose = false
                println(profileViewModel.LogIsClose)
            }

        }

        private fun saveLogClock(sharedPreferences: SharedPreferences, profileViewModel:ProfileViewModel){
            var editor: SharedPreferences.Editor = sharedPreferences.edit()
            for (i in 0 until profileViewModel.listClockData.size) {
                editor.putString("TIME $i", profileViewModel.listClockData[i].time)
                editor.putBoolean("STATE $i", profileViewModel.listClockData[i].stateEnable)
            }
            editor.putInt(DATA_SIZE_KEY, profileViewModel.listClockData.size)
            editor.apply()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        sharedPreferences = requireActivity().getSharedPreferences("ListClock", Context.MODE_PRIVATE)

        ProfilePageFragment.openLogClock(sharedPreferences, profileViewModel)

        return ComposeView(requireContext()).apply {
            setContent {
                OpenProfile(R.drawable.ic_dog_icon_group,"Борис", "Котопес")

            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        ProfilePageFragment.saveLogClock(sharedPreferences, profileViewModel)
    }

    private fun openNextPage(id: Int) = transitionContract().navigationTransition(id)

    @Composable
    fun OpenProfile(imageId: Int, namePet: String, kindPet: String) {

        Column {
            //Setting icon
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {

                Image(
                    painter = painterResource(id = R.drawable.ic_settings_profile_icon),
                    contentDescription = "-",
                    modifier = Modifier
                        .clickable {
                            openNextPage(enumIdTransition.PROFILEtoSETTINGS.id_T)
                        }
                )
            }

            buildProfileCard(imageId, namePet = namePet, kindPet = kindPet)

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
                            openNextPage(enumIdTransition.PROFILEtoADDALARM.id_T)
                        }
                )
            }

            //List Clock
            LazyColumn{
                items(profileViewModel.listClockData){listClockData->
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
                            }
                        )

                    }
                }
            }

        }
    }

    @Composable
    fun buildProfileCard(imageId: Int, namePet: String, kindPet: String) {

        Row(modifier = Modifier.fillMaxWidth()) {

            Image(
                painter = painterResource(id = imageId),
                contentDescription = "PetIcon",
                modifier = Modifier
                    .padding(start = START_PROFILE_PADDING.dp)
            )


            Column {
                Text(
                    "Имя: $namePet",
                    color = Color(R.color.black_78),
                    modifier = Modifier
                        .padding(start = START_PROFILE_PADDING.dp)
                )

                Text(
                    "Вид: $kindPet",
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
            OpenProfile(R.drawable.ic_dog_icon_group, "Борис", "Котопес")
        }
    }
}

