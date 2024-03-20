import android.content.Context
import com.example.testvkproject.App
import com.example.testvkproject.di.component.AppComponent


fun Context.appComponent(): AppComponent {
        return when (this) {
                is App -> appComponent
                else -> this.applicationContext.appComponent()
        }
}
