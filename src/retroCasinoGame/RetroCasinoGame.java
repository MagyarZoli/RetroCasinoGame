package retroCasinoGame;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.Timer;
import javax.swing.JLabel;
import javax.swing.plaf.basic.BasicButtonUI;

import list.ColorList;
import list.ImageList;
import mz.ComponentsArray;
import mz.ResponsiveFrame;

/**
 * Retro Casino Game 3 of the same picture draws a prize,
 * the draws depend on the size of the bet.
 * The game continues until the amount is 0.
 * Game elements can also be modified after the fact, the values of the classes in the list package,
 * other images, other colors can be specified,
 * plus the starting value, rotation speed, the size of the bet's prize.
 * @see list.ColorList
 * @see list.ImageList
 * @see mz.ComponentsArray
 * @see mz.ResponsiveFrame
 * @see retroCasinoGame.GamePanel
 * @see retroCasinoGame.ImagePanel
 * @see retroCasinoGame.Label
 * @see retroCasinoGame.Button
 * @since 1.0
 * @author <a href=https://github.com/MagyarZoli>Magyar Zoltán</a>
 */
public class RetroCasinoGame
extends ResponsiveFrame
implements ActionListener{

    /**
     * Storing the value of the frame width.
     * <p>Default frame width 700</p>
     */
    private int width=700;

    /**
     * Storing the value of the frame height.
     * <p>Default frame height 700.</p>
     */
    private int height=700;

    /**
     * Storing spin speed value.
     * <p>Default spin speed 60.</p>
     * @see RetroCasinoGame#setSpinSpeed(int)
     * @see RetroCasinoGame#getSpinSpeed()
     */
    private int spinSpeed = 60;

    /**
     * Storing amount value.
     * <p>Default amount 10000.</p>
     * @see RetroCasinoGame#setAmount(int)
     * @see RetroCasinoGame#getAmount()
     */
    private int amount = 10000;

    /**
     * Storing column stakes in an array.
     * <p>Default column bets:</p>
     * <p>50, 100, 200, 500, 1000,</p>
     * program does not use this array, it does not overwrite it,
     * it just passes the contents of the array to the {@code costs} array.
     * @see RetroCasinoGame#costs
     * @see RetroCasinoGame#setBetsCosts(int[])
     * @see RetroCasinoGame#getBetsCosts()
     */
    private int[] betCosts = new int[]{50,100,200,500,1000};

    /**
     * Storing the value of our icon.
     * <p>Default column bets:</p>
     * <p>1, 2, 4, 6, 8, 10,</p>
     * the program does not use or modify this array,
     * it passes the elements to the {@code iconsCosts} array.
     * @see RetroCasinoGame#iconsCosts
     * @see RetroCasinoGame#setBetIcons(int[])
     * @see RetroCasinoGame#getBetsCosts()
     */
    private int[] betIcons = new int[]{1,2,4,6,8,10};

    /**
     * Header text appearing above the amount.
     * @see RetroCasinoGame#setBankPanelTexts(String, String)
     * @see RetroCasinoGame#getBankPanelTexts()
     */
    private String cashStr = "CASH";

    /**
     * Text in the header above the amount won.
     * @see RetroCasinoGame#setBankPanelTexts(String, String)
     * @see RetroCasinoGame#getBankPanelTexts()
     */
    private String profitStr = "PROFIT";

    /**
     * Text displayed on the spin button.
     * @see RetroCasinoGame#setButtonsTexts(String, String)
     * @see RetroCasinoGame#getButtonsTexts()
     */
    private String spin = "SPIN";

    /**
     * text displayed on the bet changer button
     * @see RetroCasinoGame#setButtonsTexts(String, String)
     * @see RetroCasinoGame#getButtonsTexts()
     */
    private String bet = "BET";

    /**
     * 2-second delay, {@code Timer} class.
     */
    private final int delay = 2000;

    /**
     * The value of the uniform division of frame width and height.
     * @see RetroCasinoGame#areaArray()
     * @see RetroCasinoGame#areaBreakdown(double, int)
     */
    private int piece = 16;

    /**
     * column bet counter/index is a variable required for division.
     * @see RetroCasinoGame#betChange()
     */
    private int betCount = 0;

    /**
     * Split frame width.
     * Division is done by the {@code areaArray()} method and the {@code piece} variable.
     * @see RetroCasinoGame#areaArray()
     * @see RetroCasinoGame#areaBreakdown(double, int)
     */
    private int[] widthArray;

    /**
     * Split frame height.
     * Division is done by the {@code areaArray()} method and the {@code piece} variable.
     * @see RetroCasinoGame#areaArray()
     * @see RetroCasinoGame#areaBreakdown(double, int)
     */
    private int[] heightArray;

    /**
     * Transferring {@code betCosts} the stored values of Column Stakes.
     * @see RetroCasinoGame#betCosts
     * @see RetroCasinoGame#setBetsCosts(int[])
     * @see RetroCasinoGame#costChange()
     * @see RetroCasinoGame#bets(int[])
     * @see RetroCasinoGame#betChange()
     */
    private int[] costs = betCosts;

    /**
     * Passing icons values from the {@code betIcons} array.
     * @see RetroCasinoGame#setBetIcons(int[])
     * @see RetroCasinoGame#spinCost()
     * @see RetroCasinoGame#spinWins()
     * @see RetroCasinoGame#bets(int[])
     */
    private int[] iconsCosts = betIcons;

    /**
     * A 5-element array in which the lines that won will be stored.
     * @see RetroCasinoGame#spinWins()
     */
    private int[] wins = new int[5];

    /**
     * Call the Timer class in the field,
     * due to the availability of additional methods.
     * @see RetroCasinoGame#actionPerformed(ActionEvent)
     * @see RetroCasinoGame#gameLaunch()
     */
    private Timer timer;

    /**
     * In the GamePanel class, drawing random pictures on a 3-column is redrawn as a result of external events.
     */
    private GamePanel game = new GamePanel();

    /**
     * {@code list.ImageList} displays images in a panel. repeats each image 3x.
     */
    private ImagePanel imagePanel = new ImagePanel();

    /**
     * GitHub button through which the creator's profile can be accessed.
     */
    private Button github = new Button();

    /**
     * buttons stored in an array, array sorting is done by the {@code ComponentsArray.newComponents()} method.
     * The {@code buttons()} method performs the positioning of the buttons.
     * @see mz.ComponentsArray#newComponents(Class, int)
     * @see RetroCasinoGame#buttons(int[])
     */
    private Button[] buttons = ComponentsArray.newComponents(Button.class,2);

    /**
     * Label stored in an array, array sorting is done by the {@code ComponentsArray.newComponents()} method.
     * The functions of placing the label are provided by the {@code bets()} method.
     * @see mz.ComponentsArray#newComponents(Class, int)
     * @see RetroCasinoGame#bets(int[])
     */
    private Label[] bets = ComponentsArray.newComponents(Label.class, 5);

    /**
     * Label stored in an array, array sorting is done by the {@code ComponentsArray.newComponents()} method.
     * The functions of placing the label are provided by the {@code bank()} method.
     * @see mz.ComponentsArray#newComponents(Class, int)
     * @see RetroCasinoGame#bank(int[])
     */
    private Label[] bank = ComponentsArray.newComponents(Label.class,4);

    /**
     * Version number.
     */
    private String githubSince = "1.0";

    /**
     * Hyperlink to GitHub creator profile, its repository.
     */
    private String url = "https://github.com/MagyarZoli/RetroCasinoGame";

    /**
     * Storage of prize amount.
     * @see RetroCasinoGame#actionPerformed(ActionEvent)
     * @see RetroCasinoGame#redeemingPrize()
     */
    private int won = 0;

    /**
     * amount deducted after spins.
     * @see RetroCasinoGame#actionPerformed(ActionEvent)
     * @see RetroCasinoGame#betChange()
     * @see RetroCasinoGame#betSetUpChange()
     * @see RetroCasinoGame#betSetLowChange()
     */
    private int cost = costs[0];

    /**
     * If it finds a winning line, it returns a true value, if there is none, it returns a false value,
     * and after spinning again, it returns to a false value.
     * @see RetroCasinoGame#isFoundWinner()
     * @see RetroCasinoGame#actionPerformed(ActionEvent)
     * @see RetroCasinoGame#redeemingPrize()
     * @see RetroCasinoGame#spinWins()
     */
    private boolean foundWinner = false;

    /**
     * Arguments passed in the RetroCasinoGame constructor are forwarded to the {@code ResponsiveFrame} class.
     * additional default settings:
     * <ul>
     *     <li>window close exit button</li>
     *     <li>title set</li>
     *     <li>window icon is set, the image is provided by the {@code list.ImageList} class.</li>
     *     <li>frame background color. color is provided by the {@code list.ColorList} class.</li>
     *     <li>default dimensions: 700x700</li>
     *     <li>dividing the frame size.</li>
     *     <li>game methods</li>
     *     <li>frame visibility</li>
     * </ul>
     * @see RetroCasinoGame#areaArray()
     * @see RetroCasinoGame#gameFrame()
     */
    public RetroCasinoGame(){
        super();
        this.setDefaultCloseOperation(RetroCasinoGame.EXIT_ON_CLOSE);
        this.setTitle("Retro Casino Game");
        this.setIconImage(ImageList.IMAGES[0]);
        this.getContentPane().setBackground(ColorList.FRAME_BACKGROUND);
        this.setSize(width,height);
        areaArray();
        gameFrame();
        this.setVisible(true);
    }

    /**
     * game methodus:
     * layout panels and buttons by increasing the size of the frame</li>
     * @see retroCasinoGame.GamePanel
     * @see retroCasinoGame.ImagePanel
     * @see retroCasinoGame.Label
     * @see retroCasinoGame.Button
     * @see list.ColorList
     * @see list.ImageList
     */
    private void gameFrame(){
        game();
        images();
        bets(new int[]{5,9,2,0,4,0});
        bank(new int[]{1,1,4,0,1,0});
        buttons(new int[]{4,13,3,0,1,0});
        github();
    }

    /**
     * The size of the frame can be specified.
     * @param width the new width of this component in pixels
     * @param height the new height of this component in pixels
     */
    @Override
    public void setSize(int width, int height){
        super.setSize(width,height);
        this.width = width;
        this.height = height;
    }

    /**
     * The starting amount in the game can be set.
     * Accepts a value greater than 0.
     * @param amount
     */
    public void setAmount(int amount){
        if(amount>0){
            this.amount = amount;
        }
    }

    /**
     * By specifying an array of 5 elements,
     * it is possible to set the starting value of the bet columns.
     * Array elements cannot have negative or 0 values!
     * @param betCosts array index = 5! And array elements cannot be less than or equal to 0!
     * @throws ArrayIndexOutOfBoundsException new array length not 5!
     * @throws IllegalArgumentException array elements cannot be less than or equal to 0!
     */
    public void setBetsCosts(int[] betCosts){
        if(betCosts.length==5){
            for(int betCost : betCosts){
                if(betCost<=0){
                    throw new IllegalArgumentException("array elements cannot be less than or equal to 0! Resets to default value.");
                }
            }
            this.betCosts = betCosts;
            this.costs = betCosts;
        }
        else{
            throw new ArrayIndexOutOfBoundsException("new array length not 5! Resets to default value.");
        }
    }

    /**
     * images values can be specified in an array.
     * @param betIcons
     * @throws ArrayIndexOutOfBoundsException new array length not 6! Resets to default value.
     * @throws IllegalArgumentException array elements cannot be less than or equal to 0!
     */
    public void setBetIcons(int[] betIcons){
        if(betIcons.length==6){
            for(int betIcon : betIcons){
                if(betIcon<=0){
                    throw new IllegalArgumentException("array elements cannot be less than or equal to 0! Resets to default value.");
                }
            }
            this.betIcons = betIcons;
            this.iconsCosts = betIcons;
        }
        else{
            throw new ArrayIndexOutOfBoundsException("new array length not 6! Resets to default value.");
        }
    }

    /**
     * texts in bank headers.
     * If both values are null, the default texts remain unchanged.
     * @param cashStr
     * @param profitStr
     * @throws NullPointerException new texts are null!
     */
    public void setBankPanelTexts(String cashStr, String profitStr){
        if(cashStr!=null){
            this.cashStr = cashStr;
        }
        if(profitStr!=null){
            this.profitStr = profitStr;
        }
        else if(cashStr==null && profitStr==null){
            throw new NullPointerException("new texts are null! Resets to default value.");
        }
    }

    /**
     * texts in button.
     * If both values are null, the default texts remain unchanged.
     * @param bet
     * @param spin
     * @throws NullPointerException new texts are null!
     */
    public void setButtonsTexts(String bet, String spin){
        if(bet!=null){
            this.bet = bet;
        }
        if(spin!=null){
            this.spin = spin;
        }
        else if(bet==null && spin==null){
            throw new NullPointerException("new texts are null! Resets to default value.");
        }
    }

    /**
     * Can specify how fast and how many times the images should rotate.
     * @param spinSpeed
     * @throws IllegalArgumentException cannot be 0 or a negative integer!
     */
    public void setSpinSpeed(int spinSpeed){
        if(spinSpeed>0){
            this.spinSpeed = spinSpeed;
        }
        else{
            throw new IllegalArgumentException("cannot be 0 or a negative integer! Resets to default value: "+this.spinSpeed);
        }
    }

    /**
     * The amount in the game can be returned
     * @return amount
     */
    public int getAmount(){
        return this.amount;
    }

    /**
     * Column bet values can be retrieved.
     * @return betCosts array
     */
    public int[] getBetsCosts(){
        return this.betCosts;
    }

    /**
     * The values of images can be retrieved.
     * @return betIcons
     */
    public int[] getBetIcons(){
        return this.betIcons;
    }

    /**
     * The texts in the bank panel header can be retrieved
     * @return cashStr and profitStr
     */
    public String[] getBankPanelTexts(){
        return new String[]{this.cashStr, this.profitStr};
    }

    /**
     * texts from retrievable buttons.
     * @return bets and spin
     */
    public String[] getButtonsTexts(){
        return new String[]{this.bet, this.spin};
    }

    /**
     * the current speed of the spin can be retrieved,
     * this value is constantly decreasing.
     * @return spinSpeed
     */
    public int getSpinSpeed(){
        return this.spinSpeed;
    }

    /**
     * returns the amount won, if none then the value is 0.
     * @return won
     */
    public int getWon(){
        return this.won;
    }

    /**
     * refundable amount of the total amount.
     * @return cost
     */
    public int getCost(){
        return  this.cost;
    }

    /**
     * is there a winning spin?
     * @return foundWinner
     */
    public boolean isFoundWinner(){
        return this.foundWinner;
    }

    /**
     * possible outcomes:
     * <ol>
     *      <li>Spin button is activated, but there is no winning line.
     *          <ul>
     *              <li>Subtracting the spin value from the amount. {@code spinCost()}</li>
     *              <li>Waiting for the spin time, it asks back from the {@code GamePanel} if there are rows that match.
     *              Disable buttons until time runs out</li>
     *              <li>After deducting the amount, is there still a total amount left,
     *              if not end the game, this is still checked.</li>
     *          </ul>
     *      </li>
     *      <li>Spin button is activated and there is a winning line.
     *          <ul>
     *              <li>Adds the amount won to the total and then resets the winnings to 0.</li>
     *              <li>call {@code costChange} if the total was below a certain amount and now exceeds it,
     *              the {@code costs} array is reloaded.</li>
     *          </ul>
     *      </li>
     *      <li>Bet button is activated.
     *          <ul>
     *              <li>moves to the next or very first bet column.</li>
     *              <li>Checks that only those bet columns are available from which
     *              the amount deducted after spinning will not be less than 0.</li>
     *          </ul>
     *      </li>
     *      <li>GitHub button is activated: the browser opens the repository of the creator's github profile.</li>
     * </ol>
     * @param e the event to be processed
     * @throws URISyntaxException
     * @throws IOException
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==buttons[1] && !foundWinner){
            spinCost();
            gameLaunch();
            gameOverQuestion();
        }
        else if(e.getSource()==buttons[1] && foundWinner){
            redeemingPrize();
            costChange();
        }
        if(e.getSource()==buttons[0]){
            betChange();
            costChange();
        }
        if(e.getSource()==github){
            try {
                Desktop.getDesktop().browse(new URI(url));
            } catch (IOException | URISyntaxException e2) {
                e2.printStackTrace();
            }
        }
    }

    /**
     * dividing frame width and height and storing them in arrays.
     */
    private void areaArray(){
        this.widthArray = areaBreakdown(this.width, piece);
        this.heightArray = areaBreakdown(this.height, piece);
    }

    /**
     * creates a new array, divides it into pieces of the specified length and loads them into the array.
     * @param area
     * @param piece
     * @return divided by area piece.
     */
    private int[] areaBreakdown(double area, int piece){
        int[] newArray = new int[piece+1];
        for(int i=0; i<=piece; i++){
            newArray[i] = (int)((i*area)/piece);
        }
        return newArray;
    }

    /**
     * Specifying the size of the {@code GamePanel} with the split dimensions,
     * then added to the frame.
     * @see RetroCasinoGame#areaArray()
     */
    private void game(){
        game.setBounds(widthArray[6],heightArray[1],(widthArray[8]-widthArray[0]),(heightArray[8]-heightArray[0]));
        this.add(game);
    }

    /**
     * Specifying the size of the {@code ImagePanel} with the split dimensions, then added to the frame.
     * @see RetroCasinoGame#areaArray()
     */
    private void images(){
        imagePanel.setBounds(widthArray[2],(heightArray[8]+(widthArray[4]/3)),(widthArray[3]-(widthArray[1]/2)),(heightArray[4]-(heightArray[1]/2)));
        this.add(imagePanel);
    }

    /**
     * Creates two buttons at the same time,
     * specifies their dimensions from the divided size arrays,
     * and then defines the colors of the button. Add the {@code ActionListener()}
     * and then add it to the frame.
     * @param array two functional buttons class
     * @see RetroCasinoGame#actionPerformed(ActionEvent)
     */
    private void buttons(int[] array){
        for(int i=0; i<buttons.length; i++){
            switch (i){
                case 0:
                    buttons[i].setText(bet);
                    break;
                case 1:
                    buttons[i].setText(spin);
                    break;
                default:
                    break;
            }
            buttons[i].setForeground(ColorList.BUTTON_FOREGROUND[i]);
            buttons[i].setBackground(ColorList.BUTTON_BACKGROUND[i]);
            buttons[i].setUI(new BasicButtonUI());
            buttons[i].addActionListener(this);
            buttons[i].setBounds(widthArray[(array[0]+(5*i))], heightArray[array[1]], (widthArray[array[2]]-widthArray[array[3]]), (heightArray[array[4]]-heightArray[array[5]]));
            this.add(buttons[i]);
        }
    }

    /**
     * produced by two labels at the same time.
     * By dealing with dimensions defined by appropriate text.
     * Added the colors of the labels, then added to the frame.
     * @param array two label classes
     */
    private void bank(int[] array){
        for(int i=0; i<bank.length; i++){
            switch (i) {
                case 0:
                    bank[i].setText(cashStr);
                    break;
                case 1:
                    bank[i].setText(String.valueOf(amount));
                    break;
                case 2:
                    bank[i].setText(profitStr);
                    break;
                case 3:
                    bank[i].setText("0");
                    break;
                default:
                    break;
            }
            bank[i].setForeground(ColorList.BANK_FOREGROUND[i]);
            bank[i].setBackground(ColorList.BANK_BACKGROUND[i]);
            bank[i].setHorizontalAlignment(Label.CENTER);
            bank[i].setBounds(widthArray[array[0]], heightArray[(array[1]+i)], (widthArray[array[2]]-widthArray[array[3]]), (heightArray[array[4]]-heightArray[array[5]]));
            this.add(bank[i]);
        }
    }

    /**
     * Creating stake columns at the same time.
     * Uploaded with a list of corresponding values in html form.
     * Specified dimensions and location, then added to the frame.
     * @param array class of stake columns
     */
    private void bets(int[] array){
        for(int i=0; i<costs.length; i++){
            if(i==0){
                bets[i].setActiv(true);
            }
            bets[i].setText(
                "<html>"+
                    (iconsCosts[5]*costs[i])+"<br>"+
                    (iconsCosts[4]*costs[i])+"<br>"+
                    (iconsCosts[3]*costs[i])+"<br>"+
                    (iconsCosts[2]*costs[i])+"<br>"+
                    (iconsCosts[1]*costs[i])+"<br>"+
                    (iconsCosts[0]*costs[i])+
                "</html>"
            );
            bets[i].setOpaque(false);
            bets[i].setForeground(ColorList.BETS_FOREGROUND[i]);
            bets[i].setBounds(widthArray[(array[0]+(i*2))], heightArray[array[1]], (widthArray[array[2]]-widthArray[array[3]]), (heightArray[array[4]]-heightArray[array[5]]));
            this.add(bets[i]);
        }
    }

    /**
     * Checks whether there is a match in the 5 different possible winning
     * rows of the drawn random images of the {@code GamePanel}, if there is a match,
     * the value of the element of the array and the product of the current bet are displayed.
     */
    private void spinWins() {
        if(game.getSpin()==0){
            wins = game.getWins();
            buttons[0].setEnabled(true);
            for(int win : wins){
                if(win != 0){
                    bank[3].setText(String.valueOf(won += (iconsCosts[win - 1] * cost)));
                    if(!foundWinner){
                        foundWinner = true;
                        buttons[0].setEnabled(false);
                    }
                }
            }
        }
    }

    /**
     * starting the game, the pictures will be drawn again and after a certain delay,
     * the {@code spinWins()} method will ask if there is a winning row.
     */
    private void gameLaunch(){
        buttons[0].setEnabled(false);
        buttons[1].setEnabled(false);
        timer = new Timer(delay, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                spinWins();
                buttons[1].setEnabled(true);
                costChange();
                timer.stop();
            }
        });
    }

    /**
     * It adds the winning amount to the total,
     * then changes the won amount to 0 and restores the usability of the buttons.
     */
    private void redeemingPrize(){
        amount+=Integer.parseInt(bank[3].getText());
        bank[1].setText(String.valueOf(amount));
        foundWinner = false;
        bank[3].setText("0");
        won = 0;
        game.defaultWins();
        buttons[0].setEnabled(true);
    }

    /**
     * Can change the bet if it is not suitable for the total amount.
     * If it is at a value that can no longer be used, it resets the initial bet.
     */
    private void costChange(){
        for(int i=1; i<5; i++){
            if(amount>=betCosts[4]){
                this.costs = new int[]{betCosts[0], betCosts[1], betCosts[2], betCosts[3], betCosts[4]};
                break;
            }
            else if(amount<betCosts[i]){
                this.costs = new int[i];
                for(int j=0; j<costs.length; j++){
                    costs[j] = betCosts[j];
                }
                if(cost>=betCosts[i]){
                    betSetLowChange();
                }
                break;
            }
        }
    }

    /**
     * Subtracts the value of the spin from the total.
     */
    private void spinCost(){
        if(Integer.parseInt(bank[1].getText())>0){
            game.setSpin(spinSpeed);
            amount-=cost*iconsCosts[0];
            bank[1].setText(String.valueOf(amount));
        }
    }

    /**
     * Changes the bet to the bet column
     * @see RetroCasinoGame#actionPerformed(ActionEvent)
     */
    private void betChange(){
        if(bets[betCount].isActiv() && betCount<costs.length-1){
            betSetUpChange();
        }
        else if(bets[betCount].isActiv() && betCount==costs.length-1){
            betSetLowChange();
        }
    }

    /**
     * Increase bet
     */
    private void betSetUpChange(){
        bets[betCount+1].setActiv(true);
        cost = costs[betCount+1];
        bets[betCount].setActiv(false);
        betCount++;
    }

    /**
     * Bet reset to minimum value.
     * If the bet exceeds the total amount, it cannot be selected, so it is reset to the starting bet.
     * @see RetroCasinoGame#costChange()
     * @see RetroCasinoGame#gameOverQuestion()
     */
    private void betSetLowChange(){
        if(!(bets[0].isActiv())){
            bets[0].setActiv(true);
            cost = costs[0];
            betCount=0;
        }
        for(int i=1; i<bets.length; i++){
            if(bets[i].isActiv()) {
                bets[i].setActiv(false);
            }
        }
    }

    /**
     * When the total reaches 0 and the last spin did not win, then the game is over,
     * and it turns off the buttons and stops the time running.
     * It checks this on every spin.
     * @see RetroCasinoGame#actionPerformed(ActionEvent)
     */
    private void gameOverQuestion(){
        if(Integer.parseInt(bank[1].getText())==0){
            betSetLowChange();
            bets[0].setActiv(false);
            buttons[0].setEnabled(false);
            buttons[1].setEnabled(false);
        }
        else{
            timer.start();
        }
    }

    /**
     * Making a GitHub button in the lower left corner, adding the GitHub image,
     * addActionListener, and then adding it to the frame.
     * @see RetroCasinoGame#actionPerformed(ActionEvent)
     * @see list.ImageList#GITHUB
     */
    private void github(){
        JLabel label = new JLabel(githubSince);
        label.setIcon(ImageList.GITHUB);
        label.setForeground(ColorList.GITHUB_FOREGROUND);
        label.setBounds(widthArray[0],heightArray[14],(widthArray[2]-widthArray[0]),(heightArray[1]-heightArray[0]));
        github.setBounds(widthArray[0],heightArray[14],(widthArray[2]-widthArray[0]),(heightArray[1]-heightArray[0]));
        github.setOpaque(false);
        github.setBackground(ColorList.FRAME_BACKGROUND);
        github.add(label);
        github.addActionListener(this);
        this.add(github);
    }
}