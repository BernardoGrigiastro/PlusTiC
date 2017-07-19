package landmaster.plustic.modifiers;

import landmaster.plustic.api.*;
import landmaster.plustic.config.*;
import landmaster.plustic.util.*;
import net.minecraft.entity.*;
import net.minecraft.item.*;
import net.minecraft.nbt.*;
import net.minecraftforge.items.*;
import slimeknights.tconstruct.library.modifiers.*;
import slimeknights.tconstruct.library.utils.*;

public class ModEndlectric extends ModifierTrait {
	public static final int ENERGY_DRAW = 200;
	public static final ModEndlectric endlectric = new ModEndlectric();
	
	public ModEndlectric() {
		super("endlectric", 0x5AFCDC, 5, 0);
		Toggle.toggleable.add(identifier);
	}
	
	@Override
	public float damage(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, float newDamage, boolean isCritical) {
		energydraw:
		if (Toggle.getToggleState(tool, identifier)) {
			IItemHandler handler = player.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
			if (handler instanceof IItemHandlerModifiable) {
				for (int i=0; i<handler.getSlots(); ++i) {
					ItemStack is = handler.getStackInSlot(i).copy();
					if (!Config.isInEndlectricBlacklist(is)) {
						if (Utils.extractEnergy(is, ENERGY_DRAW, true) >= ENERGY_DRAW) {
							Utils.extractEnergy(is, ENERGY_DRAW, false);
							newDamage = augmentDamage(newDamage, TinkerUtil.getModifierTag(tool, identifier));
							((IItemHandlerModifiable) handler).setStackInSlot(i, is);
							break energydraw;
						}
					}
				}
			}
			/*
			if (Loader.isModLoaded("baubles") && player instanceof EntityPlayer) {
				IBaublesItemHandler ib = BaublesApi.getBaublesHandler((EntityPlayer)player);
				for (int i=0; i<ib.getSlots(); ++i) {
					ItemStack is = ib.getStackInSlot(i);
					is = ItemStackTools.safeCopy(is);
					if (!Config.isInEndlectricBlacklist(is)) {
						if (Utils.extractEnergy(is, ENERGY_DRAW, true) >= ENERGY_DRAW) {
							Utils.extractEnergy(is, ENERGY_DRAW, false);
							newDamage = augmentDamage(newDamage, TinkerUtil.getModifierTag(tool, identifier));
							ib.setStackInSlot(i, is);
							break energydraw;
						}
					}
				}
			}
			*/
		}
		return super.damage(tool, player, target, damage, newDamage, isCritical);
	}
	
	private float augmentDamage(float old, NBTTagCompound modifierTag) {
		ModifierNBT data = ModifierNBT.readTag(modifierTag);
		return old + 5*data.level;
	}
}
