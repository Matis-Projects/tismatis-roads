package net.tismatis.tismatisroads.items;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.s2c.play.BlockUpdateS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;
import net.tismatis.tismatisroads.blocks.MessageBoardBlockEntity;
import net.tismatis.tismatisroads.network.NetworkConstants;

public class MessageBoardEditItem extends Item {

    public MessageBoardEditItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        BlockEntity entity = context.getWorld().getBlockEntity(context.getBlockPos());
        PlayerEntity player = context.getPlayer();
        World world = context.getWorld();
        if(!world.isClient && (entity instanceof MessageBoardBlockEntity sign))
        {
            if (player instanceof ServerPlayerEntity serverPlayer) {
                serverPlayer.networkHandler.sendPacket(new BlockUpdateS2CPacket(world, sign.getPos()));

                PacketByteBuf buf = PacketByteBufs.create();
                buf.writeBlockPos(sign.getPos());
                ServerPlayNetworking.send(serverPlayer, NetworkConstants.OPEN_MESSAGE_BOARD_S2C, buf);
            }
        }

        return super.useOnBlock(context);
    }
}
