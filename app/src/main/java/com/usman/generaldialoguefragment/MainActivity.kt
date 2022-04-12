package com.usman.generaldialoguefragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.usman.generaldialoguefragment.databinding.DialogueFragmentLogoutBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GeneralDialogueFragment(supportFragmentManager)
            .setLayoutView(R.layout.dialogue_fragment_logout)
            .onResume {
                Log.i("test" ,"resume working")
            }
            .widthPercent(100)   // 0 -100     -1 => wrap
            .heightPercent(100)
            .setHideStatusBar(true)
            .setHideNavPanel(true)
            .withTag("sds")
            .onResume {  }
            .onPause {  }
            .onDestroy {  }
            .dismissListener {  }
            .show {view , dg ->
//                dg?.dismiss()
                DialogueFragmentLogoutBinding.bind(view)?.let {

                }
            }
    }
}