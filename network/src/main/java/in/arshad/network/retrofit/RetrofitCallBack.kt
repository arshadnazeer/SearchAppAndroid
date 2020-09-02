package `in`.arshad.network.retrofit

import `in`.arshad.domain.CloudBaseResponse
import `in`.arshad.domain.ErrorBaseResponse
import `in`.arshad.domain.PresenterCallBack
import okhttp3.ResponseBody

class RetrofitCallBack<T : CloudBaseResponse>(private val callBack: PresenterCallBack<T>?) : BaseCallBacks<T>() {

    override fun networkOrUnexpectedError(error: ErrorBaseResponse) {
        callBack?.onInternetError(error)
    }

    override fun success(successResponse: T) {
        callBack?.onSuccess(successResponse)
    }

    override fun failure(error: ErrorBaseResponse, rawResponseBody: ResponseBody?) {
        callBack?.onOtherError(error, rawResponseBody)
    }
}