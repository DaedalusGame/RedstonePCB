package redstonepcb;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.client.CustomModLoadingErrorDisplayException;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

@Mod(modid = RedstonePCB.MODID, acceptedMinecraftVersions = "[1.12, 1.13)")
@Mod.EventBusSubscriber
public class RedstonePCB
{
    public static final String MODID = "redstonepcb";
    public static final String NAME = "RedstonePCB";

    @SidedProxy(clientSide = "redstonepcb.ClientProxy",serverSide = "redstonepcb.ServerProxy")
    public static IProxy proxy;

    @GameRegistry.ObjectHolder("redstonepcb:pcb_wire")
    public static BlockWirePCB PCB_WIRE;
    @GameRegistry.ObjectHolder("redstonepcb:pcb_block")
    public static BlockPCB PCB_BLOCK;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        proxy.preInit();
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new EventHandler());
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init();
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().register(PCB_BLOCK = (BlockPCB) new BlockPCB().setRegistryName(new ResourceLocation(RedstonePCB.MODID, "pcb_block")));
        event.getRegistry().register(PCB_WIRE = (BlockWirePCB) new BlockWirePCB().setRegistryName(new ResourceLocation(RedstonePCB.MODID, "pcb_wire")));
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new ItemBlock(PCB_BLOCK).setRegistryName(new ResourceLocation(RedstonePCB.MODID, "pcb_block")));
    }
}
