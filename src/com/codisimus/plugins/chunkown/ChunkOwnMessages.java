package com.codisimus.plugins.chunkown;

/**
 * Holds messages that are displayed to users of this plugin
 *
 * @author Codisimus
 */
public class ChunkOwnMessages {
    public static String permission;
    public static String doNotOwn;
    public static String claimed;
    public static String limit;
    public static String unclaimed;
    public static String buyFree;
    public static String insufficientFunds;
    public static String buy;
    public static String sell;
    public static String adminSell;
    public static String adminSold;
    public static String groupLand;
    public static String worldGuard;

    /**
     * Formats all Turnstile messages
     *
     */
    static void formatAll() {
        permission = format(permission);
        doNotOwn = format(doNotOwn);
        claimed = format(claimed);
        limit = format(limit);
        unclaimed = format(unclaimed);
        buyFree = format(buyFree);
        insufficientFunds = format(insufficientFunds);
        buy = format(buy);
        sell = format(sell);
        adminSell = format(adminSell);
        adminSold = format(adminSold);
        groupLand = format(groupLand);
        worldGuard = format(worldGuard);
    }

    /**
     * Adds various Unicode characters and colors to a string
     *
     * @param string The string being formated
     * @return The formatted String
     */
    private static String format(String string) {
        return string.replace("&", "§").replace("<ae>", "æ").replace("<AE>", "Æ")
                .replace("<o/>", "ø").replace("<O/>", "Ø")
                .replace("<a>", "å").replace("<A>", "Å");
    }
}
