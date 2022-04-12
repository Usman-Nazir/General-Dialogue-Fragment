package com.usman.generaldialoguefragment

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.*
import androidx.fragment.app.DialogFragment


class CommonDialogueFragment : DialogFragment() {
    var hideNavItem = false
    var hideStatusBar = false
    var layoutView: Int? = null
    var width: Int? = -1
    var widthScreen: Int? = null
    var height: Int? = -1
    var heightScreen: Int? = null
    var enableTouchOutSide: Boolean? = null

//    var binding: DialogueFragmentLogoutBinding? = null

    override fun onCreate(args: Bundle?) {
        super.onCreate(args)
        hideNavItem = arguments?.getBoolean("hideNavItem", hideNavItem) ?: false
        hideStatusBar = arguments?.getBoolean("hideStatusBar", hideStatusBar) ?: false
        layoutView = arguments?.getInt("layoutView", 0) ?: 0
        width = arguments?.getInt("width", 0) ?: 0
        height = arguments?.getInt("height", 0)
        enableTouchOutSide = arguments?.getBoolean("enableTouchOutSide", false)
        Log.i("test" , "hideStatusBar ${hideStatusBar}")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.i("test" , "layoutView  ${layoutView}")
        val view = inflater.inflate(layoutView!!, container, false)
//        val view = inflater.inflate(R.layout.dialogue_fragment_logout, container, false)
//        binding = DialogueFragmentLogoutBinding.bind(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inits(view)
    }

    override fun onStart() {
        super.onStart()
        val displayMetrics = DisplayMetrics()
        activity?.windowManager?.defaultDisplay?.getMetrics(displayMetrics)
        heightScreen = displayMetrics.heightPixels
        widthScreen = displayMetrics.widthPixels
        dialog?.window?.setLayout(if (width == -1) ViewGroup.LayoutParams.WRAP_CONTENT else {
            (widthScreen!! * width!! / 100f).toInt()
        }, if (height == -1) ViewGroup.LayoutParams.WRAP_CONTENT else {
            (heightScreen!! * height!! / 100f).toInt()
        })
        dialog?.setCanceledOnTouchOutside(true)
    }

    override fun onPause() {
        onPause?.invoke()
        super.onPause()
    }

    override fun onDestroy() {
        onDestroy?.invoke()
        super.onDestroy()
    }
    override fun onResume() {
        super.onResume()
        onResume?.invoke()
    }

    private fun inits(view: View) {
        layoutViewManage?.invoke(view ,this)
    }

    override fun dismiss() {
        super.dismiss()
        dismissListener?.invoke()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val dialog = super.onCreateDialog(savedInstanceState)

        // request a window without the title
        dialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        if (hideNavItem)
            hideNavItem(dialog)
        if (hideStatusBar)
            hideStatusBar(dialog)
        return dialog
        //return super.onCreateDialog(savedInstanceState)
    }

    private fun hideStatusBar(dialog: Dialog) {
        if (Build.VERSION.SDK_INT < 16) {
            dialog.window!!.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        } else {
            val decorView = dialog.window!!.decorView
            val uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN
            decorView.systemUiVisibility = uiOptions
        }
    }

    fun hideNavItem(dialog: Dialog) {
        val currentApiVersion = Build.VERSION.SDK_INT
        val flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        // This work only for android 4.4+
        if (currentApiVersion >= Build.VERSION_CODES.KITKAT) {
            dialog.window?.decorView?.systemUiVisibility = flags
            // Code below is to handle presses of Volume up or Volume down.
            // Without this, after pressing volume buttons, the navigation bar will
            // show up and won't hide
            val decorView = dialog.window?.decorView
            decorView?.setOnSystemUiVisibilityChangeListener {
                dialog.window?.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
                dialog.window?.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
                /*if ((it and View.SYSTEM_UI_FLAG_FULLSCREEN) == View.VISIBLE) {*/
                decorView.systemUiVisibility = flags
                /*}*/
            }
        }
    }

    var layoutViewManage: ((view :View, dialogueFragment:DialogFragment)->Unit)? = null
    var onDestroy: (()->Unit)? = null
    var onPause: (()->Unit)? = null
    var onResume: (()->Unit)? = null
    var dismissListener: (()->Unit)? = null

    companion object {

        fun newInstance(hideNavItem: Boolean,
                        hideStatusBar: Boolean,
                        layoutView: Int,
                        width: Int,
                        height: Int,
                        enableTouchOutSide: Boolean): CommonDialogueFragment {
            val args = Bundle()

            args.putBoolean("hideNavItem", hideNavItem)
            args.putBoolean("hideStatusBar", hideStatusBar)
            args.putInt("layoutView", layoutView)
            args.putInt("width", width)
            args.putInt("height", height)
            args.putBoolean("enableTouchOutSide", enableTouchOutSide)

            val fragment = CommonDialogueFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
