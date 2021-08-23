package ru.alexmichael.petcare.ProfilePageFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import ru.alexmichael.petcare.R
import ru.alexmichael.petcare.enumIdTransition
import ru.alexmichael.petcare.extTransition.transitionContract
import ru.alexmichael.petcare.ui.theme.PetCareTheme

class ProfilePageFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                OpenProfile("Борис", "Котопес")
            }
        }
    }

    private fun openNextPage(id: Int) = transitionContract().navigationTransition(id)

    @Composable
    fun OpenProfile(namePet: String, kindPet: String) {
        Column {
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

            buildProfileCard(namePet = namePet, kindPet = kindPet)

            Spacer(modifier = Modifier.padding(top = 100.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    "Режим дня",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(R.color.black_78),
                    modifier = Modifier.padding(start = 6.dp)
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

            Row {
                Text(
                    "Поможем не забыть когда покорить вашего друга",
                    fontSize = 15.sp,
                    color = Color(R.color.black_40),
                    modifier = Modifier.padding(start = 6.dp)
                )

                Spacer(modifier = Modifier.padding(start = 12.dp))

                Image(
                    painter = painterResource(id = R.drawable.ic_plus),
                    contentDescription = "plus icon",
                    modifier = Modifier.clickable {
                        openNextPage(enumIdTransition.PROFILEtoADDALARM.id_T)
                    }
                )
            }

        }
    }

    @Composable
    fun buildProfileCard(namePet: String, kindPet: String) {

        Row(modifier = Modifier.fillMaxWidth()) {
            val startProfilePadding = 8

            Image(
                painter = painterResource(id = R.drawable.ic_dog_icon_group),
                contentDescription = "-",
                modifier = Modifier
                    .padding(start = startProfilePadding.dp)
            )


            Column {
                Text(
                    "Имя: $namePet",
                    color = Color(R.color.black_78),
                    modifier = Modifier
                        .padding(start = startProfilePadding.dp)
                )

                Text(
                    "Вид: $kindPet",
                    color = Color(R.color.black_78),
                    modifier = Modifier
                        .padding(start = startProfilePadding.dp)
                )
            }


        }
    }


    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        PetCareTheme {
            OpenProfile("Борис", "Котопес")
        }
    }
}