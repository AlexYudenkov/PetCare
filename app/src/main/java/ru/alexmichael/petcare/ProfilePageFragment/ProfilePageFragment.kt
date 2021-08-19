package ru.alexmichael.petcare.ProfilePageFragment

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import ru.alexmichael.petcare.R
import ru.alexmichael.petcare.ui.theme.PetCareTheme

class ProfilePageFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                OpenProfile()
            }
        }
    }

}

@Composable
fun OpenProfile(){
    Column {
        buildProfileCard(namePet = "Барис", kindPet = "Сумчатый кот" )
    }
}

@Composable
fun buildProfileCard(namePet:String, kindPet:String){

    Row(modifier = Modifier.padding(start = 12.dp, top = 8.dp)){
        Image(
            painter = painterResource(id = R.drawable.ic_dog_icon_group),
            contentDescription = "-",
            modifier = Modifier
                /*.border(width = 2.dp,
                        color = Color(red=239, green=239, blue = 239),
                        shape = RoundedCornerShape(20.dp))*/
        )

        Spacer(modifier = Modifier.padding(12.dp))
        Column {
            Text(namePet)
            Spacer(modifier = Modifier
                .padding(top = 8.dp)

            )
            Text(kindPet)
        }
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PetCareTheme {
        OpenProfile()
    }
}