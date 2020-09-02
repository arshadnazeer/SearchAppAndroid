package `in`.arshad.ui

import `in`.arshad.ui.extensions.showToast
import `in`.arshad.ui.helper.DynamicViewHandler
import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity(), BaseView {

    private var dynamicViewHandler: DynamicViewHandler? = null

    private var isViewInteractive: Boolean = false

    override fun closeKeyBoard() {
        if (currentFocus != null && currentFocus?.windowToken != null) {
            val inputManager = getSystemService(Context
                    .INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(currentFocus!!.windowToken,
                    InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }

    override fun showProgress() {
        if (dynamicViewHandler == null) {
            dynamicViewHandler = DynamicViewHandler(this)
        }
        dynamicViewHandler!!.show(R.layout.progress)
        closeKeyBoard()
    }

    override fun hideProgress() {
        dynamicViewHandler?.hide()
    }

    override fun showMessage(message: String?) {
        showToast(message)
    }

    override fun showInternetError() {
        if (dynamicViewHandler == null) {
            dynamicViewHandler = DynamicViewHandler(this)
        }
        val view = dynamicViewHandler!!.show(R.layout.layout_internet_error)
        val params = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT)
        params.setMargins(0, 0, 0, 0)
        view.layoutParams = params
        closeKeyBoard()
    }

    override fun hideInternetError() {
        dynamicViewHandler?.hide()
    }

    fun showToast(msg: String?) {
        msg?.also {
            window?.decorView?.showToast(it)
        }
    }

    override fun isViewInteractive() = isViewInteractive

    override fun onStart() {
        super.onStart()
        isViewInteractive = true
    }

    override fun onStop() {
        super.onStop()
        isViewInteractive = false
    }
}
