package ru.alexmichael.petcare

import android.os.Bundle
import android.view.KeyEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.alexmichael.petcare.AboutСategoryPageFragment.AboutCategoryPageFragment
import ru.alexmichael.petcare.MainPageFragment.AppContractTransition
import ru.alexmichael.petcare.MainPageFragment.MainPageFragment
import ru.alexmichael.petcare.ui.theme.PetCareTheme
import java.lang.RuntimeException

class MainActivity : FragmentActivity(), AppContractTransition {

	private lateinit var navController:NavController

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

		navController = findNavController(R.id.container)

		bottomNavigationView.setupWithNavController(navController)
	}

	override fun navigationTransition(id: Int){
		when(id){
			R.drawable.ic_cat_icon_group -> {navController.navigate(R.id.action_mainPageFragment_to_aboutCategoryPageFragment)}
			R.drawable.ic_dog_icon_group -> {navController.navigate(R.id.action_mainPageFragment_to_aboutCategoryPageFragment)}
			R.drawable.ic_mouse_icon_group -> {navController.navigate(R.id.action_mainPageFragment_to_aboutCategoryPageFragment)}
			R.drawable.ic_parrot_icon_group -> {navController.navigate(R.id.action_mainPageFragment_to_aboutCategoryPageFragment)}
			R.drawable.ic_fish_icon_group -> {navController.navigate(R.id.action_mainPageFragment_to_aboutCategoryPageFragment)}
			R.drawable.ic_tutrle_icon_group -> {navController.navigate(R.id.action_mainPageFragment_to_aboutCategoryPageFragment)}
			else -> throw RuntimeException("Error Transition: Unknown Id Transition")

		}

	}
}

