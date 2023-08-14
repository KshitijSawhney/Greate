package electrolyte.greate;

import com.mojang.logging.LogUtils;
import com.simibubi.create.foundation.data.CreateRegistrate;
import dev.toma.configuration.Configuration;
import dev.toma.configuration.config.format.ConfigFormats;
import electrolyte.greate.registry.*;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(Greate.MOD_ID)
public class Greate {

    public static final String MOD_ID = "greate";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static GreateConfig CONFIG;
    public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(Greate.MOD_ID);

    public Greate() {
        CONFIG = Configuration.registerConfig(GreateConfig.class, ConfigFormats.yaml()).getConfigInstance();
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        REGISTRATE.registerEventListeners(eventBus);
        GreateLang.register();
        Cogwheels.register();
        Girders.register();
        Millstones.register();
        Shafts.register();
        ModBlockEntityTypes.register();
        ModItems.register();
        ModRecipeTypes.register(eventBus);
    }

    public static final CreativeModeTab GREATE_TAB = new CreativeModeTab("greate") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Items.GOLDEN_APPLE);
        }
    };

    public void clientSetup(FMLClientSetupEvent event) {
        GreatePartialModels.register();
    }
}
