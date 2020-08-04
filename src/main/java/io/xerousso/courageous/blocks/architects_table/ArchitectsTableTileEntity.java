//package io.xerousso.courageous.blocks.architects_table;
//
//import io.xerousso.courageous.blocks.ModTileEntities;
//import net.minecraft.block.Block;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.entity.player.PlayerInventory;
//import net.minecraft.inventory.container.Container;
//import net.minecraft.inventory.container.INamedContainerProvider;
//import net.minecraft.item.ItemStack;
//import net.minecraft.nbt.CompoundNBT;
//import net.minecraft.network.NetworkManager;
//import net.minecraft.network.play.server.SUpdateTileEntityPacket;
//import net.minecraft.tileentity.TileEntity;
//import net.minecraft.util.Direction;
//import net.minecraft.util.text.ITextComponent;
//import net.minecraft.util.text.TranslationTextComponent;
//import net.minecraftforge.common.capabilities.Capability;
//import net.minecraftforge.common.util.INBTSerializable;
//import net.minecraftforge.common.util.LazyOptional;
//import net.minecraftforge.items.CapabilityItemHandler;
//import net.minecraftforge.items.ItemStackHandler;
//
//import javax.annotation.Nonnull;
//import javax.annotation.Nullable;
//
//public class ArchitectsTableTileEntity extends TileEntity implements INamedContainerProvider {
//
//    private LazyOptional<ItemStackHandler> handler = LazyOptional.of(() -> getHandler()).cast();
//
//
//    public ArchitectsTableTileEntity() {
//        super(ModTileEntities.ARCHITECTS_TABLE);
//    }
//
//    @Override
//    public void remove() {
//        handler.ifPresent(h -> {
//            Block.spawnAsEntity(world, pos, h.getStackInSlot(0));
//            Block.spawnAsEntity(world, pos, h.getStackInSlot(1));
//        });
//
//        super.remove();
//    }
//
//    // ---- NBT ----
////    @Override
////    public void read(CompoundNBT tag) {
////        CompoundNBT invTag = tag.getCompound("inv");
////
////        handler.ifPresent(h -> ((INBTSerializable<CompoundNBT>) h).deserializeNBT(invTag));
////
////        super.read(tag);
////    }
//
//    @Override
//    public CompoundNBT write(CompoundNBT tag) {
//        handler.ifPresent(h -> {
//            CompoundNBT compound = ((INBTSerializable<CompoundNBT>) h).serializeNBT();
//            tag.put("inv", compound);
//        });
//
//        return super.write(tag);
//    }
//
//
//    @Override
//    public CompoundNBT getUpdateTag() {
//        return write(super.getUpdateTag());
//    }
//
//    @Nullable
//    @Override
//    public SUpdateTileEntityPacket getUpdatePacket() {
//        CompoundNBT nbt = new CompoundNBT();
//        write(nbt);
//
//        return new SUpdateTileEntityPacket(getPos(), 1, nbt);
//    }
//
////    @Override
////    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
////        read(pkt.getNbtCompound());
////    }
//
//    // ---- Capabilities ----
//    private ItemStackHandler getHandler() {
//        return new ItemStackHandler(37) {
//            @Override
//            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
//                return slot == 0;
//            }
//
//            @Nonnull
//            @Override
//            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
//                if (!isItemValid(slot, stack)) return stack;
//                return super.insertItem(slot, stack, simulate);
//            }
//        };
//    }
//
//    @Nonnull
//    @Override
//    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
//        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return handler.cast();
//        return super.getCapability(cap, side);
//    }
//
//    // --- Container stuff ---
//    @Override
//    public ITextComponent getDisplayName() {
//        return new TranslationTextComponent("block.courageous.architects_table");
//    }
//
//    @Nullable
//    @Override
//    public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
//        return new ArchitectsTableContainer(i, world, pos, playerInventory);
//    }
//
//}