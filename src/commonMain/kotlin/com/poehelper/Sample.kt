package com.poehelper

expect fun test():String

fun helloCommon(): String = "Hello, Common!"

fun main(args: Array<String>) {
    println(test())

}
