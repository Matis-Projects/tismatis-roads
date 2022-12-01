package net.tismatis.tismatisroads.blocks;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class MessageBoard extends BlockWithEntity {

    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    public static IntProperty BASE_ID = IntProperty.of("baseid", 0, 3);

    public MessageBoard(Settings settings) {
        super(settings.nonOpaque());
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH).with(BASE_ID, 0));
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getPlayerFacing().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, BASE_ID);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new MessageBoardBlockEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack itemStack = player.getStackInHand(hand);
        Item item = itemStack.getItem();
        boolean isDye = item instanceof DyeItem;
        boolean isGlowInk = itemStack.isOf(Items.GLOW_INK_SAC);
        boolean isNormalInk = itemStack.isOf(Items.INK_SAC);
        boolean allCombined = (isGlowInk || isDye || isNormalInk) && player.getAbilities().allowModifyWorld;
        if (world.isClient) {
            return allCombined ? ActionResult.SUCCESS : ActionResult.CONSUME;
        } else {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof MessageBoardBlockEntity signBlockEntity) {
                boolean bl5 = signBlockEntity.isGlowingText();
                if ((!isGlowInk || !bl5) && (!isNormalInk || bl5)) {
                    if (allCombined) {
                        boolean isGlowing;
                        if (isGlowInk) {
                            world.playSound(null, pos, SoundEvents.ITEM_GLOW_INK_SAC_USE, SoundCategory.BLOCKS, 1.0F, 1.0F);
                            isGlowing = signBlockEntity.setGlowingText(true);
                            if (player instanceof ServerPlayerEntity) {
                                Criteria.ITEM_USED_ON_BLOCK.trigger((ServerPlayerEntity) player, pos, itemStack);
                            }
                        } else if (isNormalInk) {
                            world.playSound(null, pos, SoundEvents.ITEM_INK_SAC_USE, SoundCategory.BLOCKS, 1.0F, 1.0F);
                            isGlowing = signBlockEntity.setGlowingText(false);
                        } else {
                            world.playSound(null, pos, SoundEvents.ITEM_DYE_USE, SoundCategory.BLOCKS, 1.0F, 1.0F);
                            isGlowing = signBlockEntity.setTextColor(((DyeItem) item).getColor());
                        }

                        if (isGlowing) {
                            if (!player.isCreative()) {
                                itemStack.decrement(1);
                            }

                            player.incrementStat(Stats.USED.getOrCreateStat(item));
                        }
                    }
                }
            }
            return ActionResult.PASS;
        }
    }
}
