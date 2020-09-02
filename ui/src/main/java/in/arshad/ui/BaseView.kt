package `in`.arshad.ui

interface BaseView {
    fun closeKeyBoard()

    fun showProgress()

    fun hideProgress()

    fun showMessage(message: String? = "There is an error")

    fun showInternetError()

    fun hideInternetError()

    fun isViewInteractive(): Boolean
}