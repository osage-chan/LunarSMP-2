package light.breeze.commands;

import light.breeze.cosmetics.hats.DevHat;
import light.breeze.cosmetics.hats.FeatherHat;
import light.breeze.cosmetics.hats.TopHat;
import light.breeze.cosmetics.hats.WitchHat;
import light.breeze.items.burningaxe.BurningAxe;
import light.breeze.items.echobow.Echobow;
import light.breeze.items.endingot.EndIngot;
import light.breeze.items.endpickaxe.EndPickaxe;
import light.breeze.items.featherfalltotem.TotemOfFeatherfall;
import light.breeze.items.small_potion.SmallPotion;
import light.breeze.items.wardenbound.WardenBound;
import light.breeze.items.withersword.WitherSword;
import light.breeze.utils.Utils;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class CustomGive implements CommandExecutor {

    public Map<String, ItemStack> getCustomItems() {
        Map<String, ItemStack> customItems = new HashMap<>();
        customItems.put("wardenbound_sword", new WardenBound().createWardenBound());
        customItems.put("burning_axe", new BurningAxe().createBurningAxe());
        customItems.put("totem_of_featherfall", new TotemOfFeatherfall().createTOF());
        customItems.put("end_ingot", new EndIngot().createEndIngot());
        customItems.put("wither_sword", new WitherSword().createWitherSword());
        customItems.put("echo_bow", new Echobow().createEchobow("50"));

        ItemStack ebna = new Echobow().createEchobow("1");
        ebna.addUnsafeEnchantment(Enchantment.SILK_TOUCH,1);
        customItems.put("eb_noammo",ebna);

        customItems.put("end_pickaxe", new EndPickaxe().createEndPickaxe("1600"));

        ItemStack uep = new EndPickaxe().createEndPickaxe("1");
        uep.addUnsafeEnchantment(Enchantment.PIERCING,1);
        customItems.put("event_end_pickaxe",uep);

        customItems.put("small_potion", new SmallPotion().createSmallPotion(Material.BUCKET));
        customItems.put("small_potion_of_flight", new SmallPotion().createSmallPotionWithFly());

        customItems.put("devhat", new DevHat().createDevHat());
        customItems.put("featherhat", new FeatherHat().createFeatherHat());
        customItems.put("tophat", new TopHat().createTopHat());
        customItems.put("witchhat", new WitchHat().createWitchHat());
        return customItems;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Map<String, ItemStack> customItems = getCustomItems();

        if (customItems.containsKey(args[0])) {
            Player player = null;
            if (args.length > 1) {
                player = Utils.getPlayer(args[1]);
            } else {
                player = (Player) sender;
            }
            player.getInventory().addItem(customItems.get(args[0]));
            sender.sendMessage("Gave " + sender.getName() + " 1 ["  + args[0] + "].");
            player.sendMessage("Recieved [" + args[0] + "] from "  + sender.getName() + ".");
        } else {
            sender.sendMessage("Item " + args[0] + " doesn't exist.");
            return false;
        }
        return true;
    }
}
