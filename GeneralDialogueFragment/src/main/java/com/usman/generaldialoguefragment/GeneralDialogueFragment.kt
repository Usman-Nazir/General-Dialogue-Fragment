package com.usman.generaldialoguefragment

import android.util.Log
import android.view.View
import androidx.fragment.app.FragmentManager

class GeneralDialogueFragment(fm: FragmentManager) {

    private var fm :FragmentManager? = fm
    var hideNavItem = false
    private var hideStatusBar = false
    private var layoutView: Int? = null
    private var width: Int = -1
    private var height: Int = -1
    private var enableTouchOutSide: Boolean = false
    private var tag: String? = null

    fun setHideNavPanel(hideNavItem:Boolean): GeneralDialogueFragment {
        this.hideNavItem =hideNavItem
        return this
    }

    fun setHideStatusBar(hideStatusBar:Boolean): GeneralDialogueFragment {
        this.hideStatusBar =hideStatusBar
        return this
    }

    fun setLayoutView(layoutView:Int): GeneralDialogueFragment {
        this.layoutView =layoutView
        return this
    }

    fun widthPercent(width:Int): GeneralDialogueFragment {
        this.width =width
        return this
    }
    fun heightPercent(height:Int): GeneralDialogueFragment {
        this.height =height
        return this
    }
    fun enableTouchOutSide(enableTouchOutSide:Boolean): GeneralDialogueFragment {
        this.enableTouchOutSide =enableTouchOutSide
        return this
    }

    fun withTag(tag:String): GeneralDialogueFragment {
        this.tag =tag
        return this
    }

    fun show(layoutViewManage: ((view: View)->Unit)? = null){
        if (fm ==null || layoutView ==null) return
        fm?.let { fm ->
            val ft = fm?.beginTransaction()

            val prev = fm?.findFragmentByTag(if (tag != null) tag else "CommonDialogueFragment")
            if (prev != null) fm.beginTransaction().remove(prev).commit()
            val frag = CommonDialogueFragment.newInstance (
                hideNavItem,
                    hideStatusBar,
                    layoutView!!,
                    width,
                    height,
                    enableTouchOutSide)
            frag.layoutViewManage = layoutViewManage
            frag.onDestroy = onDestroy
            frag.onPause = onPause
            frag.onResume = onResume
            frag.dismissListener = dismissListener

            frag.show(ft, if (tag != null) tag else "CommonDialogueFragment")
        }
    }

    private var onDestroy: (()->Unit)? =null
    private var onPause: (()->Unit)? =null
    private var onResume: (()->Unit)? =null
    private var dismissListener: (()->Unit)? =null

    fun onDestroy (onDestroy: (()->Unit)): GeneralDialogueFragment {
        this.onDestroy = onDestroy
        return this
    }
    fun onPause (onPause: (()->Unit)): GeneralDialogueFragment {
        this.onPause = onPause
        return this
    }
    fun onResume (onResume: (()->Unit)): GeneralDialogueFragment {
        this.onResume = onResume
        return this
    }
    fun dismissListener (dismissListener: (()->Unit)): GeneralDialogueFragment {
        this.dismissListener = dismissListener
        return this
    }


}