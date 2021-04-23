package com.jkcoxson.camelmod.mixin;

import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import com.jkcoxson.camelmod.tcamelp;

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
            if (tcamelp.camalized){
                try{
                    String toSend = "<";
                    toSend += this.player.getEntityName();
                    toSend = toSend + "> ";
                    toSend = toSend + packet;
                    System.out.println(toSend);
                    String toYeet = "{\"packet\":\"chat\",\"chat\":\"**"+this.player.getEntityName()+":** "+packet+"\"}";
                    tcamelp.Yeet(toYeet);
                    ci.cancel();
                }catch (Exception e){

                }

            }
        }else {
            // Let's not cancel command this time
        }

    }
}
