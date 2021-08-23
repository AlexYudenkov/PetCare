package ru.alexmichael.petcare

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.alexmichael.petcare.extTransition.AppContractTransition
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
			enumIdTransition.PROFILEtoADDALARM.id_T -> {navController.navigate(R.id.action_profilePageFragment_to_addAlarmClock)}
			enumIdTransition.PROFILEtoSETTINGS.id_T -> {navController.navigate(R.id.action_profilePageFragment_to_settingsProfile)}
			else -> throw RuntimeException("Error Transition: Unknown Id Transition")

		}

	}
}

