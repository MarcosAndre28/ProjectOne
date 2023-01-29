package com.example.projectone.utils

import com.example.projectone.data.Api.ApiError

class ApiErrorUtils {
    companion object {
       fun getErrorMessage(code: Int): String {
            return when (code) {
                ApiError.BAD_REQUEST.code -> "Bad Request Error"
                ApiError.UNAUTHORIZED.code -> "Unauthorized Error"
                ApiError.FORBIDDEN.code -> "Forbidden Error"
                ApiError.NOT_FOUND.code -> "Not Found Error"
                ApiError.INTERNAL_SERVER_ERROR.code -> "Internal Server Error"
                ApiError.SERVICE_UNAVAILABLE.code -> "Service Unavailable Error"
                else -> "Unknown Error"
            }
        }
    }
}