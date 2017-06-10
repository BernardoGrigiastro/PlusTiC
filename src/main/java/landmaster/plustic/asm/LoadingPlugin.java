package landmaster.plustic.asm;

import java.util.*;

import landmaster.plustic.api.*;
import net.minecraftforge.fml.relauncher.*;

@IFMLLoadingPlugin.Name(ModInfo.NAME)
@IFMLLoadingPlugin.MCVersion(value = "1.10.2") // no need in 1.11.2
@IFMLLoadingPlugin.TransformerExclusions({"landmaster.plustic.asm"})
@IFMLLoadingPlugin.SortingIndex(1)
public class LoadingPlugin implements IFMLLoadingPlugin {
	@Override
	public String[] getASMTransformerClass() {
		return new String[] {Transform.class.getName()};
	}
	
	@Override
	public String getModContainerClass() {
		return null;
	}
	
	@Override
	public String getSetupClass() {
		return null;
	}
	
	@Override
	public void injectData(Map<String, Object> data) {
	}
	
	@Override
	public String getAccessTransformerClass() {
		return null;
	}
}
