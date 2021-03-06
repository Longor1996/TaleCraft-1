package de.longor.talecraft.voxelator.predicates;

import de.longor.talecraft.util.MutableBlockPos;
import de.longor.talecraft.voxelator.CachedWorldDiff;
import de.longor.talecraft.voxelator.VXPredicate;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;

public final class VXPredicateBoxSmooth extends VXPredicate {
	private final int size;
	private final double sizeSquared;
	private final Vec3i vec;

	public VXPredicateBoxSmooth(int size) {
		this.size = size;
		this.sizeSquared = size * size;
		this.vec = new Vec3i(size, size-1, size);
	}

	@Override
	public boolean test(BlockPos pos, BlockPos center, MutableBlockPos offset, CachedWorldDiff fworld) {
		int total = 0;
		float value = 0;
		Iterable<BlockPos.MutableBlockPos> iterable = BlockPos.getAllInBoxMutable(pos.subtract(vec), pos.add(vec));
		for(final BlockPos checkpos : iterable) {
			if(!fworld.isAirBlock(checkpos)) {
				value += checkpos.distanceSq(pos) / sizeSquared;
			}
			total++;
		}

		value /= total;
		value *= value;

		return value > 0.35f;
	}

}
