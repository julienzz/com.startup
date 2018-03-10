package com.startup.app

/**
 * Created by julienchahoud on 3/10/18.
 */
open class UserId {

    var userId: String = ""

    fun <T : UserId> withId(id: String): T {
        this.userId = id
        return this as T
    }

}
