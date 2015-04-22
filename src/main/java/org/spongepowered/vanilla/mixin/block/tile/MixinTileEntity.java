/*
 * This file is part of Sponge, licensed under the MIT License (MIT).
 *
 * Copyright (c) SpongePowered <https://www.spongepowered.org>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.spongepowered.vanilla.mixin.block.tile;

import net.minecraft.nbt.NBTTagCompound;
import org.spongepowered.api.block.tile.TileEntity;
import org.spongepowered.api.util.annotation.NonnullByDefault;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.common.interfaces.IMixinTileEntity;

@NonnullByDefault
@Mixin(net.minecraft.tileentity.TileEntity.class)
public abstract class MixinTileEntity implements TileEntity, IMixinTileEntity {

    private NBTTagCompound customEntityData;

    /**
     * Gets the SpongeData NBT tag, used for additional data not stored in the
     * vanilla tag.
     *
     * <p>
     * Modifying this tag will affect the data stored.
     * </p>
     *
     * @return The data tag
     */
    @Override
    public final NBTTagCompound getSpongeData() {
        if (this.customEntityData == null) {
            this.customEntityData = new NBTTagCompound();
        }

        if (!customEntityData.hasKey("SpongeData", 10)) {
            customEntityData.setTag("SpongeData", new NBTTagCompound());
        }
        return customEntityData.getCompoundTag("SpongeData");
    }
}