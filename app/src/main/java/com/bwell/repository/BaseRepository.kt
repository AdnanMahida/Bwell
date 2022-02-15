package com.bwell.repository

import com.bwell.helper.ResultState

abstract class BaseRepository {
    companion object {
        const val UNAUTHORIZED = "Unauthorized"
        const val NOT_FOUND = "Not found"
        const val ALREADY_EXIST = "Already Exist"
        const val BAD_REQUEST = "Bad Request"
        const val SOMETHING_WRONG = "Something went wrong"
        const val GENERAL_ERROR_CODE = 499

        fun <T : Any> handleSuccess(data: T): ResultState<T> {
            return ResultState.Success(data)
        }

        fun <T : Any> handleException(code: Int): ResultState<T> {
            val exception = getErrorMessage(code)
            return ResultState.Error(Exception(exception))
        }

        private fun getErrorMessage(httpCode: Int): String {
            return when (httpCode) {
                400 -> BAD_REQUEST
                401 -> UNAUTHORIZED
                404 -> NOT_FOUND
                409 -> ALREADY_EXIST
                else -> SOMETHING_WRONG
            }
        }
    }
}