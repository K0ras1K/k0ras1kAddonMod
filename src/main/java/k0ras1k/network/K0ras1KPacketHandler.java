package k0ras1k.network;

import com.google.common.collect.Lists;
import cpw.mods.fml.common.network.FMLEmbeddedChannel;
import cpw.mods.fml.common.network.FMLOutboundHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.Side;
import io.netty.channel.ChannelHandler;
import net.minecraft.entity.player.EntityPlayer;

import java.util.EnumMap;
import java.util.List;

public class K0ras1KPacketHandler {
    public static List<Class<? extends IPacket>> packetTypes = Lists.newArrayList();
    private static EnumMap<Side, FMLEmbeddedChannel> channels;

    public K0ras1KPacketHandler() {
    }

    public static void load() {
        registerPacketType(PacketGUIPressButton.class);
        registerPacketType(PacketChangeState.class);
        channels = NetworkRegistry.INSTANCE.newChannel("k0ras1k", new ChannelHandler[]{new K0ras1KChannelHandler()});
    }

    public static void registerPacketType(Class<? extends IPacket> ptype) {
        packetTypes.add(ptype);
    }

    public static void sendToAllPlayers(IPacket packet) {
        ((FMLEmbeddedChannel)channels.get(Side.SERVER)).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.ALL);
        ((FMLEmbeddedChannel)channels.get(Side.SERVER)).writeOutbound(new Object[]{packet});
    }

    public static void sendToServer(IPacket packet) {
        ((FMLEmbeddedChannel)channels.get(Side.CLIENT)).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.TOSERVER);
        ((FMLEmbeddedChannel)channels.get(Side.CLIENT)).writeOutbound(new Object[]{packet});
    }

    public static void sendToPlayer(EntityPlayer ep, IPacket packet) {
        ((FMLEmbeddedChannel)channels.get(Side.SERVER)).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.PLAYER);
        ((FMLEmbeddedChannel)channels.get(Side.SERVER)).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(ep);
        ((FMLEmbeddedChannel)channels.get(Side.SERVER)).writeOutbound(new Object[]{packet});
    }
}
