package com.sgr.owltube_v2.frontend.common

enum class RequestStatus {
    INIT,
    REQUESTING,
    SUCCESS,
    ERROR;

    fun isRequesting(): Boolean = (this == REQUESTING)
    fun isSuccess(): Boolean = (this == SUCCESS)
    fun isError(): Boolean = (this == ERROR)
}