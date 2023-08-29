package leite.sampaio.lucas.lifecycle

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("TAG_DEBUG", "onCreate()")
    }

    override fun onStart() {
        Log.d("TAG_DEBUG", "onStart()")
        super.onStart()
    }

    override fun onResume() {
        Log.d("TAG_DEBUG", "onResume()")
        super.onResume()
    }

    override fun onPause() {
        Log.d("TAG_DEBUG", "onPause()")
        super.onPause()
    }

    override fun onStop() {
        Log.d("TAG_DEBUG", "onStop()")
        super.onStop()
    }

    override fun onRestart() {
        Log.d("TAG_DEBUG", "onRestart()")
        super.onRestart()
    }

    override fun onDestroy() {
        Log.d("TAG_DEBUG", "onDestroy()")
        super.onDestroy()
    }
}

