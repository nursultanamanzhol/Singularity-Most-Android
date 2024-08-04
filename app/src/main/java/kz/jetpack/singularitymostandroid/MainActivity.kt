package kz.jetpack.singularitymostandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import kz.jetpack.singularitymostandroid.presentation.screen.MainScreen
import kz.jetpack.singularitymostandroid.ui.theme.SingularityMostAndroidTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SingularityMostAndroidTheme {
                Surface {
                    MainScreen()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SingularityMostAndroidTheme {
        MainScreen()
    }
}
