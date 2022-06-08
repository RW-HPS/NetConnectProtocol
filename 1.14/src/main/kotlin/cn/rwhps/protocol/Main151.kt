package cn.rwhps.protocol

import cn.rwhps.server.data.global.Data
import cn.rwhps.server.data.mods.ModManage
import cn.rwhps.server.mods.ModsLoad
import cn.rwhps.server.net.core.IRwHps
import cn.rwhps.server.net.core.ServiceLoader
import cn.rwhps.server.plugin.Plugin
import cn.rwhps.server.util.file.FileUtil
import cn.rwhps.server.util.log.Log

class Main151 : Plugin() {
    override fun init() {
        ServiceLoader.addService(ServiceLoader.ServiceType.ProtocolType, IRwHps.NetType.ServerProtocol.name,          TypeRwHps151::class.java)
        ServiceLoader.addService(ServiceLoader.ServiceType.Protocol,     IRwHps.NetType.ServerProtocol.name,          GameVersionServer151::class.java)
        ServiceLoader.addService(ServiceLoader.ServiceType.ProtocolPacket, IRwHps.NetType.ServerProtocol.name,        GameVersionPacket151::class.java)

        ModManage.clear()
        ModManage.load(FileUtil.getFolder(Data.Plugin_Mods_Path),"core_RW-HPS_units_114.zip",ModsLoad(Main151::class.java.getResourceAsStream("/core_RW-HPS_units_114.zip")!!))
        ModManage.loadUnits()

        Log.clog("1.14 Protocol Load: OK !")
    }
}