/*
 * Copyright 2020-2022 RW-HPS Team and contributors.
 *
 * 此源代码的使用受 GNU AFFERO GENERAL PUBLIC LICENSE version 3 许可证的约束, 可以在以下链接找到该许可证.
 * Use of this source code is governed by the GNU AGPLv3 license that can be found through the following link.
 *
 * https://github.com/RW-HPS/RW-HPS/blob/master/LICENSE
 */

package cn.rwhps.protocol

import cn.rwhps.server.data.global.Data
import cn.rwhps.server.data.player.Player
import cn.rwhps.server.io.GameOutputStream
import cn.rwhps.server.io.packet.Packet
import cn.rwhps.server.net.netconnectprotocol.realize.GameVersionPacket
import cn.rwhps.server.util.IsUtil
import cn.rwhps.server.util.PacketType
import cn.rwhps.server.util.encryption.Game
import cn.rwhps.server.util.encryption.Sha
import java.io.IOException
import java.math.BigInteger

/**
 * @author RW-HPS/Dr
 */
class GameVersionPacket151 : GameVersionPacket() {
    @Throws(IOException::class)
    override fun writePlayer(player: Player, stream: GameOutputStream) {
        if (Data.game.isStartGame) {
            stream.writeByte(player.site)
            stream.writeInt(player.ping)
            stream.writeBoolean(Data.game.sharedControl)
            stream.writeBoolean(player.controlThePlayer)
            return
        }
        stream.writeByte(player.site)
        // 并没有什么用
        stream.writeInt(player.credits)
        stream.writeInt(player.team)
        stream.writeBoolean(true)
        stream.writeString(player.name)
        stream.writeBoolean(false)

        /* -1 N/A  -2 -   -99 HOST */
        stream.writeInt(player.ping)
        stream.writeLong(System.currentTimeMillis())

        /* Is AI */
        stream.writeBoolean(false)
        /* AI Difficu */
        stream.writeInt(0)

        stream.writeInt(player.site)
        stream.writeByte(0)

        /* 共享控制 */
        stream.writeBoolean(Data.game.sharedControl)
        /* 是否掉线 */
        stream.writeBoolean(player.sharedControl)

        /* 是否投降 */
        stream.writeBoolean(false)
        stream.writeBoolean(false)
        stream.writeInt(-9999)
        stream.writeBoolean(false)
        // 延迟后显示 （HOST) [房主]
        stream.writeInt(if (player.isAdmin) 1 else 0)
    }

    @Throws(IOException::class)
    override fun getPlayerConnectPacket(): Packet {
        val out = GameOutputStream()
        out.writeString("com.corrodinggames.rwhps.forward")
        out.writeInt(1)
        out.writeInt(151)
        out.writeInt(151)
        return out.createPacket(PacketType.PREREGISTER_INFO_RECEIVE)
    }

    @Throws(IOException::class)
    override fun getPlayerRegisterPacket(name: String, uuid: String, passwd: String?, key: Int): Packet {
        val out = GameOutputStream()
        out.writeString("com.corrodinggames.rts")
        out.writeInt(4)
        out.writeInt(151)
        out.writeInt(151)
        out.writeString(name)

        if (IsUtil.isBlank(passwd)) {
            out.writeBoolean(false)
        } else {
            out.writeBoolean(true)
            out.writeString(BigInteger(1, Sha.sha256Array(passwd!!)).toString(16).uppercase())
        }

        out.writeString("com.corrodinggames.rts.java")
        out.writeString(uuid)
        out.writeInt(1198432602)
        out.writeString(Game.connectKey(key))
        return out.createPacket(PacketType.REGISTER_PLAYER)
    }
}