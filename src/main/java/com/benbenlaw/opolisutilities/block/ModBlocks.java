package com.benbenlaw.opolisutilities.block;

import com.benbenlaw.opolisutilities.OpolisUtilities;
import com.benbenlaw.opolisutilities.block.custom.DryingTableBlock;
import com.benbenlaw.opolisutilities.block.custom.ResourceGeneratorBlock;
import com.benbenlaw.opolisutilities.item.ModCreativeModTab;
import com.benbenlaw.opolisutilities.item.ModItems;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, OpolisUtilities.MOD_ID);

    public static final RegistryObject<Block> FLOATING_BLOCK = registerBlockWithoutBlockItem("floating_block_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.WOOL)
                    .instabreak()));

    public static final RegistryObject<Block> DRYING_TABLE = registerBlock("drying_table",
            () -> new DryingTableBlock(BlockBehaviour.Properties.of(Material.WOOD)
                    .strength(2f,2f)
                    .noOcclusion()),
                    ModCreativeModTab.OPOLIS_UTILITIES);

    public static final RegistryObject<Block> RESOURCE_GENERATOR = registerBlock("resource_generator",
            () -> new ResourceGeneratorBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(2f,2f)
                    .noOcclusion()),
                    ModCreativeModTab.OPOLIS_UTILITIES);



















    private static <T extends Block> RegistryObject<T> registerBlockWithoutBlockItem(String name, Supplier<T> block) {
        return BLOCKS.register(name, block);
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block,
                                                                     CreativeModeTab tab, String tooltipKey) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab, tooltipKey);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block,
                                                                            CreativeModeTab tab, String tooltipKey) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(tab)) {
            @Override
            public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
                pTooltip.add(Component.literal(tooltipKey));
            }
        });
    }




    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block,
                                                                            CreativeModeTab tab) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}