package de.longor.talecraft.items;

import java.util.List;

import de.longor.talecraft.TaleCraftTabs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TCItem extends Item {

	public TCItem() {
		this.setCreativeTab(TaleCraftTabs.tab_TaleCraftTab);
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		if(world.isRemote)
			return EnumActionResult.PASS;

		world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), 0);

		return EnumActionResult.SUCCESS;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player, EnumHand hand) {
		if(world.isRemote)
			return ActionResult.newResult(EnumActionResult.PASS, stack);

		return ActionResult.newResult(EnumActionResult.SUCCESS, stack);
	}

	@Override
	public boolean isDamageable() {
		return false;
	}

	@Override
	// Warning: Forge Method
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		// By returning TRUE, we prevent damaging the entity being hit.
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean isFull3D() {
		return true;
	}

	@Override
	public boolean isItemTool(ItemStack stack) {
		return true;
	}

	@Override
	public boolean canHarvestBlock(IBlockState state) {
		return false;
	}

	@Override
	public boolean canItemEditBlocks() {
		return false;
	}

	@Override
	public boolean onBlockStartBreak(ItemStack stack, BlockPos pos, EntityPlayer player) {
		player.worldObj.notifyBlockUpdate(pos, player.worldObj.getBlockState(pos), player.worldObj.getBlockState(pos), 0);
		return true;
	}

	@Override
	public int getItemStackLimit(ItemStack stack) {
		return 1;
	}

}
