




package k0ras1k.utils;

import k0ras1k.api.IMTRecipeManager;
import k0ras1k.Main;
import com.google.common.collect.Lists;
import cpw.mods.fml.common.registry.GameData;
import cpw.mods.fml.common.registry.GameRegistry.UniqueIdentifier;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class MTRecipeManager implements IMTRecipeManager {
    public static List<MTRecipeRecord> transformerRecipes = Lists.newArrayList();
    public static MTRecipeManager instance = new MTRecipeManager();
    public static ArrayList<String> defaultRecipeList = new ArrayList();
    private static Method getUniqueName_Item;
    private static Method getUniqueName_Block;
    public static boolean rawReflectionDone = false;

    public MTRecipeManager() {
    }

    public void addMTOreDict(String name, String output, ItemStack catalizator, int energy) {
        List<ItemStack> inputs = OreDictionary.getOres(name);
        List<ItemStack> outputs = OreDictionary.getOres(output);
        if (outputs.size() != 0) {
            if (inputs.size() != 0) {
                Iterator i$ = inputs.iterator();

                while(i$.hasNext()) {
                    ItemStack inputIS = (ItemStack)i$.next();
                    i$ = outputs.iterator();

                    while(i$.hasNext()) {
                        ItemStack outputIS = (ItemStack)i$.next();
                        this.addMTRecipe(inputIS.copy(), outputIS.copy(), catalizator, energy);
                    }
                }

            }
        }
    }

    public void addMTOreDict(String input, ItemStack output, ItemStack catalizator, int energy) {
        List<ItemStack> inputs = OreDictionary.getOres(input);
        Iterator i$ = inputs.iterator();

        while(i$.hasNext()) {
            ItemStack inputIS = (ItemStack)i$.next();
            this.addMTRecipe(inputIS.copy(), output.copy(), catalizator, energy);
        }

    }

    public void addMTOreDict(ItemStack input, String output, ItemStack catalizator, int energy) {
        List<ItemStack> outputs = OreDictionary.getOres(output);
        if (outputs.size() != 0) {
            Iterator i$ = outputs.iterator();

            while(i$.hasNext()) {
                ItemStack outputIS = (ItemStack)i$.next();
                this.addMTRecipe(input.copy(), outputIS.copy(), catalizator, energy);
            }

        }
    }



    public void initRecipes() {
        transformerRecipes.clear();
        String configFilePath = Main.configFileName.substring(0, Main.configFileName.lastIndexOf(File.separatorChar) + 1);
        String tmpFileName = Main.configFileName.substring(Main.configFileName.lastIndexOf(File.separatorChar) + 1);
        String recipesFileName = tmpFileName.substring(0, tmpFileName.lastIndexOf("."));
        String recipesFileExt = tmpFileName.substring(tmpFileName.lastIndexOf("."));
        recipesFileName = recipesFileName + "_MTRecipes" + recipesFileExt;
        File file = new File(configFilePath, recipesFileName);
        transformerRecipes.clear();
        transformerRecipes.addAll(MTRecipeConfig.parse(file));
    }

    private static void lazyReflectionInit() {
        if (!rawReflectionDone) {
            try {
                getUniqueName_Item = GameData.class.getDeclaredMethod("getUniqueName", Item.class);
                getUniqueName_Block = GameData.class.getDeclaredMethod("getUniqueName", Block.class);
                getUniqueName_Item.setAccessible(true);
                getUniqueName_Block.setAccessible(true);
            } catch (Exception var1) {
                Main.addLog("Reflection failed. This is a fatal error and not recoverable");
                throw new RuntimeException(var1);
            }

            rawReflectionDone = true;
        }

    }

    public static MTRecipeManager.RawItemData getItemData(ItemStack is) {
        lazyReflectionInit();

        try {
            Item i = is.getItem();
            if (i instanceof ItemBlock) {
                Block b = Block.getBlockFromItem(i);
                UniqueIdentifier ui = (UniqueIdentifier)getUniqueName_Block.invoke((Object)null, b);
                return new MTRecipeManager.RawItemData(ui.modId, ui.name);
            } else {
                UniqueIdentifier ui = (UniqueIdentifier)getUniqueName_Item.invoke((Object)null, i);
                return new MTRecipeManager.RawItemData(ui.modId, ui.name);
            }
        } catch (Throwable var4) {
            Main.addLog("Reflection failed. Weird error, report it.");
            var4.printStackTrace();
            return null;
        }
    }

    public void addMTRecipe(ItemStack inputItem, ItemStack outputItem, ItemStack catalizatorItem, int energyPerOperation) {
        MTRecipeRecord recipeToAdd = new MTRecipeRecord();
        recipeToAdd.inputStack = inputItem.copy();
        recipeToAdd.outputStack = outputItem.copy();
        recipeToAdd.catalizatorStack = catalizatorItem.copy();
        recipeToAdd.energyPerOperation = energyPerOperation;
        transformerRecipes.add(recipeToAdd);
    }


    public static class RawItemData {
        public final String modId;
        public final String itemName;

        public RawItemData(String id, String name) {
            this.modId = id;
            this.itemName = name;
        }
    }
}
