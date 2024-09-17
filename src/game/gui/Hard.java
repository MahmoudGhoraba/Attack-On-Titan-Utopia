package game.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;

import game.engine.Battle;
import game.engine.exceptions.GameActionException;
import game.engine.exceptions.InsufficientResourcesException;
import game.engine.exceptions.InvalidCSVFormat;
import game.engine.exceptions.InvalidLaneException;
import game.engine.lanes.Lane;
import game.engine.titans.AbnormalTitan;
import game.engine.titans.ArmoredTitan;
import game.engine.titans.ColossalTitan;
import game.engine.titans.PureTitan;
import game.engine.titans.Titan;
import javafx.scene.image.Image;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Hard {
    private Battle battle;
    private Scene scene ;
    private BorderPane root;
    private ArrayList<ImageView> titans_images= new ArrayList<ImageView>();
    private ArrayList<Text> healthOfTitans = new ArrayList<Text>();
    private Image[] titansImages = new Image[4];
    private Image[] titansWeapons = new Image[4];
    private Image[] wall1 = new Image[2];
    private int SelectedLaneShop=20;
    private Scene saved;
    private Stage primaryStage;
    private ImageView[] WallsIv = new ImageView[5]; 
    private Pane center = new Pane();
    private int iPw1 =0;
    private int iPw2 =0;
    private int iPw3 =0;
    private int iPw4 =0;
    private int iPw5 =0;
    
    private int iVw1=0;
    private int iVw2=0;
    private int iVw3=0;
    private int iVw4=0;
    private int iVw5=0;
    
    private int iTw1 =0;
    private int iTw2 =0;
    private int iTw3 =0;
    private int iTw4 =0;
    private int iTw5 =0;
    
    private int iSw1 =0;
    private int iSw2 =0;
    private int iSw3 =0;
    private int iSw4 =0;
    private int iSw5 =0;
    
    final int numCols = 11 ;
    final int numRows = 3 ;
    
    Image freeLaneImage = new Image("resources/ingame/freeLane.png");
    
    public Hard(Stage primaryStage,Scene saved) throws IOException {
    	this.saved=saved;
    	this.primaryStage=primaryStage;
        loadImage();
        battle = new Battle(1,0,100,5,125);
        root = new BorderPane();
        root.setPrefSize(1280, 720);
        HBox topHBox = new HBox();
        topHBox.setPrefSize(1143, 165);
		

        //Center building       
        center.setPrefSize(1147, 455);     
        center.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);      
   
        //text of count piercing
        Text NumOfPiercing1 = new Text();
        Text NumOfPiercing2 = new Text();
        Text NumOfPiercing3 = new Text();
        Text NumOfPiercing4 = new Text();
        Text NumOfPiercing5 = new Text();
        //text of count Sniper
        Text NumOfSniper1 = new Text();
        Text NumOfSniper2 = new Text();
        Text NumOfSniper3 = new Text();
        Text NumOfSniper4 = new Text();
        Text NumOfSniper5 = new Text();
        //text of count Trap
        Text NumOfTrap1 = new Text();
        Text NumOfTrap2 = new Text();
        Text NumOfTrap3 = new Text();
        Text NumOfTrap4 = new Text();
        Text NumOfTrap5 = new Text();
        //text of count Volley
        Text NumOfVolley1 = new Text();
        Text NumOfVolley2 = new Text();
        Text NumOfVolley3 = new Text();
        Text NumOfVolley4 = new Text();
        Text NumOfVolley5 = new Text();
        
       
        TextArea resourceTextArea = new TextArea();
        resourceTextArea.setPrefSize(484, 165);
        resourceTextArea.setFont(new Font(22));
        resourceTextArea.setEditable(false); // Set TextArea to not editable
        
        //left window
        TextArea[] txt  = new TextArea[5];
        GridPane left = createLeftGridPane(txt);
        for (int i = 0; i < txt.length; i++) {
            txt[i] = new TextArea();
            txt[i].setPrefSize(200, 100);
            txt[i].setText("Default Text");
            txt[i].setFont(new Font(8));
            txt[i].setEditable(false); // Set TextArea to not editable
            left.setRowIndex(txt[i], i);
            left.getChildren().add(txt[i]);
        };
        

        VBox[] vBoxes = new VBox[4];
        for (int i = 0; i < vBoxes.length; i++) {
            vBoxes[i] = new VBox();
            vBoxes[i].setPrefSize(199, 165);
            Button button = new Button("Buy");
           
            button.setPrefSize(199, 91);       
            TextArea textArea = new TextArea();
            if(i==0) { // i y3ny piercing
                textArea.setPrefSize(199, 82);
                textArea.setText("Name : Anti-Titan Shell\nType : Piercing Cannon\nPrice : 25\nDamage : 10");
                textArea.setEditable(false);
      
                
                button.setOnMouseClicked(event->{
                	if (SelectedLaneShop == 0){
                		try {
							battle.purchaseWeapon(1, battle.getOriginalLanes().get(0));
							
							ImageView Piercing = new ImageView(titansWeapons[0]);
							iPw1++;
							weaponCount(Piercing,NumOfPiercing1);
							NumOfPiercing1.setText("Cannons: "+iPw1);
							NumOfPiercing1.relocate(120,41);
							center.getChildren().addAll(Piercing,NumOfPiercing1);
	                		Piercing.relocate(120,43);
	                		Piercing.setFitWidth(50);
	                		Piercing.setFitHeight(50);	
	                		update(resourceTextArea,center);
	                        setUpLane(battle,txt);
	                        setUpBattleGrid();
	                        SelectedLaneShop=5;
						} catch (Exception e) {
							handleExp(e);
							
						}
                		
                	}
                	if (SelectedLaneShop == 1){
                		try {
							battle.purchaseWeapon(1, battle.getOriginalLanes().get(1));
							ImageView Piercing = new ImageView(titansWeapons[0]);
							iPw2++;
							weaponCount(Piercing,NumOfPiercing2);
							NumOfPiercing2.setText("Cannons: "+iPw2);
							NumOfPiercing2.relocate(120,141);
							center.getChildren().addAll(Piercing,NumOfPiercing2);
	                		Piercing.relocate(120,143);
	                		Piercing.setFitWidth(50);
	                		Piercing.setFitHeight(50);	
	                		update(resourceTextArea,center);
	                        setUpLane(battle,txt);
	                        setUpBattleGrid();
	                        SelectedLaneShop=5;
							} catch (Exception e) {
							handleExp(e);
						}
                		
                	}
                	if (SelectedLaneShop == 2){
                		try {
							battle.purchaseWeapon(1, battle.getOriginalLanes().get(2));
							ImageView Piercing = new ImageView(titansWeapons[0]);
							iPw3++;
							weaponCount(Piercing,NumOfPiercing3);
							NumOfPiercing3.setText("Cannons: "+iPw3);
							NumOfPiercing3.relocate(120,241);
							center.getChildren().addAll(Piercing,NumOfPiercing3);
	                		Piercing.relocate(120,243);
	                		Piercing.setFitWidth(50);
	                		Piercing.setFitHeight(50);	
	                		update(resourceTextArea,center);
	                        setUpLane(battle,txt);
	                        setUpBattleGrid();
	                        SelectedLaneShop=5;
						} catch (Exception e) {
							handleExp(e);
						}
                		
                	}
                	
                	if (SelectedLaneShop == 3){
                		try {
							battle.purchaseWeapon(1, battle.getOriginalLanes().get(3));
							ImageView Piercing = new ImageView(titansWeapons[0]);
	                		iPw4++;
							weaponCount(Piercing,NumOfPiercing4);
							NumOfPiercing4.setText("Cannons: "+iPw4);
							NumOfPiercing4.relocate(120,341);
							center.getChildren().addAll(Piercing,NumOfPiercing4);
	                		Piercing.relocate(120,343);
	                		Piercing.setFitWidth(50);
	                		Piercing.setFitHeight(50);		
	                		update(resourceTextArea,center);
	                        setUpLane(battle,txt);
	                        setUpBattleGrid();
	                        SelectedLaneShop=5;
						} catch (Exception e) {
							handleExp(e);
						}
                		
                	}
                	if (SelectedLaneShop == 4){
                		try {
							battle.purchaseWeapon(1, battle.getOriginalLanes().get(4));
							ImageView Piercing = new ImageView(titansWeapons[0]);
							iPw5++;
							weaponCount(Piercing,NumOfPiercing5);
							NumOfPiercing5.setText("Cannons: "+iPw5);
							NumOfPiercing5.relocate(120,441);
							center.getChildren().addAll(Piercing,NumOfPiercing5);
	                		Piercing.relocate(120,443);
	                		Piercing.setFitWidth(50);
	                		Piercing.setFitHeight(50);	
	                		update(resourceTextArea,center);
	                        setUpLane(battle,txt);
	                        setUpBattleGrid();
	                        SelectedLaneShop=5;
						} catch (Exception e) {
							handleExp(e);
						}
                		
                	}
                });
            }
            if(i==1) { // i y3ny sniper
                textArea.setPrefSize(199, 82);
                textArea.setText("Name : Long Range Spear\nType : Sniper Cannon\nPrice : 25\nDamage : 35");
                textArea.setEditable(false);
                
                button.setOnMouseClicked(event->{
                	if (SelectedLaneShop == 0){
                		try {
							battle.purchaseWeapon(2, battle.getOriginalLanes().get(0));
							ImageView Sniper = new ImageView(titansWeapons[1]);
							iSw1++;
							weaponCount(Sniper,NumOfSniper1);
							NumOfSniper1.setText("Snipers: "+iSw1);
							NumOfSniper1.relocate(120,89);
							center.getChildren().addAll(Sniper,NumOfSniper1);
	                		Sniper.relocate(120,91);
	                		Sniper.setFitWidth(50);
	                		Sniper.setFitHeight(50);		
	                		update(resourceTextArea,center);
	                        setUpLane(battle,txt);
	                        setUpBattleGrid();
	                        SelectedLaneShop=5;
						} catch (Exception e) {
							handleExp(e);
						}
                		
                	}
                	if (SelectedLaneShop == 1){
                		try {
							battle.purchaseWeapon(2, battle.getOriginalLanes().get(1));
							ImageView Sniper = new ImageView(titansWeapons[1]);
							iSw2++;
							weaponCount(Sniper,NumOfSniper2);
							NumOfSniper2.setText("Snipers: "+iSw2);
							NumOfSniper2.relocate(120,189);
							center.getChildren().addAll(Sniper,NumOfSniper2);
	                		Sniper.relocate(120,191);
	                		Sniper.setFitWidth(50);
	                		Sniper.setFitHeight(50);	
	                		update(resourceTextArea,center);
	                        setUpLane(battle,txt);
	                        setUpBattleGrid();
	                        SelectedLaneShop=5;
						} catch (Exception e) {
							handleExp(e);
						}
                		
                	}
                	if (SelectedLaneShop == 2){
                		try {
							battle.purchaseWeapon(2, battle.getOriginalLanes().get(2));
							ImageView Sniper = new ImageView(titansWeapons[1]);
							iSw3++;
							weaponCount(Sniper,NumOfSniper3);
							NumOfSniper3.setText("Snipers: "+iSw3);
							NumOfSniper3.relocate(120,289);
							center.getChildren().addAll(Sniper,NumOfSniper3);
	                		Sniper.relocate(120,291);
	                		Sniper.setFitWidth(50);
	                		Sniper.setFitHeight(50);	
	                		update(resourceTextArea,center);
	                        setUpLane(battle,txt);
	                        setUpBattleGrid();
	                        SelectedLaneShop=5;
						} catch (Exception e) {
							handleExp(e);
						}
                		
                	}
                	if (SelectedLaneShop == 3){
                		try {
							battle.purchaseWeapon(2, battle.getOriginalLanes().get(3));
							ImageView Sniper = new ImageView(titansWeapons[1]);
							iSw4++;
							weaponCount(Sniper,NumOfSniper4);
							NumOfSniper4.setText("Snipers: "+iSw4);
							NumOfSniper4.relocate(120,389);
							center.getChildren().addAll(Sniper,NumOfSniper4);
	                		Sniper.relocate(120,391);
	                		Sniper.setFitWidth(50);
	                		Sniper.setFitHeight(50);	
	                		update(resourceTextArea,center);
	                        setUpLane(battle,txt);
	                        setUpBattleGrid();
	                        SelectedLaneShop=5;
						} catch (Exception e) {
							handleExp(e);
						}
                		
                	}
                	if (SelectedLaneShop == 4){
                		try {
							battle.purchaseWeapon(2, battle.getOriginalLanes().get(4));
							ImageView Sniper = new ImageView(titansWeapons[1]);
							iSw5++;
							weaponCount(Sniper,NumOfSniper5);
							NumOfSniper5.setText("Snipers: "+iSw5);
							NumOfSniper5.relocate(120,489);
							center.getChildren().addAll(Sniper,NumOfSniper5);
	                		Sniper.relocate(120,491);
	                		Sniper.setFitWidth(50);
	                		Sniper.setFitHeight(50);	
	                		update(resourceTextArea,center);
	                        setUpLane(battle,txt);
	                        setUpBattleGrid();
	                        SelectedLaneShop=5;
						} catch (Exception e) {
							handleExp(e);
						}
                		
                	}
                	
                	
                });
            }
            if(i==2) { // i y3ny trap
                textArea.setPrefSize(199, 82);
                textArea.setText("Name : Proximity Trap\nType : Wall Trap\nPrice : 75\nDamage : 100");
                textArea.setEditable(false);
                button.setOnMouseClicked(event->{
                	if (SelectedLaneShop == 0){
                		try {
							battle.purchaseWeapon(4, battle.getOriginalLanes().get(0));
							ImageView trap = new ImageView(titansWeapons[3]);
							iTw1++;
							weaponCount(trap,NumOfTrap1);
							NumOfTrap1.setText("Traps: "+iTw1);
							NumOfTrap1.relocate(248,80);
							center.getChildren().addAll(trap,NumOfTrap1);
	                		trap.relocate(230,80);
	                		trap.setFitWidth(70);
	                		trap.setFitHeight(70);
	                		update(resourceTextArea,center);
	                        setUpLane(battle,txt);
	                        setUpBattleGrid();
	                        SelectedLaneShop=5;
						} catch (Exception e) {
							handleExp(e);
						}
                    
                	}
                	if (SelectedLaneShop == 1){	
                		try {
							battle.purchaseWeapon(4, battle.getOriginalLanes().get(1));
							ImageView trap = new ImageView(titansWeapons[3]);
							iTw2++;
							weaponCount(trap,NumOfTrap2);
							NumOfTrap2.setText("Traps: "+iTw2);
							NumOfTrap2.relocate(248,180);
							center.getChildren().addAll(trap,NumOfTrap2);
	                		trap.relocate(230,180);
	                		trap.setFitWidth(70);
	                		trap.setFitHeight(70);
	                		update(resourceTextArea,center);
	                        setUpLane(battle,txt);
	                        setUpBattleGrid();
	                        SelectedLaneShop=5;
						} catch (Exception e) {
							handleExp(e);
						}
                    	
                	}
                	if (SelectedLaneShop == 2){
                		try {
							battle.purchaseWeapon(4, battle.getOriginalLanes().get(2));
							ImageView trap = new ImageView(titansWeapons[3]);
							iTw3++;
							weaponCount(trap,NumOfTrap3);
							NumOfTrap3.setText("Traps: "+iTw3);
							NumOfTrap3.relocate(248,280);
							center.getChildren().addAll(trap,NumOfTrap3);
	                		trap.relocate(230,280);
	                		trap.setFitWidth(70);
	                		trap.setFitHeight(70);
	                		update(resourceTextArea,center);
	                        setUpLane(battle,txt);
	                        setUpBattleGrid();
	                        SelectedLaneShop=5;
						} catch (Exception e) {
							handleExp(e);
						}
                	}
                		if (SelectedLaneShop == 3){
                    		try {
    							battle.purchaseWeapon(4, battle.getOriginalLanes().get(3));
    							ImageView trap = new ImageView(titansWeapons[3]);
    							iTw4++;
    							weaponCount(trap,NumOfTrap4);
    							NumOfTrap4.setText("Traps: "+iTw4);
    							NumOfTrap4.relocate(248,380);
    							center.getChildren().addAll(trap,NumOfTrap4);
    	                		trap.relocate(230,380);
    	                		trap.setFitWidth(70);
    	                		trap.setFitHeight(70);
    	                		update(resourceTextArea,center);
    	                        setUpLane(battle,txt);
    	                        setUpBattleGrid();
    	                        SelectedLaneShop=5;
    						} catch (Exception e) {
    							handleExp(e);
    						}
                		}
                    	if (SelectedLaneShop == 4){
                        	try {
        						battle.purchaseWeapon(4, battle.getOriginalLanes().get(4));
        						ImageView trap = new ImageView(titansWeapons[3]);
        						iTw5++;
    							weaponCount(trap,NumOfTrap5);
    							NumOfTrap5.setText("Traps: "+iTw5);
    							NumOfTrap5.relocate(248,480);
    							center.getChildren().addAll(trap,NumOfTrap5);
    	                		trap.relocate(230,480);
    	                		trap.setFitWidth(70);
    	                		trap.setFitHeight(70);
        	               		update(resourceTextArea,center);
        	                    setUpLane(battle,txt);
        	                    setUpBattleGrid();
        	                    SelectedLaneShop=5;
        					} catch (Exception e) {
       							handleExp(e);
       						}
                    	}
                		
                });
            }
            if(i==3) {     // i y3ny volley        	
                textArea.setPrefSize(199, 82);
                textArea.setText("Name : Wall Spread Cannon\nType : Volley Spread Cannon\nPrice : 100\nDamage : 5");
                textArea.setEditable(false);
                button.setOnMouseClicked(event->{
                	if (SelectedLaneShop == 0){
                		try {
							battle.purchaseWeapon(3, battle.getOriginalLanes().get(0));
							ImageView volley = new ImageView(titansWeapons[2]);
							iVw1++;
							weaponCount(volley,NumOfVolley1);
							NumOfVolley1.setText("Volleys: "+iVw1);
							NumOfVolley1.relocate(70,68);
							center.getChildren().addAll(volley,NumOfVolley1);
	                		volley.relocate(70,70);
	                		volley.setFitWidth(50);
	                		volley.setFitHeight(50);	
	                		update(resourceTextArea,center);
	                        setUpLane(battle,txt);
	                        setUpBattleGrid();
	                        SelectedLaneShop=5;
						} catch (Exception e) {
							handleExp(e);
						}
                    	
                	}
                	if (SelectedLaneShop == 1){	
                		try {
							battle.purchaseWeapon(3, battle.getOriginalLanes().get(1));
							ImageView volley = new ImageView(titansWeapons[2]);
							iVw2++;
							weaponCount(volley,NumOfVolley2);
							NumOfVolley2.setText("Volleys: "+iVw2);
							NumOfVolley2.relocate(70,168);
							center.getChildren().addAll(volley,NumOfVolley2);
	                		volley.relocate(70,170);
	                		volley.setFitWidth(50);
	                		volley.setFitHeight(50);	
	                		update(resourceTextArea,center);
	                        setUpLane(battle,txt);
	                        setUpBattleGrid();
	                        SelectedLaneShop=5;
						} catch (Exception e) {
							handleExp(e);
						}
                    	
                	}
                	if (SelectedLaneShop == 2){
                		try {
							battle.purchaseWeapon(3, battle.getOriginalLanes().get(2));
							ImageView volley = new ImageView(titansWeapons[2]);
							iVw3++;
							weaponCount(volley,NumOfVolley3);
							NumOfVolley3.setText("Volleys: "+iVw3);
							NumOfVolley3.relocate(70,268);
							center.getChildren().addAll(volley,NumOfVolley3);
	                		volley.relocate(70,270);
	                		volley.setFitWidth(50);
	                		volley.setFitHeight(50);	
	                		update(resourceTextArea,center);
	                        setUpLane(battle,txt);
	                        setUpBattleGrid();
	                        SelectedLaneShop=5;
						} catch (Exception e) {
							handleExp(e);
						}
                    	
                	}
                	if (SelectedLaneShop == 3){
                		try {
							battle.purchaseWeapon(3, battle.getOriginalLanes().get(3));
							ImageView volley = new ImageView(titansWeapons[2]);
							iVw4++;
							weaponCount(volley,NumOfVolley4);
							NumOfVolley4.setText("Volleys: "+iVw4);
							NumOfVolley4.relocate(70,368);
							center.getChildren().addAll(volley,NumOfVolley4);
	                		volley.relocate(70,370);
	                		volley.setFitWidth(50);
	                		volley.setFitHeight(50);	
	                		update(resourceTextArea,center);
	                        setUpLane(battle,txt);
	                        setUpBattleGrid();
	                        SelectedLaneShop=5;
						} catch (Exception e) {
							handleExp(e);
						}
                    	
                	}
                	if (SelectedLaneShop == 4){
                		try {
							battle.purchaseWeapon(3, battle.getOriginalLanes().get(4));
							ImageView volley = new ImageView(titansWeapons[2]);
							iVw5++;
							weaponCount(volley,NumOfVolley5);
							NumOfVolley5.setText("Volleys: "+iVw5);
							NumOfVolley5.relocate(70,468);
							center.getChildren().addAll(volley,NumOfVolley5);
	                		volley.relocate(70,470);
	                		volley.setFitWidth(50);
	                		volley.setFitHeight(50);	
	                		update(resourceTextArea,center);
	                        setUpLane(battle,txt);
	                        setUpBattleGrid();
	                        SelectedLaneShop=5;
						} catch (Exception e) {
							handleExp(e);
						}
                    	
                	}
                });      	
            	
            }
            vBoxes[i].getChildren().addAll(button, textArea);
            topHBox.getChildren().add(vBoxes[i]);
        }

        // Adding TextArea to the top right HBox
       topHBox.getChildren().add(resourceTextArea);
        // bottom window
        HBox bot = new HBox();
        Button passTurn = new Button("PassTurn");
        bot.setAlignment(Pos.CENTER);
        bot.getChildren().add(passTurn);
        passTurn.setPrefSize(80, 80);


        Button lane1_Shop= new Button("Lane 1 ");
        
        lane1_Shop.relocate(0,100);
        lane1_Shop.setStyle("-fx-background-color: Silver");
        lane1_Shop.setOnMouseClicked(event->{
        	SelectedLaneShop = 0;
        	lane1_Shop.setStyle("-fx-background-color: Green");
        	lane1_Shop.setOnMouseExited(eve->{
        		lane1_Shop.setStyle("-fx-background-color: Silver");
        	});
        });
        Button lane2_Shop= new Button("Lane 2 ");
        
        lane2_Shop.relocate(0, 270);
        lane2_Shop.setStyle("-fx-background-color: Silver");
        lane2_Shop.setOnMouseClicked(event->{
        	SelectedLaneShop = 1;
        	lane2_Shop.setStyle("-fx-background-color: Green");
        	lane2_Shop.setOnMouseExited(eve->{
        		lane2_Shop.setStyle("-fx-background-color: Silver");
        });
        });
        Button lane3_Shop= new Button("Lane 3 ");
        
        lane3_Shop.relocate(0, 400);
        lane3_Shop.setStyle("-fx-background-color: Silver");
        lane3_Shop.setOnMouseClicked(evnt->{
        	SelectedLaneShop = 2;
        	lane3_Shop.setStyle("-fx-background-color: Green");
        	lane3_Shop.setOnMouseExited(eve->{
        		lane3_Shop.setStyle("-fx-background-color: Silver");
        });
        });
      
        Button lane4_Shop= new Button("Lane 4");
        
        lane4_Shop.relocate(0, 400);
        lane4_Shop.setStyle("-fx-background-color: Silver");
        lane4_Shop.setOnMouseClicked(evnt->{
        	SelectedLaneShop = 3;
        	lane4_Shop.setStyle("-fx-background-color: Green");
        	lane4_Shop.setOnMouseExited(eve->{
        		lane4_Shop.setStyle("-fx-background-color: Silver");
        });
        });
        Button lane5_Shop= new Button("Lane 5 ");
        
        lane5_Shop.relocate(0, 400);
        lane5_Shop.setStyle("-fx-background-color: Silver");
        lane5_Shop.setOnMouseClicked(evnt->{
        	SelectedLaneShop = 4;
        	lane5_Shop.setStyle("-fx-background-color: Green");
        	lane5_Shop.setOnMouseExited(eve->{
        		lane5_Shop.setStyle("-fx-background-color: Silver");
        });
        });
 
        
        center.getChildren().addAll(lane1_Shop,lane2_Shop,lane3_Shop,lane4_Shop,lane5_Shop);
        lane1_Shop.relocate(0,91);
        lane2_Shop.relocate(0, 182);
        lane3_Shop.relocate(0, 273);
        lane4_Shop.relocate(0, 364);
        lane5_Shop.relocate(0, 455);
      
        
        root.setTop(topHBox);
        root.setCenter(center);
        root.setBottom(bot);
        root.setLeft(left);
        root.setRight(new VBox());


        //coding
        update(resourceTextArea,center);
        setUpLane(battle,txt);
        
        passTurn.setOnMouseClicked(event1->{
            try {
            battle.passTurn();
           
            setUpLane(battle,txt);
            
            setUpBattleGrid();
            update(resourceTextArea,center);
            
            }catch(Exception e) {
            	handleExp(e);
            
        }});
        
        scene = new Scene(root);
        Button menuButton = new Button("Back to Main Menu");
        menuButton.setOnAction(event -> {
        	primaryStage.setScene(saved);
        });
        scene.setOnKeyPressed(e ->{
        	if(e.getCode()==KeyCode.ESCAPE) {
        		escFunction(menuButton);
                
        	}
        });
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private void setUpBattleGrid(){

    	for ( int i = 0 ;i<titans_images.size();i++){
    		center.getChildren().remove(titans_images.get(i)); 
    	}
    	for ( int i = 0 ;i<healthOfTitans.size();i++){
    		center.getChildren().remove(healthOfTitans.get(i)); 
    	}
    	titans_images.clear();
    	double OffestWallHeight = 455/(battle.getOriginalLanes().size());
    	addWalls();
    	ArrayList<Lane> Original_lanes =battle.getOriginalLanes();
    	
    	for (int i = 0 ; i<Original_lanes.size();i++){
    		if (Original_lanes.get(i).isLaneLost()){
    			continue;
    		}
    		PriorityQueue<Titan> titans = Original_lanes.get(i).getTitans();
    		ArrayList<Titan> tmp = new ArrayList<>();
    		while(!titans.isEmpty())
    		{
    			Titan t = titans.poll();
    			tmp.add(t);
    		}
    		for (int j = 0 ;j<tmp.size();j++){
    			titans.add(tmp.get(j));
    		}
    		for (int j = 0 ;j<tmp.size();j++){
    			Titan t= tmp.get(j);			
    					
    			if ( t instanceof PureTitan){
    				ImageView pureIV = new ImageView(titansImages[0]);
    				Text health = new Text("HP: "+t.getCurrentHealth());
    				health.setFont(Font.font("Impact", FontWeight.BOLD, 16));
    				health.setFill(Color.WHITE);
    				health.setStroke(Color.GREEN);
    				center.getChildren().addAll(pureIV,health);
    				pureIV.relocate(t.getDistance()*8+200, OffestWallHeight *( i+ 0.5));
    				pureIV.setFitWidth(90);
    				pureIV.setFitHeight(90);
    				
    				health.relocate(t.getDistance()*8+200, (OffestWallHeight *( i+ 0.5))-10);
    				healthOfTitans.add(health);
    				titans_images.add(pureIV);
    				
    			}
    			else if ( t instanceof AbnormalTitan){
    				ImageView AbnormalTitanIV = new ImageView(titansImages[1]);
    				Text health = new Text("HP: "+t.getCurrentHealth());
    				health.setFont(Font.font("Impact", FontWeight.BOLD, 16));
    				health.setFill(Color.WHITE);
    				health.setStroke(Color.GREEN);
    				center.getChildren().addAll(AbnormalTitanIV,health);
    				AbnormalTitanIV.relocate(t.getDistance()*8+150, OffestWallHeight *( i+ 0.5)+10);
    				AbnormalTitanIV.setFitWidth(90);
    				AbnormalTitanIV.setFitHeight(90);
    				
    				
    				health.relocate(t.getDistance()*8+200, (OffestWallHeight *( i+ 0.5)));
    				healthOfTitans.add(health);
    				
    				titans_images.add(AbnormalTitanIV);
    			}
    			else if ( t instanceof ArmoredTitan){
    				ImageView ArmoredTitanIV = new ImageView(titansImages[2]);
    				Text health = new Text("HP: "+t.getCurrentHealth());
    				health.setFont(Font.font("Impact", FontWeight.BOLD, 16));
    				health.setFill(Color.WHITE);
    				health.setStroke(Color.GREEN);
    				center.getChildren().addAll(ArmoredTitanIV,health);
    				ArmoredTitanIV.relocate(t.getDistance()*8+150, OffestWallHeight *( i+ 0.5)+20);
    				ArmoredTitanIV.setFitWidth(90);
    				ArmoredTitanIV.setFitHeight(90);
    				
    				
    				health.relocate(t.getDistance()*8+150, (OffestWallHeight *( i+ 0.5))+10);
    				healthOfTitans.add(health);
    				
    				titans_images.add(ArmoredTitanIV);
    			}
    			else if ( t instanceof ColossalTitan){
    				ImageView ColossalTitanIV = new ImageView(titansImages[3]);
    				
    				Text health = new Text("HP: "+t.getCurrentHealth());
    				health.setFont(Font.font("Impact", FontWeight.BOLD, 16));
    				health.setFill(Color.WHITE);
    				health.setStroke(Color.GREEN);
    				
    				center.getChildren().addAll(ColossalTitanIV,health);
    				ColossalTitanIV.relocate(t.getDistance()*5+200, OffestWallHeight *( i+ 0.5)+30);
    				ColossalTitanIV.setFitWidth(90);
    				ColossalTitanIV.setFitHeight(90);
    				
    				health.relocate(t.getDistance()*5+200, OffestWallHeight *( i+ 0.5)+30);
    				healthOfTitans.add(health);
    				
    				titans_images.add(ColossalTitanIV);
    				
    			}
    			
    			
    		}
    	}
    }
    
 


    private GridPane createLeftGridPane(TextArea[] l) {
        GridPane gridPane = new GridPane();

        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setHgrow(Priority.SOMETIMES);
        columnConstraints.setMinWidth(10);
        columnConstraints.setPrefWidth(100);
        gridPane.getColumnConstraints().addAll(columnConstraints);

        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setVgrow(Priority.SOMETIMES);
        rowConstraints.setMinHeight(10);
        rowConstraints.setPrefHeight(30);
        gridPane.getRowConstraints().addAll(rowConstraints, rowConstraints, rowConstraints);

        return gridPane;
    }


    public Scene getScene() {
        return scene;
    }
    private void loadImage() {
        titansImages[0]= new Image("resources/ingame/pure.png");
        titansImages[1]=new Image("resources/ingame/abn.png");
        titansImages[2]=new Image("resources/ingame/arm.png");
        titansImages[3]=new Image("resources/ingame/redTitan.png");
        
        titansWeapons[0] = new Image("resources/ingame/piercingCanon.png");
        titansWeapons[1] = new Image("resources/ingame/sniperCanon.png");
        titansWeapons[2] = new Image("resources/ingame/volleyCannon.png");
        titansWeapons[3] = new Image("resources/ingame/trapNew.png");
        
        wall1[0]=new Image("resources/ingame/tower.png");
        wall1[1]=new Image("resources/ingame/Damgedwall.PNG");

    }
    private void update(TextArea t, Pane Pane) {
    	
        t.setText("Score : "+battle.getScore()+"          press(esc) for Menu\nCurrent turn : "+battle.getNumberOfTurns()+"\nCurrent phase : "+battle.getBattlePhase()+"\nCurrent Resources : "+battle.getResourcesGathered()+"\nAvailable Lanes : "+battle.getLanes().size());
        addWalls();
        
    }
    private void setUpLane(Battle e,TextArea [] text) {
    	
        int i=0;
        for(Lane lane : e.getOriginalLanes()) {
        	text[i].setText("Wall current health : \n"+lane.getLaneWall().getCurrentHealth()+"\n\nDanger level : \n"+lane.getDangerLevel()+"\n\nAvailable weapons : \n"+lane.getWeapons().size());
            i++;
        }
        
    }
    private void handleExp(Exception e){
    	Stage newWindowStage = new Stage();
        newWindowStage.initModality(Modality.NONE); // Does not block events to other windows
        newWindowStage.setTitle("Warning!");

        if (e instanceof InsufficientResourcesException){
        	 Label newSceneLabel = new Label(e.getMessage());
             Button closeButton = new Button("Close");
             closeButton.setAlignment(Pos.CENTER_LEFT);
             closeButton.setOnAction(event -> newWindowStage.close());

             VBox newWindowLayout = new VBox(10);
             newWindowLayout.getChildren().addAll(newSceneLabel, closeButton);
             Scene newWindowScene = new Scene(newWindowLayout, 350, 100);

             newWindowStage.setScene(newWindowScene);
             newWindowStage.show(); 
        }
        
        else  if(e instanceof InvalidLaneException){
        	 Label newSceneLabel = new Label(e.getMessage());
             Button closeButton = new Button("Close");
             closeButton.setAlignment(Pos.CENTER_LEFT);
             closeButton.setOnAction(event -> newWindowStage.close());

             VBox newWindowLayout = new VBox(10);
             newWindowLayout.getChildren().addAll(newSceneLabel, closeButton);
             Scene newWindowScene = new Scene(newWindowLayout, 350, 100);

             newWindowStage.setScene(newWindowScene);
             newWindowStage.show(); 
        }
        
        else if(e instanceof InvalidCSVFormat ){
        	 Label newSceneLabel = new Label(e.getMessage()+((InvalidCSVFormat) e).getInputLine());
             Button closeButton = new Button("Close");
             closeButton.setAlignment(Pos.CENTER_LEFT);
             closeButton.setOnAction(event -> newWindowStage.close());

             VBox newWindowLayout = new VBox(10);
             newWindowLayout.getChildren().addAll(newSceneLabel, closeButton);
             Scene newWindowScene = new Scene(newWindowLayout, 250, 100);

             newWindowStage.setScene(newWindowScene);
             newWindowStage.show(); 
        }
        
        else  if(e instanceof GameActionException){
        	 Label newSceneLabel = new Label("ERROR : Game Action Exception");
             Button closeButton = new Button("Close");
             closeButton.setAlignment(Pos.CENTER_LEFT);
             closeButton.setOnAction(event -> newWindowStage.close());

             VBox newWindowLayout = new VBox(10);
             newWindowLayout.getChildren().addAll(newSceneLabel, closeButton);
             Scene newWindowScene = new Scene(newWindowLayout, 250, 100);

             newWindowStage.setScene(newWindowScene);
             newWindowStage.show(); 
        }
        gameOver();
          
    }
    
    public boolean gameOver() {
    	boolean myIsGameOver = true;
    	for (Lane l : battle.getOriginalLanes() ){
    		if(!l.isLaneLost()){
    			myIsGameOver = false;
    		}
    	}
    		
        if (battle.isGameOver()||myIsGameOver) {
            Stage gameOverStage = new Stage();
            gameOverStage.initModality(Modality.APPLICATION_MODAL);
            gameOverStage.setTitle("Game Over");
            gameOverStage.setOnCloseRequest(e->{
            	primaryStage.setScene(saved);
            });
            Button okButton = new Button("Back to Main Menu");
            Label l = new Label ("Score: "+battle.getScore());
            l.setTextFill(Color.PALEGOLDENROD);
            okButton.setOnAction(event -> {
            gameOverStage.close();
            primaryStage.setScene(saved);
        });

            VBox vbox = new VBox(20); // 20 pixels spacing
            vbox.getChildren().addAll(okButton,l);
            vbox.setAlignment(Pos.CENTER);
            
           
            Scene scene = new Scene(vbox, 500, 400);
            BackgroundSize size = new BackgroundSize(BackgroundSize.AUTO,BackgroundSize.AUTO,true,true,true,true);
    		Background background = new Background(new BackgroundImage(new Image("resources/GameOver.JPEG"),BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT,BackgroundPosition.CENTER,size));
    		vbox.setBackground(background);
            gameOverStage.setScene(scene);
            gameOverStage.showAndWait(); // Show the window and wait for it to be closed
            return true;
        }
        return false;
    }
    
	public void addWalls(){
		ArrayList<Lane> Original_lanes =battle.getOriginalLanes(); // elly homa 3
		
		ImageView Piercing = new ImageView(titansWeapons[0]);
		center.getChildren().add(Piercing);
		Piercing.relocate(20,30);
		Piercing.setFitWidth(1);
		Piercing.setFitHeight(1);	
	
		double OffestWallHeight = 455/(Original_lanes.size());
		
    	for ( int i = 0 ;i<Original_lanes.size();i++){
    		//gridImages[1][0]
    		
    		if(WallsIv[i] == null){
    			WallsIv[i] = new ImageView (wall1[0]);
    			center.getChildren().add(WallsIv[i]);
    			WallsIv[i].relocate(150, OffestWallHeight *( i+ 0.5));
    			WallsIv[i].setFitWidth(90);
    			WallsIv[i].setFitHeight(90);
    			
    		}
    		if(Original_lanes.get(i).isLaneLost()){
    	
    			
    			WallsIv[i].setImage(wall1[1]);
    			
    			
    		}else{
    			WallsIv[i].setImage(wall1[0]);
    		}
    	}
    	
    	
	}
	private void weaponCount(ImageView p,Text t) {
		center.getChildren().removeAll(p,t);
		t.setFont(Font.font("Impact", FontWeight.NORMAL, 10));
		t.setFill(Color.WHITE);
		t.setStroke(Color.BLACK);	

	}
	private void escFunction(Button b) {
		VBox menuHub = new VBox();
		menuHub.setAlignment(Pos.CENTER);
		menuHub.getChildren().add(b);
		Scene menu = new Scene(menuHub,1280, 720);
			primaryStage.setScene(menu);
			menu.setOnKeyPressed(e ->{
	        	if(e.getCode()==KeyCode.ESCAPE) {
	        		primaryStage.setScene(scene);
	                
	        	}
	        });
	}
	
	

}
