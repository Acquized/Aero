package cc.acquized.aero.features;

import cc.acquized.aero.file.Config;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;

public class Gapple {

    public static void addRecipe() {
        if(Config.getConfig().getBoolean("Features.CraftableOpGapple")) {
            ShapedRecipe recipe = new ShapedRecipe(new ItemStack(Material.GOLDEN_APPLE, 1, (short) 1));
            recipe.shape("ggg", "gag", "ggg");
            recipe.setIngredient('g', Material.GOLD_BLOCK);
            recipe.setIngredient('a', Material.APPLE);
            Bukkit.addRecipe(recipe);
        }
    }

}
