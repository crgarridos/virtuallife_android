package com.cristiangarrido.virtuallife.entity

/**
 * Created by cristian on 09/02/17.
 */
data class User(val id : Long, val name : String, val goods : List<String> = arrayListOf("1","2","3")){
    constructor() : this(0,"")
}