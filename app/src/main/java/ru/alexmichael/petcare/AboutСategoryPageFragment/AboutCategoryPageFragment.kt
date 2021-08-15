package ru.alexmichael.petcare.AboutСategoryPageFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment
import ru.alexmichael.petcare.ui.theme.PetCareTheme
class AboutCategoryPageFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                OpetnCategoty()
            }
        }
    }
}

@Composable
fun OpetnCategoty(){
    Text("CategoryFragment")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PetCareTheme {
        OpetnCategoty()
    }
}