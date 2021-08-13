package ru.alexmichael.petcare

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import ru.alexmichael.petcare.MainPageFragment.MainPageFragment
import ru.alexmichael.petcare.ui.theme.PetCareTheme

class MainActivity : FragmentActivity() {

	private var supportManager = supportFragmentManager

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		supportManager.beginTransaction().replace(R.id.container, MainPageFragment()).commit()


	}
}

