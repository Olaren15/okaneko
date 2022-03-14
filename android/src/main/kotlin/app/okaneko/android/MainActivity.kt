package app.okaneko.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import app.okaneko.android.di.initDI
import app.okaneko.authentication.use_case.IsUserLoggedIn
import kotlinx.coroutines.runBlocking
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.instance

class MainActivity : ComponentActivity(), DIAware {
    override val di: DI = initDI()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var loggedIn: Boolean

        runBlocking {
            val isUserLoggedIn: IsUserLoggedIn by instance()
            loggedIn = isUserLoggedIn.invoke()
        }

        setContent {
            ContentView(loggedIn)
        }
    }
}
