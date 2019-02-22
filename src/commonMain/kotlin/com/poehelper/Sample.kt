package com.poehelper

import com.soywiz.korio.async.async
import com.soywiz.korio.async.launch
import com.soywiz.korio.async.runBlockingNoSuspensions
import com.soywiz.korio.lang.Charsets
import com.soywiz.korio.lang.toString
import com.soywiz.korio.net.AsyncClient
import com.soywiz.korio.net.AsyncSocketFactory
import com.soywiz.korio.net.createTcpClient
import com.soywiz.korio.stream.readAll
import com.soywiz.korio.stream.readAvailable
import kotlinx.coroutines.GlobalScope

expect fun test(): String

fun helloCommon(): String = "Hello, Common!"


val dataFiles: ByteArray = byteArrayOf(0x03, 0x00, 0x04, 0x44, 0x00, 0x61, 0x00, 0x74, 0x00, 0x61, 0x00)

fun main(args: Array<String>) = runBlockingNoSuspensions {
    println(test())
    /*println("Super test")
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
    }*/
    connectAndDownloadFiles()
}

fun connectAndDownloadFiles(){
    GlobalScope.launch {
        val tcpClient = createTcpClient("172.65.204.172", 12995, false)
        if(tcpClient.connected){
            println("We are connected!")
            tcpClient.write(dataFiles)
            val result = tcpClient.readAll()
        }
    }
}
