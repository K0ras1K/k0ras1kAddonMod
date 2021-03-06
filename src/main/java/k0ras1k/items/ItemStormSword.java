package k0ras1k.items;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import k0ras1k.items.electric.BaseElectricSword;
import k0ras1k.materials.ItemToolMaterial;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import k0ras1k.Main;

public class ItemStormSword extends BaseElectricSword {

    private IIcon[] textures;


    public ItemStormSword(String name, String texture) {
        super(ItemToolMaterial.stormSkySword, 3, 100000, 1000, false);
        this.setUnlocalizedName(name);
        this.setCreativeTab(Main.lightBlocks);
        this.setMaxStackSize(1);
        this.setTextureName(texture);

    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister ir) {
        this.textures = new IIcon[2];
        this.textures[0] = ir.registerIcon("k0ras1k:stormSkySword0");
        this.textures[1] = ir.registerIcon("k0ras1k:stormSkySword1");
    }

    public IIcon getIcon(ItemStack stack, int pass) {
        NBTTagCompound tags = stack.getTagCompound();
        if (tags != null && tags.getBoolean("0")) {
            return this.textures[0];
        }
        return this.textures[1];
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconIndex(ItemStack stack) {
        return this.getIcon(stack, 0);
    }


    @Override
    public Multimap getAttributeModifiers(ItemStack itemStack) {
        int dmg = 10;
        NBTTagCompound nbtData = itemStack.getTagCompound();
        if (nbtData == null) {
            nbtData = new NBTTagCompound();
            itemStack.setTagCompound(nbtData);
        }
        if (nbtData.getBoolean("0")) {
            dmg = 100;
        }
        Multimap<String, AttributeModifier> ret = HashMultimap.create();
        ret.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Tool modifier", (double) dmg, 0));
        return ret;
    }

    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (player.isSneaking()) {
            NBTTagCompound tags = stack.getTagCompound();
            if (tags == null) {
                tags = new NBTTagCompound();
                stack.setTagCompound(tags);
            }

            tags.setBoolean("0", !tags.getBoolean("0"));
            player.swingItem();
        }
        return stack;
    }

    @Override
    public boolean hitEntity(ItemStack itemStack, EntityLivingBase player, EntityLivingBase entity) {
        NBTTagCompound nbtData = itemStack.getTagCompound();
        if (nbtData != null && nbtData.getBoolean("0")) {
            if (itemStack.stackTagCompound.getInteger("charge") > 1000) {
                this.useEnergy(itemStack, 1000);
            }
        } else {
            if (nbtData != null) {
                if (itemStack.stackTagCompound.getInteger("charge") > 100) {
                    this.useEnergy(itemStack, 100);
                }
            }
        }
        return false;
    }
}
