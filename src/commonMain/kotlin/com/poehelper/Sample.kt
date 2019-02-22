package com.poehelper

import com.soywiz.korio.async.runBlockingNoSuspensions
import com.soywiz.korio.lang.Charsets
import com.soywiz.korio.lang.toString
import com.soywiz.korio.net.AsyncClient
import com.soywiz.korio.stream.readAvailable

expect fun test(): String

fun helloCommon(): String = "Hello, Common!"


val dataFiles: ByteArray = byteArrayOf(0x03, 0x00, 0x04, 0x44, 0x00, 0x61, 0x00, 0x74, 0x00, 0x61, 0x00)

fun main(args: Array<String>) = runBlockingNoSuspensions {
    println(test())
    println("Super test")
    val client: AsyncClient = AsyncClient.create(false)
    println("Created async client")
    client.connect("172.65.204.172", 12995)
    println("Started connection")
    var dataToRead: ByteArray = byteArrayOf()
    if (client.connected) {
        println("Seems like we are corrected")
        client.write(dataFiles)
        dataToRead = client.readAvailable()
        println(dataToRead.toString(Charsets.UTF8))
    }

}
