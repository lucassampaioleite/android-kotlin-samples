package leite.sampaio.lucas.fragmentsexampleapplication

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import leite.sampaio.lucas.fragmentsexampleapplication.databinding.FragmentLoginBinding

class LoginFragment :  Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding
//    private lateinit var userNameAdmin: String
//    private lateinit var passwordAdmin: String
    private lateinit var person: Person

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        userNameAdmin = requireArguments().getString("USERNAME_ADMIN") ?: ""
//        passwordAdmin = requireArguments().getString("PASSWORD_ADMIN") ?: ""
        val personDefault = Person("","")
        person = requireArguments()
            .getSerializable("PERSON_ADMIN", Person::class.java) ?: personDefault
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentLoginBinding.bind(view)
//        binding.edtEmailAddress.setText(userNameAdmin)
//        binding.edtPassword.setText(passwordAdmin)
        binding.edtEmailAddress.setText(person.userName)
        binding.edtPassword.setText(person.password)

        binding.btnLogin.setOnClickListener(){
            (activity as MainActivity).replaceFragment(HomeFragment())
        }
    }
}

