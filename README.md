# General-Dialogue-Fragment

General Dialogue Fragment is a handy tool for a lazy developers no need to extra classes for dialogue fragment just add the library and rest is simple

**Instalation**

Step 1. Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			maven { url 'https://jitpack.io' }
		}
	}

Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.Usman-Nazir:General-Dialogue-Fragment:1.0.0'
	}


**Usage**

	GeneralDialogueFragment(supportFragmentManager)
		    .setLayoutView(R.layout.dialogue_fragment_logout)
		    .onResume {}
		    .widthPercent(100)   // 0 -100     -1 => wrap
		    .heightPercent(100)
		    .setHideStatusBar(true)
		    .setHideNavPanel(true)
		    .withTag("sds")
		    .onResume {  }
		    .onPause {  }
		    .onDestroy {  }
		    .dismissListener {  }
		    .show {
			DialogueFragmentLogoutBinding.bind(it)?.let {

			}
		    }

![image](https://user-images.githubusercontent.com/23031447/159422879-539c9c5e-f54c-419e-868d-02ed6c01ff23.png)
