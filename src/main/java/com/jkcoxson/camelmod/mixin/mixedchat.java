package com.jkcoxson.camelmod.mixin;

import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayNetworkHandler.class)
public class mixedchat {

    @Shadow public ServerPlayerEntity player;

    @Inject(
            method = ("method_31286"),
            at = {@At("HEAD")},
            cancellable = true
    )
    public void asdf(String packet, CallbackInfo ci) {
        if (!packet.startsWith("/")){
            String toSend = "<";
            toSend += this.player.getEntityName();
            toSend = toSend + "> ";
            toSend = toSend + packet;
            System.out.println(toSend);
            ci.cancel();
        }else {
            // Let's not cancel command this time
        }

    }
}
