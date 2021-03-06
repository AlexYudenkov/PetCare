package ru.alexmichael.petcare.AboutŠ”ategoryPageFragment

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
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                OpenCategory()
            }
        }
    }
}

@Composable
fun OpenCategory(){
    Text("CategoryFragment")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PetCareTheme {
        OpenCategory()
    }
}