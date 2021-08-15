package ru.alexmichael.petcare.MainPageFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import ru.alexmichael.petcare.R
import ru.alexmichael.petcare.ui.theme.PetCareTheme

class MainPageFragment: Fragment() {

    companion object {
        val TEXT_TOP_LINE = "Выберете котрегорию питомца"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                Start()
            }
        }
    }

    fun openCategory(id: Int) = transitionContract().navigationTransition(id)


    @Composable
    fun Start() {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Spacer(modifier = Modifier.height(30.dp))

            Text(TEXT_TOP_LINE)

            Spacer(modifier = Modifier.height(50.dp))

            buildRow(R.drawable.ic_dog_icon_group, R.drawable.ic_cat_icon_group)
            Spacer(modifier = Modifier.height(8.dp))
            buildRow(R.drawable.ic_mouse_icon_group, R.drawable.ic_parrot_icon_group)
            Spacer(modifier = Modifier.height(8.dp))
            buildRow(R.drawable.ic_fish_icon_group, R.drawable.ic_tutrle_icon_group)
        }
    }

    @Composable
    fun buildRow(id1: Int, id2: Int) {
        Row(modifier = Modifier.padding(all = 8.dp)) {
            //пропорция картинок 1.5 / 1
            buildImage(id = id1)
            buildImage(id = id2)
        }
    }

    @Composable
    fun buildImage(id: Int) {
        Image(
            painter = painterResource(id = id),
            contentDescription = "-",
            modifier = Modifier
                .width(195.dp)
                .height(130.dp)
                .clickable(
                    enabled = true,
                    onClickLabel = "Clickable image",
                    onClick = {
                        this.openCategory(id)
                    }
                )

        )

    }


    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        PetCareTheme {
            Start()
        }
    }
}