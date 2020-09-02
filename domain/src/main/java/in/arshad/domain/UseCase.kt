package `in`.arshad.domain

import okhttp3.ResponseBody

interface UseCase<Request, Response : CloudBaseResponse> {
    fun execute(request: Request, callBack: PresenterCallBack<Response>)
}

interface UseCaseSingleParam<Response : CloudBaseResponse> {
    fun execute(callBack: PresenterCallBack<Response>)
}

interface PresenterCallBack<T : CloudBaseResponse> {
    fun onSuccess(response: T)
    fun onInternetError(response: ErrorBaseResponse)
    fun onOtherError(response: ErrorBaseResponse, errorBody: ResponseBody?)
}