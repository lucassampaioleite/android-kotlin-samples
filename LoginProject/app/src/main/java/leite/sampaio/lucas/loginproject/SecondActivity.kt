package leite.sampaio.lucas.loginproject

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import leite.sampaio.lucas.loginproject.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    private val callback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            showExitConfirmationDialog()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setTitle(getString(R.string.second_activity_title))

        val userEmail = "${getString(R.string.user)} ${intent.getStringExtra(MainActivity.EXTRA_USER)}"
        val dateHour = "${getString(R.string.logged_in)} ${intent.getStringExtra(MainActivity.EXTRA_DATE_HOUR)}"

        binding.txtUser.text = userEmail
        binding.txtDateHour.text = dateHour

        onBackPressedDispatcher.addCallback(this, callback)
    }

    private fun showExitConfirmationDialog() {
        AlertDialog.Builder(this)
            .setMessage(getString(R.string.dialog_message))
            .setPositiveButton(getString(R.string.yes)) { _, _ -> finish()}
            .setNegativeButton(getString(R.string.no), null)
            .show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_default, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.option1 -> {
                Toast.makeText(this, getString(R.string.option1_selected), Toast.LENGTH_SHORT).show()
                true
            }
            R.id.sub1 -> {
                val url = "https://portal.cin.ufpe.br/"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intent)
                true
            }
            R.id.sub2 -> {
                val url = "https://play.kotlinlang.org/"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intent)
                true
            }
            R.id.option2 -> {
                val sharedText = getString(R.string.shared_text)
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/plain"
                intent.putExtra(Intent.EXTRA_TEXT, sharedText)
                startActivity(Intent.createChooser(intent, getString(R.string.share_via)))
                true
            }
            R.id.option3 -> {
                val latitude = -8.0554591
                val longitude = -34.9539049
                val zoomLevel = 17

                val gmmIntentUri = Uri.parse("geo:$latitude,$longitude?z=$zoomLevel")
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)

                mapIntent.setPackage("com.google.android.apps.maps")

                if (mapIntent.resolveActivity(packageManager) != null) {
                    startActivity(mapIntent)
                } else {
                    Toast.makeText(
                        this,
                        getString(R.string.toast_incompatible_application),
                        Toast.LENGTH_SHORT)
                        .show()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}


