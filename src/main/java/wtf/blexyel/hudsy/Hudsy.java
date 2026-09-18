package wtf.blexyel.hudsy;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wtf.blexyel.hudsy.config.Config;
import wtf.blexyel.hudsy.config.YACLConfig;

public final class Hudsy implements ModInitializer {
  public static final String MOD_ID = "hudsy";

  public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

  public void onInitialize() {
    // Write common init code here.
    LOGGER.info("Hudsy (formerly known as simplehud) is alive!!");

    Config.migrate();
    Config.HANDLER.load();
    Keybindings.init();

    ClientLifecycleEvents.CLIENT_STARTED.register(
        (minecraft) -> {
          TpsCalc.reset();
        });

    // Register client stop / disconnect hook
    ClientLifecycleEvents.CLIENT_STOPPING.register(
        minecraft -> {
          TpsCalc.reset();
        });

    ClientTickEvents.END_CLIENT_TICK.register(
        mc -> {
          while (wtf.blexyel.hudsy.Keybindings.ENABLED_KEY.consumeClick()) {
            Config.enabled = !Config.enabled;
            Config.HANDLER.save();
          }
          while (Keybindings.CONFIG_KEY.consumeClick()) {
            mc.setScreenAndShow(YACLConfig.create(mc.gui.screen()));
          }
        });
  }
}
