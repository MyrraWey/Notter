package com.muravyovdmitr.notter

/**
 * Created by Dima Muravyov on 01.08.2016.
 */

interface ParamAction<T> {
    fun execute(param: T)
}
