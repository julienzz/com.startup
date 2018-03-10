package com.startup.app

/**
 * Created by julienchahoud on 3/10/18.
 */
class Users : UserId {

    lateinit var name: String
    lateinit var image: String

    constructor() {

    }

    constructor(name: String, image: String) {
        this.name = name
        this.image = image
    }
}