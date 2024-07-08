package leite.sampaio.lucas.fragmentsexampleapplication

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import leite.sampaio.lucas.fragmentsexampleapplication.databinding.ActivityMainBinding

class MainActivity : FragmentActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val bundle = bundleOf(
//            "USERNAME_ADMIN" to "admin@cin.ufpe.br",
//            "PASSWORD_ADMIN" to "admin"
//        )

        val bundle = bundleOf(
            "PERSON_ADMIN" to Person("admin@cin.ufpe.br", "admin")
        )

        if (savedInstanceState==null){
            supportFragmentManager.commit {
                add<LoginFragment>(R.id.fragmentContainerView, args = bundle)
            }
        }
    }

    fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, fragment)
            .addToBackStack(null)
            .commit()
    }

}



