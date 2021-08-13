package ru.alexmichael.petcare.MainPageFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import ru.alexmichael.petcare.R
import ru.alexmichael.petcare.ui.theme.PetCareTheme

class MainPageFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                Start()
            }
        }
    }
}

@Composable
fun Start() {

    Column{

        Spacer(modifier = Modifier.height(20.dp))

        Text("Выберете котрегорию питомца", modifier = Modifier.padding(horizontal = 100.dp))

        Spacer(modifier = Modifier.height(50.dp))

        buildRow(R.drawable.ic_dog_icon_group, R.drawable.ic_cat_icon_group)

        buildRow(R.drawable.ic_mouse_icon_group, R.drawable.ic_parrot_icon_group)

        buildRow(R.drawable.ic_hollow_icon, R.drawable.ic_hollow_icon)
    }
}

@Composable
fun buildRow(id1: Int, id2: Int){
    Row(modifier = Modifier.padding(all = 8.dp)){
        //пропорция картинок 1.5 / 1
        Image(
            painter = painterResource(id = id1),
            contentDescription = "-",
            modifier = Modifier
                .width(195.dp).height(130.dp)

        )
        Spacer(modifier = Modifier.width(8.dp))

        Image(
            painter = painterResource(id = id2),
            contentDescription = "-",
            modifier = Modifier
                .width(195.dp).height(130.dp)

        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PetCareTheme {
        Start()
    }
}