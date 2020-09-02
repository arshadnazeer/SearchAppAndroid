package `in`.arshad.ui.utils

import `in`.arshad.ui.BaseView

inline fun <T : BaseView> T.onResponse(block: T.() -> Unit) {
    if (isViewInteractive()) {
        block()
    }
}