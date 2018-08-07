package redstonepcb;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRedstoneWire;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventHandler {
    @SubscribeEvent
    public void blockNeighborUpdate(BlockEvent.NeighborNotifyEvent notifyEvent) {
        World world = notifyEvent.getWorld();
        makeRedstonePCB(world, notifyEvent.getPos());
    }

    private void makeRedstonePCB(World world, BlockPos pos) {
        IBlockState blockstate = world.getBlockState(pos);
        Block block = blockstate.getBlock();
        Block bottomblock = world.getBlockState(pos.down()).getBlock();
        if (!world.isRemote && block instanceof BlockRedstoneWire && bottomblock instanceof BlockPCB) {
            world.setBlockState(pos, RedstonePCB.PCB_WIRE.getDefaultState());
        }
    }
}
