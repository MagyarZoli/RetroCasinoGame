package list;

import java.awt.Color;

/**
 * The color list contains the foreground and background colors of the specified components,
 * the program does not modify the colors.
 * Contains arrays based on which I can request the colors of the components.
 */
public final class ColorList {

    /**
     * frame background color
     */
    public final static Color FRAME_BACKGROUND = new Color(0,0,0);

    /**
     * background color behind moving images.
     */
    public final static Color GAME_BACKGROUND = new Color(20,20,20);

    /**
     * Github link color
     */
    public final static Color GITHUB_FOREGROUND = new Color(92,107,192);

    /*
     * components color list stored in different arrays
     */
    private final static Color SPIN_FOREGROUND = new Color(232,126,148);
    private final static Color SPIN_BACKGROUND = new Color(138,26,25);
    private final static Color BET_FOREGROUND = new Color(232,126,148);
    private final static Color BET_BACKGROUND = new Color(138,26,25);
    private final static Color CASHSTR_FOREGROUND = new Color(232,126,148);
    private final static Color CASH_FOREGROUND = new Color(232,126,148);
    private final static Color CASH_BACKGROUND = new Color(138,26,25);
    private final static Color PROFITSTR_FOREGROUND = new Color(232,126,148);
    private final static Color PROFIT_FOREGROUND = new Color(232,126,148);
    private final static Color PROFIT_BACKGROUND = new Color(138,26,25);
    private final static Color BET1_FOREGROUND = new Color(20, 190, 20);
    private final static Color BET2_FOREGROUND = new Color(30, 50, 130);
    private final static Color BET3_FOREGROUND = new Color(150, 150, 30);
    private final static Color BET4_FOREGROUND = new Color(150, 30, 30);
    private final static Color BET5_FOREGROUND = new Color(100, 50, 170);

    /**
     * contains the color of the 5 columns of bets label.
     */
    public final static Color[] BETS_FOREGROUND = new Color[]{
        BET1_FOREGROUND,
        BET2_FOREGROUND,
        BET3_FOREGROUND,
        BET4_FOREGROUND,
        BET5_FOREGROUND
    };

    /**
     * contains the foreground color of bank label 4 rows.
     */
    public final static Color[] BANK_FOREGROUND = new Color[]{
        CASHSTR_FOREGROUND,
        CASH_FOREGROUND,
        PROFITSTR_FOREGROUND,
        PROFIT_FOREGROUND
    };

    /**
     * contains the background color of bank label 4 rows.
     */
    public final static Color[] BANK_BACKGROUND = new Color[]{
        FRAME_BACKGROUND,
        CASH_BACKGROUND,
        FRAME_BACKGROUND,
        PROFIT_BACKGROUND
    };

    /**
     * Includes 2 button foreground colors.
     */
    public final static Color[] BUTTON_FOREGROUND = new Color[]{
        BET_FOREGROUND,
        SPIN_FOREGROUND
    };

    /**
     * Includes 2 button background colors.
     */
    public final static Color[] BUTTON_BACKGROUND = new Color[]{
        BET_BACKGROUND,
        SPIN_BACKGROUND
    };
}
