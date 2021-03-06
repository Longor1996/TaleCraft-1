package de.longor.talecraft.voxelator.predicates;

import de.longor.talecraft.util.MutableBlockPos;
import de.longor.talecraft.voxelator.CachedWorldDiff;
import de.longor.talecraft.voxelator.VXPredicate;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;

public final class VXPredicateStateMatch extends VXPredicate {
	private final IBlockState type;

	public VXPredicateStateMatch(IBlockState type) {
		this.type = type;
	}

	@Override
	public boolean test(BlockPos pos, BlockPos center, MutableBlockPos offset, CachedWorldDiff fworld) {
		return fworld.getBlockState(pos).equals(type);
	}

}
