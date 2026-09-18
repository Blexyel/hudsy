package wtf.blexyel.hudsy;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import wtf.blexyel.hudsy.network.SSUPayload;
import wtf.blexyel.hudsy.network.TpsPayload;

public class HudsyClient implements ClientModInitializer {
  @Override
  public void onInitializeClient() {
    PayloadTypeRegistry.clientboundPlay().register(TpsPayload.TYPE, TpsPayload.CODEC);
    PayloadTypeRegistry.clientboundPlay().register(SSUPayload.TYPE, SSUPayload.CODEC);
  }
}
