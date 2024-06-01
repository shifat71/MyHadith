import androidx.lifecycle.ViewModel
import com.shifat.myhadis.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val repository: AuthRepository): ViewModel(){

}


